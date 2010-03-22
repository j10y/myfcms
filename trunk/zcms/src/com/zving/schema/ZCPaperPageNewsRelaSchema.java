 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCPaperPageNewsRelaSchema extends Schema
 {
   private Long PageID;
   private Long ArticleID;
   private String Coords;
   private String Link;
   private String Memo;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("PageID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("ArticleID", 7, 1, 0, 0, true, true), 
     new SchemaColumn("Coords", 1, 2, 100, 0, false, false), 
     new SchemaColumn("Link", 1, 3, 200, 0, false, false), 
     new SchemaColumn("Memo", 1, 4, 1000, 0, false, false), 
     new SchemaColumn("Prop1", 1, 5, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 6, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 7, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 8, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 9, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 10, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 11, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 12, 0, 0, false, false) };
   public static final String _TableCode = "ZCPaperPageNewsRela";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCPaperPageNewsRela values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCPaperPageNewsRela set PageID=?,ArticleID=?,Coords=?,Link=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where PageID=? and ArticleID=?";
   protected static final String _DeleteSQL = "delete from ZCPaperPageNewsRela  where PageID=? and ArticleID=?";
   protected static final String _FillAllSQL = "select * from ZCPaperPageNewsRela  where PageID=? and ArticleID=?";
 
   public ZCPaperPageNewsRelaSchema()
   {
     this.TableCode = "ZCPaperPageNewsRela";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCPaperPageNewsRela values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCPaperPageNewsRela set PageID=?,ArticleID=?,Coords=?,Link=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where PageID=? and ArticleID=?";
     this.DeleteSQL = "delete from ZCPaperPageNewsRela  where PageID=? and ArticleID=?";
     this.FillAllSQL = "select * from ZCPaperPageNewsRela  where PageID=? and ArticleID=?";
     this.HasSetFlag = new boolean[13];
   }
 
   protected Schema newInstance() {
     return new ZCPaperPageNewsRelaSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCPaperPageNewsRelaSet();
   }
 
   public ZCPaperPageNewsRelaSet query() {
     return query(null, -1, -1);
   }
 
   public ZCPaperPageNewsRelaSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCPaperPageNewsRelaSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCPaperPageNewsRelaSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCPaperPageNewsRelaSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.PageID = null; else this.PageID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.ArticleID = null; else this.ArticleID = new Long(v.toString()); return; }
     if (i == 2) { this.Coords = ((String)v); return; }
     if (i == 3) { this.Link = ((String)v); return; }
     if (i == 4) { this.Memo = ((String)v); return; }
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
     if (i == 0) return this.PageID;
     if (i == 1) return this.ArticleID;
     if (i == 2) return this.Coords;
     if (i == 3) return this.Link;
     if (i == 4) return this.Memo;
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
 
   public long getPageID()
   {
     if (this.PageID == null) return 0L;
     return this.PageID.longValue();
   }
 
   public void setPageID(long pageID)
   {
     this.PageID = new Long(pageID);
   }
 
   public void setPageID(String pageID)
   {
     if (pageID == null) {
       this.PageID = null;
       return;
     }
     this.PageID = new Long(pageID);
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
 
   public String getCoords()
   {
     return this.Coords;
   }
 
   public void setCoords(String coords)
   {
     this.Coords = coords;
   }
 
   public String getLink()
   {
     return this.Link;
   }
 
   public void setLink(String link)
   {
     this.Link = link;
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
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
 * Qualified Name:     com.zving.schema.ZCPaperPageNewsRelaSchema
 * JD-Core Version:    0.5.3
 */