/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 13, 2010</p>
 * <p>更新：</p>
 */
package com.bdzb.oa.member.web.action;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Grid;

import com.bdzb.oa.member.model.Member;
import com.hxzy.base.web.window.ActionWindow;

/**
 * @author xiacc
 *
 * 描述：
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
	 * 返回 member
	 */
	public Member getMember() {
		return member;
	}

	/**
	 * 设置 member
	 */
	public void setMember(Member member) {
		this.member = member;
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
