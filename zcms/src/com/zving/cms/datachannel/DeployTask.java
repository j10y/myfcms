 package com.zving.cms.datachannel;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.schedule.GeneralTask;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.LogUtil;
 import com.zving.schema.ZCDeployJobSchema;
 import com.zving.schema.ZCDeployJobSet;
 import java.util.Date;
 import org.apache.commons.logging.Log;
 
 public class DeployTask extends GeneralTask
 {
   public void execute()
   {
     ZCDeployJobSchema job = new ZCDeployJobSchema();
     ZCDeployJobSet set = job.query(new QueryBuilder("where RetryCount<5  and status <>2"));
     if ((set != null) && (set.size() > 0)) {
       LogUtil.getLogger().info("执行分发任务 任务数：" + set.size());
     }
     for (int i = 0; i < set.size(); ++i) {
       Deploy d = new Deploy();
       d.executeJob(set.get(i));
     }
 
     if (System.currentTimeMillis() % 1000000L != 0L)
       return;
     Date yesterday = DateUtil.addDay(new Date(), -1);
     Date beforeYesterday = DateUtil.addDay(new Date(), -2);
     new QueryBuilder("delete from ZCDeployJob where RetryCount>=5  and status=3 and addtime<=?", beforeYesterday).executeNoQuery();
     new QueryBuilder("delete from ZCDeployJob where  status=2 and addtime<=?", yesterday).executeNoQuery();
     new QueryBuilder("delete from ZCDeployLog where not exists (select id from ZCDeployJob where  ZCDeployLog.jobid= ZCDeployJob.id)").executeNoQuery();
   }
 
   public String getCronExpression()
   {
     return "* * * * *";
   }
 
   public long getID() {
     return 200907241819L;
   }
 
   public String getName() {
     return "分发到目录定时任务";
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.datachannel.DeployTask
 * JD-Core Version:    0.5.3
 */