 package com.zving.cms.datachannel;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataRow;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.schema.ZCDeployLogSchema;
 import com.zving.schema.ZCDeployLogSet;
 
 public class DeployLog extends Page
 {
   public static void dg1DataBind(DataGridAction dga)
   {
     String sql1 = "select a.id,a.jobid,a.message,a.begintime,a.endtime,b.method,b.host,b.source,b.target,(select codename from zdcode where codetype='DeployMethod' and parentCode='DeployMethod' and codevalue=b.method) as methodDesc from ZCDeployLog a,ZCDeployJob b where  a.JobID=b.ID and b.SiteID=? order by a.begintime desc";
 
     if (dga.getTotal() == 0) {
       String sql2 = "select count(1) from ZCDeployLog a,ZCDeployJob b where a.JobID=b.ID and b.SiteID=?";
       dga.setTotal(new QueryBuilder(sql2, Application.getCurrentSiteID()));
     }
     dga.bindData(new QueryBuilder(sql1, Application.getCurrentSiteID()).executePagedDataTable(dga.getPageSize(), dga.getPageIndex()));
   }
 
   public static Mapx initDialog(Mapx params) {
     String sql = "select a.id,a.jobid,a.message,a.begintime,a.endtime,b.method,b.host,b.source,b.target,(select codename from zdcode where codetype='DeployMethod' and parentCode='DeployMethod' and codevalue=b.method) as methodDesc from ZCDeployLog a,ZCDeployJob b where  a.JobID=b.ID and a.ID=?";
 
     DataTable dt = new QueryBuilder(sql, params.getString("ID")).executeDataTable();
     if ((dt != null) && (dt.getRowCount() > 0)) {
       params.putAll(dt.get(0).toCaseIgnoreMapx());
     }
     return params;
   }
 
   public void del() {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     String tsql = "where id in (" + ids + ")";
     ZCDeployLogSchema ZCDeployLog = new ZCDeployLogSchema();
     ZCDeployLogSet set = ZCDeployLog.query(new QueryBuilder(tsql));
 
     Transaction trans = new Transaction();
     trans.add(set, 5);
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 
   public void delAll() {
     String tsql = "where siteid=?";
     ZCDeployLogSchema ZCDeployLog = new ZCDeployLogSchema();
     ZCDeployLogSet set = ZCDeployLog.query(new QueryBuilder(tsql, Application.getCurrentSiteID()));
     Transaction trans = new Transaction();
     trans.add(set, 5);
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.datachannel.DeployLog
 * JD-Core Version:    0.5.3
 */