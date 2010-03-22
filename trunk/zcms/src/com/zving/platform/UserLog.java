 package com.zving.platform;
 
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DBUtil;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZDUserLogSchema;
 import java.util.Date;
 
 public class UserLog extends Page
 {
   public static final String LOGIN = "Login";
   public static final String LOGOUT = "Logout";
   public static final String LOG = "Log";
   public static final Mapx USERLOG_LOGTYPE_MAP = new Mapx();
   public static final Mapx USERLOG_FORUM_MAP;
   public static final String FORUM = "Forum";
   public static final String FORUM_TOPTHEME = "TopTheme";
   public static final String FORUM_TOPCANCEL = "TopCancel";
   public static final String FORUM_DELTHEME = "DelTheme";
   public static final String FORUM_BESTTHEME = "BestTheme";
   public static final String FORUM_BESTCANCEL = "BestCancel";
   public static final String FORUM_BRIGHTTHEME = "BrightTheme";
   public static final String FORUM_UPTHEME = "UpTheme";
   public static final String FORUM_DOWNTHEME = "DownTheme";
   public static final String FORUM_MOVETHEME = "MoveTheme";
   public static final String FORUM_EDITPOST = "EditPost";
   public static final String FORUM_DELPOST = "DelPost";
   public static final String FORUM_HIDEPOST = "HidePost";
   public static final Mapx USERLOG_SITE_MAP;
   public static final String SITE = "Site";
   public static final String SITE_DELSITE = "DelSite";
   public static final String SITE_DELCATALOG = "DelCataLog";
   public static final Mapx USERLOG_USER_MAP;
   public static final String USER = "User";
   public static final String USER_DELUSER = "DelUser";
   public static final String USER_DELROLE = "DelROLE";
   public static final String USER_EDITPASSWORD = "EditPassword";
   public static final Mapx USERLOG_SYSTEM_MAP;
   public static final String SYSTEM = "System";
   public static final String SYSTEM_DELBRANCH = "DelBranch";
   public static final String SYSTEM_DELCODE = "DelCode";
   public static final String SYSTEM_DELCONFIG = "DelConfig";
   public static final String SYSTEM_DELSCHEDULE = "DelSchedule";
   public static final String SYSTEM_DELMENU = "DelMenu";
   public static final Mapx USERLOG_MAP;
   public static final Mapx USERLOG_SELECT_MAP;
 
   static
   {
     USERLOG_LOGTYPE_MAP.put("0", "");
     USERLOG_LOGTYPE_MAP.put("Login", "登陆");
     USERLOG_LOGTYPE_MAP.put("Logout", "退出");
 
     USERLOG_FORUM_MAP = new Mapx();
 
     USERLOG_FORUM_MAP.put("0", "");
     USERLOG_FORUM_MAP.put("TopTheme", "置顶主题");
     USERLOG_FORUM_MAP.put("TopCancel", "取消置顶");
     USERLOG_FORUM_MAP.put("DelTheme", "删除主题");
     USERLOG_FORUM_MAP.put("BestTheme", "设置精华");
     USERLOG_FORUM_MAP.put("BestCancel", "取消精华");
     USERLOG_FORUM_MAP.put("UpTheme", "提升主题");
     USERLOG_FORUM_MAP.put("DownTheme", "下沉主题");
     USERLOG_FORUM_MAP.put("MoveTheme", "移动主题");
     USERLOG_FORUM_MAP.put("EditPost", "编辑帖子");
     USERLOG_FORUM_MAP.put("DelPost", "删除帖子");
     USERLOG_FORUM_MAP.put("HidePost", "屏蔽帖子");
 
     USERLOG_SITE_MAP = new Mapx();
 
     USERLOG_SITE_MAP.put("0", "");
     USERLOG_SITE_MAP.put("DelSite", "删除站点");
     USERLOG_SITE_MAP.put("DelCataLog", "删除栏目");
 
     USERLOG_USER_MAP = new Mapx();
 
     USERLOG_USER_MAP.put("0", "");
     USERLOG_USER_MAP.put("DelUser", "删除用户");
     USERLOG_USER_MAP.put("DelROLE", "删除角色");
     USERLOG_USER_MAP.put("EditPassword", "修改密码");
 
     USERLOG_SYSTEM_MAP = new Mapx();
 
     USERLOG_SYSTEM_MAP.put("0", "");
     USERLOG_SYSTEM_MAP.put("DelBranch", "删除机构");
     USERLOG_SYSTEM_MAP.put("DelCode", "删除代码");
     USERLOG_SYSTEM_MAP.put("DelConfig", "删除配置项");
     USERLOG_SYSTEM_MAP.put("DelSchedule", "删除定时任务");
     USERLOG_SYSTEM_MAP.put("DelMenu", "删除菜单");
 
     USERLOG_MAP = new Mapx();
 
     USERLOG_MAP.put("Log", USERLOG_LOGTYPE_MAP);
     USERLOG_MAP.put("Forum", USERLOG_FORUM_MAP);
     USERLOG_MAP.put("Site", USERLOG_SITE_MAP);
     USERLOG_MAP.put("User", USERLOG_USER_MAP);
     USERLOG_MAP.put("System", USERLOG_SYSTEM_MAP);
 
     USERLOG_SELECT_MAP = new Mapx();
 
     USERLOG_SELECT_MAP.put("Log", "登录状态");
     USERLOG_SELECT_MAP.put("Forum", "论坛操作");
     USERLOG_SELECT_MAP.put("Site", "站点操作");
     USERLOG_SELECT_MAP.put("User", "用户角色操作");
     USERLOG_SELECT_MAP.put("System", "系统管理");
   }
 
   public static Mapx init(Mapx params) {
     Mapx map = new Mapx();
     return map;
   }
 
   public void menuVisit() {
     String id = this.Request.valueArray()[0].toString();
     if (!(StringUtil.verify(id, "Int"))) {
       return;
     }
     DataTable dt = new QueryBuilder(
       "select Name,(select Name from ZDMenu where id=a.ParentID) from ZDMenu a where id=?", id)
       .executeDataTable();
     String menu = dt.getString(0, 1) + "->" + dt.getString(0, 0);
     ZDUserLogSchema userlog = new ZDUserLogSchema();
     userlog.setUserName(User.getUserName());
     userlog.setIP(this.Request.getClientIP());
     userlog.setAddTime(new Date());
     userlog.setLogID(NoUtil.getMaxID("LogID"));
     userlog.setLogType("Menu");
     userlog.setLogMessage("访问菜单：" + menu);
     userlog.insert();
     this.Response.setStatus(1);
   }
 
   public void logout() {
     if (log("Menu", "", "退出系统", this.Request.getClientIP()))
       this.Response.setStatus(1);
     else
       this.Response.setStatus(0);
   }
 
   public static void dg1DataBind(DataGridAction dga)
   {
     StringBuffer condition = new StringBuffer(" where 1=1 ");
 
     String searchUser = dga.getParams().getString("SearchUser");
     String ip = dga.getParams().getString("IP");
     String logMessage = dga.getParams().getString("LogMessage");
     String startDate = dga.getParams().getString("StartDate");
     String endDate = dga.getParams().getString("EndDate");
     String logType = dga.getParams().getString("LogType");
     String subType = dga.getParams().getString("SubType");
 
     if (StringUtil.isNotEmpty(searchUser)) {
       condition.append(" and UserName like ");
       condition.append(" '%" + searchUser + "%'");
     }
     if (StringUtil.isNotEmpty(ip)) {
       condition.append(" and IP like ");
       condition.append(" '%" + ip + "%'");
     }
     if (StringUtil.isNotEmpty(logType)) {
       condition.append(" and LogType like ");
       condition.append(" '%" + logType + "%'");
     }
     if ((StringUtil.isNotEmpty(subType)) && (!("0".equals(subType)))) {
       condition.append(" and SubType like ");
       condition.append(" '%" + subType + "%'");
     }
     if (StringUtil.isNotEmpty(logMessage)) {
       condition.append(" and LogMessage like ");
       condition.append(" '%" + logMessage + "%'");
     }
     if ((StringUtil.isNotEmpty(startDate)) && (StringUtil.isNotEmpty(endDate))) {
       condition.append(" and AddTime >='" + startDate + "' and AddTime<='" + endDate + "'");
     }
     condition.append(" order by addtime desc");
     String sqlData = "select * from ZDUserLog " + condition;
     DataTable dt = new QueryBuilder(sqlData).executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     dt.decodeColumn("LogType", USERLOG_SELECT_MAP);
     int total = DBUtil.getCount(new QueryBuilder(sqlData));
     dga.setTotal(total);
     dga.bindData(dt);
   }
 
   public static Mapx initDialog(Mapx params) {
     Date date = new Date();
     String str = DateUtil.toString(date);
     ZDUserLogSchema userLog = new ZDUserLogSchema();
     params.put("Time", str);
     params.put("LogType", HtmlUtil.mapxToOptions(USERLOG_SELECT_MAP, userLog.getLogType(), true));
     return params;
   }
 
   public static boolean log(String logType, String subType, String logMessage, String ip) {
     return log(logType, subType, logMessage, ip, User.getUserName(), null);
   }
 
   public static boolean log(String logType, String subType, String logMessage, String ip, Transaction trans) {
     return log(logType, subType, logMessage, ip, User.getUserName(), trans);
   }
 
   public static boolean log(String logType, String subType, String logMessage, String ip, String userName) {
     return log(logType, subType, logMessage, ip, userName, null);
   }
 
   public static boolean log(String logType, String subType, String logMessage, String ip, String userName, Transaction trans)
   {
     ZDUserLogSchema userlog = new ZDUserLogSchema();
     userlog.setUserName(userName);
     userlog.setIP(ip);
     userlog.setAddTime(new Date());
     userlog.setLogID(NoUtil.getMaxID("LogID"));
     userlog.setLogType(logType);
     userlog.setSubType(subType);
     userlog.setLogMessage(logMessage);
     if (trans == null) {
       return userlog.insert();
     }
     trans.add(userlog, 1);
 
     return true;
   }
 
   public static DataTable getSubType(Mapx params) {
     String logType = params.getString("LogType");
     if ((StringUtil.isEmpty(logType)) || ("0".equals(logType))) {
       return null;
     }
     Mapx map = (Mapx)USERLOG_MAP.get(logType);
     return map.toDataTable();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.UserLog
 * JD-Core Version:    0.5.3
 */