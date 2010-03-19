/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 9, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.service.impl;

import com.hxzy.base.service.impl.BaseServiceImpl;
import com.hxzy.common.user.dao.RoleDao;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.service.RoleService;

/**
 * @author xiacc
 *
 * ������
 */
public class RoleServiceImpl extends BaseServiceImpl<Role,RoleDao> implements RoleService {

	private RoleDao roleDao;

	/* (non-Javadoc)
	 * @see base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected RoleDao getDao() {
		return roleDao;
	}

	/**
	 * ���� roleDao
	 */
	public RoleDao getRoleDao() {
		return roleDao;
	}

	/**
	 * ���� roleDao
	 */
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	
	
}
