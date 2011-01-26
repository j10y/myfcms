package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.AppFunction;

public abstract interface AppFunctionDao extends BaseDao<AppFunction>
{
  public abstract AppFunction getByKey(String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.AppFunctionDao
 * JD-Core Version:    0.5.4
 */