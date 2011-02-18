package com.htsoft.oa.service.archive;

import java.util.Date;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchHasten;

public abstract interface ArchHastenService extends BaseService<ArchHasten> {
	public abstract Date getLeastRecordByUser(Long paramLong);
}


 
 
 
 
 