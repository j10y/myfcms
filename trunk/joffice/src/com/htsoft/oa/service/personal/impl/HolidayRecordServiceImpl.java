package com.htsoft.oa.service.personal.impl;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.personal.HolidayRecordDao;
import com.htsoft.oa.model.personal.HolidayRecord;
import com.htsoft.oa.service.personal.HolidayRecordService;

public class HolidayRecordServiceImpl extends BaseServiceImpl<HolidayRecord> implements
		HolidayRecordService {
	private HolidayRecordDao dao;

	public HolidayRecordServiceImpl(HolidayRecordDao dao) {
		super(dao);
		this.dao = dao;
	}
}


 
 
 
 
 