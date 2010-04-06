/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Apr 6, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Textbox;

import com.hxzy.base.web.window.ActionWindow;
import com.hxzy.base.web.window.ListWindow;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.service.PrivilegeService;

/**
 * @author xiacc
 *
 * 描述：
 */
public class PrivilegeAdd extends ActionWindow{
	
	@Autowired
	private PrivilegeService privilegeService;
	
	private Combobox combobox;
	
	private List<Privilege> list;
	
	private Textbox privName;
	
	private Textbox privCode;

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#onBind()
	 */
	@Override
	public void onBind() {
		list = privilegeService.loadAll();
		binder.loadComponent(combobox);		
	}

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.window.ActionWindow#onSubmit()
	 */
	@Override
	public void onSubmit() {
		Privilege parent = null;
		if(combobox.getSelectedItem() != null){
			parent = (Privilege) combobox.getSelectedItem().getValue();
		}

		Privilege privilege = new Privilege();		
		privilege.setPrivName(privName.getValue());
		privilege.setPrivCode(privCode.getValue());
		privilege.setParent(parent);
		
		privilegeService.save(privilege);
		
		((ListWindow) this.getParent()).onFind();
		this.onClose();
		
	}

	/**
	 * 返回 privilegeService
	 */
	public PrivilegeService getPrivilegeService() {
		return privilegeService;
	}

	/**
	 * 设置 privilegeService
	 */
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	/**
	 * 返回 combobox
	 */
	public Combobox getCombobox() {
		return combobox;
	}

	/**
	 * 设置 combobox
	 */
	public void setCombobox(Combobox combobox) {
		this.combobox = combobox;
	}

	/**
	 * 返回 list
	 */
	public List<Privilege> getList() {
		return list;
	}

	/**
	 * 设置 list
	 */
	public void setList(List<Privilege> list) {
		this.list = list;
	}

	/**
	 * 返回 privName
	 */
	public Textbox getPrivName() {
		return privName;
	}

	/**
	 * 设置 privName
	 */
	public void setPrivName(Textbox privName) {
		this.privName = privName;
	}

	/**
	 * 返回 privCode
	 */
	public Textbox getPrivCode() {
		return privCode;
	}

	/**
	 * 设置 privCode
	 */
	public void setPrivCode(Textbox privCode) {
		this.privCode = privCode;
	}
	
	
	

}
