/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.controller;

import java.util.Map;

import com.hxzy.base.web.controller.BaseEditForm;

/**
 * <p>
 * ����: RolePrivilegeEditForm
 * </p>
 * <p>
 * ����: ��ɫȨ�ޱ༭������
 * </p>
 */
public class RolePrivilegeEditForm extends BaseEditForm {

    /**
     * ����: ��ɫID
     */
    private String roleId;

  	/**
  	 * ����: ��ɫ����
  	 */
  	private String name; 
    
    /**
     * ����: Ȩ��Map
     */
    private Map privilege;

    /**
     * ����: ���� privilege
     */
    public Map getPrivilege() {
        return privilege;
    }

    /**
     * ����: ���� privilege
     */
    public void setPrivilege(Map privilege) {
        this.privilege = privilege;
    }

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
     * ����: ���� name
     */
    public String getName() {
        return name;
    }
    /**
     * ����: ���� name 
     */
    public void setName(String name) {
        this.name = name;
    }
}
