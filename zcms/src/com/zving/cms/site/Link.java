package com.zving.cms.site;

import com.zving.cms.pub.SiteUtil;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.platform.pub.OrderUtil;
import com.zving.schema.ZCLinkSchema;
import com.zving.schema.ZCLinkSet;
import java.util.Date;

public class Link extends Page {
	public static void dg1DataBind(DataGridAction dga) {
		Object o = dga.getParam("LinkGroupID");
		String sql1 = "select ZCLink.*,(select name from zclinkgroup where id=zclink.linkgroupID) as LinkGroupName from ZCLink ";
		String wherePart = "";
		QueryBuilder qb = new QueryBuilder();

		if ((o != null) && (!("".equals(o)))) {
			wherePart = " where LinkGroupID = ?";
			qb.add(Long.parseLong(o.toString()));
		} else {
			wherePart = " where exists (select '' from ZCLinkGroup where ZCLinkGroup.ID = ZCLink.LinkGroupID and SiteID=?)";
			qb.add(Application.getCurrentSiteID());
		}
		sql1 = sql1 + wherePart;
		sql1 = sql1 + " order by OrderFlag asc,id desc";

		if (dga.getTotal() == 0) {
			qb.setSQL("select count(1) from ZCLink " + wherePart);
			dga.setTotal(qb);
		}
		qb.setSQL(sql1);
		dga.bindData(qb);
	}

	public static Mapx initDialog(Mapx params) {
		String ID = params.getString("ID");
		String type = params.getString("Type");
		if (StringUtil.isNotEmpty(ID)) {
			ZCLinkSchema link = new ZCLinkSchema();
			link.setID(ID);
			link.fill();
			Mapx map = link.toMapx();
			map.put("Type", type);
			map.put("ImageSrc", Config.getContextPath() + Config.getValue("UploadDir") + "/"
					+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/"
					+ link.getImagePath().replaceAll("//", "/"));
			return map;
		}
		params.put("LinkGroupID", params.getString("LinkGroupID"));
		params.put("Type", type);
		params.put("URL", "http://");
		params.put("ImageSrc", Config.getContextPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID())
				+ "/upload/Image/nopicture.jpg".replaceAll("//", "/"));
		return params;
	}

	public void save() {
		DataTable dt = (DataTable) this.Request.get("DT");
		Transaction trans = new Transaction();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			QueryBuilder qb = new QueryBuilder(
					"update ZCLink set Name=?,URL=?,ModifyUser=?,ModifyTime=? where ID=?");
			qb.add(dt.getString(i, "Name"));
			qb.add(dt.getString(i, "URL"));
			qb.add(User.getUserName());
			qb.add(new Date());
			qb.add(dt.getString(i, "ID"));
			trans.add(qb);
		}
		if (trans.commit())
			this.Response.setLogInfo(1, "修改成功!");
		else
			this.Response.setLogInfo(0, "修改失败!");
	}

	public void edit() {
		Transaction trans = new Transaction();
		String ID = $V("ID");
		if ((StringUtil.isEmpty(ID)) || (!(StringUtil.isDigit(ID)))) {
			this.Response.setLogInfo(0, "传入ID错误");
			return;
		}

		ZCLinkSchema link = new ZCLinkSchema();
		link.setID(ID);
		link.fill();
		link.setValue(this.Request);
		String ImageID = $V("ImageID");
		if (StringUtil.isNotEmpty(ImageID)) {
			DataTable dt = new QueryBuilder("select path,srcfilename from zcimage where id=?",
					ImageID).executeDataTable();
			link.setImagePath(dt.get(0, "path").toString()
					+ dt.get(0, "srcfilename").toString().replaceAll("//", "/"));
		}
		link.setModifyUser(User.getUserName());
		link.setModifyTime(new Date());
		trans.add(link, 2);
		if (trans.commit())
			this.Response.setLogInfo(1, "修改成功!");
		else
			this.Response.setLogInfo(0, "修改失败!");
	}

	public void add() {
		ZCLinkSchema link = new ZCLinkSchema();
		link.setID(NoUtil.getMaxID("LinkID"));
		link.setValue(this.Request);
		String ImageID = $V("ImageID");
		if (StringUtil.isNotEmpty(ImageID)) {
			DataTable dt = new QueryBuilder("select path,srcfilename from zcimage where id=?",
					ImageID).executeDataTable();
			link.setImagePath(dt.get(0, "path").toString()
					+ dt.get(0, "srcfilename").toString().replaceAll("//", "/"));
		} else {
			link.setImagePath("upload/Image/nopicture.jpg");
		}
		link.setOrderFlag(OrderUtil.getDefaultOrder());
		link.setSiteID(Application.getCurrentSiteID());
		link.setAddUser(User.getUserName());
		link.setAddTime(new Date());
		if (link.insert())
			this.Response.setLogInfo(1, "新增成功!");
		else
			this.Response.setLogInfo(0, "新增失败!");
	}

	public void del() {
		String ids = $V("IDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setLogInfo(0, "传入ID时发生错误！");
			return;
		}
		Transaction trans = new Transaction();
		ZCLinkSchema link = new ZCLinkSchema();
		ZCLinkSet set = link.query(new QueryBuilder("where id in (" + ids + ")"));
		trans.add(set, 3);

		if (trans.commit())
			this.Response.setLogInfo(1, "删除成功！");
		else
			this.Response.setLogInfo(0, "删除数据时发生错误!");
	}

	public void getPicSrc() {
		String ID = $V("PicID");
		DataTable dt = new QueryBuilder("select path,srcfilename from zcimage where id=?", ID)
				.executeDataTable();
		if (dt.getRowCount() > 0)
			this.Response.put("picSrc", Config.getContextPath() + Config.getValue("UploadDir")
					+ "/" + SiteUtil.getAlias(Application.getCurrentSiteID()) + "/"
					+ dt.get(0, "path").toString()
					+ dt.get(0, "srcfilename").toString().replaceAll("//", "/"));
	}

	public void sortColumn() {
		String target = $V("Target");
		String orders = $V("Orders");
		String type = $V("Type");
		String linkGroupID = $V("LinkGroupID");
		if ((!(StringUtil.checkID(target))) && (!(StringUtil.checkID(orders)))) {
			return;
		}
		if (OrderUtil.updateOrder("ZCLink", type, target, orders, " LinkGroupID = " + linkGroupID))
			this.Response.setMessage("排序成功");
		else
			this.Response.setError("排序失败");
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.site.Link JD-Core Version: 0.5.3
 */