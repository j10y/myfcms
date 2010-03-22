 package com.zving.platform;
 
 import com.zving.cms.pub.SiteUtil;
 import com.zving.framework.Config;
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.HtmlScript;
 import com.zving.framework.data.DataCollection;
 import com.zving.framework.data.DataRow;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Filter;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.io.PrintStream;
 
 public class Application extends Page
 {
   public static Mapx init(Mapx params)
   {
     DataTable dt = null;
     dt = new QueryBuilder("select name,id from zcsite order by BranchInnerCode ,orderflag ,id").executeDataTable();
     dt = dt.filter(new Filter() {
       public boolean filter(Object obj) {
         DataRow dr = (DataRow)obj;
         return Priv.getPriv(User.getUserName(), "site", dr.getString("ID"), "site_browse");
       }
     });
     params.put("Sites", HtmlUtil.dataTableToOptions(dt, getCurrentSiteID()));
 
     dt = new QueryBuilder("select name,id from zdmenu where  visiable='Y' and parentID=0 order by OrderFlag")
       .executeDataTable();
     dt = dt.filter(new Filter() {
       public boolean filter(Object obj) {
         DataRow dr = (DataRow)obj;
         return Priv.getPriv(User.getUserName(), "menu", Application.getCurrentSiteID() + "-" + 
           dr.getString("id"), "menu_browse");
       }
     });
     boolean hasMenu = false;
     String template = "<li id='_Menu_${ID}' onclick='Application.onMainMenuClick(this);' onMouseOver='Application.onMainMenuMouseOver(this);' onMouseOut='Application.onMainMenuMouseOut(this);'>${Name}</li>";
     String menuHtml = HtmlUtil.replaceWithDataTable(dt, template);
     if (dt.getRowCount() > 0) {
       hasMenu = true;
     }
 
     StringBuffer sb = new StringBuffer();
 
     template = "arr.push([${ID},\"${Name}\",\"${URL}\",\"${Icon}\"]);";
     sb.append("var arr;");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       String id = dt.getString(i, "ID");
       sb.append("arr = [];");
       DataTable dt2 = new QueryBuilder(
         "select name,id,url,icon from zdmenu where visiable='Y' and parentID=? order by OrderFlag", id)
         .executeDataTable();
       dt2 = dt2.filter(new Filter() {
         public boolean filter(Object obj) {
           DataRow dr = (DataRow)obj;
           return Priv.getPriv(User.getUserName(), "menu", Application.getCurrentSiteID() + "-" + 
             dr.getString("id"), "menu_browse");
         }
       });
       sb.append(HtmlUtil.replaceWithDataTable(dt2, template));
       sb.append("$('_Menu_" + id + "').ChildArray = arr;");
       if (dt2.getRowCount() > 0) {
         hasMenu = true;
       }
     }
 
     HtmlScript script = new HtmlScript();
     script.setInnerHTML(sb.toString());
     if (hasMenu)
       params.put("Menu", menuHtml + script.getOuterHtml());
     else {
       params.put("Menu", "<font color='yellow'>对不起，你没有任何菜单权限，请联系'管理员'分配菜单权限后再登陆！</font>");
     }
 
     DataCollection privDC = Login.getAllPriv(new DataCollection());
     String priv = StringUtil.htmlEncode(privDC.toXML().replaceAll("\\s+", " "));
     params.put("Privileges", priv);
     return params;
   }
 
   public void changeSite()
   {
     String SiteID = $V("SiteID");
     setCurrentSiteID(SiteID);
   }
 
   public static void setCurrentSiteID(String siteID)
   {
     if (StringUtil.isEmpty(siteID))
       User.setValue("_CurrentSiteID", "");
     else
       User.setValue("_CurrentSiteID", siteID);
   }
 
   public static long getCurrentSiteID()
   {
     String id = (String)User.getValue("_CurrentSiteID");
     if (StringUtil.isEmpty(id)) {
       if ("admin".equals(User.getUserName())) {
         System.err.println("请在站点管理->站点列表下先创建站点");
         return 0L;
       }
       System.err.println("用户：" + User.getUserName() + "没有任何站点的浏览权限，请先设置权限再登陆");
       return 0L;
     }
 
     return Long.parseLong(id);
   }
 
   public static String getCurrentSiteAlias()
   {
     return SiteUtil.getAlias(getCurrentSiteID());
   }
 
   public void changePassword()
   {
     String OldPassword = $V("OldPassword");
     String Password = $V("Password");
     QueryBuilder qb = new QueryBuilder("update ZDUser set Password=? where UserName=? and Password=?");
     qb.add(StringUtil.md5Hex(Password));
     qb.add(User.getUserName());
     qb.add(StringUtil.md5Hex(OldPassword));
     if (qb.executeNoQuery() > 0) {
       UserLog.log("User", "EditPassword", "修改密码成功", this.Request.getClientIP());
       this.Response.setMessage("修改密码成功");
       this.Response.setStatus(1);
     } else {
       UserLog.log("User", "EditPassword", "修改密码失败", this.Request.getClientIP());
       this.Response.setStatus(0);
       this.Response.setMessage("修改密码失败，旧密码不正确");
     }
   }
 
   public void logout()
   {
     String logouturl = Config.getContextPath() + "Logout.jsp";
 
     this.Response.put("Status", 1);
     UserLog.log("Log", "Logout", "正常退出系统", this.Request.getClientIP());
 
     redirect(logouturl);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.Application
 * JD-Core Version:    0.5.3
 */