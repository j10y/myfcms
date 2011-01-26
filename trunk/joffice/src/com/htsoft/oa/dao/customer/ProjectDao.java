package com.htsoft.oa.dao.customer;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.customer.Project;

public abstract interface ProjectDao extends BaseDao<Project>
{
  public abstract boolean checkProjectNo(String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.customer.ProjectDao
 * JD-Core Version:    0.5.4
 */