/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-4</p>
 * <p>���£�</p>
 */
package com.hxzy.base.web.controller.login;

/**
 * <p>
 * ����: LoginForm
 * </p>
 * <p>
 * ����: �û���½Form
 * </p>
 */
public class LoginForm {

    /**
     * ����: �û���
     */
    private String code;

    /**
     * ����: ����
     */
    private String password;

    /**
     * ����: ���� code
     */
    public String getCode() {
        return code;
    }

    /**
     * ����: ���� code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * ����: ���� password
     */
    public String getPassword() {
        return password;
    }

    /**
     * ����: ���� password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
