 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCArticlePageSchema extends Schema
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
     new SchemaColumn("ModifyTime", 0, 12, 0, 0, false, false) };
   public static final String _TableCode = "ZCArticlePage";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCArticlePage values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCArticlePage set ID=?,ArticleID=?,PageNum=?,Content=?,FileName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCArticlePage  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCArticlePage  where ID=?";
 
   public ZCArticlePageSchema()
   {
     this.TableCode = "ZCArticlePage";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCArticlePage values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCArticlePage set ID=?,ArticleID=?,PageNum=?,Content=?,FileName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCArticlePage  where ID=?";
     this.FillAllSQL = "select * from ZCArticlePage  where ID=?";
     this.HasSetFlag = new boolean[13];
   }
 
   protected Schema newInstance() {
     return new ZCArticlePageSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCArticlePageSet();
   }
 
   public ZCArticlePageSet query() {
     return query(null, -1, -1);
   }
 
   public ZCArticlePageSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCArticlePageSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCArticlePageSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCArticlePageSet)querySet(qb, pageSize, pageIndex));
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
     if (i != 12) return; this.ModifyTime = ((Date)v); return;
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
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCArticlePageSchema
 * JD-Core Version:    0.5.3
 */