package com.zving.cms.pub;

import com.zving.framework.Config;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.schema.ZCCatalogConfigSchema;
import com.zving.schema.ZCCatalogConfigSet;
import com.zving.schema.ZCSiteSchema;

public class SiteUtil {
	private static int CACHESIZE = 1000;

	private static Mapx ID_AliasMap = new Mapx(CACHESIZE);

	private static Mapx ID_URLMap = new Mapx(CACHESIZE);

	private static Mapx ID_NameMap = new Mapx(CACHESIZE);

	private static Mapx ID_AllowStatusMap = new Mapx(CACHESIZE);

	private static Mapx ID_ArchiveTimeMap = new Mapx(CACHESIZE);

	private static Mapx ID_AttachDownFlagMap = new Mapx(CACHESIZE);

	private static Mapx ID_CommentAuditFlagMap = new Mapx(CACHESIZE);

	public static String getPath(long siteID) {
		return getPath(siteID);
	}

	public static String getPath(String siteID) {
		String path = Config.getContextPath() + Config.getValue("Statical.TargetDir") + "/"
				+ getAlias(siteID) + "/";
		return path.replaceAll("//", "/");
	}

	public static String getAbsolutePath(long siteID) {
		return getAbsolutePath(String.valueOf(siteID));
	}

	public static String getAbsolutePath(String siteID) {
		String path = Config.getContextRealPath() + Config.getValue("Statical.TargetDir") + "/"
				+ getAlias(siteID) + "/";
		return path.replaceAll("//", "/");
	}

	public static String getName(long siteID) {
		return getName(siteID);
	}

	public static String getName(String siteID) {
		if (StringUtil.isEmpty(siteID)) {
			return null;
		}
		String name = ID_NameMap.getString(siteID);
		if (name == null) {
			update(siteID);
			name = ID_NameMap.getString(siteID);
		}
		return name;
	}

	public static String getAlias(long siteID) {
		return getAlias(String.valueOf(siteID));
	}

	public static String getAlias(String siteID) {
		if (StringUtil.isEmpty(siteID)) {
			return null;
		}
		String alias = ID_AliasMap.getString(siteID);
		if (alias == null) {
			update(siteID);
			alias = ID_AliasMap.getString(siteID);
		}
		return alias;
	}

	public static String getCode(long siteID) {
		return getAlias(siteID);
	}

	public static String getCode(String siteID) {
		return getAlias(siteID);
	}

	public static String getURL(long siteID) {
		return getURL(String.valueOf(siteID));
	}

	public static String getURL(String siteID) {
		String url = ID_URLMap.getString(siteID);
		if (url == null) {
			update(siteID);
			url = ID_URLMap.getString(siteID);
		}

		if (!(url.startsWith("http://"))) {
			url = "http://" + url;
		}
		return url;
	}

	public static String getArchiveTime(long siteID) {
		return getArchiveTime(siteID);
	}

	public static String getArchiveTime(String siteID) {
		String archiveTime = ID_ArchiveTimeMap.getString(siteID);
		if (StringUtil.isEmpty(archiveTime)) {
			update(siteID);
			archiveTime = ID_ArchiveTimeMap.getString(siteID);
		}
		return archiveTime;
	}

	public static String getAllowStatus(long siteID) {
		return getAllowStatus(siteID);
	}

	public static String getAllowStatus(String siteID) {
		String archiveTime = ID_AllowStatusMap.getString(siteID);
		if (StringUtil.isEmpty(archiveTime)) {
			update(siteID);
			archiveTime = ID_AllowStatusMap.getString(siteID);
		}
		return archiveTime;
	}

	public static String getAttachDownFlag(long siteID) {
		return getAttachDownFlag(siteID);
	}

	public static String getAttachDownFlag(String siteID) {
		String attachDownFlag = ID_AttachDownFlagMap.getString(siteID);
		if (StringUtil.isEmpty(attachDownFlag)) {
			update(siteID);
			attachDownFlag = ID_AttachDownFlagMap.getString(siteID);
		}
		return attachDownFlag;
	}

	public static void update(long siteID) {
		update(siteID);
	}

	public static boolean getCommentAuditFlag(String siteID) {
		String commentAuditFlag = ID_CommentAuditFlagMap.getString(siteID);
		if (StringUtil.isEmpty(commentAuditFlag)) {
			update(siteID);
			commentAuditFlag = ID_CommentAuditFlagMap.getString(siteID);
		}
		return commentAuditFlag.equalsIgnoreCase("Y");
	}

	public static boolean getCommentAuditFlag(long siteID) {
		return getCommentAuditFlag(siteID);
	}

	public static void update(String siteID) {
		ID_AliasMap.remove(siteID);
		ID_URLMap.remove(siteID);
		ID_NameMap.remove(siteID);
		ID_ArchiveTimeMap.remove(siteID);
		ID_AllowStatusMap.remove(siteID);
		ID_AttachDownFlagMap.remove(siteID);
		ID_CommentAuditFlagMap.remove(siteID);

		ZCSiteSchema site = new ZCSiteSchema();
		site.setID(siteID);
		if (site.fill()) {
			ID_AliasMap.put(siteID, site.getAlias());
			ID_URLMap.put(siteID, site.getURL());
			ID_NameMap.put(siteID, site.getName());
			ID_CommentAuditFlagMap.put(siteID, site.getCommentAuditFlag());

			ZCCatalogConfigSchema config = new ZCCatalogConfigSchema();
			ZCCatalogConfigSet configSet = config.query(new QueryBuilder(
					" where CatalogID is null and SiteID=?", siteID));
			if ((configSet != null) && (configSet.size() > 0)) {
				config = configSet.get(0);
				ID_ArchiveTimeMap.put(siteID, config.getArchiveTime());
				ID_AllowStatusMap.put(siteID, config.getAllowStatus());
				ID_AttachDownFlagMap.put(siteID, config.getAttachDownFlag());
			}
		}
	}

	public static void clearAll() {
		ID_AliasMap.clear();
		ID_URLMap.clear();
		ID_NameMap.clear();
		ID_ArchiveTimeMap.clear();
		ID_AllowStatusMap.clear();
		ID_AttachDownFlagMap.clear();
		ID_CommentAuditFlagMap.clear();
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.pub.SiteUtil JD-Core Version: 0.5.3
 */