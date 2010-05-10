/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 9, 2010</p>
 * <p>更新：</p>
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
 * 描述：
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
	 * 返回 logDao
	 */
	public LogDao getLogDao() {
		return logDao;
	}

	/**
	 * 设置 logDao
	 */
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}


	
}
