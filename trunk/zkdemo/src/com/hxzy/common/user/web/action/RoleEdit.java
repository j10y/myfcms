/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 2, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Constraint;
import org.zkoss.zul.Textbox;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.service.RoleService;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class RoleEdit extends ActionWindow {

	@Autowired
	private RoleService roleService;

	private Role role = (Role) Executions.getCurrent().getArg().get("role");

	private Textbox roleName;

	private Textbox remarks;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		if (role != null) {
			roleName.setValue(role.getRoleName());
			remarks.setValue(role.getRemarks());
		}

		// 用户名重复判断
		roleName.setConstraint(new Constraint() {
			public void validate(Component comp, Object value) throws WrongValueException {
				List list = roleService.find("from Role r where r.roleName=? and r.id<>?",
						new Object[] { value, role.getId() });

				if (list != null && list.size() != 0) {
					throw new WrongValueException(roleName, "该角色名已经存在!");
				}
			}
		});

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		role.setRemarks(remarks.getValue());
		role.setRoleName(roleName.getValue());

		roleService.update(role);

		((ListWindow) this.getParent()).onFind();
	}

}
