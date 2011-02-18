package com.htsoft.oa.dao.flow;

import java.util.List;

import org.jbpm.pvm.internal.task.TaskImpl;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.JbpmTask;

public abstract interface TaskDao extends BaseDao<TaskImpl> {
	public abstract List<TaskImpl> getTasksByUserId(String paramString, PagingBean paramPagingBean);

	public abstract List<JbpmTask> getByActivityNameVarKeyLongVal(String paramString1,
			String paramString2, Long paramLong);

	public abstract List<Long> getGroupByTask(Long paramLong);

	public abstract List<Long> getUserIdByTask(Long paramLong);
}


 
 
 
 