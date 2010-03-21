 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZDFavoriteSchema extends Schema
 {
   private String UserName;
   private Long DocID;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("UserName", 1, 0, 200, 0, true, true), 
     new SchemaColumn("DocID", 7, 1, 0, 0, true, true), 
     new SchemaColumn("AddUser", 1, 2, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 3, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 4, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 5, 0, 0, false, false) };
   public static final String _TableCode = "ZDFavorite";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDFavorite values(?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDFavorite set UserName=?,DocID=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where UserName=? and DocID=?";
   protected static final String _DeleteSQL = "delete from ZDFavorite  where UserName=? and DocID=?";
   protected static final String _FillAllSQL = "select * from ZDFavorite  where UserName=? and DocID=?";
 
   public ZDFavoriteSchema()
   {
     this.TableCode = "ZDFavorite";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDFavorite values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDFavorite set UserName=?,DocID=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where UserName=? and DocID=?";
     this.DeleteSQL = "delete from ZDFavorite  where UserName=? and DocID=?";
     this.FillAllSQL = "select * from ZDFavorite  where UserName=? and DocID=?";
     this.HasSetFlag = new boolean[6];
   }
 
   protected Schema newInstance() {
     return new ZDFavoriteSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDFavoriteSet();
   }
 
   public ZDFavoriteSet query() {
     return query(null, -1, -1);
   }
 
   public ZDFavoriteSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDFavoriteSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDFavoriteSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDFavoriteSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.UserName = ((String)v); return; }
     if (i == 1) { if (v == null) this.DocID = null; else this.DocID = new Long(v.toString()); return; }
     if (i == 2) { this.AddUser = ((String)v); return; }
     if (i == 3) { this.AddTime = ((Date)v); return; }
     if (i == 4) { this.ModifyUser = ((String)v); return; }
     if (i != 5) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.UserName;
     if (i == 1) return this.DocID;
     if (i == 2) return this.AddUser;
     if (i == 3) return this.AddTime;
     if (i == 4) return this.ModifyUser;
     if (i == 5) return this.ModifyTime;
     return null;
   }
 
   public String getUserName()
   {
     return this.UserName;
   }
 
   public void setUserName(String userName)
   {
     this.UserName = userName;
   }
 
   public long getDocID()
   {
     if (this.DocID == null) return 0L;
     return this.DocID.longValue();
   }
 
   public void setDocID(long docID)
   {
     this.DocID = new Long(docID);
   }
 
   public void setDocID(String docID)
   {
     if (docID == null) {
       this.DocID = null;
       return;
     }
     this.DocID = new Long(docID);
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
 * Qualified Name:     com.zving.schema.ZDFavoriteSchema
 * JD-Core Version:    0.5.3
 */