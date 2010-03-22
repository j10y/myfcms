 package com.zving.cms.template;
 
 import com.zving.framework.data.DataTable;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 
 public class TemplateCache
 {
   private static ThreadLocal mapLocal = new ThreadLocal();
 
   public static DataTable getDataTable(String key) {
     if (StringUtil.isEmpty(key)) {
       return null;
     }
 
     Mapx map = getCurrent();
     if (map == null) {
       map = new Mapx();
     }
     Object obj = map.get(key);
     DataTable dt = null;
     if (obj != null) {
       dt = (DataTable)obj;
     }
     return dt;
   }
 
   public static void setDataTable(String key, DataTable dt) {
     Mapx map = getCurrent();
     if (map == null) {
       map = new Mapx();
       setCurrent(map);
     }
     map.put(key, dt);
   }
 
   public static void clear() {
     Mapx map = getCurrent();
     if (map != null) {
       map.clear();
       setCurrent(map);
     }
   }
 
   public static void setCurrent(Mapx map) {
     mapLocal.set(map);
   }
 
   public static Mapx getCurrent() {
     return ((Mapx)mapLocal.get());
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.template.TemplateCache
 * JD-Core Version:    0.5.3
 */