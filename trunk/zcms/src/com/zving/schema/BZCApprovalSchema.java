 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCApprovalSchema extends Schema
 {
   private Long ID;
   private String ApproveUser;
   private Long ArticleID;
   private String Memo;
   private Long Status;
   private Date ApprovalDate;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("ApproveUser", 1, 1, 200, 0, true, false), 
     new SchemaColumn("ArticleID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Memo", 1, 3, 200, 0, false, false), 
     new SchemaColumn("Status", 7, 4, 0, 0, false, false), 
     new SchemaColumn("ApprovalDate", 0, 5, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 6, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 7, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 8, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 9, 50, 0, false, false) };
   public static final String _TableCode = "BZCApproval";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCApproval values(?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCApproval set ID=?,ApproveUser=?,ArticleID=?,Memo=?,Status=?,ApprovalDate=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCApproval  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCApproval  where ID=? and BackupNo=?";
 
   public BZCApprovalSchema()
   {
     this.TableCode = "BZCApproval";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCApproval values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCApproval set ID=?,ApproveUser=?,ArticleID=?,Memo=?,Status=?,ApprovalDate=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCApproval  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCApproval  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[10];
   }
 
   protected Schema newInstance() {
     return new BZCApprovalSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCApprovalSet();
   }
 
   public BZCApprovalSet query() {
     return query(null, -1, -1);
   }
 
   public BZCApprovalSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCApprovalSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCApprovalSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCApprovalSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.ApproveUser = ((String)v); return; }
     if (i == 2) { if (v == null) this.ArticleID = null; else this.ArticleID = new Long(v.toString()); return; }
     if (i == 3) { this.Memo = ((String)v); return; }
     if (i == 4) { if (v == null) this.Status = null; else this.Status = new Long(v.toString()); return; }
     if (i == 5) { this.ApprovalDate = ((Date)v); return; }
     if (i == 6) { this.BackupNo = ((String)v); return; }
     if (i == 7) { this.BackupOperator = ((String)v); return; }
     if (i == 8) { this.BackupTime = ((Date)v); return; }
     if (i != 9) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.ApproveUser;
     if (i == 2) return this.ArticleID;
     if (i == 3) return this.Memo;
     if (i == 4) return this.Status;
     if (i == 5) return this.ApprovalDate;
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
 
   public String getApproveUser()
   {
     return this.ApproveUser;
   }
 
   public void setApproveUser(String approveUser)
   {
     this.ApproveUser = approveUser;
   }
 
   public long getArticleID()
   {
     if (this.ArticleID == null) return 0L;
     return this.ArticleID.longValue();
   }
 
   public void setArticleID(long articleID)
   {
     this.ArticleID = new Long(articleID);
   }
 
   public void setArticleID(String articleID)
   {
     if (articleID == null) {
       this.ArticleID = null;
       return;
     }
     this.ArticleID = new Long(articleID);
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
   }
 
   public long getStatus()
   {
     if (this.Status == null) return 0L;
     return this.Status.longValue();
   }
 
   public void setStatus(long status)
   {
     this.Status = new Long(status);
   }
 
   public void setStatus(String status)
   {
     if (status == null) {
       this.Status = null;
       return;
     }
     this.Status = new Long(status);
   }
 
   public Date getApprovalDate()
   {
     return this.ApprovalDate;
   }
 
   public void setApprovalDate(Date approvalDate)
   {
     this.ApprovalDate = approvalDate;
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
 * Qualified Name:     com.zving.schema.BZCApprovalSchema
 * JD-Core Version:    0.5.3
 */