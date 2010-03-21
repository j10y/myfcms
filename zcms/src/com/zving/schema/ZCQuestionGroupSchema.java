 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCQuestionGroupSchema extends Schema
 {
   private String InnerCode;
   private String ParentInnerCode;
   private String Name;
   private Integer TreeLevel;
   private String IsLeaf;
   private Long OrderFlag;
   private String Memo;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private Date AddTime;
   private String AddUser;
   private Date ModifyTime;
   private String ModifyUser;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("InnerCode", 1, 0, 100, 0, true, true), 
     new SchemaColumn("ParentInnerCode", 1, 1, 100, 0, true, false), 
     new SchemaColumn("Name", 1, 2, 100, 0, true, false), 
     new SchemaColumn("TreeLevel", 8, 3, 0, 0, true, false), 
     new SchemaColumn("IsLeaf", 1, 4, 2, 0, false, false), 
     new SchemaColumn("OrderFlag", 7, 5, 0, 0, true, false), 
     new SchemaColumn("Memo", 1, 6, 100, 0, false, false), 
     new SchemaColumn("Prop1", 1, 7, 100, 0, false, false), 
     new SchemaColumn("Prop2", 1, 8, 100, 0, false, false), 
     new SchemaColumn("Prop3", 1, 9, 100, 0, false, false), 
     new SchemaColumn("Prop4", 1, 10, 100, 0, false, false), 
     new SchemaColumn("AddTime", 0, 11, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 12, 100, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 13, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 14, 100, 0, false, false) };
   public static final String _TableCode = "ZCQuestionGroup";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCQuestionGroup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCQuestionGroup set InnerCode=?,ParentInnerCode=?,Name=?,TreeLevel=?,IsLeaf=?,OrderFlag=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where InnerCode=?";
   protected static final String _DeleteSQL = "delete from ZCQuestionGroup  where InnerCode=?";
   protected static final String _FillAllSQL = "select * from ZCQuestionGroup  where InnerCode=?";
 
   public ZCQuestionGroupSchema()
   {
     this.TableCode = "ZCQuestionGroup";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCQuestionGroup values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCQuestionGroup set InnerCode=?,ParentInnerCode=?,Name=?,TreeLevel=?,IsLeaf=?,OrderFlag=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=? where InnerCode=?";
     this.DeleteSQL = "delete from ZCQuestionGroup  where InnerCode=?";
     this.FillAllSQL = "select * from ZCQuestionGroup  where InnerCode=?";
     this.HasSetFlag = new boolean[15];
   }
 
   protected Schema newInstance() {
     return new ZCQuestionGroupSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCQuestionGroupSet();
   }
 
   public ZCQuestionGroupSet query() {
     return query(null, -1, -1);
   }
 
   public ZCQuestionGroupSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCQuestionGroupSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCQuestionGroupSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCQuestionGroupSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { this.InnerCode = ((String)v); return; }
     if (i == 1) { this.ParentInnerCode = ((String)v); return; }
     if (i == 2) { this.Name = ((String)v); return; }
     if (i == 3) { if (v == null) this.TreeLevel = null; else this.TreeLevel = new Integer(v.toString()); return; }
     if (i == 4) { this.IsLeaf = ((String)v); return; }
     if (i == 5) { if (v == null) this.OrderFlag = null; else this.OrderFlag = new Long(v.toString()); return; }
     if (i == 6) { this.Memo = ((String)v); return; }
     if (i == 7) { this.Prop1 = ((String)v); return; }
     if (i == 8) { this.Prop2 = ((String)v); return; }
     if (i == 9) { this.Prop3 = ((String)v); return; }
     if (i == 10) { this.Prop4 = ((String)v); return; }
     if (i == 11) { this.AddTime = ((Date)v); return; }
     if (i == 12) { this.AddUser = ((String)v); return; }
     if (i == 13) { this.ModifyTime = ((Date)v); return; }
     if (i != 14) return; this.ModifyUser = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.InnerCode;
     if (i == 1) return this.ParentInnerCode;
     if (i == 2) return this.Name;
     if (i == 3) return this.TreeLevel;
     if (i == 4) return this.IsLeaf;
     if (i == 5) return this.OrderFlag;
     if (i == 6) return this.Memo;
     if (i == 7) return this.Prop1;
     if (i == 8) return this.Prop2;
     if (i == 9) return this.Prop3;
     if (i == 10) return this.Prop4;
     if (i == 11) return this.AddTime;
     if (i == 12) return this.AddUser;
     if (i == 13) return this.ModifyTime;
     if (i == 14) return this.ModifyUser;
     return null;
   }
 
   public String getInnerCode()
   {
     return this.InnerCode;
   }
 
   public void setInnerCode(String innerCode)
   {
     this.InnerCode = innerCode;
   }
 
   public String getParentInnerCode()
   {
     return this.ParentInnerCode;
   }
 
   public void setParentInnerCode(String parentInnerCode)
   {
     this.ParentInnerCode = parentInnerCode;
   }
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
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
 
   public String getIsLeaf()
   {
     return this.IsLeaf;
   }
 
   public void setIsLeaf(String isLeaf)
   {
     this.IsLeaf = isLeaf;
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
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
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
 * Qualified Name:     com.zving.schema.ZCQuestionGroupSchema
 * JD-Core Version:    0.5.3
 */