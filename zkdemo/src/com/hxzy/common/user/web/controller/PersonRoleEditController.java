/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
 */
package com.hxzy.common.user.web.controller;

import java.util.HashMap;
import java.util.HashSet;
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
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.RoleService;
import com.hxzy.common.user.service.UserService;

/**
 * <p>
 * ����: PersonRoleEditController
 * </p>
 * <p>
 * ����: ��Ա��ɫ�༭Controller
 * </p>
 */
public class PersonRoleEditController extends BaseFormController {

	/**
	 * ����: ��ԱManager
	 */
	private UserService userService;

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
		// �γɽ�ɫ����(��ȡ��ǰվ������н�ɫ�͹�����ɫ)
		Map map = new HashMap();
		map.put("roleList", roleService.loadAll());
		return map;
	}

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		// ����һ���յ�Command����
		PersonRoleEditForm form = new PersonRoleEditForm();
		// ��ȡ�û�ID
		Long userId = StringUtil.stringToLong(RequestUtils.getStringParameter(
				request, "userId", "0"));
		// �ж�Ҫ��Ȩ���û��Ƿ���ڣ��粻������ת����ʾҳ��
		User user = userService.findById(userId);
		if (user == null) {
			throw new ApplicationException("exception.msg.dataDoesNotExist");
		}
		// �γ���Ա����
		request.setAttribute("user", user);
		// ����form����
		form.setUserId(userId.toString());
		
		Map roleMap = new HashMap();
		for(Role role:user.getRoles()){
			roleMap.put(role.getId(), role);
		}
		form.setRole(roleMap);
		return form;
	}

	/*
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.BindException)
	 */
	protected void onBind(HttpServletRequest request, Object o,
			BindException errors) throws Exception {
		// �Խ�ɫ���а�
		PersonRoleEditForm form = (PersonRoleEditForm) o;
		// ��ȡ��ɫ
		String[] roles = RequestUtils.getStringParameters(request, "roles");
		Map roleMap = new HashMap();
		Long roleId = null;
		for (int i = 0; i < roles.length; i++) {
			roleId = StringUtil.stringToLong(roles[i]);
			if (roleId != null)
				roleMap.put(roleId, roleId);
		}
		form.setRole(roleMap);
	}

	/*
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		PersonRoleEditForm form = (PersonRoleEditForm) o;
		User user = (User) request.getAttribute("user");
		// �����������
		Set roles = new HashSet();
		Iterator it = form.getRole().keySet().iterator();
		Long roleId = null;
		while (it.hasNext()) {
			roleId = (Long) it.next();
			
			Role role = new Role();
			role.setId(roleId);
			
			roles.add(role);
		}
		
		user.setRoles(roles);
		
		// ��������
		userService.update(user);

		// д������־
//		Log log = new Log();
//		log.setUser(this.getCurrentUserInfo(request).getUser());
//		log.setLogObject("�û�����");
//		log.setLogAction("��Ա��ɫ����");
//		log.setDetail("�������ɫ��Ա����:" + user.getCode());
//		logService.save(log);
//
		return new ModelAndView("redirect:" + form.getReturnUrl());
	}


	
	
	/**
	 * ���� userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * ���� userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
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