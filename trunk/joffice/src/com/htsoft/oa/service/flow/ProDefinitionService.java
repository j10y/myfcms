package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.ProDefinition;

public abstract interface ProDefinitionService extends BaseService<ProDefinition>
{
  public abstract ProDefinition getByDeployId(String paramString);

  public abstract ProDefinition getByName(String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.ProDefinitionService
 * JD-Core Version:    0.5.4
 */