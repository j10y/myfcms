 package com.zving.misc;
 
 import com.zving.cms.stat.StatUtil;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Mapx;
 import java.io.PrintStream;
 
 public class IPDBCompare
 {
   public static void main(String[] args)
   {
     String district = StatUtil.getDistrictCode("219.234.128.126");
     System.out.println(district);
     Mapx map = 
       new QueryBuilder("select code,name from ZDDistrict where code like '11%' or code like '12%' or code like '31%' or code like '50%' or TreeLevel<3")
       .executeDataTable().toMapx(0, 1);
 
     if ((!(district.startsWith("00"))) && (!(district.endsWith("0000")))) {
       String prov = map.getString(district.substring(0, 2) + "0000");
       if ((prov.startsWith("黑龙江")) || (prov.startsWith("内蒙古")))
         prov = prov.substring(0, 3);
       else {
         prov = prov.substring(0, 2);
       }
       String city = map.getString(district);
       city = (city == null) ? "" : city;
       district = prov + city;
     }
     System.out.println(district);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.misc.IPDBCompare
 * JD-Core Version:    0.5.3
 */