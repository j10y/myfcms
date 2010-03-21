 package com.zving.cms.document;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataRow;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.UserList;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCMessageSchema;
 import com.zving.schema.ZCMessageSet;
 import java.util.Date;
 import org.apache.commons.lang.ArrayUtils;
 
 public class Message extends Page
 {
   public static Mapx init(Mapx params)
   {
     return null;
   }
 
   public static Mapx initDetailDialog(Mapx params) {
     String id = params.getString("ID");
     String Type = params.getString("Type");
     if (StringUtil.isEmpty(id)) {
       return null;
     }
     DataTable dt = new QueryBuilder("select * from ZCMessage where ID=?", id).executeDataTable();
     if ((dt != null) && (dt.getRowCount() > 0)) {
       params.putAll(dt.getDataRow(0).toMapx());
       if ("history".equals(Type)) {
         params.put("UserType", "收");
         params.put("FromUser", "");
       } else {
         params.put("UserType", "发");
         params.put("ToUser", "");
 
         int readFlag = Integer.parseInt(dt.getDataRow(0).getString("ReadFlag"));
         if (readFlag == 0) {
           new QueryBuilder("update ZCMessage set ReadFlag = 1 where ID=?", id).executeNoQuery();
         }
       }
     }
     return params;
   }
 
   public static Mapx initReplyDialog(Mapx params) {
     String id = params.getString("ID");
     if (StringUtil.isEmpty(id)) {
       return null;
     }
     DataTable dt = new QueryBuilder("select * from ZCMessage where ID=?", id).executeDataTable();
     if ((dt != null) && (dt.getRowCount() > 0)) {
       return dt.getDataRow(0).toMapx();
     }
     return null;
   }
 
   public static void dg1DataBind(DataGridAction dga)
   {
     String sql = "select ZCMessage.*,case readFlag when 1 then '已读' else '未读' end as ReadFlagStr,case readFlag when 1 then '' else 'red' end as color from ZCMessage where touser='" + User.getUserName() + "' " + dga.getSortString();
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(1) from ZCMessage where touser='" + User.getUserName() + "'"));
     }
     dga.bindData(new QueryBuilder(sql));
   }
 
   public static void historyDataBind(DataGridAction dga) {
     String sql = "select ZCMessage.*,case readFlag when 1 then '已读' else '未读' end as ReadFlagStr,case readFlag when 1 then '' else 'red' end as color from ZCMessage where fromuser='" + User.getUserName() + "' " + dga.getSortString();
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(1) from ZCMessage where fromuser='" + User.getUserName() + "'"));
     }
     dga.bindData(new QueryBuilder(sql));
   }
 
   public void getNewMessage() {
     String sql = "select count(*) from ZCMessage where readflag = 0 and touser='" + User.getUserName() + "'";
     this.Response.put("Count", new QueryBuilder(sql).executeString());
   }
 
   public void add() {
     Transaction trans = new Transaction();
     String toUser = $V("ToUser");
     if (!(StringUtil.checkID(toUser))) {
       this.Response.setLogInfo(0, "传入参数错误！");
       return;
     }
     String[] userList = toUser.split(",");
 
     String toRole = $V("ToRole");
     if (!(StringUtil.checkID(toRole))) {
       this.Response.setLogInfo(0, "传入参数错误！");
       return;
     }
     String[] roleList = toRole.split(",");
 
     if (roleList.length > 0) {
       String roleStr = "";
       for (int j = 0; j < roleList.length; ++j) {
         if (StringUtil.isNotEmpty(roleList[j])) {
           if (j == 0)
             roleStr = roleStr + "'" + roleList[j] + "'";
           else {
             roleStr = roleStr + ",'" + roleList[j] + "'";
           }
         }
       }
       if (StringUtil.isNotEmpty(roleStr)) {
         DataTable dt = new QueryBuilder("select UserName from zduserRole where rolecode in (" + roleStr + ")").executeDataTable();
         for (int k = 0; k < dt.getRowCount(); ++k) {
           String userName = dt.getString(k, "UserName");
           if ((!(User.getUserName().equals(userName))) && (!(ArrayUtils.contains(userList, userName)))) {
             userList = (String[])ArrayUtils.add(userList, userName);
           }
         }
       }
     }
 
     for (int i = 0; i < userList.length; ++i) {
       if ((!(userList[i].equals(User.getUserName()))) && (StringUtil.isNotEmpty(userList[i]))) {
         ZCMessageSchema message = new ZCMessageSchema();
         message.setID(NoUtil.getMaxID("MessageID"));
         message.setSubject($V("Subject"));
         message.setBox("outbox");
         message.setContent(StringUtil.htmlEncode($V("Content")));
         message.setFromUser(User.getUserName());
         message.setToUser(userList[i]);
         message.setReadFlag(0L);
         message.setAddTime(new Date());
         trans.add(message, 1);
       }
     }
 
     if (trans.commit())
       this.Response.setLogInfo(1, "新建成功！");
     else
       this.Response.setLogInfo(0, "新建失败！");
   }
 
   public void reply()
   {
     Transaction trans = new Transaction();
     String toUser = $V("ToUser");
     if (!(StringUtil.checkID(toUser))) {
       this.Response.setLogInfo(0, "传入参数错误！");
       return;
     }
     if (!(toUser.equals(User.getUserName()))) {
       ZCMessageSchema message = new ZCMessageSchema();
       message.setID(NoUtil.getMaxID("MessageID"));
       message.setSubject($V("Subject"));
       message.setBox("outbox");
       message.setContent(StringUtil.htmlEncode($V("Content")));
       message.setFromUser(User.getUserName());
       message.setToUser(toUser);
       message.setReadFlag(0L);
       message.setAddTime(new Date());
       trans.add(message, 1);
     }
     if (trans.commit())
       this.Response.setLogInfo(1, "添加回复成功！");
     else
       this.Response.setLogInfo(0, "添加回复失败！");
   }
 
   public void del()
   {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setLogInfo(0, "传入ID时发生错误");
       return;
     }
     Transaction trans = new Transaction();
 
     ZCMessageSchema message = new ZCMessageSchema();
     ZCMessageSet set = message.query(new QueryBuilder("where id in (" + ids + ")"));
     trans.add(set, 5);
 
     if (trans.commit())
       this.Response.setLogInfo(1, "删除成功");
     else
       this.Response.setLogInfo(0, "删除失败");
   }
 
   public static void bindUserList(DataGridAction dga)
   {
     String searchUserName = dga.getParam("SearchUserName");
     StringBuffer conditions = new StringBuffer();
     conditions.append(" where BranchInnerCode like '");
     conditions.append(User.getBranchInnerCode());
     conditions.append("%'");
     conditions.append(" and UserName <> '" + User.getUserName() + "'");
     if (StringUtil.isNotEmpty(searchUserName)) {
       dga.setTotal(0);
       conditions.append(" and (UserName like '%" + searchUserName.trim() + "%'");
 
       conditions.append(" or realname like '%" + searchUserName.trim() + "%')");
     }
     String sql = "select * from ZDUser " + conditions + " order by AddTime desc";
     if (dga.getTotal() == 0) {
       String sql2 = "select count(*) from ZDUser " + conditions;
       dga.setTotal(new QueryBuilder(sql2));
     }
     DataTable dt = new QueryBuilder(sql).executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     dt.decodeColumn("Status", UserList.STATUS_MAP);
     dga.bindData(dt);
   }
 
   public static void bindRoleList(DataGridAction dga) {
     String searchRoleName = dga.getParam("SearchRoleName");
     StringBuffer conditions = new StringBuffer();
     conditions.append(" where BranchInnerCode like '");
     conditions.append(User.getBranchInnerCode());
     conditions.append("%'");
     if (StringUtil.isNotEmpty(searchRoleName)) {
       dga.setTotal(0);
       conditions.append(" and (RoleCode like '%" + searchRoleName.trim() + "%'");
 
       conditions.append(" or RoleName like '%" + searchRoleName.trim() + "%')");
     }
     String sql = "select * from ZDRole " + conditions + " order by AddTime desc";
     if (dga.getTotal() == 0) {
       String sql2 = "select count(*) from ZDRole " + conditions;
       dga.setTotal(new QueryBuilder(sql2));
     }
     DataTable dt = new QueryBuilder(sql).executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     dga.bindData(dt);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.document.Message
 * JD-Core Version:    0.5.3
 */