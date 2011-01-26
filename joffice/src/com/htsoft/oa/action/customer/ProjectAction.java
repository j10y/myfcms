 package com.htsoft.oa.action.customer;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.customer.Customer;
 import com.htsoft.oa.model.customer.Project;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.FileAttach;
 import com.htsoft.oa.service.customer.ProjectService;
 import com.htsoft.oa.service.system.FileAttachService;
 import flexjson.JSONSerializer;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class ProjectAction extends BaseAction
 {
 
   @Resource
   private ProjectService projectService;
 
   @Resource
   private FileAttachService fileAttachService;
   private Project project;
   private Long projectId;
   private String projectNo;
   private String projectAttachIDs;
 
   public Long getProjectId()
   {
     return this.projectId;
   }
 
   public void setProjectId(Long projectId) {
     this.projectId = projectId;
   }
 
   public Project getProject() {
     return this.project;
   }
 
   public void setProject(Project project) {
     this.project = project;
   }
 
   public String getProjectNo() {
     return this.projectNo;
   }
 
   public void setProjectNo(String projectNo) {
     this.projectNo = projectNo;
   }
 
   public String getProjectAttachIDs() {
     return this.projectAttachIDs;
   }
 
   public void setProjectAttachIDs(String projectAttachIDs) {
     this.projectAttachIDs = projectAttachIDs;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.projectService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer json = new JSONSerializer();
     buff.append(json.exclude(new String[] { "class", "appUser.department", "contracts" }).serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.projectService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     Project project = (Project)this.projectService.get(this.projectId);
 
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(project));
     sb.append(",userId:'" + project.getAppUser().getUserId() + "'");
     sb.append(",salesman:'" + project.getAppUser().getFullname() + "'");
     sb.append(",customerName:'" + project.getCustomer().getCustomerName() + "'");
     sb.append(",customerId:'" + project.getCustomerId() + "'");
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     boolean pass = false;
     StringBuffer buff = new StringBuffer("{");
     if (this.projectId != null)
       if (this.projectService.checkProjectNo(this.project.getProjectNo()))
         pass = true;
       else buff.append("msg:'项目号已存在,请重新填写!',");
     else {
       pass = true;
     }
 
     if (pass)
     {
       String[] fileIDs = getProjectAttachIDs().split(",");
       Set projectAttachs = new HashSet();
       for (String id : fileIDs) {
         if (!id.equals("")) {
           projectAttachs.add((FileAttach)this.fileAttachService.get(new Long(id)));
         }
       }
       this.project.setProjectFiles(projectAttachs);
 
       this.projectService.save(this.project);
       buff.append("success:true,projectId:" + this.project.getProjectId() + "}");
     } else {
       buff.append("failure:true}");
     }
     setJsonString(buff.toString());
     return "success";
   }
 
   public String number()
   {
     SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss-SSS");
     String projectNo = date.format(new Date());
     setJsonString("{success:true,projectNo:'" + projectNo + "'}");
     return "success";
   }
 
   public String check()
   {
     boolean pass = false;
     pass = this.projectService.checkProjectNo(this.projectNo);
     if (pass)
       setJsonString("{success:true,pass:true}");
     else {
       setJsonString("{success:true,pass:false}");
     }
     return "success";
   }
 
   public String removeFile()
   {
     setProject((Project)this.projectService.get(this.projectId));
     Set projectFiles = this.project.getProjectFiles();
     FileAttach removeFile = (FileAttach)this.fileAttachService.get(new Long(this.projectAttachIDs));
     projectFiles.remove(removeFile);
     this.project.setProjectFiles(projectFiles);
     this.projectService.save(this.project);
     setJsonString("{success:true}");
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.customer.ProjectAction
 * JD-Core Version:    0.5.4
 */