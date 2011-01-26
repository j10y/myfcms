 package com.htsoft.oa.dao.system.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.system.CompanyDao;
 import com.htsoft.oa.model.system.Company;
 import java.util.List;
 
 public class CompanyDaoImpl extends BaseDaoImpl<Company>
   implements CompanyDao
 {
   public CompanyDaoImpl()
   {
     super(Company.class);
   }
 
   public List<Company> findCompany() {
     String hql = "from Company c";
     return findByHql(hql);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.CompanyDaoImpl
 * JD-Core Version:    0.5.4
 */