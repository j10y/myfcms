/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseEditForm;

/**
 * <p>
 * ����: PasswordModifyForm
 * </p>
 * <p>
 * ����: �����޸Ĵ�����
 * </p>
 */
public class PasswordModifyForm extends BaseEditForm {

	/**
	 * ����: ԭ����
	 */
	private String orignialPassword;

	/**
	 * ����: �¿���
	 */
	private String newPassword;

	/**
	 * ����: �¿����ظ�
	 */
	private String newPasswordRepeat;

	/**
	 * ����: ���� newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * ����: ���� newPassword
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	/**
	 * ����: ���� newPasswordRepeat
	 */
	public String getNewPasswordRepeat() {
		return newPasswordRepeat;
	}

	/**
	 * ����: ���� newPasswordRepeat
	 */
	public void setNewPasswordRepeat(String newPasswordRepeat) {
		this.newPasswordRepeat = newPasswordRepeat;
	}

	/**
	 * ����: ���� orignialPassword
	 */
	public String getOrignialPassword() {
		return orignialPassword;
	}

	/**
	 * ����: ���� orignialPassword
	 */
	public void setOrignialPassword(String orignialPassword) {
		this.orignialPassword = orignialPassword;
	}
}