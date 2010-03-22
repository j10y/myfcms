 package com.zving.search;
 
 import com.zving.cms.api.SearchAPI;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.io.PrintStream;
 import java.util.Date;
 
 public class ArticleSearcher
 {
   public static SearchResult search(Mapx map)
   {
     SearchParameters sps = new SearchParameters();
     String site = map.getString("site");
     String id = map.getString("id");
     String catalog = map.getString("catalog");
     if (StringUtil.isEmpty(catalog)) {
       catalog = map.getString("Catalog");
     }
     String order = map.getString("order");
     String time = map.getString("time");
     String keyword = map.getString("keyword");
     String query = map.getString("query");
     if (StringUtil.isEmpty(keyword)) {
       keyword = query;
     }
     String page = map.getString("page");
     String size = map.getString("size");
 
     if (StringUtil.isEmpty(id)) {
       id = SearchAPI.getIndexIDBySiteID(site);
     }
 
     if (StringUtil.isNotEmpty(keyword)) {
       sps.addFulltextField("Title", keyword, false);
       sps.addFulltextField("Content", keyword, false);
       sps.addFulltextField("_Keyword", keyword, true);
     }
 
     if ("time".equalsIgnoreCase(order)) {
       sps.setSortField("PublishDate", 3, true);
     }
 
     if (StringUtil.isNotEmpty(time)) {
       Date today = new Date();
       String StartDate = DateUtil.toString(DateUtil.addDay(today, -36500));
       if (time.equals("week"))
         StartDate = DateUtil.toString(DateUtil.addDay(today, -7));
       else if (time.equals("month"))
         StartDate = DateUtil.toString(DateUtil.addDay(today, -30));
       else if (time.equals("quarter")) {
         StartDate = DateUtil.toString(DateUtil.addDay(today, -90));
       }
       String EndDate = "2999-01-01";
       sps.setDateRange("PublishDate", StartDate, EndDate);
     }
     if (StringUtil.isNotEmpty(catalog)) {
       sps.addLeftLikeField("CatalogInnerCode", catalog);
     }
     if (StringUtil.isNotEmpty(page)) {
       sps.setPageIndex(Integer.parseInt(page) - 1);
     }
     if (StringUtil.isNotEmpty(size)) {
       sps.setPageSize(Integer.parseInt(size));
     }
     if (StringUtil.isEmpty(id)) {
       SearchResult sr = new SearchResult();
       sr.Data = new DataTable();
       return sr;
     }
     sps.setIndexID(Long.parseLong(id));
     return ArticleIndexer.search(sps);
   }
 
   public static SearchResult advanceSearch(Mapx map)
   {
     SearchParameters sps = new SearchParameters();
 
     String site = map.getString("site");
     String id = map.getString("id");
     String startDate = map.getString("startdate");
     String endDate = map.getString("enddate");
     String catalog = map.getString("catalog");
     String author = map.getString("author");
     String title = map.getString("title");
     String content = map.getString("content");
     String keyword = map.getString("keyword");
     String orderField = map.getString("orderfield");
     String descFlag = map.getString("descflag");
     String page = map.getString("page");
     String size = map.getString("size");
 
     if (StringUtil.isEmpty(id)) {
       id = SearchAPI.getIndexIDBySiteID(site);
     }
 
     if ((StringUtil.isNotEmpty(startDate)) && (StringUtil.isEmpty(endDate))) {
       endDate = "2099-01-01";
     }
     if ((StringUtil.isNotEmpty(endDate)) && (StringUtil.isEmpty(startDate))) {
       startDate = "1900-01-01";
     }
     if (StringUtil.isNotEmpty(startDate)) {
       sps.setDateRange("PublishDate", startDate, endDate);
     }
     if (StringUtil.isNotEmpty(catalog)) {
       sps.addLeftLikeField("CatalogInnerCode", catalog);
     }
     if (StringUtil.isNotEmpty(title)) {
       sps.addFulltextField("Title", title);
     }
     if (StringUtil.isNotEmpty(content)) {
       sps.addFulltextField("Content", content);
     }
     if (StringUtil.isNotEmpty(keyword)) {
       sps.addFulltextField("Title", keyword, false);
       sps.addFulltextField("Content", keyword, false);
       sps.addFulltextField("_Keyword", keyword, true);
     }
     if (StringUtil.isNotEmpty(orderField)) {
       boolean isDesc = "true".equals(descFlag);
       sps.setSortField(orderField, 3, isDesc);
     }
     if (StringUtil.isNotEmpty(author)) {
       sps.addEqualField("Author", author);
     }
     if (StringUtil.isNotEmpty(page)) {
       sps.setPageIndex(Integer.parseInt(page) - 1);
     }
     if (StringUtil.isNotEmpty(size)) {
       sps.setPageSize(Integer.parseInt(size));
     }
     if (StringUtil.isEmpty(id)) {
       SearchResult sr = new SearchResult();
       sr.Data = new DataTable();
       return sr;
     }
 
     sps.setIndexID(Long.parseLong(id));
     return ArticleIndexer.search(sps);
   }
 
   public static void main(String[] args)
   {
     SearchParameters sps = new SearchParameters();
     sps.setIndexID(12L);
     sps.addLikeField("ArticleImage", "upload");
 
     SearchResult sr = ArticleIndexer.search(sps);
     System.out.println(sr.Data);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.ArticleSearcher
 * JD-Core Version:    0.5.3
 */