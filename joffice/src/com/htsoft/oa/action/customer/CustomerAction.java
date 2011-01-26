 package com.htsoft.oa.action.customer;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.customer.Customer;
 import com.htsoft.oa.service.customer.CustomerService;
 import java.lang.reflect.Type;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class CustomerAction extends BaseAction
 {
 
   @Resource
   private CustomerService customerService;
   private Customer customer;
   private Long customerId;
   private String customerNo;
 
   public Long getCustomerId()
   {
     return this.customerId;
   }
 
   public void setCustomerId(Long customerId) {
     this.customerId = customerId;
   }
 
   public Customer getCustomer() {
     return this.customer;
   }
 
   public void setCustomer(Customer customer) {
     this.customer = customer;
   }
 
   public String getCustomerNo() {
     return this.customerNo;
   }
 
   public void setCustomerNo(String customerNo) {
     this.customerNo = customerNo;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.customerService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
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
         this.customerService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     Customer customer = (Customer)this.customerService.get(this.customerId);
 
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(customer));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     boolean pass = false;
     StringBuffer buff = new StringBuffer("{");
     if (this.customer.getCustomerId() == null)
       if (this.customerService.checkCustomerNo(this.customer.getCustomerNo()))
         pass = true;
       else buff.append("msg:'客户已存在,请重新填写.',rewrite:true,");
     else {
       pass = true;
     }
     if (pass) {
       this.customerService.save(this.customer);
       buff.append("success:true,customerId:" + this.customer.getCustomerId() + "}");
     } else {
       buff.append("failure:true}");
     }
     setJsonString(buff.toString());
     return "success";
   }
 
   public String number()
   {
     SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss-SSS");
     String customerNo = date.format(new Date());
     setJsonString("{success:true,customerNo:'" + customerNo + "'}");
     return "success";
   }
 
   public String check()
   {
     boolean pass = false;
     pass = this.customerService.checkCustomerNo(this.customerNo);
     if (pass)
       setJsonString("{success:true,pass:true}");
     else {
       setJsonString("{success:true,pass:false}");
     }
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.customer.CustomerAction
 * JD-Core Version:    0.5.4
 */