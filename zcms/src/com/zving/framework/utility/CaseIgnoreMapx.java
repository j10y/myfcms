 package com.zving.framework.utility;
 
 import java.util.HashMap;
 
 public class CaseIgnoreMapx extends Mapx
 {
   private static final long serialVersionUID = 1L;
 
   public CaseIgnoreMapx()
   {
   }
 
   public CaseIgnoreMapx(HashMap map)
   {
     putAll(map);
   }
 
   public Object put(Object key, Object value)
   {
     if ((key != null) && (key instanceof String)) {
       return super.put(key.toString().toLowerCase(), value);
     }
     return super.put(key, value);
   }
 
   public Object get(Object key)
   {
     if ((key != null) && (key instanceof String)) {
       return super.get(key.toString().toLowerCase());
     }
     return super.get(key);
   }
 
   public boolean containsKey(Object key)
   {
     if ((key != null) && (key instanceof String)) {
       return super.containsKey(key.toString().toLowerCase());
     }
     return super.containsKey(key);
   }
 
   public Object remove(Object key)
   {
     if ((key != null) && (key instanceof String)) {
       return super.remove(key.toString().toLowerCase());
     }
     return super.remove(key);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.CaseIgnoreMapx
 * JD-Core Version:    0.5.3
 */