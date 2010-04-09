/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 9, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.hxzy.base.service.impl.BaseServiceImpl;
import com.hxzy.common.user.dao.RoleDao;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.RoleService;

/**
 * @author xiacc
 *
 * 描述：
 */
public class RoleServiceImpl extends BaseServiceImpl<Role,RoleDao> implements RoleService {

	@Autowired
	private RoleDao roleDao;

	/* (non-Javadoc)
	 * @see base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected RoleDao getDao() {
		return roleDao;
	}

	/**
	 * 返回 roleDao
	 */
	public RoleDao getRoleDao() {
		return roleDao;
	}

	/**
	 * 设置 roleDao
	 */
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.service.impl.BaseServiceImpl#delete(java.lang.Object)
	 */
	@Override
	public void delete(Role persistentInstance) {
		persistentInstance = this.loadById(persistentInstance.getId());
		
		Set<User> users = persistentInstance.getUsers();
		
		for(User u:users){
			u.getRoles().remove(persistentInstance);
		}
		
		roleDao.delete(persistentInstance);
	}
	
	
	
}
