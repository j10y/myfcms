/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */
package base.user.web.controller;

import base.web.controller.BaseQueryForm;

/**
 * <p>
 * ����: PersonQueryForm
 * </p>
 * <p>
 * ����: ��Ա��ѯ������
 * </p>
 */
public class PersonQueryForm extends BaseQueryForm {

	/**
	 * ����: ��Ա����
	 */
	private String code;

	/**
	 * ����: ��Ա����
	 */
	private String name;

	/**
	 * ����: ��Ա����
	 */
	private String password;

	/**
	 * ����: ����δ����
	 */
	private String loginLastTime;

	/**
	 * ����: �û����
	 */
	private String userFlag;

	/**
	 * ����: �û���������
	 */
	private String childsiteId;
	
	/**
	 * ����: �û���������
	 */
	private String category;

	/**
	 * ����: ���� code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * ����: ���� code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * ����: ���� loginLastTime
	 */
	public String getLoginLastTime() {
		return loginLastTime;
	}

	/**
	 * ����: ���� loginLastTime
	 */
	public void setLoginLastTime(String loginLastTime) {
		this.loginLastTime = loginLastTime;
	}

	/**
	 * ����: ���� name
	 */
	public String getName() {
		return name;
	}

	/**
	 * ����: ���� name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ����: ���� password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * ����: ���� password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ����: ���� childsiteId
	 */
	public String getChildsiteId() {
		return childsiteId;
	}

	/**
	 * ����: ���� childsiteId
	 */
	public void setChildsiteId(String childsiteId) {
		this.childsiteId = childsiteId;
	}

	/**
	 * ����: ���� userFlag
	 */
	public String getUserFlag() {
		return userFlag;
	}

	/**
	 * ����: ���� userFlag
	 */
	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

	/**
	 * ����: ���� category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * ����: ���� category 
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}