package com.htsoft.oa.service.flow;

import java.util.Map;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.FormData;

public abstract interface FormDataService extends BaseService<FormData> {
	public abstract Map<String, Object> getFromDataMap(Long paramLong, String paramString);
}


 
 
 
 
 