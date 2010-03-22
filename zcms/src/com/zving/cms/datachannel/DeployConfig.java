 package com.zving.cms.datachannel;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.cache.CacheManager;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCDeployConfigSchema;
 import com.zving.schema.ZCDeployConfigSet;
 import java.util.Date;
 
 public class DeployConfig extends Page
 {
   public static Mapx init(Mapx params)
   {
     return null;
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     String sql = "select * from ZCDeployConfig where siteid=" + Application.getCurrentSiteID() + 
       dga.getSortString();
     if (dga.getTotal() == 0) {
       dga.setTotal(
         new QueryBuilder("select count(*) from ZCDeployConfig where siteid=?", 
         Application.getCurrentSiteID()));
     }
     DataTable dt = new QueryBuilder(sql).executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     dt.decodeColumn("Method", CacheManager.get("Code", "DeployMethod"));
     dga.bindData(dt);
   }
 
   public void add() {
     ZCDeployConfigSchema deployConfig = new ZCDeployConfigSchema();
     deployConfig.setID(NoUtil.getMaxID("DeployConfigID"));
     deployConfig.setSiteID(Application.getCurrentSiteID());
 
     String sourceDir = $V("SourceDir");
     sourceDir = sourceDir.replace('\\', '/');
     deployConfig.setSourceDir(sourceDir);
 
     String target = $V("TargetDir");
     target = target.replace('\\', '/');
     deployConfig.setTargetDir(target);
     deployConfig.setMethod($V("Method"));
     deployConfig.setHost($V("Host"));
     String port = $V("Port");
     if ((port != null) && (!("".equals(port)))) {
       deployConfig.setPort(Integer.parseInt(port));
     }
     deployConfig.setUserName($V("UserName"));
     deployConfig.setPassword($V("Password"));
     String beginDate = $V("BeginDate");
     String beginTime = $V("BeginTime");
 
     if ((!("".equals(beginDate))) && (!("null".equals(beginDate))) && (beginDate != null)) {
       deployConfig.setBeginTime(DateUtil.parseDateTime(beginDate + " " + beginTime));
     }
 
     deployConfig.setUseFlag(Integer.parseInt($V("UseFlag")));
     deployConfig.setAddTime(new Date());
     deployConfig.setAddUser(User.getUserName());
     if (deployConfig.insert()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("发生错误!");
     }
   }
 
   public void del() {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     String tsql = "where id in (" + ids + ")";
     ZCDeployConfigSchema ZCDeployConfig = new ZCDeployConfigSchema();
     ZCDeployConfigSet set = ZCDeployConfig.query(new QueryBuilder(tsql));
 
     Transaction trans = new Transaction();
     trans.add(set, 5);
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 
   public void edit() {
     ZCDeployConfigSchema deployConfig = new ZCDeployConfigSchema();
     deployConfig.setID(Long.parseLong($V("ID")));
     if (!(deployConfig.fill())) {
       this.Response.setStatus(0);
       this.Response.setMessage("没有对应的定时任务!");
     }
     deployConfig.setSourceDir($V("SourceDir"));
     deployConfig.setTargetDir($V("TargetDir"));
     deployConfig.setMethod($V("Method"));
     deployConfig.setHost($V("Host"));
     String port = $V("Port");
     if ((port != null) && (!("".equals(port)))) {
       deployConfig.setPort(Integer.parseInt(port));
     }
     deployConfig.setUserName($V("UserName"));
     deployConfig.setPassword($V("Password"));
     String beginDate = $V("BeginDate");
     String beginTime = $V("BeginTime");
 
     if ((!("".equals(beginDate))) && (!("null".equals(beginDate))) && (beginDate != null)) {
       deployConfig.setBeginTime(DateUtil.parseDateTime(beginDate + " " + beginTime));
     }
     deployConfig.setUseFlag(Integer.parseInt($V("UseFlag")));
     deployConfig.setModifyTime(new Date());
     deployConfig.setModifyUser(User.getUserName());
 
     Transaction trans = new Transaction();
     trans.add(deployConfig, 2);
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("发生错误!");
     }
   }
 
   public void deploy() {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     String[] idArr = ids.split(",");
     Deploy deploy = new Deploy();
     for (int i = 0; i < idArr.length; ++i) {
       long configID = Long.parseLong(idArr[i]);
       deploy.addOneJob(configID, true);
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.datachannel.DeployConfig
 * JD-Core Version:    0.5.3
 */