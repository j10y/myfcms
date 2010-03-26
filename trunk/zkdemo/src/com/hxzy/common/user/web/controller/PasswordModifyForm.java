/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseEditForm;

/**
 * <p>
 * 类名: PasswordModifyForm
 * </p>
 * <p>
 * 描述: 口令修改窗口类
 * </p>
 */
public class PasswordModifyForm extends BaseEditForm {

	/**
	 * 描述: 原口令
	 */
	private String orignialPassword;

	/**
	 * 描述: 新口令
	 */
	private String newPassword;

	/**
	 * 描述: 新口令重复
	 */
	private String newPasswordRepeat;

	/**
	 * 描述: 返回 newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * 描述: 设置 newPassword
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * 描述: 返回 newPasswordRepeat
	 */
	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}

	/**
	 * 描述: 设置 newPasswordRepeat
	 */
	public void setNewPasswordRepeat(String newPasswordRepeat) {
		this.newPasswordRepeat = newPasswordRepeat;
	}

	/**
	 * 描述: 返回 orignialPassword
	 */
	public String getOrignialPassword() {
		return orignialPassword;
	}

	/**
	 * 描述: 设置 orignialPassword
	 */
	public void setOrignialPassword(String orignialPassword) {
		this.orignialPassword = orignialPassword;
	}
}