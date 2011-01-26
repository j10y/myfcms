package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.ProcessForm;
import java.util.List;
import java.util.Map;

public abstract interface ProcessFormDao extends BaseDao<ProcessForm>
{
  public abstract List getByRunId(Long paramLong);

  public abstract ProcessForm getByRunIdActivityName(Long paramLong, String paramString);

  public abstract Long getActvityExeTimes(Long paramLong, String paramString);

  public abstract Map getVariables(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.ProcessFormDao
 * JD-Core Version:    0.5.4
 */