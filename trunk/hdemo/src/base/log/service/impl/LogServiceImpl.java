/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 9, 2010</p>
 * <p>更新：</p>
 */
package base.log.service.impl;

import base.log.dao.LogDao;
import base.log.model.Log;
import base.log.service.LogService;
import base.service.impl.BaseServiceImpl;

/**
 * @author xiacc
 *
 * 描述：
 */
public class LogServiceImpl extends BaseServiceImpl<Log,LogDao> implements LogService {
	
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
