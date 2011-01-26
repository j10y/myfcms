package com.htsoft.oa.service.flow;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.ProUserAssign;
import java.util.List;

public abstract interface ProUserAssignService extends BaseService<ProUserAssign>
{
  public abstract List<ProUserAssign> getByDeployId(String paramString);

  public abstract ProUserAssign getByDeployIdActivityName(String paramString1, String paramString2);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.ProUserAssignService
 * JD-Core Version:    0.5.4
 */