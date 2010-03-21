 package com.zving.cms.datachannel;
 
 import com.zving.cms.template.PageGenerator;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.LogUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.platform.pub.ConfigEanbleTaskManager;
 import com.zving.schema.ZCCatalogConfigSchema;
 import com.zving.schema.ZCSiteSchema;
 import com.zving.schema.ZCSiteSet;
 import org.apache.commons.logging.Log;
 
 public class PublishTaskManager extends ConfigEanbleTaskManager
 {
   public void execute(long id)
   {
     LogUtil.getLogger().info("开始发布内容");
 
     if (id == -1L) {
       DataTable dt = new QueryBuilder("select id from zcsite").executeDataTable();
       for (int i = 0; i < dt.getRowCount(); ++i) {
         Publisher p = new Publisher();
         p.publishAll(dt.getLong(i, "id"));
       }
     } else if (id == 0L) {
       ZCSiteSchema site = new ZCSiteSchema();
       ZCSiteSet set = site.query();
       for (int i = 0; i < set.size(); ++i) {
         PageGenerator p = new PageGenerator();
         p.staticPageBlock(site, null, 0);
 
         Deploy d = new Deploy();
         d.addJobs(site.getID(), p.getFileList());
       }
     } else {
       ZCCatalogConfigSchema pc = new ZCCatalogConfigSchema();
       pc.setID(id);
       if (pc.fill())
       {
         Publisher p;
         if (pc.getCatalogID() == 0L) {
           p = new Publisher();
           p.publishAll(pc.getSiteID());
         } else {
           p = new Publisher();
           p.publishCatalog(pc.getCatalogID(), true, true);
         }
       }
     }
     LogUtil.getLogger().info("结束发布内容");
   }
 
   public String getCode() {
     return "Publisher";
   }
 
   public String getName() {
     return "内容发布任务";
   }
 
   public Mapx getConfigEnableTasks() {
     Mapx map = new Mapx();
     map.put("-1", "发布全部文档");
     map.put("0", "全局区块发布");
     return map;
   }
 
   public boolean isRunning(long arg0)
   {
     return false;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.datachannel.PublishTaskManager
 * JD-Core Version:    0.5.3
 */