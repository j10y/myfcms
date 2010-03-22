 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCArticlePageSchema extends Schema
 {
   private Long ID;
   private Long ArticleID;
   private Long PageNum;
   private String Content;
   private String FileName;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("ArticleID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("PageNum", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Content", 10, 3, 0, 0, false, false), 
     new SchemaColumn("FileName", 1, 4, 255, 0, false, false), 
     new SchemaColumn("Prop1", 1, 5, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 6, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 7, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 8, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 9, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 10, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 11, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 12, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 13, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 14, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 15, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 16, 50, 0, false, false) };
   public static final String _TableCode = "BZCArticlePage";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCArticlePage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCArticlePage set ID=?,ArticleID=?,PageNum=?,Content=?,FileName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCArticlePage  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCArticlePage  where ID=? and BackupNo=?";
 
   public BZCArticlePageSchema()
   {
     this.TableCode = "BZCArticlePage";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCArticlePage values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCArticlePage set ID=?,ArticleID=?,PageNum=?,Content=?,FileName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCArticlePage  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCArticlePage  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[17];
   }
 
   protected Schema newInstance() {
     return new BZCArticlePageSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCArticlePageSet();
   }
 
   public BZCArticlePageSet query() {
     return query(null, -1, -1);
   }
 
   public BZCArticlePageSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCArticlePageSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCArticlePageSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCArticlePageSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.ArticleID = null; else this.ArticleID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.PageNum = null; else this.PageNum = new Long(v.toString()); return; }
     if (i == 3) { this.Content = ((String)v); return; }
     if (i == 4) { this.FileName = ((String)v); return; }
     if (i == 5) { this.Prop1 = ((String)v); return; }
     if (i == 6) { this.Prop2 = ((String)v); return; }
     if (i == 7) { this.Prop3 = ((String)v); return; }
     if (i == 8) { this.Prop4 = ((String)v); return; }
     if (i == 9) { this.AddUser = ((String)v); return; }
     if (i == 10) { this.AddTime = ((Date)v); return; }
     if (i == 11) { this.ModifyUser = ((String)v); return; }
     if (i == 12) { this.ModifyTime = ((Date)v); return; }
     if (i == 13) { this.BackupNo = ((String)v); return; }
     if (i == 14) { this.BackupOperator = ((String)v); return; }
     if (i == 15) { this.BackupTime = ((Date)v); return; }
     if (i != 16) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.ArticleID;
     if (i == 2) return this.PageNum;
     if (i == 3) return this.Content;
     if (i == 4) return this.FileName;
     if (i == 5) return this.Prop1;
     if (i == 6) return this.Prop2;
     if (i == 7) return this.Prop3;
     if (i == 8) return this.Prop4;
     if (i == 9) return this.AddUser;
     if (i == 10) return this.AddTime;
     if (i == 11) return this.ModifyUser;
     if (i == 12) return this.ModifyTime;
     if (i == 13) return this.BackupNo;
     if (i == 14) return this.BackupOperator;
     if (i == 15) return this.BackupTime;
     if (i == 16) return this.BackupMemo;
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
 
   public long getPageNum()
   {
     if (this.PageNum == null) return 0L;
     return this.PageNum.longValue();
   }
 
   public void setPageNum(long pageNum)
   {
     this.PageNum = new Long(pageNum);
   }
 
   public void setPageNum(String pageNum)
   {
     if (pageNum == null) {
       this.PageNum = null;
       return;
     }
     this.PageNum = new Long(pageNum);
   }
 
   public String getContent()
   {
     return this.Content;
   }
 
   public void setContent(String content)
   {
     this.Content = content;
   }
 
   public String getFileName()
   {
     return this.FileName;
   }
 
   public void setFileName(String fileName)
   {
     this.FileName = fileName;
   }
 
   public String getProp1()
   {
     return this.Prop1;
   }
 
   public void setProp1(String prop1)
   {
     this.Prop1 = prop1;
   }
 
   public String getProp2()
   {
     return this.Prop2;
   }
 
   public void setProp2(String prop2)
   {
     this.Prop2 = prop2;
   }
 
   public String getProp3()
   {
     return this.Prop3;
   }
 
   public void setProp3(String prop3)
   {
     this.Prop3 = prop3;
   }
 
   public String getProp4()
   {
     return this.Prop4;
   }
 
   public void setProp4(String prop4)
   {
     this.Prop4 = prop4;
   }
 
   public String getAddUser()
   {
     return this.AddUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.AddUser = addUser;
   }
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 
   public String getModifyUser()
   {
     return this.ModifyUser;
   }
 
   public void setModifyUser(String modifyUser)
   {
     this.ModifyUser = modifyUser;
   }
 
   public Date getModifyTime()
   {
     return this.ModifyTime;
   }
 
   public void setModifyTime(Date modifyTime)
   {
     this.ModifyTime = modifyTime;
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
 * Qualified Name:     com.zving.schema.BZCArticlePageSchema
 * JD-Core Version:    0.5.3
 */