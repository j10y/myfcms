/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 12, 2010</p>
 * <p>更新：</p>
 */
package com.bdzb.oa.supplier.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.bdzb.oa.supplier.dao.SupplierDao;
import com.bdzb.oa.supplier.model.Supplier;
import com.bdzb.oa.supplier.service.SupplierService;
import com.hxzy.base.service.impl.BaseServiceImpl;

/**
 * @author xiacc
 *
 * 描述：
 */
public class SupplierServiceImpl extends BaseServiceImpl<Supplier,SupplierDao> implements SupplierService {
	
	@Autowired
	private SupplierDao supplierDao;

	/* (non-Javadoc)
	 * @see com.hxzy.base.service.impl.BaseServiceImpl#getDao()
	 */
	@Override
	protected SupplierDao getDao() {
		return supplierDao;
	}
	
	

	
}
