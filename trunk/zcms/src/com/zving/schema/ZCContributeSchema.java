 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCContributeSchema extends Schema
 {
   private Long ID;
   private Long AuthorID;
   private Long AritcleID;
   private String Memo;
   private Date ContributeDate;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("AuthorID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("AritcleID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Memo", 1, 3, 200, 0, false, false), 
     new SchemaColumn("ContributeDate", 0, 4, 0, 0, false, false), 
     new SchemaColumn("Prop1", 1, 5, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 6, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 7, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 8, 50, 0, false, false) };
   public static final String _TableCode = "ZCContribute";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCContribute values(?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCContribute set ID=?,AuthorID=?,AritcleID=?,Memo=?,ContributeDate=?,Prop1=?,Prop2=?,Prop3=?,Prop4=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCContribute  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCContribute  where ID=?";
 
   public ZCContributeSchema()
   {
     this.TableCode = "ZCContribute";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCContribute values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCContribute set ID=?,AuthorID=?,AritcleID=?,Memo=?,ContributeDate=?,Prop1=?,Prop2=?,Prop3=?,Prop4=? where ID=?";
     this.DeleteSQL = "delete from ZCContribute  where ID=?";
     this.FillAllSQL = "select * from ZCContribute  where ID=?";
     this.HasSetFlag = new boolean[9];
   }
 
   protected Schema newInstance() {
     return new ZCContributeSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCContributeSet();
   }
 
   public ZCContributeSet query() {
     return query(null, -1, -1);
   }
 
   public ZCContributeSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCContributeSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCContributeSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCContributeSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.AuthorID = null; else this.AuthorID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.AritcleID = null; else this.AritcleID = new Long(v.toString()); return; }
     if (i == 3) { this.Memo = ((String)v); return; }
     if (i == 4) { this.ContributeDate = ((Date)v); return; }
     if (i == 5) { this.Prop1 = ((String)v); return; }
     if (i == 6) { this.Prop2 = ((String)v); return; }
     if (i == 7) { this.Prop3 = ((String)v); return; }
     if (i != 8) return; this.Prop4 = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.AuthorID;
     if (i == 2) return this.AritcleID;
     if (i == 3) return this.Memo;
     if (i == 4) return this.ContributeDate;
     if (i == 5) return this.Prop1;
     if (i == 6) return this.Prop2;
     if (i == 7) return this.Prop3;
     if (i == 8) return this.Prop4;
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
 
   public long getAuthorID()
   {
     if (this.AuthorID == null) return 0L;
     return this.AuthorID.longValue();
   }
 
   public void setAuthorID(long authorID)
   {
     this.AuthorID = new Long(authorID);
   }
 
   public void setAuthorID(String authorID)
   {
     if (authorID == null) {
       this.AuthorID = null;
       return;
     }
     this.AuthorID = new Long(authorID);
   }
 
   public long getAritcleID()
   {
     if (this.AritcleID == null) return 0L;
     return this.AritcleID.longValue();
   }
 
   public void setAritcleID(long aritcleID)
   {
     this.AritcleID = new Long(aritcleID);
   }
 
   public void setAritcleID(String aritcleID)
   {
     if (aritcleID == null) {
       this.AritcleID = null;
       return;
     }
     this.AritcleID = new Long(aritcleID);
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
   }
 
   public Date getContributeDate()
   {
     return this.ContributeDate;
   }
 
   public void setContributeDate(Date contributeDate)
   {
     this.ContributeDate = contributeDate;
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
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCContributeSchema
 * JD-Core Version:    0.5.3
 */