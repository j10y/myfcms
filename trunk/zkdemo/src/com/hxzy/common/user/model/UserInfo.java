package com.hxzy.common.user.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * ����: UserInfo
 * </p>
 * <p>
 * ����: �û���Ϣ��
 * </p>
 */
public class UserInfo implements Serializable {

	/**
	 * ����: �û�
	 */
	private User user;

	/**
	 * ����: �û�����Ȩ��
	 */
	private Map userFunPriv;

	/**
	 * ����: ��ɫ
	 */
	private Set roles;

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * ���� userFunPriv
	 */
	public Map getUserFunPriv() {
		return userFunPriv;
	}

	/**
	 * ���� userFunPriv
	 */
	public void setUserFunPriv(Map userFunPriv) {
		this.userFunPriv = userFunPriv;
	}

	/**
	 * ���� roles
	 */
	public Set getRoles() {
		return roles;
	}

	/**
	 * ���� roles
	 */
	public void setRoles(Set roles) {
		this.roles = roles;
	}
	
	

}