package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.ProcessRun;
import java.util.List;

public abstract interface ProcessRunDao extends BaseDao<ProcessRun>
{
  public abstract ProcessRun getByPiId(String paramString);

  public abstract List<ProcessRun> getByDefId(Long paramLong, PagingBean paramPagingBean);

  public abstract List<ProcessRun> getByUserIdSubject(Long paramLong, String paramString, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.ProcessRunDao
 * JD-Core Version:    0.5.4
 */