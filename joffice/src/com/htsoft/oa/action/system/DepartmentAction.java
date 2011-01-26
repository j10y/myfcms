 package com.htsoft.oa.action.system;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.system.Department;
 import com.htsoft.oa.service.system.AppUserService;
 import com.htsoft.oa.service.system.CompanyService;
 import com.htsoft.oa.service.system.DepartmentService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class DepartmentAction extends BaseAction
 {
   private Department department;
 
   @Resource
   private DepartmentService departmentService;
 
   @Resource
   private AppUserService appUserService;
 
   @Resource
   private CompanyService companyService;
 
   public Department getDepartment()
   {
     return this.department;
   }
 
   public void setDepartment(Department department) {
     this.department = department;
   }
 
   public String select()
   {
     String depId = getRequest().getParameter("depId");
     QueryFilter filter = new QueryFilter(getRequest());
     if ((StringUtils.isNotEmpty(depId)) && (!"0".equals(depId))) {
       this.department = ((Department)this.departmentService.get(new Long(depId)));
       filter.addFilter("Q_path_S_LFK", this.department.getPath());
     }
 
     filter.addSorted("path", "asc");
     List list = this.departmentService.getAll(filter);
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':").append(filter.getPagingBean().getTotalItems()).append(",result:");
     Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
     buff.append(gson.toJson(list, type));
     buff.append("}");
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String list() {
     String opt = getRequest().getParameter("opt");
     StringBuffer buff = new StringBuffer();
     if (StringUtils.isNotEmpty(opt))
       buff.append("[");
     else {
       buff.append("[{id:'0',text:'" + AppUtil.getCompanyName() + "',expanded:true,children:[");
     }
 
     List listParent = this.departmentService.findByParentId(new Long(0L));
     for (Department dep : listParent) {
       buff.append("{id:'" + dep.getDepId() + "',text:'" + dep.getDepName() + "',");
       buff.append(findChild(dep.getDepId()));
     }
     if (!listParent.isEmpty()) {
       buff.deleteCharAt(buff.length() - 1);
     }
     if (StringUtils.isNotEmpty(opt))
       buff.append("]");
     else {
       buff.append("]}]");
     }
     setJsonString(buff.toString());
     return "success";
   }
 
   public String findChild(Long depId)
   {
     StringBuffer buff1 = new StringBuffer("");
     List list = this.departmentService.findByParentId(depId);
     if (list.size() == 0) {
       buff1.append("leaf:true},");
       return buff1.toString();
     }
     buff1.append("children:[");
     for (Department dep2 : list) {
       buff1.append("{id:'" + dep2.getDepId() + "',text:'" + dep2.getDepName() + "',");
       buff1.append(findChild(dep2.getDepId()));
     }
     buff1.deleteCharAt(buff1.length() - 1);
     buff1.append("]},");
     return buff1.toString();
   }
 
   public String add()
   {
     Long parentId = this.department.getParentId();
     String depPath = "";
     int level = 0;
     if (parentId.longValue() < 1L) {
       parentId = new Long(0L);
       depPath = "0.";
     } else {
       depPath = ((Department)this.departmentService.get(parentId)).getPath();
       level = ((Department)this.departmentService.get(parentId)).getDepLevel().intValue();
     }
     if (level < 1) {
       level = 1;
     }
     this.department.setDepLevel(Integer.valueOf(level + 1));
     this.departmentService.save(this.department);
     if (this.department != null) {
       depPath = depPath + this.department.getDepId().toString() + ".";
       this.department.setPath(depPath);
       this.departmentService.save(this.department);
       setJsonString("{success:true}");
     } else {
       setJsonString("{success:false}");
     }
     return "success";
   }
 
   public String remove() {
     PagingBean pb = getInitPagingBean();
     Long depId = Long.valueOf(Long.parseLong(getRequest().getParameter("depId")));
     Department department = (Department)this.departmentService.get(depId);
     List userList = this.appUserService.findByDepartment(department.getPath(), pb);
     if (userList.size() > 0) {
       setJsonString("{success:false,message:'该部门还有人员，请将人员转移后再删除部门!'}");
       return "success";
     }
     this.departmentService.remove(depId);
     List list = this.departmentService.findByParentId(depId);
     for (Department dep : list) {
       List users = this.appUserService.findByDepartment(dep.getPath(), pb);
       if (users.size() > 0) {
         setJsonString("{success:false,message:'该部门还有人员，请将人员转移后再删除部门!'}");
         return "success";
       }
       this.departmentService.remove(dep.getDepId());
     }
     setJsonString("{success:true}");
     return "success";
   }
 
   public String detail() {
     Long depId = Long.valueOf(Long.parseLong(getRequest().getParameter("depId")));
     setDepartment((Department)this.departmentService.get(depId));
     Gson gson = new Gson();
     StringBuffer sb = new StringBuffer("{success:true,data:[");
     sb.append(gson.toJson(this.department));
     sb.append("]}");
     setJsonString(sb.toString());
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.system.DepartmentAction
 * JD-Core Version:    0.5.4
 */