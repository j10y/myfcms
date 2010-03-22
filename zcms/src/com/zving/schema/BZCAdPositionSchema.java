 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCAdPositionSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private String PositionName;
   private String Code;
   private String Description;
   private String PositionType;
   private Long PaddingTop;
   private Long PaddingLeft;
   private Long PositionWidth;
   private Long PositionHeight;
   private String JsName;
   private String Prop1;
   private String Prop2;
   private String Prop3;
   private String Prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("PositionName", 1, 2, 100, 0, true, false), 
     new SchemaColumn("Code", 1, 3, 50, 0, true, false), 
     new SchemaColumn("Description", 10, 4, 0, 0, false, false), 
     new SchemaColumn("PositionType", 1, 5, 20, 0, false, false), 
     new SchemaColumn("PaddingTop", 7, 6, 0, 0, false, false), 
     new SchemaColumn("PaddingLeft", 7, 7, 0, 0, false, false), 
     new SchemaColumn("PositionWidth", 7, 8, 0, 0, false, false), 
     new SchemaColumn("PositionHeight", 7, 9, 0, 0, false, false), 
     new SchemaColumn("JsName", 1, 10, 100, 0, false, false), 
     new SchemaColumn("Prop1", 1, 11, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 12, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 13, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 14, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 15, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 16, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 17, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 18, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 19, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 20, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 21, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 22, 50, 0, false, false) };
   public static final String _TableCode = "BZCAdPosition";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCAdPosition values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCAdPosition set ID=?,SiteID=?,PositionName=?,Code=?,Description=?,PositionType=?,PaddingTop=?,PaddingLeft=?,PositionWidth=?,PositionHeight=?,JsName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCAdPosition  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCAdPosition  where ID=? and BackupNo=?";
 
   public BZCAdPositionSchema()
   {
     this.TableCode = "BZCAdPosition";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCAdPosition values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCAdPosition set ID=?,SiteID=?,PositionName=?,Code=?,Description=?,PositionType=?,PaddingTop=?,PaddingLeft=?,PositionWidth=?,PositionHeight=?,JsName=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCAdPosition  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCAdPosition  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[23];
   }
 
   protected Schema newInstance() {
     return new BZCAdPositionSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCAdPositionSet();
   }
 
   public BZCAdPositionSet query() {
     return query(null, -1, -1);
   }
 
   public BZCAdPositionSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCAdPositionSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCAdPositionSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCAdPositionSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { this.PositionName = ((String)v); return; }
     if (i == 3) { this.Code = ((String)v); return; }
     if (i == 4) { this.Description = ((String)v); return; }
     if (i == 5) { this.PositionType = ((String)v); return; }
     if (i == 6) { if (v == null) this.PaddingTop = null; else this.PaddingTop = new Long(v.toString()); return; }
     if (i == 7) { if (v == null) this.PaddingLeft = null; else this.PaddingLeft = new Long(v.toString()); return; }
     if (i == 8) { if (v == null) this.PositionWidth = null; else this.PositionWidth = new Long(v.toString()); return; }
     if (i == 9) { if (v == null) this.PositionHeight = null; else this.PositionHeight = new Long(v.toString()); return; }
     if (i == 10) { this.JsName = ((String)v); return; }
     if (i == 11) { this.Prop1 = ((String)v); return; }
     if (i == 12) { this.Prop2 = ((String)v); return; }
     if (i == 13) { this.Prop3 = ((String)v); return; }
     if (i == 14) { this.Prop4 = ((String)v); return; }
     if (i == 15) { this.AddUser = ((String)v); return; }
     if (i == 16) { this.AddTime = ((Date)v); return; }
     if (i == 17) { this.ModifyUser = ((String)v); return; }
     if (i == 18) { this.ModifyTime = ((Date)v); return; }
     if (i == 19) { this.BackupNo = ((String)v); return; }
     if (i == 20) { this.BackupOperator = ((String)v); return; }
     if (i == 21) { this.BackupTime = ((Date)v); return; }
     if (i != 22) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.PositionName;
     if (i == 3) return this.Code;
     if (i == 4) return this.Description;
     if (i == 5) return this.PositionType;
     if (i == 6) return this.PaddingTop;
     if (i == 7) return this.PaddingLeft;
     if (i == 8) return this.PositionWidth;
     if (i == 9) return this.PositionHeight;
     if (i == 10) return this.JsName;
     if (i == 11) return this.Prop1;
     if (i == 12) return this.Prop2;
     if (i == 13) return this.Prop3;
     if (i == 14) return this.Prop4;
     if (i == 15) return this.AddUser;
     if (i == 16) return this.AddTime;
     if (i == 17) return this.ModifyUser;
     if (i == 18) return this.ModifyTime;
     if (i == 19) return this.BackupNo;
     if (i == 20) return this.BackupOperator;
     if (i == 21) return this.BackupTime;
     if (i == 22) return this.BackupMemo;
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
 
   public String getPositionName()
   {
     return this.PositionName;
   }
 
   public void setPositionName(String positionName)
   {
     this.PositionName = positionName;
   }
 
   public String getCode()
   {
     return this.Code;
   }
 
   public void setCode(String code)
   {
     this.Code = code;
   }
 
   public String getDescription()
   {
     return this.Description;
   }
 
   public void setDescription(String description)
   {
     this.Description = description;
   }
 
   public String getPositionType()
   {
     return this.PositionType;
   }
 
   public void setPositionType(String positionType)
   {
     this.PositionType = positionType;
   }
 
   public long getPaddingTop()
   {
     if (this.PaddingTop == null) return 0L;
     return this.PaddingTop.longValue();
   }
 
   public void setPaddingTop(long paddingTop)
   {
     this.PaddingTop = new Long(paddingTop);
   }
 
   public void setPaddingTop(String paddingTop)
   {
     if (paddingTop == null) {
       this.PaddingTop = null;
       return;
     }
     this.PaddingTop = new Long(paddingTop);
   }
 
   public long getPaddingLeft()
   {
     if (this.PaddingLeft == null) return 0L;
     return this.PaddingLeft.longValue();
   }
 
   public void setPaddingLeft(long paddingLeft)
   {
     this.PaddingLeft = new Long(paddingLeft);
   }
 
   public void setPaddingLeft(String paddingLeft)
   {
     if (paddingLeft == null) {
       this.PaddingLeft = null;
       return;
     }
     this.PaddingLeft = new Long(paddingLeft);
   }
 
   public long getPositionWidth()
   {
     if (this.PositionWidth == null) return 0L;
     return this.PositionWidth.longValue();
   }
 
   public void setPositionWidth(long positionWidth)
   {
     this.PositionWidth = new Long(positionWidth);
   }
 
   public void setPositionWidth(String positionWidth)
   {
     if (positionWidth == null) {
       this.PositionWidth = null;
       return;
     }
     this.PositionWidth = new Long(positionWidth);
   }
 
   public long getPositionHeight()
   {
     if (this.PositionHeight == null) return 0L;
     return this.PositionHeight.longValue();
   }
 
   public void setPositionHeight(long positionHeight)
   {
     this.PositionHeight = new Long(positionHeight);
   }
 
   public void setPositionHeight(String positionHeight)
   {
     if (positionHeight == null) {
       this.PositionHeight = null;
       return;
     }
     this.PositionHeight = new Long(positionHeight);
   }
 
   public String getJsName()
   {
     return this.JsName;
   }
 
   public void setJsName(String jsName)
   {
     this.JsName = jsName;
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
 
   public String getBackupNo()
   {
     return this.BackupNo;
   }
 
   public void setBackupNo(String backupNo)
   {
     this.BackupNo = backupNo;
   }
 
   public String getBackupOperator()
   {
     return this.BackupOperator;
   }
 
   public void setBackupOperator(String backupOperator)
   {
     this.BackupOperator = backupOperator;
   }
 
   public Date getBackupTime()
   {
     return this.BackupTime;
   }
 
   public void setBackupTime(Date backupTime)
   {
     this.BackupTime = backupTime;
   }
 
   public String getBackupMemo()
   {
     return this.BackupMemo;
   }
 
   public void setBackupMemo(String backupMemo)
   {
     this.BackupMemo = backupMemo;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.schema.BZCAdPositionSchema
 * JD-Core Version:    0.5.3
 */