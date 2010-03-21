 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZDBranchSchema extends Schema
 {
   private String BranchInnerCode;
   private String BranchCode;
   private String ParentInnerCode;
   private String Type;
   private Long OrderFlag;
   private String Name;
   private Long TreeLevel;
   private String IsLeaf;
   private String Phone;
   private String Fax;
   private String Manager;
   private String Leader1;
   private String Leader2;
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
     new SchemaColumn("BranchInnerCode", 1, 0, 100, 0, true, true), 
     new SchemaColumn("BranchCode", 1, 1, 100, 0, false, false), 
     new SchemaColumn("ParentInnerCode", 1, 2, 100, 0, true, false), 
     new SchemaColumn("Type", 1, 3, 1, 0, true, false), 
     new SchemaColumn("OrderFlag", 7, 4, 0, 0, true, false), 
     new SchemaColumn("Name", 1, 5, 100, 0, true, false), 
     new SchemaColumn("TreeLevel", 7, 6, 0, 0, true, false), 
     new SchemaColumn("IsLeaf", 1, 7, 2, 0, false, false), 
     new SchemaColumn("Phone", 1, 8, 20, 0, false, false), 
     new SchemaColumn("Fax", 1, 9, 20, 0, false, false), 
     new SchemaColumn("Manager", 1, 10, 100, 0, false, false), 
     new SchemaColumn("Leader1", 1, 11, 100, 0, false, false), 
     new SchemaColumn("Leader2", 1, 12, 100, 0, false, false), 
     new SchemaColumn("Prop1", 1, 13, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 14, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 15, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 16, 50, 0, false, false), 
     new SchemaColumn("Memo", 1, 17, 100, 0, false, false), 
     new SchemaColumn("AddTime", 0, 18, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 19, 200, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 20, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 21, 200, 0, false, false) };
   public static final String _TableCode = "ZDBranch";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDBranch values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDBranch set BranchInnerCode=?,BranchCode=?,ParentInnerCode=?,Type=?,OrderFlag=?,Name=?,TreeLevel=?,IsLeaf=?,Phone=?,Fax=?,Manager=?,Leader1=?,Leader2=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where BranchInnerCode=?";
   protected static final String _DeleteSQL = "delete from ZDBranch  where BranchInnerCode=?";
   protected static final String _FillAllSQL = "select * from ZDBranch  where BranchInnerCode=?";
 
   public ZDBranchSchema()
   {
     this.TableCode = "ZDBranch";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDBranch values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDBranch set BranchInnerCode=?,BranchCode=?,ParentInnerCode=?,Type=?,OrderFlag=?,Name=?,TreeLevel=?,IsLeaf=?,Phone=?,Fax=?,Manager=?,Leader1=?,Leader2=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where BranchInnerCode=?";
     this.DeleteSQL = "delete from ZDBranch  where BranchInnerCode=?";
     this.FillAllSQL = "select * from ZDBranch  where BranchInnerCode=?";
     this.HasSetFlag = new boolean[22];
   }
 
   protected Schema newInstance() {
     return new ZDBranchSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDBranchSet();
   }
 
   public ZDBranchSet query() {
     return query(null, -1, -1);
   }
 
   public ZDBranchSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDBranchSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDBranchSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDBranchSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.BranchInnerCode = ((String)v); return; }
     if (i == 1) { this.BranchCode = ((String)v); return; }
     if (i == 2) { this.ParentInnerCode = ((String)v); return; }
     if (i == 3) { this.Type = ((String)v); return; }
     if (i == 4) { if (v == null) this.OrderFlag = null; else this.OrderFlag = new Long(v.toString()); return; }
     if (i == 5) { this.Name = ((String)v); return; }
     if (i == 6) { if (v == null) this.TreeLevel = null; else this.TreeLevel = new Long(v.toString()); return; }
     if (i == 7) { this.IsLeaf = ((String)v); return; }
     if (i == 8) { this.Phone = ((String)v); return; }
     if (i == 9) { this.Fax = ((String)v); return; }
     if (i == 10) { this.Manager = ((String)v); return; }
     if (i == 11) { this.Leader1 = ((String)v); return; }
     if (i == 12) { this.Leader2 = ((String)v); return; }
     if (i == 13) { this.Prop1 = ((String)v); return; }
     if (i == 14) { this.Prop2 = ((String)v); return; }
     if (i == 15) { this.Prop3 = ((String)v); return; }
     if (i == 16) { this.Prop4 = ((String)v); return; }
     if (i == 17) { this.Memo = ((String)v); return; }
     if (i == 18) { this.AddTime = ((Date)v); return; }
     if (i == 19) { this.AddUser = ((String)v); return; }
     if (i == 20) { this.ModifyTime = ((Date)v); return; }
     if (i != 21) return; this.ModifyUser = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.BranchInnerCode;
     if (i == 1) return this.BranchCode;
     if (i == 2) return this.ParentInnerCode;
     if (i == 3) return this.Type;
     if (i == 4) return this.OrderFlag;
     if (i == 5) return this.Name;
     if (i == 6) return this.TreeLevel;
     if (i == 7) return this.IsLeaf;
     if (i == 8) return this.Phone;
     if (i == 9) return this.Fax;
     if (i == 10) return this.Manager;
     if (i == 11) return this.Leader1;
     if (i == 12) return this.Leader2;
     if (i == 13) return this.Prop1;
     if (i == 14) return this.Prop2;
     if (i == 15) return this.Prop3;
     if (i == 16) return this.Prop4;
     if (i == 17) return this.Memo;
     if (i == 18) return this.AddTime;
     if (i == 19) return this.AddUser;
     if (i == 20) return this.ModifyTime;
     if (i == 21) return this.ModifyUser;
     return null;
   }
 
   public String getBranchInnerCode()
   {
     return this.BranchInnerCode;
   }
 
   public void setBranchInnerCode(String branchInnerCode)
   {
     this.BranchInnerCode = branchInnerCode;
   }
 
   public String getBranchCode()
   {
     return this.BranchCode;
   }
 
   public void setBranchCode(String branchCode)
   {
     this.BranchCode = branchCode;
   }
 
   public String getParentInnerCode()
   {
     return this.ParentInnerCode;
   }
 
   public void setParentInnerCode(String parentInnerCode)
   {
     this.ParentInnerCode = parentInnerCode;
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
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
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
   }
 
   public long getTreeLevel()
   {
     if (this.TreeLevel == null) return 0L;
     return this.TreeLevel.longValue();
   }
 
   public void setTreeLevel(long treeLevel)
   {
     this.TreeLevel = new Long(treeLevel);
   }
 
   public void setTreeLevel(String treeLevel)
   {
     if (treeLevel == null) {
       this.TreeLevel = null;
       return;
     }
     this.TreeLevel = new Long(treeLevel);
   }
 
   public String getIsLeaf()
   {
     return this.IsLeaf;
   }
 
   public void setIsLeaf(String isLeaf)
   {
     this.IsLeaf = isLeaf;
   }
 
   public String getPhone()
   {
     return this.Phone;
   }
 
   public void setPhone(String phone)
   {
     this.Phone = phone;
   }
 
   public String getFax()
   {
     return this.Fax;
   }
 
   public void setFax(String fax)
   {
     this.Fax = fax;
   }
 
   public String getManager()
   {
     return this.Manager;
   }
 
   public void setManager(String manager)
   {
     this.Manager = manager;
   }
 
   public String getLeader1()
   {
     return this.Leader1;
   }
 
   public void setLeader1(String leader1)
   {
     this.Leader1 = leader1;
   }
 
   public String getLeader2()
   {
     return this.Leader2;
   }
 
   public void setLeader2(String leader2)
   {
     this.Leader2 = leader2;
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
 * Qualified Name:     com.zving.schema.ZDBranchSchema
 * JD-Core Version:    0.5.3
 */