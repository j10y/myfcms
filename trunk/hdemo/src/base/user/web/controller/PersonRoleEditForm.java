/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */
package base.user.web.controller;

import java.util.Map;
import java.util.Set;

import base.web.controller.BaseEditForm;

/**
 * <p>
 * ����: PersonRoleEditForm
 * </p>
 * <p>
 * ����:
 * </p>
 */
public class PersonRoleEditForm extends BaseEditForm {

	/**
	 * ����: ��ԱID
	 */
	private String userId;

	/**
	 * ����: ��ɫMap
	 */
	private Map role;

	/**
	 * ����: ���� userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ����: ���� userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ���� role
	 */
	public Map getRole() {
		return role;
	}

	/**
	 * ���� role
	 */
	public void setRole(Map role) {
		this.role = role;
	}
	
	


}