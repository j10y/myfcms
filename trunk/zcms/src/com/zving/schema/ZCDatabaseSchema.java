 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCDatabaseSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private String Name;
   private String ServerType;
   private String Address;
   private Long Port;
   private String UserName;
   private String Password;
   private String DBName;
   private String TestTable;
   private String Memo;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Name", 1, 2, 100, 0, true, false), 
     new SchemaColumn("ServerType", 1, 3, 10, 0, true, false), 
     new SchemaColumn("Address", 1, 4, 100, 0, true, false), 
     new SchemaColumn("Port", 7, 5, 0, 0, true, false), 
     new SchemaColumn("UserName", 1, 6, 50, 0, true, false), 
     new SchemaColumn("Password", 1, 7, 50, 0, true, false), 
     new SchemaColumn("DBName", 1, 8, 50, 0, true, false), 
     new SchemaColumn("TestTable", 1, 9, 50, 0, false, false), 
     new SchemaColumn("Memo", 1, 10, 100, 0, false, false), 
     new SchemaColumn("Prop1", 1, 11, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 12, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 13, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 14, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 15, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 16, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 17, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 18, 0, 0, false, false) };
   public static final String _TableCode = "ZCDatabase";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCDatabase values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCDatabase set ID=?,SiteID=?,Name=?,ServerType=?,Address=?,Port=?,UserName=?,Password=?,DBName=?,TestTable=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCDatabase  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCDatabase  where ID=?";
 
   public ZCDatabaseSchema()
   {
     this.TableCode = "ZCDatabase";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCDatabase values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCDatabase set ID=?,SiteID=?,Name=?,ServerType=?,Address=?,Port=?,UserName=?,Password=?,DBName=?,TestTable=?,Memo=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCDatabase  where ID=?";
     this.FillAllSQL = "select * from ZCDatabase  where ID=?";
     this.HasSetFlag = new boolean[19];
   }
 
   protected Schema newInstance() {
     return new ZCDatabaseSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCDatabaseSet();
   }
 
   public ZCDatabaseSet query() {
     return query(null, -1, -1);
   }
 
   public ZCDatabaseSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCDatabaseSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCDatabaseSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCDatabaseSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { this.Name = ((String)v); return; }
     if (i == 3) { this.ServerType = ((String)v); return; }
     if (i == 4) { this.Address = ((String)v); return; }
     if (i == 5) { if (v == null) this.Port = null; else this.Port = new Long(v.toString()); return; }
     if (i == 6) { this.UserName = ((String)v); return; }
     if (i == 7) { this.Password = ((String)v); return; }
     if (i == 8) { this.DBName = ((String)v); return; }
     if (i == 9) { this.TestTable = ((String)v); return; }
     if (i == 10) { this.Memo = ((String)v); return; }
     if (i == 11) { this.Prop1 = ((String)v); return; }
     if (i == 12) { this.Prop2 = ((String)v); return; }
     if (i == 13) { this.Prop3 = ((String)v); return; }
     if (i == 14) { this.Prop4 = ((String)v); return; }
     if (i == 15) { this.AddUser = ((String)v); return; }
     if (i == 16) { this.AddTime = ((Date)v); return; }
     if (i == 17) { this.ModifyUser = ((String)v); return; }
     if (i != 18) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.Name;
     if (i == 3) return this.ServerType;
     if (i == 4) return this.Address;
     if (i == 5) return this.Port;
     if (i == 6) return this.UserName;
     if (i == 7) return this.Password;
     if (i == 8) return this.DBName;
     if (i == 9) return this.TestTable;
     if (i == 10) return this.Memo;
     if (i == 11) return this.Prop1;
     if (i == 12) return this.Prop2;
     if (i == 13) return this.Prop3;
     if (i == 14) return this.Prop4;
     if (i == 15) return this.AddUser;
     if (i == 16) return this.AddTime;
     if (i == 17) return this.ModifyUser;
     if (i == 18) return this.ModifyTime;
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
 
   public String getServerType()
   {
     return this.ServerType;
   }
 
   public void setServerType(String serverType)
   {
     this.ServerType = serverType;
   }
 
   public String getAddress()
   {
     return this.Address;
   }
 
   public void setAddress(String address)
   {
     this.Address = address;
   }
 
   public long getPort()
   {
     if (this.Port == null) return 0L;
     return this.Port.longValue();
   }
 
   public void setPort(long port)
   {
     this.Port = new Long(port);
   }
 
   public void setPort(String port)
   {
     if (port == null) {
       this.Port = null;
       return;
     }
     this.Port = new Long(port);
   }
 
   public String getUserName()
   {
     return this.UserName;
   }
 
   public void setUserName(String userName)
   {
     this.UserName = userName;
   }
 
   public String getPassword()
   {
     return this.Password;
   }
 
   public void setPassword(String password)
   {
     this.Password = password;
   }
 
   public String getDBName()
   {
     return this.DBName;
   }
 
   public void setDBName(String dBName)
   {
     this.DBName = dBName;
   }
 
   public String getTestTable()
   {
     return this.TestTable;
   }
 
   public void setTestTable(String testTable)
   {
     this.TestTable = testTable;
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
 * Qualified Name:     com.zving.schema.ZCDatabaseSchema
 * JD-Core Version:    0.5.3
 */