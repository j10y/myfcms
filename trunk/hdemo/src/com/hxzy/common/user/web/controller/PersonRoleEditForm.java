/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.controller;

import java.util.Map;

import com.hxzy.base.web.controller.BaseEditForm;

/**
 * <p>
 * 类名: PersonRoleEditForm
 * </p>
 * <p>
 * 描述:
 * </p>
 */
public class PersonRoleEditForm extends BaseEditForm {

	/**
	 * 描述: 人员ID
	 */
	private String userId;

	/**
	 * 描述: 角色Map
	 */
	private Map role;

	/**
	 * 描述: 返回 userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 描述: 设置 userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 返回 role
	 */
	public Map getRole() {
		return role;
	}

	/**
	 * 设置 role
	 */
	public void setRole(Map role) {
		this.role = role;
	}
	
	


}