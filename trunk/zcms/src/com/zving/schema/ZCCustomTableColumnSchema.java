 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCCustomTableColumnSchema extends Schema
 {
   private Long ID;
   private Long TableID;
   private String Code;
   private String Name;
   private String DataType;
   private Long Length;
   private String isPrimaryKey;
   private String isMandatory;
   private String DefaultValue;
   private String InputType;
   private String CSSStyle;
   private Integer MaxLength;
   private String ListOptions;
   private String isAutoID;
   private String Prop1;
   private String Prop2;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("TableID", 7, 1, 0, 0, false, false), 
     new SchemaColumn("Code", 1, 2, 50, 0, true, false), 
     new SchemaColumn("Name", 1, 3, 100, 0, true, false), 
     new SchemaColumn("DataType", 1, 4, 50, 0, true, false), 
     new SchemaColumn("Length", 7, 5, 0, 0, false, false), 
     new SchemaColumn("isPrimaryKey", 1, 6, 2, 0, false, false), 
     new SchemaColumn("isMandatory", 1, 7, 2, 0, true, false), 
     new SchemaColumn("DefaultValue", 1, 8, 50, 0, false, false), 
     new SchemaColumn("InputType", 1, 9, 2, 0, false, false), 
     new SchemaColumn("CSSStyle", 1, 10, 200, 0, false, false), 
     new SchemaColumn("MaxLength", 8, 11, 0, 0, false, false), 
     new SchemaColumn("ListOptions", 10, 12, 0, 0, false, false), 
     new SchemaColumn("isAutoID", 1, 13, 2, 0, false, false), 
     new SchemaColumn("Prop1", 1, 14, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 15, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 16, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 17, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 18, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 19, 0, 0, false, false) };
   public static final String _TableCode = "ZCCustomTableColumn";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCCustomTableColumn values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCCustomTableColumn set ID=?,TableID=?,Code=?,Name=?,DataType=?,Length=?,isPrimaryKey=?,isMandatory=?,DefaultValue=?,InputType=?,CSSStyle=?,MaxLength=?,ListOptions=?,isAutoID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCCustomTableColumn  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCCustomTableColumn  where ID=?";
 
   public ZCCustomTableColumnSchema()
   {
     this.TableCode = "ZCCustomTableColumn";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCCustomTableColumn values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCCustomTableColumn set ID=?,TableID=?,Code=?,Name=?,DataType=?,Length=?,isPrimaryKey=?,isMandatory=?,DefaultValue=?,InputType=?,CSSStyle=?,MaxLength=?,ListOptions=?,isAutoID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCCustomTableColumn  where ID=?";
     this.FillAllSQL = "select * from ZCCustomTableColumn  where ID=?";
     this.HasSetFlag = new boolean[20];
   }
 
   protected Schema newInstance() {
     return new ZCCustomTableColumnSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCCustomTableColumnSet();
   }
 
   public ZCCustomTableColumnSet query() {
     return query(null, -1, -1);
   }
 
   public ZCCustomTableColumnSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCCustomTableColumnSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCCustomTableColumnSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCCustomTableColumnSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.TableID = null; else this.TableID = new Long(v.toString()); return; }
     if (i == 2) { this.Code = ((String)v); return; }
     if (i == 3) { this.Name = ((String)v); return; }
     if (i == 4) { this.DataType = ((String)v); return; }
     if (i == 5) { if (v == null) this.Length = null; else this.Length = new Long(v.toString()); return; }
     if (i == 6) { this.isPrimaryKey = ((String)v); return; }
     if (i == 7) { this.isMandatory = ((String)v); return; }
     if (i == 8) { this.DefaultValue = ((String)v); return; }
     if (i == 9) { this.InputType = ((String)v); return; }
     if (i == 10) { this.CSSStyle = ((String)v); return; }
     if (i == 11) { if (v == null) this.MaxLength = null; else this.MaxLength = new Integer(v.toString()); return; }
     if (i == 12) { this.ListOptions = ((String)v); return; }
     if (i == 13) { this.isAutoID = ((String)v); return; }
     if (i == 14) { this.Prop1 = ((String)v); return; }
     if (i == 15) { this.Prop2 = ((String)v); return; }
     if (i == 16) { this.AddUser = ((String)v); return; }
     if (i == 17) { this.AddTime = ((Date)v); return; }
     if (i == 18) { this.ModifyUser = ((String)v); return; }
     if (i != 19) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.TableID;
     if (i == 2) return this.Code;
     if (i == 3) return this.Name;
     if (i == 4) return this.DataType;
     if (i == 5) return this.Length;
     if (i == 6) return this.isPrimaryKey;
     if (i == 7) return this.isMandatory;
     if (i == 8) return this.DefaultValue;
     if (i == 9) return this.InputType;
     if (i == 10) return this.CSSStyle;
     if (i == 11) return this.MaxLength;
     if (i == 12) return this.ListOptions;
     if (i == 13) return this.isAutoID;
     if (i == 14) return this.Prop1;
     if (i == 15) return this.Prop2;
     if (i == 16) return this.AddUser;
     if (i == 17) return this.AddTime;
     if (i == 18) return this.ModifyUser;
     if (i == 19) return this.ModifyTime;
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
 
   public long getTableID()
   {
     if (this.TableID == null) return 0L;
     return this.TableID.longValue();
   }
 
   public void setTableID(long tableID)
   {
     this.TableID = new Long(tableID);
   }
 
   public void setTableID(String tableID)
   {
     if (tableID == null) {
       this.TableID = null;
       return;
     }
     this.TableID = new Long(tableID);
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
 
   public String getDataType()
   {
     return this.DataType;
   }
 
   public void setDataType(String dataType)
   {
     this.DataType = dataType;
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
 
   public String getIsPrimaryKey()
   {
     return this.isPrimaryKey;
   }
 
   public void setIsPrimaryKey(String isPrimaryKey)
   {
     this.isPrimaryKey = isPrimaryKey;
   }
 
   public String getIsMandatory()
   {
     return this.isMandatory;
   }
 
   public void setIsMandatory(String isMandatory)
   {
     this.isMandatory = isMandatory;
   }
 
   public String getDefaultValue()
   {
     return this.DefaultValue;
   }
 
   public void setDefaultValue(String defaultValue)
   {
     this.DefaultValue = defaultValue;
   }
 
   public String getInputType()
   {
     return this.InputType;
   }
 
   public void setInputType(String inputType)
   {
     this.InputType = inputType;
   }
 
   public String getCSSStyle()
   {
     return this.CSSStyle;
   }
 
   public void setCSSStyle(String cSSStyle)
   {
     this.CSSStyle = cSSStyle;
   }
 
   public int getMaxLength()
   {
     if (this.MaxLength == null) return 0;
     return this.MaxLength.intValue();
   }
 
   public void setMaxLength(int maxLength)
   {
     this.MaxLength = new Integer(maxLength);
   }
 
   public void setMaxLength(String maxLength)
   {
     if (maxLength == null) {
       this.MaxLength = null;
       return;
     }
     this.MaxLength = new Integer(maxLength);
   }
 
   public String getListOptions()
   {
     return this.ListOptions;
   }
 
   public void setListOptions(String listOptions)
   {
     this.ListOptions = listOptions;
   }
 
   public String getIsAutoID()
   {
     return this.isAutoID;
   }
 
   public void setIsAutoID(String isAutoID)
   {
     this.isAutoID = isAutoID;
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
     return this.AddUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.AddUser = addUser;
   }
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 
   public String getModifyUser()
   {
     return this.ModifyUser;
   }
 
   public void setModifyUser(String modifyUser)
   {
     this.ModifyUser = modifyUser;
   }
 
   public Date getModifyTime()
   {
     return this.ModifyTime;
   }
 
   public void setModifyTime(Date modifyTime)
   {
     this.ModifyTime = modifyTime;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZCCustomTableColumnSchema
 * JD-Core Version:    0.5.3
 */