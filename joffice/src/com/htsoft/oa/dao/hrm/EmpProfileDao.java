package com.htsoft.oa.dao.hrm;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.hrm.EmpProfile;

public abstract interface EmpProfileDao extends BaseDao<EmpProfile>
{
  public abstract boolean checkProfileNo(String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.EmpProfileDao
 * JD-Core Version:    0.5.4
 */