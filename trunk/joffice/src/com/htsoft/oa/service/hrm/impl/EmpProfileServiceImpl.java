 package com.htsoft.oa.service.hrm.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.hrm.EmpProfileDao;
 import com.htsoft.oa.model.hrm.EmpProfile;
 import com.htsoft.oa.service.hrm.EmpProfileService;
 
 public class EmpProfileServiceImpl extends BaseServiceImpl<EmpProfile>
   implements EmpProfileService
 {
   private EmpProfileDao dao;
 
   public EmpProfileServiceImpl(EmpProfileDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public boolean checkProfileNo(String profileNo)
   {
     return this.dao.checkProfileNo(profileNo);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.impl.EmpProfileServiceImpl
 * JD-Core Version:    0.5.4
 */