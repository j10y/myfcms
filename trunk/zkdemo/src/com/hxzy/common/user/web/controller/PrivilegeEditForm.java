/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Mar 19, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseEditForm;

/**
 * @author xiacc
 *
 * ������
 */
public class PrivilegeEditForm extends BaseEditForm {

	/**
	 * �������ڵ�ID
	 */
	private String id;
	
	/**
	 * ����: �����ڵ�
	 */
	private String parentId;

	/**
	 * ����: �Ƿ�Ϊ�ֵܽڵ�
	 */
	private String isBrother;
	
	/**
	 * ������Ȩ�޵ı���
	 */
	private String privCode;

	/**
	 * ����: Ȩ������
	 */
	private String privName;

	/**
	 * ���� parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * ���� parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * ���� id
	 */
	public String getId() {
		return id;
	}

	/**
	 * ���� id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * ���� isBrother
	 */
	public String getIsBrother() {
		return isBrother;
	}

	/**
	 * ���� isBrother
	 */
	public void setIsBrother(String isBrother) {
		this.isBrother = isBrother;
	}

	/**
	 * ���� privCode
	 */
	public String getPrivCode() {
		return privCode;
	}

	/**
	 * ���� privCode
	 */
	public void setPrivCode(String privCode) {
		this.privCode = privCode;
	}

	/**
	 * ���� privName
	 */
	public String getPrivName() {
		return privName;
	}

	/**
	 * ���� privName
	 */
	public void setPrivName(String privName) {
		this.privName = privName;
	}

	
	
	
	
}
