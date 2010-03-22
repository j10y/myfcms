 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCVoteLogSchema extends Schema
 {
   private Long ID;
   private Long VoteID;
   private String IP;
   private String Result;
   private String Prop1;
   private String Prop2;
   private String addUser;
   private Date addTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("VoteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("IP", 1, 2, 20, 0, true, false), 
     new SchemaColumn("Result", 1, 3, 2000, 0, false, false), 
     new SchemaColumn("Prop1", 1, 4, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 5, 50, 0, false, false), 
     new SchemaColumn("addUser", 1, 6, 50, 0, false, false), 
     new SchemaColumn("addTime", 0, 7, 0, 0, true, false) };
   public static final String _TableCode = "ZCVoteLog";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCVoteLog values(?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCVoteLog set ID=?,VoteID=?,IP=?,Result=?,Prop1=?,Prop2=?,addUser=?,addTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCVoteLog  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCVoteLog  where ID=?";
 
   public ZCVoteLogSchema()
   {
     this.TableCode = "ZCVoteLog";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCVoteLog values(?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCVoteLog set ID=?,VoteID=?,IP=?,Result=?,Prop1=?,Prop2=?,addUser=?,addTime=? where ID=?";
     this.DeleteSQL = "delete from ZCVoteLog  where ID=?";
     this.FillAllSQL = "select * from ZCVoteLog  where ID=?";
     this.HasSetFlag = new boolean[8];
   }
 
   protected Schema newInstance() {
     return new ZCVoteLogSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCVoteLogSet();
   }
 
   public ZCVoteLogSet query() {
     return query(null, -1, -1);
   }
 
   public ZCVoteLogSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCVoteLogSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCVoteLogSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCVoteLogSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.VoteID = null; else this.VoteID = new Long(v.toString()); return; }
     if (i == 2) { this.IP = ((String)v); return; }
     if (i == 3) { this.Result = ((String)v); return; }
     if (i == 4) { this.Prop1 = ((String)v); return; }
     if (i == 5) { this.Prop2 = ((String)v); return; }
     if (i == 6) { this.addUser = ((String)v); return; }
     if (i != 7) return; this.addTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.VoteID;
     if (i == 2) return this.IP;
     if (i == 3) return this.Result;
     if (i == 4) return this.Prop1;
     if (i == 5) return this.Prop2;
     if (i == 6) return this.addUser;
     if (i == 7) return this.addTime;
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
 
   public long getVoteID()
   {
     if (this.VoteID == null) return 0L;
     return this.VoteID.longValue();
   }
 
   public void setVoteID(long voteID)
   {
     this.VoteID = new Long(voteID);
   }
 
   public void setVoteID(String voteID)
   {
     if (voteID == null) {
       this.VoteID = null;
       return;
     }
     this.VoteID = new Long(voteID);
   }
 
   public String getIP()
   {
     return this.IP;
   }
 
   public void setIP(String iP)
   {
     this.IP = iP;
   }
 
   public String getResult()
   {
     return this.Result;
   }
 
   public void setResult(String result)
   {
     this.Result = result;
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
     return this.addUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.addUser = addUser;
   }
 
   public Date getAddTime()
   {
     return this.addTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.addTime = addTime;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVoteLogSchema
 * JD-Core Version:    0.5.3
 */