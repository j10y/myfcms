package com.htsoft.oa.dao.system;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.Company;

public abstract interface CompanyDao extends BaseDao<Company> {
	public abstract List<Company> findByHql(String paramString);

	public abstract List<Company> findCompany();
}


 
 
 
 