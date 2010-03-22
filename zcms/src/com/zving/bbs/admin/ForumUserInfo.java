 package com.zving.bbs.admin;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZCForumGroupSchema;
 import com.zving.schema.ZCForumMemberSchema;
 
 public class ForumUserInfo extends Page
 {
   public static Mapx init(Mapx params)
   {
     String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
     String sql = "select Name,ID from ZCForumGroup where " + sqlSiteID + " and type<>'0'";
     DataTable dt = new QueryBuilder(sql).executeDataTable();
     params.put("UserGroup", HtmlUtil.dataTableToOptions(dt));
     return params;
   }
 
   public static void getList(DataGridAction dga)
   {
     String UserGroupID = dga.getParam("UserGroup");
     String LowerScore = dga.getParam("LowerScore");
     String UpperScore = dga.getParam("UpperScore");
     String LowerThemeCount = dga.getParam("LowerThemeCount");
     String UpperThemeCount = dga.getParam("UpperThemeCount");
     String UserName = dga.getParam("ForumUserName");
 
     StringBuffer sb = new StringBuffer();
     if (StringUtil.isNotEmpty(UserName)) {
       sb.append(" and UserName like '%" + UserName + "%'");
     }
     if (StringUtil.isNotEmpty(LowerScore)) {
       sb.append(" and ForumScore>=" + LowerScore);
     }
     if (StringUtil.isNotEmpty(UpperScore)) {
       sb.append(" and ForumScore <=" + UpperScore);
     }
     if (StringUtil.isNotEmpty(LowerThemeCount)) {
       sb.append(" and ThemeCount>=" + LowerThemeCount);
     }
     if (StringUtil.isNotEmpty(UpperThemeCount)) {
       sb.append(" and ThemeCount<=" + UpperThemeCount);
     }
     if ((StringUtil.isNotEmpty(UserGroupID)) && (!(UserGroupID.equals("0")))) {
       sb.append(" and UserGroupID = '" + UserGroupID + "'");
     }
     String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
     String sql = "select a.UserName,b.Name,c.Name AdminName,a.ThemeCount,a.ForumScore from ZCForumMember a join ZCForumGroup b on(b.ID=a.UserGroupID) left join ZCForumGroup c on(a.AdminID=c.ID) where a." + 
       sqlSiteID + " and b." + sqlSiteID + 
       sb.toString();
     DataTable dt = new QueryBuilder(sql).executeDataTable();
 
     dga.bindData(dt);
   }
 
   public static Mapx initEditDialog(Mapx params)
   {
     String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
     ZCForumMemberSchema member = new ZCForumMemberSchema();
     ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
     String userName = params.getString("UserName");
     member.setUserName(userName);
     member.fill();
     String sql = "select m.ThemeCount, m.ForumScore, f1.ID AID, f1.Name, f2.SystemName, f1.Type, a.* from ZCForumMember m, ZCForumGroup f1, ZCForumGroup f2, ZCAdminGroup a where m." + 
       sqlSiteID + " and  f1." + sqlSiteID + " and f2." + sqlSiteID + " and a." + sqlSiteID + 
       " and f1.ID=a.GroupID and f1.RadminID=f2.ID and m.UserName='" + userName + "'";
 
     String sqlUserGroup = "select Name,ID from ZCForumGroup where " + sqlSiteID + " and (Type='2' and SystemName <> '游客' and SystemName<>'系统管理员') or Type='3' ";
     String sqlAdminGroup = "select Name,f.ID from ZCForumGroup f join ZCAdminGroup a on (f.ID=a.GroupID) and f." + sqlSiteID + " and a." + sqlSiteID + " and Type='2' and Name <> '系统管理员'";
     String sqlSpecial = "select ID from ZCForumGroup where " + sqlSiteID + " and Type='3'";
 
     DataTable dtUserGroup = new QueryBuilder(sqlUserGroup).executeDataTable();
     DataTable dtAdminGroup = new QueryBuilder(sqlAdminGroup).executeDataTable();
     DataTable dtSpecial = new QueryBuilder(sqlSpecial).executeDataTable();
     String userGroupValue = Long.toString(member.getUserGroupID());
     String adminGroupValue = Long.toString(member.getAdminID());
     String[] arrTemp = new String[dtSpecial.getRowCount()];
     for (int i = 0; i < dtSpecial.getRowCount(); ++i) {
       userGroup.setID(dtSpecial.getString(i, "ID"));
       userGroup.fill();
 
       if (userGroup.getRadminID() == 0L) {
         arrTemp[i] = dtSpecial.getString(i, "ID");
       }
     }
 
     params.put("IDs", StringUtil.join(arrTemp));
     params.put("UserGroup", HtmlUtil.dataTableToOptions(dtUserGroup, userGroupValue));
     params.put("AdminGroup", HtmlUtil.dataTableToOptions(dtAdminGroup, adminGroupValue));
     DataTable dt1 = new QueryBuilder(sql).executeDataTable();
     DataTable dt2 = new QueryBuilder("select ID from ZCForumGroup where " + sqlSiteID + " and SystemName='版主'").executeDataTable();
     params.put("ID", dt1.getString(0, "ID"));
     params.put("ID", dt2.getString(0, "ID"));
     params.put("ThemeCount", dt1.getString(0, "ThemeCount"));
     params.put("ForumScore", dt1.getString(0, "ForumScore"));
 
     return params;
   }
 
   public void editUserGroup() {
     if ($V("UserName").equals("admin")) {
       this.Response.setLogInfo(0, "系统管理员不能更改！");
       return;
     }
     String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
     Transaction trans = new Transaction();
     String userGroupID = $V("UserGroup");
     ZCForumMemberSchema member = new ZCForumMemberSchema();
     ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
     member.setUserName($V("UserName"));
     member.fill();
     member.setForumScore($V("ForumScore"));
     member.setThemeCount($V("ThemeCount"));
     userGroup.setID(userGroupID);
     userGroup.fill();
     String sql;
     int count;
     if (!(userGroupID.equals("0"))) {
       member.setUserGroupID(userGroupID);
       if (userGroup.getType().equals("2")) {
         member.setAdminID(userGroupID);
       }
       else if (userGroup.getRadminID() != 0L) {
         member.setAdminID(userGroup.getRadminID());
       } else {
         if ($V("AdminGroup").equals("0")) {
           sql = "select count(*) from ZCForum where " + sqlSiteID + " and ForumAdmin like '%" + member.getUserName() + ",%'";
           count = new QueryBuilder(sql).executeInt();
           if (count > 0) {
             this.Response.setLogInfo(0, "请先在相应板块下删除该用户的版主身份");
             return;
           }
         }
         member.setAdminID($V("AdminGroup"));
       }
     }
     else
     {
       member.setUserGroupID(0L);
       member.setAdminID(0L);
       sql = "select count(*) from ZCForum where " + sqlSiteID + " and ForumAdmin like '%" + member.getUserName() + ",%'";
       count = new QueryBuilder(sql).executeInt();
       if (count > 0) {
         this.Response.setLogInfo(0, "请先在相应板块下删除该用户的版主身份");
         return;
       }
       ForumUtil.userGroupChange(member);
     }
 
     trans.add(member, 2);
     if (trans.commit())
       this.Response.setLogInfo(1, "操作成功");
     else
       this.Response.setLogInfo(0, "操作失败");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.admin.ForumUserInfo
 * JD-Core Version:    0.5.3
 */