 package com.htsoft.oa.action.hrm;
 
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.hrm.SalaryItem;
 import com.htsoft.oa.service.hrm.SalaryItemService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class SalaryItemAction extends BaseAction
 {
 
   @Resource
   private SalaryItemService salaryItemService;
   private SalaryItem salaryItem;
   private Long salaryItemId;
 
   public Long getSalaryItemId()
   {
     return this.salaryItemId;
   }
 
   public void setSalaryItemId(Long salaryItemId) {
     this.salaryItemId = salaryItemId;
   }
 
   public SalaryItem getSalaryItem() {
     return this.salaryItem;
   }
 
   public void setSalaryItem(SalaryItem salaryItem) {
     this.salaryItem = salaryItem;
   }
 
   public String list()
   {
     PagingBean pb = getInitPagingBean();
     String ids = getRequest().getParameter("exclude");
     if (StringUtils.isNotEmpty(ids)) {
       ids = ids.substring(0, ids.length() - 1);
     }
 
     List list = this.salaryItemService.getAllExcludeId(ids, pb);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(pb.getTotalItems()).append(",result:");
 
     Gson gson = new Gson();
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String search()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.salaryItemService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     Gson gson = new Gson();
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.salaryItemService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     SalaryItem salaryItem = (SalaryItem)this.salaryItemService.get(this.salaryItemId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(salaryItem));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.salaryItemService.save(this.salaryItem);
     setJsonString("{success:true}");
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.hrm.SalaryItemAction
 * JD-Core Version:    0.5.4
 */