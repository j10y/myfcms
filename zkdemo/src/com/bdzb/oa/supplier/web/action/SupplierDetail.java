/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 13, 2010</p>
 * <p>更新：</p>
 */
package com.bdzb.oa.supplier.web.action;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Grid;

import com.bdzb.oa.supplier.model.Supplier;
import com.hxzy.base.web.window.ActionWindow;

/**
 * @author xiacc
 *
 * 描述：
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
	 * 返回 supplier
	 */
	public Supplier getSupplier() {
		return supplier;
	}

	/**
	 * 设置 supplier
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * 返回 grid
	 */
	public Grid getGrid() {
		return grid;
	}

	/**
	 * 设置 grid
	 */
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	
	

}
