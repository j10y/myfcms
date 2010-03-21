 package com.zving.cms.pub;
 
 import com.zving.framework.utility.Mapx;
 
 public class RSSItem
 {
   private Mapx attrMap = new Mapx();
 
   public String getAuthor() {
     return this.attrMap.getString("author");
   }
 
   public void setAuthor(String author) {
     this.attrMap.put("author", author);
   }
 
   public String getDescription() {
     return this.attrMap.getString("description");
   }
 
   public void setDescription(String description) {
     this.attrMap.put("description", description);
   }
 
   public String getLink() {
     return this.attrMap.getString("link");
   }
 
   public void setLink(String link) {
     this.attrMap.put("link", link);
   }
 
   public String getPubDate() {
     return this.attrMap.getString("pubDate");
   }
 
   public void setPubDate(String pubDate) {
     this.attrMap.put("pubDate", pubDate);
   }
 
   public String getTitle() {
     return this.attrMap.getString("title");
   }
 
   public void setTitle(String title) {
     this.attrMap.put("title", title);
   }
 
   public String getComments() {
     return this.attrMap.getString("comments");
   }
 
   public void setComments(String comments) {
     this.attrMap.put("comments", comments);
   }
 
   public void setAttribute(String attrName, String attrValue) {
     this.attrMap.put(attrName, attrValue);
   }
 
   public String getAttribute(String attrName) {
     return this.attrMap.getString(attrName);
   }
 
   public Mapx getAttributeMap() {
     return this.attrMap;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.pub.RSSItem
 * JD-Core Version:    0.5.3
 */