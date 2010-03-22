 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDMenuSchema extends Schema
 {
   private Long ID;
   private Long ParentID;
   private String Name;
   private String Type;
   private String URL;
   private String Visiable;
   private String Icon;
   private Long OrderFlag;
   private String Prop1;
   private String Prop2;
   private String Memo;
   private Date AddTime;
   private String AddUser;
   private Date ModifyTime;
   private String ModifyUser;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("ParentID", 7, 1, 0, 0, true, false), 
     new SchemaColumn("Name", 1, 2, 40, 0, true, false), 
     new SchemaColumn("Type", 1, 3, 2, 0, true, false), 
     new SchemaColumn("URL", 1, 4, 200, 0, false, false), 
     new SchemaColumn("Visiable", 1, 5, 2, 0, false, false), 
     new SchemaColumn("Icon", 1, 6, 40, 0, false, false), 
     new SchemaColumn("OrderFlag", 7, 7, 0, 0, true, false), 
     new SchemaColumn("Prop1", 1, 8, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 9, 50, 0, false, false), 
     new SchemaColumn("Memo", 1, 10, 50, 0, false, false), 
     new SchemaColumn("AddTime", 0, 11, 0, 0, true, false), 
     new SchemaColumn("AddUser", 1, 12, 200, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 13, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 1, 14, 200, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 15, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 16, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 17, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 18, 50, 0, false, false) };
   public static final String _TableCode = "BZDMenu";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDMenu values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDMenu set ID=?,ParentID=?,Name=?,Type=?,URL=?,Visiable=?,Icon=?,OrderFlag=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDMenu  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDMenu  where ID=? and BackupNo=?";
 
   public BZDMenuSchema()
   {
     this.TableCode = "BZDMenu";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDMenu values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDMenu set ID=?,ParentID=?,Name=?,Type=?,URL=?,Visiable=?,Icon=?,OrderFlag=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDMenu  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDMenu  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[19];
   }
 
   protected Schema newInstance() {
     return new BZDMenuSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDMenuSet();
   }
 
   public BZDMenuSet query() {
     return query(null, -1, -1);
   }
 
   public BZDMenuSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDMenuSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDMenuSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDMenuSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.ParentID = null; else this.ParentID = new Long(v.toString()); return; }
     if (i == 2) { this.Name = ((String)v); return; }
     if (i == 3) { this.Type = ((String)v); return; }
     if (i == 4) { this.URL = ((String)v); return; }
     if (i == 5) { this.Visiable = ((String)v); return; }
     if (i == 6) { this.Icon = ((String)v); return; }
     if (i == 7) { if (v == null) this.OrderFlag = null; else this.OrderFlag = new Long(v.toString()); return; }
     if (i == 8) { this.Prop1 = ((String)v); return; }
     if (i == 9) { this.Prop2 = ((String)v); return; }
     if (i == 10) { this.Memo = ((String)v); return; }
     if (i == 11) { this.AddTime = ((Date)v); return; }
     if (i == 12) { this.AddUser = ((String)v); return; }
     if (i == 13) { this.ModifyTime = ((Date)v); return; }
     if (i == 14) { this.ModifyUser = ((String)v); return; }
     if (i == 15) { this.BackupNo = ((String)v); return; }
     if (i == 16) { this.BackupOperator = ((String)v); return; }
     if (i == 17) { this.BackupTime = ((Date)v); return; }
     if (i != 18) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.ParentID;
     if (i == 2) return this.Name;
     if (i == 3) return this.Type;
     if (i == 4) return this.URL;
     if (i == 5) return this.Visiable;
     if (i == 6) return this.Icon;
     if (i == 7) return this.OrderFlag;
     if (i == 8) return this.Prop1;
     if (i == 9) return this.Prop2;
     if (i == 10) return this.Memo;
     if (i == 11) return this.AddTime;
     if (i == 12) return this.AddUser;
     if (i == 13) return this.ModifyTime;
     if (i == 14) return this.ModifyUser;
     if (i == 15) return this.BackupNo;
     if (i == 16) return this.BackupOperator;
     if (i == 17) return this.BackupTime;
     if (i == 18) return this.BackupMemo;
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
 
   public long getParentID()
   {
     if (this.ParentID == null) return 0L;
     return this.ParentID.longValue();
   }
 
   public void setParentID(long parentID)
   {
     this.ParentID = new Long(parentID);
   }
 
   public void setParentID(String parentID)
   {
     if (parentID == null) {
       this.ParentID = null;
       return;
     }
     this.ParentID = new Long(parentID);
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
 
   public String getURL()
   {
     return this.URL;
   }
 
   public void setURL(String uRL)
   {
     this.URL = uRL;
   }
 
   public String getVisiable()
   {
     return this.Visiable;
   }
 
   public void setVisiable(String visiable)
   {
     this.Visiable = visiable;
   }
 
   public String getIcon()
   {
     return this.Icon;
   }
 
   public void setIcon(String icon)
   {
     this.Icon = icon;
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
 
   public String getMemo()
   {
     return this.Memo;
   }
 
   public void setMemo(String memo)
   {
     this.Memo = memo;
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
 * Qualified Name:     com.zving.schema.BZDMenuSchema
 * JD-Core Version:    0.5.3
 */