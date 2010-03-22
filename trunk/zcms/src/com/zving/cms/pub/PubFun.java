package com.zving.cms.pub;

import com.zving.cms.template.HtmlNameParser;
import com.zving.cms.template.HtmlNameRule;
import com.zving.framework.Config;
import com.zving.framework.User;
import com.zving.framework.data.DataRow;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.Filter;
import com.zving.framework.utility.HtmlUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import com.zving.platform.Application;
import com.zving.schema.ZCArticleSchema;
import com.zving.schema.ZCCatalogSchema;
import com.zving.schema.ZCImagePlayerSchema;
import com.zving.schema.ZCImagePlayerSet;
import com.zving.schema.ZCSiteSchema;
import com.zving.schema.ZDDistrictSchema;
import com.zving.schema.ZDUserSchema;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class PubFun {
	public static final String INDENT = "　";
	public static final String SEPARATE = "|";
	public static Mapx userRoleMap = new Mapx(1000);

	public static DataTable District = null;

	public static String getBranchOptions() {
		return getBranchOptions(null);
	}

	public static String getBranchOptions(Object checkedValue) {
		DataTable dt = new QueryBuilder(
				"select Name,BranchInnerCode,TreeLevel from ZDBranch where BranchInnerCode like ? order by OrderFlag",
				User.getBranchInnerCode() + "%").executeDataTable();
		indentDataTable(dt);
		return HtmlUtil.dataTableToOptions(dt, checkedValue);
	}

	public static void initUserRoleMap(String userName) {
		userRoleMap.remove(userName);
		DataTable dt = new QueryBuilder("select RoleCode from zduserrole where UserName = ?",
				userName).executeDataTable();
		List list = new ArrayList();
		for (int i = 0; i < dt.getRowCount(); ++i) {
			list.add(dt.getString(i, 0));
		}
		userRoleMap.put(userName, list);
	}

	public static List getRoleCodesByUserName(String userName) {
		List list = (List) userRoleMap.get(userName);
		if (list == null) {
			initUserRoleMap(userName);
			list = (List) userRoleMap.get(userName);
		}
		return list;
	}

	public static void indentDataTable(DataTable dt) {
		indentDataTable(dt, 0, 2, 1);
	}

	public static void indentDataTable(DataTable dt, int n, int m, int firstLevel) {
		for (int i = 0; i < dt.getRowCount(); ++i) {
			int level = Integer.parseInt(dt.getString(i, m));
			StringBuffer sb = new StringBuffer();
			for (int j = firstLevel; j < level; ++j) {
				sb.append("　");
			}
			dt.set(i, n, sb.toString() + dt.getString(i, n));
		}
	}

	public static String getCurrentPage(long catalogID, int level, String separator, String target) {
		String url = "";
		String parentID = CatalogUtil.getParentID(catalogID);
		String catalogPath = CatalogUtil.getPath(catalogID);
		if (StringUtil.isEmpty(parentID)) {
			return "";
		}
		String levelString = (level == 0) ? "./" : "";
		for (int i = 0; i < level; ++i) {
			levelString = levelString + "../";
		}

		String linkText = CatalogUtil.getName(catalogID);
		if (!("0".equals(parentID))) {
			url = getCurrentPage(Long.parseLong(parentID), level, separator, target);
			url = url + " " + separator + " <a href='" + levelString + catalogPath + "' target='"
					+ target + "'>" + linkText + "</a>";
		} else {
			url = url + " " + separator + " <a href='" + levelString + catalogPath + "' target='"
					+ target + "'>" + linkText + "</a>";
			linkText = "首页";
			url = "<a href='" + levelString + "' target='" + target + "'>" + linkText + "</a>"
					+ url;
		}

		return url;
	}

	public static String getArticleURL(long articleID) {
		return getArticleURL(String.valueOf(articleID));
	}

	public static String getArticleURL(ZCArticleSchema article) {
		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(article.getCatalogID());
		catalog.fill();

		ZCSiteSchema site = new ZCSiteSchema();
		site.setID(article.getSiteID());
		site.fill();

		return getArticleURL(site, catalog, article);
	}

	public static String getArticleURL(String articleID) {
		ZCArticleSchema article = new ZCArticleSchema();
		article.setID(articleID);
		article.fill();

		ZCCatalogSchema catalog = new ZCCatalogSchema();
		catalog.setID(article.getCatalogID());
		catalog.fill();

		ZCSiteSchema site = new ZCSiteSchema();
		site.setID(article.getSiteID());
		site.fill();

		return getArticleURL(site, catalog, article);
	}

	public static String getArticleURL(ZCSiteSchema site, ZCCatalogSchema catalog,
			ZCArticleSchema article) {
		HtmlNameParser nameParser = new HtmlNameParser(site.toDataRow(), catalog.toDataRow(),
				article.toDataRow(), catalog.getDetailNameRule());
		HtmlNameRule h = nameParser.getNameRule();
		String url = h.getFullPath();
		return url;
	}

	public static String getGoodsURL(String ID) {
		DataTable dt = new QueryBuilder("select catalogID from zcgoods where ID=?", ID)
				.executeDataTable();
		long catalogID = Long.parseLong(dt.get(0, "catalogID").toString());
		String url = CatalogUtil.getPath(catalogID) + ID + ".shtml";
		return url;
	}

	public static String getImagePath(String imageID, String imageIndex) {
		String imagePath = null;
		if (StringUtil.isEmpty(imageIndex)) {
			imageIndex = "src";
		}
		if (StringUtil.isEmpty(imageID)) {
			imageID = "0";
		}
		String imageSql = "select id,path,catalogID,filename,SrcFileName from zcimage where id=?";
		DataTable dtImage = new QueryBuilder(imageSql, imageID).executeDataTable();
		if ((dtImage != null) && (dtImage.getRowCount() > 0)) {
			if ("src".equals(imageIndex))
				imagePath = dtImage.getString(0, "path") + dtImage.getString(0, "SrcFileName");
			else {
				imagePath = dtImage.getString(0, "path") + imageIndex + "_"
						+ dtImage.getString(0, "fileName");
			}
		}
		return imagePath;
	}

	public static String getImagePath(long imageID, String imageIndex) {
		return getImagePath(String.valueOf(imageID), imageIndex);
	}

	public static String getImagePath(long imageID) {
		return getImagePath(imageID, null);
	}

	public static String getUserRealName(String userName) {
		ZDUserSchema user = new ZDUserSchema();
		user.setUserName(userName);
		if (!(user.fill())) {
			return "";
		}
		return user.getRealName();
	}

	public static String getImagePath(String imageID) {
		return getImagePath(imageID, null);
	}

	public static String getImagePlayer(String ImagePlayCode) {
		ZCImagePlayerSchema imagePlayer = new ZCImagePlayerSchema();
		imagePlayer.setCode(ImagePlayCode);
		ZCImagePlayerSet set = imagePlayer.query();
		if (set.size() > 0)
			imagePlayer = set.get(0);
		else {
			System.out.println("没有" + ImagePlayCode + "对应的图片播放器，请检查" + ImagePlayCode + "是否正确。");
		}
		return getImagePlayer(imagePlayer);
	}

	public static String getImagePlayer(ZCImagePlayerSchema imagePlayer) {
		QueryBuilder qb = new QueryBuilder(
				"select b.* from ZCImageRela a,zcimage b where a.id = b.id  and a.RelaID=? and a.RelaType=? order by a.orderflag desc, a.addtime desc",
				imagePlayer.getID(), "ImagePlayerImage");
		DataTable dt = qb.executeDataTable();
		String id = "flashcontent";
		String Alias = Config.getValue("UploadDir") + "/"
				+ SiteUtil.getAlias(Application.getCurrentSiteID()) + "/";
		Alias = Alias.replaceAll("///", "/");
		Alias = Alias.replaceAll("//", "/");
		StringBuffer pics = new StringBuffer();
		StringBuffer links = new StringBuffer();
		StringBuffer texts = new StringBuffer();
		pics.append("'");
		links.append("'");
		texts.append("'");
		for (int i = 0; i < dt.getRowCount(); ++i) {
			if (i != 0) {
				pics.append("|");
				links.append("|");
				texts.append("|");
			}
			pics.append("../" + Alias + dt.getString(i, "path") + "1_"
					+ dt.getString(i, "FileName"));
			if (StringUtil.isNotEmpty(Alias + dt.getString(i, "LinkURL")))
				links.append(dt.getString(i, "LinkURL"));
			if (StringUtil.isNotEmpty(dt.getString(i, "LinkText")))
				texts.append(dt.getString(i, "LinkText"));
		}
		pics.append("'");
		links.append("'");
		texts.append("'");

		StringBuffer sb = new StringBuffer();
		sb.append("<script type=\"text/javascript\" src=\"" + Config.getContextPath()
				+ "Tools/Swfobject.js\"></script>\n");
		sb.append("<div id='" + id + "'>\n");
		sb.append("  This text is replaced by the Flash movie.\n");
		sb.append("</div>\n");
		sb.append("<script type='text/javascript'>\n");
		sb.append("var so=new SWFObject('" + Config.getContextPath()
				+ "Tools/ImagePlayer.swf','ImagePlayer1'," + imagePlayer.getWidth() + ","
				+ (imagePlayer.getHeight() + 22L) + ",'7','#FFFFFF','high');\n");
		sb.append("so.addVariable('wmode','transparent');\n");
		sb.append("so.addVariable('pics'," + pics.toString() + ");\n");
		sb.append("so.addVariable('links'," + links.toString() + ");\n");
		sb.append("so.addVariable('texts'," + texts.toString() + ");\n");
		sb.append("so.addVariable('borderwidth'," + imagePlayer.getWidth() + ");\n");
		sb.append("so.addVariable('borderheight'," + imagePlayer.getHeight() + ");\n");
		if ("Y".equals(imagePlayer.getProp1())) {
			sb.append("so.addVariable('textheight',22);\n");
			sb.append("so.addVariable('show_text','1');\n");
		} else {
			sb.append("so.addVariable('show_text','0');\n");
		}

		sb.append("so.addVariable('txtcolor','FFFF00');\n");
		sb.append("so.addVariable('overtxtcolor','FFFF00');\n");
		sb.append("so.addVariable('overtxtcolor','FFFF00');\n");

		sb.append("so.write('" + id + "');\n");
		sb.append("</script>");

		return sb.toString();
	}

	public static void initDistrict() {
		District = new QueryBuilder("select Name,Code,TreeLevel,Type from zddistrict")
				.executeDataTable();
	}

	public static DataTable getProvince() {
		if (District == null) {
			initDistrict();
		}
		return District.filter(new Filter() {
			public boolean filter(Object o) {
				DataRow dr = (DataRow) o;

				return ("1".equals(dr.get("TreeLevel")));
			}
		});
	}

	public static DataTable getCity(String Province) {
		if (District == null) {
			initDistrict();
		}
		if (StringUtil.isNotEmpty(Province)) {
			ZDDistrictSchema district = new ZDDistrictSchema();
			district.setCode(Province);
			district.fill();
			if ("0".equals(district.getType())) {
				return District.filter(new Filter(Province) {
					public boolean filter(Object o) {
						DataRow dr = (DataRow) o;

						return (((String) this.Param).equals(dr.getString("Code")));
					}

				});
			}

			return District.filter(new Filter(Province) {
				public boolean filter(Object o) {
					DataRow dr = (DataRow) o;

					return (("2".equals(dr.getString("TreeLevel"))) && (dr.getString("Code")
							.substring(0, 2).equals(((String) this.Param).substring(0, 2))));
				}

			});
		}

		return new DataTable();
	}

	public static DataTable getDistrict(String City) {
		if (District == null) {
			initDistrict();
		}
		if (StringUtil.isNotEmpty(City)) {
			ZDDistrictSchema district = new ZDDistrictSchema();
			district.setCode(City);
			district.fill();
			if ("0".equalsIgnoreCase(district.getType())) {
				return District.filter(new Filter(City) {
					public boolean filter(Object o) {
						DataRow dr = (DataRow) o;

						return (("3".equals(dr.get("TreeLevel").toString())) && (dr.get("Code")
								.toString().substring(0, 3).equals(((String) this.Param).substring(
								0, 3))));
					}

				});
			}

			return District.filter(new Filter(City) {
				public boolean filter(Object o) {
					DataRow dr = (DataRow) o;

					return (("3".equals(dr.getString("TreeLevel"))) && (dr.get("Code").toString()
							.substring(0, 4).equals(((String) this.Param).substring(0, 4))));
				}

			});
		}

		return new DataTable();
	}

	public static void main(String[] args) {
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.cms.pub.PubFun JD-Core Version: 0.5.3
 */