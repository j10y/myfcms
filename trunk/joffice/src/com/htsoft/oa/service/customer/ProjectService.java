package com.htsoft.oa.service.customer;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.customer.Project;

public abstract interface ProjectService extends BaseService<Project> {
	public abstract boolean checkProjectNo(String paramString);
}


 
 
 
 
 