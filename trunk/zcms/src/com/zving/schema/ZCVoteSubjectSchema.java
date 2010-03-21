 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCVoteSubjectSchema extends Schema
 {
   private Long ID;
   private Long VoteID;
   private String Type;
   private String Subject;
   private String Prop1;
   private String Prop2;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("VoteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Type", 1, 2, 1, 0, true, false), 
     new SchemaColumn("Subject", 1, 3, 100, 0, true, false), 
     new SchemaColumn("Prop1", 1, 4, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 5, 50, 0, false, false) };
   public static final String _TableCode = "ZCVoteSubject";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCVoteSubject values(?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCVoteSubject set ID=?,VoteID=?,Type=?,Subject=?,Prop1=?,Prop2=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCVoteSubject  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCVoteSubject  where ID=?";
 
   public ZCVoteSubjectSchema()
   {
     this.TableCode = "ZCVoteSubject";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCVoteSubject values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCVoteSubject set ID=?,VoteID=?,Type=?,Subject=?,Prop1=?,Prop2=? where ID=?";
     this.DeleteSQL = "delete from ZCVoteSubject  where ID=?";
     this.FillAllSQL = "select * from ZCVoteSubject  where ID=?";
     this.HasSetFlag = new boolean[6];
   }
 
   protected Schema newInstance() {
     return new ZCVoteSubjectSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCVoteSubjectSet();
   }
 
   public ZCVoteSubjectSet query() {
     return query(null, -1, -1);
   }
 
   public ZCVoteSubjectSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCVoteSubjectSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCVoteSubjectSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCVoteSubjectSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.VoteID = null; else this.VoteID = new Long(v.toString()); return; }
     if (i == 2) { this.Type = ((String)v); return; }
     if (i == 3) { this.Subject = ((String)v); return; }
     if (i == 4) { this.Prop1 = ((String)v); return; }
     if (i != 5) return; this.Prop2 = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.VoteID;
     if (i == 2) return this.Type;
     if (i == 3) return this.Subject;
     if (i == 4) return this.Prop1;
     if (i == 5) return this.Prop2;
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
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
   }
 
   public String getSubject()
   {
     return this.Subject;
   }
 
   public void setSubject(String subject)
   {
     this.Subject = subject;
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
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVoteSubjectSchema
 * JD-Core Version:    0.5.3
 */