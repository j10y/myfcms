package com.htsoft.oa.service.system;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.SysConfig;
import java.util.Map;

public abstract interface SysConfigService extends BaseService<SysConfig>
{
  public abstract SysConfig findByKey(String paramString);

  public abstract Map findByType();
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.SysConfigService
 * JD-Core Version:    0.5.4
 */