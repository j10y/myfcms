package com.zving.cms.resource;

import com.zving.cms.datachannel.Publisher;
import com.zving.cms.pub.CatalogUtil;
import com.zving.cms.pub.SiteUtil;
import com.zving.cms.site.Catalog;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataListAction;
import com.zving.framework.controls.TreeAction;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.data.Transaction;
import com.zving.framework.utility.ChineseSpelling;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.Filter;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.Priv;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCCatalogSchema;
import com.zving.schema.ZCCatalogSet;
import com.zving.schema.ZCImageRelaSchema;
import com.zving.schema.ZCImageRelaSet;
import com.zving.schema.ZCImageSchema;
import com.zving.schema.ZCImageSet;
import com.zving.schema.ZCSiteSchema;
import java.io.File;
import java.util.Date;

public class ImageLib extends Page {
	public static Mapx initEditDialog(Mapx params) {
		String ID = params.getString("ID");
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(ID);
		catalog.fill();
		return catalog.toMapx();
	}

	public void setImageCover() {
		String ID = $V("ID");
		String imagePath = "";
		DataTable dt = new QueryBuilder("select path,filename from zcimage where id=?", ID)
				.executeDataTable();
		if (dt.getRowCount() > 0) {
			imagePath = dt.get(0, "path").toString() + dt.get(0, "filename").toString();
		}
		ZCImageSchema image = new ZCImageSchema();
		image.setID(Long.parseLong(ID));
		image.fill();
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(image.getCatalogID());
		catalog.fill();
		catalog.setImagePath(imagePath);
		if (catalog.update())
			this.Response.setLogInfo(1, "设置专辑封面成功！");
		else
			this.Response.setLogInfo(0, "设置专辑封面失败！");
	}

	public void setTopper() {
		String ID = $V("ID");
		ZCImageSchema image = new ZCImageSchema();
		image.setID(Long.parseLong(ID));
		image.fill();
		QueryBuilder qb = new QueryBuilder(
				"select max(OrderFlag) from ZCImage where CatalogID = ?", image.getCatalogID());
		image.setOrderFlag(qb.executeLong() + 1L);
		if (image.update())
			this.Response.setLogInfo(1, "置顶成功！");
		else
			this.Response.setLogInfo(0, "置顶失败！");
	}

	public void ImageLibEdit() {
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setValue(this.Request);
		catalog.fill();
		catalog.setValue(this.Request);
		catalog.setAlias(StringUtil.getChineseFirstAlpha(catalog.getName()));
		if (catalog.update()) {
			CatalogUtil.update(catalog.getID());
			this.Response.setLogInfo(1, "修改图片分类成功！");
		} else {
			this.Response.setLogInfo(0, "修改图片分类失败！");
		}
	}

	public void delLib() {
		String catalogID = $V("catalogID");
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(Long.parseLong(catalogID));
		if (!(catalog.fill())) {
			this.Response.setLogInfo(0, "没有图片分类！");
		}

		ZCCatalogSet catalogSet = new ZCCatalogSchema().query(new QueryBuilder(
				"where InnerCode like '" + catalog.getInnerCode() + "%'"));
		Transaction trans = new Transaction();
		ZCImageSet imageSet = new ZCImageSchema().query(new QueryBuilder(
				"where CatalogInnerCode like '" + catalog.getInnerCode() + "%'"));
		for (int i = 0; i < imageSet.size(); ++i) {
			ZCImageRelaSet ImageRelaSet = new ZCImageRelaSchema().query(new QueryBuilder(
					" where id = ?", imageSet.get(i).getID()));
			trans.add(ImageRelaSet, 5);
		}

		File file = new File(Config.getContextRealPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/upload/Image/"
				+ CatalogUtil.getPath(catalog.getID()));
		FileUtil.delete(file);
		trans.add(imageSet, 5);
		trans.add(catalogSet, 5);
		if (trans.commit()) {
			CatalogUtil.clearAll();
			this.Response.setLogInfo(1, "删除图片分类成功！");

			Publisher p = new Publisher();
			p.deleteFileTask(imageSet);
		} else {
			this.Response.setLogInfo(0, "删除图片分类失败！");
		}
	}

	public static void treeDataBind(TreeAction ta) {
		String SiteID = String.valueOf(Application.getCurrentSiteID());
		DataTable dt = null;
		Mapx params = ta.getParams();
		String parentLevel = (String) params.get("ParentLevel");
		String parentID = (String) params.get("ParentID");
		QueryBuilder qb;
		if (ta.isLazyLoad()) {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,SingleFlag,InnerCode from ZCCatalog Where Type =? and SiteID =? and TreeLevel>? and innerCode like ? order by orderflag");
			qb.add(4);
			qb.add(SiteID);
			qb.add(parentLevel);
			qb.add(CatalogUtil.getInnerCode(parentID) + "%");
			dt = qb.executeDataTable();
		} else {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,SingleFlag,InnerCode from ZCCatalog Where Type =? and SiteID=? and TreeLevel-1<=? order by orderflag");
			qb.add(4);
			qb.add(SiteID);
			qb.add(ta.getLevel());
			dt = qb.executeDataTable();
		}
		ta.setRootText("图片库");
		dt = dt.filter(new Filter() {
			public boolean filter(Object obj) {
				DataRow dr = (DataRow) obj;
				return Priv.getPriv(User.getUserName(), "image", dr.getString("InnerCode"),
						"image_browse");
			}
		});
		ta.bindData(dt);
	}

	public static void dg1DataList(DataListAction dla) {
		String CatalogID = dla.getParam("CatalogID");
		if (StringUtil.isEmpty(CatalogID)) {
			dla.bindData(new DataTable());
			return;
		}
		String Name = dla.getParam("Name");
		String StartDate = dla.getParam("StartDate");
		String EndDate = dla.getParam("EndDate");
		QueryBuilder qb = new QueryBuilder();
		StringBuffer conditions = new StringBuffer();
		conditions.append(" where 1 = 1");
		if ("0".equals(CatalogID)) {
			conditions.append(" and SiteID = ?");
			qb.add(Application.getCurrentSiteID());
		} else {
			conditions.append(" and CatalogID = ?");
			qb.add(CatalogID);
		}
		if (StringUtil.isNotEmpty(Name)) {
			conditions.append(" and Name like ?");
			qb.add("%" + Name.trim() + "%");
		}
		if (StringUtil.isNotEmpty(StartDate)) {
			conditions.append(" and addtime >= ? ");
			qb.add(StartDate);
		}
		if (StringUtil.isNotEmpty(EndDate)) {
			conditions.append(" and addtime <= ? ");
			qb.add(EndDate);
		}
		qb.setSQL("select count(*) from ZCImage " + conditions);
		dla.setTotal(qb);
		String alias = Config.getContextPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
		alias = alias.replaceAll("///", "/");
		alias = alias.replaceAll("//", "/");
		String sql = "select * from ZCImage " + conditions + " order by OrderFlag desc,ID desc";
		qb.setSQL(sql);
		DataTable dt = qb.executePagedDataTable(dla.getPageSize(), dla.getPageIndex());
		dt.insertColumn("Alias", alias);
		dla.bindData(dt);
	}

	public static void dg1DataListBrowse(DataListAction dla) {
		String Search = dla.getParam("Search");
		if ((Search == null) || ("".equals(Search))) {
			dla.bindData(new DataTable());
			return;
		}
		String alias = Config.getContextPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
		alias = alias.replaceAll("///", "/");
		alias = alias.replaceAll("//", "/");
		String CatalogID = dla.getParam("_CatalogID");
		String Name = dla.getParam("Name");
		String StartDate = dla.getParam("StartDate");
		String EndDate = dla.getParam("EndDate");
		String Info = dla.getParam("Info");
		StringBuffer conditions = new StringBuffer();
		QueryBuilder qb = new QueryBuilder();
		if ((StringUtil.isNotEmpty(CatalogID)) && (!("null".equals(CatalogID)))) {
			conditions.append(" where CatalogID = ?");
			qb.add(CatalogID);
		} else {
			conditions.append(" where SiteID = ?");
			qb.add(Application.getCurrentSiteID());
		}
		if (StringUtil.isNotEmpty(Name)) {
			conditions.append(" and Name like ?");
			qb.add("%" + Name.trim() + "%");
		}
		if (StringUtil.isNotEmpty(Info)) {
			conditions.append(" and Info like ?");
			qb.add("%" + Info.trim() + "%");
		}
		if (StringUtil.isNotEmpty(StartDate)) {
			conditions.append(" and addtime >= ? ");
			qb.add(StartDate);
		}
		if (StringUtil.isNotEmpty(EndDate)) {
			conditions.append(" and addtime <= ? ");
			qb.add(EndDate);
		}

		String sql = "select * from ZCImage " + conditions + " order by ID desc";
		qb.setSQL("select count(1) from ZCImage " + conditions);
		dla.setTotal(qb);
		qb.setSQL(sql);
		DataTable dt = qb.executePagedDataTable(dla.getPageSize(), dla.getPageIndex());
		String SelectType = dla.getParam("SelectType");
		if ((SelectType == null) || ("".equals(SelectType))) {
			SelectType = "checkbox";
		}
		dt.insertColumn("alias", alias);
		dt.insertColumn("selecttype", SelectType);
		dla.bindData(dt);
	}

	public void add() {
		String name = $V("Name");
		String parentID = $V("ParentID");
		String DT = $V("DetailTemplate");
		String LT = $V("ListTemplate");
		String IT = $V("IndexTemplate");
		Transaction trans = new Transaction();
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		ZCCatalogSchema pCatalog = new ZCCatalogSchema();

		catalog.setID(NoUtil.getMaxID("CatalogID"));
		catalog.setSiteID(Application.getCurrentSiteID());

		if ((parentID.equals("0")) || (StringUtil.isEmpty(parentID))) {
			catalog.setParentID(0L);
			catalog.setTreeLevel(1L);
			ZCSiteSchema site = new ZCSiteSchema();
			site.setID(catalog.getSiteID());
			site.fill();
			site.setImageLibCount(site.getImageLibCount() + 1L);
			trans.add(site, 2);
		} else {
			catalog.setParentID(Long.parseLong(parentID));
			pCatalog.setID(catalog.getParentID());
			pCatalog.fill();
			catalog.setTreeLevel(pCatalog.getTreeLevel() + 1L);

			pCatalog.setChildCount(pCatalog.getChildCount() + 1L);
			trans.add(pCatalog, 2);
		}

		String innerCode = CatalogUtil.createCatalogInnerCode(pCatalog.getInnerCode());
		catalog.setInnerCode(innerCode);

		catalog.setName(name);
		catalog.setURL(" ");
		String alias = ChineseSpelling.getFirstAlpha(name).toLowerCase();
		if (Catalog.checkAliasExists(alias, catalog.getParentID())) {
			alias = alias + NoUtil.getMaxID("AliasNo");
		}
		catalog.setAlias(alias);
		catalog.setType(4L);
		catalog.setIndexTemplate(IT);
		catalog.setListTemplate(LT);
		catalog.setListNameRule("");
		catalog.setDetailTemplate(DT);
		catalog.setDetailNameRule("");
		catalog.setChildCount(0L);
		catalog.setIsLeaf(1L);
		catalog.setTotal(0L);
		catalog.setOrderFlag(Catalog.getCatalogOrderFlag(parentID, catalog.getType()));
		catalog.setLogo("");
		catalog.setListPageSize(10L);
		catalog.setPublishFlag("Y");
		catalog.setHitCount(0L);
		catalog.setMeta_Keywords("");
		catalog.setMeta_Description("");
		catalog.setOrderColumn("");
		catalog.setAddUser(User.getUserName());
		catalog.setAddTime(new Date());

		trans.add(catalog, 1);

		Catalog.initCatalogConfig(catalog, trans);

		if (trans.commit()) {
			this.Response.setLogInfo(1, "新建图片分类成功!");
			CatalogUtil.update(catalog.getID());
		} else {
			this.Response.setLogInfo(0, "新建图片分类失败!");
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

		ZCImageSchema image = new ZCImageSchema();
		ZCImageSet set = image.query(new QueryBuilder("where id in(" + ids + ")"));

		this.Response.setStatus(1);
		Publisher p = new Publisher();
		long id = p.publishSetTask("Image", set);
		$S("TaskID", id);
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.resource.ImageLib JD-Core Version: 0.5.3
 */