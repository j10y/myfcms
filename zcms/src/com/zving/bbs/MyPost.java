 package com.zving.bbs;
 
 import com.zving.framework.Ajax;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataListAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.util.Date;
 
 public class MyPost extends Ajax
 {
   public static void getMyPost(DataListAction dla)
   {
     String addTime = dla.getParams().getString("addtime");
     String orderBy = dla.getParams().getString("orderby");
     String ascdesc = dla.getParams().getString("ascdesc");
     StringBuffer sb = new StringBuffer();
 
     if ((!(StringUtil.isEmpty(addTime))) && (!("0".equals(addTime)))) {
       Date date = new Date();
       date.setTime(new Date().getTime() - Long.parseLong(addTime));
       addTime = DateUtil.toDateTimeString(date);
       sb.append(" and p.addTime > '" + addTime + "'");
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
     String sqlSiteID = "SiteID=" + dla.getParam("SiteID");
     String sqlData = "select p.*, t.Subject TSubject, f.Name from ZCPost p, ZCTheme t, ZCForum f where p." + 
       sqlSiteID + " and t." + sqlSiteID + " and f." + sqlSiteID + 
       " and p.ThemeID=t.ID and p.Invisible='Y' and t.ForumID=f.ID and p.first='N' and p.AddUser='" + User.getUserName() + "' and t.status='Y' and t.VerifyFlag='Y' " + sb;
     String sqlTotal = "select count(*) from ZCPost p, ZCTheme t, ZCForum f where p." + 
       sqlSiteID + " and t." + sqlSiteID + " and f." + sqlSiteID + 
       " and p.ThemeID=t.ID and p.Invisible='Y' and t.ForumID=f.ID and p.first='N' and p.AddUser='" + User.getUserName() + "' and t.status='Y' and t.VerifyFlag='Y' " + sb;
     DataTable dt = new QueryBuilder(sqlData).executePagedDataTable(dla.getPageSize(), dla.getPageIndex());
     dt.insertColumn("AuditStatus");
     dt.insertColumn("Operation");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       if (dt.get(i, "VerifyFlag").equals("Y")) {
         dt.set(i, "AuditStatus", "正常");
         if (dt.get(i, "ApplyDel") == null)
           dt.set(i, "Operation", "<a href='javascript:applyDel(" + dt.get(i, "ID") + ")'>申请删除</a>");
         else
           dt.set(i, "Operation", "已申请删除");
       }
       else {
         dt.set(i, "AuditStatus", "待审核");
         dt.set(i, "Operation", "<cite><a href='javascript:editPost(" + dt.get(i, "ID") + ")'>修改</a></cite> <em><a href='javascript:del(" + dt.get(i, "ID") + ")' >删除</a></em>");
       }
     }
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
 * Qualified Name:     com.zving.bbs.MyPost
 * JD-Core Version:    0.5.3
 */