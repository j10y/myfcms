 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.CompanyDao;
 import com.htsoft.oa.model.system.Company;
 import com.htsoft.oa.service.system.CompanyService;
 import java.util.List;
 
 public class CompanyServiceImpl extends BaseServiceImpl<Company>
   implements CompanyService
 {
   private CompanyDao companyDao;
 
   public CompanyServiceImpl(CompanyDao companyDao)
   {
     super(companyDao);
     this.companyDao = companyDao;
   }
 
   public List<Company> findCompany()
   {
     return this.companyDao.findCompany();
   }
 
   public List<Company> findByHql(String hql)
   {
     return null;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.CompanyServiceImpl
 * JD-Core Version:    0.5.4
 */