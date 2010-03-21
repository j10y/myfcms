 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDColumnValueSchema extends Schema
 {
   private Long ID;
   private Long ColumnID;
   private String ColumnCode;
   private String TextValue;
   private String RelaType;
   private String RelaID;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("ColumnID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("ColumnCode", 1, 2, 100, 0, true, false), 
     new SchemaColumn("TextValue", 10, 3, 0, 0, false, false), 
     new SchemaColumn("RelaType", 1, 4, 2, 0, false, false), 
     new SchemaColumn("RelaID", 1, 5, 100, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 6, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 7, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 8, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 9, 50, 0, false, false) };
   public static final String _TableCode = "BZDColumnValue";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDColumnValue values(?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDColumnValue set ID=?,ColumnID=?,ColumnCode=?,TextValue=?,RelaType=?,RelaID=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDColumnValue  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDColumnValue  where ID=? and BackupNo=?";
 
   public BZDColumnValueSchema()
   {
     this.TableCode = "BZDColumnValue";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDColumnValue values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDColumnValue set ID=?,ColumnID=?,ColumnCode=?,TextValue=?,RelaType=?,RelaID=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDColumnValue  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDColumnValue  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[10];
   }
 
   protected Schema newInstance() {
     return new BZDColumnValueSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDColumnValueSet();
   }
 
   public BZDColumnValueSet query() {
     return query(null, -1, -1);
   }
 
   public BZDColumnValueSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDColumnValueSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDColumnValueSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDColumnValueSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.ColumnID = null; else this.ColumnID = new Long(v.toString()); return; }
     if (i == 2) { this.ColumnCode = ((String)v); return; }
     if (i == 3) { this.TextValue = ((String)v); return; }
     if (i == 4) { this.RelaType = ((String)v); return; }
     if (i == 5) { this.RelaID = ((String)v); return; }
     if (i == 6) { this.BackupNo = ((String)v); return; }
     if (i == 7) { this.BackupOperator = ((String)v); return; }
     if (i == 8) { this.BackupTime = ((Date)v); return; }
     if (i != 9) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.ColumnID;
     if (i == 2) return this.ColumnCode;
     if (i == 3) return this.TextValue;
     if (i == 4) return this.RelaType;
     if (i == 5) return this.RelaID;
     if (i == 6) return this.BackupNo;
     if (i == 7) return this.BackupOperator;
     if (i == 8) return this.BackupTime;
     if (i == 9) return this.BackupMemo;
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
 
   public long getColumnID()
   {
     if (this.ColumnID == null) return 0L;
     return this.ColumnID.longValue();
   }
 
   public void setColumnID(long columnID)
   {
     this.ColumnID = new Long(columnID);
   }
 
   public void setColumnID(String columnID)
   {
     if (columnID == null) {
       this.ColumnID = null;
       return;
     }
     this.ColumnID = new Long(columnID);
   }
 
   public String getColumnCode()
   {
     return this.ColumnCode;
   }
 
   public void setColumnCode(String columnCode)
   {
     this.ColumnCode = columnCode;
   }
 
   public String getTextValue()
   {
     return this.TextValue;
   }
 
   public void setTextValue(String textValue)
   {
     this.TextValue = textValue;
   }
 
   public String getRelaType()
   {
     return this.RelaType;
   }
 
   public void setRelaType(String relaType)
   {
     this.RelaType = relaType;
   }
 
   public String getRelaID()
   {
     return this.RelaID;
   }
 
   public void setRelaID(String relaID)
   {
     this.RelaID = relaID;
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
 * Qualified Name:     com.zving.schema.BZDColumnValueSchema
 * JD-Core Version:    0.5.3
 */