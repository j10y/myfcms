 package com.zving.search.index;
 
 import com.zving.search.crawl.FileDownloader;
 
 public class IndexerThread extends Thread
 {
   private Indexer indexer;
 
   public void run()
   {
     try
     {
       if (this.indexer.isUpdateFlag()) {
         this.indexer.update(); break label89:
       }
       label89: this.indexer.create();
     }
     finally {
       synchronized (FileDownloader.class) {
         this.indexer.aliveThreadCount -= 1;
       }
     }
   }
 
   public Indexer getIndexer() {
     return this.indexer;
   }
 
   public void setIndexer(Indexer indexer) {
     this.indexer = indexer;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.index.IndexerThread
 * JD-Core Version:    0.5.3
 */