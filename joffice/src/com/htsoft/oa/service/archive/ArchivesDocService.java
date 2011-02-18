package com.htsoft.oa.service.archive;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchivesDoc;

public abstract interface ArchivesDocService extends BaseService<ArchivesDoc> {
	public abstract List<ArchivesDoc> findByAid(Long paramLong);
}


 
 
 
 
 