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

import com.hxzy.base.web.controller.BaseQueryForm;

/**
 * <p>
 * ����: RolePrivilegeEditForm
 * </p>
 * <p>
 * ����: ��ɫ��ĿȨ�ޱ༭����
 * </p>
 */
public class TreePrivilegeEditForm extends BaseQueryForm {
  
  	/**
  	 * ����: ��ɫ���
  	 */
  	private String roleId;
  	
  	/**
  	 * ����: ��ɫ����
  	 */
  	private String name;  	
  	
  	/**
  	 * ����: Ȩ�ޱ��
  	 */
  	private Map privilege;  	
  	
  	public TreePrivilegeEditForm(){}  	

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
