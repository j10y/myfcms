 package com.zving.search.crawl;
 
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.RegexParser;
 import com.zving.framework.utility.ServletUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.search.DocumentList;
 import com.zving.search.SearchUtil;
 import java.util.ArrayList;
 
 public class UrlExtracter extends Thread
 {
   private int threadCount = 4;
 
   protected int aliveThreadCount = 0;
 
   protected int busyThreadCount = 0;
   protected FileDownloader fileDownloader;
   protected int size;
   protected int extractedCount;
   ArrayList urlArr = new ArrayList();
 
   ArrayList rpArr = new ArrayList();
 
   ArrayList rpFilterArr = new ArrayList();
   CrawlConfig cc;
 
   protected void init(DocumentList list, int level, FileDownloader fileDownloader)
   {
     this.fileDownloader = fileDownloader;
     this.cc = list.getCrawler().getConfig();
     String[] arr = this.cc.getUrlLevels()[level].trim().split("\n");
     this.urlArr.clear();
     this.rpArr.clear();
     String url;
     RegexParser rp;
     for (int i = 0; i < arr.length; ++i) {
       url = arr[i].trim();
       if (StringUtil.isEmpty(url)) {
         continue;
       }
       url = url.trim();
       url = SearchUtil.escapeUrl(url);
       rp = new RegexParser(url, true);
       this.urlArr.add(url);
       this.rpArr.add(rp);
     }
     if (this.cc.isFilterFlag()) {
       arr = this.cc.getFilterExpr().trim().split("\n");
       for (i = 0; i < arr.length; ++i) {
         url = arr[i].trim();
         if (StringUtil.isEmpty(url)) {
           continue;
         }
         url = url.trim();
         rp = new RegexParser(url, true);
         this.rpFilterArr.add(rp);
       }
     }
   }
 
   public void extract(DocumentList list, int level, FileDownloader fileDownloader) {
     init(list, level, fileDownloader);
     this.aliveThreadCount = this.threadCount;
     this.size = list.size();
     list.moveFirst();
     for (int i = 0; i < this.threadCount; ++i) {
       UrlExtracterThread edt = new UrlExtracterThread();
       edt.setExtracter(this);
       edt.setList(list);
       edt.setLevel(level);
       edt.setThreadIndex(i);
       edt.start();
     }
     try {
       while (this.aliveThreadCount != 0)
         Thread.sleep(1000L);
     }
     catch (InterruptedException e) {
       e.printStackTrace();
     }
   }
 
   public boolean isMatchedUrl(String url2, String refUrl)
   {
     String ext = ServletUtil.getUrlExtension(url2);
     if ((StringUtil.isNotEmpty(ext)) && 
       (((StringUtil.isEmpty(this.fileDownloader.getAllowExtension())) || 
       (this.fileDownloader.getAllowExtension().indexOf(ext) < 0))) && 
       (this.fileDownloader.getDenyExtension().indexOf(ext) >= 0)) {
       return false;
     }
 
     boolean matchedFlag = false;
     for (int i = 0; i < this.rpFilterArr.size(); ++i) {
       RegexParser rp = (RegexParser)this.rpFilterArr.get(i);
 
       Mapx[] maps = rp.getMatchedMaps(url2);
       if (maps.length != 0)
       {
         return false;
       }
     }
     for (i = 0; i < this.rpArr.size(); ++i) {
       String url = (String)this.urlArr.get(i);
       RegexParser rp = (RegexParser)this.rpArr.get(i);
 
       if (url.indexOf(47, 8) > 0) {
         String prefix = url.substring(0, url.indexOf(47, 8));
         if ((prefix.indexOf(36) < 0) && 
           (!(url2.startsWith(prefix))))
         {
           continue;
         }
 
       }
 
       Mapx[] maps = rp.getMatchedMaps(url2);
       if (maps.length == 0) {
         continue;
       }
       matchedFlag = true;
     }
 
     return matchedFlag;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.crawl.UrlExtracter
 * JD-Core Version:    0.5.3
 */