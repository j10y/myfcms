package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.Company;
import java.util.List;

public abstract interface CompanyDao extends BaseDao<Company>
{
  public abstract List<Company> findByHql(String paramString);

  public abstract List<Company> findCompany();
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.CompanyDao
 * JD-Core Version:    0.5.4
 */