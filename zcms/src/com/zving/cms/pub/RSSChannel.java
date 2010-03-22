 package com.zving.cms.pub;
 
 import com.zving.framework.utility.Mapx;
 import java.util.ArrayList;
 
 public class RSSChannel extends RSSItem
 {
   private Mapx attrMap = new Mapx();
 
   private ArrayList items = new ArrayList();
 
   public void addItem(RSSItem item) {
     this.items.add(item);
   }
 
   public String getTitle() {
     return this.attrMap.getString("title");
   }
 
   public void setTitle(String title) {
     this.attrMap.put("title", title);
   }
 
   public int size() {
     return this.items.size();
   }
 
   public RSSItem getItem(int i) {
     return ((RSSItem)this.items.get(i));
   }
 
   public String getLink() {
     return this.attrMap.getString("link");
   }
 
   public void setLink(String link) {
     this.attrMap.put("link", link);
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
 * Qualified Name:     com.zving.cms.pub.RSSChannel
 * JD-Core Version:    0.5.3
 */