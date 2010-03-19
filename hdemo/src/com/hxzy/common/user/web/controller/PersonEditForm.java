/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseForm;

/**
 * <p>
 * 类名: PersonEditForm
 * </p>
 * <p>
 * 描述: 人员编辑窗口类
 * </p>
 */
public class PersonEditForm extends BaseForm {
    
    /**
     * 描述: 编辑标志
     */
    private String editFlag;
    
    /**
     * 描述: 人员id
     */
    private String id;
    
    /**
     * 描述: 人员代码
     */
    private String code;
    
    /**
     * 描述: 人员名称
     */
    private String name;
    
    /**
     * 描述: 口令
     */
    private String password;
    
    /**
     * 描述: 重复口令
     */
    private String passwordRepeat;
    
    /**
     * 描述: 人员类型
     */
    private String userFlag;
    
    /**
     * 描述: 子站点ID 
     */
    private String childSiteId;
    
    private String orgId;

    /**
     * 描述: 返回 childSiteId
     */
    public String getChildSiteId() {
        return childSiteId;
    }

    /**
     * 描述: 设置 childSiteId 
     */
    public void setChildSiteId(String childSiteId) {
        this.childSiteId = childSiteId;
    }

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
     * 描述: 返回 editFlag
     */
    public String getEditFlag() {
        return editFlag;
    }

    /**
     * 描述: 设置 editFlag 
     */
    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    /**
     * 描述: 返回 id
     */
    public String getId() {
        return id;
    }

    /**
     * 描述: 设置 id 
     */
    public void setId(String id) {
        this.id = id;
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
     * 描述: 返回 passwordRepeat
     */
    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    /**
     * 描述: 设置 passwordRepeat 
     */
    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	} 
    
    
    
}