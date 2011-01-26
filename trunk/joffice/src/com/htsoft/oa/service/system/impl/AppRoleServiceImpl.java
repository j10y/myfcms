 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.AppRoleDao;
 import com.htsoft.oa.model.system.AppRole;
 import com.htsoft.oa.service.system.AppRoleService;
 import java.util.HashMap;
 import java.util.Set;
 
 public class AppRoleServiceImpl extends BaseServiceImpl<AppRole>
   implements AppRoleService
 {
   private AppRoleDao dao;
 
   public AppRoleServiceImpl(AppRoleDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public AppRole getByRoleName(String roleName) {
     return this.dao.getByRoleName(roleName);
   }
 
   public HashMap<String, Set<String>> getSecurityDataSource()
   {
     return this.dao.getSecurityDataSource();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.AppRoleServiceImpl
 * JD-Core Version:    0.5.4
 */