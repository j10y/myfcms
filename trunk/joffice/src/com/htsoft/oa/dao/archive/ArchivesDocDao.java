package com.htsoft.oa.dao.archive;

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.archive.ArchivesDoc;

public abstract interface ArchivesDocDao extends BaseDao<ArchivesDoc> {
	public abstract List<ArchivesDoc> findByAid(Long paramLong);
}


 
 
 
 
 