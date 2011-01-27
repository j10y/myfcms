 package com.htsoft.oa.action.system;
 
 import com.google.gson.Gson;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.UserSub;
 import com.htsoft.oa.service.system.AppUserService;
 import com.htsoft.oa.service.system.UserSubService;
 import flexjson.DateTransformer;
 import flexjson.JSONSerializer;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class UserSubAction extends BaseAction
 {
 
   @Resource
   private UserSubService userSubService;
   private UserSub userSub;
 
   @Resource
   private AppUserService appUserService;
   private Long subId;
 
   public Long getSubId()
   {
     return this.subId;
   }
 
   public void setSubId(Long subId) {
     this.subId = subId;
   }
 
   public UserSub getUserSub() {
     return this.userSub;
   }
 
   public void setUserSub(UserSub userSub) {
     this.userSub = userSub;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId().toString());
     filter.addFilter("Q_subAppUser.delFlag_SN_EQ", "0");
     List list = this.userSubService.getAll(filter);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
     JSONSerializer serializer = new JSONSerializer();
     serializer.transform(new DateTransformer("yyyy-MM-dd"), new String[] { "subAppUser.accessionTime" });
     buff.append(serializer.exclude(new String[] { "subAppUser.password", "class" }).serialize(list));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String combo() {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId().toString());
     List<UserSub> list = this.userSubService.getAll(filter);
     StringBuffer buff = new StringBuffer("[");
     for (UserSub sub : list) {
       buff.append("['" + sub.getSubAppUser().getUserId().toString() + "','" + sub.getSubAppUser().getFullname() + "'],");
     }
     if (list.size() > 0) {
       buff.deleteCharAt(buff.length() - 1);
     }
     buff.append("]");
     setJsonString(buff.toString());
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.userSubService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     UserSub userSub = (UserSub)this.userSubService.get(this.subId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(userSub));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     String subUserIds = getRequest().getParameter("subUserIds");
     String[] strSubUserId = subUserIds.split(",");
     for (int i = 0; i < strSubUserId.length; ++i) {
       UserSub usb = new UserSub();
       usb.setUserId(ContextUtil.getCurrentUserId());
       Long subUserId = new Long(strSubUserId[i]);
       AppUser subAppUser = (AppUser)this.appUserService.get(subUserId);
       usb.setSubAppUser(subAppUser);
       this.userSubService.save(usb);
     }
     setJsonString("{success:true}");
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.UserSubAction
 * JD-Core Version:    0.5.4
 */