package com.htsoft.oa.service.hrm;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.hrm.EmpProfile;

public abstract interface EmpProfileService extends BaseService<EmpProfile> {
	public abstract boolean checkProfileNo(String paramString);
}


 
 
 
 
 