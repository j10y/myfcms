/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 13, 2010</p>
 * <p>���£�</p>
 */
package com.bdzb.oa.supplier.web.action;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Grid;

import com.bdzb.oa.supplier.model.Supplier;
import com.hxzy.base.web.window.ActionWindow;

/**
 * @author xiacc
 *
 * ������
 */
public class SupplierDetail extends ActionWindow {
	
	private Supplier supplier = (Supplier) Executions.getCurrent().getArg().get("supplier");

	private Grid grid;
	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		binder.loadComponent(grid);
	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		// TODO Auto-generated method stub

	}

	/**
	 * ���� supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * ���� supplier
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * ���� grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * ���� grid
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	

}
