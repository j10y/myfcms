 package com.zving.search.crawl;
 
 import com.zving.framework.messages.LongTimeTask;
 import com.zving.framework.utility.LogUtil;
 import com.zving.search.DocumentList;
 import com.zving.search.SearchUtil;
 import com.zving.search.WebDocument;
 import org.apache.commons.logging.Log;
 
 public class UrlExtracterThread extends Thread
 {
   private DocumentList list;
   private int level;
   private boolean isBusy;
   private UrlExtracter extracter;
   private int threadIndex;
   private WebDocument doc;
 
   public void run()
   {
     this.doc = this.list.next();
     while (this.doc != null) {
       if ((this.list.getCrawler().getConfig().getMaxPageCount() > 0) && 
         (this.list.size() >= this.list.getCrawler().getConfig().getMaxPageCount())) {
         break;
       }
       if (!(this.list.getCrawler().task.checkStop())) {
         if (!(this.isBusy)) {
           synchronized (UrlExtracter.class) {
             this.extracter.busyThreadCount += 1;
           }
         }
         this.isBusy = true;
         if ((this.doc.getLevel() == this.level - 1) && (this.doc.isTextContent())) {
           String[] urls = SearchUtil.getUrlFromHtml(this.doc.getContentText());
           for (int k = 0; k < urls.length; ++k) {
             String url2 = urls[k];
             url2 = SearchUtil.normalizeUrl(url2, this.doc.getUrl());
             url2 = SearchUtil.escapeUrl(url2);
             if ((!(this.extracter.isMatchedUrl(url2, this.doc.getUrl()))) || 
               (this.list.containsKey(url2))) continue;
             if ((this.list.getCrawler().getConfig().getMaxPageCount() > 0) && 
               (this.list.size() >= this.list.getCrawler().getConfig().getMaxPageCount())) {
               break;
             }
             WebDocument doc2 = new WebDocument();
             doc2.setUrl(url2);
             doc2.setLevel(this.level);
             doc2.setRefUrl(this.doc.getUrl());
             this.list.put(doc2);
             CrawlScriptUtil util = new CrawlScriptUtil();
             util.doc = doc2;
             util.list = this.list;
 
             this.list.getCrawler().executeScript("before", util);
           }
 
           this.extracter.extractedCount += 1;
           long percent = Math.round(this.extracter.extractedCount * 10000.0D / this.extracter.size);
           long p = percent % 100L;
           String pStr = p;
           if (p < 10L) {
             pStr = "0" + p;
           }
           LogUtil.getLogger().info("Extracting,Thread " + this.threadIndex + "\tL" + this.doc.getLevel() + "\t" + 
             (percent / 
             100L) + "." + pStr + "%");
         }
         this.doc = this.list.next();
       } else {
         if (this.isBusy) {
           synchronized (FileDownloader.class) {
             this.extracter.busyThreadCount -= 1;
           }
         }
         this.doc = null;
       }
     }
     synchronized (UrlExtracter.class) {
       this.isBusy = false;
       this.extracter.busyThreadCount -= 1;
       this.extracter.aliveThreadCount -= 1;
     }
   }
 
   public int getLevel() {
     return this.level;
   }
 
   public void setLevel(int level) {
     this.level = level;
   }
 
   public DocumentList getList() {
     return this.list;
   }
 
   public void setList(DocumentList list) {
     this.list = list;
   }
 
   public UrlExtracter getExtracter() {
     return this.extracter;
   }
 
   public void setExtracter(UrlExtracter extracter) {
     this.extracter = extracter;
   }
 
   public int getThreadIndex() {
     return this.threadIndex;
   }
 
   public void setThreadIndex(int threadIndex) {
     this.threadIndex = threadIndex;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.crawl.UrlExtracterThread
 * JD-Core Version:    0.5.3
 */