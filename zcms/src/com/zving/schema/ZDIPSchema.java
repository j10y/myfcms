 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDIPSchema extends Schema
 {
   private Long IP1;
   private Long IP2;
   private String IP3;
   private String IP4;
   private String Address;
   private String Memo;
   private String DistrictCode;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("IP1", 7, 0, 0, 0, true, true), 
     new SchemaColumn("IP2", 7, 1, 0, 0, true, true), 
     new SchemaColumn("IP3", 1, 2, 30, 0, true, false), 
     new SchemaColumn("IP4", 1, 3, 30, 0, false, false), 
     new SchemaColumn("Address", 1, 4, 200, 0, false, false), 
     new SchemaColumn("Memo", 1, 5, 200, 0, false, false), 
     new SchemaColumn("DistrictCode", 1, 6, 10, 0, false, false) };
   public static final String _TableCode = "ZDIP";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDIP values(?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDIP set IP1=?,IP2=?,IP3=?,IP4=?,Address=?,Memo=?,DistrictCode=? where IP1=? and IP2=?";
   protected static final String _DeleteSQL = "delete from ZDIP  where IP1=? and IP2=?";
   protected static final String _FillAllSQL = "select * from ZDIP  where IP1=? and IP2=?";
 
   public ZDIPSchema()
   {
     this.TableCode = "ZDIP";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDIP values(?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDIP set IP1=?,IP2=?,IP3=?,IP4=?,Address=?,Memo=?,DistrictCode=? where IP1=? and IP2=?";
     this.DeleteSQL = "delete from ZDIP  where IP1=? and IP2=?";
     this.FillAllSQL = "select * from ZDIP  where IP1=? and IP2=?";
     this.HasSetFlag = new boolean[7];
   }
 
   protected Schema newInstance() {
     return new ZDIPSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDIPSet();
   }
 
   public ZDIPSet query() {
     return query(null, -1, -1);
   }
 
   public ZDIPSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDIPSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDIPSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDIPSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.IP1 = null; else this.IP1 = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.IP2 = null; else this.IP2 = new Long(v.toString()); return; }
     if (i == 2) { this.IP3 = ((String)v); return; }
     if (i == 3) { this.IP4 = ((String)v); return; }
     if (i == 4) { this.Address = ((String)v); return; }
     if (i == 5) { this.Memo = ((String)v); return; }
     if (i != 6) return; this.DistrictCode = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.IP1;
     if (i == 1) return this.IP2;
     if (i == 2) return this.IP3;
     if (i == 3) return this.IP4;
     if (i == 4) return this.Address;
     if (i == 5) return this.Memo;
     if (i == 6) return this.DistrictCode;
     return null;
   }
 
   public long getIP1()
   {
     if (this.IP1 == null) return 0L;
     return this.IP1.longValue();
   }
 
   public void setIP1(long iP1)
   {
     this.IP1 = new Long(iP1);
   }
 
   public void setIP1(String iP1)
   {
     if (iP1 == null) {
       this.IP1 = null;
       return;
     }
     this.IP1 = new Long(iP1);
   }
 
   public long getIP2()
   {
     if (this.IP2 == null) return 0L;
     return this.IP2.longValue();
   }
 
   public void setIP2(long iP2)
   {
     this.IP2 = new Long(iP2);
   }
 
   public void setIP2(String iP2)
   {
     if (iP2 == null) {
       this.IP2 = null;
       return;
     }
     this.IP2 = new Long(iP2);
   }
 
   public String getIP3()
   {
     return this.IP3;
   }
 
   public void setIP3(String iP3)
   {
     this.IP3 = iP3;
   }
 
   public String getIP4()
   {
     return this.IP4;
   }
 
   public void setIP4(String iP4)
   {
     this.IP4 = iP4;
   }
 
   public String getAddress()
   {
     return this.Address;
   }
 
   public void setAddress(String address)
   {
     this.Address = address;
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
   }
 
   public String getDistrictCode()
   {
     return this.DistrictCode;
   }
 
   public void setDistrictCode(String districtCode)
   {
     this.DistrictCode = districtCode;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDIPSchema
 * JD-Core Version:    0.5.3
 */