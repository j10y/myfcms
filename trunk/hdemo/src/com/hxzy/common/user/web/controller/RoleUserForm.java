package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseForm;

public class RoleUserForm extends BaseForm {
	/**
	 * ����: �༭��־
	 */
	private String editFlag;

	/**
	 * ��ɫID
	 */
	private Long roleId;

	/**
	 * �û�List
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
