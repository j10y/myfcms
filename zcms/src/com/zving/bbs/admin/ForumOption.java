 package com.zving.bbs.admin;
 
 import com.zving.framework.Page;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.schema.ZCForumSchema;
 
 public class ForumOption extends Page
 {
   public static Mapx basicInit(Mapx params)
   {
     String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
     String ID = params.getString("ID");
     ZCForumSchema forum = new ZCForumSchema();
     forum.setID(ID);
     forum.fill();
     params = forum.toMapx();
     if (forum.getParentID() != 0L) {
       DataTable dt = new QueryBuilder("select Name,ID from ZCForum where " + sqlSiteID + " and ParentID=0 order by orderflag").executeDataTable();
       params.put("ParentForum", HtmlUtil.dataTableToOptions(dt, forum.getParentID(), false));
     }
     Mapx map1 = new Mapx();
     map1.put("Y", "显示");
     map1.put("N", "不显示");
     params.put("Visible", HtmlUtil.mapxToRadios("Visible", map1, forum.getVisible()));
     Mapx map2 = new Mapx();
     map2.put("Y", "锁定");
     map2.put("N", "开启");
     params.put("Locked", HtmlUtil.mapxToRadios("Locked", map2, forum.getLocked()));
     return params;
   }
 
   public static Mapx postOptionInit(Mapx params)
   {
     String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
     String ID = params.getString("ID");
     ZCForumSchema forum = new ZCForumSchema();
     forum.setID(ID);
     forum.fill();
     params = forum.toMapx();
     DataTable dt = new QueryBuilder("select Name,ID from ZCForum where " + sqlSiteID + " and ParentID=0 order by orderflag").executeDataTable();
     params.put("ParentForum", HtmlUtil.dataTableToOptions(dt, forum.getParentID()));
     Mapx map = new Mapx();
     map.put("N", "否");
     map.put("T", "审核新主题");
     map.put("Y", "审核新主题和新回复");
     params.put("Verify", HtmlUtil.mapxToRadios("Verify", map, forum.getVerify()));
     map.clear();
     map.put("Y", "是");
     map.put("N", "否");
     params.put("EditPost", HtmlUtil.mapxToRadios("EditPost", map, forum.getEditPost()));
     params.put("AllowTheme", HtmlUtil.mapxToRadios("AllowTheme", map, forum.getAllowTheme()));
     params.put("Recycle", HtmlUtil.mapxToRadios("Recycle", map, forum.getEditPost()));
     params.put("AllowHTML", HtmlUtil.mapxToRadios("AllowHTML", map, forum.getEditPost()));
     params.put("AllowFace", HtmlUtil.mapxToRadios("AllowFace", map, forum.getEditPost()));
     params.put("ReplyPost", HtmlUtil.mapxToRadios("ReplyPost", map, forum.getReplyPost()));
 
     return params;
   }
 
   public static Mapx initOption(Mapx params) {
     return params;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.admin.ForumOption
 * JD-Core Version:    0.5.3
 */