/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 5, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.service.impl;

import com.hxzy.base.service.impl.BaseServiceImpl;
import com.hxzy.common.user.dao.UserDao;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 *
 * ������
 */
public class UserServiceImpl extends BaseServiceImpl<User,UserDao> implements UserService {

	private UserDao userDao;


	

	/**
	 * ���� userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}




	/**
	 * ���� userDao
	 */
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}




	/* (non-Javadoc)
	 * @see base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected UserDao getDao() {
		return userDao;
	}

}
