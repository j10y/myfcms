 package com.zving.cms.stat;
 
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZCStatItemSchema;
 import com.zving.schema.ZCStatItemSet;
 import java.util.Date;
 
 public abstract class AbstractStat
 {
   public static final int PERIOD_DAY = 1;
   public static final int PERIOD_HOUR = 2;
   protected boolean isNewMonth = false;
 
   public void init()
   {
     String period = DateUtil.toString(new Date(), "yyyyMM");
     DataTable dt = 
       new QueryBuilder("select * from ZCStatItem where Period=? and Type=?", period, getStatType()).executeDataTable();
     if (dt.getRowCount() == 0) {
       this.isNewMonth = true;
     } else {
       int day = Integer.parseInt(DateUtil.getCurrentDate("dd")) + 5 - 1;
       for (int i = 0; i < dt.getRowCount(); ++i) {
         String str = dt.getString(i, day);
         if (StringUtil.isEmpty(str)) {
           str = "0";
         }
         int count = Integer.parseInt(str);
         long siteID = Long.parseLong(dt.getString(i, "SiteID"));
         String type = dt.getString(i, "Type");
         String subtype = dt.getString(i, "SubType");
         String item = dt.getString(i, "Item");
 
         String[] avgSubTypes = getAverageSubTypes();
         boolean flag = false;
         if (avgSubTypes != null) {
           for (int j = 0; j < avgSubTypes.length; ++j) {
             if (avgSubTypes[j].equals(subtype)) {
               VisitCount.getInstance().addAverage(siteID, type, subtype, item, count, false);
               flag = true;
               break;
             }
           }
         }
         if (!(flag))
           VisitCount.getInstance().add(siteID, type, subtype, item, count, false);
       }
     }
   }
 
   public abstract void deal(Visit paramVisit);
 
   public void onPeriodChange(int type, long current)
   {
     if (type == 1) {
       String period = DateUtil.toString(new Date(current), "yyyyMMdd");
       if (period.endsWith("01")) {
         VisitCount.getInstance().clearType(getStatType(), true);
         this.isNewMonth = true;
       } else {
         VisitCount.getInstance().clearType(getStatType(), false);
       }
     }
   }
 
   public void update(VisitCount vc, long current, boolean newMonthFlag, boolean isNewPeriod)
   {
     Date today = new Date(current);
     if (isNewPeriod) {
       today = DateUtil.addDay(today, -1);
     }
     String period = DateUtil.toString(today, "yyyyMM");
     int day = Integer.parseInt(DateUtil.toString(today, "dd"));
     String type = getStatType();
     long[] sites;
     if (newMonthFlag) {
       ZCStatItemSet set = new ZCStatItemSet();
       sites = vc.getSites();
       for (int i = 0; i < sites.length; ++i) {
         String[] subtypes = vc.getSubTypes(sites[i], type);
         for (int j = 0; j < subtypes.length; ++j) {
           String[] items = vc.getItems(sites[i], type, subtypes[j]);
           for (int k = 0; k < items.length; ++k) {
             long count = vc.get(sites[i], type, subtypes[j], items[k]);
             if (count == 0L) {
               continue;
             }
             ZCStatItemSchema si = new ZCStatItemSchema();
             si.setSiteID(sites[i]);
             si.setPeriod(period);
             si.setType(type);
             si.setSubType(subtypes[j]);
             si.setItem(items[k]);
             for (int m = 5; m < si.getColumnCount(); ++m) {
               si.setV(m, new Integer(0));
             }
             si.setV(day + 4, new Long(count));
             set.add(si);
             VisitCount.getInstance().setNotNeedInsert(sites[i], type, subtypes[j], items[k]);
           }
         }
       }
       try {
         set.insert();
       } catch (Exception e) {
         e.printStackTrace();
       }
       this.isNewMonth = false;
     } else {
       QueryBuilder qb = 
         new QueryBuilder("update ZCStatItem set Count" + day + "=? where SiteID=? and " + 
         "Period=? and Type=? and SubType=? and Item=?");
       qb.setBatchMode(true);
       sites = vc.getSites();
       ZCStatItemSet set = new ZCStatItemSet();
       for (int i = 0; i < sites.length; ++i) {
         String[] subtypes = vc.getSubTypes(sites[i], type);
         for (int j = 0; j < subtypes.length; ++j) {
           String[] items = vc.getItems(sites[i], type, subtypes[j]);
           for (int k = 0; k < items.length; ++k) {
             long count = vc.get(sites[i], type, subtypes[j], items[k]);
             boolean isNeedInsert = vc.isNeedInsert(sites[i], type, subtypes[j], items[k]);
             if (count == 0L) {
               continue;
             }
             if (!(isNeedInsert)) {
               qb.add(count);
               qb.add(sites[i]);
               qb.add(period);
               qb.add(type);
               qb.add(subtypes[j]);
               qb.add(items[k]);
               qb.addBatch();
             }
             else if (StringUtil.isNotEmpty(items[k])) {
               ZCStatItemSchema si = new ZCStatItemSchema();
               si.setSiteID(sites[i]);
               si.setPeriod(period);
               si.setType(type);
               si.setSubType(subtypes[j]);
               si.setItem(items[k]);
               for (int m = 5; m < si.getColumnCount(); ++m) {
                 si.setV(m, new Integer(0));
               }
               si.setV(day + 4, new Long(count));
               set.add(si);
               VisitCount.getInstance().setNotNeedInsert(sites[i], type, subtypes[j], items[k]);
             }
           }
         }
       }
 
       set.insert();
       qb.executeNoQuery();
     }
   }
 
   public abstract String getStatType();
 
   public String[] getAverageSubTypes()
   {
     return null;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.stat.AbstractStat
 * JD-Core Version:    0.5.3
 */