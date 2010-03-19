/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-4</p>
 * <p>更新：</p>
 */
package com.hxzy.base.web.controller.login;

/**
 * <p>
 * 类名: LoginForm
 * </p>
 * <p>
 * 描述: 用户登陆Form
 * </p>
 */
public class LoginForm {

    /**
     * 描述: 用户名
     */
    private String code;

    /**
     * 描述: 口令
     */
    private String password;

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
}
