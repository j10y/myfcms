/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 13, 2010</p>
 * <p>���£�</p>
 */
package com.bdzb.oa.expert.web.aciton;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Grid;

import com.bdzb.oa.expert.model.Expert;
import com.hxzy.base.web.window.ActionWindow;

/**
 * @author xiacc
 *
 * ������
 */
public class ExpertDetail extends ActionWindow {
	
	private Expert expert = (Expert) Executions.getCurrent().getArg().get("expert");

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
	 * ���� expert
	 */
	public Expert getExpert() {
		return expert;
	}

	/**
	 * ���� expert
	 */
	public void setExpert(Expert expert) {
		this.expert = expert;
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
