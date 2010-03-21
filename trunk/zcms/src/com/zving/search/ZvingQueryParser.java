 package com.zving.search;
 
 import org.apache.lucene.analysis.Analyzer;
 import org.apache.lucene.queryParser.ParseException;
 import org.apache.lucene.queryParser.QueryParser;
 import org.apache.lucene.search.Query;
 import org.apache.lucene.util.Version;
 
 public class ZvingQueryParser extends QueryParser
 {
   private static final char[] Filters = "+-&|!(){}[]^\"~*?:\\".toCharArray();
 
   public ZvingQueryParser(String field, Analyzer analyzer) {
     super(Version.LUCENE_CURRENT, field, analyzer);
   }
 
   public synchronized Query parse(String content) throws ParseException {
     StringBuffer sb = new StringBuffer();
     char[] cs = content.toCharArray();
     for (int i = 0; i < cs.length; ++i) {
       for (int j = 0; j < Filters.length; ++j) {
         if (cs[i] == Filters[j]) {
           sb.append("\\");
           break;
         }
       }
       sb.append(cs[i]);
     }
     return super.parse(sb.toString());
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.search.ZvingQueryParser
 * JD-Core Version:    0.5.3
 */