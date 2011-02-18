package com.htsoft.oa.dao.hrm;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.hrm.EmpProfile;

public abstract interface EmpProfileDao extends BaseDao<EmpProfile> {
	public abstract boolean checkProfileNo(String paramString);
}


 
 
 
 