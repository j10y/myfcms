/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseQueryForm;


/**
 * <p>
 * ����: RoleQueryForm
 * </p>
 * <p>
 * ����: ��ɫ��ѯ������
 * </p>
 */
public class RoleQueryForm extends BaseQueryForm {

    /**
     * ����: ��ɫ����
     */
    private String roleName;

    /**
     * ���캯��
     */
    public RoleQueryForm() {

    }

    /**
     * ����: ���� roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * ����: ���� roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
