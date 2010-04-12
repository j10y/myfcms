/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 12, 2010</p>
 * <p>更新：</p>
 */
package com.bdzb.oa.expert.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bdzb.oa.expert.dao.ExpertDao;
import com.bdzb.oa.expert.model.Expert;
import com.bdzb.oa.expert.service.ExpertService;
import com.hxzy.base.dao.BaseDao;
import com.hxzy.base.service.impl.BaseServiceImpl;

/**
 * @author xiacc
 *
 * 描述：
 */
public class ExpertServiceImpl extends BaseServiceImpl<Expert,ExpertDao> implements ExpertService {
	
	@Autowired
	private ExpertDao expertDao;

	/* (non-Javadoc)
	 * @see com.hxzy.base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected ExpertDao getDao() {
		return expertDao;
	}

	/**
	 * 返回 expertDao
	 */
	public ExpertDao getExpertDao() {
		return expertDao;
	}

	/**
	 * 设置 expertDao
	 */
	public void setExpertDao(ExpertDao expertDao) {
		this.expertDao = expertDao;
	}

	
}
