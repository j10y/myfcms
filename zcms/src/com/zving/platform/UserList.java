 package com.zving.platform;
 
 import com.zving.cms.pub.PubFun;
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.controls.TreeAction;
 import com.zving.framework.data.DataCollection;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.license.LicenseInfo;
 import com.zving.framework.utility.Errorx;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZDPrivilegeSchema;
 import com.zving.schema.ZDUserRoleSchema;
 import com.zving.schema.ZDUserRoleSet;
 import com.zving.schema.ZDUserSchema;
 import com.zving.schema.ZDUserSet;
 import java.util.Date;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class UserList extends Page
 {
   public static final String ADMINISTRATOR = "admin";
   public static final String STATUS_NORMAL = "N";
   public static final String STATUS_STOP = "S";
   public static final Mapx STATUS_MAP = new Mapx();
   static String Password;
   private static Pattern userPattern;
   private static Pattern idPattern;
 
   static
   {
     STATUS_MAP.put("N", "正常");
     STATUS_MAP.put("S", "停用");
 
     Password = "zvingzving";
 
     userPattern = Pattern.compile("[\\w@\\.一-龥]{1,20}", 34);
 
     idPattern = Pattern.compile("[\\w@\\.\\,一-龥]*", 34);
   }
 
   public static Mapx init(Mapx params)
   {
     params.put("IsBranchAdmin", HtmlUtil.codeToRadios("IsBranchAdmin", "YesOrNo", "N"));
     params.put("Status", HtmlUtil.mapxToRadios("Status", STATUS_MAP, "N"));
     params.put("BranchInnerCode", PubFun.getBranchOptions());
     return params;
   }
 
   public static Mapx initEditDialog(Mapx params)
   {
     String userName = params.getString("UserName");
     ZDUserSchema user = new ZDUserSchema();
     user.setUserName(userName);
     user.fill();
     params = user.toMapx();
     params.put("IsBranchAdmin", HtmlUtil.codeToRadios("IsBranchAdmin", "YesOrNo", user.getIsBranchAdmin()));
     params.put("Status", HtmlUtil.mapxToRadios("Status", STATUS_MAP, user.getStatus()));
     params.put("BranchInnerCode", PubFun.getBranchOptions(user.getBranchInnerCode()));
     params.put("Password", Password);
     return params;
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     String searchUserName = dga.getParam("SearchUserName");
     StringBuffer conditions = new StringBuffer();
     conditions.append(" where BranchInnerCode like '");
     conditions.append(User.getBranchInnerCode());
     conditions.append("%'");
     if (StringUtil.isNotEmpty(searchUserName)) {
       conditions.append(" and (UserName like '%" + searchUserName.trim() + "%'");
 
       conditions.append(" or realname like '%" + searchUserName.trim() + "%'");
 
       conditions.append(" or prop1 like '%" + searchUserName.trim() + "%')");
     }
     String sql = "select * from ZDUser " + conditions + " order by AddTime desc";
     dga.setTotal(new QueryBuilder("select count(*) from ZDUser " + conditions));
     DataTable dt = new QueryBuilder(sql).executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     dt.decodeColumn("BranchInnerCode", new QueryBuilder("select BranchInnerCode,Name from ZDBranch")
       .executeDataTable().toMapx(0, 1));
     dt.decodeColumn("Status", STATUS_MAP);
     dga.bindData(dt);
   }
 
   public static void initRoleTree(TreeAction ta) {
     DataTable dt = new QueryBuilder(
       "select RoleCode,'' as ParentID,'1' as TreeLevel,RoleName,'' as Checked from zdrole ")
       .executeDataTable();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       if ("everyone".equalsIgnoreCase(dt.getString(i, "RoleCode"))) {
         dt.set(i, "Checked", "Checked");
       }
     }
     ta.setRootText("角色");
     ta.setIdentifierColumnName("RoleCode");
     ta.bindData(dt);
   }
 
   public static void initEditRoleTree(TreeAction ta) {
     String userName = ta.getParam("UserName");
     DataTable dt = new QueryBuilder(
       "select RoleCode,'' as ParentID,'1' as TreeLevel,RoleName,(select 'Checked' from ZDUserRole b where b.RoleCode=ZDRole.RoleCode and UserName=?) as Checked from zdrole ", 
       userName).executeDataTable();
     ta.setRootText("角色");
     ta.setIdentifierColumnName("RoleCode");
     ta.bindData(dt);
   }
 
   public void add() {
     if (new QueryBuilder("select count(*) from ZDUser").executeInt() >= LicenseInfo.getUserLimit()) {
       this.Response.setError("后台用户数超出限制，请联系泽元软件更换License！");
       return;
     }
     Transaction trans = new Transaction();
     if (!(add(trans, this.Request))) {
       this.Response.setLogInfo(0, Errorx.printString());
       return;
     }
     if (trans.commit()) {
       this.Response.setLogInfo(1, "新建用户成功!");
       Priv.updateAllPriv($V("UserName"));
     } else {
       this.Response.setLogInfo(0, "新建用户失败!");
     }
   }
 
   public static boolean add(Transaction trans, DataCollection dc)
   {
     String userName = dc.getString("UserName");
     if (!(userPattern.matcher(userName).matches())) {
       Errorx.addError("用户名最多20位，仅限英文字母，数字，汉字，半角“.”、“@”");
       return false;
     }
     ZDUserSchema user = new ZDUserSchema();
     user.setValue(dc);
     user.setUserName(user.getUserName().toLowerCase());
     if (user.fill()) {
       Errorx.addError(dc.getString("UserName") + "用户已经存在!");
       return false;
     }
 
     user.setPassword(StringUtil.md5Hex(dc.getString("Password")));
     if ((dc.getString("Type") == null) || ("".equals(dc.getString("Type"))))
       user.setType("0");
     else {
       user.setType(dc.getString("Type"));
     }
     user.setProp1(dc.getString("Prop1"));
     user.setAddTime(new Date());
     user.setAddUser(User.getUserName());
     trans.add(user, 1);
 
     String roleCodes = dc.getString("RoleCode");
     if (StringUtil.isEmpty(roleCodes)) {
       return true;
     }
     String[] RoleCodes = roleCodes.split(",");
     String currentUserName = User.getUserName();
 
     for (int i = 0; i < RoleCodes.length; ++i) {
       if (StringUtil.isEmpty(RoleCodes[i])) continue; if (StringUtil.isEmpty(user.getUserName())) {
         continue;
       }
       ZDUserRoleSchema userRole = new ZDUserRoleSchema();
       userRole.setUserName(user.getUserName());
       userRole.setRoleCode(RoleCodes[i]);
       userRole.setAddTime(new Date());
       userRole.setAddUser(currentUserName);
       trans.add(userRole, 1);
     }
     return true;
   }
 
   public void save() {
     Transaction trans = new Transaction();
     if (!(save(trans, this.Request))) {
       return;
     }
     if (trans.commit()) {
       this.Response.setLogInfo(1, "修改成功");
       PubFun.initUserRoleMap($V("UserName"));
       Priv.updateAllPriv($V("UserName"));
     } else {
       this.Response.setLogInfo(0, "修改失败");
     }
   }
 
   public boolean save(Transaction trans, DataCollection dc) {
     ZDUserSchema user = new ZDUserSchema();
     String newPassword = dc.getString("NewPassword");
     String newConfirmPassword = dc.getString("NewConfirmPassword");
     if (!(newPassword.equals(newConfirmPassword))) {
       this.Response.setLogInfo(0, "密码不一致!");
       return false;
     }
     user.setUserName(dc.getString("UserName"));
     if (!(user.fill())) {
       return false;
     }
     user.setValue(dc);
     if (("admin".equalsIgnoreCase(user.getUserName())) && ("S".equalsIgnoreCase(user.getStatus()))) {
       this.Response.setLogInfo(0, "admin为系统自带的用户，拥有最高管理权限，不能停用!");
       return false;
     }
     user.setModifyTime(new Date());
     user.setModifyUser(User.getUserName());
     if ((StringUtil.isNotEmpty(newPassword)) && (!(Password.equals(newPassword)))) {
       user.setPassword(StringUtil.md5Hex(newPassword));
     }
     trans.add(user, 2);
 
     ZDUserRoleSchema userRole = new ZDUserRoleSchema();
     userRole.setUserName(user.getUserName());
     trans.add(userRole.query(), 5);
 
     String roleCodes = dc.getString("RoleCode");
     if (StringUtil.isEmpty(roleCodes)) {
       return true;
     }
     String[] RoleCodes = roleCodes.split(",");
     String currentUserName = User.getUserName();
 
     for (int i = 0; i < RoleCodes.length; ++i) {
       if (StringUtil.isEmpty(RoleCodes[i])) continue; if (StringUtil.isEmpty(user.getUserName())) {
         continue;
       }
       userRole = new ZDUserRoleSchema();
       userRole.setUserName(user.getUserName());
       userRole.setRoleCode(RoleCodes[i]);
       userRole.setAddTime(new Date());
       userRole.setAddUser(currentUserName);
       trans.add(userRole, 1);
     }
     return true;
   }
 
   public void del()
   {
     String UserNames = $V("UserNames");
     if (!(idPattern.matcher(UserNames).matches())) {
       this.Response.setLogInfo(0, "传入用户名称时发生错误!");
       return;
     }
     Transaction trans = new Transaction();
     if (!(del(trans, this.Request))) {
       this.Response.setLogInfo(0, Errorx.printString());
       return;
     }
     if (trans.commit()) {
       UserLog.log("User", "DelUser", "删除用户:" + UserNames + "成功", this.Request.getClientIP());
       this.Response.setLogInfo(1, "删除用户成功!");
     } else {
       UserLog.log("User", "DelUser", "删除用户:" + UserNames + "失败", this.Request.getClientIP());
       this.Response.setLogInfo(0, "删除用户失败!");
     }
   }
 
   public void stopUser() {
     String UserNames = $V("UserNames");
     if (!(idPattern.matcher(UserNames).matches())) {
       this.Response.setLogInfo(0, "传入用户名称时发生错误!");
       return;
     }
     ZDUserSchema user = new ZDUserSchema();
     ZDUserSet userSet = user.query(
       new QueryBuilder(" where UserName in ('" + UserNames.replaceAll(",", "','") + 
       "')"));
     for (int i = 0; i < userSet.size(); ++i) {
       if ("admin".equalsIgnoreCase(userSet.get(i).getUserName())) {
         this.Response.setLogInfo(0, "admin为系统自带的用户，拥有最高管理权限，不能停用!");
         return;
       }
       userSet.get(i).setStatus("S");
     }
     if (userSet.update())
       this.Response.setLogInfo(1, "停用用户成功!");
     else
       this.Response.setLogInfo(0, "停用用户失败!");
   }
 
   public static boolean del(Transaction trans, DataCollection dc)
   {
     String UserNames = dc.getString("UserNames");
     ZDUserSchema user = new ZDUserSchema();
     ZDUserSet userSet = user.query(
       new QueryBuilder(" where UserName in ('" + UserNames.replaceAll(",", "','") + 
       "')"));
     trans.add(userSet, 5);
 
     for (int i = 0; i < userSet.size(); ++i) {
       user = userSet.get(i);
       if (User.getUserName().equals(user.getUserName())) {
         Errorx.addError("当前用户为：" + User.getUserName() + ",不能删除自身用户!");
         UserLog.log("User", "DelUser", "删除用户:" + user.getUserName() + "失败", ((RequestImpl)dc).getClientIP());
         return false;
       }
       if ("admin".equalsIgnoreCase(user.getUserName())) {
         Errorx.addError("admin为系统自带的用户，拥有最高管理权限，不能删除!");
         UserLog.log("User", "DelUser", "删除用户:" + user.getUserName() + "失败", ((RequestImpl)dc).getClientIP());
         return false;
       }
 
       ZDUserRoleSchema userRole = new ZDUserRoleSchema();
       userRole.setUserName(user.getUserName());
       ZDUserRoleSet userRoleSet = userRole.query();
       trans.add(userRoleSet, 5);
 
       trans.add(new ZDPrivilegeSchema().query(
         new QueryBuilder("where OwnerType=? and Owner=?", 
         "U", user.getUserName())), 5);
     }
 
     return true;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.UserList
 * JD-Core Version:    0.5.3
 */