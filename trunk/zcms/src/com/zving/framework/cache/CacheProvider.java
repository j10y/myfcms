 package com.zving.framework.cache;
 
 import com.zving.framework.utility.Mapx;
 
 public abstract class CacheProvider
 {
   protected Mapx map;
 
   public abstract Mapx get(String paramString);
 
   public Object get(String type, Object key)
   {
     return get(type).get(key);
   }
 
   public abstract String getType();
 
   public void update(String type)
   {
   }
 
   public abstract void update(String paramString, Object paramObject);
 
   public void add(String type, Mapx cm)
   {
     this.map.put(type, cm);
   }
 
   public void add(String type, Object key, Object value)
   {
     ((Mapx)this.map.get(type)).put(key, value);
   }
 
   public void remove(String type, Mapx cm)
   {
     this.map.remove(type);
   }
 
   public void remove(String type, Object key, Object value)
   {
     ((Mapx)this.map.get(type)).remove(key);
   }
 
   public abstract void init();
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.cache.CacheProvider
 * JD-Core Version:    0.5.3
 */