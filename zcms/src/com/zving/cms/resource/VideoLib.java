package com.zving.cms.resource;

import com.zving.cms.datachannel.Publisher;
import com.zving.cms.pub.CatalogUtil;
import com.zving.cms.pub.SiteUtil;
import com.zving.cms.site.Catalog;
import com.zving.framework.Config;
import com.zving.framework.Page;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.controls.DataGridAction;
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
import com.zving.schema.ZCSiteSchema;
import com.zving.schema.ZCVideoRelaSchema;
import com.zving.schema.ZCVideoRelaSet;
import com.zving.schema.ZCVideoSchema;
import com.zving.schema.ZCVideoSet;
import java.io.File;
import java.util.Date;

public class VideoLib extends Page {
	public static Mapx initEditDialog(Mapx params) {
		long ID = Long.parseLong(params.get("ID").toString());
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(ID);
		catalog.fill();
		return catalog.toMapx();
	}

	public void setVideoCover() {
		String ID = this.Request.get("ID").toString();
		String imagePath = "";
		DataTable dt = new QueryBuilder("select path,filename from ZCVideo where id=?", ID)
				.executeDataTable();
		if (dt.getRowCount() > 0) {
			imagePath = dt.get(0, "path").toString() + dt.get(0, "filename").toString();
		}
		ZCVideoSchema video = new ZCVideoSchema();
		video.setID(Long.parseLong(ID));
		video.fill();
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(video.getCatalogID());
		catalog.fill();
		catalog.setImagePath(imagePath);
		if (catalog.update())
			this.Response.setLogInfo(1, "设置专辑封面成功！");
		else
			this.Response.setLogInfo(0, "设置专辑封面失败！");
	}

	public void setTopper() {
		String ID = this.Request.get("ID").toString();
		ZCVideoSchema video = new ZCVideoSchema();
		video.setID(Long.parseLong(ID));
		video.fill();
		QueryBuilder qb = new QueryBuilder(
				"select max(OrderFlag) from ZCVideo where CatalogID = ?", video.getCatalogID());
		video.setOrderFlag(qb.executeInt() + 1);
		if (video.update())
			this.Response.setLogInfo(1, "置顶成功！");
		else
			this.Response.setLogInfo(0, "置顶失败！");
	}

	public void VideoLibEdit() {
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setValue(this.Request);
		catalog.fill();
		catalog.setValue(this.Request);
		catalog.setAlias(StringUtil.getChineseFirstAlpha(catalog.getName()));
		if (catalog.update()) {
			CatalogUtil.update(catalog.getID());
			this.Response.setLogInfo(1, "修改视频分类成功！");
		} else {
			this.Response.setLogInfo(0, "修改视频分类失败！");
		}
	}

	public static void treeDataBind(TreeAction ta) {
		String SiteID = String.valueOf(Application.getCurrentSiteID());
		DataTable dt = null;
		Mapx params = ta.getParams();
		String parentLevel = params.getString("ParentLevel");
		String parentID = params.getString("ParentID");
		QueryBuilder qb;
		if (ta.isLazyLoad()) {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,SingleFlag,InnerCode from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel>? and innerCode like ? order by orderflag");
			qb.add(5);
			qb.add(SiteID);
			qb.add(parentLevel);
			qb.add(CatalogUtil.getInnerCode(parentID) + "%");
			dt = qb.executeDataTable();
		} else {
			qb = new QueryBuilder(
					"select ID,ParentID,TreeLevel,Name,SingleFlag,InnerCode from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel-1 <=? order by orderflag");
			qb.add(5);
			qb.add(SiteID);
			qb.add(ta.getLevel());
			dt = qb.executeDataTable();
		}
		ta.setRootText("视频库");
		dt = dt.filter(new Filter() {
			public boolean filter(Object obj) {
				DataRow dr = (DataRow) obj;
				return Priv.getPriv(User.getUserName(), "video", dr.getString("InnerCode"),
						"video_browse");
			}
		});
		ta.bindData(dt);
	}

	public static void dg1DataList(DataListAction dla) {
		String Alias = Config.getContextPath() + "/" + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
		Alias = Alias.replaceAll("///", "/");
		Alias = Alias.replaceAll("//", "/");
		String CatalogID = dla.getParams().getString("CatalogID");
		if (StringUtil.isEmpty(CatalogID)) {
			dla.bindData(new DataTable());
			return;
		}
		String Name = dla.getParams().getString("Name");
		String StartDate = dla.getParam("StartDate");
		String EndDate = dla.getParam("EndDate");
		if ((StringUtil.isEmpty(CatalogID)) && (StringUtil.isEmpty(Name))) {
			dla.setTotal(0);
			dla.bindData(new DataTable());
			return;
		}
		StringBuffer conditions = new StringBuffer();
		QueryBuilder qb = new QueryBuilder();
		conditions.append(" where 1 = 1");
		if (StringUtil.isNotEmpty(CatalogID)) {
			conditions.append(" and CatalogID = ?");
			qb.add(CatalogID);
		} else {
			dla.setTotal(0);
			dla.bindData(new DataTable());
			return;
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

		String sql = "select count(1) from ZCVideo " + conditions;
		qb.setSQL(sql);
		dla.setTotal(qb);
		sql = "select * from ZCVideo " + conditions + " order by OrderFlag desc,ID desc";
		qb.setSQL(sql);
		DataTable dt = qb.executePagedDataTable(dla.getPageSize(), dla.getPageIndex());
		dt.insertColumn("Alias", Alias);
		dla.bindData(dt);
	}

	public static void dg1DataBindBrowse(DataGridAction dga) {
		String Search = dga.getParams().getString("Search");
		if ((Search == null) || ("".equals(Search))) {
			dga.bindData(new DataTable());
			return;
		}

		String CatalogID = dga.getParams().getString("_CatalogID");
		String Name = dga.getParams().getString("Name");
		String Info = dga.getParams().getString("Info");
		StringBuffer conditions = new StringBuffer();
		QueryBuilder qb = new QueryBuilder();
		if (StringUtil.isEmpty(CatalogID)) {
			conditions.append(" where SiteID =?");
			qb.add(Application.getCurrentSiteID());
		} else {
			conditions.append(" where CatalogID =?");
			qb.add(CatalogID);
		}
		if (StringUtil.isNotEmpty(Name)) {
			conditions.append(" and Name like ?");
			qb.add("%" + Name.trim() + "%");
		}
		if (StringUtil.isNotEmpty(Info)) {
			conditions.append(" and Info like ?");
			qb.add("%" + Info.trim() + "%");
		}
		String sql = "select count(1) from ZCVideo " + conditions;
		qb.setSQL(sql);
		dga.setTotal(qb);
		sql = "select * from ZCVideo " + conditions + " order by ID desc";
		qb.setSQL(sql);
		DataTable dt = qb.executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
		dga.bindData(dt);
	}

	public static void dg1DataListBrowse(DataGridAction dga) {
		String Search = dga.getParams().getString("Search");
		if ((Search == null) || ("".equals(Search))) {
			dga.bindData(new DataTable());
			return;
		}

		String CatalogID = dga.getParams().getString("_CatalogID");
		String Name = dga.getParams().getString("Name");
		String Info = dga.getParams().getString("Info");
		StringBuffer conditions = new StringBuffer();
		QueryBuilder qb = new QueryBuilder();
		if (StringUtil.isEmpty(CatalogID)) {
			conditions.append(" where SiteID =?");
			qb.add(Application.getCurrentSiteID());
		} else {
			conditions.append(" where CatalogID =?");
			qb.add(CatalogID);
		}
		if (StringUtil.isNotEmpty(Name)) {
			conditions.append(" and Name like ?");
			qb.add("%" + Name.trim() + "%");
		}
		if (StringUtil.isNotEmpty(Info)) {
			conditions.append(" and Info like ?");
			qb.add("%" + Info.trim() + "%");
		}
		String sql = "select count(1) from ZCVideo " + conditions;
		qb.setSQL(sql);
		dga.setTotal(qb);
		sql = "select * from ZCVideo " + conditions + " order by ID desc";
		qb.setSQL(sql);
		DataTable dt = qb.executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
		dga.bindData(dt);
	}

	public void dg1Edit() {
		DataTable dt = (DataTable) this.Request.get("DT");
		ZCVideoSet set = new ZCVideoSet();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			ZCVideoSchema video = new ZCVideoSchema();
			video.setValue(dt.getDataRow(i));
			video.setModifyTime(new Date());
			video.setModifyUser(User.getUserName());
			set.add(video);
		}
		if (set.update())
			this.Response.setLogInfo(1, "保存成功!");
		else
			this.Response.setLogInfo(0, "保存失败!");
	}

	public void edit() {
		ZCVideoSchema Video = new ZCVideoSchema();
		Video.setValue(this.Request);
		Video.fill();
		Video.setValue(this.Request);
		if (Video.update())
			this.Response.setLogInfo(1, "修改视频成功");
		else
			this.Response.setLogInfo(0, "修改视频失败");
	}

	public void add() {
		String name = $V("Name");
		String parentID = $V("ParentID");
		String IT = $V("IndexTemplate");
		String DT = $V("DetailTemplate");
		String LT = $V("ListTemplate");
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
		catalog.setType(5L);
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
			CatalogUtil.update(catalog.getID());
			this.Response.setLogInfo(1, "新建视频分类成功!");
		} else {
			this.Response.setLogInfo(0, "新建视频分类失败!");
		}
	}

	public void delLib() {
		String catalogID = $V("catalogID");
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(Long.parseLong(catalogID));
		if (!(catalog.fill())) {
			this.Response.setLogInfo(0, "没有视频分类！");
		}

		ZCCatalogSet catalogSet = new ZCCatalogSchema().query(new QueryBuilder(
				"where InnerCode like '" + catalog.getInnerCode() + "%'"));
		Transaction trans = new Transaction();
		ZCVideoSet videoSet = new ZCVideoSchema().query(new QueryBuilder(
				" where CatalogInnerCode like '" + catalog.getInnerCode() + "%'"));
		for (int i = 0; i < videoSet.size(); ++i) {
			FileUtil.delete(Config.getContextRealPath() + videoSet.get(i).getPath()
					+ videoSet.get(i).getFileName());
			ZCVideoRelaSet VideoRelaSet = new ZCVideoRelaSchema().query(new QueryBuilder(
					" where id =?", videoSet.get(i).getID()));
			trans.add(VideoRelaSet, 5);
		}

		File file = new File(Config.getContextRealPath() + Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/upload/Video/"
				+ CatalogUtil.getPath(Long.parseLong(catalogID)));
		FileUtil.delete(file);

		trans.add(videoSet, 5);
		trans.add(catalogSet, 5);
		if (trans.commit()) {
			CatalogUtil.clearAll();
			this.Response.setLogInfo(1, "删除视频分类成功！");

			Publisher p = new Publisher();
			p.deleteFileTask(videoSet);
		} else {
			this.Response.setLogInfo(0, "删除视频频分类失败！");
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

		ZCVideoSchema video = new ZCVideoSchema();
		ZCVideoSet set = video.query(new QueryBuilder("where id in(" + ids + ")"));

		this.Response.setStatus(1);
		Publisher p = new Publisher();
		long id = p.publishSetTask("Video", set);
		$S("TaskID", id);
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.resource.VideoLib JD-Core Version: 0.5.3
 */