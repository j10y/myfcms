 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.DepartmentDao;
 import com.htsoft.oa.model.system.Department;
 import com.htsoft.oa.service.system.DepartmentService;
 import java.util.List;
 
 public class DepartmentServiceImpl extends BaseServiceImpl<Department>
   implements DepartmentService
 {
   private DepartmentDao dao;
 
   public DepartmentServiceImpl(DepartmentDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<Department> findByParentId(Long parentId) {
     return this.dao.findByParentId(parentId);
   }
 
   public List<Department> findByDepName(String depName) {
     return this.dao.findByDepName(depName);
   }
 
   public List<Department> findByPath(String path) {
     return this.dao.findByPath(path);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.DepartmentServiceImpl
 * JD-Core Version:    0.5.4
 */