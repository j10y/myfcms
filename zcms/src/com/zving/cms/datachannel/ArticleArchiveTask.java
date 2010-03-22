 package com.zving.cms.datachannel;
 
 import com.zving.cms.pub.CatalogUtil;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.schedule.GeneralTask;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.LogUtil;
 import com.zving.framework.utility.StringUtil;
 import java.util.Date;
 import org.apache.commons.logging.Log;
 
 public class ArticleArchiveTask extends GeneralTask
 {
   public void execute()
   {
     LogUtil.getLogger().info("定时归档任务");
     DataTable siteDT = new QueryBuilder("select ID from zcsite").executeDataTable();
     for (int j = 0; j < siteDT.getRowCount(); ++j) {
       DataTable catalogDT = new QueryBuilder("select ID,innercode from zccatalog where siteID = ? and type= '1'", 
         siteDT.getString(j, "ID")).executeDataTable();
       Transaction trans = new Transaction();
       for (int i = 0; i < catalogDT.getRowCount(); ++i) {
         String archiveTime = CatalogUtil.getArchiveTime(catalogDT.getString(i, "ID"));
         if (StringUtil.isNotEmpty(archiveTime)) {
           trans.add(
             new QueryBuilder("update zcarticle set status = '50',TopFlag='0' where catalogID=" + 
             catalogDT.getString(i, "ID") + " and publishdate <= '" + 
             DateUtil.toString(DateUtil.addMonth(new Date(), -Integer.parseInt(archiveTime)), "yyyy-MM-dd HH:mm:ss") + 
             "' and siteID=" + siteDT.getString(j, "ID")));
         }
       }
 
       trans.commit();
     }
     LogUtil.getLogger().info("扫描定时归档任务结束");
   }
 
   public String getCronExpression() {
     return "0 0 1 * *";
   }
 
   public long getID() {
     return 200912081905L;
   }
 
   public String getName() {
     return "对文章进行归档";
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.datachannel.ArticleArchiveTask
 * JD-Core Version:    0.5.3
 */