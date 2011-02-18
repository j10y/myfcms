package com.htsoft.oa.dao.hrm.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.hrm.StandSalaryItemDao;
import com.htsoft.oa.model.hrm.StandSalaryItem;

public class StandSalaryItemDaoImpl extends BaseDaoImpl<StandSalaryItem> implements
		StandSalaryItemDao {
	public StandSalaryItemDaoImpl() {
		super(StandSalaryItem.class);
	}

	public List<StandSalaryItem> getAllByStandardId(Long standardId) {
		String hql = "from StandSalaryItem ssi where ssi.standSalary.standardId=?";
		return findByHql(hql, new Object[] { standardId });
	}
}


 
 
 
 
 