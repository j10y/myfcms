 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDMaxNoSchema extends Schema
 {
   private String NoType;
   private String NoSubType;
   private Long MaxValue;
   private Long Length;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("NoType", 1, 0, 20, 0, true, true), 
     new SchemaColumn("NoSubType", 1, 1, 255, 0, true, true), 
     new SchemaColumn("MaxValue", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Length", 7, 3, 0, 0, false, false) };
   public static final String _TableCode = "ZDMaxNo";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDMaxNo values(?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDMaxNo set NoType=?,NoSubType=?,MaxValue=?,Length=? where NoType=? and NoSubType=?";
   protected static final String _DeleteSQL = "delete from ZDMaxNo  where NoType=? and NoSubType=?";
   protected static final String _FillAllSQL = "select * from ZDMaxNo  where NoType=? and NoSubType=?";
 
   public ZDMaxNoSchema()
   {
     this.TableCode = "ZDMaxNo";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDMaxNo values(?,?,?,?)";
     this.UpdateAllSQL = "update ZDMaxNo set NoType=?,NoSubType=?,MaxValue=?,Length=? where NoType=? and NoSubType=?";
     this.DeleteSQL = "delete from ZDMaxNo  where NoType=? and NoSubType=?";
     this.FillAllSQL = "select * from ZDMaxNo  where NoType=? and NoSubType=?";
     this.HasSetFlag = new boolean[4];
   }
 
   protected Schema newInstance() {
     return new ZDMaxNoSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDMaxNoSet();
   }
 
   public ZDMaxNoSet query() {
     return query(null, -1, -1);
   }
 
   public ZDMaxNoSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDMaxNoSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDMaxNoSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDMaxNoSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.NoType = ((String)v); return; }
     if (i == 1) { this.NoSubType = ((String)v); return; }
     if (i == 2) { if (v == null) this.MaxValue = null; else this.MaxValue = new Long(v.toString()); return; }
     if (i != 3) return; if (v == null) this.Length = null; else this.Length = new Long(v.toString()); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.NoType;
     if (i == 1) return this.NoSubType;
     if (i == 2) return this.MaxValue;
     if (i == 3) return this.Length;
     return null;
   }
 
   public String getNoType()
   {
     return this.NoType;
   }
 
   public void setNoType(String noType)
   {
     this.NoType = noType;
   }
 
   public String getNoSubType()
   {
     return this.NoSubType;
   }
 
   public void setNoSubType(String noSubType)
   {
     this.NoSubType = noSubType;
   }
 
   public long getMaxValue()
   {
     if (this.MaxValue == null) return 0L;
     return this.MaxValue.longValue();
   }
 
   public void setMaxValue(long maxValue)
   {
     this.MaxValue = new Long(maxValue);
   }
 
   public void setMaxValue(String maxValue)
   {
     if (maxValue == null) {
       this.MaxValue = null;
       return;
     }
     this.MaxValue = new Long(maxValue);
   }
 
   public long getLength()
   {
     if (this.Length == null) return 0L;
     return this.Length.longValue();
   }
 
   public void setLength(long length)
   {
     this.Length = new Long(length);
   }
 
   public void setLength(String length)
   {
     if (length == null) {
       this.Length = null;
       return;
     }
     this.Length = new Long(length);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDMaxNoSchema
 * JD-Core Version:    0.5.3
 */