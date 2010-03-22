 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCPageBlockSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private Long CatalogID;
   private String Name;
   private String Code;
   private Long Type;
   private String SortField;
   private String Memo;
   private String FileName;
   private String Template;
   private String Content;
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
     new SchemaColumn("CatalogID", 7, 2, 0, 0, false, false), 
     new SchemaColumn("Name", 1, 3, 100, 0, true, false), 
     new SchemaColumn("Code", 1, 4, 100, 0, true, false), 
     new SchemaColumn("Type", 7, 5, 0, 0, false, false), 
     new SchemaColumn("SortField", 1, 6, 50, 0, false, false), 
     new SchemaColumn("Memo", 1, 7, 1000, 0, false, false), 
     new SchemaColumn("FileName", 1, 8, 100, 0, true, false), 
     new SchemaColumn("Template", 1, 9, 100, 0, true, false), 
     new SchemaColumn("Content", 10, 10, 0, 0, false, false), 
     new SchemaColumn("Prop1", 1, 11, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 12, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 13, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 14, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 15, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 16, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 17, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 18, 0, 0, false, false) };
   public static final String _TableCode = "ZCPageBlock";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCPageBlock values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCPageBlock set ID=?,SiteID=?,CatalogID=?,Name=?,Code=?,Type=?,SortField=?,Memo=?,FileName=?,Template=?,Content=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCPageBlock  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCPageBlock  where ID=?";
 
   public ZCPageBlockSchema()
   {
     this.TableCode = "ZCPageBlock";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCPageBlock values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCPageBlock set ID=?,SiteID=?,CatalogID=?,Name=?,Code=?,Type=?,SortField=?,Memo=?,FileName=?,Template=?,Content=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCPageBlock  where ID=?";
     this.FillAllSQL = "select * from ZCPageBlock  where ID=?";
     this.HasSetFlag = new boolean[19];
   }
 
   protected Schema newInstance() {
     return new ZCPageBlockSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCPageBlockSet();
   }
 
   public ZCPageBlockSet query() {
     return query(null, -1, -1);
   }
 
   public ZCPageBlockSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCPageBlockSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCPageBlockSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCPageBlockSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.CatalogID = null; else this.CatalogID = new Long(v.toString()); return; }
     if (i == 3) { this.Name = ((String)v); return; }
     if (i == 4) { this.Code = ((String)v); return; }
     if (i == 5) { if (v == null) this.Type = null; else this.Type = new Long(v.toString()); return; }
     if (i == 6) { this.SortField = ((String)v); return; }
     if (i == 7) { this.Memo = ((String)v); return; }
     if (i == 8) { this.FileName = ((String)v); return; }
     if (i == 9) { this.Template = ((String)v); return; }
     if (i == 10) { this.Content = ((String)v); return; }
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
     if (i == 2) return this.CatalogID;
     if (i == 3) return this.Name;
     if (i == 4) return this.Code;
     if (i == 5) return this.Type;
     if (i == 6) return this.SortField;
     if (i == 7) return this.Memo;
     if (i == 8) return this.FileName;
     if (i == 9) return this.Template;
     if (i == 10) return this.Content;
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
 
   public long getCatalogID()
   {
     if (this.CatalogID == null) return 0L;
     return this.CatalogID.longValue();
   }
 
   public void setCatalogID(long catalogID)
   {
     this.CatalogID = new Long(catalogID);
   }
 
   public void setCatalogID(String catalogID)
   {
     if (catalogID == null) {
       this.CatalogID = null;
       return;
     }
     this.CatalogID = new Long(catalogID);
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
 
   public long getType()
   {
     if (this.Type == null) return 0L;
     return this.Type.longValue();
   }
 
   public void setType(long type)
   {
     this.Type = new Long(type);
   }
 
   public void setType(String type)
   {
     if (type == null) {
       this.Type = null;
       return;
     }
     this.Type = new Long(type);
   }
 
   public String getSortField()
   {
     return this.SortField;
   }
 
   public void setSortField(String sortField)
   {
     this.SortField = sortField;
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
   }
 
   public String getFileName()
   {
     return this.FileName;
   }
 
   public void setFileName(String fileName)
   {
     this.FileName = fileName;
   }
 
   public String getTemplate()
   {
     return this.Template;
   }
 
   public void setTemplate(String template)
   {
     this.Template = template;
   }
 
   public String getContent()
   {
     return this.Content;
   }
 
   public void setContent(String content)
   {
     this.Content = content;
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
 * Qualified Name:     com.zving.schema.ZCPageBlockSchema
 * JD-Core Version:    0.5.3
 */