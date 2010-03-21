 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZDMemberLevelSchema extends Schema
 {
   private Long ID;
   private String Name;
   private String Type;
   private Float Discount;
   private String IsDefault;
   private Integer TreeLevel;
   private Long Score;
   private String IsValidate;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("Name", 1, 1, 100, 0, true, false), 
     new SchemaColumn("Type", 1, 2, 10, 0, true, false), 
     new SchemaColumn("Discount", 5, 3, 3, 1, true, false), 
     new SchemaColumn("IsDefault", 1, 4, 1, 0, true, false), 
     new SchemaColumn("TreeLevel", 8, 5, 0, 0, true, false), 
     new SchemaColumn("Score", 7, 6, 0, 0, true, false), 
     new SchemaColumn("IsValidate", 1, 7, 1, 0, true, false), 
     new SchemaColumn("AddUser", 1, 8, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 9, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 10, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 11, 0, 0, false, false) };
   public static final String _TableCode = "ZDMemberLevel";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZDMemberLevel values(?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZDMemberLevel set ID=?,Name=?,Type=?,Discount=?,IsDefault=?,TreeLevel=?,Score=?,IsValidate=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZDMemberLevel  where ID=?";
   protected static final String _FillAllSQL = "select * from ZDMemberLevel  where ID=?";
 
   public ZDMemberLevelSchema()
   {
     this.TableCode = "ZDMemberLevel";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZDMemberLevel values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZDMemberLevel set ID=?,Name=?,Type=?,Discount=?,IsDefault=?,TreeLevel=?,Score=?,IsValidate=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZDMemberLevel  where ID=?";
     this.FillAllSQL = "select * from ZDMemberLevel  where ID=?";
     this.HasSetFlag = new boolean[12];
   }
 
   protected Schema newInstance() {
     return new ZDMemberLevelSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZDMemberLevelSet();
   }
 
   public ZDMemberLevelSet query() {
     return query(null, -1, -1);
   }
 
   public ZDMemberLevelSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZDMemberLevelSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZDMemberLevelSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZDMemberLevelSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Name = ((String)v); return; }
     if (i == 2) { this.Type = ((String)v); return; }
     if (i == 3) { if (v == null) this.Discount = null; else this.Discount = new Float(v.toString()); return; }
     if (i == 4) { this.IsDefault = ((String)v); return; }
     if (i == 5) { if (v == null) this.TreeLevel = null; else this.TreeLevel = new Integer(v.toString()); return; }
     if (i == 6) { if (v == null) this.Score = null; else this.Score = new Long(v.toString()); return; }
     if (i == 7) { this.IsValidate = ((String)v); return; }
     if (i == 8) { this.AddUser = ((String)v); return; }
     if (i == 9) { this.AddTime = ((Date)v); return; }
     if (i == 10) { this.ModifyUser = ((String)v); return; }
     if (i != 11) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Name;
     if (i == 2) return this.Type;
     if (i == 3) return this.Discount;
     if (i == 4) return this.IsDefault;
     if (i == 5) return this.TreeLevel;
     if (i == 6) return this.Score;
     if (i == 7) return this.IsValidate;
     if (i == 8) return this.AddUser;
     if (i == 9) return this.AddTime;
     if (i == 10) return this.ModifyUser;
     if (i == 11) return this.ModifyTime;
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
 
   public String getName()
   {
     return this.Name;
   }
 
   public void setName(String name)
   {
     this.Name = name;
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
   }
 
   public float getDiscount()
   {
     if (this.Discount == null) return 0.0F;
     return this.Discount.floatValue();
   }
 
   public void setDiscount(float discount)
   {
     this.Discount = new Float(discount);
   }
 
   public void setDiscount(String discount)
   {
     if (discount == null) {
       this.Discount = null;
       return;
     }
     this.Discount = new Float(discount);
   }
 
   public String getIsDefault()
   {
     return this.IsDefault;
   }
 
   public void setIsDefault(String isDefault)
   {
     this.IsDefault = isDefault;
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
 
   public long getScore()
   {
     if (this.Score == null) return 0L;
     return this.Score.longValue();
   }
 
   public void setScore(long score)
   {
     this.Score = new Long(score);
   }
 
   public void setScore(String score)
   {
     if (score == null) {
       this.Score = null;
       return;
     }
     this.Score = new Long(score);
   }
 
   public String getIsValidate()
   {
     return this.IsValidate;
   }
 
   public void setIsValidate(String isValidate)
   {
     this.IsValidate = isValidate;
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
 * Qualified Name:     com.zving.schema.ZDMemberLevelSchema
 * JD-Core Version:    0.5.3
 */