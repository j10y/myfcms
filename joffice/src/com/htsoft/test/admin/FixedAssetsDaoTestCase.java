 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.FixedAssetsDao;
 import com.htsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import java.util.Calendar;
 import java.util.Date;
 import java.util.GregorianCalendar;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class FixedAssetsDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private FixedAssetsDao fixedAssetsDao;
 
   @Test
   public void text()
   {
     GregorianCalendar calendar2 = new GregorianCalendar(2009, 2, 5);
 
     System.out.println(getMonth(new Date(), calendar2.getTime()));
   }
 
   public int getMonth(Date s, Date e)
   {
     if (s.after(e)) {
       Date t = s;
       s = e;
       e = t;
     }
     Calendar start = Calendar.getInstance();
     start.setTime(s);
     Calendar end = Calendar.getInstance();
     end.setTime(e);
     Calendar temp = Calendar.getInstance();
     temp.setTime(e);
     temp.add(5, 1);
 
     int y = end.get(1) - start.get(1);
     int m = end.get(2) - start.get(2);
 
     if ((start.get(5) == 1) && (temp.get(5) == 1))
       return y 
     if ((start.get(5) != 1) && 
       (temp.get(5) == 1))
       return y 
     if ((start.get(5) == 1) && 
       (temp.get(5) != 1)) {
       return y 
     }
     return (y 
   }
 }


 
 
 