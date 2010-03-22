package com.zving.cms.dataservice;

import com.zving.cms.pub.SiteUtil;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCAdPositionSchema;
import com.zving.schema.ZCAdPositionSet;
import com.zving.schema.ZCAdvertisementSchema;
import com.zving.schema.ZCAdvertisementSet;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class AdvertiseLayout extends Page {
	public static Mapx PosTypes = new Mapx();

	static {
		PosTypes.put("banner", "矩形横幅");
		PosTypes.put("fixure", "固定位置");
		PosTypes.put("float", "漂浮移动");
		PosTypes.put("couplet", "对联广告");
		PosTypes.put("imagechange", "图片轮换广告");
		PosTypes.put("code", "代码调用");
	}

	public static void dg1DataBind(DataGridAction dga) {
		String SearchContent = dga.getParam("SearchContent");
		StringBuffer conditions = new StringBuffer();
		conditions.append(" where SiteID = ?");
		if (StringUtil.isNotEmpty(SearchContent)) {
			conditions.append(" and PositionName like '");
			conditions.append("%");
			conditions.append(SearchContent.trim());
			conditions.append("%'");
		}

		dga.setTotal(new QueryBuilder("select count(*) from zcadposition" + conditions, Application
				.getCurrentSiteID()));
		QueryBuilder qb = new QueryBuilder(
				"select a.id id,a.SiteID SiteID,a.PositionName PositionName,a.PositionType PositionType,a.Description Description,(select AdType from zcadvertisement c where a.id=c.positionid and c.isopen='Y') AdType,a.JSName,a.PositionWidth PositionSizeWidth,a.PositionHeight PositionSizeHeight from zcadposition a "
						+ conditions + " order by a.id desc", Application.getCurrentSiteID());
		DataTable dt = qb.executePagedDataTable(dga.getPageSize(), dga.getPageIndex());

		dt.decodeColumn("ADType", Advertise.ADTypes);
		dt.decodeColumn("PositionType", PosTypes);
		dga.bindData(dt);
	}

	public static Mapx DialogInit(Mapx params) {
		String id = (String) params.get("ID");

		if (StringUtil.isNotEmpty(id)) {
			ZCAdPositionSchema position = new ZCAdPositionSchema();
			position.setID(id);
			position.fill();
			params.putAll(position.toMapx());
		}
		return params;
	}

	public void add() {
		ZCAdPositionSchema position = new ZCAdPositionSchema();
		String id = $V("ID");
		String PositionName = $V("PositionName");
		int NameCount;
		if (StringUtil.isNotEmpty(id)) {
			position.setID(id);
			position.fill();
			if (!(PositionName.equals(position.getPositionName()))) {
				NameCount = new QueryBuilder(
						"select count(*) from zcadposition where PositionName = ?", PositionName)
						.executeInt();
				if (NameCount > 0) {
					this.Response.setLogInfo(0, "已经有同名的版位，请您重新填写版位名");
					return;
				}
			}
			position.setCode(id);
			position.setModifyUser(User.getUserName());
			position.setModifyTime(new Date());
			position.setJsName(createJS("modify", position));
		} else {
			NameCount = new QueryBuilder(
					"select count(*) from zcadposition where PositionName = ?", PositionName)
					.executeInt();
			if (NameCount > 0) {
				this.Response.setLogInfo(0, "已经有同名的版位，请您重新填写版位名");
				return;
			}
			position.setID(NoUtil.getMaxID("AdPositionID"));
			position.setCode(String.valueOf(position.getID()));
			position.setAddUser(User.getUserName());
			position.setAddTime(new Date());
			position.setJsName(createJS("add", position));
		}
		position.setSiteID(Application.getCurrentSiteID());
		position.setValue(this.Request);

		if (StringUtil.isNotEmpty(id)) {
			ZCAdvertisementSchema adv = new ZCAdvertisementSchema();
			ZCAdvertisementSet advset;
			if ($V("hPositionType").equals($V("PositionType"))) {
				advset = new ZCAdvertisementSchema().query(new QueryBuilder(
						"where positionid=? and isopen='Y'", id));

				if (advset.size() != 0) {
					adv = advset.get(0);
					if (!(Advertise.CreateJSCode(adv, position))) {
						this.Response.setStatus(0);
						this.Response.setMessage("生成广告js发生错误!");
					}
				}
			} else {
				advset = new ZCAdvertisementSchema().query(new QueryBuilder("where positionid=?",
						id));
				if (advset.size() > 0) {
					Transaction trans = new Transaction();
					trans.add(advset, 5);
					trans.commit();
				}
			}

		}

		if (StringUtil.isNotEmpty(id)) {
			if (position.update())
				this.Response.setLogInfo(1, "修改成功");
			else {
				this.Response.setLogInfo(0, "发生错误");
			}
		} else if (position.insert())
			this.Response.setLogInfo(1, "新增成功");
		else
			this.Response.setLogInfo(0, "发生错误");
	}

	public void del() {
		String ids = $V("IDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}
		Transaction trans = new Transaction();
		ZCAdPositionSchema ad = new ZCAdPositionSchema();
		ZCAdvertisementSchema adv = new ZCAdvertisementSchema();
		ZCAdPositionSet set = ad.query(new QueryBuilder("where id in (" + ids + ")"));
		ZCAdvertisementSet adSet = adv.query(new QueryBuilder("where PositionID in (" + ids + ")"));
		trans.add(set, 3);
		trans.add(adSet, 3);
		if (trans.commit()) {
			for (int i = 0; i < set.size(); ++i) {
				ZCAdPositionSchema position = set.get(i);
				String JSPath = Config.getContextRealPath() + Config.getValue("UploadDir") + "/"
						+ SiteUtil.getAlias(position.getSiteID()) + "/" + position.getJsName();
				File file = new File(JSPath);
				if (file.exists()) {
					file.delete();
				}
			}
			this.Response.setLogInfo(1, "删除版位成功！");
		} else {
			this.Response.setLogInfo(0, "操作数据库时发生错误!");
		}
	}

	public void copy() {
		String id = $V("ID");
		ZCAdPositionSchema ad = new ZCAdPositionSchema();
		ad.setID(Long.parseLong(id));
		ad.fill();
		String PositionName = ad.getPositionName();
		PositionName = "复制  " + PositionName;
		ad.setID(NoUtil.getMaxID("AdPositionID"));
		int count = 0;
		String Code = ad.getCode();
		do {
			Code = "CopyOf" + Code;
			count = new QueryBuilder("select count(*) from zcadposition where Code = ?", Code)
					.executeInt();
		} while (count > 0);
		ad.setCode(Code);
		ad.setAddUser(User.getUserName());
		ad.setAddTime(new Date());
		ad.setJsName(createJS("copy", ad));
		ad.setPositionName(PositionName);
		if (ad.insert()) {
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("发生错误!");
		}
	}

	public static String createJS(String type, ZCAdPositionSchema adp) {
		String path = Config.getContextRealPath() + Config.getValue("UploadDir") + "/"
				+ Application.getCurrentSiteAlias() + "/js/";
		String oldPath = adp.getJsName();
		File f = new File(path);
		if (!(f.exists())) {
			f.mkdir();
		}
		String filename = "";
		File file;
		if (type.equalsIgnoreCase("add")) {
			filename = adp.getCode() + ".js";
			path = path + filename;
			file = new File(path);
			if (file.exists())
				file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			path = "js/" + filename;
		} else if (type.equalsIgnoreCase("modify")) {
			if ("".equals(oldPath)) {
				filename = adp.getCode() + ".js";
				path = path + filename;
				file = new File(path);
				if (file.exists())
					file.delete();
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				path = "js/" + filename;
			} else {
				path = Config.getContextRealPath() + Config.getValue("UploadDir") + "/"
						+ Application.getCurrentSiteAlias() + "/" + oldPath;
				FileUtil.delete(path);
				file = new File(path);
				if (!(file.exists())) {
					try {
						file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				path = oldPath;
			}
		} else if (type.equalsIgnoreCase("copy")) {
			filename = adp.getCode() + ".js";
			path = path + filename;
			file = new File(path);
			if (file.exists())
				file.delete();
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			path = "js/" + filename;
		}
		return path;
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.dataservice.AdvertiseLayout JD-Core Version: 0.5.3
 */