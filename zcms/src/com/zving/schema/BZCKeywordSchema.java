 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class BZCKeywordSchema extends Schema
 {
   private Long ID;
   private String Keyword;
   private Long SiteId;
   private Long KeywordType;
   private String LinkUrl;
   private String LinkTarget;
   private String LinkAlt;
   private Long HintCount;
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
     new SchemaColumn("Keyword", 1, 1, 200, 0, true, false), 
     new SchemaColumn("SiteId", 7, 2, 0, 0, true, false), 
     new SchemaColumn("KeywordType", 7, 3, 0, 0, false, false), 
     new SchemaColumn("LinkUrl", 1, 4, 200, 0, false, false), 
     new SchemaColumn("LinkTarget", 1, 5, 10, 0, false, false), 
     new SchemaColumn("LinkAlt", 1, 6, 200, 0, false, false), 
     new SchemaColumn("HintCount", 7, 7, 0, 0, false, false), 
     new SchemaColumn("Prop1", 1, 8, 50, 0, false, false), 
     new SchemaColumn("Prop2", 1, 9, 50, 0, false, false), 
     new SchemaColumn("Prop3", 1, 10, 50, 0, false, false), 
     new SchemaColumn("Prop4", 1, 11, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 12, 200, 0, true, false), 
     new SchemaColumn("AddTime", 0, 13, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 14, 200, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 15, 0, 0, false, false), 
     new SchemaColumn("BackupNo", 1, 16, 15, 0, true, true), 
     new SchemaColumn("BackupOperator", 1, 17, 200, 0, true, false), 
     new SchemaColumn("BackupTime", 0, 18, 0, 0, true, false), 
     new SchemaColumn("BackupMemo", 1, 19, 50, 0, false, false) };
   public static final String _TableCode = "BZCKeyword";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into BZCKeyword values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update BZCKeyword set ID=?,Keyword=?,SiteId=?,KeywordType=?,LinkUrl=?,LinkTarget=?,LinkAlt=?,HintCount=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
   protected static final String _DeleteSQL = "delete from BZCKeyword  where ID=? and BackupNo=?";
   protected static final String _FillAllSQL = "select * from BZCKeyword  where ID=? and BackupNo=?";
 
   public BZCKeywordSchema()
   {
     this.TableCode = "BZCKeyword";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into BZCKeyword values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update BZCKeyword set ID=?,Keyword=?,SiteId=?,KeywordType=?,LinkUrl=?,LinkTarget=?,LinkAlt=?,HintCount=?,Prop1=?,Prop2=?,Prop3=?,Prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=?,BackupNo=?,BackupOperator=?,BackupTime=?,BackupMemo=? where ID=? and BackupNo=?";
     this.DeleteSQL = "delete from BZCKeyword  where ID=? and BackupNo=?";
     this.FillAllSQL = "select * from BZCKeyword  where ID=? and BackupNo=?";
     this.HasSetFlag = new boolean[20];
   }
 
   protected Schema newInstance() {
     return new BZCKeywordSchema();
   }
 
   protected SchemaSet newSet() {
     return new BZCKeywordSet();
   }
 
   public BZCKeywordSet query() {
     return query(null, -1, -1);
   }
 
   public BZCKeywordSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public BZCKeywordSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public BZCKeywordSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((BZCKeywordSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { this.Keyword = ((String)v); return; }
     if (i == 2) { if (v == null) this.SiteId = null; else this.SiteId = new Long(v.toString()); return; }
     if (i == 3) { if (v == null) this.KeywordType = null; else this.KeywordType = new Long(v.toString()); return; }
     if (i == 4) { this.LinkUrl = ((String)v); return; }
     if (i == 5) { this.LinkTarget = ((String)v); return; }
     if (i == 6) { this.LinkAlt = ((String)v); return; }
     if (i == 7) { if (v == null) this.HintCount = null; else this.HintCount = new Long(v.toString()); return; }
     if (i == 8) { this.Prop1 = ((String)v); return; }
     if (i == 9) { this.Prop2 = ((String)v); return; }
     if (i == 10) { this.Prop3 = ((String)v); return; }
     if (i == 11) { this.Prop4 = ((String)v); return; }
     if (i == 12) { this.AddUser = ((String)v); return; }
     if (i == 13) { this.AddTime = ((Date)v); return; }
     if (i == 14) { this.ModifyUser = ((String)v); return; }
     if (i == 15) { this.ModifyTime = ((Date)v); return; }
     if (i == 16) { this.BackupNo = ((String)v); return; }
     if (i == 17) { this.BackupOperator = ((String)v); return; }
     if (i == 18) { this.BackupTime = ((Date)v); return; }
     if (i != 19) return; this.BackupMemo = ((String)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.Keyword;
     if (i == 2) return this.SiteId;
     if (i == 3) return this.KeywordType;
     if (i == 4) return this.LinkUrl;
     if (i == 5) return this.LinkTarget;
     if (i == 6) return this.LinkAlt;
     if (i == 7) return this.HintCount;
     if (i == 8) return this.Prop1;
     if (i == 9) return this.Prop2;
     if (i == 10) return this.Prop3;
     if (i == 11) return this.Prop4;
     if (i == 12) return this.AddUser;
     if (i == 13) return this.AddTime;
     if (i == 14) return this.ModifyUser;
     if (i == 15) return this.ModifyTime;
     if (i == 16) return this.BackupNo;
     if (i == 17) return this.BackupOperator;
     if (i == 18) return this.BackupTime;
     if (i == 19) return this.BackupMemo;
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
 
   public String getKeyword()
   {
     return this.Keyword;
   }
 
   public void setKeyword(String keyword)
   {
     this.Keyword = keyword;
   }
 
   public long getSiteId()
   {
     if (this.SiteId == null) return 0L;
     return this.SiteId.longValue();
   }
 
   public void setSiteId(long siteId)
   {
     this.SiteId = new Long(siteId);
   }
 
   public void setSiteId(String siteId)
   {
     if (siteId == null) {
       this.SiteId = null;
       return;
     }
     this.SiteId = new Long(siteId);
   }
 
   public long getKeywordType()
   {
     if (this.KeywordType == null) return 0L;
     return this.KeywordType.longValue();
   }
 
   public void setKeywordType(long keywordType)
   {
     this.KeywordType = new Long(keywordType);
   }
 
   public void setKeywordType(String keywordType)
   {
     if (keywordType == null) {
       this.KeywordType = null;
       return;
     }
     this.KeywordType = new Long(keywordType);
   }
 
   public String getLinkUrl()
   {
     return this.LinkUrl;
   }
 
   public void setLinkUrl(String linkUrl)
   {
     this.LinkUrl = linkUrl;
   }
 
   public String getLinkTarget()
   {
     return this.LinkTarget;
   }
 
   public void setLinkTarget(String linkTarget)
   {
     this.LinkTarget = linkTarget;
   }
 
   public String getLinkAlt()
   {
     return this.LinkAlt;
   }
 
   public void setLinkAlt(String linkAlt)
   {
     this.LinkAlt = linkAlt;
   }
 
   public long getHintCount()
   {
     if (this.HintCount == null) return 0L;
     return this.HintCount.longValue();
   }
 
   public void setHintCount(long hintCount)
   {
     this.HintCount = new Long(hintCount);
   }
 
   public void setHintCount(String hintCount)
   {
     if (hintCount == null) {
       this.HintCount = null;
       return;
     }
     this.HintCount = new Long(hintCount);
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
 * Qualified Name:     com.zving.schema.BZCKeywordSchema
 * JD-Core Version:    0.5.3
 */