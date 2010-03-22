 package com.zving.cms.dataservice;
 
 import com.zving.framework.Ajax;
 import com.zving.framework.controls.DataListAction;
 import com.zving.framework.data.DataColumn;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
import com.zving.framework.utility.StringUtil;
 
 public class MessageList extends Ajax
 {
   public static void dg1DataList(DataListAction dla)
   {
     String SiteID = dla.getParam("SiteID");
     if (StringUtil.isEmpty(SiteID)) {
       SiteID = String.valueOf(new QueryBuilder("select ID from zcsite order by AddTime desc").executeOneValue());
     }
     String sql = "select * from zcmessageboard where PublishFlag = '1' and Type = 'message' and SiteID = '" + SiteID + "' order by ID desc";
     String sql2 = "select count(*) from zcmessageboard where PublishFlag = '1' and Type = 'message' and SiteID = '" + SiteID + "'";
     QueryBuilder qb = new QueryBuilder();
     qb.setSQL(sql2);
     dla.setTotal(qb);
     qb.setSQL(sql);
     DataTable dt = qb.executePagedDataTable(dla.getPageSize(), dla.getPageIndex());
     dt.insertColumn("Display");
     dt.insertColumn("Attach");
     if ((dt != null) && (dt.getRowCount() > 0)) {
       for (int i = 0; i < dt.getRowCount(); ++i) {
         if (dt.getString(i, "ReplyFlag").equals("1"))
           dt.set(i, "Display", "");
         else {
           dt.set(i, "Display", "none");
         }
         if (StringUtil.isNotEmpty(dt.getString(i, "AttachPath")))
           dt.set(i, "Attach", "&nbsp;&nbsp;&nbsp;<a href='#;' style='color:#FF6600' title='" + dt.getString(i, "Prop1") + "' onclick = 'download(" + dt.getString(i, "ID") + ")'>附件下载</a>");
         else {
           dt.set(i, "Attach", "");
         }
       }
       dt.getDataColumn("AddTime").setDateFormat("yyyy-MM-dd HH:mm:ss");
     }
     dla.bindData(dt);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.dataservice.MessageList
 * JD-Core Version:    0.5.3
 */