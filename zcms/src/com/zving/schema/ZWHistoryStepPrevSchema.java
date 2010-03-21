 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 
 public class ZWHistoryStepPrevSchema extends Schema
 {
   private Long ID;
   private Long PreviousID;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("PreviousID", 7, 1, 0, 0, true, true) };
   public static final String _TableCode = "ZWHistoryStepPrev";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZWHistoryStepPrev values(?,?)";
   protected static final String _UpdateAllSQL = "update ZWHistoryStepPrev set ID=?,PreviousID=? where ID=? and PreviousID=?";
   protected static final String _DeleteSQL = "delete from ZWHistoryStepPrev  where ID=? and PreviousID=?";
   protected static final String _FillAllSQL = "select * from ZWHistoryStepPrev  where ID=? and PreviousID=?";
 
   public ZWHistoryStepPrevSchema()
   {
     this.TableCode = "ZWHistoryStepPrev";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZWHistoryStepPrev values(?,?)";
     this.UpdateAllSQL = "update ZWHistoryStepPrev set ID=?,PreviousID=? where ID=? and PreviousID=?";
     this.DeleteSQL = "delete from ZWHistoryStepPrev  where ID=? and PreviousID=?";
     this.FillAllSQL = "select * from ZWHistoryStepPrev  where ID=? and PreviousID=?";
     this.HasSetFlag = new boolean[2];
   }
 
   protected Schema newInstance() {
     return new ZWHistoryStepPrevSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZWHistoryStepPrevSet();
   }
 
   public ZWHistoryStepPrevSet query() {
     return query(null, -1, -1);
   }
 
   public ZWHistoryStepPrevSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZWHistoryStepPrevSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZWHistoryStepPrevSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZWHistoryStepPrevSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i != 1) return; if (v == null) this.PreviousID = null; else this.PreviousID = new Long(v.toString()); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.PreviousID;
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
 
   public long getPreviousID()
   {
     if (this.PreviousID == null) return 0L;
     return this.PreviousID.longValue();
   }
 
   public void setPreviousID(long previousID)
   {
     this.PreviousID = new Long(previousID);
   }
 
   public void setPreviousID(String previousID)
   {
     if (previousID == null) {
       this.PreviousID = null;
       return;
     }
     this.PreviousID = new Long(previousID);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZWHistoryStepPrevSchema
 * JD-Core Version:    0.5.3
 */