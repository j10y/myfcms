 package com.zving.bbs.admin;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.schema.ZCAdminGroupSchema;
 import com.zving.schema.ZCForumGroupSchema;
 
 public class ForumManageGroupOption extends Page
 {
   public static Mapx init(Mapx params)
   {
     ZCAdminGroupSchema manageGroup = new ZCAdminGroupSchema();
     String ID = params.getString("ID");
     manageGroup.setGroupID(ID);
     manageGroup.fill();
     ZCForumGroupSchema group = new ZCForumGroupSchema();
     group.setID(manageGroup.getGroupID());
     group.fill();
     params = manageGroup.toMapx();
     params.put("Name", group.getName());
     Mapx map = new Mapx();
     map.put("Y", "允许");
     map.put("N", "不允许");
     params.put("AllowEditUser", HtmlUtil.mapxToRadios("AllowEditUser", map, manageGroup.getAllowEditUser()));
     params.put("AllowForbidUser", HtmlUtil.mapxToRadios("AllowForbidUser", map, manageGroup.getAllowForbidUser()));
     params.put("AllowEditForum", HtmlUtil.mapxToRadios("AllowEditForum", map, manageGroup.getAllowEditForum()));
     params.put("AllowVerfyPost", HtmlUtil.mapxToRadios("AllowVerfyPost", map, manageGroup.getAllowVerfyPost()));
     params.put("AllowEdit", HtmlUtil.mapxToRadios("AllowEdit", map, manageGroup.getAllowEdit()));
     params.put("AllowDel", HtmlUtil.mapxToRadios("AllowDel", map, manageGroup.getAllowDel()));
     params.put("Hide", HtmlUtil.mapxToRadios("Hide", map, manageGroup.getHide()));
 
     return params; }
 
   public static Mapx initThemeOption(Mapx params) {
     ZCAdminGroupSchema manageGroup = new ZCAdminGroupSchema();
     manageGroup.setGroupID(params.getString("ID"));
     manageGroup.fill();
     params = manageGroup.toMapx();
     Mapx map = new Mapx();
     map.put("Y", "允许");
     map.put("N", "不允许");
     params.put("RemoveTheme", HtmlUtil.mapxToRadios("RemoveTheme", map, manageGroup.getRemoveTheme()));
     params.put("MoveTheme", HtmlUtil.mapxToRadios("MoveTheme", map, manageGroup.getMoveTheme()));
     params.put("TopTheme", HtmlUtil.mapxToRadios("TopTheme", map, manageGroup.getTopTheme()));
     params.put("BrightTheme", HtmlUtil.mapxToRadios("BrightTheme", map, manageGroup.getBrightTheme()));
     params.put("UpOrDownTheme", HtmlUtil.mapxToRadios("UpOrDownTheme", map, manageGroup.getUpOrDownTheme()));
     params.put("BestTheme", HtmlUtil.mapxToRadios("BestTheme", map, manageGroup.getBestTheme()));
     return params;
   }
 
   public void editSave() {
     Transaction trans = new Transaction();
     ZCAdminGroupSchema manageGroup = new ZCAdminGroupSchema();
     manageGroup.setGroupID($V("GroupID"));
     manageGroup.fill();
     manageGroup.setValue(this.Request);
     manageGroup.setAllowEditUser($V("AllowEditUser"));
 
     trans.add(manageGroup, 2);
     if (trans.commit())
       this.Response.setLogInfo(1, "设置成功!");
     else
       this.Response.setLogInfo(0, "操作失败！");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.admin.ForumManageGroupOption
 * JD-Core Version:    0.5.3
 */