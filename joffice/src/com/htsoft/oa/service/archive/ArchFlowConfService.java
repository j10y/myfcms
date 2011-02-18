package com.htsoft.oa.service.archive;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchFlowConf;

public abstract interface ArchFlowConfService extends BaseService<ArchFlowConf> {
	public abstract ArchFlowConf getByFlowType(Short paramShort);
}


 
 
 
 
 