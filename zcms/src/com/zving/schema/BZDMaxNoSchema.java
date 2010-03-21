 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDMaxNoSchema extends Schema
 {
   private String NoType;
   private String NoSubType;
   private Long MaxValue;
   private Long Length;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("NoType", 1, 0, 20, 0, true, true), 
     new SchemaColumn("NoSubType", 1, 1, 255, 0, true, true), 
     new SchemaColumn("MaxValue", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Length", 7, 3, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 4, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 5, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 6, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 7, 50, 0, false, false) };
   public static final String _TableCode = "BZDMaxNo";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDMaxNo values(?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDMaxNo set NoType=?,NoSubType=?,MaxValue=?,Length=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where NoType=? and NoSubType=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDMaxNo  where NoType=? and NoSubType=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDMaxNo  where NoType=? and NoSubType=? and BackupNo=?";
 
   public BZDMaxNoSchema()
   {
     this.TableCode = "BZDMaxNo";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDMaxNo values(?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDMaxNo set NoType=?,NoSubType=?,MaxValue=?,Length=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where NoType=? and NoSubType=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDMaxNo  where NoType=? and NoSubType=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDMaxNo  where NoType=? and NoSubType=? and BackupNo=?";
     this.HasSetFlag = new boolean[8];
   }
 
   protected Schema newInstance() {
     return new BZDMaxNoSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDMaxNoSet();
   }
 
   public BZDMaxNoSet query() {
     return query(null, -1, -1);
   }
 
   public BZDMaxNoSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDMaxNoSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDMaxNoSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDMaxNoSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.NoType = ((String)v); return; }
     if (i == 1) { this.NoSubType = ((String)v); return; }
     if (i == 2) { if (v == null) this.MaxValue = null; else this.MaxValue = new Long(v.toString()); return; }
     if (i == 3) { if (v == null) this.Length = null; else this.Length = new Long(v.toString()); return; }
     if (i == 4) { this.BackupNo = ((String)v); return; }
     if (i == 5) { this.BackupOperator = ((String)v); return; }
     if (i == 6) { this.BackupTime = ((Date)v); return; }
     if (i != 7) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.NoType;
     if (i == 1) return this.NoSubType;
     if (i == 2) return this.MaxValue;
     if (i == 3) return this.Length;
     if (i == 4) return this.BackupNo;
     if (i == 5) return this.BackupOperator;
     if (i == 6) return this.BackupTime;
     if (i == 7) return this.BackupMemo;
     return null;
   }
 
   public String getNoType()
   {
     return this.NoType;
   }
 
   public void setNoType(String noType)
   {
     this.NoType = noType;
   }
 
   public String getNoSubType()
   {
     return this.NoSubType;
   }
 
   public void setNoSubType(String noSubType)
   {
     this.NoSubType = noSubType;
   }
 
   public long getMaxValue()
   {
     if (this.MaxValue == null) return 0L;
     return this.MaxValue.longValue();
   }
 
   public void setMaxValue(long maxValue)
   {
     this.MaxValue = new Long(maxValue);
   }
 
   public void setMaxValue(String maxValue)
   {
     if (maxValue == null) {
       this.MaxValue = null;
       return;
     }
     this.MaxValue = new Long(maxValue);
   }
 
   public long getLength()
   {
     if (this.Length == null) return 0L;
     return this.Length.longValue();
   }
 
   public void setLength(long length)
   {
     this.Length = new Long(length);
   }
 
   public void setLength(String length)
   {
     if (length == null) {
       this.Length = null;
       return;
     }
     this.Length = new Long(length);
   }
 
   public String getBackupNo()
   {
     return this.BackupNo;
   }
 
   public void setBackupNo(String backupNo)
   {
     this.BackupNo = backupNo;
   }
 
   public String getBackupOperator()
   {
     return this.BackupOperator;
   }
 
   public void setBackupOperator(String backupOperator)
   {
     this.BackupOperator = backupOperator;
   }
 
   public Date getBackupTime()
   {
     return this.BackupTime;
   }
 
   public void setBackupTime(Date backupTime)
   {
     this.BackupTime = backupTime;
   }
 
   public String getBackupMemo()
   {
     return this.BackupMemo;
   }
 
   public void setBackupMemo(String backupMemo)
   {
     this.BackupMemo = backupMemo;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZDMaxNoSchema
 * JD-Core Version:    0.5.3
 */