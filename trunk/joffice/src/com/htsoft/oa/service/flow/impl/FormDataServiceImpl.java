package com.htsoft.oa.service.flow.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.flow.FormDataDao;
import com.htsoft.oa.model.flow.FormData;
import com.htsoft.oa.service.flow.FormDataService;

public class FormDataServiceImpl extends BaseServiceImpl<FormData> implements FormDataService {
	private FormDataDao dao;

	public FormDataServiceImpl(FormDataDao dao) {
		super(dao);
		this.dao = dao;
	}

	public Map<String, Object> getFromDataMap(Long runId, String activityName) {
		List<FormData> list = this.dao.getByRunIdActivityName(runId, activityName);
		Map dataMap = new HashMap();
		for (FormData form : list) {
			dataMap.put(form.getFieldName(), form.getValue());
		}
		return dataMap;
	}
}


 
 
 
 
 