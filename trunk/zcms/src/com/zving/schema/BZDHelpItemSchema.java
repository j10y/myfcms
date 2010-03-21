 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZDHelpItemSchema extends Schema
 {
   private Long ID;
   private String Code;
   private String Name;
   private String Type;
   private String Content;
   private String Prop1;
   private String Prop2;
   private String Memo;
   private Date AddTime;
   private Long AddUser;
   private Date ModifyTime;
   private Long ModifyUser;
   private String BackupNo;
   private String BackupOperator;
   private Date BackupTime;
   private String BackupMemo;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("Code", 1, 1, 20, 0, true, false), 
     new SchemaColumn("Name", 1, 2, 20, 0, true, false), 
     new SchemaColumn("Type", 1, 3, 2, 0, true, false), 
     new SchemaColumn("Content", 10, 4, 0, 0, true, false), 
     new SchemaColumn("Prop1", 1, 5, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 6, 50, 0, false, false), 
     new SchemaColumn("Memo", 1, 7, 100, 0, false, false), 
     new SchemaColumn("AddTime", 0, 8, 0, 0, true, false), 
     new SchemaColumn("AddUser", 7, 9, 0, 0, true, false), 
     new SchemaColumn("ModifyTime", 0, 10, 0, 0, false, false), 
     new SchemaColumn("ModifyUser", 7, 11, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 12, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 13, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 14, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 15, 50, 0, false, false) };
   public static final String _TableCode = "BZDHelpItem";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZDHelpItem values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZDHelpItem set ID=?,Code=?,Name=?,Type=?,Content=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZDHelpItem  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZDHelpItem  where ID=? and BackupNo=?";
 
   public BZDHelpItemSchema()
   {
     this.TableCode = "BZDHelpItem";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZDHelpItem values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZDHelpItem set ID=?,Code=?,Name=?,Type=?,Content=?,Prop1=?,Prop2=?,Memo=?,AddTime=?,AddUser=?,ModifyTime=?,ModifyUser=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZDHelpItem  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZDHelpItem  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[16];
   }
 
   protected Schema newInstance() {
     return new BZDHelpItemSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZDHelpItemSet();
   }
 
   public BZDHelpItemSet query() {
     return query(null, -1, -1);
   }
 
   public BZDHelpItemSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZDHelpItemSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZDHelpItemSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZDHelpItemSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Code = ((String)v); return; }
     if (i == 2) { this.Name = ((String)v); return; }
     if (i == 3) { this.Type = ((String)v); return; }
     if (i == 4) { this.Content = ((String)v); return; }
     if (i == 5) { this.Prop1 = ((String)v); return; }
     if (i == 6) { this.Prop2 = ((String)v); return; }
     if (i == 7) { this.Memo = ((String)v); return; }
     if (i == 8) { this.AddTime = ((Date)v); return; }
     if (i == 9) { if (v == null) this.AddUser = null; else this.AddUser = new Long(v.toString()); return; }
     if (i == 10) { this.ModifyTime = ((Date)v); return; }
     if (i == 11) { if (v == null) this.ModifyUser = null; else this.ModifyUser = new Long(v.toString()); return; }
     if (i == 12) { this.BackupNo = ((String)v); return; }
     if (i == 13) { this.BackupOperator = ((String)v); return; }
     if (i == 14) { this.BackupTime = ((Date)v); return; }
     if (i != 15) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Code;
     if (i == 2) return this.Name;
     if (i == 3) return this.Type;
     if (i == 4) return this.Content;
     if (i == 5) return this.Prop1;
     if (i == 6) return this.Prop2;
     if (i == 7) return this.Memo;
     if (i == 8) return this.AddTime;
     if (i == 9) return this.AddUser;
     if (i == 10) return this.ModifyTime;
     if (i == 11) return this.ModifyUser;
     if (i == 12) return this.BackupNo;
     if (i == 13) return this.BackupOperator;
     if (i == 14) return this.BackupTime;
     if (i == 15) return this.BackupMemo;
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
 
   public long getAddUser()
   {
     if (this.AddUser == null) return 0L;
     return this.AddUser.longValue();
   }
 
   public void setAddUser(long addUser)
   {
     this.AddUser = new Long(addUser);
   }
 
   public void setAddUser(String addUser)
   {
     if (addUser == null) {
       this.AddUser = null;
       return;
     }
     this.AddUser = new Long(addUser);
   }
 
   public Date getModifyTime()
   {
     return this.ModifyTime;
   }
 
   public void setModifyTime(Date modifyTime)
   {
     this.ModifyTime = modifyTime;
   }
 
   public long getModifyUser()
   {
     if (this.ModifyUser == null) return 0L;
     return this.ModifyUser.longValue();
   }
 
   public void setModifyUser(long modifyUser)
   {
     this.ModifyUser = new Long(modifyUser);
   }
 
   public void setModifyUser(String modifyUser)
   {
     if (modifyUser == null) {
       this.ModifyUser = null;
       return;
     }
     this.ModifyUser = new Long(modifyUser);
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
 * Qualified Name:     com.zving.schema.BZDHelpItemSchema
 * JD-Core Version:    0.5.3
 */