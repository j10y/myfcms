 package com.zving.search;
 
 import com.zving.cms.api.ArticleAPI;
 import com.zving.cms.dataservice.ColumnUtil;
 import com.zving.framework.Config;
 import com.zving.framework.data.DBConnConfig;
 import com.zving.framework.data.DBConnPool;
 import com.zving.framework.data.DataAccess;
 import com.zving.framework.data.DataColumn;
 import com.zving.framework.data.DataRow;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.NumberUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZCFullTextSchema;
 import com.zving.search.index.IndexUtil;
 import com.zving.search.index.Indexer;
 import java.io.File;
 import java.io.IOException;
 import java.io.StringReader;
 import java.util.List;
 import org.apache.lucene.analysis.TokenStream;
 import org.apache.lucene.document.Document;
 import org.apache.lucene.document.Field;
 import org.apache.lucene.document.Field.Index;
 import org.apache.lucene.document.Field.Store;
 import org.apache.lucene.index.CorruptIndexException;
 import org.apache.lucene.index.IndexWriter;
 import org.apache.lucene.index.Term;
 import org.apache.lucene.search.BooleanQuery;
 import org.apache.lucene.search.IndexSearcher;
 import org.apache.lucene.search.MatchAllDocsQuery;
 import org.apache.lucene.search.ScoreDoc;
 import org.apache.lucene.search.TopDocs;
 import org.apache.lucene.search.highlight.Highlighter;
 import org.apache.lucene.search.highlight.QueryScorer;
 import org.apache.lucene.search.highlight.SimpleFragmenter;
 import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
 import org.apache.lucene.store.FSDirectory;
 import org.apache.lucene.store.LockObtainFailedException;
 import org.wltea.analyzer.lucene.IKAnalyzer;
 
 public class ArticleIndexer extends Indexer
 {
   private long id;
   private int PageSize = 200;
 
   public ArticleIndexer(long fullTextID) {
     this.id = fullTextID;
     setPath(Config.getContextRealPath() + "WEB-INF/data/index/" + this.id + "/");
   }
 
   public void create()
   {
     try
     {
       ZCFullTextSchema ft = new ZCFullTextSchema();
       ft.setID(this.id);
       if (!(ft.fill())) {
         return;
       }
       if (!(ft.getType().equals("Article"))) {
         return;
       }
       if ("true".equals(Config.getValue("App.MinimalMemory"))) {
         this.PageSize = 50;
       }
       String rela = ft.getRelaText();
       int total;
       int i;
       DataTable dt;
       if (rela.indexOf("-1") >= 0) {
         QueryBuilder qb = new QueryBuilder("select ID from ZCCatalog where SiteID=?", ft.getSiteID());
         DataTable catalogs = qb.executeDataTable();
         for (int j = 0; j < catalogs.getRowCount(); ++j) {
           qb = new QueryBuilder(
             "select (select Name from zccatalog where id=zcarticle.catalogid) as catalogname,zcarticle.* from zcarticle where siteid=" + 
             ft.getSiteID() + " and status=" + 30 + " and catalogid =?", 
             catalogs.getString(j, "ID"));
           total = DataAccess.getCount(DBConnPool.getDBConnConfig().DBType, qb);
           for (i = 0; i * this.PageSize < total; ++i) {
             dt = qb.executePagedDataTable(this.PageSize, i);
             ColumnUtil.extendDocColumnData(dt, catalogs.getString(j, "ID"));
             add(dt, this.writer);
           }
         }
         return;
       }
 
       String[] catalogs = rela.split(",");
       for (int j = 0; j < catalogs.length; ++j) {
         QueryBuilder qb = new QueryBuilder(
           "select (select Name from zccatalog where id=zcarticle.catalogid) as catalogname,zcarticle.* from zcarticle where siteid=" + 
           ft.getSiteID() + " and status=" + 30 + " and catalogid =?", 
           catalogs[j]);
         total = DataAccess.getCount(DBConnPool.getDBConnConfig().DBType, qb);
         for (i = 0; i * this.PageSize < total; ++i) {
           dt = qb.executePagedDataTable(this.PageSize, i);
           ColumnUtil.extendDocColumnData(dt, catalogs[j]);
           add(dt, this.writer);
         }
       }
     }
     catch (CorruptIndexException e) {
       e.printStackTrace();
     } catch (LockObtainFailedException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   private void index(DataTable dt, IndexWriter writer, boolean updateFlag) throws CorruptIndexException, IOException {
     for (int i = 0; i < dt.getRowCount(); ++i) {
       Document doc = new Document();
       DataRow dr = dt.getDataRow(i);
       String content = IndexUtil.getTextFromHtml(dr.getString("Content"));
 
       StringBuffer sb = new StringBuffer();
       sb.append(dr.getString("Title"));
       sb.append(" ");
       sb.append(content);
       try {
         String url = ArticleAPI.getPublishedURL(Long.parseLong(dr.getString("ID")));
         for (int n = 0; n < dt.getColCount(); ++n) {
           String columnName = dt.getDataColumn(n).getColumnName();
           if (columnName.equalsIgnoreCase("URL"))
             doc.add(new Field("URL", url, Field.Store.YES, Field.Index.NO));
           else if (columnName.equalsIgnoreCase("TITLE"))
             doc.add(new Field("TITLE", dr.getString(n), Field.Store.YES, Field.Index.ANALYZED));
           else if (columnName.equalsIgnoreCase("Content"))
             doc.add(new Field("CONTENT", content, Field.Store.YES, Field.Index.ANALYZED));
           else {
             doc.add(
               new Field(columnName.toUpperCase(), dr.getString(n), Field.Store.YES, 
               Field.Index.NOT_ANALYZED));
           }
         }
 
         doc.add(new Field("_KEYWORD", sb.toString(), Field.Store.NO, Field.Index.ANALYZED));
 
         if ((updateFlag) && (StringUtil.isNotEmpty(dt.getString(i, "ModifyTime")))) {
           writer.updateDocument(new Term("ID", dt.getString(i, "ID")), doc); break label347:
         }
         label347: writer.addDocument(doc);
       }
       catch (Throwable t) {
         t.printStackTrace();
       }
     }
   }
 
   private void add(DataTable dt, IndexWriter writer) throws CorruptIndexException, IOException {
     index(dt, writer, false);
   }
 
   private void update(DataTable dt, IndexWriter writer) throws CorruptIndexException, IOException {
     index(dt, writer, true);
   }
 
   public void update()
   {
     try
     {
       ZCFullTextSchema ft = new ZCFullTextSchema();
       ft.setID(this.id);
       if (!(ft.fill())) {
         return;
       }
       if (!(ft.getType().equals("Article"))) {
         return;
       }
       if ("true".equals(Config.getValue("App.MinimalMemory"))) {
         this.PageSize = 50;
       }
       String rela = ft.getRelaText();
       int j;
       QueryBuilder qb;
       int total;
       int i;
       DataTable dt;
       DataTable dt;
       int i;
       if (rela.indexOf("-1") >= 0) {
         DataTable catalogs = new QueryBuilder("select distinct CatalogID from zcarticle where siteid=" + 
           ft.getSiteID() + " and (addtime>=?" + " or modifytime>=?) and status=" + 
           30, this.lastDate, this.lastDate).executeDataTable();
         for (j = 0; j < catalogs.getRowCount(); ++j) {
           qb = new QueryBuilder(
             "select (select Name from zccatalog where id=zcarticle.catalogid) as catalogname,zcarticle.* from zcarticle where siteid=" + 
             ft.getSiteID() + 
             " and CatalogID=? and (addtime>=? or modifytime>=?) and status=" + 
             30, catalogs.getString(j, "CatalogID"), this.lastDate);
           qb.add(this.lastDate);
           total = DataAccess.getCount(DBConnPool.getDBConnConfig().DBType, qb);
           for (i = 0; i * this.PageSize < total; ++i) {
             dt = qb.executePagedDataTable(this.PageSize, i);
             ColumnUtil.extendDocColumnData(dt, catalogs.getString(j, "ID"));
             update(dt, this.writer);
           }
         }
         dt = new QueryBuilder("select id from bzcarticle where status=30 and siteid=? and backuptime>?", 
           ft.getSiteID(), this.lastDate).executeDataTable();
         for (i = 0; i < dt.getRowCount(); ++i) {
           this.writer.deleteDocuments(new Term("ID", dt.getString(i, 0)));
         }
         dt = new QueryBuilder("select id from zcarticle where status=40 and siteid=? and modifytime>?", 
           ft.getSiteID(), this.lastDate).executeDataTable();
         for (i = 0; i < dt.getRowCount(); ++i)
           this.writer.deleteDocuments(new Term("ID", dt.getString(i, 0)));
       }
       else {
         String[] catalogs = rela.split(",");
         for (dt = 0; dt < catalogs.length; ++dt) {
           i = new QueryBuilder(
             "select (select Name from zccatalog where id=zcarticle.catalogid) as catalogname,zcarticle.* from zcarticle where status=30 and catalogid=? and (addtime>=? or modifytime>=?)", 
             catalogs[dt], this.lastDate);
           i.add(this.lastDate);
           total = DataAccess.getCount(DBConnPool.getDBConnConfig().DBType, i);
           for (i = 0; i * this.PageSize < total; ++i) {
             dt = i.executePagedDataTable(this.PageSize, i);
             ColumnUtil.extendDocColumnData(dt, catalogs[dt]);
             add(dt, this.writer);
           }
         }
         dt = new QueryBuilder("select id from bzcarticle where status=30 and siteid=? and catalogid in (" + 
           rela + ") and backuptime>?", ft.getSiteID(), this.lastDate)
           .executeDataTable();
         for (i = 0; i < dt.getRowCount(); ++i) {
           this.writer.deleteDocuments(new Term("ID", dt.getString(i, 0)));
         }
         dt = new QueryBuilder("select id from zcarticle where status=40 and siteid=? and modifytime>?", 
           ft.getSiteID(), this.lastDate).executeDataTable();
         for (i = 0; i < dt.getRowCount(); ++i) {
           this.writer.deleteDocuments(new Term("ID", dt.getString(i, 0)));
         }
       }
       this.writer.optimize(true);
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public static SearchResult search(SearchParameters sps) {
     try {
       long t = System.currentTimeMillis();
       IndexSearcher searcher = new IndexSearcher(FSDirectory.open(
         new File(Config.getContextRealPath() + 
         "WEB-INF/data/index/" + sps.getIndexID())), true);
 
       if (sps.getBooleanQuery().clauses().size() == 0) {
         MatchAllDocsQuery maq = new MatchAllDocsQuery();
         sps.addQuery(maq);
       }
 
       SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=red>", "</font>");
       Highlighter highlighter = new Highlighter(formatter, new QueryScorer(sps.getBooleanQuery()));
       int abstractLength = 150;
       highlighter.setTextFragmenter(new SimpleFragmenter(abstractLength));
       DataTable dt = new DataTable();
 
       int start = sps.getPageIndex() * sps.getPageSize();
       TopDocs docs = null;
       if (sps.getSort() != null)
         docs = searcher.search(sps.getBooleanQuery(), sps.getDateRangeFilter(), start + sps.getPageSize(), sps
           .getSort());
       else {
         docs = searcher.search(sps.getBooleanQuery(), sps.getDateRangeFilter(), start + sps.getPageSize());
       }
 
       for (int i = start; (i < start + sps.getPageSize()) && (i < docs.scoreDocs.length); ++i) {
         Document doc = searcher.doc(docs.scoreDocs[i].doc);
         if (i == start) {
           Object[] fields = doc.getFields().toArray();
           for (int j = 0; j < fields.length; ++j) {
             String name = ((Field)fields[j]).name();
             dt.insertColumn(name);
           }
         }
         String title = doc.get("TITLE");
         TokenStream tokenStream = new IKAnalyzer().tokenStream("TITLE", new StringReader(title));
 
         String tmp = highlighter.getBestFragment(tokenStream, title);
         if (StringUtil.isNotEmpty(tmp)) {
           title = tmp;
         }
         String content = doc.get("CONTENT");
         tokenStream = new IKAnalyzer().tokenStream("CONTENT", new StringReader(content));
         tmp = highlighter.getBestFragment(tokenStream, content);
         if (StringUtil.isNotEmpty(tmp)) {
           content = tmp.trim();
         }
         else if (content.length() > abstractLength) {
           content = content.substring(0, abstractLength);
         }
 
         dt.insertRow(new String[dt.getColCount()]);
         for (int j = 0; j < dt.getColCount(); ++j) {
           dt.set(i - start, j, doc.get(dt.getDataColumn(j).getColumnName()));
         }
         dt.set(i - start, "TITLE", title);
         dt.set(i - start, "CONTENT", content);
       }
       searcher.close();
 
       SearchResult r = new SearchResult();
       r.Data = dt;
       r.Total = docs.totalHits;
       r.UsedTime = NumberUtil.round((System.currentTimeMillis() - t) * 1.0D / 1000.0D, 3);
       return r;
     } catch (Exception e) {
       e.printStackTrace();
     }
     return null;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.ArticleIndexer
 * JD-Core Version:    0.5.3
 */