/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 19, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseEditForm;

/**
 * @author xiacc
 *
 * 描述：
 */
public class PrivilegeEditForm extends BaseEditForm {

	/**
	 * 描述：节点ID
	 */
	private String id;
	
	/**
	 * 描述: 父级节点
	 */
	private String parentId;

	/**
	 * 描述: 是否为兄弟节点
	 */
	private String isBrother;
	
	/**
	 * 描述：权限的编码
	 */
	private String privCode;

	/**
	 * 描述: 权限描述
	 */
	private String privName;

	/**
	 * 返回 parentId
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置 parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * 返回 id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 返回 isBrother
	 */
	public String getIsBrother() {
		return isBrother;
	}

	/**
	 * 设置 isBrother
	 */
	public void setIsBrother(String isBrother) {
		this.isBrother = isBrother;
	}

	/**
	 * 返回 privCode
	 */
	public String getPrivCode() {
		return privCode;
	}

	/**
	 * 设置 privCode
	 */
	public void setPrivCode(String privCode) {
		this.privCode = privCode;
	}

	/**
	 * 返回 privName
	 */
	public String getPrivName() {
		return privName;
	}

	/**
	 * 设置 privName
	 */
	public void setPrivName(String privName) {
		this.privName = privName;
	}

	
	
	
	
}
