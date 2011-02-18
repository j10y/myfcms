package com.htsoft.oa.service.flow;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.action.flow.FlowRunInfo;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProcessForm;
import com.htsoft.oa.model.flow.ProcessRun;

public abstract interface ProcessRunService extends BaseService<ProcessRun> {
	public abstract ProcessRun initNewProcessRun(ProDefinition paramProDefinition);

	public abstract void saveProcessRun(ProcessRun paramProcessRun, ProcessForm paramProcessForm,
			FlowRunInfo paramFlowRunInfo);

	public abstract ProcessRun getByExeId(String paramString);

	public abstract ProcessRun getByTaskId(String paramString);

	public abstract ProcessRun getByPiId(String paramString);

	public abstract void saveAndNextStep(FlowRunInfo paramFlowRunInfo);

	public abstract void removeByDefId(Long paramLong);

	public abstract List<ProcessRun> getByUserIdSubject(Long paramLong, String paramString,
			PagingBean paramPagingBean);
}


 
 
 
 
 