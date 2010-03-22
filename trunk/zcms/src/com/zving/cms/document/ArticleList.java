package com.zving.cms.document;

import com.zving.cms.datachannel.Publisher;
import com.zving.cms.pub.CatalogUtil;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
import com.zving.framework.controls.TreeAction;
import com.zving.framework.controls.TreeItem;
import com.zving.framework.data.DataAccess;
import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.messages.LongTimeTask;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.Errorx;
import com.zving.framework.utility.Filter;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.Priv;
import com.zving.platform.pub.NoUtil;
import com.zving.platform.pub.OrderUtil;
import com.zving.schema.ZCArticleLogSchema;
import com.zving.schema.ZCArticleSchema;
import com.zving.schema.ZCArticleSet;
import com.zving.schema.ZCCommentSchema;
import com.zving.schema.ZCCommentSet;
import com.zving.schema.ZCImageRelaSchema;
import com.zving.schema.ZCImageRelaSet;
import com.zving.schema.ZDColumnValueSchema;
import com.zving.schema.ZDColumnValueSet;
import com.zving.schema.ZWCurrentStepSchema;
import com.zving.schema.ZWCurrentStepSet;
import com.zving.workflow.Workflow;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ArticleList extends Page {
	public static void magazineListDataBind(DataGridAction dga) {
		String catalogID = (String) dga.getParams().get("CatalogID");
		if (StringUtil.isEmpty(catalogID)) {
			catalogID = dga.getParams().getString("Cookie.DocList.LastMagazineCatalog");
			if ((StringUtil.isEmpty(catalogID)) || ("null".equals(catalogID))) {
				catalogID = "0";
			}
			dga.getParams().put("CatalogID", catalogID);
		}
		dg1DataBind(dga);
	}

	public static void dg1DataBind(DataGridAction dga) {
		String catalogID = (String) dga.getParams().get("CatalogID");
		if (StringUtil.isEmpty(catalogID)) {
			catalogID = dga.getParams().getString("Cookie.DocList.LastCatalog");
			if ((StringUtil.isEmpty(catalogID)) || ("null".equals(catalogID))) {
				catalogID = "0";
			}
			dga.getParams().put("CatalogID", catalogID);
		}
		String keyword = (String) dga.getParams().get("Keyword");
		String startDate = (String) dga.getParams().get("StartDate");
		String endDate = (String) dga.getParams().get("EndDate");
		String condition = "";
		QueryBuilder qb = new QueryBuilder();
		qb.add(catalogID);
		if (StringUtil.isNotEmpty(keyword)) {
			condition = " and title like ? ";
			qb.add("%" + keyword.trim() + "%");
		}
		if (StringUtil.isNotEmpty(startDate)) {
			condition = condition + " and publishdate >= ? ";
			qb.add(startDate);
		}
		if (StringUtil.isNotEmpty(endDate)) {
			condition = condition + " and publishdate <= ? ";
			qb.add(endDate);
		}

		String sql = "select ID,Title,AddUser,PublishDate,Addtime,Status,WorkFlowID,Type,TopFlag,OrderFlag,TitleStyle,TopDate from ZCArticle where CatalogID=?"
				+ condition;
		String sql2 = "select count(*) from ZCArticle where catalogid=? " + condition;

		String listType = (String) dga.getParams().get("Type");
		if (StringUtil.isEmpty(listType)) {
			listType = "ALL";
		}
		if ("ADD".equals(listType)) {
			sql = sql + " and adduser='" + User.getUserName() + "'";
			sql2 = sql2 + " and adduser='" + User.getUserName() + "'";
		} else if ("WORKFLOW".equals(listType)) {
			sql = sql + " and status=10";
			sql2 = sql2 + " and status=10";
		} else if ("TOPUBLISH".equals(listType)) {
			sql = sql + " and status=20";
			sql2 = sql2 + " and status=20";
		} else if ("PUBLISHED".equals(listType)) {
			sql = sql + " and status=30";
			sql2 = sql2 + " and status=30";
		} else if ("OFFLINE".equals(listType)) {
			sql = sql + " and status=40";
			sql2 = sql2 + " and status=40";
		} else if ("ARCHIVE".equals(listType)) {
			sql = sql + " and status=50";
			sql2 = sql2 + " and status=50";
		} else if ("ALL".equals(listType)) {
			sql = sql;
			sql2 = sql2;
		} else if ("Member".equals(listType)) {
			sql = sql + " and Prop4 = 'Member' ";
			sql2 = sql2 + " and Prop4 = 'Member' ";
		} else if ("Editer".equals(listType)) {
			sql = sql + " and Prop4  is null ";
			sql2 = sql2 + " and Prop4  is null ";
		}
		sql = sql + dga.getSortString();

		if (StringUtil.isNotEmpty(dga.getSortString()))
			sql = sql + " ,orderflag desc";
		else {
			sql = sql + " order by topflag desc,orderflag desc";
		}

		qb.setSQL(sql2);
		dga.setTotal(qb);

		qb.setSQL(sql);
		DataTable dt = qb.executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
		if ((dt != null) && (dt.getRowCount() > 0)) {
			dt.decodeColumn("Status", Article.STATUS_MAP);
			dt.getDataColumn("PublishDate").setDateFormat("yy-MM-dd HH:mm");
		}

		DataAccess da = new DataAccess();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			String topFlag = dt.getString(i, "TopFlag");
			String topdate = "永久置顶";
			if (StringUtil.isNotEmpty(dt.getString(i, "TopDate"))) {
				topdate = DateUtil.toString((Date) dt.get(i, "TopDate"), "yyyy-MM-dd HH:mm:ss");
			}
			if ("1".equals(topFlag))
				dt.set(i, "TopFlag", "<img src='../Framework/Images/icon_up.gif' title='有效期限: "
						+ topdate + "'>");
			else {
				dt.set(i, "TopFlag", "");
			}
			if (dt.getString(i, "Status").equals("10")) {
				String workflowDefID = new QueryBuilder(
						"select workflow from zccatalog where id =?", catalogID).executeString();
				Workflow workFlow = new Workflow(workflowDefID, da);
				long workFlowID = Long.parseLong(dt.getString(i, "WorkFlowID"));
				ZWCurrentStepSet currentSteps = workFlow.findCurrentSteps(workFlowID);
				ZWCurrentStepSchema step = workFlow.getCurrentStep(10, currentSteps);
				dt.set(i, "StatusName", step.getStatus());
			}
		}
		try {
			da.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		dga.bindData(dt);
	}

	public static void dialogDg1DataBind(DataGridAction dga) {
		String catalogID = (String) dga.getParams().get("CatalogID");
		if (StringUtil.isEmpty(catalogID)) {
			catalogID = "0";
		}
		String keyword = (String) dga.getParams().get("Keyword");
		String condition = "";
		QueryBuilder qb = new QueryBuilder();
		qb.add(catalogID);
		if (StringUtil.isNotEmpty(keyword)) {
			condition = " and title like ?";
			qb.add("%" + keyword.trim() + "%");
		}

		String sql = "select ID,Title,author,publishDate,Addtime,catalogID,topflag from ZCArticle where catalogid=?"
				+ condition;
		String sql2 = "select count(*) from ZCArticle where catalogid=?" + condition;

		sql = sql + dga.getSortString();

		qb.setSQL(sql2);
		dga.setTotal(qb);

		qb.setSQL(sql);
		DataTable dt = qb.executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
		int size = dt.getRowCount();
		String[] columnValue = new String[dt.getRowCount()];
		for (int i = 0; i < size; ++i) {
			long articleID = Long.parseLong(dt.getString(i, "ID"));
			long catalog = Long.parseLong(dt.getString(i, "catalogID"));
			columnValue[i] = CatalogUtil.getPath(catalog) + articleID + ".shtml";
		}

		dt.insertColumn("Link", columnValue);
		dga.bindData(dt);
	}

	public static void treeDataBind(TreeAction ta) {
		String siteID = String.valueOf(Application.getCurrentSiteID());
		String parentTreeLevel = (String) ta.getParams().get("ParentLevel");
		String parentID = (String) ta.getParams().get("ParentID");
		DataTable dt = null;
		QueryBuilder qb;
		if (ta.isLazyLoad()) {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,InnerCode from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel>? and innerCode like ? order by orderflag,innercode ");
			qb.add(1);
			qb.add(siteID);
			qb.add(parentTreeLevel);
			qb.add(CatalogUtil.getInnerCode(parentID) + "%");
			dt = qb.executeDataTable();
		} else {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,InnerCode,SingleFlag from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel-1 <=? order by orderflag,innercode ");
			qb.add(1);
			qb.add(siteID);
			qb.add(ta.getLevel());
			dt = qb.executeDataTable();
		}
		ta.setRootText("文档库");
		dt = dt.filter(new Filter() {
			public boolean filter(Object obj) {
				DataRow dr = (DataRow) obj;
				return Priv.getPriv(User.getUserName(), "article", dr.getString("InnerCode"),
						"article_browse");
			}
		});
		ta.bindData(dt);
		List items = ta.getItemList();
		for (int i = 1; i < items.size(); ++i) {
			TreeItem item = (TreeItem) items.get(i);
			if ("Y".equals(item.getData().getString("SingleFlag")))
				item.setIcon("Icons/treeicon11.gif");
		}
	}

	public static Mapx init(Mapx params) {
		String catalogID = (String) params.get("CatalogID");
		if (catalogID == null) {
			return params;
		}
		DataTable dtCatalog = new QueryBuilder("select siteid from zccatalog where id=?", catalogID)
				.executeDataTable();
		long siteID = ((Long) dtCatalog.get(0, "siteid")).longValue();
		params.put("SiteID", siteID);
		params.put("ListType", (String) params.get("Type"));
		return params;
	}

	public void add() {
	}

	public void up() {
		String ids = $V("ArticleIDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("错误的参数!");
			return;
		}
		String now = DateUtil.getCurrentDateTime();
		Transaction trans = new Transaction();
		trans.add(new QueryBuilder("update zcarticle set Status =30,PublishDate = '" + now
				+ "',DownlineDate = '2999-12-31 23:59:59' where id in(" + ids + ")"));
		if (trans.commit()) {
			upTask(ids);
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
		}
	}

	private long upTask(final String ids) {
		LongTimeTask ltt = new LongTimeTask() {
			public void execute() {
				Publisher p = new Publisher();
				ZCArticleSchema site = new ZCArticleSchema();
				ZCArticleSet set = site.query(new QueryBuilder("where id in (" + ids + ")"));
				if ((set != null) && (set.size() > 0)) {
					p.publishArticle(set, false, this);
					p.publishCatalog(set.get(0).getCatalogID(), false, false);
					setPercent(100);
				}
			}
		};
		ltt.setUser(User.getCurrent());
		ltt.start();
		return ltt.getTaskID();
	}

	public void down() {
		String ids = $V("ArticleIDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("错误的参数!");
			return;
		}
		String now = DateUtil.getCurrentDateTime();
		Transaction trans = new Transaction();
		trans.add(new QueryBuilder("update zcarticle set Status = 40,TopFlag='0',DownlineDate = '"
				+ now + "',modifyTime='" + now + "' where id in(" + ids + ")"));
		if (trans.commit()) {
			ZCArticleSchema site = new ZCArticleSchema();
			ZCArticleSet set = site.query(new QueryBuilder("where id in (" + ids + ")"));
			downTask(set);

			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
		}
	}

	private long downTask(final ZCArticleSet set) {
		LongTimeTask ltt = new LongTimeTask() {

			public void execute() {
				Publisher p = new Publisher();
				if ((set != null) && (set.size() > 0)) {
					p.deletePubishedFile(set);
					p.publishCatalog(set.get(0).getCatalogID(), false, false, 5);
					setPercent(100);
				}
			}
		};
		ltt.setUser(User.getCurrent());
		ltt.start();
		return ltt.getTaskID();
	}

	public void del() {
		String ids = $V("IDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("错误的参数!");
			return;
		}
		Transaction trans = new Transaction();
		ZCArticleSchema article = new ZCArticleSchema();
		ZCArticleSet set = article.query(new QueryBuilder("where id in(" + ids
				+ ") or id in(select id from zcarticle where refersourceid in (" + ids + ") )"));

		trans.add(set, 5);
		Mapx catalogMap = new Mapx();

		for (int i = 0; i < set.size(); ++i) {
			article = set.get(i);
			String sqlArticleCount = "update zccatalog set total = total-1,isdirty=1 where innercode in("
					+ CatalogUtil.getParentCatalogCode(article.getCatalogInnerCode()) + ")";
			trans.add(new QueryBuilder(sqlArticleCount));

			ZDColumnValueSchema colValue = new ZDColumnValueSchema();
			colValue.setRelaID(String.valueOf(article.getID()));
			ZDColumnValueSet colValueSet = colValue.query();
			trans.add(colValueSet, 5);

			ZCImageRelaSchema imageRela = new ZCImageRelaSchema();
			imageRela.setRelaID(article.getID());
			ZCImageRelaSet imageRelaSet = imageRela.query();
			trans.add(imageRelaSet, 5);

			ZCCommentSchema comment = new ZCCommentSchema();
			comment.setRelaID(article.getID());
			ZCCommentSet commentSet = comment.query();
			trans.add(commentSet, 5);

			ZCArticleLogSchema articleLog = new ZCArticleLogSchema();
			articleLog.setID(NoUtil.getMaxID("ArticleLogID"));
			articleLog.setArticleID(article.getID());
			articleLog.setAction("DELETE");
			articleLog.setActionDetail("删除。删除原因：" + $V("DeleteReason"));
			articleLog.setAddUser(User.getUserName());
			articleLog.setAddTime(new Date());
			trans.add(articleLog, 1);

			catalogMap.put(article.getCatalogID(), article.getCatalogInnerCode());
		}

		if (trans.commit()) {
			downTask(set);
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("操作数据库时发生错误!");
		}
	}

	public void publish() {
		String ids = $V("IDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("操作数据库时发生错误!");
			return;
		}
		if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ID时发生错误!");
			return;
		}

		ZCArticleSchema article = new ZCArticleSchema();
		ZCArticleSet set = article.query(new QueryBuilder("where id in(" + ids
				+ ") or id in(select id from zcarticle where refersourceid in (" + ids + ") )"));

		this.Response.setStatus(1);
		long id = publishSetTask(set);
		$S("TaskID", id);
	}

	private long publishSetTask(final ZCArticleSet set) {
		LongTimeTask ltt = new LongTimeTask() {

			public void execute() {
				Publisher p = new Publisher();
				p.publishArticle(set, this);
				setPercent(100);
			}
		};
		ltt.setUser(User.getCurrent());
		ltt.start();
		return ltt.getTaskID();
	}

	private long publishTask(final ZCArticleSet set) {
		LongTimeTask ltt = new LongTimeTask() {

			public void execute() {
				Publisher p = new Publisher();
				if ((set != null) && (set.size() > 0)) {
					p.publishCatalog(set.get(0).getCatalogID(), false, true);
					setPercent(100);
				}
			}
		};
		ltt.setUser(User.getCurrent());
		ltt.start();
		return ltt.getTaskID();
	}

	public void move() {
		String articleIDs = $V("ArticleIDs");
		if (!(StringUtil.checkID(articleIDs))) {
			this.Response.setError("操作数据库时发生错误!");
			return;
		}

		String catalogID = $V("CatalogID");
		if (!(StringUtil.checkID(catalogID))) {
			this.Response.setError("传入CatalogID时发生错误!");
			return;
		}

		Transaction trans = new Transaction();
		ZCArticleSchema srcArticle = new ZCArticleSchema();
		ZCArticleSet set = srcArticle.query(new QueryBuilder("where id in (" + articleIDs + ")"));
		long srcCatalogID = 0L;

		String[] srcArticleIDs = (String[]) null;
		if (set.size() > 0) {
			srcArticleIDs = new String[set.size()];
			for (int i = 0; i < set.size(); ++i) {
				srcArticleIDs[i] = String.valueOf(set.get(i).getID());
			}
		}

		for (int i = 0; i < set.size(); ++i) {
			ZCArticleSchema article = set.get(i);
			srcCatalogID = article.getCatalogID();
			String destCatalogID = catalogID;
			trans.add(new QueryBuilder("update zccatalog set total = total+1 where id=?",
					destCatalogID));
			trans.add(new QueryBuilder("update zccatalog set total = total-1 where id=?",
					srcCatalogID));
			article.setCatalogInnerCode(CatalogUtil.getInnerCode(catalogID));
			article.setCatalogID(catalogID);
			article.setOrderFlag(OrderUtil.getDefaultOrder());
			trans.add(article, 2);

			ZCArticleLogSchema articleLog = new ZCArticleLogSchema();
			articleLog.setArticleID(article.getID());
			articleLog.setID(NoUtil.getMaxID("ArticleLogID"));
			articleLog.setAction("MOVE");
			articleLog.setActionDetail("转移。从" + CatalogUtil.getName(srcCatalogID) + "转移到"
					+ CatalogUtil.getName(destCatalogID) + "。");
			articleLog.setAddUser(User.getUserName());
			articleLog.setAddTime(new Date());
			trans.add(articleLog, 1);
		}

		if (trans.commit()) {
			Publisher p = new Publisher();

			p.deletePubishedFile(set);

			if ((p.publishArticle(set)) && (p.publishCatalog(srcCatalogID, false, false)))
				this.Response.setMessage("转移成功");
			else
				this.Response.setError("转移成功,发布失败。" + Errorx.printString());
		} else {
			this.Response.setError("操作数据库时发生错误!");
		}
	}

	public void copy() {
		String articleIDs = $V("ArticleIDs");
		if (!(StringUtil.checkID(articleIDs))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ArticleID时发生错误!");
			return;
		}
		if ((articleIDs.indexOf("\"") >= 0) || (articleIDs.indexOf("'") >= 0)) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入ArticleID时发生错误!");
			return;
		}

		String catalogIDs = $V("CatalogIDs");
		if (!(StringUtil.checkID(catalogIDs))) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入CatalogID时发生错误!");
			return;
		}
		if ((catalogIDs.indexOf("\"") >= 0) || (catalogIDs.indexOf("'") >= 0)) {
			this.Response.setStatus(0);
			this.Response.setMessage("传入CatalogID时发生错误!");
			return;
		}

		Transaction trans = new Transaction();
		ZCArticleSchema site = new ZCArticleSchema();
		ZCArticleSet set = site.query(new QueryBuilder("where id in (" + articleIDs + ")"));
		DataTable valueDT = null;
		String copySiteID = null;
		long srcCatalogID = 0L;

		String referType = $V("ReferType");

		if (StringUtil.isEmpty(referType)) {
			referType = "1";
		}
		int refer = Integer.parseInt(referType);

		String[] catalogArr = catalogIDs.split(",");
		ZCArticleSet destSet = new ZCArticleSet();
		for (int i = 0; i < set.size(); ++i) {
			ZCArticleSchema article = set.get(i);
			srcCatalogID = article.getCatalogID();
			for (int j = 0; j < catalogArr.length; ++j) {
				if (String.valueOf(srcCatalogID).equals(catalogArr[j])) {
					continue;
				}
				ZCArticleSchema articleClone = (ZCArticleSchema) article.clone();
				long articleID = NoUtil.getMaxID("DocID");
				articleClone.setID(articleID);
				articleClone.setCatalogID(catalogArr[j]);
				articleClone.setCatalogInnerCode(CatalogUtil.getInnerCode(catalogArr[j]));
				if (StringUtil.isEmpty(copySiteID)) {
					copySiteID = CatalogUtil.getSiteID(catalogArr[j]);
				}

				if (refer == 2) {
					articleClone.setType("4");
					String path = Config.getContextPath() + Config.getValue("Statical.TargetDir")
							+ "/" + Application.getCurrentSiteAlias() + "/";
					path = path.replaceAll("///", "/");
					path = path.replaceAll("//", "/");
					String referURL = path + CatalogUtil.getPath(srcCatalogID) + articleID
							+ ".shtml";
					articleClone.setRedirectURL(referURL);
					articleClone.setContent("");
				}

				articleClone.setSiteID(copySiteID);
				articleClone.setOrderFlag(System.currentTimeMillis());
				articleClone.setPublishDate(new Date());
				trans.add(articleClone, 1);

				String sqlArticleCount = "update zccatalog set total = total+1 where id=?";
				trans.add(new QueryBuilder(sqlArticleCount, catalogArr[j]));

				ZCArticleLogSchema articleLog = new ZCArticleLogSchema();
				articleLog.setID(NoUtil.getMaxID("ArticleLogID"));
				articleLog.setArticleID(article.getID());
				articleLog.setAction("COPY");
				articleLog.setActionDetail("复制。从"
						+ CatalogUtil.getName(new StringBuffer(String.valueOf(article
								.getCatalogID())).toString())
						+ "复制到"
						+ CatalogUtil.getName(new StringBuffer(String.valueOf(articleClone
								.getCatalogID())).toString()) + "。");
				articleLog.setAddUser(User.getUserName());
				articleLog.setAddTime(new Date());
				trans.add(articleLog, 1);

				destSet.add(articleClone);

				valueDT = new QueryBuilder(
						"select * from zdcolumnvalue where relaid = ("
								+ article.getID()
								+ ") and exists (select * from zdcolumnrela where relaid=? and columncode = zdcolumnvalue.columncode)",
						catalogArr[j]).executeDataTable();
				for (int k = 0; (valueDT != null) && (k < valueDT.getRowCount()); ++k) {
					String columnID = new QueryBuilder(
							"select columnID from zdcolumnrela where columncode = ? and relaID = ?",
							valueDT.getString(k, "ColumnCode"), catalogArr[j]).executeString();
					ZDColumnValueSchema valueClone = new ZDColumnValueSchema();
					valueClone.setID(NoUtil.getMaxID("ColumnValueID"));
					valueClone.setColumnCode(valueDT.getString(k, "ColumnCode"));
					valueClone.setColumnID(columnID);
					valueClone.setRelaType("2");
					valueClone.setTextValue(valueDT.getString(k, "TextValue"));
					valueClone.setRelaID(String.valueOf(articleID));
					trans.add(valueClone, 1);
				}
			}
		}

		if (trans.commit()) {
			publishTask(destSet);
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("操作数据库时发生错误!");
		}
	}

	public void sortArticle() {
		String target = $V("Target");
		String orders = $V("Orders");
		String type = $V("Type");
		String catalogID = $V("CatalogID");
		boolean topFlag = "true".equals($V("TopFlag"));
		if ((!(StringUtil.checkID(target))) && (!(StringUtil.checkID(orders)))) {
			return;
		}
		Transaction tran = new Transaction();
		QueryBuilder qb;
		if (topFlag) {
			qb = new QueryBuilder("update ZCArticle set TopFlag='1' where OrderFlag in (" + orders
					+ ")");
			tran.add(qb);
		} else {
			qb = new QueryBuilder("update ZCArticle set TopFlag='0' where OrderFlag in (" + orders
					+ ")");
			tran.add(qb);
		}
		OrderUtil.updateOrder("ZCArticle", "OrderFlag", type, target, orders, null, tran);
		if (tran.commit()) {
			final String id = catalogID;
			LongTimeTask ltt = new LongTimeTask() {

				public void execute() {
					Publisher p = new Publisher();
					p.publishCatalog(Long.parseLong(id), false, false, 3);
					setPercent(100);
				}
			};
			ltt.setUser(User.getCurrent());
			ltt.start();

			this.Response.setMessage("操作成功");
		} else {
			this.Response.setError("操作失败");
		}
	}

	public void setTop() {
		String ids = $V("IDs");
		String topDate = $V("TopDate");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("错误的参数!");
			return;
		}
		QueryBuilder qb = new QueryBuilder("update ZCArticle set TopFlag='1' where id in (" + ids
				+ ")");
		if (StringUtil.isNotEmpty(topDate)) {
			qb = new QueryBuilder("update ZCArticle set TopFlag='1',TopDate='" + topDate + " "
					+ $V("TopTime") + "' where id in (" + ids + ")");
		}
		qb.executeNoQuery();
		this.Response.setStatus(1);

		ZCArticleSchema article = new ZCArticleSchema();
		final ZCArticleSet set = article.query(new QueryBuilder(" where id in (" + ids + ")"));
		LongTimeTask ltt = new LongTimeTask() {

			public void execute() {
				Publisher p = new Publisher();
				p.publishCatalog(set.get(0).getCatalogID(), false, false);
				setPercent(100);
			}
		};
		ltt.setUser(User.getCurrent());
		ltt.start();
	}

	public void setNotTop() {
		String ids = $V("IDs");
		if (!(StringUtil.checkID(ids))) {
			this.Response.setStatus(0);
			this.Response.setMessage("错误的参数!");
			return;
		}
		QueryBuilder qb = new QueryBuilder("update ZCArticle set TopFlag='0' where id in (" + ids
				+ ")");
		qb.executeNoQuery();
		this.Response.setStatus(1);

		ZCArticleSchema article = new ZCArticleSchema();
		final ZCArticleSet set = article.query(new QueryBuilder(" where id in (" + ids + ")"));
		LongTimeTask ltt = new LongTimeTask() {

			public void execute() {
				Publisher p = new Publisher();
				p.publishCatalog(set.get(0).getCatalogID(), false, false);
				setPercent(100);
			}
		};
		ltt.setUser(User.getCurrent());
		ltt.start();
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.document.ArticleList JD-Core Version: 0.5.3
 */