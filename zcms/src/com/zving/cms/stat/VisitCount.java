 package com.zving.cms.stat;
 
 import com.zving.framework.utility.ExitEventListener;
 import com.zving.framework.utility.Mapx;
 
 public class VisitCount
   implements Cloneable
 {
   private Mapx siteMap = new Mapx();
   private static VisitCount instance;
 
   public synchronized Object clone()
   {
     VisitCount vc = new VisitCount();
     vc.siteMap = ((Mapx)this.siteMap.clone());
     long[] sites = vc.getSites();
     for (int i = 0; i < sites.length; ++i) {
       Mapx typeMap = (Mapx)vc.siteMap.get(new Long(sites[i]));
       Object[] types = typeMap.keyArray();
       for (int j = 0; j < types.length; ++j) {
         Mapx subtypeMap = (Mapx)typeMap.get(types[j]);
         Object[] subtypes = subtypeMap.keyArray();
         for (int m = 0; m < subtypes.length; ++m) {
           Mapx itemMap = (Mapx)subtypeMap.get(subtypes[m]);
           Object[] items = itemMap.keyArray();
           for (int k = 0; k < items.length; ++k) {
             ItemValue v = (ItemValue)itemMap.get(items[k]);
             itemMap.put(items[k], v.clone());
           }
         }
       }
     }
     return vc;
   }
 
   public static VisitCount getInstance() {
     if (instance == null) {
       synchronized (VisitCount.class) {
         if (instance == null) {
           instance = new VisitCount();
         }
       }
     }
     return instance;
   }
 
   public synchronized long get(long siteID, String type, String subType, String item) {
     Mapx typeMap = (Mapx)this.siteMap.get(new Long(siteID));
     if (typeMap == null) {
       return 0L;
     }
     Mapx subtypeMap = (Mapx)typeMap.get(type);
     if (subtypeMap == null) {
       return 0L;
     }
     Mapx itemMap = (Mapx)subtypeMap.get(subType);
     if (itemMap == null) {
       return 0L;
     }
     ItemValue v = (ItemValue)itemMap.get(item);
     if (v == null) {
       return 0L;
     }
     if (v.isAverageValue) {
       if (v.Divisor == 0) {
         return 0L;
       }
       return new Double(Math.ceil(v.Count * 1.0D / v.Divisor)).intValue();
     }
     return v.Count;
   }
 
   public synchronized boolean isNeedInsert(long siteID, String type, String subType, String item) {
     Mapx typeMap = (Mapx)this.siteMap.get(new Long(siteID));
     if (typeMap == null) {
       return false;
     }
     Mapx subtypeMap = (Mapx)typeMap.get(type);
     if (subtypeMap == null) {
       return false;
     }
     Mapx itemMap = (Mapx)subtypeMap.get(subType);
     if (itemMap == null) {
       return false;
     }
     ItemValue v = (ItemValue)itemMap.get(item);
     if (v == null) {
       return false;
     }
     return v.isNeedInsert;
   }
 
   public synchronized void setNotNeedInsert(long siteID, String type, String subType, String item) {
     Mapx typeMap = (Mapx)this.siteMap.get(new Long(siteID));
     if (typeMap == null) {
       return;
     }
     Mapx subtypeMap = (Mapx)typeMap.get(type);
     if (subtypeMap == null) {
       return;
     }
     Mapx itemMap = (Mapx)subtypeMap.get(subType);
     if (itemMap == null) {
       return;
     }
     ItemValue v = (ItemValue)itemMap.get(item);
     if (v == null) {
       return;
     }
     v.isNeedInsert = false;
   }
 
   public synchronized void add(long siteID, String type, String subType, String item) {
     add(siteID, type, subType, item, 1, true);
   }
 
   public synchronized void add(long siteID, String type, String subType, String item, boolean isNeedInsert) {
     add(siteID, type, subType, item, 1, isNeedInsert);
   }
 
   public synchronized void add(long siteID, String type, String subType, String item, int count, boolean isNeedInsert)
   {
     Mapx typeMap = (Mapx)this.siteMap.get(new Long(siteID));
     if (typeMap == null) {
       typeMap = new Mapx();
       this.siteMap.put(new Long(siteID), typeMap);
     }
     Mapx subtypeMap = (Mapx)typeMap.get(type);
     if (subtypeMap == null) {
       subtypeMap = new Mapx();
       typeMap.put(type, subtypeMap);
     }
     Mapx itemMap = (Mapx)subtypeMap.get(subType);
     if (itemMap == null) {
       itemMap = new Mapx();
       subtypeMap.put(subType, itemMap);
     }
     ItemValue v = (ItemValue)itemMap.get(item);
     if (v == null) {
       v = new ItemValue(count, 0, false, isNeedInsert);
       itemMap.put(item, v);
     } else {
       v.Count += count;
     }
   }
 
   public synchronized void set(long siteID, String type, String subType, String item, int count) {
     set(siteID, type, subType, item, count, true);
   }
 
   public synchronized void set(long siteID, String type, String subType, String item, long count, boolean isNeedInsert) {
     Mapx typeMap = (Mapx)this.siteMap.get(new Long(siteID));
     if (typeMap == null) {
       typeMap = new Mapx();
       this.siteMap.put(new Long(siteID), typeMap);
     }
     Mapx subtypeMap = (Mapx)typeMap.get(type);
     if (subtypeMap == null) {
       subtypeMap = new Mapx();
       typeMap.put(type, subtypeMap);
     }
     Mapx itemMap = (Mapx)subtypeMap.get(subType);
     if (itemMap == null) {
       itemMap = new Mapx();
       subtypeMap.put(subType, itemMap);
     }
     ItemValue v = (ItemValue)itemMap.get(item);
     if (v == null) {
       v = new ItemValue(count, 0, false, isNeedInsert);
       itemMap.put(item, v);
     }
     else
     {
       v.Count = count;
     }
   }
 
   public synchronized void addAverage(long siteID, String type, String subType, String item, long count) {
     addAverage(siteID, type, subType, item, count, true);
   }
 
   public synchronized void addAverage(long siteID, String type, String subType, String item, long count, boolean isNeedInsert)
   {
     Mapx typeMap = (Mapx)this.siteMap.get(new Long(siteID));
     if (typeMap == null) {
       typeMap = new Mapx();
       this.siteMap.put(new Long(siteID), typeMap);
     }
     Mapx subtypeMap = (Mapx)typeMap.get(type);
     if (subtypeMap == null) {
       subtypeMap = new Mapx();
       typeMap.put(type, subtypeMap);
     }
     Mapx itemMap = (Mapx)subtypeMap.get(subType);
     if (itemMap == null) {
       itemMap = new Mapx();
       subtypeMap.put(subType, itemMap);
     }
     ItemValue v = (ItemValue)itemMap.get(item);
     if (v == null) {
       v = new ItemValue(count, 1, true, isNeedInsert);
       itemMap.put(item, v);
     } else {
       v.Count += count;
       v.Divisor += 1;
     }
   }
 
   public synchronized void initLRUMap(long siteID, String type, String subType, int maxSize, ExitEventListener listener)
   {
     Mapx typeMap = (Mapx)this.siteMap.get(new Long(siteID));
     if (typeMap == null) {
       typeMap = new Mapx();
       this.siteMap.put(new Long(siteID), typeMap);
     }
     Mapx subtypeMap = (Mapx)typeMap.get(type);
     if (subtypeMap == null) {
       subtypeMap = new Mapx();
       typeMap.put(type, subtypeMap);
     }
     Mapx itemMap = (Mapx)subtypeMap.get(subType);
     if (itemMap == null) {
       itemMap = new Mapx(maxSize);
       itemMap.setExitEventListener(listener);
       subtypeMap.put(subType, itemMap);
     }
   }
 
   public synchronized void clearType(String type, boolean removeFlag)
   {
     long[] sites = getSites();
     for (int i = 0; i < sites.length; ++i) {
       Mapx typeMap = (Mapx)this.siteMap.get(new Long(sites[i]));
       if (typeMap == null) {
         return;
       }
       if (removeFlag) {
         typeMap.remove(type);
       }
       else {
         Mapx subtypeMap = (Mapx)typeMap.get(type);
         if (subtypeMap == null) {
           continue;
         }
         Object[] subtypes = subtypeMap.keyArray();
         for (int m = 0; m < subtypes.length; ++m) {
           Mapx itemMap = (Mapx)subtypeMap.get(subtypes[m]);
           Object[] items = itemMap.keyArray();
           for (int k = 0; k < items.length; ++k) {
             ItemValue v = (ItemValue)itemMap.get(items[k]);
             if (v.Count == 0L) {
               continue;
             }
             v.Count = 0L;
             v.Divisor = 0;
             v.isNeedInsert = false;
           }
         }
       }
     }
   }
 
   public synchronized void clearSubType(String type, String subType, boolean removeFlag)
   {
     long[] sites = getSites();
     for (int i = 0; i < sites.length; ++i) {
       Mapx typeMap = (Mapx)this.siteMap.get(new Long(sites[i]));
       Mapx subtypeMap = (Mapx)typeMap.get(type);
       if (subtypeMap == null) {
         return;
       }
       if (removeFlag) {
         subtypeMap.remove(subType);
       }
       else {
         Mapx itemMap = (Mapx)subtypeMap.get(subType);
         Object[] items = itemMap.keyArray();
         for (int k = 0; k < items.length; ++k) {
           ItemValue v = (ItemValue)itemMap.get(items[k]);
           if (v.Count == 0L) {
             continue;
           }
           v.Count = 0L;
           v.Divisor = 0;
           v.isNeedInsert = false; }
       }
     }
   }
 
   public synchronized long[] getSites() {
     Object[] ks = this.siteMap.keyArray();
     long[] arr = new long[this.siteMap.size()];
     for (int i = 0; i < this.siteMap.size(); ++i) {
       arr[i] = Long.parseLong(ks[i].toString());
     }
     return arr;
   }
 
   public synchronized String[] getTypes(long siteID) {
     Mapx typeMap = (Mapx)this.siteMap.get(new Long(siteID));
     if (typeMap == null) {
       return new String[0];
     }
     Object[] ks = typeMap.keyArray();
     String[] arr = new String[ks.length];
     for (int i = 0; i < ks.length; ++i) {
       arr[i] = ks[i].toString();
     }
     return arr;
   }
 
   public synchronized String[] getSubTypes(long siteID, String type) {
     Mapx typeMap = (Mapx)this.siteMap.get(new Long(siteID));
     if (typeMap == null) {
       return new String[0];
     }
     Mapx subtypeMap = (Mapx)typeMap.get(type);
     if (subtypeMap == null) {
       return new String[0];
     }
     Object[] ks = subtypeMap.keyArray();
     String[] arr = new String[ks.length];
     for (int i = 0; i < ks.length; ++i) {
       arr[i] = ks[i].toString();
     }
     return arr;
   }
 
   public synchronized String[] getItems(long siteID, String type, String subType) {
     Mapx typeMap = (Mapx)this.siteMap.get(new Long(siteID));
     if (typeMap == null) {
       return new String[0];
     }
     Mapx subtypeMap = (Mapx)typeMap.get(type);
     if (subtypeMap == null) {
       return new String[0];
     }
     Mapx itemMap = (Mapx)subtypeMap.get(subType);
     if (itemMap == null) {
       return new String[0];
     }
     Object[] ks = itemMap.keyArray();
     String[] arr = new String[ks.length];
     for (int i = 0; i < ks.length; ++i) {
       if (ks[i] == null)
         arr[i] = null;
       else {
         arr[i] = ks[i].toString();
       }
     }
     return arr;
   }
 
   public String toString() {
     long[] sites = getSites();
     StringBuffer sb = new StringBuffer();
     for (int i = 0; i < sites.length; ++i) {
       Mapx typeMap = (Mapx)this.siteMap.get(new Long(sites[i]));
       Object[] types = typeMap.keyArray();
       for (int j = 0; j < types.length; ++j) {
         Mapx subtypeMap = (Mapx)typeMap.get(types[j]);
         Object[] subtypes = subtypeMap.keyArray();
         for (int m = 0; m < subtypes.length; ++m) {
           Mapx itemMap = (Mapx)subtypeMap.get(subtypes[m]);
           Object[] items = itemMap.keyArray();
           for (int k = 0; k < items.length; ++k) {
             ItemValue v = (ItemValue)itemMap.get(items[k]);
             long count = v.Count;
             if (v.isAverageValue) {
               if (v.Divisor == 0)
                 count = 0L;
               else {
                 count /= v.Divisor;
               }
             }
             sb.append(sites[i] + "," + types[j] + "," + subtypes[m] + "," + items[k] + ":\t" + count + 
               ":\t" + v.isNeedInsert + "\n");
           }
         }
       }
     }
     return sb.toString();
   }
 
   public int size() {
     long[] sites = getSites();
     int size = 0;
     for (int i = 0; i < sites.length; ++i) {
       Mapx typeMap = (Mapx)this.siteMap.get(new Long(sites[i]));
       Object[] types = typeMap.keyArray();
       for (int j = 0; j < types.length; ++j) {
         Mapx subtypeMap = (Mapx)typeMap.get(types[j]);
         Object[] subtypes = subtypeMap.keyArray();
         for (int m = 0; m < subtypes.length; ++m) {
           Mapx itemMap = (Mapx)subtypeMap.get(subtypes[m]);
           size += itemMap.size();
         }
       }
     }
     return size;
   }
 
   public static class ItemValue
   {
     public long Count;
     public int Divisor;
     public boolean isAverageValue;
     public boolean isNeedInsert;
 
     public ItemValue(long Count, int Divisor, boolean isAverageValue, boolean isNeedInsert)
     {
       this.Count = Count;
       this.Divisor = Divisor;
       this.isAverageValue = isAverageValue;
       this.isNeedInsert = isNeedInsert;
     }
 
     public Object clone() {
       return new ItemValue(this.Count, this.Divisor, this.isAverageValue, this.isNeedInsert);
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.VisitCount
 * JD-Core Version:    0.5.3
 */