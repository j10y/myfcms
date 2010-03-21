 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 
 public class ZCVideoRelaSchema extends Schema
 {
   private Long ID;
   private Long RelaID;
   private String RelaType;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("RelaID", 7, 1, 0, 0, true, true), 
     new SchemaColumn("RelaType", 1, 2, 20, 0, true, true) };
   public static final String _TableCode = "ZCVideoRela";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCVideoRela values(?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCVideoRela set ID=?,RelaID=?,RelaType=? where ID=? and RelaID=? and RelaType=?";
   protected static final String _DeleteSQL = "delete from ZCVideoRela  where ID=? and RelaID=? and RelaType=?";
   protected static final String _FillAllSQL = "select * from ZCVideoRela  where ID=? and RelaID=? and RelaType=?";
 
   public ZCVideoRelaSchema()
   {
     this.TableCode = "ZCVideoRela";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCVideoRela values(?,?,?)";
     this.UpdateAllSQL = "update ZCVideoRela set ID=?,RelaID=?,RelaType=? where ID=? and RelaID=? and RelaType=?";
     this.DeleteSQL = "delete from ZCVideoRela  where ID=? and RelaID=? and RelaType=?";
     this.FillAllSQL = "select * from ZCVideoRela  where ID=? and RelaID=? and RelaType=?";
     this.HasSetFlag = new boolean[3];
   }
 
   protected Schema newInstance() {
     return new ZCVideoRelaSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCVideoRelaSet();
   }
 
   public ZCVideoRelaSet query() {
     return query(null, -1, -1);
   }
 
   public ZCVideoRelaSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCVideoRelaSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCVideoRelaSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCVideoRelaSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.RelaID = null; else this.RelaID = new Long(v.toString()); return; }
     if (i != 2) return; this.RelaType = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.RelaID;
     if (i == 2) return this.RelaType;
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
 
   public long getRelaID()
   {
     if (this.RelaID == null) return 0L;
     return this.RelaID.longValue();
   }
 
   public void setRelaID(long relaID)
   {
     this.RelaID = new Long(relaID);
   }
 
   public void setRelaID(String relaID)
   {
     if (relaID == null) {
       this.RelaID = null;
       return;
     }
     this.RelaID = new Long(relaID);
   }
 
   public String getRelaType()
   {
     return this.RelaType;
   }
 
   public void setRelaType(String relaType)
   {
     this.RelaType = relaType;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCVideoRelaSchema
 * JD-Core Version:    0.5.3
 */