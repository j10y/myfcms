package com.htsoft.oa.dao.archive.impl;

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.archive.ArchFlowConfDao;
import com.htsoft.oa.model.archive.ArchFlowConf;

public class ArchFlowConfDaoImpl extends BaseDaoImpl<ArchFlowConf> implements ArchFlowConfDao {
	public ArchFlowConfDaoImpl() {
		super(ArchFlowConf.class);
	}

	public ArchFlowConf getByFlowType(Short archType) {
		String hql = "from ArchFlowConf vo where vo.archType=?";
		Object[] objs = { archType };
		List list = findByHql(hql, objs);
		if (list.size() == 1) {
			return (ArchFlowConf) list.get(0);
		}
		return null;
	}
}


 
 
 
 
 