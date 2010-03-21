 package com.zving.misc;
 
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.StringUtil;
 
 public class CleanArticleTitle
 {
   public static void main(String[] args)
   {
     DataTable dt = new QueryBuilder("select id,title from ZCArticle").executeDataTable();
     QueryBuilder qb = new QueryBuilder("update ZCArticle set Title=? where id=?");
     qb.setBatchMode(true);
     for (int i = 0; i < dt.getRowCount(); ++i) {
       String id = dt.getString(i, 0);
       String title = dt.getString(i, 1);
       title = StringUtil.htmlDecode(title);
       title = title.replaceAll("\\<.*?\\>", "");
       qb.add(title);
       qb.add(id);
       qb.addBatch();
     }
     qb.executeNoQuery();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.misc.CleanArticleTitle
 * JD-Core Version:    0.5.3
 */