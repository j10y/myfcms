package com.htsoft.oa.service.customer;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.customer.Project;

public abstract interface ProjectService extends BaseService<Project>
{
  public abstract boolean checkProjectNo(String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.ProjectService
 * JD-Core Version:    0.5.4
 */