/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 9, 2010</p>
 * <p>更新：</p>
 */
package base.user.service.impl;

import base.service.impl.BaseServiceImpl;
import base.user.dao.PrivilegeDao;
import base.user.model.Privilege;
import base.user.service.PrivilegeService;

/**
 * @author xiacc
 *
 * 描述：
 */
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege,PrivilegeDao> implements PrivilegeService {

	
	private PrivilegeDao privilegeDao;

	/* (non-Javadoc)
	 * @see base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected PrivilegeDao getDao() {
		return privilegeDao;
	}

	/**
	 * 返回 privilegeDao
	 */
	public PrivilegeDao getPrivilegeDao() {
		return privilegeDao;
	}

	/**
	 * 设置 privilegeDao
	 */
	public void setPrivilegeDao(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}
	
	

}
