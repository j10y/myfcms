package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseForm;

public class RoleUserForm extends BaseForm {
	/**
	 * 描述: 编辑标志
	 */
	private String editFlag;

	/**
	 * 角色ID
	 */
	private Long roleId;

	/**
	 * 用户List
	 */
	private String[] userId;

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String[] getUserId() {
		return userId;
	}

	public void setUserId(String[] userId) {
		this.userId = userId;
	}

}
