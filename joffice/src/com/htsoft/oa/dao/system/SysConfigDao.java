package com.htsoft.oa.dao.system;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.SysConfig;
import java.util.List;

public abstract interface SysConfigDao extends BaseDao<SysConfig>
{
  public abstract SysConfig findByKey(String paramString);

  public abstract List<SysConfig> findConfigByTypeName(String paramString);

  public abstract List findTypeNames();
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.SysConfigDao
 * JD-Core Version:    0.5.4
 */