/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 9, 2010</p>
 * <p>���£�</p>
 */
package base.log.service.impl;

import base.log.dao.LogDao;
import base.log.model.Log;
import base.log.service.LogService;
import base.service.impl.BaseServiceImpl;

/**
 * @author xiacc
 *
 * ������
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
