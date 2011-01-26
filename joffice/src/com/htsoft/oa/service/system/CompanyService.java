package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.Company;
import java.util.List;

public abstract interface CompanyService extends BaseService<Company>
{
  public abstract List<Company> findByHql(String paramString);

  public abstract List<Company> findCompany();
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.CompanyService
 * JD-Core Version:    0.5.4
 */