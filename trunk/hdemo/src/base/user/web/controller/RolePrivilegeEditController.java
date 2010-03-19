/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：</p>
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
 * 类名: RolePrivilegeEditController
 * </p>
 * <p>
 * 描述: 角色功能权限授予Controller
 * </p>
 */
public class RolePrivilegeEditController extends BaseFormController {

	/**
	 * 描述: 权限Manager
	 */
	private PrivilegeService privilegeService;

	/**
	 * 描述: 角色Manager
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
		// 创建一个空的Command对象
		RolePrivilegeEditForm form = new RolePrivilegeEditForm();
		// 获取角色代码
		Long roleId = StringUtil.stringToLong(RequestUtils.getStringParameter(
				request, "roleId", "0"));
		// 判断要授权的角色是否存在，如不存在则转入提示页面
		Role role = roleService.findById(roleId);
		if (role == null) {
			throw new ApplicationException("exception.msg.dataDoesNotExist");
		}
		// 形成角色数据
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
		// 对权限进行绑定
		RolePrivilegeEditForm form = (RolePrivilegeEditForm) o;
		// 获取权限
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
		// 判断要授权的角色是否存在，如不存在则转入提示页面
		Role role = (Role) request.getAttribute("role");
		// 设置相关数据
		ArrayList privileges = new ArrayList();
		Iterator it = form.getPrivilege().values().iterator();
		Privilege privilege = null;
		while (it.hasNext()) {
			privilege = (Privilege) it.next();
			privileges.add(privilege);
		}
		// 保存数据
		role.getPrivileges().clear();
		role.getPrivileges().addAll(form.getPrivilege().values());
		
		roleService.update(role);

		// 写操作日志
//		Log log = new Log();
//		log.setUser(this.getCurrentUserInfo(request).getUser());
//		log.setLogObject("角色管理");
//		log.setLogAction("角色功能权限授予");
//		log.setDetail("被授予功能权限的角色:" + role.getRoleName());
//		logService.save(log);

		return new ModelAndView("redirect:" + form.getReturnUrl());
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
	 * 返回 roleService
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * 设置 roleService
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	


}