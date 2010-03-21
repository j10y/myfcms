 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCVoteItemSchema extends Schema
 {
   private Long ID;
   private Long SubjectID;
   private Long VoteID;
   private String Item;
   private Long Score;
   private String ItemType;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SubjectID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("VoteID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Item", 1, 3, 100, 0, true, false), 
     new SchemaColumn("Score", 7, 4, 0, 0, true, false), 
     new SchemaColumn("ItemType", 1, 5, 1, 0, true, false) };
   public static final String _TableCode = "ZCVoteItem";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCVoteItem values(?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCVoteItem set ID=?,SubjectID=?,VoteID=?,Item=?,Score=?,ItemType=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCVoteItem  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCVoteItem  where ID=?";
 
   public ZCVoteItemSchema()
   {
     this.TableCode = "ZCVoteItem";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCVoteItem values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCVoteItem set ID=?,SubjectID=?,VoteID=?,Item=?,Score=?,ItemType=? where ID=?";
     this.DeleteSQL = "delete from ZCVoteItem  where ID=?";
     this.FillAllSQL = "select * from ZCVoteItem  where ID=?";
     this.HasSetFlag = new boolean[6];
   }
 
   protected Schema newInstance() {
     return new ZCVoteItemSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCVoteItemSet();
   }
 
   public ZCVoteItemSet query() {
     return query(null, -1, -1);
   }
 
   public ZCVoteItemSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCVoteItemSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCVoteItemSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCVoteItemSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SubjectID = null; else this.SubjectID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.VoteID = null; else this.VoteID = new Long(v.toString()); return; }
     if (i == 3) { this.Item = ((String)v); return; }
     if (i == 4) { if (v == null) this.Score = null; else this.Score = new Long(v.toString()); return; }
     if (i != 5) return; this.ItemType = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SubjectID;
     if (i == 2) return this.VoteID;
     if (i == 3) return this.Item;
     if (i == 4) return this.Score;
     if (i == 5) return this.ItemType;
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
 
   public long getSubjectID()
   {
     if (this.SubjectID == null) return 0L;
     return this.SubjectID.longValue();
   }
 
   public void setSubjectID(long subjectID)
   {
     this.SubjectID = new Long(subjectID);
   }
 
   public void setSubjectID(String subjectID)
   {
     if (subjectID == null) {
       this.SubjectID = null;
       return;
     }
     this.SubjectID = new Long(subjectID);
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
 
   public String getItem()
   {
     return this.Item;
   }
 
   public void setItem(String item)
   {
     this.Item = item;
   }
 
   public long getScore()
   {
     if (this.Score == null) return 0L;
     return this.Score.longValue();
   }
 
   public void setScore(long score)
   {
     this.Score = new Long(score);
   }
 
   public void setScore(String score)
   {
     if (score == null) {
       this.Score = null;
       return;
     }
     this.Score = new Long(score);
   }
 
   public String getItemType()
   {
     return this.ItemType;
   }
 
   public void setItemType(String itemType)
   {
     this.ItemType = itemType;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVoteItemSchema
 * JD-Core Version:    0.5.3
 */