package com.htsoft.oa.dao.hrm.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.hrm.SalaryItemDao;
import com.htsoft.oa.model.hrm.SalaryItem;

public class SalaryItemDaoImpl extends BaseDaoImpl<SalaryItem> implements SalaryItemDao {
	public SalaryItemDaoImpl() {
		super(SalaryItem.class);
	}

	public List<SalaryItem> getAllExcludeId(String excludeIds, PagingBean pb) {
		String hql = "from SalaryItem ";
		if (StringUtils.isNotEmpty(excludeIds)) {
			hql = hql + "where salaryItemId not in(" + excludeIds + ")";
		}
		return findByHql(hql, null, pb);
	}
}


 
 
 
 
 