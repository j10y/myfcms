/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 5, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.service.impl;

import com.hxzy.base.service.impl.BaseServiceImpl;
import com.hxzy.common.user.dao.UserDao;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * @author xiacc
 *
 * 描述：
 */
public class UserServiceImpl extends BaseServiceImpl<User,UserDao> implements UserService {

	private UserDao userDao;


	

	/**
	 * 返回 userDao
	 */
	public UserDao getUserDao() {
		return userDao;
	}




	/**
	 * 设置 userDao
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
