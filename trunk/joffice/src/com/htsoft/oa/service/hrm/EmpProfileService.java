package com.htsoft.oa.service.hrm;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.hrm.EmpProfile;

public abstract interface EmpProfileService extends BaseService<EmpProfile>
{
  public abstract boolean checkProfileNo(String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.EmpProfileService
 * JD-Core Version:    0.5.4
 */