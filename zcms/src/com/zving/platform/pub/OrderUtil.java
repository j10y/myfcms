 package com.zving.platform.pub;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.StringUtil;
 import java.util.Arrays;
 import org.apache.commons.lang.ArrayUtils;
 
 public class OrderUtil
 {
   private static long currentOrder = System.currentTimeMillis();
 
   public static boolean updateOrder(String table, String type, String targetOrder, String orders, String wherePart)
   {
     return updateOrder(table, "OrderFlag", type, targetOrder, orders, wherePart);
   }
 
   public static boolean updateOrder(String table, String column, String type, String targetOrder, String orders, String wherePart) {
     return updateOrder(table, column, type, targetOrder, orders, wherePart, null);
   }
 
   public static boolean updateOrder(String table, String column, String type, String targetOrder, String orders, String wherePart, Transaction tran) {
     if ((StringUtil.isEmpty(targetOrder)) || (targetOrder.length() < 13)) {
       targetOrder = getDefaultOrder();
     }
     if (!(StringUtil.checkID(targetOrder))) {
       return false;
     }
     if (!(StringUtil.checkID(orders))) {
       return false;
     }
     if (StringUtil.isEmpty(wherePart)) {
       wherePart = "1=1";
     }
 
     String[] arrtmp = orders.split(",");
     arrtmp = (String[])ArrayUtils.removeElement(arrtmp, targetOrder);
     long[] arr = new long[arrtmp.length + 1];
     for (int i = 0; i < arrtmp.length; ++i) {
       arr[i] = Long.parseLong(arrtmp[i]);
     }
     long target = Long.parseLong(targetOrder);
     arr[arrtmp.length] = target;
     Arrays.sort(arr);
 
     boolean bFlag = true;
     if (tran == null) {
       tran = new Transaction();
       bFlag = false;
     }
     QueryBuilder qb = null;
     boolean flag = type.equals("After");
     for (int i = 0; i < arr.length; ++i) {
       if (arr[i] == target)
       {
         int d;
         int j;
         if (flag) {
           target = target + arr.length - i - 1L;
           d = arr.length - 1;
           for (j = 0; j < arr.length; ++j) {
             if (j != i) {
               qb = 
                 new QueryBuilder("update " + table + " set " + column + "=? where " + column + "=?", (target - d) * 10L);
               --d;
             } else {
               qb = 
                 new QueryBuilder("update " + table + " set " + column + "=? where " + column + "=?", target * 10L);
             }
             qb.add(arr[j]);
             tran.add(qb);
           }
 
           for (j = 0; j < i; ++j) {
             if (arr[j] + 1L == arr[(j + 1)]) {
               continue;
             }
             qb = 
               new QueryBuilder("update " + table + " set " + column + "=" + column + "-? where " + column + 
               " between ? and ? and " + wherePart);
             qb.add(j + 1);
             qb.add(arr[j]);
             qb.add(arr[(j + 1)]);
             tran.add(qb);
           }
 
           for (j = arr.length - 1; j > i; --j) {
             if (arr[j] == arr[(j - 1)] + 1L) {
               continue;
             }
             qb = 
               new QueryBuilder("update " + table + " set " + column + "=" + column + "+? where " + column + 
               " between ? and ? and " + wherePart);
             qb.add(arr.length - j);
             qb.add(arr[(j - 1)]);
             qb.add(arr[j]);
             tran.add(qb);
           }
         } else {
           target -= i;
           d = 1;
           for (j = 0; j < arr.length; ++j) {
             if (j != i) {
               qb = 
                 new QueryBuilder("update " + table + " set " + column + "=? where " + column + "=?", (target + d) * 10L);
               ++d;
             } else {
               qb = 
                 new QueryBuilder("update " + table + " set " + column + "=? where " + column + "=?", target * 10L);
             }
             qb.add(arr[j]);
             tran.add(qb);
           }
 
           for (j = 0; j < i; ++j) {
             if (arr[j] + 1L == arr[(j + 1)]) {
               continue;
             }
             qb = 
               new QueryBuilder("update " + table + " set " + column + "=" + column + "-? where " + column + 
               " between ? and ? and " + wherePart);
             qb.add(j + 1);
             qb.add(arr[j]);
             qb.add(arr[(j + 1)]);
             tran.add(qb);
           }
 
           for (j = arr.length - 1; j > i; --j) {
             if (arr[j] == arr[(j - 1)] + 1L) {
               continue;
             }
             qb = 
               new QueryBuilder("update " + table + " set " + column + "=" + column + "+? where " + column + 
               " between ? and ? and " + wherePart);
             qb.add(arr.length - j);
             qb.add(arr[(j - 1)]);
             qb.add(arr[j]);
             tran.add(qb);
           }
         }
         qb = 
           new QueryBuilder("update " + table + " set " + column + "=" + column + "/10 where " + column + ">? and " + 
           wherePart, target * 9L);
         tran.add(qb);
         if (bFlag) {
           return true;
         }
         return tran.commit();
       }
     }
 
     return false;
   }
 
   public static synchronized long getDefaultOrder()
   {
     if (System.currentTimeMillis() <= currentOrder) {
       return (++currentOrder);
     }
     return (OrderUtil.currentOrder = System.currentTimeMillis());
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.pub.OrderUtil
 * JD-Core Version:    0.5.3
 */