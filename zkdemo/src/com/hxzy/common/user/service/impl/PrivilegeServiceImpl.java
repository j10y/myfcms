/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 9, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.hxzy.base.service.impl.BaseServiceImpl;
import com.hxzy.common.user.dao.PrivilegeDao;
import com.hxzy.common.user.dao.RoleDao;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.service.PrivilegeService;

/**
 * @author xiacc
 *
 * ������
 */
public class PrivilegeServiceImpl extends BaseServiceImpl<Privilege,PrivilegeDao> implements PrivilegeService {

	@Autowired
	private PrivilegeDao privilegeDao;	

	/* (non-Javadoc)
	 * @see base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected PrivilegeDao getDao() {
		return privilegeDao;
	}

	/**
	 * ���� privilegeDao
	 */
	public PrivilegeDao getPrivilegeDao() {
		return privilegeDao;
	}

	/**
	 * ���� privilegeDao
	 */
	public void setPrivilegeDao(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.service.impl.BaseServiceImpl#delete(java.lang.Object)
	 */
	@Override
	public void delete(Privilege persistentInstance) {
		persistentInstance = this.loadById(persistentInstance.getId());
		
		Set<Role> roles = persistentInstance.getRoles();
		
		for(Role r:roles){
			r.getPrivileges().remove(persistentInstance);
		}	
		
		privilegeDao.delete(persistentInstance);
	}
	
	
	
	

}
