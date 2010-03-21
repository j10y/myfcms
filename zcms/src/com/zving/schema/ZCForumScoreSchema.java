 package com.zving.schema;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaSet;
 import java.util.Date;
 
 public class ZCForumScoreSchema extends Schema
 {
   private Long ID;
   private Long SiteID;
   private Long InitScore;
   private Long PublishTheme;
   private Long DeleteTheme;
   private Long PublishPost;
   private Long DeletePost;
   private Long Best;
   private Long CancelBest;
   private Long Bright;
   private Long CancelBright;
   private Long TopTheme;
   private Long CancelTop;
   private Long UpTheme;
   private Long DownTheme;
   private Long Upload;
   private Long Download;
   private Long Search;
   private Long Vote;
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
     new SchemaColumn("InitScore", 7, 2, 0, 0, false, false), 
     new SchemaColumn("PublishTheme", 7, 3, 0, 0, false, false), 
     new SchemaColumn("DeleteTheme", 7, 4, 0, 0, false, false), 
     new SchemaColumn("PublishPost", 7, 5, 0, 0, false, false), 
     new SchemaColumn("DeletePost", 7, 6, 0, 0, false, false), 
     new SchemaColumn("Best", 7, 7, 0, 0, false, false), 
     new SchemaColumn("CancelBest", 7, 8, 0, 0, false, false), 
     new SchemaColumn("Bright", 7, 9, 0, 0, false, false), 
     new SchemaColumn("CancelBright", 7, 10, 0, 0, false, false), 
     new SchemaColumn("TopTheme", 7, 11, 0, 0, false, false), 
     new SchemaColumn("CancelTop", 7, 12, 0, 0, false, false), 
     new SchemaColumn("UpTheme", 7, 13, 0, 0, false, false), 
     new SchemaColumn("DownTheme", 7, 14, 0, 0, false, false), 
     new SchemaColumn("Upload", 7, 15, 0, 0, false, false), 
     new SchemaColumn("Download", 7, 16, 0, 0, false, false), 
     new SchemaColumn("Search", 7, 17, 0, 0, false, false), 
     new SchemaColumn("Vote", 7, 18, 0, 0, false, false), 
     new SchemaColumn("prop1", 1, 19, 50, 0, false, false), 
     new SchemaColumn("prop2", 1, 20, 50, 0, false, false), 
     new SchemaColumn("prop3", 1, 21, 50, 0, false, false), 
     new SchemaColumn("prop4", 1, 22, 50, 0, false, false), 
     new SchemaColumn("AddUser", 1, 23, 100, 0, true, false), 
     new SchemaColumn("AddTime", 0, 24, 0, 0, true, false), 
     new SchemaColumn("ModifyUser", 1, 25, 100, 0, false, false), 
     new SchemaColumn("ModifyTime", 0, 26, 0, 0, false, false) };
   public static final String _TableCode = "ZCForumScore";
   public static final String _NameSpace = "com.zving.schema";
   protected static final String _InsertAllSQL = "insert into ZCForumScore values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
   protected static final String _UpdateAllSQL = "update ZCForumScore set ID=?,SiteID=?,InitScore=?,PublishTheme=?,DeleteTheme=?,PublishPost=?,DeletePost=?,Best=?,CancelBest=?,Bright=?,CancelBright=?,TopTheme=?,CancelTop=?,UpTheme=?,DownTheme=?,Upload=?,Download=?,Search=?,Vote=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
   protected static final String _DeleteSQL = "delete from ZCForumScore  where ID=?";
   protected static final String _FillAllSQL = "select * from ZCForumScore  where ID=?";
 
   public ZCForumScoreSchema()
   {
     this.TableCode = "ZCForumScore";
     this.NameSpace = "com.zving.schema";
     this.Columns = _Columns;
     this.InsertAllSQL = "insert into ZCForumScore values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
     this.UpdateAllSQL = "update ZCForumScore set ID=?,SiteID=?,InitScore=?,PublishTheme=?,DeleteTheme=?,PublishPost=?,DeletePost=?,Best=?,CancelBest=?,Bright=?,CancelBright=?,TopTheme=?,CancelTop=?,UpTheme=?,DownTheme=?,Upload=?,Download=?,Search=?,Vote=?,prop1=?,prop2=?,prop3=?,prop4=?,AddUser=?,AddTime=?,ModifyUser=?,ModifyTime=? where ID=?";
     this.DeleteSQL = "delete from ZCForumScore  where ID=?";
     this.FillAllSQL = "select * from ZCForumScore  where ID=?";
     this.HasSetFlag = new boolean[27];
   }
 
   protected Schema newInstance() {
     return new ZCForumScoreSchema();
   }
 
   protected SchemaSet newSet() {
     return new ZCForumScoreSet();
   }
 
   public ZCForumScoreSet query() {
     return query(null, -1, -1);
   }
 
   public ZCForumScoreSet query(QueryBuilder qb) {
     return query(qb, -1, -1);
   }
 
   public ZCForumScoreSet query(int pageSize, int pageIndex) {
     return query(null, pageSize, pageIndex);
   }
 
   public ZCForumScoreSet query(QueryBuilder qb, int pageSize, int pageIndex) {
     return ((ZCForumScoreSet)querySet(qb, pageSize, pageIndex));
   }
 
   public void setV(int i, Object v) {
     if (i == 0) { if (v == null) this.ID = null; else this.ID = new Long(v.toString()); return; }
     if (i == 1) { if (v == null) this.SiteID = null; else this.SiteID = new Long(v.toString()); return; }
     if (i == 2) { if (v == null) this.InitScore = null; else this.InitScore = new Long(v.toString()); return; }
     if (i == 3) { if (v == null) this.PublishTheme = null; else this.PublishTheme = new Long(v.toString()); return; }
     if (i == 4) { if (v == null) this.DeleteTheme = null; else this.DeleteTheme = new Long(v.toString()); return; }
     if (i == 5) { if (v == null) this.PublishPost = null; else this.PublishPost = new Long(v.toString()); return; }
     if (i == 6) { if (v == null) this.DeletePost = null; else this.DeletePost = new Long(v.toString()); return; }
     if (i == 7) { if (v == null) this.Best = null; else this.Best = new Long(v.toString()); return; }
     if (i == 8) { if (v == null) this.CancelBest = null; else this.CancelBest = new Long(v.toString()); return; }
     if (i == 9) { if (v == null) this.Bright = null; else this.Bright = new Long(v.toString()); return; }
     if (i == 10) { if (v == null) this.CancelBright = null; else this.CancelBright = new Long(v.toString()); return; }
     if (i == 11) { if (v == null) this.TopTheme = null; else this.TopTheme = new Long(v.toString()); return; }
     if (i == 12) { if (v == null) this.CancelTop = null; else this.CancelTop = new Long(v.toString()); return; }
     if (i == 13) { if (v == null) this.UpTheme = null; else this.UpTheme = new Long(v.toString()); return; }
     if (i == 14) { if (v == null) this.DownTheme = null; else this.DownTheme = new Long(v.toString()); return; }
     if (i == 15) { if (v == null) this.Upload = null; else this.Upload = new Long(v.toString()); return; }
     if (i == 16) { if (v == null) this.Download = null; else this.Download = new Long(v.toString()); return; }
     if (i == 17) { if (v == null) this.Search = null; else this.Search = new Long(v.toString()); return; }
     if (i == 18) { if (v == null) this.Vote = null; else this.Vote = new Long(v.toString()); return; }
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
     if (i == 2) return this.InitScore;
     if (i == 3) return this.PublishTheme;
     if (i == 4) return this.DeleteTheme;
     if (i == 5) return this.PublishPost;
     if (i == 6) return this.DeletePost;
     if (i == 7) return this.Best;
     if (i == 8) return this.CancelBest;
     if (i == 9) return this.Bright;
     if (i == 10) return this.CancelBright;
     if (i == 11) return this.TopTheme;
     if (i == 12) return this.CancelTop;
     if (i == 13) return this.UpTheme;
     if (i == 14) return this.DownTheme;
     if (i == 15) return this.Upload;
     if (i == 16) return this.Download;
     if (i == 17) return this.Search;
     if (i == 18) return this.Vote;
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
 
   public long getInitScore()
   {
     if (this.InitScore == null) return 0L;
     return this.InitScore.longValue();
   }
 
   public void setInitScore(long initScore)
   {
     this.InitScore = new Long(initScore);
   }
 
   public void setInitScore(String initScore)
   {
     if (initScore == null) {
       this.InitScore = null;
       return;
     }
     this.InitScore = new Long(initScore);
   }
 
   public long getPublishTheme()
   {
     if (this.PublishTheme == null) return 0L;
     return this.PublishTheme.longValue();
   }
 
   public void setPublishTheme(long publishTheme)
   {
     this.PublishTheme = new Long(publishTheme);
   }
 
   public void setPublishTheme(String publishTheme)
   {
     if (publishTheme == null) {
       this.PublishTheme = null;
       return;
     }
     this.PublishTheme = new Long(publishTheme);
   }
 
   public long getDeleteTheme()
   {
     if (this.DeleteTheme == null) return 0L;
     return this.DeleteTheme.longValue();
   }
 
   public void setDeleteTheme(long deleteTheme)
   {
     this.DeleteTheme = new Long(deleteTheme);
   }
 
   public void setDeleteTheme(String deleteTheme)
   {
     if (deleteTheme == null) {
       this.DeleteTheme = null;
       return;
     }
     this.DeleteTheme = new Long(deleteTheme);
   }
 
   public long getPublishPost()
   {
     if (this.PublishPost == null) return 0L;
     return this.PublishPost.longValue();
   }
 
   public void setPublishPost(long publishPost)
   {
     this.PublishPost = new Long(publishPost);
   }
 
   public void setPublishPost(String publishPost)
   {
     if (publishPost == null) {
       this.PublishPost = null;
       return;
     }
     this.PublishPost = new Long(publishPost);
   }
 
   public long getDeletePost()
   {
     if (this.DeletePost == null) return 0L;
     return this.DeletePost.longValue();
   }
 
   public void setDeletePost(long deletePost)
   {
     this.DeletePost = new Long(deletePost);
   }
 
   public void setDeletePost(String deletePost)
   {
     if (deletePost == null) {
       this.DeletePost = null;
       return;
     }
     this.DeletePost = new Long(deletePost);
   }
 
   public long getBest()
   {
     if (this.Best == null) return 0L;
     return this.Best.longValue();
   }
 
   public void setBest(long best)
   {
     this.Best = new Long(best);
   }
 
   public void setBest(String best)
   {
     if (best == null) {
       this.Best = null;
       return;
     }
     this.Best = new Long(best);
   }
 
   public long getCancelBest()
   {
     if (this.CancelBest == null) return 0L;
     return this.CancelBest.longValue();
   }
 
   public void setCancelBest(long cancelBest)
   {
     this.CancelBest = new Long(cancelBest);
   }
 
   public void setCancelBest(String cancelBest)
   {
     if (cancelBest == null) {
       this.CancelBest = null;
       return;
     }
     this.CancelBest = new Long(cancelBest);
   }
 
   public long getBright()
   {
     if (this.Bright == null) return 0L;
     return this.Bright.longValue();
   }
 
   public void setBright(long bright)
   {
     this.Bright = new Long(bright);
   }
 
   public void setBright(String bright)
   {
     if (bright == null) {
       this.Bright = null;
       return;
     }
     this.Bright = new Long(bright);
   }
 
   public long getCancelBright()
   {
     if (this.CancelBright == null) return 0L;
     return this.CancelBright.longValue();
   }
 
   public void setCancelBright(long cancelBright)
   {
     this.CancelBright = new Long(cancelBright);
   }
 
   public void setCancelBright(String cancelBright)
   {
     if (cancelBright == null) {
       this.CancelBright = null;
       return;
     }
     this.CancelBright = new Long(cancelBright);
   }
 
   public long getTopTheme()
   {
     if (this.TopTheme == null) return 0L;
     return this.TopTheme.longValue();
   }
 
   public void setTopTheme(long topTheme)
   {
     this.TopTheme = new Long(topTheme);
   }
 
   public void setTopTheme(String topTheme)
   {
     if (topTheme == null) {
       this.TopTheme = null;
       return;
     }
     this.TopTheme = new Long(topTheme);
   }
 
   public long getCancelTop()
   {
     if (this.CancelTop == null) return 0L;
     return this.CancelTop.longValue();
   }
 
   public void setCancelTop(long cancelTop)
   {
     this.CancelTop = new Long(cancelTop);
   }
 
   public void setCancelTop(String cancelTop)
   {
     if (cancelTop == null) {
       this.CancelTop = null;
       return;
     }
     this.CancelTop = new Long(cancelTop);
   }
 
   public long getUpTheme()
   {
     if (this.UpTheme == null) return 0L;
     return this.UpTheme.longValue();
   }
 
   public void setUpTheme(long upTheme)
   {
     this.UpTheme = new Long(upTheme);
   }
 
   public void setUpTheme(String upTheme)
   {
     if (upTheme == null) {
       this.UpTheme = null;
       return;
     }
     this.UpTheme = new Long(upTheme);
   }
 
   public long getDownTheme()
   {
     if (this.DownTheme == null) return 0L;
     return this.DownTheme.longValue();
   }
 
   public void setDownTheme(long downTheme)
   {
     this.DownTheme = new Long(downTheme);
   }
 
   public void setDownTheme(String downTheme)
   {
     if (downTheme == null) {
       this.DownTheme = null;
       return;
     }
     this.DownTheme = new Long(downTheme);
   }
 
   public long getUpload()
   {
     if (this.Upload == null) return 0L;
     return this.Upload.longValue();
   }
 
   public void setUpload(long upload)
   {
     this.Upload = new Long(upload);
   }
 
   public void setUpload(String upload)
   {
     if (upload == null) {
       this.Upload = null;
       return;
     }
     this.Upload = new Long(upload);
   }
 
   public long getDownload()
   {
     if (this.Download == null) return 0L;
     return this.Download.longValue();
   }
 
   public void setDownload(long download)
   {
     this.Download = new Long(download);
   }
 
   public void setDownload(String download)
   {
     if (download == null) {
       this.Download = null;
       return;
     }
     this.Download = new Long(download);
   }
 
   public long getSearch()
   {
     if (this.Search == null) return 0L;
     return this.Search.longValue();
   }
 
   public void setSearch(long search)
   {
     this.Search = new Long(search);
   }
 
   public void setSearch(String search)
   {
     if (search == null) {
       this.Search = null;
       return;
     }
     this.Search = new Long(search);
   }
 
   public long getVote()
   {
     if (this.Vote == null) return 0L;
     return this.Vote.longValue();
   }
 
   public void setVote(long vote)
   {
     this.Vote = new Long(vote);
   }
 
   public void setVote(String vote)
   {
     if (vote == null) {
       this.Vote = null;
       return;
     }
     this.Vote = new Long(vote);
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
 * Qualified Name:     com.zving.schema.ZCForumScoreSchema
 * JD-Core Version:    0.5.3
 */