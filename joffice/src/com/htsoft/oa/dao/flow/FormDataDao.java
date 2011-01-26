package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.FormData;
import java.util.List;

public abstract interface FormDataDao extends BaseDao<FormData>
{
  public abstract List<FormData> getByRunIdActivityName(Long paramLong, String paramString);

  public abstract FormData getByFormIdFieldName(Long paramLong, String paramString);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.FormDataDao
 * JD-Core Version:    0.5.4
 */