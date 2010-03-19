/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：</p>
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
 * 类名: PersonRoleEditController
 * </p>
 * <p>
 * 描述: 人员角色编辑Controller
 * </p>
 */
public class PersonRoleEditController extends BaseFormController {

	/**
	 * 描述: 人员Manager
	 */
	private UserService userService;

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
		// 形成角色数据(获取当前站点的所有角色和公共角色)
		Map map = new HashMap();
		map.put("roleList", roleService.loadAll());
		return map;
	}

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		// 创建一个空的Command对象
		PersonRoleEditForm form = new PersonRoleEditForm();
		// 获取用户ID
		Long userId = StringUtil.stringToLong(RequestUtils.getStringParameter(
				request, "userId", "0"));
		// 判断要授权的用户是否存在，如不存在则转入提示页面
		User user = userService.findById(userId);
		if (user == null) {
			throw new ApplicationException("exception.msg.dataDoesNotExist");
		}
		// 形成人员数据
		request.setAttribute("user", user);
		// 设置form数据
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
		// 对角色进行绑定
		PersonRoleEditForm form = (PersonRoleEditForm) o;
		// 获取角色
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
		// 设置相关数据
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
		
		// 保存数据
		userService.update(user);

		// 写操作日志
//		Log log = new Log();
//		log.setUser(this.getCurrentUserInfo(request).getUser());
//		log.setLogObject("用户管理");
//		log.setLogAction("人员角色授予");
//		log.setDetail("被授予角色人员代码:" + user.getCode());
//		logService.save(log);
//
		return new ModelAndView("redirect:" + form.getReturnUrl());
	}


	
	
	/**
	 * 返回 userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * 设置 userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
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