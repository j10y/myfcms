package com.htsoft.oa.dao.flow;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.JbpmTask;
import java.util.List;
import org.jbpm.pvm.internal.task.TaskImpl;

public abstract interface TaskDao extends BaseDao<TaskImpl>
{
  public abstract List<TaskImpl> getTasksByUserId(String paramString, PagingBean paramPagingBean);

  public abstract List<JbpmTask> getByActivityNameVarKeyLongVal(String paramString1, String paramString2, Long paramLong);

  public abstract List<Long> getGroupByTask(Long paramLong);

  public abstract List<Long> getUserIdByTask(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.TaskDao
 * JD-Core Version:    0.5.4
 */