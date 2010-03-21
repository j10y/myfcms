 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCArticleLogSchema extends Schema
 {
   private Long ID;
   private Long ArticleID;
   private String Action;
   private String ActionDetail;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String AddUser;
   private Date AddTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("ArticleID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Action", 1, 2, 200, 0, true, false), 
     new SchemaColumn("ActionDetail", 1, 3, 200, 0, false, false), 
     new SchemaColumn("Prop1", 1, 4, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 5, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 6, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 7, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 8, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 9, 0, 0, true, false) };
   public static final String _TableCode = "ZCArticleLog";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCArticleLog values(?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCArticleLog set ID=?,ArticleID=?,Action=?,ActionDetail=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCArticleLog  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCArticleLog  where ID=?";
 
   public ZCArticleLogSchema()
   {
     this.TableCode = "ZCArticleLog";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCArticleLog values(?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCArticleLog set ID=?,ArticleID=?,Action=?,ActionDetail=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=? where ID=?";
     this.DeleteSQL = "delete from ZCArticleLog  where ID=?";
     this.FillAllSQL = "select * from ZCArticleLog  where ID=?";
     this.HasSetFlag = new boolean[10];
   }
 
   protected Schema newInstance() {
     return new ZCArticleLogSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCArticleLogSet();
   }
 
   public ZCArticleLogSet query() {
     return query(null, -1, -1);
   }
 
   public ZCArticleLogSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCArticleLogSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCArticleLogSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCArticleLogSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.ArticleID = null; else this.ArticleID = new Long(v.toString()); return; }
     if (i == 2) { this.Action = ((String)v); return; }
     if (i == 3) { this.ActionDetail = ((String)v); return; }
     if (i == 4) { this.Prop1 = ((String)v); return; }
     if (i == 5) { this.Prop2 = ((String)v); return; }
     if (i == 6) { this.Prop3 = ((String)v); return; }
     if (i == 7) { this.Prop4 = ((String)v); return; }
     if (i == 8) { this.AddUser = ((String)v); return; }
     if (i != 9) return; this.AddTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.ArticleID;
     if (i == 2) return this.Action;
     if (i == 3) return this.ActionDetail;
     if (i == 4) return this.Prop1;
     if (i == 5) return this.Prop2;
     if (i == 6) return this.Prop3;
     if (i == 7) return this.Prop4;
     if (i == 8) return this.AddUser;
     if (i == 9) return this.AddTime;
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
 
   public String getAction()
   {
     return this.Action;
   }
 
   public void setAction(String action)
   {
     this.Action = action;
   }
 
   public String getActionDetail()
   {
     return this.ActionDetail;
   }
 
   public void setActionDetail(String actionDetail)
   {
     this.ActionDetail = actionDetail;
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
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCArticleLogSchema
 * JD-Core Version:    0.5.3
 */