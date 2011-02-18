package com.htsoft.oa.dao.archive;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.archive.ArchivesHandle;

public abstract interface ArchivesHandleDao extends BaseDao<ArchivesHandle> {
	public abstract ArchivesHandle findByUAIds(Long paramLong1, Long paramLong2);

	public abstract List<ArchivesHandle> findByAid(Long paramLong);
}


 
 
 
 
 