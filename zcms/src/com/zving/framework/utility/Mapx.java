 package com.zving.framework.utility;
 
 import com.zving.framework.data.DataColumn;
 import com.zving.framework.data.DataTable;
 import java.util.Collection;
 import java.util.Iterator;
 import java.util.LinkedHashMap;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import org.apache.commons.logging.Log;
 
 public class Mapx extends LinkedHashMap
 {
   private static final float DEFAULT_LOAD_FACTOR = 0.75F;
   private static final int DEFAULT_INIT_CAPACITY = 16;
   private static final long serialVersionUID = 200904201752L;
   private final int maxCapacity;
   private final boolean maxFlag;
   private int hitCount;
   private int missCount;
   private long lastWarnTime;
   private ExitEventListener listener;
 
   public Mapx(int maxCapacity, boolean LRUFlag)
   {
     super(maxCapacity, 0.75F, LRUFlag);
 
     this.hitCount = 0;
 
     this.missCount = 0;
 
     this.lastWarnTime = 0L;
 
     this.maxCapacity = maxCapacity;
     this.maxFlag = true;
   }
 
   public Mapx(int maxCapacity)
   {
     this(maxCapacity, true);
   }
 
   public Mapx()
   {
     super(16, 0.75F, false);
 
     this.hitCount = 0;
 
     this.missCount = 0;
 
     this.lastWarnTime = 0L;
 
     this.maxCapacity = 0;
     this.maxFlag = false;
   }
 
   public Object clone()
   {
     Mapx map = (Mapx)super.clone();
     Object[] ks = keyArray();
     Object[] vs = valueArray();
     for (int i = 0; i < ks.length; ++i) {
       Object v = vs[i];
       if (v instanceof Mapx) {
         map.put(ks[i], ((Mapx)v).clone());
       }
     }
     return map;
   }
 
   protected boolean removeEldestEntry(Map.Entry eldest) {
     boolean flag = (this.maxFlag) && (size() > this.maxCapacity);
     if ((flag) && (this.listener != null)) {
       this.listener.onExit(eldest.getKey(), eldest.getValue());
     }
     return flag;
   }
 
   public void setExitEventListener(ExitEventListener listener)
   {
     this.listener = listener;
   }
 
   public Object[] keyArray()
   {
     if (size() == 0) {
       return new Object[0];
     }
     Object[] arr = new Object[size()];
     int i = 0;
     for (Iterator iter = keySet().iterator(); iter.hasNext(); ) {
       arr[(i++)] = iter.next();
     }
     return arr;
   }
 
   public Object[] valueArray()
   {
     if (size() == 0) {
       return new Object[0];
     }
     Object[] arr = new Object[size()];
     int i = 0;
     for (Iterator iter = values().iterator(); iter.hasNext(); ) {
       arr[(i++)] = iter.next();
     }
     return arr;
   }
 
   public String getString(Object key) {
     Object o = get(key);
     if (o == null) {
       return null;
     }
     return o.toString();
   }
 
   public void put(Object key, int num)
   {
     put(key, new Integer(num));
   }
 
   public void put(Object key, long num) {
     put(key, new Long(num));
   }
 
   public int getInt(Object key) {
     Object o = get(key);
     if (o instanceof Number)
       return ((Number)o).intValue();
     if (o != null) {
       try {
         return Integer.parseInt(o.toString());
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return 0;
   }
 
   public long getLong(Object key) {
     Object o = get(key);
     if (o instanceof Number)
       return ((Number)o).longValue();
     if (o != null) {
       try {
         return Long.parseLong(o.toString());
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return 0L;
   }
 
   public Object get(Object key)
   {
     Object o = super.get(key);
     if (this.maxFlag) {
       if (o == null)
         this.missCount += 1;
       else {
         this.hitCount += 1;
       }
       if ((this.missCount > 1000) && (this.hitCount * 1.0D / this.missCount < 0.1D) && 
         (System.currentTimeMillis() - this.lastWarnTime > 1000000L)) {
         this.lastWarnTime = System.currentTimeMillis();
         StackTraceElement[] stack = new Throwable().getStackTrace();
         StringBuffer sb = new StringBuffer();
         for (int i = 0; i < stack.length; ++i) {
           StackTraceElement ste = stack[i];
           if (ste.getClassName().indexOf("DBConnPoolImpl") == -1) {
             sb.append("\t");
             sb.append(ste.getClassName());
             sb.append(".");
             sb.append(ste.getMethodName());
             sb.append("(),行号:");
             sb.append(ste.getLineNumber());
             sb.append("\n");
           }
         }
         LogUtil.getLogger().warn("缓存命中率过低!");
         LogUtil.getLogger().warn(sb);
       }
     }
 
     return o;
   }
 
   public static Mapx convertToMapx(Map map)
   {
     Mapx mapx = new Mapx();
     mapx.putAll(map);
     return mapx;
   }
 
   public DataTable toDataTable()
   {
     DataColumn[] dcs = { new DataColumn("Key", 1), 
       new DataColumn("Value", 1) };
     Object[] ks = keyArray();
     Object[][] vs = new Object[ks.length][2];
     DataTable dt = new DataTable(dcs, vs);
     for (int i = 0; i < ks.length; ++i) {
       dt.set(i, 0, ks[i]);
       dt.set(i, 1, get(ks[i]));
     }
     return dt;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.Mapx
 * JD-Core Version:    0.5.3
 */