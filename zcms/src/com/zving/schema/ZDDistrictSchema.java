 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 
 public class ZDDistrictSchema extends Schema
 {
   private String Code;
   private String Name;
   private String CodeOrder;
   private Integer TreeLevel;
   private String Type;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("Code", 1, 0, 6, 0, true, true), 
     new SchemaColumn("Name", 1, 1, 100, 0, false, false), 
     new SchemaColumn("CodeOrder", 1, 2, 20, 0, false, false), 
     new SchemaColumn("TreeLevel", 8, 3, 0, 0, false, false), 
     new SchemaColumn("Type", 1, 4, 2, 0, false, false) };
   public static final String _TableCode = "ZDDistrict";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDDistrict values(?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDDistrict set Code=?,Name=?,CodeOrder=?,TreeLevel=?,Type=? where Code=?";
   protected static final String _DeleteSQL = "delete from ZDDistrict  where Code=?";
   protected static final String _FillAllSQL = "select * from ZDDistrict  where Code=?";
 
   public ZDDistrictSchema()
   {
     this.TableCode = "ZDDistrict";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDDistrict values(?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDDistrict set Code=?,Name=?,CodeOrder=?,TreeLevel=?,Type=? where Code=?";
     this.DeleteSQL = "delete from ZDDistrict  where Code=?";
     this.FillAllSQL = "select * from ZDDistrict  where Code=?";
     this.HasSetFlag = new boolean[5];
   }
 
   protected Schema newInstance() {
     return new ZDDistrictSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDDistrictSet();
   }
 
   public ZDDistrictSet query() {
     return query(null, -1, -1);
   }
 
   public ZDDistrictSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDDistrictSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDDistrictSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDDistrictSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.Code = ((String)v); return; }
     if (i == 1) { this.Name = ((String)v); return; }
     if (i == 2) { this.CodeOrder = ((String)v); return; }
     if (i == 3) { if (v == null) this.TreeLevel = null; else this.TreeLevel = new Integer(v.toString()); return; }
     if (i != 4) return; this.Type = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.Code;
     if (i == 1) return this.Name;
     if (i == 2) return this.CodeOrder;
     if (i == 3) return this.TreeLevel;
     if (i == 4) return this.Type;
     return null;
   }
 
   public String getCode()
   {
     return this.Code;
   }
 
   public void setCode(String code)
   {
     this.Code = code;
   }
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
   }
 
   public String getCodeOrder()
   {
     return this.CodeOrder;
   }
 
   public void setCodeOrder(String codeOrder)
   {
     this.CodeOrder = codeOrder;
   }
 
   public int getTreeLevel()
   {
     if (this.TreeLevel == null) return 0;
     return this.TreeLevel.intValue();
   }
 
   public void setTreeLevel(int treeLevel)
   {
     this.TreeLevel = new Integer(treeLevel);
   }
 
   public void setTreeLevel(String treeLevel)
   {
     if (treeLevel == null) {
       this.TreeLevel = null;
       return;
     }
     this.TreeLevel = new Integer(treeLevel);
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDDistrictSchema
 * JD-Core Version:    0.5.3
 */