 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCNotesSchema extends Schema
 {
   private Long ID;
   private String Title;
   private String Content;
   private Date NoteTime;
   private String Prop1;
   private String Prop2;
   private String AddUser;
   private Date AddTime;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("Title", 1, 1, 100, 0, true, false), 
     new SchemaColumn("Content", 1, 2, 1000, 0, true, false), 
     new SchemaColumn("NoteTime", 0, 3, 0, 0, true, false), 
     new SchemaColumn("Prop1", 1, 4, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 5, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 6, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 7, 0, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 8, 0, 0, false, false) };
   public static final String _TableCode = "ZCNotes";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCNotes values(?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCNotes set ID=?,Title=?,Content=?,NoteTime=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCNotes  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCNotes  where ID=?";
 
   public ZCNotesSchema()
   {
     this.TableCode = "ZCNotes";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCNotes values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCNotes set ID=?,Title=?,Content=?,NoteTime=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCNotes  where ID=?";
     this.FillAllSQL = "select * from ZCNotes  where ID=?";
     this.HasSetFlag = new boolean[9];
   }
 
   protected Schema newInstance() {
     return new ZCNotesSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCNotesSet();
   }
 
   public ZCNotesSet query() {
     return query(null, -1, -1);
   }
 
   public ZCNotesSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCNotesSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCNotesSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCNotesSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Title = ((String)v); return; }
     if (i == 2) { this.Content = ((String)v); return; }
     if (i == 3) { this.NoteTime = ((Date)v); return; }
     if (i == 4) { this.Prop1 = ((String)v); return; }
     if (i == 5) { this.Prop2 = ((String)v); return; }
     if (i == 6) { this.AddUser = ((String)v); return; }
     if (i == 7) { this.AddTime = ((Date)v); return; }
     if (i != 8) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Title;
     if (i == 2) return this.Content;
     if (i == 3) return this.NoteTime;
     if (i == 4) return this.Prop1;
     if (i == 5) return this.Prop2;
     if (i == 6) return this.AddUser;
     if (i == 7) return this.AddTime;
     if (i == 8) return this.ModifyTime;
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
 
   public String getTitle()
   {
     return this.Title;
   }
 
   public void setTitle(String title)
   {
     this.Title = title;
   }
 
   public String getContent()
   {
     return this.Content;
   }
 
   public void setContent(String content)
   {
     this.Content = content;
   }
 
   public Date getNoteTime()
   {
     return this.NoteTime;
   }
 
   public void setNoteTime(Date noteTime)
   {
     this.NoteTime = noteTime;
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
 * Qualified Name:     com.zving.schema.ZCNotesSchema
 * JD-Core Version:    0.5.3
 */