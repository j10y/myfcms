 package com.zving.bbs.admin;
 
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 
 public class ForumScore
 {
   public long InitScore;
   public long PublishTheme;
   public long DeleteTheme;
   public long PublishPost;
   public long DeletePost;
   public long Best;
   public long CancelBest;
   public long Bright;
   public long CancelBright;
   public long TopTheme;
   public long CancelTop;
   public long UpTheme;
   public long DownTheme;
   public long Upload;
   public long Download;
   public long Search;
   public long Vote;
 
   public ForumScore()
   {
     init(); }
 
   public ForumScore(String SiteID) {
     this(Long.parseLong(SiteID)); }
 
   public ForumScore(long SiteID) {
     init(SiteID);
   }
 
   public void init(long SiteID) {
     String sql = "select * from ZCForumScore where SiteID=" + SiteID;
     DataTable dt = new QueryBuilder(sql).executeDataTable();
     this.InitScore = dt.getLong(0, "InitScore");
     this.PublishTheme = dt.getLong(0, "PublishTheme");
     this.DeleteTheme = dt.getLong(0, "DeleteTheme");
     this.PublishPost = dt.getLong(0, "PublishPost");
     this.DeletePost = dt.getLong(0, "DeletePost");
     this.Best = dt.getLong(0, "Best");
     this.CancelBest = dt.getLong(0, "CancelBest");
     this.Bright = dt.getLong(0, "Bright");
     this.CancelBright = dt.getLong(0, "CancelBright");
     this.TopTheme = dt.getLong(0, "TopTheme");
     this.CancelTop = dt.getLong(0, "CancelTop");
     this.UpTheme = dt.getLong(0, "UpTheme");
     this.DownTheme = dt.getLong(0, "DownTheme");
     this.Upload = dt.getLong(0, "Upload");
     this.Download = dt.getLong(0, "Download");
     this.Search = dt.getLong(0, "Search");
     this.Vote = dt.getLong(0, "Vote"); }
 
   public void init() {
     init(ForumUtil.getCurrentBBSSiteID());
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.admin.ForumScore
 * JD-Core Version:    0.5.3
 */