package com.zving.cms.dataservice;

import com.zving.cms.pub.SiteUtil;
import com.zving.cms.resource.AudioLib;
import com.zving.cms.resource.ImageLib;
import com.zving.cms.resource.VideoLib;
import com.zving.cms.site.Catalog;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.controls.TreeAction;
import com.zving.framework.controls.TreeItem;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.schedule.CronManager;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCFullTextSchema;
import com.zving.schema.ZCFullTextSet;
import com.zving.schema.ZDScheduleSchema;
import com.zving.schema.ZDScheduleSet;
import java.util.Date;
import java.util.List;

public class FullText extends Page {
	public static Mapx init(Mapx map) {
		return map;
	}

	public static void treeDataBind(TreeAction ta) {
		if ("Article".equals(ta.getParam("Type"))) {
			Catalog.treeDataBind(ta);
		}
		if ("Image".equals(ta.getParam("Type"))) {
			ImageLib.treeDataBind(ta);
		}
		if ("Video".equals(ta.getParam("Type"))) {
			VideoLib.treeDataBind(ta);
		}
		if ("Radio".equals(ta.getParam("Type"))) {
			AudioLib.treeDataBind(ta);
		}
		if ((ta.getDataSource() != null) && (StringUtil.isEmpty(ta.getParam("ParentID")))) {
			DataTable dt = ta.getDataSource();
			dt.insertRow(new Object[dt.getColCount()], 0);
			dt.set(0, 0, "-1");
			dt.set(0, 1, "0");
			dt.set(0, 2, "1");
			dt.set(0, 3, "<font color='red'>全部</font>");
			ta.bindData(dt);
			if ("Article".equals(ta.getParam("Type"))) {
				List items = ta.getItemList();
				for (int i = 1; i < items.size(); ++i) {
					TreeItem item = (TreeItem) items.get(i);
					if ("Y".equals(item.getData().getString("SingleFlag")))
						item.setIcon("Icons/treeicon11.gif");
				}
			}
		}
	}

	public static void dg1DataBind(DataGridAction dga) {
		DataTable dt = new QueryBuilder("select * from ZCFullText where siteid=?", Application
				.getCurrentSiteID()).executeDataTable();
		Mapx map = new Mapx();
		map.put("Article", "文章检索");
		map.put("Image", "图片检索");
		map.put("Video", "视频检索");
		map.put("Radio", "音频检索");
		dt.decodeColumn("Type", map);
		dga.bindData(dt);
	}

	public void add() {
		Transaction tran = new Transaction();
		ZCFullTextSchema ft = new ZCFullTextSchema();
		if (StringUtil.isEmpty($V("ID"))) {
			ft.setValue(this.Request);
			ft.setID(NoUtil.getMaxID("FullTextID"));
			ft.setSiteID(Application.getCurrentSiteID());
			ft.setAddTime(new Date());
			ft.setAddUser(User.getUserName());
			tran.add(ft, 1);
		} else {
			ft.setID(Long.parseLong($V("ID")));
			ft.fill();
			ft.setValue(this.Request);
			ft.setModifyTime(new Date());
			ft.setModifyUser(User.getUserName());
			tran.add(ft, 2);
		}
		if (tran.commit())
			this.Response.setMessage("保存成功");
		else
			this.Response.setError("发生错误,保存失败");
	}

	public void del() {
		String ids = $V("IDs");
		Transaction tran = new Transaction();
		String[] arr = ids.split("\\,");
		for (int i = 0; i < arr.length; ++i) {
			tran.add(new QueryBuilder("delete from ZCFullText where id=?", arr[i]));
			tran.add(new QueryBuilder(
					"delete from ZDSchedule where SourceID=? and TypeCode='IndexMaintenance'",
					arr[i]));
		}
		if (tran.commit())
			this.Response.setMessage("删除成功");
		else
			this.Response.setError("发生错误,删除失败");
	}

	public void manual() {
		String ids = $V("IDs");
		String[] arr = ids.split("\\,");
		FullTextTaskManager ftm = (FullTextTaskManager) CronManager.getInstance()
				.getCronTaskManager("IndexMaintenance");
		for (int i = 0; i < arr.length; ++i) {
			long id = Long.parseLong(arr[i]);
			if (ftm.isRunning(id)) {
				this.Response.setMessage("索引维护定时任务正在进行，请稍候重试!");
				return;
			}
			String file = Config.getContextRealPath() + "WEB-INF/data/index/" + id + "/time.lock";
			FileUtil.delete(file);
			ftm.execute(id);
			do
				try {
					Thread.sleep(100L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			while (ftm.isRunning(id));
		}

		this.Response.setMessage("生成索引成功");
	}

	public static void dealAutoIndex(long siteID, boolean autoIndex) {
		ZCFullTextSchema ft = new ZCFullTextSchema();
		ft.setSiteID(siteID);
		ft.setProp1("AutoIndex");
		ZCFullTextSet set = ft.query();
		long sourceID;
		ZDScheduleSchema sd;
		ZDScheduleSet sdSet;
		if (!(autoIndex)) {
			if (set.size() == 0) {
				return;
			}
			sourceID = set.get(0).getID();
			sd = new ZDScheduleSchema();
			sd.setSourceID(sourceID);
			sdSet = sd.query();
			if (sdSet.size() == 0) {
				return;
			}
			sd = sdSet.get(0);
			if ("N".equals(sd.getIsUsing())) {
				return;
			}
			sd.setIsUsing("N");
			sd.update();
		} else if (set.size() == 0) {
			ft.setID(NoUtil.getMaxID("FullTextID"));
			ft.setRelaText("-1");
			ft.setType("Article");
			ft.setName("全站索引-" + SiteUtil.getName(ft.getSiteID()));
			ft.setCode("AllArticle");
			ft.setAddUser("SYS");
			ft.setAddTime(new Date());

			sd = new ZDScheduleSchema();
			sd.setAddTime(new Date());
			sd.setAddUser("SYS");
			sd.setCronExpression("*/3 * * * *");
			sd.setID(NoUtil.getMaxID("ScheduleID"));
			sd.setIsUsing("Y");
			sd.setPlanType("Period");
			sd.setTypeCode("IndexMaintenance");
			sd.setStartTime(new Date());
			sd.setSourceID(ft.getID());

			Transaction tran = new Transaction();
			tran.add(ft, 1);
			tran.add(sd, 1);
			tran.commit();
		} else {
			ft = set.get(0);
			sourceID = set.get(0).getID();
			sd = new ZDScheduleSchema();
			sd.setSourceID(sourceID);
			sdSet = sd.query();
			if (sdSet.size() == 0) {
				sd.setAddTime(new Date());
				sd.setAddUser("SYS");
				sd.setCronExpression("*/3 * * * *");
				sd.setID(NoUtil.getMaxID("ScheduleID"));
				sd.setIsUsing("Y");
				sd.setPlanType("Period");
				sd.setTypeCode("IndexMaintenance");
				sd.setStartTime(new Date());
				sd.setSourceID(ft.getID());
				sd.insert();
			}
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.dataservice.FullText JD-Core Version: 0.5.3
 */