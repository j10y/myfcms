 package com.htsoft.oa.action.communicate;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.communicate.PhoneBook;
 import com.htsoft.oa.model.communicate.PhoneGroup;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.communicate.PhoneBookService;
 import flexjson.DateTransformer;
 import flexjson.JSONSerializer;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class PhoneBookAction extends BaseAction
 {
 
   @Resource
   private PhoneBookService phoneBookService;
   private PhoneBook phoneBook;
   private PhoneGroup phoneGroup;
   private Long phoneId;
 
   public Long getPhoneId()
   {
     return this.phoneId;
   }
 
   public void setPhoneId(Long phoneId) {
     this.phoneId = phoneId;
   }
 
   public PhoneGroup getPhoneGroup() {
     return this.phoneGroup;
   }
 
   public void setPhoneGroup(PhoneGroup phoneGroup) {
     this.phoneGroup = phoneGroup;
   }
 
   public PhoneBook getPhoneBook() {
     return this.phoneBook;
   }
 
   public void setPhoneBook(PhoneBook phoneBook) {
     this.phoneBook = phoneBook;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
     String strGroupId = getRequest().getParameter("groupId");
     if (StringUtils.isNotEmpty(strGroupId)) {
       Long groupId = Long.valueOf(Long.parseLong(strGroupId));
       if (groupId.longValue() > 0L) {
         filter.addFilter("Q_phoneGroup.groupId_L_EQ", strGroupId);
       }
     }
     List list = this.phoneBookService.getAll(filter);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
     Type type = new TypeToken() {  }
     .getType();
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
     buff.append(gson.toJson(list, type));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String share() {
     String fullname = getRequest().getParameter("fullname");
     String sharedUser = getRequest().getParameter("sharedUser");
     PagingBean pb = getInitPagingBean();
     List list = this.phoneBookService.sharedPhoneBooks(fullname, sharedUser, pb);
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(",result:");
     JSONSerializer serializer = new JSONSerializer();
     buff.append(serializer.exclude(new String[] { "class", "phoneGroup", "appUser.department", "appUser.password" }).serialize(list));
     buff.append("}");
     this.jsonString = buff.toString();
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.phoneBookService.remove(new Long(id));
       }
     }
     this.jsonString = "{success:true}";
     return "success";
   }
 
   public String get()
   {
     PhoneBook phoneBook = (PhoneBook)this.phoneBookService.get(this.phoneId);
     JSONSerializer serializer = new JSONSerializer();
     serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"), new String[] { "birthday" });
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(serializer.exclude(new String[] { "class", "appUser", "phoneGroup.appUser" }).serialize(phoneBook));
     sb.append("}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String save()
   {
     AppUser appUser = ContextUtil.getCurrentUser();
     this.phoneBook.setAppUser(appUser);
     this.phoneBookService.save(this.phoneBook);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String detail()
   {
     String strPhoneId = getRequest().getParameter("phoneId");
     if (StringUtils.isNotEmpty(strPhoneId)) {
       Long phoneId = new Long(strPhoneId);
       this.phoneBook = ((PhoneBook)this.phoneBookService.get(phoneId));
     }
     return "detail";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.communicate.PhoneBookAction
 * JD-Core Version:    0.5.4
 */