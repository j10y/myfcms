package com.htsoft.oa.dao.personal;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.personal.DutySystem;

public abstract interface DutySystemDao extends BaseDao<DutySystem> {
	public abstract void updateForNotDefult();

	public abstract List<DutySystem> getDefaultDutySystem();
}


 
 
 
 
 