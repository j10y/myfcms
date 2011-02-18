package com.htsoft.oa.dao.archive;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.archive.ArchFlowConf;

public abstract interface ArchFlowConfDao extends BaseDao<ArchFlowConf> {
	public abstract ArchFlowConf getByFlowType(Short paramShort);
}


 
 
 
 
 