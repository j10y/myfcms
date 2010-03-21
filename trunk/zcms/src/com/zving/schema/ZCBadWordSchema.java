 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCBadWordSchema extends Schema
 {
   private Long ID;
   private Long TreeLevel;
   private String Word;
   private String ReplaceWord;
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
     new SchemaColumn("TreeLevel", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Word", 1, 2, 50, 0, true, false), 
     new SchemaColumn("ReplaceWord", 1, 3, 50, 0, false, false), 
     new SchemaColumn("Prop1", 1, 4, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 5, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 6, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 7, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 8, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 9, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 10, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 11, 0, 0, false, false) };
   public static final String _TableCode = "ZCBadWord";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCBadWord values(?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCBadWord set ID=?,TreeLevel=?,Word=?,ReplaceWord=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCBadWord  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCBadWord  where ID=?";
 
   public ZCBadWordSchema()
   {
     this.TableCode = "ZCBadWord";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCBadWord values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCBadWord set ID=?,TreeLevel=?,Word=?,ReplaceWord=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCBadWord  where ID=?";
     this.FillAllSQL = "select * from ZCBadWord  where ID=?";
     this.HasSetFlag = new boolean[12];
   }
 
   protected Schema newInstance() {
     return new ZCBadWordSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCBadWordSet();
   }
 
   public ZCBadWordSet query() {
     return query(null, -1, -1);
   }
 
   public ZCBadWordSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCBadWordSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCBadWordSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCBadWordSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.TreeLevel = null; else this.TreeLevel = new Long(v.toString()); return; }
     if (i == 2) { this.Word = ((String)v); return; }
     if (i == 3) { this.ReplaceWord = ((String)v); return; }
     if (i == 4) { this.Prop1 = ((String)v); return; }
     if (i == 5) { this.Prop2 = ((String)v); return; }
     if (i == 6) { this.Prop3 = ((String)v); return; }
     if (i == 7) { this.Prop4 = ((String)v); return; }
     if (i == 8) { this.AddUser = ((String)v); return; }
     if (i == 9) { this.AddTime = ((Date)v); return; }
     if (i == 10) { this.ModifyUser = ((String)v); return; }
     if (i != 11) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.TreeLevel;
     if (i == 2) return this.Word;
     if (i == 3) return this.ReplaceWord;
     if (i == 4) return this.Prop1;
     if (i == 5) return this.Prop2;
     if (i == 6) return this.Prop3;
     if (i == 7) return this.Prop4;
     if (i == 8) return this.AddUser;
     if (i == 9) return this.AddTime;
     if (i == 10) return this.ModifyUser;
     if (i == 11) return this.ModifyTime;
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
 
   public long getTreeLevel()
   {
     if (this.TreeLevel == null) return 0L;
     return this.TreeLevel.longValue();
   }
 
   public void setTreeLevel(long treeLevel)
   {
     this.TreeLevel = new Long(treeLevel);
   }
 
   public void setTreeLevel(String treeLevel)
   {
     if (treeLevel == null) {
       this.TreeLevel = null;
       return;
     }
     this.TreeLevel = new Long(treeLevel);
   }
 
   public String getWord()
   {
     return this.Word;
   }
 
   public void setWord(String word)
   {
     this.Word = word;
   }
 
   public String getReplaceWord()
   {
     return this.ReplaceWord;
   }
 
   public void setReplaceWord(String replaceWord)
   {
     this.ReplaceWord = replaceWord;
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
 * Qualified Name:     com.zving.schema.ZCBadWordSchema
 * JD-Core Version:    0.5.3
 */