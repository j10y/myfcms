 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDDistrictSchema extends Schema
 {
   private String Code;
   private String Name;
   private String CodeOrder;
   private Integer TreeLevel;
   private String Type;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("Code", 1, 0, 6, 0, true, true), 
     new SchemaColumn("Name", 1, 1, 100, 0, false, false), 
     new SchemaColumn("CodeOrder", 1, 2, 20, 0, false, false), 
     new SchemaColumn("TreeLevel", 8, 3, 0, 0, false, false), 
     new SchemaColumn("Type", 1, 4, 2, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 5, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 6, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 7, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 8, 50, 0, false, false) };
   public static final String _TableCode = "BZDDistrict";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDDistrict values(?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDDistrict set Code=?,Name=?,CodeOrder=?,TreeLevel=?,Type=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where Code=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDDistrict  where Code=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDDistrict  where Code=? and BackupNo=?";
 
   public BZDDistrictSchema()
   {
     this.TableCode = "BZDDistrict";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDDistrict values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDDistrict set Code=?,Name=?,CodeOrder=?,TreeLevel=?,Type=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where Code=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDDistrict  where Code=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDDistrict  where Code=? and BackupNo=?";
     this.HasSetFlag = new boolean[9];
   }
 
   protected Schema newInstance() {
     return new BZDDistrictSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDDistrictSet();
   }
 
   public BZDDistrictSet query() {
     return query(null, -1, -1);
   }
 
   public BZDDistrictSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDDistrictSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDDistrictSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDDistrictSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.Code = ((String)v); return; }
     if (i == 1) { this.Name = ((String)v); return; }
     if (i == 2) { this.CodeOrder = ((String)v); return; }
     if (i == 3) { if (v == null) this.TreeLevel = null; else this.TreeLevel = new Integer(v.toString()); return; }
     if (i == 4) { this.Type = ((String)v); return; }
     if (i == 5) { this.BackupNo = ((String)v); return; }
     if (i == 6) { this.BackupOperator = ((String)v); return; }
     if (i == 7) { this.BackupTime = ((Date)v); return; }
     if (i != 8) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.Code;
     if (i == 1) return this.Name;
     if (i == 2) return this.CodeOrder;
     if (i == 3) return this.TreeLevel;
     if (i == 4) return this.Type;
     if (i == 5) return this.BackupNo;
     if (i == 6) return this.BackupOperator;
     if (i == 7) return this.BackupTime;
     if (i == 8) return this.BackupMemo;
     return null;
   }
 
   public String getCode()
   {
     return this.Code;
   }
 
   public void setCode(String code)
   {
     this.Code = code;
   }
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
   }
 
   public String getCodeOrder()
   {
     return this.CodeOrder;
   }
 
   public void setCodeOrder(String codeOrder)
   {
     this.CodeOrder = codeOrder;
   }
 
   public int getTreeLevel()
   {
     if (this.TreeLevel == null) return 0;
     return this.TreeLevel.intValue();
   }
 
   public void setTreeLevel(int treeLevel)
   {
     this.TreeLevel = new Integer(treeLevel);
   }
 
   public void setTreeLevel(String treeLevel)
   {
     if (treeLevel == null) {
       this.TreeLevel = null;
       return;
     }
     this.TreeLevel = new Integer(treeLevel);
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
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
 * Qualified Name:     com.zving.schema.BZDDistrictSchema
 * JD-Core Version:    0.5.3
 */