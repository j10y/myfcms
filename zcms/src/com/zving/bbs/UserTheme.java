 package com.zving.bbs;
 
 import com.zving.framework.Ajax;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataListAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.io.PrintStream;
 import java.util.Date;
 
 public class UserTheme extends Ajax
 {
   public static void getList(DataListAction dla)
   {
     String addTime = dla.getParams().getString("addtime");
     String orderBy = dla.getParams().getString("orderby");
     String ascdesc = dla.getParams().getString("ascdesc");
     StringBuffer sb = new StringBuffer();
 
     if ((!(StringUtil.isEmpty(addTime))) && (!("0".equals(addTime)))) {
       Date date = new Date();
       date.setTime(new Date().getTime() - Long.parseLong(addTime));
       addTime = DateUtil.toDateTimeString(date);
       sb.append(" and addTime > '" + addTime + "'");
     }
 
     if (!(StringUtil.isEmpty(orderBy)))
       sb.append(" order by " + orderBy);
     else {
       sb.append(" order by OrderTime desc");
     }
 
     if ((!(StringUtil.isEmpty(ascdesc))) && 
       ("DESC".equals(ascdesc))) {
       sb.append(" desc ");
     }
 
     String sqlData = "select * from ZCTheme  where Status='Y' and VerifyFlag='Y' and AddUser='" + dla.getParam("LastPoster") + "'" + sb;
     String sqlTotal = "select count(*) from ZCTheme  where Status='Y' and VerifyFlag='Y' and AddUser='" + dla.getParam("LastPoster") + "'" + sb;
     System.out.println("SQL:" + sqlData);
     DataTable dt = new QueryBuilder(sqlData).executePagedDataTable(dla.getPageSize(), dla.getPageIndex());
     int total = new QueryBuilder(sqlTotal).executeInt();
     dla.setTotal(total);
     dla.bindData(dt);
   }
 
   public static Mapx init(Mapx params) {
     params.put("AddUser", User.getUserName());
     return params;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.UserTheme
 * JD-Core Version:    0.5.3
 */