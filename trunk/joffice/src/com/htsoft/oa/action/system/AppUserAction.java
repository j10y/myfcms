 package com.htsoft.oa.action.system;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.Constants;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.log.Action;
 import com.htsoft.core.model.OnlineUser;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.util.StringUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.system.AppRole;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.Department;
 import com.htsoft.oa.model.system.IndexDisplay;
 import com.htsoft.oa.model.system.PanelItem;
 import com.htsoft.oa.service.system.AppRoleService;
 import com.htsoft.oa.service.system.AppUserService;
 import com.htsoft.oa.service.system.DepartmentService;
 import com.htsoft.oa.service.system.IndexDisplayService;
 import com.htsoft.oa.service.system.UserSubService;
 import flexjson.DateTransformer;
 import flexjson.JSONSerializer;
 import java.lang.reflect.Type;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.HashMap;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Set;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 
 public class AppUserAction extends BaseAction
 {
   private static Long SUPPER_MANAGER_ID = Long.valueOf(-1L);
 
   @Resource
   private AppUserService appUserService;
 
   @Resource
   private DepartmentService departmentService;
 
   @Resource
   private AppRoleService appRoleService;
 
   @Resource
   private UserSubService userSubService;
 
   @Resource
   private IndexDisplayService indexDisplayService;
   private AppUser appUser;
   private Long userId;
   private Long depId;
   private Long roleId;
 
   public Long getDepId() { return this.depId; }
 
   public void setDepId(Long depId)
   {
     this.depId = depId;
   }
 
   public Long getRoleId() {
     return this.roleId;
   }
 
   public void setRoleId(Long roleId) {
     this.roleId = roleId;
   }
 
   public Long getUserId() {
     return this.userId;
   }
 
   public void setUserId(Long userId) {
     this.userId = userId;
   }
 
   public AppUser getAppUser() {
     return this.appUser;
   }
 
   public void setAppUser(AppUser appUser) {
     this.appUser = appUser;
   }
 
   public String getCurrent()
   {
     AppUser currentUser = ContextUtil.getCurrentUser();
     Department curDep = currentUser.getDepartment();
     if (curDep == null) {
       curDep = new Department();
       curDep.setDepId(Long.valueOf(0L));
       curDep.setDepName(AppUtil.getCompanyName());
     }
     Iterator publicIds = AppUtil.getPublicMenuIds().iterator();
     StringBuffer publicIdSb = new StringBuffer();
 
     while (publicIds.hasNext()) {
       publicIdSb.append(",").append((String)publicIds.next());
     }
     List list = this.indexDisplayService.findByUser(currentUser.getUserId());
     List items = new ArrayList();
     for (IndexDisplay id : list) {
       PanelItem pi = new PanelItem();
       pi.setPanelId(id.getPortalId());
       pi.setColumn(id.getColNum().intValue());
       pi.setRow(id.getRowNum().intValue());
       items.add(pi);
     }
     StringBuffer sb = new StringBuffer();
     sb.append("{success:true,user:{userId:'").append(
       currentUser.getUserId()).append("',fullname:'").append(
       currentUser.getFullname()).append("',depId:'")
       .append(curDep.getDepId()).append("',depName:'")
       .append(curDep.getDepName()).append("',rights:'");
     sb.append(currentUser.getRights().toString().replace("[", "").replace("]", ""));
     if ((!"".equals(currentUser.getRights())) && (publicIdSb.length() > 0)) {
       sb.append(publicIdSb.toString());
     }
     Gson gson = new Gson();
     sb.append("',items:").append(gson.toJson(items).toString());
     sb.append("}}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String list() {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_delFlag_SN_EQ", Constants.FLAG_UNDELETED
       .toString());
     List list = this.appUserService.getAll(filter);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(
       ",result:");
     JSONSerializer serializer = new JSONSerializer();
     serializer.transform(new DateTransformer("yyyy-MM-dd"), new String[] { "accessionTime" });
     buff.append(serializer.exclude(new String[] { "password" }).serialize(list));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String select()
   {
     PagingBean pb = getInitPagingBean();
     String strDepId = getRequest().getParameter("depId");
 
     String path = "0.";
     this.appUser = ContextUtil.getCurrentUser();
     if (StringUtils.isNotEmpty(strDepId)) {
       Long depId = Long.valueOf(Long.parseLong(strDepId));
       Department dep = (Department)this.departmentService.get(depId);
       if (dep != null)
         path = dep.getPath();
     }
     else {
       Department dep = this.appUser.getDepartment();
       if (dep != null) {
         path = dep.getPath();
       }
     }
     List list = this.appUserService.findByDepartment(path, pb);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(",result:");
     JSONSerializer serializer = new JSONSerializer();
     serializer.transform(new DateTransformer("yyyy-MM-dd"), new String[] { "accessionTime" });
     buff.append(serializer.exclude(new String[] { "password" }).serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String online()
   {
     Map onlineUsers = new HashMap();
     Map onlineUsersByDep = new HashMap();
     Map onlineUsersByRole = new HashMap();
 
     onlineUsers = AppUtil.getOnlineUsers();
 
     if (this.depId != null) {
       for (String sessionId : onlineUsers.keySet()) {
         OnlineUser onlineUser = new OnlineUser();
         onlineUser = (OnlineUser)onlineUsers.get(sessionId);
 
         String path = "";
         if (!onlineUser.getUserId().equals(AppUser.SUPER_USER)) {
           path = onlineUser.getDepPath();
         }
         if (!this.depId.equals(new Long(0L))) {
           if (Pattern.compile("." + this.depId + ".").matcher(path).find())
             onlineUsersByDep.put(sessionId, onlineUser);
         }
         else {
           onlineUsersByDep.put(sessionId, onlineUser);
         }
       }
 
     }
 
     if (this.roleId != null) {
       for (String sessionId : onlineUsers.keySet()) {
         OnlineUser onlineUser = new OnlineUser();
         onlineUser = (OnlineUser)onlineUsers.get(sessionId);
 
         if (Pattern.compile("," + this.roleId + ",")
           .matcher(onlineUser.getRoleIds()).find()) {
           onlineUsersByRole.put(sessionId, onlineUser);
         }
       }
     }
 
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(onlineUsers.size()).append(",result:");
 
     Gson gson = new Gson();
     if (this.depId != null)
       buff.append(gson.toJson(onlineUsersByDep.values(), type));
     else if (this.roleId != null)
       buff.append(gson.toJson(onlineUsersByRole.values(), type));
     else {
       buff.append(gson.toJson(onlineUsers.values(), type));
     }
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String find()
   {
     String strRoleId = getRequest().getParameter("roleId");
     PagingBean pb = getInitPagingBean();
     if (StringUtils.isNotEmpty(strRoleId)) {
       List userList = this.appUserService.findByRole(Long.valueOf(Long.parseLong(strRoleId)), pb);
       Type type = new TypeToken() {
       }.getType();
       StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
         .append(pb.getTotalItems()).append(",result:");
       Gson gson = new GsonBuilder()
         .excludeFieldsWithoutExposeAnnotation().create();
       buff.append(gson.toJson(userList, type));
       buff.append("}");
 
       this.jsonString = buff.toString();
     } else {
       this.jsonString = "{success:false}";
     }
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     StringBuffer buff = new StringBuffer("{success:true");
     if (ids != null) {
       buff.append(",msg:'");
       for (String id : ids) {
         AppUser delUser = (AppUser)this.appUserService.get(new Long(id));
         AppRole superManager = (AppRole)this.appRoleService.get(SUPPER_MANAGER_ID);
         if (delUser.getRoles().contains(superManager)) {
           buff.append("员工:").append(delUser.getUsername()).append(
             "是超级管理员,不能删除!<br><br/>");
         } else if (delUser.getUserId().equals(
           ContextUtil.getCurrentUserId())) {
           buff.append("不能删除自己!<br></br>");
         } else {
           delUser.setStatus(Constants.FLAG_DISABLE);
           delUser.setDelFlag(Constants.FLAG_DELETED);
           delUser.setUsername("__" + delUser.getUsername());
           this.appUserService.save(delUser);
         }
       }
       buff.append("'");
     }
     buff.append("}");
     setJsonString(buff.toString());
     return "success";
   }
 
   public String get()
   {
     AppUser appUser = null;
     JSONSerializer json = 
       JsonUtil.getJSONSerializer(new String[] { "accessionTime" });
     if (this.userId != null) {
       appUser = (AppUser)this.appUserService.get(this.userId);
     } else {
       json.exclude(new String[] { "accessionTime", "department", 
         "password", "status", "position" });
       appUser = ContextUtil.getCurrentUser();
     }
 
     StringBuffer sb = new StringBuffer("{success:true,totalCounts:1,data:[");
     sb.append(JsonUtil.getJSONSerializer(new String[] { "accessionTime" })
       .serialize(appUser));
     sb.append("]}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   @Action(description="添加或保存用户信息")
   public String save()
   {
     String rolesIds = getRequest().getParameter("AppUserRoles");
     String[] ids = rolesIds.split(",");
     Set roles = new HashSet();
     for (String id : ids) {
       if (!"".equals(id)) {
         AppRole role = (AppRole)this.appRoleService.get(new Long(id));
         roles.add(role);
       }
     }
     this.appUser.setRoles(roles);
     if (this.appUser.getUserId() != null) {
       AppUser old = (AppUser)this.appUserService.get(this.appUser.getUserId());
       this.appUser.setDelFlag(old.getDelFlag());
       this.appUser.setPassword(old.getPassword());
       this.appUserService.merge(this.appUser);
       setJsonString("{success:true}");
     }
     else if (this.appUserService.findByUserName(this.appUser.getUsername()) == null) {
       this.appUser.setDelFlag(Constants.FLAG_UNDELETED);
       this.appUser.setPassword(StringUtil.encryptSha256(this.appUser.getPassword()));
       this.appUserService.merge(this.appUser);
       setJsonString("{success:true}");
     } else {
       setJsonString("{success:false,msg:'用户登录账号:" + this.appUser.getUsername() + "已存在,请重新输入账号.'}");
     }
 
     return "success";
   }
 
   public String selectedRoles()
   {
     if (this.userId != null) {
       setAppUser((AppUser)this.appUserService.get(this.userId));
       Set roles = this.appUser.getRoles();
       StringBuffer sb = new StringBuffer("[");
       for (AppRole role : roles) {
         sb.append("['" + role.getRoleId() + "','" + role.getRoleName() + 
           "'],");
       }
       sb.deleteCharAt(sb.length() - 1);
       sb.append("]");
       setJsonString(sb.toString());
     }
     return "success";
   }
 
   public String chooseRoles()
   {
     List chooseRoles = this.appRoleService.getAll();
 
     if (this.userId != null) {
       setAppUser((AppUser)this.appUserService.get(this.userId));
       Set selectedRoles = this.appUser.getRoles();
       for (AppRole role : selectedRoles) {
         chooseRoles.remove(role);
       }
     }
     StringBuffer sb = new StringBuffer("[");
     for (AppRole role : chooseRoles) {
       if (role.getStatus().shortValue() != 0) {
         sb.append("['" + role.getRoleId() + "','" + role.getRoleName() + 
           "'],");
       }
     }
     sb.deleteCharAt(sb.length() - 1);
     sb.append("]");
     setJsonString(sb.toString());
     return "success";
   }
 
   @Action(description="修改密码")
   public String resetPassword()
   {
     String userId = getRequest().getParameter("appUserUserId");
     String oldPassword = StringUtil.encryptSha256(getRequest()
       .getParameter("oldPassword"));
     String newPassword = getRequest().getParameter("newPassword");
     String againPassword = getRequest().getParameter("againPassword");
     if (StringUtils.isNotEmpty(userId))
       setAppUser((AppUser)this.appUserService.get(new Long(userId)));
     else {
       setAppUser(ContextUtil.getCurrentUser());
     }
     StringBuffer msg = new StringBuffer("{msg:'");
     boolean pass = false;
     if (oldPassword.equals(this.appUser.getPassword()))
       if (newPassword.equals(againPassword))
         pass = true;
       else
         msg.append("两次输入不一致.'");
     else
       msg.append("旧密码输入不正确.'");
     if (pass) {
       this.appUser.setPassword(StringUtil.encryptSha256(newPassword));
       this.appUserService.save(this.appUser);
       setJsonString("{success:true}");
     } else {
       msg.append(",failure:true}");
       setJsonString(msg.toString());
     }
     return "success";
   }
 
   public String photo()
   {
     setAppUser((AppUser)this.appUserService.get(getUserId()));
     this.appUser.setPhoto("");
     this.appUserService.save(this.appUser);
     return "success";
   }
 
   public String subAdepartment()
   {
     PagingBean pb = getInitPagingBean();
     String strDepId = getRequest().getParameter("depId");
     String path = "0.";
     AppUser user = ContextUtil.getCurrentUser();
     if (StringUtils.isNotEmpty(strDepId)) {
       Long depId = Long.valueOf(Long.parseLong(strDepId));
       Department dep = (Department)this.departmentService.get(depId);
       if (dep != null)
         path = dep.getPath();
     }
     else {
       Department dep = user.getDepartment();
       if (dep != null) {
         path = dep.getPath();
       }
     }
     if ("0.".equals(path)) {
       path = null;
     }
     Long uId = user.getUserId();
     Set userIds = this.userSubService.findAllUpUser(uId);
     List userIdsL = this.userSubService.subUsers(uId);
     userIds.add(uId);
     for (Long l : userIdsL) {
       userIds.add(l);
     }
     List list = this.appUserService.findSubAppUser(path, userIds, pb);
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(",result:");
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
       .create();
     buff.append(gson.toJson(list, type));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String subArole()
   {
     String strRoleId = getRequest().getParameter("roleId");
     PagingBean pb = getInitPagingBean();
     AppUser user = ContextUtil.getCurrentUser();
     if (StringUtils.isNotEmpty(strRoleId)) {
       Long uId = user.getUserId();
       Set userIds = this.userSubService.findAllUpUser(uId);
       List userIdsL = this.userSubService.subUsers(uId);
       userIds.add(uId);
       for (Long l : userIdsL) {
         userIds.add(l);
       }
       List userList = this.appUserService.findSubAppUserByRole(
         new Long(strRoleId), userIds, pb);
 
       Type type = new TypeToken() {
       }.getType();
       StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
         .append(pb.getTotalItems()).append(",result:");
       Gson gson = new GsonBuilder()
         .excludeFieldsWithoutExposeAnnotation().create();
       buff.append(gson.toJson(userList, type));
       buff.append("}");
       this.jsonString = buff.toString();
     } else {
       this.jsonString = "{success:false}";
     }
     return "success";
   }
 
   public String onlineAsub()
   {
     Map onlineUsers = new HashMap();
     Map onlineUsersBySub = new HashMap();
     onlineUsers = AppUtil.getOnlineUsers();
 
     AppUser user = ContextUtil.getCurrentUser();
     Long uId = user.getUserId();
     Set userIds = this.userSubService.findAllUpUser(uId);
     userIds.add(uId);
     List userIdsL = this.userSubService.subUsers(uId);
     for (Long l : userIdsL) {
       userIds.add(l);
     }
     for (String sessionId : onlineUsers.keySet()) {
       OnlineUser onlineUser = new OnlineUser();
       onlineUser = (OnlineUser)onlineUsers.get(sessionId);
       if (!userIds.contains(onlineUser.getUserId())) {
         onlineUsersBySub.put(sessionId, onlineUser);
       }
     }
     Type type = new TypeToken() {
     }.getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(onlineUsers.size()).append(",result:");
     Gson gson = new Gson();
     buff.append(gson.toJson(onlineUsersBySub.values(), type));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String upUser()
   {
     List ids = this.userSubService.upUser(ContextUtil.getCurrentUserId());
     List list = new ArrayList();
     for (Long l : ids) {
       list.add((AppUser)this.appUserService.get(l));
     }
     StringBuffer buff = new StringBuffer("[");
     for (AppUser user : list) {
       buff.append("['" + user.getUserId().toString() + "','" + 
         user.getFullname() + "'],");
     }
     if (list.size() > 0) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]");
     setJsonString(buff.toString());
     return "success";
   }
 
   @Action(description="修改个人资料")
   public String profile()
   {
     AppUser old = ContextUtil.getCurrentUser();
     try {
       BeanUtil.copyNotNullProperties(old, this.appUser);
     } catch (Exception e) {
       this.logger.info(e);
     }
     this.appUserService.save(old);
     this.jsonString = "{success:true}";
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.AppUserAction
 * JD-Core Version:    0.5.4
 */