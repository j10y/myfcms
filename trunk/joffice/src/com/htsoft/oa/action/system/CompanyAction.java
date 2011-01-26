 package com.htsoft.oa.action.system;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.oa.model.system.Company;
 import com.htsoft.oa.service.system.CompanyService;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 
 public class CompanyAction extends BaseAction
 {
   private Company company;
 
   @Resource
   private CompanyService companyService;
 
   public Company getCompany()
   {
     return this.company;
   }
 
   public void setCompany(Company company) {
     this.company = company;
   }
 
   public String check()
   {
     List list = this.companyService.findCompany();
     if (list.size() > 0)
       setJsonString("{success:true}");
     else {
       setJsonString("{success:false}");
     }
     return "success";
   }
 
   public String list() {
     List list = this.companyService.findCompany();
     if (list.size() > 0) {
       this.company = ((Company)list.get(0));
       Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
       StringBuffer cf = new StringBuffer("{success:true,result:[");
       cf.append(gson.toJson(this.company));
       cf.append("]}");
       setJsonString(cf.toString());
     } else {
       setJsonString("{success:false,message:'还没有填写公司信息'}");
       return "success";
     }
     return "success";
   }
 
   public String add()
   {
     this.companyService.save(this.company);
     Map map = AppUtil.getSysConfig();
     map.remove("app.logoPath");
     map.remove("app.companyName");
     map.put("app.logoPath", this.company.getLogo());
     map.put("app.companyName", this.company.getCompanyName());
     setJsonString("{success:true}");
     return "success";
   }
 
   public String delphoto() {
     List list = this.companyService.findCompany();
     if (list.size() > 0) {
       this.company = ((Company)list.get(0));
       this.company.setLogo("");
       this.companyService.save(this.company);
     }
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.CompanyAction
 * JD-Core Version:    0.5.4
 */