package com.htsoft.oa.service.flow;

import com.htsoft.core.jbpm.pv.TaskInfo;
import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import java.util.List;
import java.util.Set;
import org.jbpm.pvm.internal.task.TaskImpl;

public abstract interface TaskService extends BaseService<TaskImpl>
{
  public abstract List<TaskImpl> getTasksByUserId(String paramString, PagingBean paramPagingBean);

  public abstract List<TaskInfo> getTaskInfosByUserId(String paramString, PagingBean paramPagingBean);

  public abstract Set<Long> getHastenByActivityNameVarKeyLongVal(String paramString1, String paramString2, Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.TaskService
 * JD-Core Version:    0.5.4
 */