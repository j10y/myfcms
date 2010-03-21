 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCApprovalSchema extends Schema
 {
   private Long ID;
   private String ApproveUser;
   private Long ArticleID;
   private String Memo;
   private Long Status;
   private Date ApprovalDate;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("ApproveUser", 1, 1, 200, 0, true, false), 
     new SchemaColumn("ArticleID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Memo", 1, 3, 200, 0, false, false), 
     new SchemaColumn("Status", 7, 4, 0, 0, false, false), 
     new SchemaColumn("ApprovalDate", 0, 5, 0, 0, false, false) };
   public static final String _TableCode = "ZCApproval";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCApproval values(?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCApproval set ID=?,ApproveUser=?,ArticleID=?,Memo=?,Status=?,ApprovalDate=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCApproval  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCApproval  where ID=?";
 
   public ZCApprovalSchema()
   {
     this.TableCode = "ZCApproval";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCApproval values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCApproval set ID=?,ApproveUser=?,ArticleID=?,Memo=?,Status=?,ApprovalDate=? where ID=?";
     this.DeleteSQL = "delete from ZCApproval  where ID=?";
     this.FillAllSQL = "select * from ZCApproval  where ID=?";
     this.HasSetFlag = new boolean[6];
   }
 
   protected Schema newInstance() {
     return new ZCApprovalSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCApprovalSet();
   }
 
   public ZCApprovalSet query() {
     return query(null, -1, -1);
   }
 
   public ZCApprovalSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCApprovalSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCApprovalSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCApprovalSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.ApproveUser = ((String)v); return; }
     if (i == 2) { if (v == null) this.ArticleID = null; else this.ArticleID = new Long(v.toString()); return; }
     if (i == 3) { this.Memo = ((String)v); return; }
     if (i == 4) { if (v == null) this.Status = null; else this.Status = new Long(v.toString()); return; }
     if (i != 5) return; this.ApprovalDate = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.ApproveUser;
     if (i == 2) return this.ArticleID;
     if (i == 3) return this.Memo;
     if (i == 4) return this.Status;
     if (i == 5) return this.ApprovalDate;
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
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCApprovalSchema
 * JD-Core Version:    0.5.3
 */