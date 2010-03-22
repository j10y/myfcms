 package com.zving.search;
 
 import com.zving.framework.utility.FileCachedMapx;
 import com.zving.framework.utility.FileCachedMapx.Entry;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.search.crawl.Crawler;
 import java.io.PrintStream;
 
 public class DocumentList
 {
   private FileCachedMapx docMap = null;
 
   private FileCachedMapx contentMap = null;
 
   public FileCachedMapx.Entry lastEntry = null;
 
   public FileCachedMapx.Entry lastEmptyEntry = null;
   private String path;
   private Crawler crawler;
 
   public Crawler getCrawler()
   {
     return this.crawler;
   }
 
   public void setCrawler(Crawler crawler) {
     this.crawler = crawler;
   }
 
   public DocumentList(String cacheDir) {
     this.path = cacheDir;
     if ((!(this.path.endsWith("/"))) && (!(this.path.endsWith("\\")))) {
       this.path += "/";
     }
     this.docMap = new FileCachedMapx(this.path + "doc/", 1000000);
     this.docMap.setMaxItemInMemory(0);
     this.contentMap = new FileCachedMapx(this.path + "content/", 1000000);
     this.contentMap.setMaxItemInMemory(0);
   }
 
   public synchronized WebDocument next()
   {
     FileCachedMapx.Entry e = null;
     if (this.lastEntry == null) {
       e = this.lastEntry = this.docMap.firstEntry();
     } else {
       e = this.lastEntry.next();
       if (e != null) {
         this.lastEntry = e;
       }
     }
     if (e == null) {
       return null;
     }
     WebDocument doc = new WebDocument();
     doc.parseBytes((byte[])e.getValue());
     if (doc == null) {
       System.out.println("发生致命错误：" + e.getKey());
     }
     doc.setList(this);
     return doc;
   }
 
   public synchronized WebDocument nextEmpty() {
     FileCachedMapx.Entry e = null;
     if (this.lastEmptyEntry == null)
       e = this.docMap.firstEntry();
     else {
       e = this.lastEmptyEntry.next();
     }
     while (e != null) {
       if (!(this.contentMap.containsKey(e.getKey()))) {
         this.lastEmptyEntry = e;
         break;
       }
       e = e.next();
     }
     if (e == null) {
       return null;
     }
     WebDocument doc = new WebDocument();
     doc.parseBytes((byte[])e.getValue());
     doc.setList(this);
     return doc;
   }
 
   public synchronized void moveFirst() {
     this.lastEntry = null;
   }
 
   public synchronized void put(WebDocument doc) {
     String url = dealUrl(doc.getUrl());
     doc.setUrl(url);
     this.docMap.put(url, doc.toBytes());
     doc.setList(this);
     if (doc.getContent() != null)
       this.contentMap.put(url, doc.getContent());
   }
 
   public synchronized boolean hasContent(String url)
   {
     return this.contentMap.containsKey(url);
   }
 
   public synchronized void remove(String url) {
     url = dealUrl(url);
     this.docMap.remove(url);
     this.contentMap.remove(url);
   }
 
   public WebDocument get(String url) {
     url = dealUrl(url);
     WebDocument doc = new WebDocument();
     byte[] bs = this.docMap.getByteArray(url);
     if (bs == null) {
       return null;
     }
     doc.parseBytes(bs);
     doc.setList(this);
     return doc;
   }
 
   public boolean containsKey(String url) {
     return this.docMap.containsKey(url);
   }
 
   public byte[] getContent(String url) {
     url = dealUrl(url);
     return ((byte[])this.contentMap.get(url));
   }
 
   public int size() {
     return this.docMap.size();
   }
 
   public void save() {
     this.docMap.save();
     this.contentMap.save();
   }
 
   public void close() {
     this.docMap.close();
     this.contentMap.close();
   }
 
   public void toDir(String path)
   {
     moveFirst();
     WebDocument doc = next();
     while (doc != null) {
       if (hasContent(doc.getUrl())) {
         String url = doc.getUrl();
         FileUtil.writeByte(path + "/" + StringUtil.urlEncode(url), doc.getContent());
       }
       doc = next();
     }
   }
 
   public void delete() {
     close();
     FileUtil.delete(this.path);
     this.docMap = null;
     this.contentMap = null;
   }
 
   private static String dealUrl(String url) {
     if (url.endsWith("/")) {
       url = url.substring(0, url.length() - 1);
     }
     if (url.indexOf("#") > 0) {
       url = url.substring(0, url.indexOf("#"));
     }
     url = SearchUtil.escapeUrl(url);
     return url;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.DocumentList
 * JD-Core Version:    0.5.3
 */