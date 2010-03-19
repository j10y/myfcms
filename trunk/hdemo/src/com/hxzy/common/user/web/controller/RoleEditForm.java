/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseEditForm;

/**
 * <p>
 * 类名: RoleEditForm
 * </p>
 * <p>
 * 描述: 角色编辑窗口类
 * </p>
 */
public class RoleEditForm extends BaseEditForm {

    /**
     * 描述: 角色ID
     */
    private String roleId;

    /**
     * 描述: 角色名称
     */
    private String roleName;


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
     * 描述: 返回 roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 描述: 设置 roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
