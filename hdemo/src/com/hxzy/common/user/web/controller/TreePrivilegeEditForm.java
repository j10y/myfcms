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

import com.hxzy.base.web.controller.BaseQueryForm;

/**
 * <p>
 * 类名: RolePrivilegeEditForm
 * </p>
 * <p>
 * 描述: 角色栏目权限编辑窗口
 * </p>
 */
public class TreePrivilegeEditForm extends BaseQueryForm {
  
  	/**
  	 * 描述: 角色编号
  	 */
  	private String roleId;
  	
  	/**
  	 * 描述: 角色名称
  	 */
  	private String name;  	
  	
  	/**
  	 * 描述: 权限编号
  	 */
  	private Map privilege;  	
  	
  	public TreePrivilegeEditForm(){}  	

    /**
     * 描述: 返回 roleId
     */
    public String getRoleId() {
      return roleId;
    }
    /**
     * 描述: 设置 roleId 
     */
    public void setRoleId(String roleId) {
      this.roleId = roleId;
    }    
    /**
     * 描述: 返回 privilege
     */
    public Map getPrivilege() {
        return privilege;
    }
    /**
     * 描述: 设置 privilege 
     */
    public void setPrivilege(Map privilege) {
        this.privilege = privilege;
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
}
