/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 12, 2010</p>
 * <p>���£�</p>
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
 * ������
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
