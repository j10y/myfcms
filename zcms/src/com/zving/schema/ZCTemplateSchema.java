 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCTemplateSchema extends Schema
 {
   private Long ID;
   private String Code;
   private Long SiteID;
   private String Name;
   private String FileName;
   private String Type;
   private String Prop1;
   private String Prop2;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("Code", 1, 1, 100, 0, true, false), 
     new SchemaColumn("SiteID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Name", 1, 3, 100, 0, true, false), 
     new SchemaColumn("FileName", 1, 4, 100, 0, true, false), 
     new SchemaColumn("Type", 1, 5, 2, 0, false, false), 
     new SchemaColumn("Prop1", 1, 6, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 7, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 8, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 9, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 10, 200, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 11, 0, 0, true, false) };
   public static final String _TableCode = "ZCTemplate";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCTemplate values(?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCTemplate set ID=?,Code=?,SiteID=?,Name=?,FileName=?,Type=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCTemplate  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCTemplate  where ID=?";
 
   public ZCTemplateSchema()
   {
     this.TableCode = "ZCTemplate";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCTemplate values(?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCTemplate set ID=?,Code=?,SiteID=?,Name=?,FileName=?,Type=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCTemplate  where ID=?";
     this.FillAllSQL = "select * from ZCTemplate  where ID=?";
     this.HasSetFlag = new boolean[12];
   }
 
   protected Schema newInstance() {
     return new ZCTemplateSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCTemplateSet();
   }
 
   public ZCTemplateSet query() {
     return query(null, -1, -1);
   }
 
   public ZCTemplateSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCTemplateSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCTemplateSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCTemplateSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Code = ((String)v); return; }
     if (i == 2) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 3) { this.Name = ((String)v); return; }
     if (i == 4) { this.FileName = ((String)v); return; }
     if (i == 5) { this.Type = ((String)v); return; }
     if (i == 6) { this.Prop1 = ((String)v); return; }
     if (i == 7) { this.Prop2 = ((String)v); return; }
     if (i == 8) { this.AddUser = ((String)v); return; }
     if (i == 9) { this.AddTime = ((Date)v); return; }
     if (i == 10) { this.ModifyUser = ((String)v); return; }
     if (i != 11) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Code;
     if (i == 2) return this.SiteID;
     if (i == 3) return this.Name;
     if (i == 4) return this.FileName;
     if (i == 5) return this.Type;
     if (i == 6) return this.Prop1;
     if (i == 7) return this.Prop2;
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
 
   public String getCode()
   {
     return this.Code;
   }
 
   public void setCode(String code)
   {
     this.Code = code;
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
 
   public String getFileName()
   {
     return this.FileName;
   }
 
   public void setFileName(String fileName)
   {
     this.FileName = fileName;
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
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
 * Qualified Name:     com.zving.schema.ZCTemplateSchema
 * JD-Core Version:    0.5.3
 */