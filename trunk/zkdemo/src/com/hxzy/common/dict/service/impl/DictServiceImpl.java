/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 23, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.dict.service.impl;

import com.hxzy.base.service.impl.BaseServiceImpl;
import com.hxzy.common.dict.dao.DictDao;
import com.hxzy.common.dict.model.Dict;
import com.hxzy.common.dict.service.DictService;

/**
 * @author xiacc
 *
 * ������
 */
public class DictServiceImpl extends BaseServiceImpl<Dict,DictDao> implements DictService {

	private DictDao dictDao;

	/**
	 * ���� dictDao
	 */
	public DictDao getDictDao() {
		return dictDao;
	}

	/**
	 * ���� dictDao
	 */
	public void setDictDao(DictDao dictDao) {
		this.dictDao = dictDao;
	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected DictDao getDao() {
		return dictDao;
	}
	
	

}
