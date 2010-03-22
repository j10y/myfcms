 package com.zving.framework.cache;
 
 import com.zving.framework.Config;
 import com.zving.framework.utility.Mapx;
 import java.io.File;
 import java.util.List;
 import org.dom4j.Document;
 import org.dom4j.DocumentException;
 import org.dom4j.Element;
 import org.dom4j.io.SAXReader;
 
 public class CacheManager
 {
   private static Mapx map;
 
   private static void loadConfig()
   {
     if (map == null) {
       map = new Mapx();
       String path = Config.getContextRealPath() + "WEB-INF/classes/framework.xml";
       SAXReader reader = new SAXReader(false);
       try
       {
         Document doc = reader.read(new File(path));
         Element root = doc.getRootElement();
         Element cache = root.element("cache");
         if (cache != null) {
           List types = cache.elements();
           for (int i = 0; i < types.size(); ++i) {
             Element type = (Element)types.get(i);
             String className = type.attributeValue("class");
             try {
               CacheProvider cp = (CacheProvider)Class.forName(className).newInstance();
               cp.init();
               map.put(cp.getType(), cp);
             } catch (InstantiationException e) {
               e.printStackTrace();
             } catch (IllegalAccessException e) {
               e.printStackTrace();
             } catch (ClassNotFoundException e) {
               e.printStackTrace();
             }
           }
         }
       } catch (DocumentException e) {
         e.printStackTrace();
       }
       CacheProvider cp = new CodeCache();
       cp.init();
       map.put(cp.getType(), cp);
     }
   }
 
   public static CacheProvider getCache(String name)
   {
     synchronized (CacheManager.class) {
       if (map == null) {
         loadConfig();
       }
     }
     return ((CacheProvider)map.get(name));
   }
 
   public static Object get(String providerName, String type, Object key) {
     return getCache(providerName).get(type).get(key);
   }
 
   public static Mapx get(String providerName, String type) {
     return getCache(providerName).get(type);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.cache.CacheManager
 * JD-Core Version:    0.5.3
 */