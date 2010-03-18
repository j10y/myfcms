/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 5, 2010</p>
 * <p>更新：</p>
 */
package base.user.service.impl;

import base.service.impl.BaseServiceImpl;
import base.user.dao.BaseUserDao;
import base.user.model.BaseUser;
import base.user.service.BaseUserService;

/**
 * @author xiacc
 *
 * 描述：
 */
public class BaseUserServiceImpl extends BaseServiceImpl<BaseUser,BaseUserDao> implements BaseUserService {

	private BaseUserDao baseUserDao;

	/**
	 * 返回 baseUserDao
	 */
	public BaseUserDao getBaseUserDao() {
		return baseUserDao;
	}

	/**
	 * 设置 baseUserDao
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
