/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 12, 2010</p>
 * <p>���£�</p>
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
 * ������
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
	 * ���� expertDao
	 */
	public ExpertDao getExpertDao() {
		return expertDao;
	}

	/**
	 * ���� expertDao
	 */
	public void setExpertDao(ExpertDao expertDao) {
		this.expertDao = expertDao;
	}

	
}
