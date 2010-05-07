/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 13, 2010</p>
 * <p>���£�</p>
 */
package com.bdzb.oa.member.web.action;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Grid;

import com.bdzb.oa.member.model.Member;
import com.hxzy.base.web.window.ActionWindow;

/**
 * @author xiacc
 *
 * ������
 */
public class MemberDetail extends ActionWindow {
	
	private Member member = (Member) Executions.getCurrent().getArg().get("member");

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
	 * ���� member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * ���� member
	 */
	public void setMember(Member member) {
		this.member = member;
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
