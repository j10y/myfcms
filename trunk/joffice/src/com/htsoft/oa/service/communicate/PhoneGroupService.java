package com.htsoft.oa.service.communicate;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.communicate.PhoneGroup;

public abstract interface PhoneGroupService extends BaseService<PhoneGroup> {
	public abstract Integer findLastSn(Long paramLong);

	public abstract PhoneGroup findBySn(Integer paramInteger, Long paramLong);

	public abstract List<PhoneGroup> findBySnUp(Integer paramInteger, Long paramLong);

	public abstract List<PhoneGroup> findBySnDown(Integer paramInteger, Long paramLong);

	public abstract List<PhoneGroup> getAll(Long paramLong);
}


 
 
 
 
 