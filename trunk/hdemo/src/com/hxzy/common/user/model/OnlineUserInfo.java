package com.hxzy.common.user.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * ����: OnlineUserInfo
 * </p>
 * <p>
 * ����: �����û���Ϣ
 * </p>
 */
public class OnlineUserInfo implements Serializable {

	/**
	 * ����: �û���¼ʱ��
	 */
	private Date loginDate;

	/**
	 * ����: �û���Ϣ
	 */
	private User user;

	/**
	 * ����: �û�Session
	 */
	private Object userSession;

	/**
	 * ����: ���� loginDate
	 */
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * ����: ���� loginDate
	 */
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * ����: ���� userSession
	 */
	public Object getUserSession() {
		return userSession;
	}

	/**
	 * ����: ���� userSession
	 */
	public void setUserSession(Object userSession) {
		this.userSession = userSession;
	}
}