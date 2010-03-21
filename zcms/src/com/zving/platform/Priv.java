 package com.zving.platform;
 
 import com.zving.cms.pub.CatalogUtil;
 import com.zving.cms.pub.PubFun;
 import com.zving.framework.Config;
 import com.zving.framework.User;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.util.HashMap;
 import java.util.Hashtable;
 import java.util.List;
 import java.util.Map;
 
 public class Priv
 {
   public static final String MENU = "menu";
   public static final String MENU_BROWSE = "menu_browse";
   public static final Mapx MENU_MAP = new Mapx();
   public static final String SITE = "site";
   public static final String SITE_BROWSE = "site_browse";
   public static final String SITE_MANAGE = "site_manage";
   public static final Mapx SITE_MAP;
   public static final String ARTICLE = "article";
   public static final String ARTICLE_BROWSE = "article_browse";
   public static final String ARTICLE_MANAGE = "article_manage";
   public static final String ARTICLE_MODIFY = "article_modify";
   public static final Mapx ARTICLE_MAP;
   public static final String IMAGE = "image";
   public static final String IMAGE_BROWSE = "image_browse";
   public static final String IMAGE_MANAGE = "image_manage";
   public static final String IMAGE_MODIFY = "image_modify";
   public static final Mapx IMAGE_MAP;
   public static final String VIDEO = "video";
   public static final String VIDEO_BROWSE = "video_browse";
   public static final String VIDEO_MANAGE = "video_manage";
   public static final String VIDEO_MODIFY = "video_modify";
   public static final Mapx VIDEO_MAP;
   public static final String AUDIO = "audio";
   public static final String AUDIO_BROWSE = "audio_browse";
   public static final String AUDIO_MANAGE = "audio_manage";
   public static final String AUDIO_MODIFY = "audio_modify";
   public static final Mapx AUDIO_MAP;
   public static final String ATTACH = "attach";
   public static final String ATTACH_BROWSE = "attach_browse";
   public static final String ATTACH_MANAGE = "attach_manage";
   public static final String ATTACH_MODIFY = "attach_modify";
   public static final Mapx ATTACH_MAP;
   public static final Mapx PRIV_MAP;
   public static final String OWNERTYPE_USER = "U";
   private static Map UserPrivMap;
 
   static
   {
     MENU_MAP.put("menu_browse", "菜单浏览");
 
     SITE_MAP = new Mapx();
 
     SITE_MAP.put("site_browse", "站点浏览");
     SITE_MAP.put("site_manage", "站点管理");
 
     ARTICLE_MAP = new Mapx();
 
     ARTICLE_MAP.put("article_browse", "文章栏目浏览");
     ARTICLE_MAP.put("article_manage", "文章栏目管理");
     ARTICLE_MAP.put("article_modify", "文章管理");
 
     IMAGE_MAP = new Mapx();
 
     IMAGE_MAP.put("image_browse", "图片栏目浏览");
     IMAGE_MAP.put("image_manage", "图片栏目管理");
     IMAGE_MAP.put("image_modify", "图片管理");
 
     VIDEO_MAP = new Mapx();
 
     VIDEO_MAP.put("video_browse", "视频栏目浏览");
     VIDEO_MAP.put("video_manage", "视频栏目管理");
     VIDEO_MAP.put("video_modify", "视频管理");
 
     AUDIO_MAP = new Mapx();
 
     AUDIO_MAP.put("audio_browse", "音频栏目浏览");
     AUDIO_MAP.put("audio_manage", "音频栏目管理");
     AUDIO_MAP.put("audio_modify", "音频管理");
 
     ATTACH_MAP = new Mapx();
 
     ATTACH_MAP.put("attach_browse", "附件栏目浏览");
     ATTACH_MAP.put("attach_manage", "附件栏目管理");
     ATTACH_MAP.put("attach_modify", "附件管理");
 
     PRIV_MAP = new Mapx();
 
     PRIV_MAP.put("menu", MENU_MAP);
     PRIV_MAP.put("site", SITE_MAP);
     PRIV_MAP.put("article", ARTICLE_MAP);
     PRIV_MAP.put("image", IMAGE_MAP);
     PRIV_MAP.put("video", VIDEO_MAP);
     PRIV_MAP.put("audio", AUDIO_MAP);
     PRIV_MAP.put("attach", ATTACH_MAP);
 
     UserPrivMap = new Hashtable(); }
 
   public static void updateAllPriv(String UserName) {
     Object obj = new QueryBuilder("select UserName from ZDUser where UserName=?", UserName).executeOneValue();
     if (obj == null) {
       UserPrivMap.remove(UserName);
       return;
     }
     Object[] ks = PRIV_MAP.keyArray();
     for (int i = 0; i < PRIV_MAP.size(); ++i)
       updatePriv(UserName, (String)ks[i]);
   }
 
   public static void updatePriv(String UserName, String PrivType)
   {
     String sql = "select ID,Code,Value from ZDPrivilege where OwnerType=? and Owner=? and PrivType=?";
     QueryBuilder qb = new QueryBuilder(sql);
     qb.add("U");
     qb.add(UserName);
     qb.add(PrivType);
     DataTable dt = qb.executeDataTable();
     Map PrivTypeMap = getPrivTypeMap(UserName, PrivType);
     RolePriv.getMapFromDataTable(PrivTypeMap, dt);
   }
 
   private static Map getPrivTypeMap(String UserName, String PrivType) {
     Map UserNamePrivMap = (Map)UserPrivMap.get(UserName);
     if (UserNamePrivMap == null) {
       UserNamePrivMap = new Hashtable();
       UserPrivMap.put(UserName, UserNamePrivMap);
       updateAllPriv(UserName);
     }
     Map PrivTypeMap = (Map)UserNamePrivMap.get(PrivType);
     if (PrivTypeMap == null) {
       PrivTypeMap = new HashMap();
       UserNamePrivMap.put(PrivType, PrivTypeMap);
     }
     return PrivTypeMap;
   }
 
   public static boolean getPriv(String PrivType, String ID, String Code) {
     return getPriv(User.getUserName(), PrivType, ID, Code);
   }
 
   public static boolean getPriv(String UserName, String PrivType, String ID, String Code) {
     if ("admin".equalsIgnoreCase(UserName)) {
       return true;
     }
     String value = getUserPriv(UserName, PrivType, ID, Code);
     if ("1".equals(value))
       return true;
     if ("-1".equals(value)) {
       return false;
     }
     List roleCodeList = PubFun.getRoleCodesByUserName(UserName);
     if ((roleCodeList != null) && (roleCodeList.size() != 0)) {
       return RolePriv.getRolePriv((String[])roleCodeList.toArray(new String[roleCodeList.size()]), PrivType, ID, Code);
     }
     return false;
   }
 
   private static String getUserPriv(String UserName, String PrivType, String ID, String Code)
   {
     if ("menu".equals(PrivType)) {
       map = getPrivTypeMap(UserName, PrivType);
       map = (Map)map.get(ID);
       if (map != null) {
         return ((String)map.get(Code));
       }
       return null;
     }
     if ("site".equals(PrivType)) {
       map = getPrivTypeMap(UserName, PrivType);
       map = (Map)map.get(ID);
       if (map != null) {
         return ((String)map.get(Code));
       }
       return null;
     }
 
     Map map = getPrivTypeMap(UserName, PrivType);
     map = (Map)map.get(ID);
     if (map != null) {
       return ((String)map.get(Code));
     }
     if (ID.length() > 7)
     {
       return getUserPriv(UserName, PrivType, ID.substring(0, ID.length() - 4), Code);
     }
 
     return getUserPriv(UserName, "site", CatalogUtil.getSiteIDByInnerCode(ID), Code);
   }
 
   public static boolean isValidURL(String URL)
   {
     if (StringUtil.isNotEmpty(URL)) {
       URL = URL.replaceAll("/+", "/");
       if (URL.startsWith("/")) {
         URL = URL.substring(1);
       }
     }
     if (!(Config.isDatabaseConfiged)) {
       return true;
     }
     String menuID = Menu.MenuCacheMap.getString(URL);
 
     return ((!(StringUtil.isNotEmpty(menuID))) || (getPriv(User.getUserName(), "menu", Application.getCurrentSiteID() + "-" + menuID, "menu_browse")));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.Priv
 * JD-Core Version:    0.5.3
 */