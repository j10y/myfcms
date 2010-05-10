/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 12, 2010</p>
 * <p>更新：</p>
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
 * 描述：
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
		StringBuilder sb = new StringBuilder("删除");
		for (Supplier sup : suppliers) {
			sb.append(sup.getCompanyName());
			sb.append(",");
		}
		
		return "删除"+sb.toString();
	}

}
