/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 9, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hxzy.base.service.impl.BaseServiceImpl;
import com.hxzy.common.log.dao.LogDao;
import com.hxzy.common.log.model.Log;
import com.hxzy.common.log.service.LogService;

/**
 * @author xiacc
 *
 * ������
 */
public class LogServiceImpl extends BaseServiceImpl<Log,LogDao> implements LogService {
	
	@Autowired
	private LogDao logDao;

	/* (non-Javadoc)
	 * @see base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected LogDao getDao() {
		return getLogDao();
	}

	/**
	 * ���� logDao
	 */
	public LogDao getLogDao() {
		return logDao;
	}

	/**
	 * ���� logDao
	 */
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}


	
}
