package com.htsoft.oa.dao.flow.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.FormDataDao;
import com.htsoft.oa.model.flow.FormData;

public class FormDataDaoImpl extends BaseDaoImpl<FormData> implements FormDataDao {
	public FormDataDaoImpl() {
		super(FormData.class);
	}

	public List<FormData> getByRunIdActivityName(Long runId, String activityName) {
		String hql = "from FormData fd where fd.processForm.processRun.runId=? and fd.processForm.activityName=?";
		return findByHql(hql, new Object[] { runId, activityName });
	}

	public FormData getByFormIdFieldName(Long formId, String fieldName) {
		String hql = "from FormData fd where fd.processForm.formId=? and fd.fieldName=?";
		return (FormData) findUnique(hql, new Object[] { formId, fieldName });
	}
}


 
 
 
 
 