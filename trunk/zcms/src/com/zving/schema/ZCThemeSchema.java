 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCThemeSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private Long ForumID;
   private String Subject;
   private String IPAddress;
   private String Type;
   private String Status;
   private String LastPost;
   private String LastPoster;
   private Integer ViewCount;
   private Integer ReplyCount;
   private Long OrderFlag;
   private String VerifyFlag;
   private String TopTheme;
   private String Best;
   private String Bright;
   private String Applydel;
   private Date LastPostTime;
   private Date OrderTime;
   private String prop1;
   private String prop2;
   private String prop3;
   private String prop4;
   private String AddUser;
   private Date AddTime;
   private String ModifyUser;
   private Date ModifyTime;
   public static final SchemaColumn[] _Columns = { 
     new SchemaColumn("ID", 7, 0, 0, 0, true, true), 
     new SchemaColumn("SiteID", 7, 1, 0, 0, false, false), 
     new SchemaColumn("ForumID", 7, 2, 0, 0, true, false), 
     new SchemaColumn("Subject", 1, 3, 100, 0, false, false), 
     new SchemaColumn("IPAddress", 1, 4, 20, 0, false, false), 
     new SchemaColumn("Type", 1, 5, 2, 0, false, false), 
     new SchemaColumn("Status", 1, 6, 2, 0, false, false), 
     new SchemaColumn("LastPost", 1, 7, 100, 0, false, false), 
     new SchemaColumn("LastPoster", 1, 8, 100, 0, false, false), 
     new SchemaColumn("ViewCount", 8, 9, 0, 0, false, false), 
     new SchemaColumn("ReplyCount", 8, 10, 0, 0, false, false), 
     new SchemaColumn("OrderFlag", 7, 11, 0, 0, true, false), 
     new SchemaColumn("VerifyFlag", 1, 12, 2, 0, false, false), 
     new SchemaColumn("TopTheme", 1, 13, 2, 0, false, false), 
     new SchemaColumn("Best", 1, 14, 2, 0, false, false), 
     new SchemaColumn("Bright", 1, 15, 100, 0, false, false), 
     new SchemaColumn("Applydel", 1, 16, 2, 0, false, false), 
     new SchemaColumn("LastPostTime", 0, 17, 0, 0, false, false), 
     new SchemaColumn("OrderTime", 0, 18, 0, 0, false, false), 
     new SchemaColumn("prop1", 1, 19, 50, 0, false, false), 
     new SchemaColumn("prop2", 1, 20, 50, 0, false, false), 
     new SchemaColumn("prop3", 1, 21, 50, 0, false, false), 
     new SchemaColumn("prop4", 1, 22, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 23, 100, 0, true, false), 
     new SchemaColumn("AddTime", 0, 24, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 25, 100, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 26, 0, 0, false, false) };
   public static final String _TableCode = "ZCTheme";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCTheme values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCTheme set ID=?,SiteID=?,ForumID=?,Subject=?,IPAddress=?,Type=?,Status=?,LastPost=?,LastPoster=?,ViewCount=?,ReplyCount=?,OrderFlag=?,VerifyFlag=?,TopTheme=?,Best=?,Bright=?,Applydel=?,LastPostTime=?,OrderTime=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCTheme  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCTheme  where ID=?";
 
   public ZCThemeSchema()
   {
     this.TableCode = "ZCTheme";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCTheme values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCTheme set ID=?,SiteID=?,ForumID=?,Subject=?,IPAddress=?,Type=?,Status=?,LastPost=?,LastPoster=?,ViewCount=?,ReplyCount=?,OrderFlag=?,VerifyFlag=?,TopTheme=?,Best=?,Bright=?,Applydel=?,LastPostTime=?,OrderTime=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCTheme  where ID=?";
     this.FillAllSQL = "select * from ZCTheme  where ID=?";
     this.HasSetFlag = new boolean[27];
   }
 
   protected Schema newInstance() {
     return new ZCThemeSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCThemeSet();
   }
 
   public ZCThemeSet query() {
     return query(null, -1, -1);
   }
 
   public ZCThemeSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCThemeSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCThemeSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCThemeSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.ForumID = null; else this.ForumID = new Long(v.toString()); return; }
     if (i == 3) { this.Subject = ((String)v); return; }
     if (i == 4) { this.IPAddress = ((String)v); return; }
     if (i == 5) { this.Type = ((String)v); return; }
     if (i == 6) { this.Status = ((String)v); return; }
     if (i == 7) { this.LastPost = ((String)v); return; }
     if (i == 8) { this.LastPoster = ((String)v); return; }
     if (i == 9) { if (v == null) this.ViewCount = null; else this.ViewCount = new Integer(v.toString()); return; }
     if (i == 10) { if (v == null) this.ReplyCount = null; else this.ReplyCount = new Integer(v.toString()); return; }
     if (i == 11) { if (v == null) this.OrderFlag = null; else this.OrderFlag = new Long(v.toString()); return; }
     if (i == 12) { this.VerifyFlag = ((String)v); return; }
     if (i == 13) { this.TopTheme = ((String)v); return; }
     if (i == 14) { this.Best = ((String)v); return; }
     if (i == 15) { this.Bright = ((String)v); return; }
     if (i == 16) { this.Applydel = ((String)v); return; }
     if (i == 17) { this.LastPostTime = ((Date)v); return; }
     if (i == 18) { this.OrderTime = ((Date)v); return; }
     if (i == 19) { this.prop1 = ((String)v); return; }
     if (i == 20) { this.prop2 = ((String)v); return; }
     if (i == 21) { this.prop3 = ((String)v); return; }
     if (i == 22) { this.prop4 = ((String)v); return; }
     if (i == 23) { this.AddUser = ((String)v); return; }
     if (i == 24) { this.AddTime = ((Date)v); return; }
     if (i == 25) { this.ModifyUser = ((String)v); return; }
     if (i != 26) return; this.ModifyTime = ((Date)v); return;
   }
 
   public Object getV(int i) {
     if (i == 0) return this.ID;
     if (i == 1) return this.SiteID;
     if (i == 2) return this.ForumID;
     if (i == 3) return this.Subject;
     if (i == 4) return this.IPAddress;
     if (i == 5) return this.Type;
     if (i == 6) return this.Status;
     if (i == 7) return this.LastPost;
     if (i == 8) return this.LastPoster;
     if (i == 9) return this.ViewCount;
     if (i == 10) return this.ReplyCount;
     if (i == 11) return this.OrderFlag;
     if (i == 12) return this.VerifyFlag;
     if (i == 13) return this.TopTheme;
     if (i == 14) return this.Best;
     if (i == 15) return this.Bright;
     if (i == 16) return this.Applydel;
     if (i == 17) return this.LastPostTime;
     if (i == 18) return this.OrderTime;
     if (i == 19) return this.prop1;
     if (i == 20) return this.prop2;
     if (i == 21) return this.prop3;
     if (i == 22) return this.prop4;
     if (i == 23) return this.AddUser;
     if (i == 24) return this.AddTime;
     if (i == 25) return this.ModifyUser;
     if (i == 26) return this.ModifyTime;
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
 
   public long getForumID()
   {
     if (this.ForumID == null) return 0L;
     return this.ForumID.longValue();
   }
 
   public void setForumID(long forumID)
   {
     this.ForumID = new Long(forumID);
   }
 
   public void setForumID(String forumID)
   {
     if (forumID == null) {
       this.ForumID = null;
       return;
     }
     this.ForumID = new Long(forumID);
   }
 
   public String getSubject()
   {
     return this.Subject;
   }
 
   public void setSubject(String subject)
   {
     this.Subject = subject;
   }
 
   public String getIPAddress()
   {
     return this.IPAddress;
   }
 
   public void setIPAddress(String iPAddress)
   {
     this.IPAddress = iPAddress;
   }
 
   public String getType()
   {
     return this.Type;
   }
 
   public void setType(String type)
   {
     this.Type = type;
   }
 
   public String getStatus()
   {
     return this.Status;
   }
 
   public void setStatus(String status)
   {
     this.Status = status;
   }
 
   public String getLastPost()
   {
     return this.LastPost;
   }
 
   public void setLastPost(String lastPost)
   {
     this.LastPost = lastPost;
   }
 
   public String getLastPoster()
   {
     return this.LastPoster;
   }
 
   public void setLastPoster(String lastPoster)
   {
     this.LastPoster = lastPoster;
   }
 
   public int getViewCount()
   {
     if (this.ViewCount == null) return 0;
     return this.ViewCount.intValue();
   }
 
   public void setViewCount(int viewCount)
   {
     this.ViewCount = new Integer(viewCount);
   }
 
   public void setViewCount(String viewCount)
   {
     if (viewCount == null) {
       this.ViewCount = null;
       return;
     }
     this.ViewCount = new Integer(viewCount);
   }
 
   public int getReplyCount()
   {
     if (this.ReplyCount == null) return 0;
     return this.ReplyCount.intValue();
   }
 
   public void setReplyCount(int replyCount)
   {
     this.ReplyCount = new Integer(replyCount);
   }
 
   public void setReplyCount(String replyCount)
   {
     if (replyCount == null) {
       this.ReplyCount = null;
       return;
     }
     this.ReplyCount = new Integer(replyCount);
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
 
   public String getVerifyFlag()
   {
     return this.VerifyFlag;
   }
 
   public void setVerifyFlag(String verifyFlag)
   {
     this.VerifyFlag = verifyFlag;
   }
 
   public String getTopTheme()
   {
     return this.TopTheme;
   }
 
   public void setTopTheme(String topTheme)
   {
     this.TopTheme = topTheme;
   }
 
   public String getBest()
   {
     return this.Best;
   }
 
   public void setBest(String best)
   {
     this.Best = best;
   }
 
   public String getBright()
   {
     return this.Bright;
   }
 
   public void setBright(String bright)
   {
     this.Bright = bright;
   }
 
   public String getApplydel()
   {
     return this.Applydel;
   }
 
   public void setApplydel(String applydel)
   {
     this.Applydel = applydel;
   }
 
   public Date getLastPostTime()
   {
     return this.LastPostTime;
   }
 
   public void setLastPostTime(Date lastPostTime)
   {
     this.LastPostTime = lastPostTime;
   }
 
   public Date getOrderTime()
   {
     return this.OrderTime;
   }
 
   public void setOrderTime(Date orderTime)
   {
     this.OrderTime = orderTime;
   }
 
   public String getProp1()
   {
     return this.prop1;
   }
 
   public void setProp1(String prop1)
   {
     this.prop1 = prop1;
   }
 
   public String getProp2()
   {
     return this.prop2;
   }
 
   public void setProp2(String prop2)
   {
     this.prop2 = prop2;
   }
 
   public String getProp3()
   {
     return this.prop3;
   }
 
   public void setProp3(String prop3)
   {
     this.prop3 = prop3;
   }
 
   public String getProp4()
   {
     return this.prop4;
   }
 
   public void setProp4(String prop4)
   {
     this.prop4 = prop4;
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
 * Qualified Name:     com.zving.schema.ZCThemeSchema
 * JD-Core Version:    0.5.3
 */