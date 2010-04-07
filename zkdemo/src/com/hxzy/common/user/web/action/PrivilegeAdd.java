/**
 * <p>��Ŀ���ƣ�</p>
 * <p>��Ȩ���� (c) </p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�Apr 6, 2010</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.TreeWindow;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.service.PrivilegeService;

/**
 * @author xiacc
 * 
 * ������
 */
public class PrivilegeAdd extends ActionWindow {

	@Autowired
	private PrivilegeService privilegeService;

	private Combobox combobox;

	private List<Privilege> privileges = (List<Privilege>) Executions.getCurrent().getArg().get(
			"privileges");

	private Textbox privName;

	private Textbox privCode;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		Privilege parent = null;
		if (combobox.getSelectedItem() != null) {
			parent = (Privilege) combobox.getSelectedItem().getValue();
		}

		Privilege privilege = new Privilege();
		privilege.setPrivName(privName.getValue());
		privilege.setPrivCode(privCode.getValue());
		privilege.setParent(parent);

		privilegeService.save(privilege);

		((TreeWindow) this.getParent()).init();
		this.onClose();

	}

	/**
	 * ���� privilegeService
	 */
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	/**
	 * ���� privilegeService
	 */
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	/**
	 * ���� combobox
	 */
	public Combobox getCombobox() {
		return combobox;
	}

	/**
	 * ���� combobox
	 */
	public void setCombobox(Combobox combobox) {
		this.combobox = combobox;
	}

	/**
	 * ���� privileges
	 */
	public List<Privilege> getPrivileges() {
		return privileges;
	}

	/**
	 * ���� privileges
	 */
	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	/**
	 * ���� privName
	 */
	public Textbox getPrivName() {
		return privName;
	}

	/**
	 * ���� privName
	 */
	public void setPrivName(Textbox privName) {
		this.privName = privName;
	}

	/**
	 * ���� privCode
	 */
	public Textbox getPrivCode() {
		return privCode;
	}

	/**
	 * ���� privCode
	 */
	public void setPrivCode(Textbox privCode) {
		this.privCode = privCode;
	}

}
