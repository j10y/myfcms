 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDColumnValueSchema extends Schema
 {
   private Long ID;
   private Long ColumnID;
   private String ColumnCode;
   private String TextValue;
   private String RelaType;
   private String RelaID;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("ColumnID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("ColumnCode", 1, 2, 100, 0, true, false), 
     new SchemaColumn("TextValue", 10, 3, 0, 0, false, false), 
     new SchemaColumn("RelaType", 1, 4, 2, 0, false, false), 
     new SchemaColumn("RelaID", 1, 5, 100, 0, false, false) };
   public static final String _TableCode = "ZDColumnValue";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDColumnValue values(?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDColumnValue set ID=?,ColumnID=?,ColumnCode=?,TextValue=?,RelaType=?,RelaID=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZDColumnValue  where ID=?";
   protected static final String _FillAllSQL = "select * from ZDColumnValue  where ID=?";
 
   public ZDColumnValueSchema()
   {
     this.TableCode = "ZDColumnValue";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDColumnValue values(?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDColumnValue set ID=?,ColumnID=?,ColumnCode=?,TextValue=?,RelaType=?,RelaID=? where ID=?";
     this.DeleteSQL = "delete from ZDColumnValue  where ID=?";
     this.FillAllSQL = "select * from ZDColumnValue  where ID=?";
     this.HasSetFlag = new boolean[6];
   }
 
   protected Schema newInstance() {
     return new ZDColumnValueSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDColumnValueSet();
   }
 
   public ZDColumnValueSet query() {
     return query(null, -1, -1);
   }
 
   public ZDColumnValueSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDColumnValueSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDColumnValueSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDColumnValueSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.ColumnID = null; else this.ColumnID = new Long(v.toString()); return; }
     if (i == 2) { this.ColumnCode = ((String)v); return; }
     if (i == 3) { this.TextValue = ((String)v); return; }
     if (i == 4) { this.RelaType = ((String)v); return; }
     if (i != 5) return; this.RelaID = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.ColumnID;
     if (i == 2) return this.ColumnCode;
     if (i == 3) return this.TextValue;
     if (i == 4) return this.RelaType;
     if (i == 5) return this.RelaID;
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
 
   public long getColumnID()
   {
     if (this.ColumnID == null) return 0L;
     return this.ColumnID.longValue();
   }
 
   public void setColumnID(long columnID)
   {
     this.ColumnID = new Long(columnID);
   }
 
   public void setColumnID(String columnID)
   {
     if (columnID == null) {
       this.ColumnID = null;
       return;
     }
     this.ColumnID = new Long(columnID);
   }
 
   public String getColumnCode()
   {
     return this.ColumnCode;
   }
 
   public void setColumnCode(String columnCode)
   {
     this.ColumnCode = columnCode;
   }
 
   public String getTextValue()
   {
     return this.TextValue;
   }
 
   public void setTextValue(String textValue)
   {
     this.TextValue = textValue;
   }
 
   public String getRelaType()
   {
     return this.RelaType;
   }
 
   public void setRelaType(String relaType)
   {
     this.RelaType = relaType;
   }
 
   public String getRelaID()
   {
     return this.RelaID;
   }
 
   public void setRelaID(String relaID)
   {
     this.RelaID = relaID;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDColumnValueSchema
 * JD-Core Version:    0.5.3
 */