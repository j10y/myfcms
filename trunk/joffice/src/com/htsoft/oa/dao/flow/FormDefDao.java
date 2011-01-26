package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.FormDef;
import java.util.List;

public abstract interface FormDefDao extends BaseDao<FormDef>
{
  public abstract List<FormDef> getByDeployId(String paramString);

  public abstract FormDef getByDeployIdActivityName(String paramString1, String paramString2);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.FormDefDao
 * JD-Core Version:    0.5.4
 */