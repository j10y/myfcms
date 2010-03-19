package com.hxzy.common.user.model;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 类名: UserInfo
 * </p>
 * <p>
 * 描述: 用户信息类
 * </p>
 */
public class UserInfo implements Serializable {

	/**
	 * 描述: 用户
	 */
	private User user;

	/**
	 * 描述: 用户功能权限
	 */
	private Map userFunPriv;

	/**
	 * 描述: 角色
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
	 * 返回 userFunPriv
	 */
	public Map getUserFunPriv() {
		return userFunPriv;
	}

	/**
	 * 设置 userFunPriv
	 */
	public void setUserFunPriv(Map userFunPriv) {
		this.userFunPriv = userFunPriv;
	}

	/**
	 * 返回 roles
	 */
	public Set getRoles() {
		return roles;
	}

	/**
	 * 设置 roles
	 */
	public void setRoles(Set roles) {
		this.roles = roles;
	}
	
	

}