 package com.zving.search;
 
 import java.io.IOException;
 import org.apache.lucene.index.Term;
 import org.apache.lucene.search.BooleanClause.Occur;
 import org.apache.lucene.search.BooleanQuery;
 import org.apache.lucene.search.PrefixQuery;
 import org.apache.lucene.search.Query;
 import org.apache.lucene.search.Sort;
 import org.apache.lucene.search.SortField;
 import org.apache.lucene.search.TermQuery;
 import org.apache.lucene.search.TermRangeFilter;
 import org.apache.lucene.search.TermRangeQuery;
 import org.apache.lucene.search.WildcardQuery;
 import org.wltea.analyzer.lucene.IKQueryParser;
 
 public class SearchParameters
 {
   private BooleanQuery booleanQuery = new BooleanQuery();
 
   private Sort sort = null;
 
   private TermRangeFilter filter = null;
 
   private int pageSize = 10;
 
   private int pageIndex = 0;
   private long indexID;
 
   public void addFulltextField(String field, String query)
   {
     addFulltextField(field, query, true);
   }
 
   public void addFulltextField(String field, String query, boolean isMust)
   {
     try
     {
       Query q = IKQueryParser.parse(field.toUpperCase(), query);
       if (field.equalsIgnoreCase("Title")) {
         q.setBoost(10000.0F);
       }
       this.booleanQuery.add(q, (isMust) ? BooleanClause.Occur.MUST : BooleanClause.Occur.SHOULD);
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public void addNotEqualField(String field, String query) {
     TermQuery q = new TermQuery(new Term(field.toUpperCase(), query));
     this.booleanQuery.add(q, BooleanClause.Occur.MUST_NOT);
   }
 
   public void addRightLikeField(String field, String query)
   {
     addRightLikeField(field, query, true);
   }
 
   public void addRightLikeField(String field, String query, boolean isMust)
   {
     WildcardQuery q = new WildcardQuery(new Term(field.toUpperCase(), "*" + query));
     this.booleanQuery.add(q, (isMust) ? BooleanClause.Occur.MUST : BooleanClause.Occur.SHOULD);
   }
 
   public void addLeftLikeField(String field, String query)
   {
     addLeftLikeField(field, query, true);
   }
 
   public void addLeftLikeField(String field, String query, boolean isMust)
   {
     PrefixQuery q = new PrefixQuery(new Term(field.toUpperCase(), query));
     this.booleanQuery.add(q, (isMust) ? BooleanClause.Occur.MUST : BooleanClause.Occur.SHOULD);
   }
 
   public void addLikeField(String field, String query)
   {
     addLikeField(field, query, true);
   }
 
   public void addLikeField(String field, String query, boolean isMust)
   {
     WildcardQuery q = new WildcardQuery(new Term(field.toUpperCase(), "*" + query + "*"));
     this.booleanQuery.add(q, (isMust) ? BooleanClause.Occur.MUST : BooleanClause.Occur.SHOULD);
   }
 
   public void addEqualField(String field, String query)
   {
     addEqualField(field, query, true);
   }
 
   public void addEqualField(String field, String query, boolean isMust)
   {
     WildcardQuery q = new WildcardQuery(new Term(field.toUpperCase(), query));
     this.booleanQuery.add(q, (isMust) ? BooleanClause.Occur.MUST : BooleanClause.Occur.SHOULD);
   }
 
   public void addRangeField(String field, String valueBegin, String valueEnd)
   {
     addRangeField(field, valueBegin, valueEnd, true);
   }
 
   public void addRangeField(String field, String valueBegin, String valueEnd, boolean isMust)
   {
     TermRangeQuery q = new TermRangeQuery(field, valueBegin, valueEnd, true, true);
     this.booleanQuery.add(q, (isMust) ? BooleanClause.Occur.MUST : BooleanClause.Occur.SHOULD);
   }
 
   public void addQuery(Query q)
   {
     addQuery(q, true);
   }
 
   public void addQuery(Query q, boolean isMust)
   {
     this.booleanQuery.add(q, (isMust) ? BooleanClause.Occur.MUST : BooleanClause.Occur.SHOULD);
   }
 
   public void setSortField(String field, int dataType, boolean descFlag)
   {
     this.sort = new Sort(new SortField(field.toUpperCase(), dataType, descFlag));
   }
 
   public void setDateRange(String field, String startDate, String endDate)
   {
     this.filter = new TermRangeFilter(field.toUpperCase(), startDate, endDate, true, true);
   }
 
   public BooleanQuery getBooleanQuery() {
     return this.booleanQuery;
   }
 
   public TermRangeFilter getDateRangeFilter() {
     return this.filter;
   }
 
   public Sort getSort() {
     return this.sort;
   }
 
   public int getPageSize() {
     return this.pageSize;
   }
 
   public void setPageSize(int pageSize) {
     this.pageSize = pageSize;
   }
 
   public int getPageIndex() {
     return this.pageIndex;
   }
 
   public void setPageIndex(int pageIndex) {
     if (pageIndex < 0) {
       pageIndex = 0;
     }
     this.pageIndex = pageIndex;
   }
 
   public long getIndexID() {
     return this.indexID;
   }
 
   public void setIndexID(long indexID) {
     this.indexID = indexID;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.SearchParameters
 * JD-Core Version:    0.5.3
 */