package com.zving.cms.site;

import com.zving.cms.datachannel.Publisher;
import com.zving.cms.pub.CatalogUtil;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.TreeAction;
import com.zving.framework.controls.TreeItem;
import com.zving.framework.data.DataCollection;
import com.zving.framework.data.DataColumn;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.messages.LongTimeTask;
import com.zving.framework.schedule.CronExpressionException;
import com.zving.framework.schedule.CronMonitor;
import com.zving.framework.utility.ChineseSpelling;
import com.zving.framework.utility.DateUtil;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.Filter;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.Priv;
import com.zving.platform.UserLog;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCArticleSchema;
import com.zving.schema.ZCAttachmentSchema;
import com.zving.schema.ZCAudioSchema;
import com.zving.schema.ZCCatalogConfigSchema;
import com.zving.schema.ZCCatalogSchema;
import com.zving.schema.ZCCatalogSet;
import com.zving.schema.ZCImageSchema;
import com.zving.schema.ZCPageBlockItemSchema;
import com.zving.schema.ZCPageBlockItemSet;
import com.zving.schema.ZCPageBlockSchema;
import com.zving.schema.ZCPageBlockSet;
import com.zving.schema.ZCSiteSchema;
import com.zving.schema.ZCVideoSchema;
import com.zving.schema.ZDColumnRelaSchema;
import com.zving.schema.ZDColumnRelaSet;
import com.zving.schema.ZDColumnValueSchema;
import com.zving.schema.ZDColumnValueSet;
import com.zving.schema.ZDScheduleSchema;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Catalog extends Page {
	public static final int CATALOG = 1;
	public static final int SUBJECT = 2;
	public static final int MAGAZINE = 3;
	public static final int IMAGELIB = 4;
	public static final int VIDEOLIB = 5;
	public static final int AUDIOLIB = 6;
	public static final int ATTACHMENTLIB = 7;
	public static final int NEWSPAPER = 8;
	public static final int SHOP_GOODS = 9;
	public static final int SHOP_GOODS_BRAND = 10;
	public static final int SHOP_GOODS_TYPE = 11;
	public static final Mapx CatalogTypeMap = new Mapx();

	static {
		CatalogTypeMap.put("1", "栏目");
		CatalogTypeMap.put("2", "专题");
		CatalogTypeMap.put("3", "杂志");
		CatalogTypeMap.put("4", "图片");
		CatalogTypeMap.put("5", "视频");
		CatalogTypeMap.put("6", "音频");
		CatalogTypeMap.put("7", "附件");
		CatalogTypeMap.put("9", "商品");
		CatalogTypeMap.put("10", "品牌");
		CatalogTypeMap.put("11", "商品其他分类");
	}

	public static Mapx init(Mapx params) {
		Object o1 = params.get("CatalogID");
		if (o1 == null) {
			o1 = params.get("Cookie.DocList.LastCatalog");
		}
		if (o1 != null) {
			long ID = Long.parseLong(o1.toString());
			ZCCatalogSchema catalog = new ZCCatalogSchema();
			catalog.setID(ID);
			if (catalog.fill()) {
				Mapx map = catalog.toMapx();
				String imagePath = catalog.getImagePath();
				if (StringUtil.isNotEmpty(imagePath)) {
					map.put("PicSrc", imagePath);
				}
				String publishFlag = catalog.getPublishFlag();
				if (StringUtil.isEmpty(publishFlag)) {
					publishFlag = "Y";
				}
				map
						.put("PublishFlag", HtmlUtil.codeToRadios("PublishFlag", "YesOrNo",
								publishFlag));

				String allowContribute = catalog.getAllowContribute();
				if (StringUtil.isEmpty(allowContribute)) {
					allowContribute = "N";
				}
				map.put("AllowContribute", HtmlUtil.codeToRadios("AllowContribute", "YesOrNo",
						allowContribute));

				String singleFlag = catalog.getSingleFlag();
				if (StringUtil.isEmpty(singleFlag)) {
					singleFlag = "N";
				}
				map.put("SingleFlag", HtmlUtil.codeToRadios("SingleFlag", "YesOrNo", singleFlag));

				map.put("CatalogType", HtmlUtil.codeToOptions("CodeType", catalog.getType()));
				DataTable workflowDT = new QueryBuilder("select name,ID from zwworkflowdef")
						.executeDataTable();
				map.put("Workflow", HtmlUtil.dataTableToOptions(workflowDT, catalog.getWorkflow(),
						true));

				String keywordFlag = catalog.getKeywordFlag();
				if (StringUtil.isEmpty(keywordFlag))
					keywordFlag = "N";
				String s1;
				if (StringUtil.isNotEmpty(catalog.getIndexTemplate())) {
					s1 = "<a href='javascript:void(0);' onclick=\"openEditor('"
							+ catalog.getIndexTemplate()
							+ "')\"><img src='../Platform/Images/icon_edit.gif'"
							+ " width='14' height='14'></a>";
					map.put("edit", s1);
				}
				if (StringUtil.isNotEmpty(catalog.getListTemplate())) {
					s1 = "<a href='javascript:void(0);' onclick=\"openEditor('"
							+ catalog.getListTemplate()
							+ "')\"><img src='../Platform/Images/icon_edit.gif'"
							+ " width='14' height='14'></a>";
					map.put("edit1", s1);
				}
				if (StringUtil.isNotEmpty(catalog.getDetailTemplate())) {
					s1 = "<a href='javascript:void(0);' onclick=\"openEditor('"
							+ catalog.getDetailTemplate()
							+ "')\"><img src='../Platform/Images/icon_edit.gif'"
							+ " width='14' height='14'></a>";
					map.put("edit2", s1);
				}

				if (StringUtil.isEmpty(catalog.getDetailNameRule())) {
					map.put("DetailNameRule", "/${catalogpath}/${document.id}.shtml");
				}

				if ("Y".equals(catalog.getSingleFlag())) {
					map.put("IsDisplay", "none");
				}

				return map;
			}
		}

		return null;
	}

	public static Mapx initDialog(Mapx params) {
		params.put("SiteID", Application.getCurrentSiteID());
		params.put("PublishFlag", HtmlUtil.codeToRadios("PublishFlag", "YesOrNo", "Y"));
		params.put("SingleFlag", HtmlUtil.codeToRadios("SingleFlag", "YesOrNo", "N"));
		params.put("AllowContribute", HtmlUtil.codeToRadios("AllowContribute", "YesOrNo", "N"));
		params.put("CatalogType", HtmlUtil.codeToOptions("CatalogType"));
		params.put("DetailNameRule", "/${catalogpath}/${document.id}.shtml");
		params.put("ListTemplate", "/template/list.html");
		params.put("DetailTemplate", "/template/detail.html");
		DataTable workflowDT = new QueryBuilder("select name,ID from zwworkflowdef")
				.executeDataTable();
		params.put("Workflow", HtmlUtil.dataTableToOptions(workflowDT, true));
		return params;
	}

	public void getPicSrc() {
		String ID = $V("PicID");
		QueryBuilder qb = new QueryBuilder("select path,filename from zcimage where id=?", ID);
		DataTable dt = qb.executeDataTable();
		if (dt.getRowCount() > 0) {
			this.Response.put("picSrc", dt.get(0, "path").toString() + "1_"
					+ dt.get(0, "filename").toString());
			this.Response.put("ImagePath", dt.get(0, "path").toString() + "1_"
					+ dt.get(0, "filename").toString());
		}
	}

	public static void treeDataBind(TreeAction ta) {
		Object obj = ta.getParams().get("SiteID");
		String siteID = String.valueOf(Application.getCurrentSiteID());
		Object typeObj = ta.getParams().get("CatalogType");
		int catalogType = (typeObj != null) ? Integer.parseInt(typeObj.toString()) : 1;
		String parentTreeLevel = ta.getParams().getString("ParentLevel");
		String parentID = ta.getParams().getString("ParentID");
		DataTable dt = null;
		QueryBuilder qb;
		if (ta.isLazyLoad()) {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,SingleFlag,InnerCode,OrderFlag from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel>? and innerCode like ? order by orderflag,innercode");

			qb.add(catalogType);
			qb.add(siteID);
			qb.add(parentTreeLevel);
			qb.add(CatalogUtil.getInnerCode(parentID) + "%");
			dt = qb.executeDataTable();
		} else {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,SingleFlag,InnerCode,OrderFlag from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel-1 <=? order by orderflag,innercode");

			qb.add(catalogType);
			qb.add(siteID);
			qb.add(ta.getLevel());
			dt = qb.executeDataTable();
		}

		String siteName = "文档库";
		dt = dt.filter(new Filter() {
			public boolean filter(Object obj) {
				DataRow dr = (DataRow) obj;
				return Priv.getPriv(User.getUserName(), "article", dr.getString("InnerCode"),
						"article_browse");
			}
		});
		String inputType = (String) ta.getParams().get("Type");
		if ("3".equals(inputType))
			ta.setRootText("<input type='radio' name=CatalogID id='_site' value='" + siteID
					+ "'><label for='_site'>" + siteName + "</label>");
		else if ("2".equals(inputType))
			ta.setRootText("<input type='CheckBox' name=CatalogID id='_site' value='" + siteID
					+ "' onclick='selectAll()'><label for='_site'>" + siteName + "</label>");
		else {
			ta.setRootText(siteName);
		}

		ta.bindData(dt);
		List items = ta.getItemList();
		for (int i = 1; i < items.size(); ++i) {
			TreeItem item = (TreeItem) items.get(i);
			if ("Y".equals(item.getData().getString("SingleFlag")))
				item.setIcon("Icons/treeicon11.gif");
		}
	}

	public static void treeDiagDataBind(TreeAction ta) {
		Object obj = ta.getParams().get("SiteID");
		String siteID = String.valueOf(Application.getCurrentSiteID());
		Object typeObj = ta.getParams().get("CatalogType");
		int catalogType = (typeObj != null) ? Integer.parseInt(typeObj.toString()) : 1;
		String parentTreeLevel = ta.getParams().getString("ParentLevel");
		String parentID = ta.getParams().getString("ParentID");
		DataTable dt = null;
		QueryBuilder qb;
		if (ta.isLazyLoad()) {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,SingleFlag,InnerCode,OrderFlag from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel>? and innerCode like ? order by orderflag,innercode");

			qb.add(catalogType);
			qb.add(siteID);
			qb.add(parentTreeLevel);
			qb.add(CatalogUtil.getInnerCode(parentID) + "%");
			dt = qb.executeDataTable();
		} else {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,SingleFlag,InnerCode,OrderFlag from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel-1 <=? order by orderflag,innercode");

			qb.add(catalogType);
			qb.add(siteID);
			qb.add(ta.getLevel());
			dt = qb.executeDataTable();
		}

		String siteName = "文档库";

		String inputType = (String) ta.getParams().get("Type");
		if ("3".equals(inputType))
			ta.setRootText("<input type='radio' name=CatalogID id='_site' value='" + siteID
					+ "'><label for='_site'>" + siteName + "</label>");
		else if ("2".equals(inputType))
			ta.setRootText("<input type='CheckBox' name=CatalogID id='_site' value='" + siteID
					+ "' onclick='selectAll()'><label for='_site'>" + siteName + "</label>");
		else {
			ta.setRootText(siteName);
		}
		dt = dt.filter(new Filter() {
			public boolean filter(Object obj) {
				DataRow dr = (DataRow) obj;
				return Priv.getPriv(User.getUserName(), "article", dr.getString("InnerCode"),
						"article_manage");
			}
		});
		ta.bindData(dt);
	}

	public static void treeResourceDataBind(TreeAction ta) {
		String siteID = ta.getParam("SiteID");
		siteID = String.valueOf(Application.getCurrentSiteID());
		String catalogTypeStr = ta.getParam("CatalogType");
		if (catalogTypeStr == null) {
			catalogTypeStr = ta.getParams().getString("Cookie.ResourceCatalog.CatalogType");
		}
		int catalogType = (catalogTypeStr != null) ? Integer.parseInt(catalogTypeStr.toString())
				: 4;
		String parentTreeLevel = (String) ta.getParams().get("ParentLevel");
		String parentID = (String) ta.getParams().get("ParentID");
		DataTable dt = null;
		QueryBuilder qb;
		if (ta.isLazyLoad()) {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,SingleFlag,InnerCode,OrderFlag from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel>? and innerCode like ? order by orderflag,innercode");

			qb.add(catalogType);
			qb.add(siteID);
			qb.add(parentTreeLevel);
			qb.add(CatalogUtil.getInnerCode(parentID) + "%");
			dt = qb.executeDataTable();
		} else {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,SingleFlag,InnerCode,OrderFlag from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel-1 <=? order by orderflag,innercode");

			qb.add(catalogType);
			qb.add(siteID);
			qb.add(ta.getLevel());
			dt = qb.executeDataTable();
		}

		String siteName = "";
		if (4 == catalogType) {
			siteName = "图片库";
			dt = dt.filter(new Filter() {
				public boolean filter(Object obj) {
					DataRow dr = (DataRow) obj;
					return Priv.getPriv(User.getUserName(), "image", dr.getString("InnerCode"),
							"image_browse");
				}
			});
		} else if (5 == catalogType) {
			siteName = "视频库";
			dt = dt.filter(new Filter() {
				public boolean filter(Object obj) {
					DataRow dr = (DataRow) obj;
					return Priv.getPriv(User.getUserName(), "video", dr.getString("InnerCode"),
							"video_browse");
				}
			});
		} else if (6 == catalogType) {
			siteName = "音频库";
			dt = dt.filter(new Filter() {
				public boolean filter(Object obj) {
					DataRow dr = (DataRow) obj;
					return Priv.getPriv(User.getUserName(), "audio", dr.getString("InnerCode"),
							"audio_browse");
				}
			});
		} else if (7 == catalogType) {
			siteName = "附件库";
			dt = dt.filter(new Filter() {
				public boolean filter(Object obj) {
					DataRow dr = (DataRow) obj;
					return Priv.getPriv(User.getUserName(), "attach", dr.getString("InnerCode"),
							"attach_browse");
				}
			});
		}
		String inputType = (String) ta.getParams().get("Type");
		if ("3".equals(inputType))
			ta.setRootText("<input type='radio' name=CatalogID id='_site' value='" + siteID
					+ "'><label for='_site'>" + siteName + "</label>");
		else if ("2".equals(inputType))
			ta.setRootText("<input type='CheckBox' name=CatalogID id='_site' value='" + siteID
					+ "' onclick='selectAll()'><label for='_site'>" + siteName + "</label>");
		else {
			ta.setRootText(siteName);
		}

		ta.bindData(dt);
	}

	public void dg1Edit() {
		DataTable dt = (DataTable) this.Request.get("DT");
		int count = dt.getRowCount();
		StringBuffer sb = new StringBuffer();
		QueryBuilder qb = new QueryBuilder();
		sb.append("update ZCCatalog set ");
		for (int i = 0; i < count; ++i) {
			sb.append(dt.get(i, "InnerCode"));
			sb.append("=?");

			qb.add(dt.get(i, "Value"));
		}
		sb.append(" modifyuser = '");
		sb.append(User.getUserName());
		sb.append("',modifytime = '");
		sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		sb.append("' where ID = ?");
		qb.add(dt.get(0, "ID_key"));

		qb.setSQL(sb.toString());
		if (qb.executeNoQuery() != -1) {
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("发生错误!");
		}
	}

	public void add() {
		Transaction trans = new Transaction();
		ZCCatalogSchema catalog = add(this.Request, trans);
		if ((catalog != null) && (trans.commit())) {
			CatalogUtil.update(catalog.getID());
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("添加栏目发生错误！请检查名称或别名是否重复。");
		}
	}

	public ZCCatalogSchema add(DataCollection dc, Transaction trans) {
		String alias = dc.getString("Alias");
		String name = dc.getString("Name");
		int treeLevel = 1;

		ZCCatalogSchema pCatalog = new ZCCatalogSchema();
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		String type = dc.getString("Type");
		if ((StringUtil.isEmpty(type)) || ("null".equals(type))) {
			type = "1";
		}

		catalog.setValue(dc);
		String parentID = dc.getString("ParentID");

		if ((parentID != null) && (!("0".equals(parentID))) && (!("null".equals(parentID)))) {
			pCatalog.setID(Integer.parseInt(parentID));
			pCatalog.fill();

			treeLevel = (int) pCatalog.getTreeLevel() + 1;
			if (checkAliasExists(alias, pCatalog.getID())) {
				return null;
			}

			if (checkNameExists(name, pCatalog.getID())) {
				return null;
			}

			catalog.setParentID(pCatalog.getID());
			catalog.setSiteID(pCatalog.getSiteID());
			catalog.setTreeLevel(treeLevel);
			pCatalog.setChildCount(pCatalog.getChildCount() + 1L);
			pCatalog.setIsLeaf(0L);
			trans.add(pCatalog, 2);
		} else {
			ZCSiteSchema site = new ZCSiteSchema();
			site.setID(Application.getCurrentSiteID());
			site.fill();

			catalog.setParentID(0L);
			catalog.setSiteID(site.getID());

			if (checkAliasExists(alias, 0L)) {
				return null;
			}

			if (checkNameExists(name, pCatalog.getID())) {
				return null;
			}

			catalog.setTreeLevel(treeLevel);
			parentID = "0";

			if ("1".equals(type))
				site.setChannelCount(site.getChannelCount() + 1L);
			else if ("2".equals(type))
				site.setSpecialCount(site.getSpecialCount() + 1L);
			else if ("3".equals(type)) {
				site.setMagzineCount(site.getMagzineCount() + 1L);
			}
			trans.add(site, 2);
		}

		String url = dc.getString("URL");
		if ((url == null) || ("".equals(url))) {
			if ((parentID != null) && (!("0".equals(parentID))) && (!("null".equals(parentID)))) {
				url = url + CatalogUtil.getPath(catalog.getParentID());
			}
			url = url + dc.getString("Alias") + "/";
		}
		long catalogID = NoUtil.getMaxID("CatalogID");
		catalog.setID(catalogID);

		String innerCode = CatalogUtil.createCatalogInnerCode(pCatalog.getInnerCode());
		catalog.setInnerCode(innerCode);

		catalog.setURL(url);
		catalog.setType(type);
		catalog.setChildCount(0L);
		catalog.setIsLeaf(1L);
		catalog.setTotal(0L);
		catalog.setHitCount(0L);

		String orderFlag = getCatalogOrderFlag(parentID, type);
		catalog.setOrderFlag(Long.parseLong(orderFlag) + 1L);
		catalog.setListPageSize(20L);

		String publishFlag = dc.getString("PublishFlag");
		if ((publishFlag != null) && (!("".equals(publishFlag))))
			catalog.setPublishFlag(publishFlag);
		else {
			catalog.setPublishFlag("Y");
		}

		if ("Y".equals(dc.get("SingleFlag")))
			catalog.setSingleFlag("Y");
		else {
			catalog.setSingleFlag("N");
		}

		if ("Y".equals(dc.get("AllowContribute")))
			catalog.setAllowContribute("Y");
		else {
			catalog.setAllowContribute("N");
		}

		catalog.setAddUser(User.getUserName());
		catalog.setAddTime(new Date());

		trans.add(catalog, 1);
		trans.add(new QueryBuilder("update zccatalog set orderflag=orderflag+1 where orderflag>"
				+ orderFlag + " and type=" + type));

		initCatalogConfig(catalog, trans);
		return catalog;
	}

	public long add(ZCCatalogSchema parent, ZCCatalogSchema catalog, Transaction trans) {
		String type = String.valueOf(catalog.getType());
		if ((StringUtil.isEmpty(type)) || ("null".equals(type))) {
			type = "1";
		}

		if (parent != null) {
			catalog.setParentID(parent.getID());
			catalog.setSiteID(parent.getSiteID());
			catalog.setTreeLevel(parent.getTreeLevel() + 1L);
			parent.setChildCount(parent.getChildCount() + 1L);
			parent.setIsLeaf(0L);
			trans.add((ZCCatalogSchema) parent.clone(), 2);
		} else {
			ZCSiteSchema site = new ZCSiteSchema();
			site.setID(Application.getCurrentSiteID());
			site.fill();

			catalog.setParentID(0L);
			catalog.setSiteID(site.getID());
			catalog.setTreeLevel(1L);

			if ("1".equals(type))
				site.setChannelCount(site.getChannelCount() + 1L);
			else if ("2".equals(type))
				site.setSpecialCount(site.getSpecialCount() + 1L);
			else if ("3".equals(type)) {
				site.setMagzineCount(site.getMagzineCount() + 1L);
			}
			trans.add(site, 2);
		}

		String orderFlag = getCatalogOrderFlag(parent.getID(), type);
		catalog.setOrderFlag(Long.parseLong(orderFlag) + 1L);

		long catalogID = NoUtil.getMaxID("CatalogID");
		catalog.setID(catalogID);

		String innerCode = CatalogUtil.createCatalogInnerCode(parent.getInnerCode());
		catalog.setInnerCode(innerCode);

		catalog.setAddUser(User.getUserName());
		catalog.setAddTime(new Date());

		trans.add(catalog, 1);
		initCatalogConfig(catalog, trans);

		return catalogID;
	}

	public static void initCatalogConfig(ZCCatalogSchema catalog, Transaction trans) {
		ZCCatalogConfigSchema config = new ZCCatalogConfigSchema();
		config.setID(NoUtil.getMaxID("PublishConfigID"));
		config.setAddTime(new Date());
		config.setAddUser(User.getUserName());
		config.setStartTime(new Date());
		config.setArchiveTime("12");
		config.setAttachDownFlag("Y");
		config.setAfterEditStatus("30");
		config.setAllowStatus("0,30");
		config.setHotWordFlag("N");
		config.setSiteID(catalog.getSiteID());
		config.setCatalogID(catalog.getID());
		config.setCatalogInnerCode(catalog.getInnerCode());
		config.setIsUsing("Y");
		config.setPlanType("Period");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		StringBuffer sb = new StringBuffer();
		int minute = c.get(12);
		int hour = c.get(11);
		int day = c.get(5);
		sb.append(minute);
		sb.append(" ");
		sb.append(hour);
		sb.append(" ");
		sb.append(day);
		sb.append("-");
		sb.append(day - 1);
		sb.append("/1");
		sb.append(" * *");
		config.setCronExpression(sb.toString());
		trans.add(config, 1);

		ZDScheduleSchema schedule = new ZDScheduleSchema();
		schedule.setID(NoUtil.getMaxID("ScheduleID"));
		schedule.setSourceID(config.getID());
		schedule.setTypeCode("Publisher");
		schedule.setProp1("Config");
		schedule.setAddTime(new Date());
		schedule.setAddUser(User.getUserName());
		schedule.setCronExpression(config.getCronExpression());
		schedule.setPlanType(config.getPlanType());
		schedule.setStartTime(config.getStartTime());
		schedule.setIsUsing(config.getIsUsing());
		trans.add(schedule, 1);
		try {
			CronMonitor.getNextRunTime(schedule.getCronExpression());
		} catch (CronExpressionException e) {
			e.printStackTrace();
		}
	}

	public void save() {
		if (!(Priv.getPriv("article", $V("InnerCode"), "article_manage"))) {
			this.Response.setLogInfo(0, "操作失败，你没有权限进行此操作！");
			return;
		}

		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(Long.parseLong($V("ID")));
		catalog.fill();

		String name = $V("Name");
		if ((!(name.equals(catalog.getName()))) && (checkNameExists(name, catalog.getParentID()))) {
			this.Response.setLogInfo(0, "栏目名称" + name + "已经存在。");
			return;
		}

		String alias = $V("Alias");
		if ((!(alias.equals(catalog.getAlias())))
				&& (checkAliasExists(alias, catalog.getParentID()))) {
			this.Response.setLogInfo(0, "栏目别名" + alias + "已经存在。");
			return;
		}

		String oldWorkflow = catalog.getWorkflow();
		catalog.setValue(this.Request);
		catalog.setModifyUser(User.getUserName());
		catalog.setModifyTime(new Date());

		Transaction trans = new Transaction();
		trans.add(catalog, 2);

		String extend = $V("Extend");
		if (!("1".equals(extend))) {
			if ("2".equals(extend)) {
				String innerCode = catalog.getInnerCode();
				QueryBuilder qb = new QueryBuilder(
						"update zccatalog set IndexTemplate=?,ListTemplate=?,DetailTemplate=?,rssTemplate=? where innercode like ? and TreeLevel>?");

				qb.add($V("IndexTemplate"));
				qb.add($V("ListTemplate"));
				qb.add($V("DetailTemplate"));
				qb.add($V("RssTemplate"));
				qb.add(innerCode + "%");
				qb.add(catalog.getTreeLevel());

				trans.add(qb);
			} else if ("3".equals(extend)) {
				QueryBuilder qb = new QueryBuilder(
						"update zccatalog set IndexTemplate=?,ListTemplate=?,DetailTemplate=? ,rssTemplate=? where siteID=? and Type=?");

				qb.add($V("IndexTemplate"));
				qb.add($V("ListTemplate"));
				qb.add($V("DetailTemplate"));
				qb.add($V("RssTemplate"));
				qb.add(catalog.getSiteID());
				qb.add(catalog.getType());

				trans.add(qb);
			}
		}

		String wfExtend = $V("WorkFlowExtend");
		DataTable tempDT;
		if ("1".equals(wfExtend)) {
			tempDT = new QueryBuilder(
					"select distinct catalogid from zcarticle a where a.status=10 and a.catalogid= ?",
					catalog.getID()).executeDataTable();
			if ((tempDT.getRowCount() > 0)
					&& (!(oldWorkflow.equalsIgnoreCase(catalog.getWorkflow())))) {
				this.Response.setLogInfo(0, "本栏目下还存在‘流转中’的文章,不能更改工作流!");
				return;
			}
		} else {
			String tempStr;
			int i;
			if ("2".equals(wfExtend)) {
				tempDT = new QueryBuilder(
						"select distinct catalogid from zcarticle a where a.status=10 and a.cataloginnercode like ?",
						catalog.getInnerCode() + "%").executeDataTable();
				if (tempDT.getRowCount() > 0) {
					tempStr = "";
					for (i = 0; i < tempDT.getRowCount(); ++i) {
						if (!(CatalogUtil.getWorkflow(tempDT.getString(i, 0))
								.equalsIgnoreCase(catalog.getWorkflow()))) {
							tempStr = tempStr + CatalogUtil.getName(tempDT.getString(i, 0)) + " ";
						}
					}
					if (StringUtil.isNotEmpty(tempStr)) {
						this.Response.setLogInfo(0, tempStr + "栏目下还存在‘流转中’的文章,不能更改工作流!");
						return;
					}
				}
				trans.add(new QueryBuilder(
						"update zccatalog set workflow =? where innercode like ?", catalog
								.getWorkflow(), catalog.getInnerCode() + "%"));
			} else if ("3".equals(wfExtend)) {
				tempDT = new QueryBuilder(
						"select distinct catalogid from zcarticle a where a.status=? and a.siteID= ? ",
						10L, Application.getCurrentSiteID()).executeDataTable();
				if (tempDT.getRowCount() > 0) {
					tempStr = "";
					for (i = 0; i < tempDT.getRowCount(); ++i) {
						if (!(CatalogUtil.getWorkflow(tempDT.getString(i, 0))
								.equalsIgnoreCase(catalog.getWorkflow()))) {
							tempStr = tempStr + CatalogUtil.getName(tempDT.getString(i, 0)) + " ";
						}
					}
					if (StringUtil.isNotEmpty(tempStr)) {
						this.Response.setLogInfo(0, tempStr + "栏目还存在‘流转中’的文章,不能更改工作流!");
						return;
					}
				}
				trans.add(new QueryBuilder("update zccatalog set workflow =? where siteID ="
						+ Application.getCurrentSiteID() + " and Type=? ", catalog.getWorkflow(),
						catalog.getType()));
			}
		}
		if (trans.commit()) {
			CatalogUtil.update(catalog.getID());
			this.Response.setLogInfo(1, "保存成功!");
		} else {
			this.Response.setLogInfo(0, "保存失败!");
		}
	}

	public void saveTemplate() {
		Transaction trans = new Transaction();
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(Long.parseLong($V("ID")));
		catalog.fill();
		catalog.setValue(this.Request);
		catalog.setModifyUser(User.getUserName());
		catalog.setModifyTime(new Date());

		trans.add(catalog, 2);

		if (trans.commit())
			this.Response.setLogInfo(1, "保存模板成功");
		else
			this.Response.setLogInfo(0, "保存模板失败");
	}

	public static void importTreeDataBind(TreeAction ta) {
		Object obj = ta.getParams().get("ParentID");
		long parentID = (obj != null) ? Long.parseLong(obj.toString()) : 0L;

		String type = (ta.getParams().get("Type") == null) ? null : ta.getParams().get("Type")
				.toString();
		String rootText = null;

		if (parentID == 0L) {
			rootText = "文档库";
			if ((type != null) && (!("".equals(type))) && (!("null".equals(type))))
				rootText = CatalogTypeMap.getString(type) + "库";
		} else {
			rootText = new QueryBuilder("select Name from ZCCatalog where ID=?", String
					.valueOf(parentID)).executeString();
			rootText = (rootText == null) ? "" : rootText;
		}
		ta.setRootText(rootText);
		String fileName = ta.getParams().get("FilePath").toString();
		if (fileName.indexOf("Framework") != -1) {
			fileName = fileName.replaceAll("Upload", "");
		}
		int TreeLevel = 1;
		if (parentID != 0L) {
			ZCCatalogSchema catalog = new ZCCatalogSchema();
			catalog.setID(parentID);
			catalog.fill();
			TreeLevel = (int) catalog.getTreeLevel();
		}
		DataTable dt = getTreeDataTable(fileName, parentID, TreeLevel);
		ta.bindData(dt);
	}

	private static DataTable getTreeDataTable(String fileName, long parentID, int TreeLevel) {
		byte[] bs = FileUtil.readByte(fileName);
		String fileText = null;
		String[] catalogs;
		try {
			if (StringUtil.isUTF8(bs)) {
				fileText = new String(bs, "UTF-8");
			}
			fileText = FileUtil.readText(fileName, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catalogs = fileText.split("\n");

		DataTable dt = new DataTable();
		DataColumn ID = new DataColumn("ID", 3);
		DataColumn ParentID = new DataColumn("ParentID", 3);
		DataColumn Level = new DataColumn("TreeLevel", 8);
		DataColumn Name = new DataColumn("Name", 1);
		dt.insertColumn(ID);
		dt.insertColumn(ParentID);
		dt.insertColumn(Level);
		dt.insertColumn(Name);

		long lastID = 1L;
		int topTreeLevel = TreeLevel;
		Mapx mapx = new Mapx();
		mapx.put(TreeLevel, String.valueOf(parentID));
		for (int i = 0; i < catalogs.length; ++i) {
			String catalog = catalogs[i];

			if (StringUtil.isEmpty(catalog.trim())) {
				continue;
			}
			catalog = StringUtil.toDBC(catalog);
			catalog = StringUtil.rightTrim(catalog);
			if (catalog.lastIndexOf(32) != -1) {
				int length = 0;
				for (int k = 0; k < catalog.length(); ++k) {
					length = k + 1;
					if (catalog.charAt(k) != ' ') {
						break;
					}
				}
				int currentTreeLevel = length / 2 + 1;
				if (currentTreeLevel > TreeLevel) {
					parentID = lastID;
					TreeLevel = currentTreeLevel;
					mapx.put(TreeLevel, String.valueOf(parentID));
				} else if (currentTreeLevel < TreeLevel) {
					TreeLevel = currentTreeLevel;
					parentID = Long.parseLong(mapx.get(TreeLevel).toString());
				}
			} else {
				TreeLevel = topTreeLevel;
				parentID = Long.parseLong(mapx.get(TreeLevel).toString());
			}
			lastID = i + 1;
			Object[] catalogRow = { new Long(lastID), new Long(parentID), new Integer(TreeLevel),
					catalog.trim() };
			dt.insertRow(catalogRow);
		}

		return dt;
	}

	public void importCatalog() {
		long parentID = Long.parseLong($V("ParentID"));

		String fileName = $V("FilePath");
		byte[] bs = FileUtil.readByte(fileName);
		String fileText = null;
		String[] catalogs;
		try {
			if (StringUtil.isUTF8(bs)) {
				fileText = new String(bs, "UTF-8");
			}
			fileText = FileUtil.readText(fileName, "GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catalogs = fileText.split("\n");
		String type = $V("Type");
		if ((StringUtil.isEmpty(type)) || ("null".equals(type))) {
			type = "1";
		}

		int TreeLevel = 1;
		Mapx catalogMap = new Mapx();
		if (parentID != 0L) {
			ZCCatalogSchema catalog = new ZCCatalogSchema();
			catalog.setID(parentID);
			catalog.fill();
			TreeLevel = (int) catalog.getTreeLevel();
			catalogMap.put(catalog.getID(), catalog);
		}
		long lastID = 0L;
		int topTreeLevel = TreeLevel;
		Mapx mapx = new Mapx();
		mapx.put(TreeLevel, String.valueOf(parentID));

		Transaction trans = new Transaction();
		String orderFlag = getCatalogOrderFlag(parentID, type);
		if (StringUtil.isEmpty(orderFlag)) {
			orderFlag = "0";
		}
		for (int i = 0; i < catalogs.length; ++i) {
			String catalogName = catalogs[i];

			if (StringUtil.isEmpty(catalogName.trim())) {
				continue;
			}

			catalogName = StringUtil.toDBC(catalogName);
			catalogName = StringUtil.rightTrim(catalogName);
			if (catalogName.lastIndexOf(32) != -1) {
				int length = 0;
				for (int k = 0; k < catalogName.length(); ++k) {
					length = k + 1;
					if (catalogName.charAt(k) != ' ') {
						break;
					}
				}
				int currentTreeLevel = length / 2 + topTreeLevel;
				if (currentTreeLevel > TreeLevel) {
					parentID = lastID;
					TreeLevel = currentTreeLevel;
					mapx.put(TreeLevel, String.valueOf(parentID));
				} else if (currentTreeLevel < TreeLevel) {
					TreeLevel = currentTreeLevel;
					parentID = Long.parseLong(mapx.get(TreeLevel).toString());
				}
			} else {
				TreeLevel = topTreeLevel;
				parentID = Long.parseLong(mapx.get(TreeLevel).toString());
			}

			catalogName = catalogName.trim();

			ZCCatalogSchema pCatalog = new ZCCatalogSchema();
			ZCCatalogSchema catalog = new ZCCatalogSchema();
			if (parentID != 0L) {
				pCatalog.setID(parentID);
				boolean isNewData = false;
				if (!(pCatalog.fill())) {
					pCatalog = (ZCCatalogSchema) catalogMap.get(parentID);
					isNewData = true;
				}

				catalog.setParentID(pCatalog.getID());
				catalog.setSiteID(pCatalog.getSiteID());
				catalog.setTreeLevel(pCatalog.getTreeLevel() + 1L);
				pCatalog.setChildCount(pCatalog.getChildCount() + 1L);
				pCatalog.setIsLeaf(0L);
				catalogMap.put(pCatalog.getID(), pCatalog);

				if (isNewData) {
					trans.add(pCatalog, 1);
					initCatalogConfig(pCatalog, trans);
				} else {
					trans.add(pCatalog, 2);
				}
			} else {
				ZCSiteSchema site = new ZCSiteSchema();
				site.setID(Application.getCurrentSiteID());
				site.fill();

				catalog.setParentID(0L);
				catalog.setSiteID(site.getID());
				catalog.setTreeLevel(1L);

				if ("1".equals(type))
					site.setChannelCount(site.getChannelCount() + 1L);
				else if ("2".equals(type))
					site.setSpecialCount(site.getSpecialCount() + 1L);
				else if ("3".equals(type))
					site.setMagzineCount(site.getMagzineCount() + 1L);
				else if ("4".equals(type)) {
					site.setImageLibCount(site.getMagzineCount() + 1L);
				}
				trans.add(site, 2);
			}

			if (checkAliasExists(catalogName, catalog.getParentID())) {
				continue;
			}

			String alias = ChineseSpelling.getFirstAlpha(catalogName).toLowerCase();
			if (checkAliasExists(alias, catalog.getParentID())) {
				alias = alias + NoUtil.getMaxID("AliasNo");
			}

			String innerCode = CatalogUtil.createCatalogInnerCode(pCatalog.getInnerCode());
			catalog.setInnerCode(innerCode);

			lastID = NoUtil.getMaxID("CatalogID");
			catalog.setID(lastID);
			catalog.setName(catalogName);
			catalog.setAlias(alias);

			catalog.setURL(alias + "/");
			if ((StringUtil.isNotEmpty(type)) && (!("null".equals(type))))
				catalog.setType(Integer.parseInt(type));
			else {
				catalog.setType(1L);
			}

			catalog.setListTemplate($V("ListTemplate"));
			catalog.setDetailTemplate($V("DetailTemplate"));
			catalog.setChildCount(0L);
			catalog.setIsLeaf(1L);
			catalog.setTotal(0L);
			catalog.setLogo($V("Logo"));
			catalog.setListPageSize(20L);
			catalog.setPublishFlag("Y");
			catalog.setOrderFlag(orderFlag);
			catalog.setSingleFlag("N");
			catalog.setKeywordFlag("N");
			catalog.setAllowContribute("N");
			catalog.setHitCount(0L);
			catalog.setMeta_Keywords($V("Meta_Keywords"));
			catalog.setMeta_Description($V("Meta_Description"));
			catalog.setOrderColumn($V("OrderColumn"));
			catalog.setKeywordSetting("NO");
			catalog.setProp1($V("Prop1"));
			catalog.setProp2($V("Prop2"));
			catalog.setProp3($V("Prop3"));
			catalog.setProp4($V("Prop4"));
			catalog.setAddUser(User.getUserName());
			catalog.setAddTime(new Date());

			catalogMap.put(catalog.getID(), catalog);

			trans.add(catalog, 1);
			initCatalogConfig(catalog, trans);
		}

		if (trans.commit()) {
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("插入数据发生错误!");
		}
	}

	public void move() {
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		long catalogID = Long.parseLong($V("CatalogID"));
		long parentID = Long.parseLong($V("ParentID"));
		catalog.setID(catalogID);
		catalog.fill();
		Transaction tran = new Transaction();

		long TreeLevel = 0L;
		String parentInnerCode = "";
		if (parentID == 0L) {
			TreeLevel = 1L;
			if (catalog.getTreeLevel() == 1L) {
				this.Response.setStatus(0);
				this.Response.setMessage("选择的栏目和目标栏目处于同一级，不能转移。");
				return;
			}

			ZCSiteSchema site = new ZCSiteSchema();
			site.setID(Application.getCurrentSiteID());
			site.fill();

			long type = catalog.getType();
			if (type == 1L)
				site.setChannelCount(site.getChannelCount() + 1L);
			else if (type == 2L)
				site.setSpecialCount(site.getSpecialCount() + 1L);
			else if (type == 3L) {
				site.setMagzineCount(site.getMagzineCount() + 1L);
			}
			tran.add(site, 2);
		} else {
			ZCCatalogSchema pCatalog = new ZCCatalogSchema();
			pCatalog.setID(parentID);
			pCatalog.fill();

			parentInnerCode = pCatalog.getInnerCode();
			TreeLevel = pCatalog.getTreeLevel() + 1L;

			if ((catalog.getTreeLevel() == TreeLevel) && (catalog.getParentID() == parentID)) {
				this.Response.setStatus(0);
				this.Response.setMessage("选择的栏目和目标栏目处于同一级，不能转移。");
				return;
			}

			pCatalog.setChildCount(pCatalog.getChildCount() + 1L);
			pCatalog.setIsLeaf(0L);
			tran.add(pCatalog, 2);
		}

		long oldParentID = catalog.getParentID();
		if (oldParentID == 0L) {
			ZCSiteSchema site = new ZCSiteSchema();
			site.setID(Application.getCurrentSiteID());
			site.fill();

			long type = catalog.getType();
			if (type == 1L)
				site.setChannelCount(site.getChannelCount() - 1L);
			else if (type == 2L)
				site.setSpecialCount(site.getSpecialCount() - 1L);
			else if (type == 3L) {
				site.setMagzineCount(site.getMagzineCount() - 1L);
			}
			tran.add(site, 2);
		} else {
			tran.add(new QueryBuilder("update zccatalog set childcount=childcount-1 where id=?",
					oldParentID));
		}

		String orderflag = getCatalogOrderFlag(parentID, catalog.getType());
		String innerCode = (TreeLevel == 1L) ? CatalogUtil.createCatalogInnerCode("") : CatalogUtil
				.createCatalogInnerCode(parentInnerCode);
		catalog.setInnerCode(innerCode);

		catalog.setTreeLevel(TreeLevel);

		catalog.setParentID(parentID);
		catalog.setOrderFlag(getCatalogOrderFlag(parentID, catalog.getType()));
		catalog.setModifyUser(User.getUserName());
		catalog.setModifyTime(new Date());

		tran.add(catalog, 2);
		tran.add(new QueryBuilder("update zccatalog set orderflag = " + orderflag + 1
				+ " where id =?", catalogID));
		tran.add(new QueryBuilder("update zcarticle set CatalogInnerCode=? where catalogid=?",
				innerCode, catalogID));
		tran.add(new QueryBuilder("update bzcarticle set CatalogInnerCode=? where catalogid=?",
				innerCode, catalogID));

		Mapx map = new Mapx();
		if (catalog.getChildCount() > 0L) {
			ZCCatalogSet childCatalogSet = new ZCCatalogSchema().query(new QueryBuilder(
					"where parentid=?", catalogID));
			for (int i = 0; i < childCatalogSet.size(); ++i) {
				Mapx childMap = moveChild(childCatalogSet.get(i), TreeLevel, innerCode, orderflag
						+ 2 + i);
				map.putAll(childMap);
			}
		}

		Object[] ks = map.keyArray();
		for (int i = 0; i < map.size(); ++i) {
			String sql = ks[i].toString();
			tran.add(new QueryBuilder(sql));
		}

		String count = new QueryBuilder("select count(*) from zccatalog where innercode like '"
				+ CatalogUtil.getInnerCode(catalogID) + "%' and type=" + catalog.getType())
				.executeString();
		tran.add(new QueryBuilder("update zccatalog set orderflag=" + orderflag + count
				+ " where orderflag > " + orderflag + " and type=" + catalog.getType()
				+ " and innercode not like '" + CatalogUtil.getInnerCode(catalogID) + "%'"));

		if (tran.commit()) {
			CatalogUtil.update(catalogID);
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("保存数据发生错误!");
		}
	}

	public Mapx moveChild(ZCCatalogSchema catalog, long TreeLevel, String parentInnerCode,
			String orderflag) {
		Mapx map = new Mapx();
		long catalogID = catalog.getID();

		TreeLevel += 1L;

		String innerCode = CatalogUtil.createCatalogInnerCode(parentInnerCode);

		map.put(
				"update zccatalog set TreeLevel=" + TreeLevel + ",innercode='" + innerCode
						+ "',modifyuser='" + User.getUserName() + "',modifytime='"
						+ DateUtil.getCurrentDate() + "',OrderFlag=" + orderflag + " where id="
						+ catalogID, new Integer(4));

		map.put("update zcarticle set CatalogInnerCode='" + innerCode + "' where catalogid="
				+ catalogID, new Integer(4));
		map.put("update bzcarticle set CatalogInnerCode='" + innerCode + "' where catalogid="
				+ catalogID, new Integer(4));

		if (catalog.getChildCount() > 0L) {
			ZCCatalogSet childCatalogSet = new ZCCatalogSchema().query(new QueryBuilder(
					"where parentid=?", catalogID));
			for (int i = 0; i < childCatalogSet.size(); ++i) {
				Mapx childMap = moveChild(childCatalogSet.get(i), TreeLevel, innerCode, orderflag
						+ 1 + i);
				map.putAll(childMap);
			}
		}

		return map;
	}

	public void publish() {
		long id;
		if ("0".equals($V("type"))) {
			id = publishAllTask(Application.getCurrentSiteID());
			this.Response.setStatus(1);
			$S("TaskID", id);
		} else {
			long catalogID = Long.parseLong($V("CatalogID"));
			if ("1".equals($V("ChildFlag")))
				id = publishTask(catalogID, true, true);
			else {
				id = publishTask(catalogID, false, true);
			}
			this.Response.setStatus(1);
			$S("TaskID", id);
		}
	}

	private long publishTask(final long catalogID, final boolean child, final boolean detail) {
		LongTimeTask ltt = new LongTimeTask() {

			public void execute() {
				Publisher p = new Publisher();
				p.publishCatalog(catalogID, child, detail, this);
				setPercent(100);
			}
		};
		ltt.setUser(User.getCurrent());
		ltt.start();
		return ltt.getTaskID();
	}

	public long publishAllTask(final long siteID) {
		LongTimeTask ltt = new LongTimeTask() {

			public void execute() {
				Publisher p = new Publisher();
				p.publishAll(siteID, this);
				setPercent(100);
			}
		};
		ltt.setUser(User.getCurrent());
		ltt.start();
		return ltt.getTaskID();
	}

	public void del() {
		Transaction trans = new Transaction();
		String ID = $V("CatalogID");

		deleteCatalog(trans, Long.parseLong(ID));

		if (trans.commit()) {
			CatalogUtil.update(ID);
			UserLog.log("Site", "DelCataLog", "删除栏目成功", this.Request.getClientIP());
			this.Response.setStatus(1);
		} else {
			UserLog.log("Site", "DelCataLog", "删除栏目失败", this.Request.getClientIP());
			this.Response.setStatus(0);
			this.Response.setMessage("删除栏目失败");
		}
	}

	public void clean() {
		Transaction trans = new Transaction();
		String ID = $V("CatalogID");
		trans
				.add(new QueryBuilder(
						"delete from zcarticlelog where exists(select articleid from zcarticle where id=zcarticlelog.articleid and catalogid=?)",
						ID));
		trans.add(new QueryBuilder("delete from zcarticle where catalogid=?", ID));
		if (trans.commit()) {
			Publisher p = new Publisher();
			FileUtil.delete(CatalogUtil.getAbsolutePath(Long.parseLong(ID)));
			p.publishCatalog(Long.parseLong(ID), false, true);
			this.Response.setStatus(1);
		} else {
			this.Response.setStatus(0);
			this.Response.setMessage("清空栏目失败");
		}
	}

	public static void deleteCatalog(Transaction trans, long ID) {
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(ID);
		catalog.fill();

		ZCCatalogSchema pCatalog = new ZCCatalogSchema();
		pCatalog.setID(catalog.getParentID());
		pCatalog.fill();
		pCatalog.setChildCount(pCatalog.getChildCount() - 1L);
		if (pCatalog.getChildCount() == 0L)
			pCatalog.setIsLeaf(1L);
		else {
			pCatalog.setIsLeaf(0L);
		}
		trans.add(pCatalog, 2);

		ZCCatalogSet catalogSet = catalog.query(new QueryBuilder(" where InnerCode like ?", catalog
				.getInnerCode()
				+ "%"));
		trans.add(catalogSet, 5);
		trans.add(new ZCArticleSchema().query(new QueryBuilder(" where CatalogInnerCode like ?",
				catalog.getInnerCode() + "%")), 5);
		trans.add(new ZCImageSchema().query(new QueryBuilder(" where CatalogInnerCode like ?",
				catalog.getInnerCode() + "%")), 5);
		trans.add(new ZCVideoSchema().query(new QueryBuilder(" where CatalogInnerCode like ?",
				catalog.getInnerCode() + "%")), 5);
		trans.add(new ZCAudioSchema().query(new QueryBuilder(" where CatalogInnerCode like ?",
				catalog.getInnerCode() + "%")), 5);
		trans.add(new ZCAttachmentSchema().query(new QueryBuilder(" where CatalogInnerCode like ?",
				catalog.getInnerCode() + "%")), 5);
		trans.add(new ZCCatalogConfigSchema().query(new QueryBuilder(
				" where siteID=? and CatalogInnerCode like ?", catalog.getSiteID(), catalog
						.getInnerCode()
						+ "%")), 5);

		String ids = "";
		for (int i = 0; i < catalogSet.size(); ++i) {
			ids = ids + catalogSet.get(i).getID();
			if (i != catalogSet.size() - 1) {
				ids = ids + ",";
			}

			FileUtil.delete(CatalogUtil.getAbsolutePath(catalogSet.get(i).getID()));
		}
		ZCPageBlockSet blockSet = new ZCPageBlockSchema().query(new QueryBuilder(
				" where catalogid in (" + ids + ")"));
		for (int i = 0; i < blockSet.size(); ++i) {
			ZCPageBlockItemSet itemSet = new ZCPageBlockItemSchema().query(new QueryBuilder(
					" where blockID=?", blockSet.get(i).getID()));
			trans.add(itemSet, 5);
		}
		trans.add(blockSet, 5);

		String idsStr = "'" + ids.replaceAll(",", "','") + "'";

		ZDColumnRelaSet columnRelaSet = new ZDColumnRelaSchema().query(new QueryBuilder(
				" where RelaID in(" + idsStr + ")"));
		trans.add(columnRelaSet, 5);

		ZDColumnValueSet columnValueSet1 = new ZDColumnValueSchema().query(new QueryBuilder(
				" where RelaID in(" + idsStr + ")"));
		trans.add(columnValueSet1, 5);

		String wherepart = " where exists (select ID from zcarticle where cataloginnercode like '"
				+ catalog.getInnerCode() + "%' and ID=zdcolumnvalue.relaID )";
		if (Config.isDB2()) {
			wherepart = " where exists (select ID from zcarticle where cataloginnercode like '"
					+ catalog.getInnerCode() + "%' and char(ID)=zdcolumnvalue.relaID )";
		}
		ZDColumnValueSet columnValueSet2 = new ZDColumnValueSchema().query(new QueryBuilder(
				wherepart));
		trans.add(columnValueSet2, 5);
	}

	public static void createDefaultCatalog(String siteID, int type) {
		createDefaultCatalog(Long.parseLong(siteID), type);
	}

	public static void createDefaultCatalog(long siteID, int type) {
		DataTable dt = new QueryBuilder("select id from zccatalog where siteid =?  and type=?",
				siteID, type).executePagedDataTable(1, 0);
		if ((dt != null) && (dt.getRowCount() > 0)) {
			return;
		}
		Transaction trans = new Transaction();

		ZCCatalogSchema defaultCatalog = new ZCCatalogSchema();
		defaultCatalog.setID(NoUtil.getMaxID("CatalogID"));
		defaultCatalog.setSiteID(siteID);
		defaultCatalog.setParentID(0L);
		defaultCatalog.setInnerCode(CatalogUtil.createCatalogInnerCode(""));
		defaultCatalog.setTreeLevel(1L);
		String Name = "";
		switch (type) {
		case 4:
			Name = "默认图片";
			break;
		case 5:
			Name = "默认视频";
			break;
		case 6:
			Name = "默认音频";
			break;
		case 7:
			Name = "默认附件";
			break;
		default:
			Name = "默认图片";
		}

		defaultCatalog.setName(Name);
		defaultCatalog.setURL("");
		defaultCatalog.setAlias("default");
		defaultCatalog.setType(type);
		defaultCatalog.setListTemplate("");
		defaultCatalog.setListNameRule("");
		defaultCatalog.setDetailTemplate("");
		defaultCatalog.setDetailNameRule("");
		defaultCatalog.setChildCount(0L);
		defaultCatalog.setIsLeaf(1L);
		defaultCatalog.setTotal(0L);
		defaultCatalog.setOrderFlag(getCatalogOrderFlag(0L, type));
		defaultCatalog.setLogo("");
		defaultCatalog.setListPageSize(20L);
		defaultCatalog.setPublishFlag("Y");
		defaultCatalog.setHitCount(0L);
		defaultCatalog.setMeta_Keywords("");
		defaultCatalog.setMeta_Description("");
		defaultCatalog.setOrderColumn("");
		defaultCatalog.setAddUser(User.getUserName());
		defaultCatalog.setAddTime(new Date());
		trans.add(defaultCatalog, 1);
		trans.commit();
	}

	public void sortCatalog() {
		String CatalogType = this.Request.get("CatalogType").toString();
		int Move = Integer.parseInt(this.Request.get("Move").toString());
		String CatalogID = this.Request.get("CatalogID").toString();
		if (sortCatalog(CatalogID, Move, CatalogType))
			this.Response.setLogInfo(1, "排序成功！");
		else
			this.Response.setLogInfo(0, "排序失败！");
	}

	public static boolean sortCatalog(String CatalogID, int Move, String CatalogType) {
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(CatalogID);
		if (!catalog.fill())
			return true;
		String TreeLevel = (new StringBuffer(String.valueOf(catalog.getTreeLevel()))).toString();
		DataTable allDT = (new QueryBuilder("select * from zccatalog where siteID = ? and Type = "
				+ CatalogType + " order by orderflag,innercode", Application.getCurrentSiteID()))
				.executeDataTable();
		int selfSize = (new QueryBuilder("select count(1) from zccatalog where siteID = "
				+ Application.getCurrentSiteID() + " and innercode like '" + catalog.getInnerCode()
				+ "%' and Type = " + CatalogType)).executeInt();
		List catalogList = new ArrayList();
		if (Move > 0) {
			int count = 0;
			int index = 0;
			boolean isMove = false;
			for (int i = allDT.getRowCount() - 1; i >= 0; i--) {
				if (isMove
						&& allDT.getString(i, "TreeLevel").equals(TreeLevel)
						&& (TreeLevel.equals("1") || allDT.getString(i, "InnerCode").startsWith(
								catalog.getInnerCode().substring(0,
										catalog.getInnerCode().length() - 4))))
					count++;
				if (allDT.getString(i, "ID").equals(CatalogID)) {
					index = i;
					isMove = true;
				} else {
					if (allDT.getString(i, "InnerCode").startsWith(catalog.getInnerCode()))
						continue;
					catalogList.add(allDT.getDataRow(i));
				}
				if (count == Move
						|| count != 0
						&& i - 1 >= 0
						&& count < Move
						&& Integer.parseInt(allDT.getString(i - 1, "TreeLevel")) < Integer
								.parseInt(TreeLevel) || count != 0 && count < Move && i == 0) {
					for (int j = (index + selfSize) - 1; j >= index; j--)
						catalogList.add(allDT.getDataRow(j));

					Move = -1;
				}
			}

			if (count > 0) {
				Transaction trans = new Transaction();
				for (int i = 0; i < catalogList.size(); i++) {
					DataRow dr = (DataRow) catalogList.get(i);
					trans.add(new QueryBuilder("update zccatalog set orderflag = ? where ID = ?",
							catalogList.size() - i, dr.getString("ID")));
				}

				return trans.commit();
			}
		} else {
			Move = -Move;
			int count = 0;
			int index = 0;
			boolean isMove = false;
			for (int i = 0; i < allDT.getRowCount(); i++) {
				if (isMove
						&& allDT.getString(i, "TreeLevel").equals(TreeLevel)
						&& (TreeLevel.equals("1") || allDT.getString(i, "InnerCode").startsWith(
								catalog.getInnerCode().substring(0,
										catalog.getInnerCode().length() - 4))))
					count++;
				if (allDT.getString(i, "ID").equals(CatalogID)) {
					index = i;
					i = (i - 1) + selfSize;
					isMove = true;
				} else {
					catalogList.add(allDT.getDataRow(i));
				}
				if (count == Move
						|| count != 0
						&& i + 1 < allDT.getRowCount()
						&& count < Move
						&& Integer.parseInt(allDT.getString(i + 1, "TreeLevel")) < Integer
								.parseInt(TreeLevel) || count != 0 && count < Move
						&& i == allDT.getRowCount() - 1) {
					for (int j = index; j < index + selfSize; j++)
						catalogList.add(allDT.getDataRow(j));

					Move = -1;
				}
			}

			if (count > 0) {
				Transaction trans = new Transaction();
				for (int i = 0; i < catalogList.size(); i++) {
					DataRow dr = (DataRow) catalogList.get(i);
					trans.add(new QueryBuilder("update zccatalog set orderflag = ? where ID = ?",
							i + 1, dr.getString("ID")));
				}

				return trans.commit();
			}
		}
		return true;
	}


	public static String getCatalogOrderFlag(long ParentID, long CatalogType) {
		return getCatalogOrderFlag(ParentID, CatalogType);
	}

	public static String getCatalogOrderFlag(String ParentID, long CatalogType) {
		return getCatalogOrderFlag(ParentID, CatalogType);
	}

	public static String getCatalogOrderFlag(long ParentID, String CatalogType) {
		return getCatalogOrderFlag(ParentID, CatalogType);
	}

	public static boolean checkAliasExists(String alias, long parentID) {
		int count = new QueryBuilder(
				"select count(*) from zccatalog where alias=? and parentID=? and siteID="
						+ Application.getCurrentSiteID(), alias, parentID).executeInt();
		return (count > 0);
	}

	public static boolean checkNameExists(String Name, long parentID) {
		int count = new QueryBuilder(
				"select count(*) from zccatalog where name=? and parentID=? and siteID="
						+ Application.getCurrentSiteID(), Name, parentID).executeInt();
		return (count > 0);
	}

	public static String getCatalogOrderFlag(String ParentID, String CatalogType) {
		DataTable parentDT = null;
		if ((StringUtil.isEmpty(ParentID)) || ("0".equals(ParentID))) {
			parentDT = new QueryBuilder("select * from zccatalog where siteID = "
					+ Application.getCurrentSiteID() + " and type = " + CatalogType
					+ " and TreeLevel = 1 order by orderflag,innercode").executeDataTable();
		} else {
			String innercode = CatalogUtil.getInnerCode(ParentID);
			parentDT = new QueryBuilder("select * from zccatalog where siteID = "
					+ Application.getCurrentSiteID() + " and type = " + CatalogType
					+ " and innercode like '" + innercode + "%' order by orderflag,innercode")
					.executeDataTable();
		}
		if ((parentDT != null) && (parentDT.getRowCount() > 0)) {
			return parentDT.getString(parentDT.getRowCount() - 1, "OrderFlag");
		}
		return "0";
	}

	public static void main(String[] args) {
		ZCCatalogSet set = new ZCCatalogSchema().query(new QueryBuilder(
				"where siteid=14 order by id"));
		for (int k = 1; k < 30; ++k)
			for (int i = 0; i < set.size(); ++i) {
				ZCCatalogSchema schema = (ZCCatalogSchema) set.get(i).clone();
				if (schema.getTreeLevel() == 1L)
					schema.setInnerCode(StringUtil.leftPad(String.valueOf(Integer.parseInt(schema
							.getInnerCode().substring(0, 4))
							+ set.size() * k / 3), '0', 4));
				else if (schema.getTreeLevel() == 2L)
					schema.setInnerCode(StringUtil.leftPad(String.valueOf(Integer.parseInt(schema
							.getInnerCode().substring(0, 4))
							+ set.size() * k / 3), '0', 4)
							+ "0001");
				else if (schema.getTreeLevel() == 3L) {
					schema.setInnerCode(StringUtil.leftPad(String.valueOf(Integer.parseInt(schema
							.getInnerCode().substring(0, 4))
							+ set.size() * k / 3), '0', 4)
							+ "00010001");
				}

				schema.setID(schema.getID() + set.size() * k);
				if (schema.getParentID() != 0L) {
					schema.setParentID(schema.getParentID() + set.size() * k);
				}
				schema.insert();
			}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.site.Catalog JD-Core Version: 0.5.3
 */