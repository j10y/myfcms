/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 12, 2010</p>
 * <p>���£�</p>
 */
package com.bdzb.oa.supplier.web.action;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;

import com.bdzb.oa.supplier.model.Supplier;
import com.bdzb.oa.supplier.service.SupplierService;
import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;

/**
 * @author xiacc
 * 
 * ������
 */
public class SupplierDelete extends ActionWindow {

	@Autowired
	private SupplierService supplierService;

	private Set<Supplier> suppliers = (Set<Supplier>) Executions.getCurrent().getArg().get("suppliers");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		for (Supplier sup : suppliers) {
			supplierService.delete(sup);
		}

		((ListWindow) this.getParent()).onFind();
	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("ɾ��");
		for (Supplier sup : suppliers) {
			sb.append(sup.getCompanyName());
			sb.append(",");
		}
		
		return "ɾ��"+sb.toString();
	}

}
