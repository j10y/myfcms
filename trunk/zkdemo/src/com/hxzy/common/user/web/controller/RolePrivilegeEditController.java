/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.exception.ApplicationException;
import com.hxzy.base.util.StringUtil;
import com.hxzy.base.web.controller.BaseFormController;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.service.PrivilegeService;
import com.hxzy.common.user.service.RoleService;

/**
 * <p>
 * ����: RolePrivilegeEditController
 * </p>
 * <p>
 * ����: ��ɫ����Ȩ������Controller
 * </p>
 */
public class RolePrivilegeEditController extends BaseFormController {

	/**
	 * ����: Ȩ��Manager
	 */
	private PrivilegeService privilegeService;

	/**
	 * ����: ��ɫManager
	 */
	private RoleService roleService;

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#referenceData(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(HttpServletRequest request, Object o,
			Errors errors) throws Exception {
		Map map = new HashMap();
		map.put("privilegeList", privilegeService.loadAll());
		return map;
	}

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		// ����һ���յ�Command����
		RolePrivilegeEditForm form = new RolePrivilegeEditForm();
		// ��ȡ��ɫ����
		Long roleId = StringUtil.stringToLong(RequestUtils.getStringParameter(
				request, "roleId", "0"));
		// �ж�Ҫ��Ȩ�Ľ�ɫ�Ƿ���ڣ��粻������ת����ʾҳ��
		Role role = roleService.findById(roleId);
		if (role == null) {
			throw new ApplicationException("exception.msg.dataDoesNotExist");
		}
		// �γɽ�ɫ����
		request.setAttribute("role", role);

		Map map = new HashMap();
		map.put("roleId", roleId);
		
		Set<Privilege> privileges = role.getPrivileges();
		
		Map userPrivileges = new HashMap(); 
		for(Privilege p:privileges){
			userPrivileges.put(p.getId(), p);
		}
		
		form.setRoleId(roleId.toString());
		form.setPrivilege(userPrivileges);
		return form;
	}

	/*
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.BindException)
	 */
	protected void onBind(HttpServletRequest request, Object o,
			BindException errors) throws Exception {
		// ��Ȩ�޽��а�
		RolePrivilegeEditForm form = (RolePrivilegeEditForm) o;
		// ��ȡȨ��
		String[] privileges = RequestUtils.getStringParameters(request,"privileges");
		HashMap map = new HashMap();
		String privilegeId = null;
		for (int i = 0; i < privileges.length; i++) {
			privilegeId = privileges[i];
			if (privilegeId != null)
				map.put(privilegeId, privilegeService.findById(new Long(privilegeId)));
		}
		form.setPrivilege(map);
	}

	/*
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		RolePrivilegeEditForm form = (RolePrivilegeEditForm) o;
		// �ж�Ҫ��Ȩ�Ľ�ɫ�Ƿ���ڣ��粻������ת����ʾҳ��
		Role role = (Role) request.getAttribute("role");
		// �����������
		ArrayList privileges = new ArrayList();
		Iterator it = form.getPrivilege().values().iterator();
		Privilege privilege = null;
		while (it.hasNext()) {
			privilege = (Privilege) it.next();
			privileges.add(privilege);
		}
		// ��������
		role.getPrivileges().clear();
		role.getPrivileges().addAll(form.getPrivilege().values());
		
		roleService.update(role);

		// д������־
//		Log log = new Log();
//		log.setUser(this.getCurrentUserInfo(request).getUser());
//		log.setLogObject("��ɫ����");
//		log.setLogAction("��ɫ����Ȩ������");
//		log.setDetail("�����蹦��Ȩ�޵Ľ�ɫ:" + role.getRoleName());
//		logService.save(log);

		return new ModelAndView("redirect:" + form.getReturnUrl());
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
	 * ���� roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * ���� roleService
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	


}