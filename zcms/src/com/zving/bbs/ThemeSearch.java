 package com.zving.bbs;
 
 import com.zving.bbs.admin.ForumUtil;
 import com.zving.framework.Ajax;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataListAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 
 public class ThemeSearch extends Ajax
 {
   public static Mapx init(Mapx params)
   {
     params.put("AddUser", User.getUserName());
     params.put("Priv", ForumUtil.initPriv(params.getString("SiteID")));
     return params; }
 
   public static void dg1DataBind(DataListAction dla) {
     String SiteID = dla.getParam("SiteID");
     ForumPriv priv = new ForumPriv(SiteID);
     if (!(priv.getPriv("AllowSearch"))) {
       return;
     }
     StringBuffer condition = new StringBuffer("");
     String searchAddUser = dla.getParams().getString("SearchAddUser").replace('*', '%');
     String searchSubject = dla.getParams().getString("SearchSubject");
 
     if (StringUtil.isNotEmpty(searchAddUser)) {
       condition.append(" and AddUser like ");
       condition.append(" '" + searchAddUser + "'");
     }
     if (StringUtil.isNotEmpty(searchSubject)) {
       condition.append(" and Subject like ");
       condition.append("'%" + searchSubject + "%' ");
     }
     String sql = "select * from ZCTheme where SiteID=" + SiteID + " and Status='Y' " + 
       condition + " order by ID desc ";
     DataTable dt = new QueryBuilder(sql).executePagedDataTable(dla
       .getPageSize(), dla.getPageIndex());
     dla.bindData(dt); }
 
   public static Mapx initAddDialog(Mapx params) {
     return params;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.ThemeSearch
 * JD-Core Version:    0.5.3
 */