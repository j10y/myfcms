/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 1, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.action;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.service.RoleService;

/**
 * @author xiacc
 * 
 * 描述：用户删除
 */
public class RoleDelete extends ActionWindow {

	@Autowired
	private RoleService roleService;

	private Set<Role> roles = (Set<Role>) Executions.getCurrent().getArg().get("roles");

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onBind() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		for (Role role : roles) {
			roleService.delete(role);
		}

		((ListWindow) this.getParent()).onFind();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("删除");

		for (Role role : roles) {
			sb.append(role.getRoleName());
			sb.append(",");
		}

		return sb.toString();
	}

}
