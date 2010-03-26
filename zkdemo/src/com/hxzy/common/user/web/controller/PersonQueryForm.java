/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseQueryForm;

/**
 * <p>
 * 类名: PersonQueryForm
 * </p>
 * <p>
 * 描述: 人员查询窗口类
 * </p>
 */
public class PersonQueryForm extends BaseQueryForm {

	/**
	 * 描述: 人员代码
	 */
	private String code;

	/**
	 * 描述: 人员名称
	 */
	private String name;

	/**
	 * 描述: 人员口令
	 */
	private String password;

	/**
	 * 描述: 近期未上网
	 */
	private String loginLastTime;

	/**
	 * 描述: 用户类别
	 */
	private String userFlag;

	/**
	 * 描述: 用户所属处室
	 */
	private String childsiteId;
	
	/**
	 * 描述: 用户所属警种
	 */
	private String category;

	/**
	 * 描述: 返回 code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 描述: 设置 code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 描述: 返回 loginLastTime
	 */
	public String getLoginLastTime() {
		return loginLastTime;
	}

	/**
	 * 描述: 设置 loginLastTime
	 */
	public void setLoginLastTime(String loginLastTime) {
		this.loginLastTime = loginLastTime;
	}

	/**
	 * 描述: 返回 name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 描述: 设置 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 描述: 返回 password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 描述: 设置 password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 描述: 返回 childsiteId
	 */
	public String getChildsiteId() {
		return childsiteId;
	}

	/**
	 * 描述: 设置 childsiteId
	 */
	public void setChildsiteId(String childsiteId) {
		this.childsiteId = childsiteId;
	}

	/**
	 * 描述: 返回 userFlag
	 */
	public String getUserFlag() {
		return userFlag;
	}

	/**
	 * 描述: 设置 userFlag
	 */
	public void setUserFlag(String userFlag) {
		this.userFlag = userFlag;
	}

	/**
	 * 描述: 返回 category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 描述: 设置 category 
	 */
	public void setCategory(String category) {
		this.category = category;
	}

}