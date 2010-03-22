 package com.zving.bbs.admin;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.schema.ZCForumScoreSchema;
 import com.zving.schema.ZCForumScoreSet;
 
 public class ForumScoreSetting extends Page
 {
   public static Mapx init(Mapx params)
   {
     ZCForumScoreSet set = new ZCForumScoreSchema().query();
     params = set.get(0).toMapx();
 
     return params;
   }
 
   public void save() {
     Transaction trans = new Transaction();
     ZCForumScoreSchema forumScore = new ZCForumScoreSchema().query().get(0);
     forumScore.setValue(this.Request);
     trans.add(forumScore, 2);
     if (trans.commit())
       this.Response.setLogInfo(1, "操作成功！");
     else
       this.Response.setLogInfo(0, "操作失败");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.admin.ForumScoreSetting
 * JD-Core Version:    0.5.3
 */