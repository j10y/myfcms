/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseEditForm;

/**
 * <p>
 * ����: RoleEditForm
 * </p>
 * <p>
 * ����: ��ɫ�༭������
 * </p>
 */
public class RoleEditForm extends BaseEditForm {

    /**
     * ����: ��ɫID
     */
    private String roleId;

    /**
     * ����: ��ɫ����
     */
    private String roleName;


    /**
     * ����: ���� roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * ����: ���� roleId
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
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
