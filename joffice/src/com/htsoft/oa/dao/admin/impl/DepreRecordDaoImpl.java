package com.htsoft.oa.dao.admin.impl;

import java.util.Date;

import org.hibernate.Query;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.admin.DepreRecordDao;
import com.htsoft.oa.model.admin.DepreRecord;

public class DepreRecordDaoImpl extends BaseDaoImpl<DepreRecord> implements DepreRecordDao {
	public DepreRecordDaoImpl() {
		super(DepreRecord.class);
	}

	public Date findMaxDate(Long assetsId) {
		String hql = "select max(vo.calTime) from DepreRecord vo where vo.fixedAssets.assetsId=?";
		Query query = getSession().createQuery(hql);
		query.setLong(0, assetsId.longValue());
		Date date = (Date) query.list().get(0);
		return date;
	}
}


 
 
 
 
 