 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCTemplateBlockRelaSchema extends Schema
 {
   private Long SiteID;
   private String FileName;
   private Long BlockID;
   private String Prop1;
   private String Prop2;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("SiteID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("FileName", 1, 1, 100, 0, true, true), 
     new SchemaColumn("BlockID", 7, 2, 0, 0, true, true), 
     new SchemaColumn("Prop1", 1, 3, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 4, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 5, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 6, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 7, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 8, 0, 0, false, false) };
   public static final String _TableCode = "ZCTemplateBlockRela";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCTemplateBlockRela values(?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCTemplateBlockRela set SiteID=?,FileName=?,BlockID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where SiteID=? and FileName=? and BlockID=?";
   protected static final String _DeleteSQL = "delete from ZCTemplateBlockRela  where SiteID=? and FileName=? and BlockID=?";
   protected static final String _FillAllSQL = "select * from ZCTemplateBlockRela  where SiteID=? and FileName=? and BlockID=?";
 
   public ZCTemplateBlockRelaSchema()
   {
     this.TableCode = "ZCTemplateBlockRela";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCTemplateBlockRela values(?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCTemplateBlockRela set SiteID=?,FileName=?,BlockID=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where SiteID=? and FileName=? and BlockID=?";
     this.DeleteSQL = "delete from ZCTemplateBlockRela  where SiteID=? and FileName=? and BlockID=?";
     this.FillAllSQL = "select * from ZCTemplateBlockRela  where SiteID=? and FileName=? and BlockID=?";
     this.HasSetFlag = new boolean[9];
   }
 
   protected Schema newInstance() {
     return new ZCTemplateBlockRelaSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCTemplateBlockRelaSet();
   }
 
   public ZCTemplateBlockRelaSet query() {
     return query(null, -1, -1);
   }
 
   public ZCTemplateBlockRelaSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCTemplateBlockRelaSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCTemplateBlockRelaSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCTemplateBlockRelaSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 1) { this.FileName = ((String)v); return; }
     if (i == 2) { if (v == null) this.BlockID = null; else this.BlockID = new Long(v.toString()); return; }
     if (i == 3) { this.Prop1 = ((String)v); return; }
     if (i == 4) { this.Prop2 = ((String)v); return; }
     if (i == 5) { this.AddUser = ((String)v); return; }
     if (i == 6) { this.AddTime = ((Date)v); return; }
     if (i == 7) { this.ModifyUser = ((String)v); return; }
     if (i != 8) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.SiteID;
     if (i == 1) return this.FileName;
     if (i == 2) return this.BlockID;
     if (i == 3) return this.Prop1;
     if (i == 4) return this.Prop2;
     if (i == 5) return this.AddUser;
     if (i == 6) return this.AddTime;
     if (i == 7) return this.ModifyUser;
     if (i == 8) return this.ModifyTime;
     return null;
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
 
   public String getFileName()
   {
     return this.FileName;
   }
 
   public void setFileName(String fileName)
   {
     this.FileName = fileName;
   }
 
   public long getBlockID()
   {
     if (this.BlockID == null) return 0L;
     return this.BlockID.longValue();
   }
 
   public void setBlockID(long blockID)
   {
     this.BlockID = new Long(blockID);
   }
 
   public void setBlockID(String blockID)
   {
     if (blockID == null) {
       this.BlockID = null;
       return;
     }
     this.BlockID = new Long(blockID);
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
 * Qualified Name:     com.zving.schema.ZCTemplateBlockRelaSchema
 * JD-Core Version:    0.5.3
 */