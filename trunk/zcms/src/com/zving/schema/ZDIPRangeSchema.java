 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDIPRangeSchema extends Schema
 {
   private String DistrictCode;
   private String IPRanges;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("DistrictCode", 1, 0, 10, 0, true, true), 
     new SchemaColumn("IPRanges", 10, 1, 0, 0, true, false) };
   public static final String _TableCode = "ZDIPRange";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDIPRange values(?,?)";
   protected static final String _UpdateAllSQL = "update ZDIPRange set DistrictCode=?,IPRanges=? where DistrictCode=?";
   protected static final String _DeleteSQL = "delete from ZDIPRange  where DistrictCode=?";
   protected static final String _FillAllSQL = "select * from ZDIPRange  where DistrictCode=?";
 
   public ZDIPRangeSchema()
   {
     this.TableCode = "ZDIPRange";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDIPRange values(?,?)";
     this.UpdateAllSQL = "update ZDIPRange set DistrictCode=?,IPRanges=? where DistrictCode=?";
     this.DeleteSQL = "delete from ZDIPRange  where DistrictCode=?";
     this.FillAllSQL = "select * from ZDIPRange  where DistrictCode=?";
     this.HasSetFlag = new boolean[2];
   }
 
   protected Schema newInstance() {
     return new ZDIPRangeSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDIPRangeSet();
   }
 
   public ZDIPRangeSet query() {
     return query(null, -1, -1);
   }
 
   public ZDIPRangeSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDIPRangeSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDIPRangeSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDIPRangeSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.DistrictCode = ((String)v); return; }
     if (i != 1) return; this.IPRanges = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.DistrictCode;
     if (i == 1) return this.IPRanges;
     return null;
   }
 
   public String getDistrictCode()
   {
     return this.DistrictCode;
   }
 
   public void setDistrictCode(String districtCode)
   {
     this.DistrictCode = districtCode;
   }
 
   public String getIPRanges()
   {
     return this.IPRanges;
   }
 
   public void setIPRanges(String iPRanges)
   {
     this.IPRanges = iPRanges;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDIPRangeSchema
 * JD-Core Version:    0.5.3
 */