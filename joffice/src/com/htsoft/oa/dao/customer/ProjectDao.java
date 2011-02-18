package com.htsoft.oa.dao.customer;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.customer.Project;

public abstract interface ProjectDao extends BaseDao<Project> {
	public abstract boolean checkProjectNo(String paramString);
}


 
 
 
 