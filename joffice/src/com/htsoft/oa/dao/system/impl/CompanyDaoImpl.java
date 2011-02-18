package com.htsoft.oa.dao.system.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.CompanyDao;
import com.htsoft.oa.model.system.Company;

public class CompanyDaoImpl extends BaseDaoImpl<Company> implements CompanyDao {
	public CompanyDaoImpl() {
		super(Company.class);
	}

	public List<Company> findCompany() {
		String hql = "from Company c";
		return findByHql(hql);
	}
}


 
 
 
 
 