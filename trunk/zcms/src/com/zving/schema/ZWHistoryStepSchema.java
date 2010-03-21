 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZWHistoryStepSchema extends Schema
 {
   private Long ID;
   private Long EntryID;
   private Integer StepID;
   private Integer ActionID;
   private String Owner;
   private Date StartDate;
   private Date FinishDate;
   private Date DueDate;
   private String Status;
   private String Caller;
   private String Memo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("EntryID", 7, 1, 0, 0, false, false), 
     new SchemaColumn("StepID", 8, 2, 0, 0, false, false), 
     new SchemaColumn("ActionID", 8, 3, 0, 0, false, false), 
     new SchemaColumn("Owner", 1, 4, 200, 0, false, false), 
     new SchemaColumn("StartDate", 0, 5, 0, 0, false, false), 
     new SchemaColumn("FinishDate", 0, 6, 0, 0, false, false), 
     new SchemaColumn("DueDate", 0, 7, 0, 0, false, false), 
     new SchemaColumn("Status", 1, 8, 40, 0, false, false), 
     new SchemaColumn("Caller", 1, 9, 200, 0, false, false), 
     new SchemaColumn("Memo", 1, 10, 100, 0, false, false) };
   public static final String _TableCode = "ZWHistoryStep";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZWHistoryStep values(?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZWHistoryStep set ID=?,EntryID=?,StepID=?,ActionID=?,Owner=?,StartDate=?,FinishDate=?,DueDate=?,Status=?,Caller=?,Memo=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZWHistoryStep  where ID=?";
   protected static final String _FillAllSQL = "select * from ZWHistoryStep  where ID=?";
 
   public ZWHistoryStepSchema()
   {
     this.TableCode = "ZWHistoryStep";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZWHistoryStep values(?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZWHistoryStep set ID=?,EntryID=?,StepID=?,ActionID=?,Owner=?,StartDate=?,FinishDate=?,DueDate=?,Status=?,Caller=?,Memo=? where ID=?";
     this.DeleteSQL = "delete from ZWHistoryStep  where ID=?";
     this.FillAllSQL = "select * from ZWHistoryStep  where ID=?";
     this.HasSetFlag = new boolean[11];
   }
 
   protected Schema newInstance() {
     return new ZWHistoryStepSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZWHistoryStepSet();
   }
 
   public ZWHistoryStepSet query() {
     return query(null, -1, -1);
   }
 
   public ZWHistoryStepSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZWHistoryStepSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZWHistoryStepSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZWHistoryStepSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.EntryID = null; else this.EntryID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.StepID = null; else this.StepID = new Integer(v.toString()); return; }
     if (i == 3) { if (v == null) this.ActionID = null; else this.ActionID = new Integer(v.toString()); return; }
     if (i == 4) { this.Owner = ((String)v); return; }
     if (i == 5) { this.StartDate = ((Date)v); return; }
     if (i == 6) { this.FinishDate = ((Date)v); return; }
     if (i == 7) { this.DueDate = ((Date)v); return; }
     if (i == 8) { this.Status = ((String)v); return; }
     if (i == 9) { this.Caller = ((String)v); return; }
     if (i != 10) return; this.Memo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.EntryID;
     if (i == 2) return this.StepID;
     if (i == 3) return this.ActionID;
     if (i == 4) return this.Owner;
     if (i == 5) return this.StartDate;
     if (i == 6) return this.FinishDate;
     if (i == 7) return this.DueDate;
     if (i == 8) return this.Status;
     if (i == 9) return this.Caller;
     if (i == 10) return this.Memo;
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
 
   public long getEntryID()
   {
     if (this.EntryID == null) return 0L;
     return this.EntryID.longValue();
   }
 
   public void setEntryID(long entryID)
   {
     this.EntryID = new Long(entryID);
   }
 
   public void setEntryID(String entryID)
   {
     if (entryID == null) {
       this.EntryID = null;
       return;
     }
     this.EntryID = new Long(entryID);
   }
 
   public int getStepID()
   {
     if (this.StepID == null) return 0;
     return this.StepID.intValue();
   }
 
   public void setStepID(int stepID)
   {
     this.StepID = new Integer(stepID);
   }
 
   public void setStepID(String stepID)
   {
     if (stepID == null) {
       this.StepID = null;
       return;
     }
     this.StepID = new Integer(stepID);
   }
 
   public int getActionID()
   {
     if (this.ActionID == null) return 0;
     return this.ActionID.intValue();
   }
 
   public void setActionID(int actionID)
   {
     this.ActionID = new Integer(actionID);
   }
 
   public void setActionID(String actionID)
   {
     if (actionID == null) {
       this.ActionID = null;
       return;
     }
     this.ActionID = new Integer(actionID);
   }
 
   public String getOwner()
   {
     return this.Owner;
   }
 
   public void setOwner(String owner)
   {
     this.Owner = owner;
   }
 
   public Date getStartDate()
   {
     return this.StartDate;
   }
 
   public void setStartDate(Date startDate)
   {
     this.StartDate = startDate;
   }
 
   public Date getFinishDate()
   {
     return this.FinishDate;
   }
 
   public void setFinishDate(Date finishDate)
   {
     this.FinishDate = finishDate;
   }
 
   public Date getDueDate()
   {
     return this.DueDate;
   }
 
   public void setDueDate(Date dueDate)
   {
     this.DueDate = dueDate;
   }
 
   public String getStatus()
   {
     return this.Status;
   }
 
   public void setStatus(String status)
   {
     this.Status = status;
   }
 
   public String getCaller()
   {
     return this.Caller;
   }
 
   public void setCaller(String caller)
   {
     this.Caller = caller;
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZWHistoryStepSchema
 * JD-Core Version:    0.5.3
 */