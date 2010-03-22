package com.zving.cms.dataservice;

import com.zving.cms.document.Article;
import com.zving.framework.Page;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.schema.ZCCatalogSchema;
import com.zving.schema.ZDUserSchema;
import java.util.Date;

public class PublishStaff extends Page {
	public static Mapx initStaff(Mapx params) {
		String dateStr = DateUtil.toString(new Date(), "yyyy-MM-dd");
		Mapx map = new Mapx();
		map.put("today", dateStr);
		return map;
	}

	public static Mapx initInputor(Mapx params) {
		String dateStr = DateUtil.toString(new Date(), "yyyy-MM-dd");
		Mapx map = new Mapx();
		map.put("today", dateStr);
		String username = params.getString("Username");
		ZDUserSchema user = new ZDUserSchema();
		user.setUserName(username);
		if (user.fill()) {
			map.putAll(user.toMapx());
		}
		map.put("Username", username);
		return map;
	}

	public static Mapx initInputorColumn(Mapx params) {
		String dateStr = DateUtil.toString(new Date(), "yyyy-MM-dd");
		Mapx map = new Mapx();
		map.put("today", dateStr);
		String username = params.getString("ColumnInputor");
		String catalogID = params.getString("CatalogID");
		String catalogInnerCode = params.getString("CatalogInnerCode");
		map.put("CatalogID", catalogID);
		map.put("CatalogInnerCode", catalogInnerCode);
		ZDUserSchema user = new ZDUserSchema();
		user.setUserName(username);
		if (user.fill()) {
			map.putAll(user.toMapx());
		}
		map.put("ColumnInputor", username);

		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(catalogID);
		if (catalog.fill()) {
			map.put("CatalogName", catalog.getName());
		}

		return map;
	}

	public static void dg1DataBind(DataGridAction dga) {
		StringBuffer condition = new StringBuffer("");
		String startDate = dga.getParam("startDate");
		String endDate = dga.getParam("endDate");
		if ((StringUtil.isNotEmpty(startDate)) && (StringUtil.isNotEmpty(endDate))) {
			endDate = DateUtil.toString(DateUtil.addDay(DateUtil.parse(endDate, "yyyy-MM-dd"), 1),
					"yyyy-MM-dd");
			condition.append(" and a.AddTime>'" + startDate + "' and a.AddTime<'" + endDate + "'");
		}

		String sqlData = "select a.AddUser,b.RealName as Inputor,a.BranchInnerCode, c.name as branchname, sum(1) as ArticleCount, sum(case when a.Status in (30,50) then 1 else 0 end) as PublishCount from zcarticle a left outer join zduser b on a.AddUser=b.UserName left outer join  zdbranch c on c.BranchInnerCode=b.BranchInnerCode  where a.SiteID=? and a.Type='1'";

		String sqlTotal = "select count(distinct AddUser) from zcarticle where SiteID=? and Type='1'";
		DataTable dt = new QueryBuilder(
				sqlData
						+ condition
						+ " group by a.AddUser,b.realname,a.branchinnercode,c.name order by a.BranchInnerCode",
				Application.getCurrentSiteID()).executePagedDataTable(dga.getPageSize(), dga
				.getPageIndex());
		int total = new QueryBuilder(sqlTotal, Application.getCurrentSiteID()).executeInt();
		dga.setTotal(total);
		dga.bindData(dt);
	}

	public static void dg11DataBind(DataGridAction dga) {
		StringBuffer condition = new StringBuffer("");
		String startDate = dga.getParam("startDate");
		String endDate = dga.getParam("endDate");
		String username = dga.getParam("Username");

		if (StringUtil.isNotEmpty(username)) {
			condition.append(" and AddUser='" + username + "'");
		}

		if ((StringUtil.isNotEmpty(startDate)) && (StringUtil.isNotEmpty(endDate))) {
			endDate = DateUtil.toString(DateUtil.addDay(DateUtil.parse(endDate, "yyyy-MM-dd"), 1),
					"yyyy-MM-dd");
			condition.append(" and AddTime>'" + startDate + "' and AddTime<'" + endDate + "'");
		}

		String sqlData = "select a.Name as CatalogName,a.ID,a.InnerCode,a.TreeLevel,a.SingleFlag,a.OrderFlag,b.*  from zccatalog a left outer join  (select CatalogID,CatalogInnerCode,sum(1) as ArticleCount, sum(case when Status in ('4','5') then 1 else 0 end) as PublishCount  from zcarticle where SiteID=? and Type='1' "
				+ condition
				+ " group by CatalogID,CatalogInnerCode) b"
				+ " on a.ID=b.CatalogID where a.SiteID=? and a.Type not in ('4','5','6','7')";
		String sqlTotal = "select count(*) from zccatalog where SiteID=? and Type not in ('4','5','6','7')";
		DataTable dt = new QueryBuilder(sqlData + "  order by InnerCode", Application
				.getCurrentSiteID(), Application.getCurrentSiteID()).executeDataTable();
		statDataTable(dt);
		dt.insertColumn("Padding");
		for (int i = 0; i < dt.getRowCount(); ++i) {
			String treelevel = dt.getString(i, "TreeLevel");
			int level = Integer.parseInt(treelevel);
			dt.set(i, "Padding", (level - 1) * 20);
		}

		int total = new QueryBuilder(sqlTotal, Application.getCurrentSiteID()).executeInt();
		dga.setTotal(total);
		dga.bindData(dt);
	}

	public static void dg12DataBind(DataGridAction dga) {
		StringBuffer condition = new StringBuffer("");
		String startDate = dga.getParam("startDate");
		String endDate = dga.getParam("endDate");
		String username = dga.getParam("ColumnInputor");
		String catalogInnerCode = dga.getParam("CatalogInnerCode");

		if (StringUtil.isNotEmpty(username)) {
			condition.append(" and AddUser='" + username + "'");
		}

		if (StringUtil.isNotEmpty(catalogInnerCode)) {
			condition.append(" and CatalogInnerCode like '" + catalogInnerCode + "%'");
		}

		if ((StringUtil.isNotEmpty(startDate)) && (StringUtil.isNotEmpty(endDate))) {
			endDate = DateUtil.toString(DateUtil.addDay(DateUtil.parse(endDate, "yyyy-MM-dd"), 1),
					"yyyy-MM-dd");
			condition.append(" and AddTime>'" + startDate + "' and AddTime<'" + endDate + "'");
		}

		String sqlData = "select zcarticle.*,(case when ModifyTime is null then AddTime else ModifyTime end) as LastModifyTime from zcarticle where SiteID=? and Type='1' "
				+ condition + "  order by CatalogInnerCode,OrderFlag desc";
		String sqlTotal = "select count(*) from zcarticle where SiteID=? and Type ='1' "
				+ condition;
		DataTable dt = new QueryBuilder(sqlData, Application.getCurrentSiteID())
				.executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
		dt.decodeColumn("Status", Article.STATUS_MAP);
		int total = new QueryBuilder(sqlTotal, Application.getCurrentSiteID()).executeInt();
		dga.setTotal(total);
		dga.bindData(dt);
	}

	public static void statDataTable(DataTable dt) {
		if (dt == null) {
			return;
		}
		int rowCount = dt.getRowCount();
		for (int i = 0; i < rowCount; ++i) {
			String articleCount = (dt.get(i, "ArticleCount") == null) ? null : dt.getString(i,
					"ArticleCount");
			String publishCount = (dt.get(i, "PublishCount") == null) ? null : dt.getString(i,
					"PublishCount");
			String[] arr = getArticleCount(dt, i);
			int columnArticleCount = Integer.parseInt(arr[0]);
			int columnPublishCount = Integer.parseInt(arr[1]);
			if (StringUtil.isEmpty(articleCount)) {
				dt.set(i, "ArticleCount", columnArticleCount);
			}
			if (StringUtil.isEmpty(publishCount))
				dt.set(i, "PublishCount", columnPublishCount);
		}
	}

	public static String[] getArticleCount(DataTable dt, int index) {
		if (dt == null) {
			return new String[] { "0", "0" };
		}
		int columnArticleCount = 0;
		int columnPublishCount = 0;
		int rowCount = dt.getRowCount();
		String innerCode = dt.getString(index, "InnerCode");
		for (int i = index + 1; i < rowCount; ++i) {
			String code = dt.getString(i, "InnerCode");
			String articleCount = (dt.get(i, "ArticleCount") == null) ? null : dt.getString(i,
					"ArticleCount");
			String publishCount = (dt.get(i, "PublishCount") == null) ? null : dt.getString(i,
					"PublishCount");
			if (!(code.startsWith(innerCode)))
				break;
			int count;
			if (StringUtil.isNotEmpty(articleCount)) {
				count = Integer.parseInt(articleCount);
				columnArticleCount += count;
			}
			if (StringUtil.isNotEmpty(publishCount)) {
				count = Integer.parseInt(publishCount);
				columnPublishCount += count;
			}

		}

		return new String[] { String.valueOf(columnArticleCount),
				String.valueOf(columnPublishCount) };
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.dataservice.PublishStaff JD-Core Version: 0.5.3
 */