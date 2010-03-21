 package com.zving.cms.api;
 
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.utility.Errorx;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZDBranchSchema;
 import com.zving.schema.ZDPrivilegeSchema;
 import com.zving.schema.ZDUserRoleSchema;
 import com.zving.schema.ZDUserRoleSet;
 import com.zving.schema.ZDUserSchema;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.Set;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class UserAPI
   implements APIInterface
 {
   private Mapx params;
   private static final Pattern userPattern = Pattern.compile("[\\w@\\.\\,一-龥]*", 34);
 
   public boolean delete() {
     String username = this.params.getString("Username");
     username = username.toLowerCase();
 
     if (!(userPattern.matcher(username).matches())) {
       return false;
     }
 
     if ("administrator".equalsIgnoreCase(username)) {
       return false;
     }
 
     if ("admin".equalsIgnoreCase(username)) {
       return false;
     }
 
     ZDUserSchema user = new ZDUserSchema();
     user.setUserName(username);
     if (!(user.fill())) {
       return false;
     }
 
     Transaction trans = new Transaction();
 
     ZDUserRoleSchema userRole = new ZDUserRoleSchema();
     userRole.setUserName(user.getUserName());
     ZDUserRoleSet userRoleSet = userRole.query();
     trans.add(userRoleSet, 3);
 
     trans.add(new ZDPrivilegeSchema().query(new QueryBuilder("where OwnerType=? and Owner=?", "U", user.getUserName())), 3);
     trans.add(user, 3);
 
     return (trans.commit());
   }
 
   public long insert()
   {
     return insert(new Transaction());
   }
 
   public long insert(Transaction trans)
   {
     String username = this.params.getString("Username");
     String realname = this.params.getString("RealName");
     String password = this.params.getString("Password");
     String email = this.params.getString("Email");
     String branchInnerCode = this.params.getString("BranchInnerCode");
     String isBranchAdmin = this.params.getString("IsBranchAdmin");
     String status = this.params.getString("Status");
 
     String type = this.params.getString("Type");
     if ((StringUtil.isEmpty(username)) || (StringUtil.isEmpty(password))) {
       return -1L;
     }
 
     username = username.toLowerCase();
     if (!(userPattern.matcher(username).matches())) {
       Errorx.addError("用户名最多20位，仅限英文字母，数字，汉字，半角“.”、“@”");
       return -1L;
     }
 
     ZDUserSchema user = new ZDUserSchema();
     user.setUserName(username);
     if (user.fill()) {
       Errorx.addError(username + "用户已经存在!");
       return 0L;
     }
 
     user.setRealName(realname);
     if (StringUtil.isEmpty(realname)) {
       user.setRealName(username);
     }
 
     if (StringUtil.isEmpty(branchInnerCode)) {
       branchInnerCode = "0001";
     }
     user.setBranchInnerCode(branchInnerCode);
 
     if ("Y".equals(isBranchAdmin))
       user.setIsBranchAdmin("Y");
     else {
       user.setIsBranchAdmin("N");
     }
 
     if ("Y".equals(status))
       user.setStatus("Y");
     else {
       user.setStatus("N");
     }
 
     user.setPassword(StringUtil.md5Hex(password));
     if (StringUtil.isEmpty(type))
       user.setType("0");
     else {
       user.setType(type);
     }
     user.setEmail(email);
     user.setProp1(this.params.getString("Prop1"));
     user.setAddTime(new Date());
     user.setAddUser("wsdl");
 
     trans.add(user, 1);
 
     String roleCodes = this.params.getString("RoleCode");
     if (StringUtil.isNotEmpty(roleCodes)) {
       roleCodes = "'" + roleCodes.replaceAll(",", "','") + "'";
       DataTable dt = new QueryBuilder("select RoleCode from zdrole where RoleCode in (" + roleCodes + ")")
         .executeDataTable();
 
       String[] RoleCodes = (String[])dt.getColumnValues(0);
       Date addTime = new Date();
       for (int i = 0; i < RoleCodes.length; ++i) {
         if (StringUtil.isEmpty(RoleCodes[i])) {
           continue;
         }
         ZDUserRoleSchema userRole = new ZDUserRoleSchema();
         userRole.setUserName(user.getUserName());
         userRole.setRoleCode(RoleCodes[i]);
         userRole.setAddTime(addTime);
         userRole.setAddUser("wsdl");
 
         trans.add(userRole, 1);
       }
     } else {
       ZDUserRoleSchema userRole = new ZDUserRoleSchema();
       userRole.setUserName(user.getUserName());
       userRole.setRoleCode("everyone");
       userRole.setAddTime(new Date());
       userRole.setAddUser("wsdl");
 
       trans.add(userRole, 1);
     }
 
     if (trans.commit())
       return 1L;
     return -1L;
   }
 
   public boolean setSchema(Schema schema) {
     return false;
   }
 
   public boolean update() {
     String username = this.params.getString("Username");
     String realname = this.params.getString("RealName");
     String password = this.params.getString("Password");
     String email = this.params.getString("Email");
     String branchInnerCode = this.params.getString("BranchInnerCode");
     String isBranchAdmin = this.params.getString("IsBranchAdmin");
     String status = this.params.getString("Status");
 
     String type = this.params.getString("Type");
     if (StringUtil.isEmpty(username)) {
       return false;
     }
 
     username = username.toLowerCase();
     if (!(userPattern.matcher(username).matches())) {
       Errorx.addError("用户名最多20位，仅限英文字母，数字，汉字，半角“.”、“@”");
       return false;
     }
     ZDUserSchema user = new ZDUserSchema();
     user.setUserName(username);
     if (!(user.fill())) {
       Errorx.addError(username + "用户不存在!");
       return false;
     }
 
     if (StringUtil.isNotEmpty(branchInnerCode)) {
       ZDBranchSchema branch = new ZDBranchSchema();
       branch.setBranchInnerCode(branchInnerCode);
       if (!(branch.fill())) {
         Errorx.addError(branch.getBranchCode() + "机构不存在!");
         return false;
       }
 
       user.setBranchInnerCode(branchInnerCode);
     }
 
     Transaction trans = new Transaction();
     if (StringUtil.isNotEmpty(realname)) {
       user.setRealName(realname);
     }
 
     if ("Y".equals(isBranchAdmin))
       user.setIsBranchAdmin("Y");
     else {
       user.setIsBranchAdmin("N");
     }
 
     if ("Y".equals(status))
       user.setStatus("Y");
     else {
       user.setStatus("N");
     }
 
     if (StringUtil.isNotEmpty(password)) {
       user.setPassword(StringUtil.md5Hex(password));
     }
     if (StringUtil.isEmpty(type))
       user.setType("0");
     else {
       user.setType(type);
     }
     if (StringUtil.isNotEmpty(email)) {
       user.setEmail(email);
     }
     user.setProp1(this.params.getString("Prop1"));
     user.setModifyTime(new Date());
     user.setModifyUser("wsdl");
 
     trans.add(user, 2);
 
     String roleCodes = this.params.getString("RoleCode");
     if (StringUtil.isNotEmpty(roleCodes)) {
       ZDUserRoleSchema userRole = new ZDUserRoleSchema();
       userRole.setUserName(user.getUserName());
       trans.add(userRole.query(), 3);
 
       roleCodes = "'" + roleCodes.replaceAll(",", "','") + "'";
       DataTable dt = new QueryBuilder("select RoleCode from zdrole where RoleCode in (" + roleCodes + ")")
         .executeDataTable();
       String[] RoleCodes = (String[])dt.getColumnValues(0);
 
       Date date = new Date();
       for (int i = 0; i < RoleCodes.length; ++i) {
         if (StringUtil.isEmpty(RoleCodes[i])) {
           continue;
         }
         userRole = new ZDUserRoleSchema();
         userRole.setUserName(user.getUserName());
         userRole.setRoleCode(RoleCodes[i]);
         userRole.setAddTime(date);
         userRole.setAddUser("wsdl");
         trans.add(userRole, 1);
       }
 
     }
 
     return (trans.commit());
   }
 
   public Mapx getParams()
   {
     return this.params;
   }
 
   public void setParams(Mapx params) {
     convertParams(params);
     this.params = params;
   }
 
   public void convertParams(Mapx params) {
     Iterator iter = params.keySet().iterator();
     while (iter.hasNext()) {
       Object key = iter.next();
       String value = params.getString(key);
       if ((StringUtil.isEmpty(value)) || ("null".equalsIgnoreCase(value)))
         this.params.put(key, "");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.api.UserAPI
 * JD-Core Version:    0.5.3
 */