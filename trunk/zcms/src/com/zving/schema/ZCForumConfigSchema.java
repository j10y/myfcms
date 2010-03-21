 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCForumConfigSchema extends Schema
 {
   private Long ID;
   private String Name;
   private Long SiteID;
   private String Subdomains;
   private String Des;
   private String TempCloseFlag;
   private String prop1;
   private String prop2;
   private String prop3;
   private String prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("Name", 1, 1, 50, 0, false, false), 
     new SchemaColumn("SiteID", 7, 2, 0, 0, false, false), 
     new SchemaColumn("Subdomains", 1, 3, 50, 0, false, false), 
     new SchemaColumn("Des", 1, 4, 1024, 0, false, false), 
     new SchemaColumn("TempCloseFlag", 1, 5, 2, 0, false, false), 
     new SchemaColumn("prop1", 1, 6, 50, 0, false, false), 
     new SchemaColumn("prop2", 1, 7, 50, 0, false, false), 
     new SchemaColumn("prop3", 1, 8, 50, 0, false, false), 
     new SchemaColumn("prop4", 1, 9, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 10, 100, 0, true, false), 
     new SchemaColumn("AddTime", 0, 11, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 12, 100, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 13, 0, 0, false, false) };
   public static final String _TableCode = "ZCForumConfig";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCForumConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCForumConfig set ID=?,Name=?,SiteID=?,Subdomains=?,Des=?,TempCloseFlag=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCForumConfig  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCForumConfig  where ID=?";
 
   public ZCForumConfigSchema()
   {
     this.TableCode = "ZCForumConfig";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCForumConfig values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCForumConfig set ID=?,Name=?,SiteID=?,Subdomains=?,Des=?,TempCloseFlag=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCForumConfig  where ID=?";
     this.FillAllSQL = "select * from ZCForumConfig  where ID=?";
     this.HasSetFlag = new boolean[14];
   }
 
   protected Schema newInstance() {
     return new ZCForumConfigSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCForumConfigSet();
   }
 
   public ZCForumConfigSet query() {
     return query(null, -1, -1);
   }
 
   public ZCForumConfigSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCForumConfigSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCForumConfigSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCForumConfigSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Name = ((String)v); return; }
     if (i == 2) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 3) { this.Subdomains = ((String)v); return; }
     if (i == 4) { this.Des = ((String)v); return; }
     if (i == 5) { this.TempCloseFlag = ((String)v); return; }
     if (i == 6) { this.prop1 = ((String)v); return; }
     if (i == 7) { this.prop2 = ((String)v); return; }
     if (i == 8) { this.prop3 = ((String)v); return; }
     if (i == 9) { this.prop4 = ((String)v); return; }
     if (i == 10) { this.AddUser = ((String)v); return; }
     if (i == 11) { this.AddTime = ((Date)v); return; }
     if (i == 12) { this.ModifyUser = ((String)v); return; }
     if (i != 13) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Name;
     if (i == 2) return this.SiteID;
     if (i == 3) return this.Subdomains;
     if (i == 4) return this.Des;
     if (i == 5) return this.TempCloseFlag;
     if (i == 6) return this.prop1;
     if (i == 7) return this.prop2;
     if (i == 8) return this.prop3;
     if (i == 9) return this.prop4;
     if (i == 10) return this.AddUser;
     if (i == 11) return this.AddTime;
     if (i == 12) return this.ModifyUser;
     if (i == 13) return this.ModifyTime;
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
 
   public String getSubdomains()
   {
     return this.Subdomains;
   }
 
   public void setSubdomains(String subdomains)
   {
     this.Subdomains = subdomains;
   }
 
   public String getDes()
   {
     return this.Des;
   }
 
   public void setDes(String des)
   {
     this.Des = des;
   }
 
   public String getTempCloseFlag()
   {
     return this.TempCloseFlag;
   }
 
   public void setTempCloseFlag(String tempCloseFlag)
   {
     this.TempCloseFlag = tempCloseFlag;
   }
 
   public String getProp1()
   {
     return this.prop1;
   }
 
   public void setProp1(String prop1)
   {
     this.prop1 = prop1;
   }
 
   public String getProp2()
   {
     return this.prop2;
   }
 
   public void setProp2(String prop2)
   {
     this.prop2 = prop2;
   }
 
   public String getProp3()
   {
     return this.prop3;
   }
 
   public void setProp3(String prop3)
   {
     this.prop3 = prop3;
   }
 
   public String getProp4()
   {
     return this.prop4;
   }
 
   public void setProp4(String prop4)
   {
     this.prop4 = prop4;
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
 * Qualified Name:     com.zving.schema.ZCForumConfigSchema
 * JD-Core Version:    0.5.3
 */