package com.htsoft.oa.dao.flow;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.FormData;

public abstract interface FormDataDao extends BaseDao<FormData> {
	public abstract List<FormData> getByRunIdActivityName(Long paramLong, String paramString);

	public abstract FormData getByFormIdFieldName(Long paramLong, String paramString);
}


 
 
 
 