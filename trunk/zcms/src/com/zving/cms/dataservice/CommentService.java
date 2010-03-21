 package com.zving.cms.dataservice;
 
 import com.zving.framework.Ajax;
 import com.zving.framework.Config;
 import com.zving.framework.controls.DataListAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Mapx;
 
 public class CommentService extends Ajax
 {
   public static Mapx init(Mapx params)
   {
     params.put("ServicesContext", Config.getValue("ServicesContext"));
     params.put("CommentActionURL", Config.getValue("CommentActionURL"));
     params.put("CommentCountJS", Config.getValue("CommentCountJS"));
     params.put("CommentListJS", Config.getValue("CommentListJS"));
     params.put("CommentListPageJS", Config.getValue("CommentListPageJS"));
     return params;
   }
 
   public static void dg1DataBind(DataListAction dla) {
     String relaID = dla.getParam("RelaID");
     String siteID = dla.getParam("SiteID");
     String CommentFlag = new QueryBuilder("select CommentAuditFlag from ZCSite where ID = ?", siteID).executeString();
     String WherePart = "";
     if ("Y".equals(CommentFlag))
       WherePart = " and verifyflag='Y'";
     else {
       WherePart = "";
     }
     if (dla.getTotal() == 0) {
       dla.setTotal(new QueryBuilder("select count(*) from ZCComment where relaID = ?" + WherePart, relaID));
     }
     DataTable dt = new QueryBuilder("select * from ZCComment where relaID = ? " + WherePart + " order by ID desc", relaID).executePagedDataTable(dla.getPageSize(), dla.getPageIndex());
     for (int i = 0; i < dt.getRowCount(); ++i) {
       if (dt.get(i, "AntiCount") == null) {
         dt.set(i, "AntiCount", 0);
       }
       if (dt.get(i, "SupporterCount") == null) {
         dt.set(i, "SupporterCount", 0);
       }
     }
     dla.bindData(dt);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.dataservice.CommentService
 * JD-Core Version:    0.5.3
 */