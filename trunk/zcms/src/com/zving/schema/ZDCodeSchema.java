 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZDCodeSchema extends Schema
 {
   private String CodeType;
   private String ParentCode;
   private String CodeValue;
   private String CodeName;
   private Long CodeOrder;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String Memo;
   private Date AddTime;
   private String AddUser;
   private Date ModifyTime;
   private String ModifyUser;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("CodeType", 1, 0, 40, 0, true, true), 
     new SchemaColumn("ParentCode", 1, 1, 40, 0, true, true), 
     new SchemaColumn("CodeValue", 1, 2, 40, 0, true, true), 
     new SchemaColumn("CodeName", 1, 3, 40, 0, true, false), 
     new SchemaColumn("CodeOrder", 7, 4, 0, 0, true, false), 
     new SchemaColumn("Prop1", 1, 5, 40, 0, false, false), 
     new SchemaColumn("Prop2", 1, 6, 40, 0, false, false), 
     new SchemaColumn("Prop3", 1, 7, 40, 0, false, false), 
     new SchemaColumn("Prop4", 1, 8, 40, 0, false, false), 
     new SchemaColumn("Memo", 1, 9, 40, 0, false, false), 
     new SchemaColumn("AddTime", 0, 10, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 11, 200, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 12, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 13, 200, 0, false, false) };
   public static final String _TableCode = "ZDCode";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDCode values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDCode set CodeType=?,ParentCode=?,CodeValue=?,CodeName=?,CodeOrder=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where CodeType=? and ParentCode=? and CodeValue=?";
   protected static final String _DeleteSQL = "delete from ZDCode  where CodeType=? and ParentCode=? and CodeValue=?";
   protected static final String _FillAllSQL = "select * from ZDCode  where CodeType=? and ParentCode=? and CodeValue=?";
 
   public ZDCodeSchema()
   {
     this.TableCode = "ZDCode";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDCode values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDCode set CodeType=?,ParentCode=?,CodeValue=?,CodeName=?,CodeOrder=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where CodeType=? and ParentCode=? and CodeValue=?";
     this.DeleteSQL = "delete from ZDCode  where CodeType=? and ParentCode=? and CodeValue=?";
     this.FillAllSQL = "select * from ZDCode  where CodeType=? and ParentCode=? and CodeValue=?";
     this.HasSetFlag = new boolean[14];
   }
 
   protected Schema newInstance() {
     return new ZDCodeSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDCodeSet();
   }
 
   public ZDCodeSet query() {
     return query(null, -1, -1);
   }
 
   public ZDCodeSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDCodeSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDCodeSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDCodeSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.CodeType = ((String)v); return; }
     if (i == 1) { this.ParentCode = ((String)v); return; }
     if (i == 2) { this.CodeValue = ((String)v); return; }
     if (i == 3) { this.CodeName = ((String)v); return; }
     if (i == 4) { if (v == null) this.CodeOrder = null; else this.CodeOrder = new Long(v.toString()); return; }
     if (i == 5) { this.Prop1 = ((String)v); return; }
     if (i == 6) { this.Prop2 = ((String)v); return; }
     if (i == 7) { this.Prop3 = ((String)v); return; }
     if (i == 8) { this.Prop4 = ((String)v); return; }
     if (i == 9) { this.Memo = ((String)v); return; }
     if (i == 10) { this.AddTime = ((Date)v); return; }
     if (i == 11) { this.AddUser = ((String)v); return; }
     if (i == 12) { this.ModifyTime = ((Date)v); return; }
     if (i != 13) return; this.ModifyUser = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.CodeType;
     if (i == 1) return this.ParentCode;
     if (i == 2) return this.CodeValue;
     if (i == 3) return this.CodeName;
     if (i == 4) return this.CodeOrder;
     if (i == 5) return this.Prop1;
     if (i == 6) return this.Prop2;
     if (i == 7) return this.Prop3;
     if (i == 8) return this.Prop4;
     if (i == 9) return this.Memo;
     if (i == 10) return this.AddTime;
     if (i == 11) return this.AddUser;
     if (i == 12) return this.ModifyTime;
     if (i == 13) return this.ModifyUser;
     return null;
   }
 
   public String getCodeType()
   {
     return this.CodeType;
   }
 
   public void setCodeType(String codeType)
   {
     this.CodeType = codeType;
   }
 
   public String getParentCode()
   {
     return this.ParentCode;
   }
 
   public void setParentCode(String parentCode)
   {
     this.ParentCode = parentCode;
   }
 
   public String getCodeValue()
   {
     return this.CodeValue;
   }
 
   public void setCodeValue(String codeValue)
   {
     this.CodeValue = codeValue;
   }
 
   public String getCodeName()
   {
     return this.CodeName;
   }
 
   public void setCodeName(String codeName)
   {
     this.CodeName = codeName;
   }
 
   public long getCodeOrder()
   {
     if (this.CodeOrder == null) return 0L;
     return this.CodeOrder.longValue();
   }
 
   public void setCodeOrder(long codeOrder)
   {
     this.CodeOrder = new Long(codeOrder);
   }
 
   public void setCodeOrder(String codeOrder)
   {
     if (codeOrder == null) {
       this.CodeOrder = null;
       return;
     }
     this.CodeOrder = new Long(codeOrder);
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
 
   public String getProp3()
   {
     return this.Prop3;
   }
 
   public void setProp3(String prop3)
   {
     this.Prop3 = prop3;
   }
 
   public String getProp4()
   {
     return this.Prop4;
   }
 
   public void setProp4(String prop4)
   {
     this.Prop4 = prop4;
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
   }
 
   public Date getAddTime()
   {
     return this.AddTime;
   }
 
   public void setAddTime(Date addTime)
   {
     this.AddTime = addTime;
   }
 
   public String getAddUser()
   {
     return this.AddUser;
   }
 
   public void setAddUser(String addUser)
   {
     this.AddUser = addUser;
   }
 
   public Date getModifyTime()
   {
     return this.ModifyTime;
   }
 
   public void setModifyTime(Date modifyTime)
   {
     this.ModifyTime = modifyTime;
   }
 
   public String getModifyUser()
   {
     return this.ModifyUser;
   }
 
   public void setModifyUser(String modifyUser)
   {
     this.ModifyUser = modifyUser;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.ZDCodeSchema
 * JD-Core Version:    0.5.3
 */