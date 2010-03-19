package com.hxzy.common.user.model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 类名: OnlineUserInfo
 * </p>
 * <p>
 * 描述: 在线用户信息
 * </p>
 */
public class OnlineUserInfo implements Serializable {

	/**
	 * 描述: 用户登录时间
	 */
	private Date loginDate;

	/**
	 * 描述: 用户信息
	 */
	private User user;

	/**
	 * 描述: 用户Session
	 */
	private Object userSession;

	/**
	 * 描述: 返回 loginDate
	 */
	public Date getLoginDate() {
		return loginDate;
	}

	/**
	 * 描述: 设置 loginDate
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
	 * 描述: 返回 userSession
	 */
	public Object getUserSession() {
		return userSession;
	}

	/**
	 * 描述: 设置 userSession
	 */
	public void setUserSession(Object userSession) {
		this.userSession = userSession;
	}
}