 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDPrivilegeSchema extends Schema
 {
   private String OwnerType;
   private String Owner;
   private String PrivType;
   private String ID;
   private String Code;
   private String Value;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("OwnerType", 1, 0, 1, 0, true, true), 
     new SchemaColumn("Owner", 1, 1, 100, 0, true, true), 
     new SchemaColumn("PrivType", 1, 2, 40, 0, true, true), 
     new SchemaColumn("ID", 1, 3, 100, 0, true, true), 
     new SchemaColumn("Code", 1, 4, 40, 0, true, true), 
     new SchemaColumn("Value", 1, 5, 2, 0, true, false), 
     new SchemaColumn("BackupNo", 1, 6, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 7, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 8, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 9, 50, 0, false, false) };
   public static final String _TableCode = "BZDPrivilege";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDPrivilege values(?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDPrivilege set OwnerType=?,Owner=?,PrivType=?,ID=?,Code=?,Value=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=? and BackupNo=?";
 
   public BZDPrivilegeSchema()
   {
     this.TableCode = "BZDPrivilege";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDPrivilege values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDPrivilege set OwnerType=?,Owner=?,PrivType=?,ID=?,Code=?,Value=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDPrivilege  where OwnerType=? and Owner=? and PrivType=? and ID=? and Code=? and BackupNo=?";
     this.HasSetFlag = new boolean[10];
   }
 
   protected Schema newInstance() {
     return new BZDPrivilegeSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDPrivilegeSet();
   }
 
   public BZDPrivilegeSet query() {
     return query(null, -1, -1);
   }
 
   public BZDPrivilegeSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDPrivilegeSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDPrivilegeSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDPrivilegeSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.OwnerType = ((String)v); return; }
     if (i == 1) { this.Owner = ((String)v); return; }
     if (i == 2) { this.PrivType = ((String)v); return; }
     if (i == 3) { this.ID = ((String)v); return; }
     if (i == 4) { this.Code = ((String)v); return; }
     if (i == 5) { this.Value = ((String)v); return; }
     if (i == 6) { this.BackupNo = ((String)v); return; }
     if (i == 7) { this.BackupOperator = ((String)v); return; }
     if (i == 8) { this.BackupTime = ((Date)v); return; }
     if (i != 9) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.OwnerType;
     if (i == 1) return this.Owner;
     if (i == 2) return this.PrivType;
     if (i == 3) return this.ID;
     if (i == 4) return this.Code;
     if (i == 5) return this.Value;
     if (i == 6) return this.BackupNo;
     if (i == 7) return this.BackupOperator;
     if (i == 8) return this.BackupTime;
     if (i == 9) return this.BackupMemo;
     return null;
   }
 
   public String getOwnerType()
   {
     return this.OwnerType;
   }
 
   public void setOwnerType(String ownerType)
   {
     this.OwnerType = ownerType;
   }
 
   public String getOwner()
   {
     return this.Owner;
   }
 
   public void setOwner(String owner)
   {
     this.Owner = owner;
   }
 
   public String getPrivType()
   {
     return this.PrivType;
   }
 
   public void setPrivType(String privType)
   {
     this.PrivType = privType;
   }
 
   public String getID()
   {
     return this.ID;
   }
 
   public void setID(String iD)
   {
     this.ID = iD;
   }
 
   public String getCode()
   {
     return this.Code;
   }
 
   public void setCode(String code)
   {
     this.Code = code;
   }
 
   public String getValue()
   {
     return this.Value;
   }
 
   public void setValue(String value)
   {
     this.Value = value;
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
 * Qualified Name:     com.zving.schema.BZDPrivilegeSchema
 * JD-Core Version:    0.5.3
 */