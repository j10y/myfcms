package com.htsoft.oa.service.system;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.Company;

public abstract interface CompanyService extends BaseService<Company> {
	public abstract List<Company> findByHql(String paramString);

	public abstract List<Company> findCompany();
}


 
 
 
 
 