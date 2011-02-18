package com.htsoft.oa.service.flow;

import java.util.List;
import java.util.Map;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.ProcessForm;

public abstract interface ProcessFormService extends BaseService<ProcessForm> {
	public abstract List getByRunId(Long paramLong);

	public abstract ProcessForm getByRunIdActivityName(Long paramLong, String paramString);

	public abstract Long getActvityExeTimes(Long paramLong, String paramString);

	public abstract Map getVariables(Long paramLong);
}


 
 
 
 
 