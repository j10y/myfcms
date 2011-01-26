 package com.htsoft.oa.service.archive.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.archive.ArchRecUserDao;
 import com.htsoft.oa.model.archive.ArchRecUser;
 import com.htsoft.oa.service.archive.ArchRecUserService;
 import java.util.List;
 
 public class ArchRecUserServiceImpl extends BaseServiceImpl<ArchRecUser>
   implements ArchRecUserService
 {
   private ArchRecUserDao dao;
 
   public ArchRecUserServiceImpl(ArchRecUserDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List findDepAll()
   {
     return this.dao.findDepAll();
   }
 
   public ArchRecUser getByDepId(Long depId)
   {
     return this.dao.getByDepId(depId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.ArchRecUserServiceImpl
 * JD-Core Version:    0.5.4
 */