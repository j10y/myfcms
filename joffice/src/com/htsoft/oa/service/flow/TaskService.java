package com.htsoft.oa.service.flow;

import java.util.List;
import java.util.Set;

import org.jbpm.pvm.internal.task.TaskImpl;

import com.htsoft.core.jbpm.pv.TaskInfo;
import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;

public abstract interface TaskService extends BaseService<TaskImpl> {
	public abstract List<TaskImpl> getTasksByUserId(String paramString, PagingBean paramPagingBean);

	public abstract List<TaskInfo> getTaskInfosByUserId(String paramString,
			PagingBean paramPagingBean);

	public abstract Set<Long> getHastenByActivityNameVarKeyLongVal(String paramString1,
			String paramString2, Long paramLong);
}


 
 
 
 