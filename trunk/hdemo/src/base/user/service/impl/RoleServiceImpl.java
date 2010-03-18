/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 9, 2010</p>
 * <p>更新：</p>
 */
package base.user.service.impl;

import base.service.impl.BaseServiceImpl;
import base.user.dao.RoleDao;
import base.user.model.Role;
import base.user.service.RoleService;

/**
 * @author xiacc
 *
 * 描述：
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
	
	
	
}
