 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.system.AppUserDao;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.system.AppUserService;
 import java.util.List;
 import java.util.Set;
 
 public class AppUserServiceImpl extends BaseServiceImpl<AppUser>
   implements AppUserService
 {
   private AppUserDao dao;
 
   public AppUserServiceImpl(AppUserDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public AppUser findByUserName(String username)
   {
     return this.dao.findByUserName(username);
   }
 
   public List findByDepartment(String path, PagingBean pb)
   {
     return this.dao.findByDepartment(path, pb);
   }
 
   public List findByRole(Long roleId, PagingBean pb)
   {
     return this.dao.findByRole(roleId, pb);
   }
 
   public List findByRoleId(Long roleId) {
     return this.dao.findByRole(roleId);
   }
 
   public List<AppUser> findSubAppUser(String path, Set<Long> userIds, PagingBean pb)
   {
     return this.dao.findSubAppUser(path, userIds, pb);
   }
 
   public List<AppUser> findSubAppUserByRole(Long roleId, Set<Long> userIds, PagingBean pb)
   {
     return this.dao.findSubAppUserByRole(roleId, userIds, pb);
   }
 
   public List<AppUser> findByDepId(Long depId)
   {
     return this.dao.findByDepId(depId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.AppUserServiceImpl
 * JD-Core Version:    0.5.4
 */