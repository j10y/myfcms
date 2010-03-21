 package com.zving.search.index;
 
 import com.zving.framework.Config;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.search.DocumentList;
 import com.zving.search.HtmlTextExtracter;
 import com.zving.search.WebDocument;
 import java.io.PrintStream;
 import org.apache.lucene.document.Document;
 import org.apache.lucene.document.Field;
 import org.apache.lucene.document.Field.Index;
 import org.apache.lucene.document.Field.Store;
 
 public class DefaultIndexer extends Indexer
 {
   private long id;
 
   public DefaultIndexer(long id)
   {
     setPath(Config.getContextRealPath() + "WEB-INF/data/index/Crawl" + id);
     this.id = id;
     setSingleThreadMode(false);
   }
 
   public void update()
   {
   }
 
   public void create() {
     try {
       DocumentList list = new DocumentList(Config.getContextRealPath() + "WEB-INF/data/" + this.id + "/");
       WebDocument wdoc = list.next();
       while (wdoc != null) {
         if (wdoc == null) {
           return;
         }
         if (wdoc.isTextContent()) {
           String content = wdoc.getContentText();
           HtmlTextExtracter hte = new HtmlTextExtracter();
           hte.setHtml(content);
           hte.setUrl(wdoc.getUrl());
           String text = hte.getContentText();
           if (StringUtil.isNotEmpty(text)) {
             String title = StringUtil.getHtmlTitle(content);
             if ((StringUtil.isNotEmpty(title)) && (text.length() > 100)) {
               Document idoc = new Document();
               idoc.add(new Field("CONTENT", text, Field.Store.YES, Field.Index.ANALYZED));
               idoc.add(
                 new Field("TITLE", StringUtil.getHtmlTitle(content), Field.Store.YES, 
                 Field.Index.ANALYZED));
               idoc.add(new Field("URL", wdoc.getUrl(), Field.Store.YES, Field.Index.NOT_ANALYZED));
               idoc.add(
                 new Field("DATE", DateUtil.toDateTimeString(wdoc.getLastmodifiedDate()), 
                 Field.Store.YES, Field.Index.NOT_ANALYZED));
               idoc.add(
                 new Field("DATE", DateUtil.toDateTimeString(wdoc.getLastmodifiedDate()), 
                 Field.Store.YES, Field.Index.NOT_ANALYZED));
               double k1 = 2.0D * Math.pow(1.0D / wdoc.getLevel(), 0.5D);
               double k2 = Math.pow(0.01D, Math.pow(1 / text.length(), 0.5D));
               idoc.setBoost((float)(k1 + k2));
               addDocument(idoc);
               System.out.println(1);
             }
           } else {
             System.out.println("未能提取正文:" + wdoc.getUrl());
           }
         }
         wdoc = list.next();
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public static void main(String[] args) {
     DefaultIndexer indexer = new DefaultIndexer(8L);
     indexer.start();
   }
 
   public long getId() {
     return this.id;
   }
 
   public void setId(long id) {
     this.id = id;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.index.DefaultIndexer
 * JD-Core Version:    0.5.3
 */