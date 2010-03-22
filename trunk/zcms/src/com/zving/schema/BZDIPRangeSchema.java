 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDIPRangeSchema extends Schema
 {
   private String DistrictCode;
   private String IPRanges;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("DistrictCode", 1, 0, 10, 0, true, true), 
     new SchemaColumn("IPRanges", 10, 1, 0, 0, true, false), 
     new SchemaColumn("BackupNo", 1, 2, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 3, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 4, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 5, 50, 0, false, false) };
   public static final String _TableCode = "BZDIPRange";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDIPRange values(?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDIPRange set DistrictCode=?,IPRanges=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where DistrictCode=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDIPRange  where DistrictCode=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDIPRange  where DistrictCode=? and BackupNo=?";
 
   public BZDIPRangeSchema()
   {
     this.TableCode = "BZDIPRange";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDIPRange values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDIPRange set DistrictCode=?,IPRanges=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where DistrictCode=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDIPRange  where DistrictCode=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDIPRange  where DistrictCode=? and BackupNo=?";
     this.HasSetFlag = new boolean[6];
   }
 
   protected Schema newInstance() {
     return new BZDIPRangeSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDIPRangeSet();
   }
 
   public BZDIPRangeSet query() {
     return query(null, -1, -1);
   }
 
   public BZDIPRangeSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDIPRangeSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDIPRangeSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDIPRangeSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.DistrictCode = ((String)v); return; }
     if (i == 1) { this.IPRanges = ((String)v); return; }
     if (i == 2) { this.BackupNo = ((String)v); return; }
     if (i == 3) { this.BackupOperator = ((String)v); return; }
     if (i == 4) { this.BackupTime = ((Date)v); return; }
     if (i != 5) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.DistrictCode;
     if (i == 1) return this.IPRanges;
     if (i == 2) return this.BackupNo;
     if (i == 3) return this.BackupOperator;
     if (i == 4) return this.BackupTime;
     if (i == 5) return this.BackupMemo;
     return null;
   }
 
   public String getDistrictCode()
   {
     return this.DistrictCode;
   }
 
   public void setDistrictCode(String districtCode)
   {
     this.DistrictCode = districtCode;
   }
 
   public String getIPRanges()
   {
     return this.IPRanges;
   }
 
   public void setIPRanges(String iPRanges)
   {
     this.IPRanges = iPRanges;
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
 * Qualified Name:     com.zving.schema.BZDIPRangeSchema
 * JD-Core Version:    0.5.3
 */