/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.controller;

import com.hxzy.base.web.controller.BaseForm;

/**
 * <p>
 * ����: PersonEditForm
 * </p>
 * <p>
 * ����: ��Ա�༭������
 * </p>
 */
public class PersonEditForm extends BaseForm {
    
    /**
     * ����: �༭��־
     */
    private String editFlag;
    
    /**
     * ����: ��Աid
     */
    private String id;
    
    /**
     * ����: ��Ա����
     */
    private String code;
    
    /**
     * ����: ��Ա����
     */
    private String name;
    
    /**
     * ����: ����
     */
    private String password;
    
    /**
     * ����: �ظ�����
     */
    private String passwordRepeat;
    
    /**
     * ����: ��Ա����
     */
    private String userFlag;
    
    /**
     * ����: ��վ��ID 
     */
    private String childSiteId;
    
    private String orgId;

    /**
     * ����: ���� childSiteId
     */
    public String getChildSiteId() {
        return childSiteId;
    }

    /**
     * ����: ���� childSiteId 
     */
    public void setChildSiteId(String childSiteId) {
        this.childSiteId = childSiteId;
    }

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
     * ����: ���� editFlag
     */
    public String getEditFlag() {
        return editFlag;
    }

    /**
     * ����: ���� editFlag 
     */
    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    /**
     * ����: ���� id
     */
    public String getId() {
        return id;
    }

    /**
     * ����: ���� id 
     */
    public void setId(String id) {
        this.id = id;
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

    /**
     * ����: ���� passwordRepeat
     */
    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    /**
     * ����: ���� passwordRepeat 
     */
    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    /**
     * ����: ���� userFlag
     */
    public String getUserFlag() {
        return userFlag;
    }

    /**
     * ����: ���� userFlag 
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