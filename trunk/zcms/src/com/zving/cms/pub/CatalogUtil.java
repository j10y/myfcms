package com.zving.cms.pub;

import com.zving.cms.dataservice.ColumnUtil;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.platform.pub.NoUtil;
import com.zving.schema.ZCCatalogConfigSchema;
import com.zving.schema.ZCCatalogConfigSet;
import com.zving.schema.ZCCatalogSchema;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CatalogUtil {
	private static int CACHESIZE = 1000;

	private static Mapx ID_NameMap = new Mapx(CACHESIZE);

	private static Mapx ID_AliasMap = new Mapx(CACHESIZE);

	private static Mapx ID_ParentMap = new Mapx(CACHESIZE);

	private static Mapx ID_SiteIDMap = new Mapx(CACHESIZE);

	private static Mapx ID_InnerCodeMap = new Mapx(CACHESIZE);

	private static Mapx ID_CatalogTypeMap = new Mapx(CACHESIZE);

	private static Mapx ID_GoodsTypeIDMap = new Mapx(CACHESIZE);

	private static Mapx ID_ChildCountMap = new Mapx(CACHESIZE);

	private static Mapx ID_TreeLevelMap = new Mapx(CACHESIZE);

	private static Mapx InnerCode_NameMap = new Mapx(CACHESIZE);

	private static Mapx InnerCode_IDMap = new Mapx(CACHESIZE);

	private static Mapx InnerCode_SiteIDMap = new Mapx(CACHESIZE);

	private static Mapx Alias_IDMap = new Mapx(CACHESIZE);

	private static Mapx Names_IDMap = new Mapx(CACHESIZE);

	private static Mapx ID_TemplateRuleMap = new Mapx(CACHESIZE);

	private static Mapx ID_SingleFlagMap = new Mapx(CACHESIZE);

	private static Mapx ID_WorkflowMap = new Mapx(CACHESIZE);

	private static Mapx ID_PathMap = new Mapx(CACHESIZE);

	private static Mapx ID_IDPathMap = new Mapx(CACHESIZE);

	private static Mapx ID_AllowStatusMap = new Mapx(CACHESIZE);

	private static Mapx ID_ArchiveTimeMap = new Mapx(CACHESIZE);

	private static Mapx ID_HotWordFlagMap = new Mapx(CACHESIZE);

	private static Mapx ID_AttachDownFlagMap = new Mapx(CACHESIZE);

	public static String getName(String catalogID) {
		String name = ID_NameMap.getString(catalogID);
		if (name == null) {
			update(catalogID);
			name = ID_NameMap.getString(catalogID);
		}
		return name;
	}

	public static String getName(long catalogID) {
		return getName(catalogID);
	}

	public static String getNameByInnerCode(String catalogInnerCode) {
		String name = InnerCode_NameMap.getString(catalogInnerCode);
		if (name == null) {
			String catalogID = new QueryBuilder("select id from zccatalog where innercode=?",
					catalogInnerCode).executeString();
			update(catalogID);
			name = InnerCode_NameMap.getString(catalogInnerCode);
		}
		return name;
	}

	public static String getIDByNames(String names) {
		return getIDByNames(Application.getCurrentSiteID(), names);
	}

	public static String getIDByNames(long siteID, String names) {
		return getIDByNames(siteID, names);
	}

	public static String getIDByNames(String siteID, String names) {
		if (StringUtil.isEmpty(names)) {
			return null;
		}
		if (names.startsWith("/")) {
			names = names.substring(1);
		}
		if (names.endsWith("/")) {
			names = names.substring(0, names.length() - 1);
		}
		String[] catalogNames = names.split("/");
		int catalogLenth = catalogNames.length;
		String id = "";
		if (catalogLenth > 0) {
			if (StringUtil.isDigit(catalogNames[(catalogLenth - 1)])) {
				id = catalogNames[(catalogLenth - 1)];
			} else if (catalogLenth > 1) {
				String catalogStr = StringUtil.join(catalogNames, "_");
				id = getCatalogIDByNames(siteID, catalogStr);
			} else {
				id = getCatalogIDByNames(siteID, catalogNames[0]);
			}
		}
		return id;
	}

	private static String getCatalogIDByNames(String siteID, String names) {
		if (StringUtil.isEmpty(names)) {
			return null;
		}

		if (names.startsWith("_")) {
			names = names.substring(1);
		}

		if (names.endsWith("_")) {
			names = names.substring(0, names.length() - 1);
		}

		String[] catalogNames = names.split("_");
		String catalogID = "";
		if (catalogNames.length <= 0)
			return null;
		if (catalogNames.length == 1)
			if (StringUtil.isDigit(catalogNames[0])) {
				catalogID = catalogNames[0];
			} else {
				String catalogStr = siteID + "_" + catalogNames[0];
				catalogID = Names_IDMap.getString(catalogStr);
			}
		else if (catalogNames.length > 1) {
			for (int i = 0; i < catalogNames.length; ++i) {
				String catalogStr = "";
				if (i == 0) {
					if (StringUtil.isDigit(catalogNames[i])) {
						catalogID = catalogNames[i];
					} else {
						catalogStr = siteID + "_" + catalogNames[i];
						catalogID = Names_IDMap.getString(catalogStr);
					}
				} else {
					catalogStr = siteID + "_" + catalogID + "_" + catalogNames[i];
					catalogID = Names_IDMap.getString(catalogStr);
				}
			}
		}

		return catalogID;
	}

	public static String getIDsByName(String names) {
		return getIDsByName(Application.getCurrentSiteID(), names);
	}

	public static String getIDsByName(long siteID, String names) {
		return getIDsByName(siteID, names);
	}

	public static String getIDsByName(String siteID, String names) {
		if (StringUtil.isEmpty(names)) {
			return null;
		}
		if (names.startsWith(",")) {
			names = names.substring(1);
		}
		if (names.endsWith(",")) {
			names = names.substring(0, names.length() - 1);
		}
		String[] catalogNames = names.split(",");
		int catalogLenth = catalogNames.length;
		String id = "";
		if (catalogLenth > 0) {
			for (int i = 0; i < catalogLenth; ++i) {
				if (StringUtil.isDigit(catalogNames[i])) {
					id = id + catalogNames[i] + ",";
				} else {
					String catalogStr = siteID + "_" + catalogNames[i];
					String catalogid = Names_IDMap.getString(catalogStr);
					if (StringUtil.isEmpty(catalogid)) {
						catalogid = new QueryBuilder("select id from zccatalog where name='"
								+ catalogNames[i] + "' and siteID=?", siteID).executeString();
						update(catalogid);
						catalogid = Names_IDMap.getString(catalogStr);
					}
					id = id + catalogid + ",";
				}
			}
		}

		if (StringUtil.isNotEmpty(id)) {
			id = id.substring(0, id.length() - 1);
		}

		return id;
	}

	public static String getIDByName(String catalogName) {
		return getIDByName(Application.getCurrentSiteID(), catalogName);
	}

	public static String getIDByName(long siteID, String catalogName) {
		return getIDByName(siteID, catalogName);
	}

	public static String getIDByName(String siteID, String catalogName) {
		String ID = "";
		if (StringUtil.isDigit(catalogName)) {
			ID = catalogName;
		} else {
			ID = Names_IDMap.getString(siteID + "_" + catalogName);
			if (ID == null) {
				String catalogID = new QueryBuilder(
						"select id from zccatalog where name=? and siteID=?", catalogName, siteID)
						.executeString();
				if (StringUtil.isEmpty(catalogID)) {
					return null;
				}
				update(catalogID);
				ID = catalogID;
			}
		}
		return ID;
	}

	public static String getIDByName(long siteID, long parentID, String catalogName) {
		return getIDByName(siteID, parentID, catalogName);
	}

	public static String getIDByName(long siteID, String parentID, String catalogName) {
		return getIDByName(siteID, parentID, catalogName);
	}

	public static String getIDByName(String siteID, long parentID, String catalogName) {
		return getIDByName(siteID, parentID, catalogName);
	}

	public static String getIDByName(String siteID, String parentID, String catalogName) {
		String ID = "";
		if (StringUtil.isDigit(catalogName)) {
			ID = catalogName;
		} else {
			ID = Names_IDMap.getString(siteID + "_" + parentID + catalogName);
			if (ID == null) {
				String catalogID = new QueryBuilder(
						"select id from zccatalog where name=? and siteID=? and parentID='"
								+ parentID + "'", catalogName, siteID).executeString();
				if (StringUtil.isEmpty(catalogID)) {
					return null;
				}
				update(catalogID);
				ID = catalogID;
			}
		}
		return ID;
	}

	public static String getIDByInnerCode(String catalogInnerCode) {
		String ID = InnerCode_IDMap.getString(catalogInnerCode);
		if (ID == null) {
			String catalogID = new QueryBuilder("select id from zccatalog where innercode=?",
					catalogInnerCode).executeString();
			if (StringUtil.isEmpty(catalogID)) {
				return null;
			}
			update(catalogID);
			ID = catalogID;
		}
		return ID;
	}

	public static String getPath(long catalogID) {
		return getPath(String.valueOf(catalogID));
	}

	public static String getPath(String catalogID) {
		String path = ID_PathMap.getString(catalogID);
		if (path == null) {
			update(catalogID);
			path = ID_PathMap.getString(catalogID);
		}
		return path;
	}

	public static String getCatalogIDPath(long catalogID) {
		return getCatalogIDPath(catalogID);
	}

	public static String getCatalogIDPath(String catalogID) {
		String path = ID_IDPathMap.getString(catalogID + "_ID");
		if (path == null) {
			update(catalogID);
			path = ID_IDPathMap.getString(catalogID + "_ID");
		}
		return path;
	}

	public static String getInnerCode(long catalogID) {
		return getInnerCode(catalogID);
	}

	public static String getInnerCode(String catalogID) {
		if (StringUtil.isEmpty(catalogID)) {
			return null;
		}
		String innerCode = ID_InnerCodeMap.getString(catalogID);
		if (innerCode == null) {
			update(catalogID);
			innerCode = ID_InnerCodeMap.getString(catalogID);
		}
		return innerCode;
	}

	public static long getCatalogType(long catalogID) {
		return getCatalogType(catalogID);
	}

	public static long getCatalogType(String catalogID) {
		if (StringUtil.isEmpty(catalogID)) {
			return 0L;
		}
		long catalogType = ID_CatalogTypeMap.getLong(catalogID);
		if (StringUtil.isEmpty(String.valueOf(catalogType))) {
			update(catalogID);
			catalogType = ID_CatalogTypeMap.getLong(catalogID);
		}
		return catalogType;
	}

	public static String getSiteID(long catalogID) {
		return getSiteID(catalogID);
	}

	public static String getSiteID(String catalogID) {
		if (StringUtil.isEmpty(catalogID)) {
			return null;
		}
		String siteID = ID_SiteIDMap.getString(catalogID);
		if (siteID == null) {
			update(catalogID);
			siteID = ID_SiteIDMap.getString(catalogID);
		}
		return siteID;
	}

	public static String getSiteIDByInnerCode(String catalogInnerCode) {
		String siteID = InnerCode_SiteIDMap.getString(catalogInnerCode);
		if (siteID == null) {
			String catalogID = new QueryBuilder("select id from zccatalog where innercode=?",
					catalogInnerCode).executeString();
			update(catalogID);
			siteID = InnerCode_SiteIDMap.getString(catalogInnerCode);
		}
		return siteID;
	}

	public static String getCatalogIndex(long catalogID) {
		String url = getPath(catalogID);
		url = url + "index.shtml";
		return url;
	}

	public static String getAbsolutePath(long catalogID) {
		return getAbsolutePath(catalogID);
	}

	public static String getAbsolutePath(String catalogID) {
		String path = ID_PathMap.getString(catalogID);
		if (path == null) {
			update(catalogID);
			path = ID_PathMap.getString(catalogID);
		}
		return SiteUtil.getPath(getSiteID(catalogID)) + path;
	}

	public static String getAbsoluteIDPath(long catalogID) {
		return getAbsoluteIDPath(catalogID);
	}

	public static String getAbsoluteIDPath(String catalogID) {
		String path = ID_IDPathMap.getString(catalogID);
		if (path == null) {
			update(catalogID);
			path = ID_IDPathMap.getString(catalogID);
		}
		return SiteUtil.getAbsolutePath(getSiteID(catalogID)) + path;
	}

	public static String getAlias(long catalogID) {
		return getAlias(catalogID);
	}

	public static String getAlias(String catalogID) {
		String alias = ID_AliasMap.getString(catalogID);
		if (alias == null) {
			update(catalogID);
			alias = ID_AliasMap.getString(catalogID);
		}
		return alias.toLowerCase();
	}

	public static String getIDByAlias(String alias) {
		String ID = Alias_IDMap.getString(alias);
		if (ID == null) {
			String catalogID = new QueryBuilder("select ID from zccatalog where alias=?", alias)
					.executeString();
			update(catalogID);
			ID = Alias_IDMap.getString(alias);
		}
		return ID;
	}

	public static String getGoodsTypeID(long catalogID) {
		return getGoodsTypeID(catalogID);
	}

	public static String getGoodsTypeID(String catalogID) {
		String goodsTypeID = ID_GoodsTypeIDMap.getString(catalogID);
		if (goodsTypeID == null) {
			update(catalogID);
			goodsTypeID = ID_GoodsTypeIDMap.getString(catalogID);
		}
		return goodsTypeID;
	}

	public static String getParentID(long catalogID) {
		return getParentID(catalogID);
	}

	public static String getParentID(String catalogID) {
		String parentID = ID_ParentMap.getString(catalogID);
		if (parentID == null) {
			update(catalogID);
			parentID = ID_ParentMap.getString(catalogID);
		}
		return parentID;
	}

	public static String getChildCount(long catalogID) {
		return getChildCount(catalogID);
	}

	public static String getChildCount(String catalogID) {
		String childCount = ID_ChildCountMap.getString(catalogID);
		if (childCount == null) {
			update(catalogID);
			childCount = ID_ChildCountMap.getString(catalogID);
		}
		return childCount;
	}

	public static DataTable getCatalogOptions(long type) {
		DataTable dt = new QueryBuilder(
				"select Name,ID,TreeLevel,ParentID from ZCCatalog where SiteID = ? and Type = ? order by ID",
				Application.getCurrentSiteID(), type).executeDataTable();
		PubFun.indentDataTable(dt, 0, 2, 0);
		return dt;
	}

	public static DataTable getList(int type) {
		return getList(type, 1);
	}

	public static DataTable getList(int type, int firstLevel) {
		String sql = "select Name,ID ,Level from ZCCatalog where Type=? and siteID =? order by InnerCode";
		DataTable dt = new QueryBuilder(sql, type, Application.getCurrentSiteID())
				.executeDataTable();
		PubFun.indentDataTable(dt, 0, 2, firstLevel);
		return dt;
	}

	public static String getWorkflow(long catalogID) {
		return getWorkflow(catalogID);
	}

	public static String getWorkflow(String catalogID) {
		String workflow = ID_WorkflowMap.getString(catalogID);
		if (workflow == null) {
			update(catalogID);
			workflow = ID_WorkflowMap.getString(catalogID);
		}
		return workflow;
	}

	public static String getAllowStatus(long catalogID) {
		return getAllowStatus(catalogID);
	}

	public static String getAllowStatus(String catalogID) {
		String allowStatus = ID_AllowStatusMap.getString(catalogID);
		if (allowStatus == null) {
			update(catalogID);
			allowStatus = ID_AllowStatusMap.getString(catalogID);
		}
		return allowStatus;
	}

	public static String getAttachDownFlag(long catalogID) {
		return getAttachDownFlag(catalogID);
	}

	public static String getAttachDownFlag(String catalogID) {
		String attachDownFlag = ID_AttachDownFlagMap.getString(catalogID);
		if (StringUtil.isEmpty(attachDownFlag)) {
			update(catalogID);
			attachDownFlag = ID_AttachDownFlagMap.getString(catalogID);
		}
		return attachDownFlag;
	}

	public static String getArchiveTime(long catalogID) {
		return getArchiveTime(catalogID);
	}

	public static String getArchiveTime(String catalogID) {
		String archiveTime = ID_ArchiveTimeMap.getString(catalogID);
		if (archiveTime == null) {
			update(catalogID);
			archiveTime = ID_ArchiveTimeMap.getString(catalogID);
		}
		return archiveTime;
	}

	public static String getTemplateRule(long catalogID) {
		return getTemplateRule(catalogID);
	}

	public static String getTemplateRule(String catalogID) {
		String templateRule = ID_TemplateRuleMap.getString(catalogID);
		if (templateRule == null) {
			update(catalogID);
			templateRule = ID_TemplateRuleMap.getString(catalogID);
		}
		return templateRule;
	}

	public static String getSingleFlag(long catalogID) {
		return getSingleFlag(catalogID);
	}

	public static String getSingleFlag(String catalogID) {
		String singleFlag = ID_SingleFlagMap.getString(catalogID);
		if (singleFlag == null) {
			update(catalogID);
			singleFlag = ID_SingleFlagMap.getString(catalogID);
		}
		return singleFlag;
	}

	public static String getParentCatalogCode(String innerCode) {
		if (innerCode == null) {
			return "";
		}
		String[] arr = new String[innerCode.length() / 6];
		int i = 0;
		while (innerCode.length() >= 6) {
			arr[(i++)] = innerCode;
			innerCode = innerCode.substring(0, innerCode.length() - 6);
		}
		return "'" + StringUtil.join(arr, "','") + "'";
	}

	public static int getTreeLevel(long catalogID) {
		return getTreeLevel(catalogID);
	}

	public static int getTreeLevel(String catalogID) {
		String treeLevel = ID_TreeLevelMap.getString(catalogID);
		if (StringUtil.isEmpty(treeLevel)) {
			update(catalogID);
			treeLevel = ID_TreeLevelMap.getString(catalogID);
		}
		return Integer.parseInt(treeLevel);
	}

	public static int getLevel(String innerCode) {
		if (innerCode == null) {
			return 0;
		}
		int codeLength = innerCode.length();
		if (codeLength < 1) {
			return 0;
		}
		return (codeLength / 6);
	}

	public static String getLevelStr(long catalogID) {
		return getLevelStr(catalogID);
	}

	public static String getLevelStr(String catalogID) {
		String levelString = "";
		String detailTemplateNameRule = getTemplateRule(catalogID);
		if (StringUtil.isNotEmpty(detailTemplateNameRule)) {
			Pattern p = Pattern.compile("\\$\\{CatalogPath\\}", 2);
			Matcher matcher = p.matcher(detailTemplateNameRule);
			int level = 0;
			if (matcher.find()) {
				level = getTreeLevel(catalogID);
			}

			detailTemplateNameRule = matcher.replaceAll("");
			detailTemplateNameRule = detailTemplateNameRule.replaceAll("///", "/");
			detailTemplateNameRule = detailTemplateNameRule.replaceAll("//", "/");
			if (detailTemplateNameRule.startsWith("/")) {
				detailTemplateNameRule = detailTemplateNameRule.substring(1);
			}

			level += StringUtil.count(detailTemplateNameRule, "/");

			for (int i = 0; i < level; ++i) {
				levelString = levelString + "../";
			}
		}
		return levelString;
	}

	public static String getLevelStr(int level) {
		String levelString = "";
		for (int i = 0; i < level; ++i) {
			levelString = levelString + "../";
		}
		return levelString;
	}

	public static int getPathLevel(String fullPath, int startLevel) {
		int level = 0;
		if (StringUtil.isEmpty(fullPath)) {
			return level;
		}

		Pattern p = Pattern.compile("\\$\\{CatalogPath\\}", 2);
		Matcher matcher = p.matcher(fullPath);
		if (!(matcher.find())) {
			startLevel = 0;
		}
		fullPath = matcher.replaceAll("");

		fullPath = fullPath.replaceAll("///", "/");
		fullPath = fullPath.replaceAll("//", "/");
		if (fullPath.startsWith("/")) {
			fullPath = fullPath.substring(1);
		}

		level = StringUtil.count(fullPath, "/");

		return (level + startLevel);
	}

	private static String updatePath(String catalogID) {
		String path = "";
		String parentID = getParentID(catalogID);
		if (StringUtil.isEmpty(parentID)) {
			return "";
		}
		path = getAlias(new StringBuffer(String.valueOf(catalogID)).toString()).toLowerCase() + "/";
		if (!("0".equals(parentID))) {
			path = updatePath(parentID) + path;
		}

		return path;
	}

	private static String getIDPath(String catalogID) {
		String path = "";
		String parentID = getParentID(catalogID);
		if (StringUtil.isEmpty(parentID)) {
			return "";
		}
		path = catalogID + "/";
		if (!("0".equals(parentID))) {
			path = getIDPath(parentID) + path;
		}

		return path;
	}

	public static DataRow getData(String catalogID) {
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(catalogID);
		if (catalog.fill()) {
			DataRow dr = catalog.toDataRow();
			ColumnUtil.extendCatalogColumnData(dr, catalog.getSiteID(), "");
			return dr;
		}
		return null;
	}

	public static String getHotWordFlag(String catalogID) {
		String HotWordFlag = ID_HotWordFlagMap.getString(catalogID);
		if (HotWordFlag == null) {
			update(catalogID);
			HotWordFlag = ID_HotWordFlagMap.getString(catalogID);
		}
		return HotWordFlag;
	}

	public static void update(long catalogID) {
		update(catalogID);
	}

	public static void update(String catalogID) {
		if (StringUtil.isEmpty(catalogID)) {
			return;
		}
		ID_NameMap.remove(catalogID);
		ID_AliasMap.remove(catalogID);
		ID_ParentMap.remove(catalogID);
		ID_SiteIDMap.remove(catalogID);
		ID_InnerCodeMap.remove(catalogID);
		ID_CatalogTypeMap.remove(catalogID);
		ID_GoodsTypeIDMap.remove(catalogID);
		ID_ChildCountMap.remove(catalogID);
		ID_TreeLevelMap.remove(catalogID);
		InnerCode_IDMap.remove(catalogID);
		InnerCode_NameMap.remove(catalogID);
		InnerCode_SiteIDMap.remove(catalogID);
		ID_TemplateRuleMap.remove(catalogID);
		ID_WorkflowMap.remove(catalogID);
		Alias_IDMap.remove(catalogID);
		Names_IDMap.remove(catalogID);
		ID_PathMap.remove(catalogID);
		ID_IDPathMap.remove(catalogID);
		ID_SingleFlagMap.remove(catalogID);
		ID_AllowStatusMap.remove(catalogID);
		ID_ArchiveTimeMap.remove(catalogID);
		ID_HotWordFlagMap.remove(catalogID);
		ID_AttachDownFlagMap.remove(catalogID);

		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(catalogID);

		if (catalog.fill()) {
			ID_NameMap.put(catalogID, catalog.getName());
			ID_AliasMap.put(catalogID, catalog.getAlias());
			ID_ParentMap.put(catalogID, catalog.getParentID());
			ID_SiteIDMap.put(catalogID, catalog.getSiteID());
			ID_InnerCodeMap.put(catalogID, catalog.getInnerCode());
			ID_CatalogTypeMap.put(catalogID, catalog.getType());
			ID_GoodsTypeIDMap.put(catalogID, catalog.getProp4());
			ID_ChildCountMap.put(catalogID, catalog.getChildCount());
			ID_TreeLevelMap.put(catalogID, catalog.getTreeLevel());
			InnerCode_IDMap.put(catalog.getInnerCode(), catalogID);
			InnerCode_NameMap.put(catalog.getInnerCode(), catalog.getName());
			InnerCode_SiteIDMap.put(catalog.getInnerCode(), catalog.getSiteID());
			Alias_IDMap.put(catalogID, catalog.getAlias());
			ID_PathMap.put(catalogID, updatePath(catalogID));
			ID_IDPathMap.put(catalogID, getIDPath(catalogID));
			ID_SingleFlagMap.put(catalogID, catalog.getSingleFlag());
			ID_TemplateRuleMap.put(catalogID, catalog.getDetailNameRule());
			ID_WorkflowMap.put(catalogID, catalog.getWorkflow());

			if (StringUtil.isEmpty(Names_IDMap.getString(catalogID))) {
				Names_IDMap.put(catalog.getSiteID() + "_" + catalog.getName(), catalog.getID());
			}

			if ((StringUtil.isNotEmpty(ID_NameMap.getString(catalog.getParentID())))
					&& (!("0".equalsIgnoreCase(String.valueOf(catalog.getParentID()))))) {
				Names_IDMap.put(catalog.getSiteID()
						+ "_"
						+ ID_NameMap.getString(new StringBuffer(String.valueOf(catalog
								.getParentID())).toString()) + "_" + catalog.getName(), catalogID);
				Names_IDMap.put(catalog.getSiteID() + "_" + catalog.getParentID() + "_"
						+ catalog.getName(), catalogID);
			}

			ZCCatalogConfigSchema config = new ZCCatalogConfigSchema();
			ZCCatalogConfigSet configSet = config.query(new QueryBuilder(" where CatalogID=?",
					catalog.getID()));

			if ((configSet != null) && (configSet.size() > 0)) {
				config = configSet.get(0);
				ID_AllowStatusMap.put(catalogID, config.getAllowStatus());
				ID_ArchiveTimeMap.put(catalogID, config.getArchiveTime());
				ID_HotWordFlagMap.put(catalogID, config.getHotWordFlag());
				ID_AttachDownFlagMap.put(catalogID, config.getAttachDownFlag());
			}
		}
	}

	public static void clearAll() {
		ID_NameMap.clear();
		ID_AliasMap.clear();
		ID_ParentMap.clear();
		ID_SiteIDMap.clear();
		ID_InnerCodeMap.clear();
		ID_CatalogTypeMap.clear();
		ID_GoodsTypeIDMap.clear();
		ID_ChildCountMap.clear();
		ID_TreeLevelMap.clear();
		InnerCode_NameMap.clear();
		InnerCode_IDMap.clear();
		InnerCode_SiteIDMap.clear();
		Alias_IDMap.clear();
		Names_IDMap.clear();
		ID_TemplateRuleMap.clear();
		ID_WorkflowMap.clear();
		ID_PathMap.clear();
		ID_IDPathMap.clear();
		ID_SingleFlagMap.clear();
		ID_AllowStatusMap.clear();
		ID_ArchiveTimeMap.clear();
		ID_HotWordFlagMap.clear();
		ID_AttachDownFlagMap.clear();
	}

	public static String createCatalogInnerCode(String parentCode) {
		if (StringUtil.isNotEmpty(parentCode)) {
			return NoUtil.getMaxNo("CatalogInnerCode", parentCode, 6);
		}
		return NoUtil.getMaxNo("CatalogInnerCode", 6);
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.pub.CatalogUtil JD-Core Version: 0.5.3
 */