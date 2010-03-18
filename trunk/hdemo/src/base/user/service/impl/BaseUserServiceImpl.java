/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 5, 2010</p>
 * <p>���£�</p>
 */
package base.user.service.impl;

import base.service.impl.BaseServiceImpl;
import base.user.dao.BaseUserDao;
import base.user.model.BaseUser;
import base.user.service.BaseUserService;

/**
 * @author xiacc
 *
 * ������
 */
public class BaseUserServiceImpl extends BaseServiceImpl<BaseUser,BaseUserDao> implements BaseUserService {

	private BaseUserDao baseUserDao;

	/**
	 * ���� baseUserDao
	 */
	public BaseUserDao getBaseUserDao() {
		return baseUserDao;
	}

	/**
	 * ���� baseUserDao
	 */
	public void setBaseUserDao(BaseUserDao baseUserDao) {
		this.baseUserDao = baseUserDao;
	}

	/* (non-Javadoc)
	 * @see base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected BaseUserDao getDao() {
		return baseUserDao;
	}

}
