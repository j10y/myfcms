package com.htsoft.oa.service.archive;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchivesHandle;

public abstract interface ArchivesHandleService extends BaseService<ArchivesHandle> {
	public abstract ArchivesHandle findByUAIds(Long paramLong1, Long paramLong2);

	public abstract List<ArchivesHandle> findByAid(Long paramLong);

	public abstract int countHandler(Long paramLong);
}


 
 
 
 
 