 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCDeployLogSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private Long JobID;
   private String Message;
   private String Memo;
   private Date BeginTime;
   private Date EndTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("JobID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Message", 1, 3, 500, 0, false, false), 
     new SchemaColumn("Memo", 1, 4, 200, 0, false, false), 
     new SchemaColumn("BeginTime", 0, 5, 0, 0, false, false), 
     new SchemaColumn("EndTime", 0, 6, 0, 0, false, false) };
   public static final String _TableCode = "ZCDeployLog";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCDeployLog values(?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCDeployLog set ID=?,SiteID=?,JobID=?,Message=?,Memo=?,BeginTime=?,EndTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCDeployLog  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCDeployLog  where ID=?";
 
   public ZCDeployLogSchema()
   {
     this.TableCode = "ZCDeployLog";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCDeployLog values(?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCDeployLog set ID=?,SiteID=?,JobID=?,Message=?,Memo=?,BeginTime=?,EndTime=? where ID=?";
     this.DeleteSQL = "delete from ZCDeployLog  where ID=?";
     this.FillAllSQL = "select * from ZCDeployLog  where ID=?";
     this.HasSetFlag = new boolean[7];
   }
 
   protected Schema newInstance() {
     return new ZCDeployLogSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCDeployLogSet();
   }
 
   public ZCDeployLogSet query() {
     return query(null, -1, -1);
   }
 
   public ZCDeployLogSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCDeployLogSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCDeployLogSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCDeployLogSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.JobID = null; else this.JobID = new Long(v.toString()); return; }
     if (i == 3) { this.Message = ((String)v); return; }
     if (i == 4) { this.Memo = ((String)v); return; }
     if (i == 5) { this.BeginTime = ((Date)v); return; }
     if (i != 6) return; this.EndTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.JobID;
     if (i == 3) return this.Message;
     if (i == 4) return this.Memo;
     if (i == 5) return this.BeginTime;
     if (i == 6) return this.EndTime;
     return null;
   }
 
   public long getID()
   {
     if (this.ID == null) return 0L;
     return this.ID.longValue();
   }
 
   public void setID(long iD)
   {
     this.ID = new Long(iD);
   }
 
   public void setID(String iD)
   {
     if (iD == null) {
       this.ID = null;
       return;
     }
     this.ID = new Long(iD);
   }
 
   public long getSiteID()
   {
     if (this.SiteID == null) return 0L;
     return this.SiteID.longValue();
   }
 
   public void setSiteID(long siteID)
   {
     this.SiteID = new Long(siteID);
   }
 
   public void setSiteID(String siteID)
   {
     if (siteID == null) {
       this.SiteID = null;
       return;
     }
     this.SiteID = new Long(siteID);
   }
 
   public long getJobID()
   {
     if (this.JobID == null) return 0L;
     return this.JobID.longValue();
   }
 
   public void setJobID(long jobID)
   {
     this.JobID = new Long(jobID);
   }
 
   public void setJobID(String jobID)
   {
     if (jobID == null) {
       this.JobID = null;
       return;
     }
     this.JobID = new Long(jobID);
   }
 
   public String getMessage()
   {
     return this.Message;
   }
 
   public void setMessage(String message)
   {
     this.Message = message;
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
   }
 
   public Date getBeginTime()
   {
     return this.BeginTime;
   }
 
   public void setBeginTime(Date beginTime)
   {
     this.BeginTime = beginTime;
   }
 
   public Date getEndTime()
   {
     return this.EndTime;
   }
 
   public void setEndTime(Date endTime)
   {
     this.EndTime = endTime;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCDeployLogSchema
 * JD-Core Version:    0.5.3
 */