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
 * 类名: RoleQueryForm
 * </p>
 * <p>
 * 描述: 角色查询窗口类
 * </p>
 */
public class RoleQueryForm extends BaseQueryForm {

    /**
     * 描述: 角色名称
     */
    private String roleName;

    /**
     * 构造函数
     */
    public RoleQueryForm() {

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
