/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 23, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.dict.service.impl;

import com.hxzy.base.service.impl.BaseServiceImpl;
import com.hxzy.common.dict.dao.DictDao;
import com.hxzy.common.dict.model.Dict;
import com.hxzy.common.dict.service.DictService;

/**
 * @author xiacc
 *
 * 描述：
 */
public class DictServiceImpl extends BaseServiceImpl<Dict,DictDao> implements DictService {

	private DictDao dictDao;

	/**
	 * 返回 dictDao
	 */
	public DictDao getDictDao() {
		return dictDao;
	}

	/**
	 * 设置 dictDao
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
