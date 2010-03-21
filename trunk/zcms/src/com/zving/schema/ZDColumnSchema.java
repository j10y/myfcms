 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZDColumnSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private String Name;
   private String Code;
   private String VerifyType;
   private Integer MaxLength;
   private String InputType;
   private String DefaultValue;
   private String ListOption;
   private String HTML;
   private String IsMandatory;
   private Long OrderFlag;
   private Integer RowSize;
   private Integer ColSize;
   private String Prop1;
   private String Prop2;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Name", 1, 2, 100, 0, true, false), 
     new SchemaColumn("Code", 1, 3, 100, 0, true, false), 
     new SchemaColumn("VerifyType", 1, 4, 2, 0, true, false), 
     new SchemaColumn("MaxLength", 8, 5, 0, 0, false, false), 
     new SchemaColumn("InputType", 1, 6, 2, 0, true, false), 
     new SchemaColumn("DefaultValue", 1, 7, 50, 0, false, false), 
     new SchemaColumn("ListOption", 1, 8, 1000, 0, false, false), 
     new SchemaColumn("HTML", 10, 9, 0, 0, false, false), 
     new SchemaColumn("IsMandatory", 1, 10, 1, 0, true, false), 
     new SchemaColumn("OrderFlag", 7, 11, 0, 0, false, false), 
     new SchemaColumn("RowSize", 8, 12, 0, 0, false, false), 
     new SchemaColumn("ColSize", 8, 13, 0, 0, false, false), 
     new SchemaColumn("Prop1", 1, 14, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 15, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 16, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 17, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 18, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 19, 0, 0, false, false) };
   public static final String _TableCode = "ZDColumn";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDColumn values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDColumn set ID=?,SiteID=?,Name=?,Code=?,VerifyType=?,MaxLength=?,InputType=?,DefaultValue=?,ListOption=?,HTML=?,IsMandatory=?,OrderFlag=?,RowSize=?,ColSize=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZDColumn  where ID=?";
   protected static final String _FillAllSQL = "select * from ZDColumn  where ID=?";
 
   public ZDColumnSchema()
   {
     this.TableCode = "ZDColumn";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDColumn values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDColumn set ID=?,SiteID=?,Name=?,Code=?,VerifyType=?,MaxLength=?,InputType=?,DefaultValue=?,ListOption=?,HTML=?,IsMandatory=?,OrderFlag=?,RowSize=?,ColSize=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZDColumn  where ID=?";
     this.FillAllSQL = "select * from ZDColumn  where ID=?";
     this.HasSetFlag = new boolean[20];
   }
 
   protected Schema newInstance() {
     return new ZDColumnSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDColumnSet();
   }
 
   public ZDColumnSet query() {
     return query(null, -1, -1);
   }
 
   public ZDColumnSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDColumnSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDColumnSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDColumnSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { this.Name = ((String)v); return; }
     if (i == 3) { this.Code = ((String)v); return; }
     if (i == 4) { this.VerifyType = ((String)v); return; }
     if (i == 5) { if (v == null) this.MaxLength = null; else this.MaxLength = new Integer(v.toString()); return; }
     if (i == 6) { this.InputType = ((String)v); return; }
     if (i == 7) { this.DefaultValue = ((String)v); return; }
     if (i == 8) { this.ListOption = ((String)v); return; }
     if (i == 9) { this.HTML = ((String)v); return; }
     if (i == 10) { this.IsMandatory = ((String)v); return; }
     if (i == 11) { if (v == null) this.OrderFlag = null; else this.OrderFlag = new Long(v.toString()); return; }
     if (i == 12) { if (v == null) this.RowSize = null; else this.RowSize = new Integer(v.toString()); return; }
     if (i == 13) { if (v == null) this.ColSize = null; else this.ColSize = new Integer(v.toString()); return; }
     if (i == 14) { this.Prop1 = ((String)v); return; }
     if (i == 15) { this.Prop2 = ((String)v); return; }
     if (i == 16) { this.AddUser = ((String)v); return; }
     if (i == 17) { this.AddTime = ((Date)v); return; }
     if (i == 18) { this.ModifyUser = ((String)v); return; }
     if (i != 19) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.Name;
     if (i == 3) return this.Code;
     if (i == 4) return this.VerifyType;
     if (i == 5) return this.MaxLength;
     if (i == 6) return this.InputType;
     if (i == 7) return this.DefaultValue;
     if (i == 8) return this.ListOption;
     if (i == 9) return this.HTML;
     if (i == 10) return this.IsMandatory;
     if (i == 11) return this.OrderFlag;
     if (i == 12) return this.RowSize;
     if (i == 13) return this.ColSize;
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
 
   public long getSiteID()
   {
     if (this.SiteID == null) return 0L;
     return this.SiteID.longValue();
   }
 
   public void setSiteID(long siteID)
   {
     this.SiteID = new Long(siteID);
   }
 
   public void setSiteID(String siteID)
   {
     if (siteID == null) {
       this.SiteID = null;
       return;
     }
     this.SiteID = new Long(siteID);
   }
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
   }
 
   public String getCode()
   {
     return this.Code;
   }
 
   public void setCode(String code)
   {
     this.Code = code;
   }
 
   public String getVerifyType()
   {
     return this.VerifyType;
   }
 
   public void setVerifyType(String verifyType)
   {
     this.VerifyType = verifyType;
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
 
   public String getInputType()
   {
     return this.InputType;
   }
 
   public void setInputType(String inputType)
   {
     this.InputType = inputType;
   }
 
   public String getDefaultValue()
   {
     return this.DefaultValue;
   }
 
   public void setDefaultValue(String defaultValue)
   {
     this.DefaultValue = defaultValue;
   }
 
   public String getListOption()
   {
     return this.ListOption;
   }
 
   public void setListOption(String listOption)
   {
     this.ListOption = listOption;
   }
 
   public String getHTML()
   {
     return this.HTML;
   }
 
   public void setHTML(String hTML)
   {
     this.HTML = hTML;
   }
 
   public String getIsMandatory()
   {
     return this.IsMandatory;
   }
 
   public void setIsMandatory(String isMandatory)
   {
     this.IsMandatory = isMandatory;
   }
 
   public long getOrderFlag()
   {
     if (this.OrderFlag == null) return 0L;
     return this.OrderFlag.longValue();
   }
 
   public void setOrderFlag(long orderFlag)
   {
     this.OrderFlag = new Long(orderFlag);
   }
 
   public void setOrderFlag(String orderFlag)
   {
     if (orderFlag == null) {
       this.OrderFlag = null;
       return;
     }
     this.OrderFlag = new Long(orderFlag);
   }
 
   public int getRowSize()
   {
     if (this.RowSize == null) return 0;
     return this.RowSize.intValue();
   }
 
   public void setRowSize(int rowSize)
   {
     this.RowSize = new Integer(rowSize);
   }
 
   public void setRowSize(String rowSize)
   {
     if (rowSize == null) {
       this.RowSize = null;
       return;
     }
     this.RowSize = new Integer(rowSize);
   }
 
   public int getColSize()
   {
     if (this.ColSize == null) return 0;
     return this.ColSize.intValue();
   }
 
   public void setColSize(int colSize)
   {
     this.ColSize = new Integer(colSize);
   }
 
   public void setColSize(String colSize)
   {
     if (colSize == null) {
       this.ColSize = null;
       return;
     }
     this.ColSize = new Integer(colSize);
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
 * Qualified Name:     com.zving.schema.ZDColumnSchema
 * JD-Core Version:    0.5.3
 */