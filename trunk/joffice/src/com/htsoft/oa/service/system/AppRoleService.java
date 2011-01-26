package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.AppRole;
import java.util.HashMap;
import java.util.Set;

public abstract interface AppRoleService extends BaseService<AppRole>
{
  public abstract AppRole getByRoleName(String paramString);

  public abstract HashMap<String, Set<String>> getSecurityDataSource();
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.AppRoleService
 * JD-Core Version:    0.5.4
 */