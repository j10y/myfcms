package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.AppRole;
import java.util.HashMap;
import java.util.Set;

public abstract interface AppRoleDao extends BaseDao<AppRole>
{
  public abstract AppRole getByRoleName(String paramString);

  public abstract HashMap<String, Set<String>> getSecurityDataSource();
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.AppRoleDao
 * JD-Core Version:    0.5.4
 */