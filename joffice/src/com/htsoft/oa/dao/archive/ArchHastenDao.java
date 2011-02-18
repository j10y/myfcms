package com.htsoft.oa.dao.archive;

import java.util.Date;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.archive.ArchHasten;

public abstract interface ArchHastenDao extends BaseDao<ArchHasten> {
	public abstract Date getLeastRecordByUser(Long paramLong);
}


 
 
 
 
 