 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCCustomTableSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private String Code;
   private String Name;
   private String Type;
   private Long DatabaseID;
   private String OldCode;
   private String FormContent;
   private String Memo;
   private String AllowView;
   private String AllowModify;
   private String Prop1;
   private String Prop2;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Code", 1, 2, 50, 0, true, false), 
     new SchemaColumn("Name", 1, 3, 100, 0, true, false), 
     new SchemaColumn("Type", 1, 4, 10, 0, true, false), 
     new SchemaColumn("DatabaseID", 7, 5, 0, 0, false, false), 
     new SchemaColumn("OldCode", 1, 6, 50, 0, false, false), 
     new SchemaColumn("FormContent", 10, 7, 0, 0, false, false), 
     new SchemaColumn("Memo", 1, 8, 100, 0, false, false), 
     new SchemaColumn("AllowView", 1, 9, 2, 0, true, false), 
     new SchemaColumn("AllowModify", 1, 10, 2, 0, true, false), 
     new SchemaColumn("Prop1", 1, 11, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 12, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 13, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 14, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 15, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 16, 0, 0, false, false) };
   public static final String _TableCode = "ZCCustomTable";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCCustomTable values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCCustomTable set ID=?,SiteID=?,Code=?,Name=?,Type=?,DatabaseID=?,OldCode=?,FormContent=?,Memo=?,AllowView=?,AllowModify=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCCustomTable  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCCustomTable  where ID=?";
 
   public ZCCustomTableSchema()
   {
     this.TableCode = "ZCCustomTable";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCCustomTable values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCCustomTable set ID=?,SiteID=?,Code=?,Name=?,Type=?,DatabaseID=?,OldCode=?,FormContent=?,Memo=?,AllowView=?,AllowModify=?,Prop1=?,Prop2=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCCustomTable  where ID=?";
     this.FillAllSQL = "select * from ZCCustomTable  where ID=?";
     this.HasSetFlag = new boolean[17];
   }
 
   protected Schema newInstance() {
     return new ZCCustomTableSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCCustomTableSet();
   }
 
   public ZCCustomTableSet query() {
     return query(null, -1, -1);
   }
 
   public ZCCustomTableSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCCustomTableSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCCustomTableSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCCustomTableSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { this.Code = ((String)v); return; }
     if (i == 3) { this.Name = ((String)v); return; }
     if (i == 4) { this.Type = ((String)v); return; }
     if (i == 5) { if (v == null) this.DatabaseID = null; else this.DatabaseID = new Long(v.toString()); return; }
     if (i == 6) { this.OldCode = ((String)v); return; }
     if (i == 7) { this.FormContent = ((String)v); return; }
     if (i == 8) { this.Memo = ((String)v); return; }
     if (i == 9) { this.AllowView = ((String)v); return; }
     if (i == 10) { this.AllowModify = ((String)v); return; }
     if (i == 11) { this.Prop1 = ((String)v); return; }
     if (i == 12) { this.Prop2 = ((String)v); return; }
     if (i == 13) { this.AddUser = ((String)v); return; }
     if (i == 14) { this.AddTime = ((Date)v); return; }
     if (i == 15) { this.ModifyUser = ((String)v); return; }
     if (i != 16) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.Code;
     if (i == 3) return this.Name;
     if (i == 4) return this.Type;
     if (i == 5) return this.DatabaseID;
     if (i == 6) return this.OldCode;
     if (i == 7) return this.FormContent;
     if (i == 8) return this.Memo;
     if (i == 9) return this.AllowView;
     if (i == 10) return this.AllowModify;
     if (i == 11) return this.Prop1;
     if (i == 12) return this.Prop2;
     if (i == 13) return this.AddUser;
     if (i == 14) return this.AddTime;
     if (i == 15) return this.ModifyUser;
     if (i == 16) return this.ModifyTime;
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
 
   public String getCode()
   {
     return this.Code;
   }
 
   public void setCode(String code)
   {
     this.Code = code;
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
 
   public long getDatabaseID()
   {
     if (this.DatabaseID == null) return 0L;
     return this.DatabaseID.longValue();
   }
 
   public void setDatabaseID(long databaseID)
   {
     this.DatabaseID = new Long(databaseID);
   }
 
   public void setDatabaseID(String databaseID)
   {
     if (databaseID == null) {
       this.DatabaseID = null;
       return;
     }
     this.DatabaseID = new Long(databaseID);
   }
 
   public String getOldCode()
   {
     return this.OldCode;
   }
 
   public void setOldCode(String oldCode)
   {
     this.OldCode = oldCode;
   }
 
   public String getFormContent()
   {
     return this.FormContent;
   }
 
   public void setFormContent(String formContent)
   {
     this.FormContent = formContent;
   }
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
   }
 
   public String getAllowView()
   {
     return this.AllowView;
   }
 
   public void setAllowView(String allowView)
   {
     this.AllowView = allowView;
   }
 
   public String getAllowModify()
   {
     return this.AllowModify;
   }
 
   public void setAllowModify(String allowModify)
   {
     this.AllowModify = allowModify;
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
 * Qualified Name:     com.zving.schema.ZCCustomTableSchema
 * JD-Core Version:    0.5.3
 */