 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCTemplateTagRelaSchema extends Schema
 {
   private Long ID;
   private Long TemplateID;
   private Long SiteID;
   private String Prop1;
   private String Prop2;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("TemplateID", 7, 1, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Prop1", 1, 3, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 4, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 5, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 6, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 7, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 8, 0, 0, false, false) };
   public static final String _TableCode = "ZCTemplateTagRela";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCTemplateTagRela values(?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCTemplateTagRela set ID=?,TemplateID=?,SiteID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=? and TemplateID=?";
   protected static final String _DeleteSQL = "delete from ZCTemplateTagRela  where ID=? and TemplateID=?";
   protected static final String _FillAllSQL = "select * from ZCTemplateTagRela  where ID=? and TemplateID=?";
 
   public ZCTemplateTagRelaSchema()
   {
     this.TableCode = "ZCTemplateTagRela";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCTemplateTagRela values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCTemplateTagRela set ID=?,TemplateID=?,SiteID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=? and TemplateID=?";
     this.DeleteSQL = "delete from ZCTemplateTagRela  where ID=? and TemplateID=?";
     this.FillAllSQL = "select * from ZCTemplateTagRela  where ID=? and TemplateID=?";
     this.HasSetFlag = new boolean[9];
   }
 
   protected Schema newInstance() {
     return new ZCTemplateTagRelaSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCTemplateTagRelaSet();
   }
 
   public ZCTemplateTagRelaSet query() {
     return query(null, -1, -1);
   }
 
   public ZCTemplateTagRelaSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCTemplateTagRelaSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCTemplateTagRelaSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCTemplateTagRelaSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.TemplateID = null; else this.TemplateID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 3) { this.Prop1 = ((String)v); return; }
     if (i == 4) { this.Prop2 = ((String)v); return; }
     if (i == 5) { this.AddUser = ((String)v); return; }
     if (i == 6) { this.AddTime = ((Date)v); return; }
     if (i == 7) { this.ModifyUser = ((String)v); return; }
     if (i != 8) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.TemplateID;
     if (i == 2) return this.SiteID;
     if (i == 3) return this.Prop1;
     if (i == 4) return this.Prop2;
     if (i == 5) return this.AddUser;
     if (i == 6) return this.AddTime;
     if (i == 7) return this.ModifyUser;
     if (i == 8) return this.ModifyTime;
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
 
   public long getTemplateID()
   {
     if (this.TemplateID == null) return 0L;
     return this.TemplateID.longValue();
   }
 
   public void setTemplateID(long templateID)
   {
     this.TemplateID = new Long(templateID);
   }
 
   public void setTemplateID(String templateID)
   {
     if (templateID == null) {
       this.TemplateID = null;
       return;
     }
     this.TemplateID = new Long(templateID);
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
 * Qualified Name:     com.zving.schema.ZCTemplateTagRelaSchema
 * JD-Core Version:    0.5.3
 */