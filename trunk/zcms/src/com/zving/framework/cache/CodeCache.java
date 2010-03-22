 package com.zving.framework.cache;
 
 import com.zving.framework.data.DataRow;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Mapx;
 import java.io.PrintStream;
 
 public class CodeCache extends CacheProvider
 {
   private Mapx codeMap;
 
   public String getType()
   {
     return "Code";
   }
 
   public Mapx get(String key) {
     if (this.codeMap == null) {
       init();
     }
     return ((Mapx)this.codeMap.get(key));
   }
 
   public void init() {
     DataTable dt = new QueryBuilder("select CodeType,ParentCode,CodeName,CodeValue from ZDCode").executeDataTable();
     this.codeMap = new Mapx();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       DataRow dr = dt.getDataRow(i);
       String codetype = dr.getString("CodeType");
       Mapx map = null;
       if (!(this.codeMap.containsKey(codetype))) {
         map = new Mapx(20000);
         this.codeMap.put(codetype, map);
       } else {
         map = (Mapx)this.codeMap.get(codetype);
       }
       if (dr.getString("ParentCode").equalsIgnoreCase("System")) {
         continue;
       }
       map.put(dr.get("CodeValue"), dr.get("CodeName"));
     }
   }
 
   public void update(String type, Object key)
   {
   }
 
   public static void main(String[] args)
   {
     System.out.println(CacheManager.getCache("Code").get("AreaCode"));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.cache.CodeCache
 * JD-Core Version:    0.5.3
 */