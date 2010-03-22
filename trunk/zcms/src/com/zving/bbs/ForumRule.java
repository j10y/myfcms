 package com.zving.bbs;
 
 import com.zving.framework.utility.Mapx;
 import com.zving.schema.ZCForumSchema;
 
 public class ForumRule
 {
   static final String NO = "N";
   static final String YES = "Y";
   Mapx map;
 
   public ForumRule(long ForumID)
   {
     this.map = new Mapx();
 
     initRule(ForumID); }
 
   public ForumRule(String ForumID) {
     this(Long.parseLong(ForumID));
   }
 
   public boolean getRule(String ruleType)
   {
     return this.map.get(ruleType).equals("Y");
   }
 
   private void initRule(long ForumID) {
     ZCForumSchema forum = new ZCForumSchema();
     forum.setID(ForumID);
     forum.fill();
     initMap(forum);
   }
 
   private void initMap(ZCForumSchema forum) {
     this.map.put("AllowTheme", forum.getAllowTheme());
     this.map.put("EditPost", forum.getEditPost());
     this.map.put("ReplyPost", forum.getReplyPost());
     this.map.put("Locked", forum.getLocked());
     this.map.put("AllowFace", forum.getAllowFace());
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.ForumRule
 * JD-Core Version:    0.5.3
 */