package base.user.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import base.exception.ApplicationException;
import base.log.model.Log;
import base.user.model.BaseUser;
import base.user.model.Role;
import base.user.service.BaseUserService;
import base.user.service.RoleService;
import base.util.StringUtil;
import base.web.controller.BaseFormController;

public class RoleUserController extends BaseFormController {
	
	private RoleService roleService;
	
	private BaseUserService baseUserService;
	
	/*
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		// request.getParameterValues("orgTreeId");
		String[] userIds = request.getParameterValues("userId");

		// 创建一个空的Command对象
		RoleUserForm form = new RoleUserForm();
		Long roleId = StringUtil.stringToLong(RequestUtils.getStringParameter(
				request, "roleId", "0"));
		Role role = roleService.findById(roleId);
		if (role == null) {
			throw new ApplicationException("exception.msg.dataDoesNotExist");
		}
		request.setAttribute("role", role);

		form.setRoleId(roleId);
		form.setUserId(userIds);

		return form;
	}

	protected Map referenceData(HttpServletRequest request, Object o,
			Errors errors) throws Exception {
		Long roleId = new Long(0);
		if (request.getParameter("roleId") != null) {
			roleId = Long.valueOf(request.getParameter("roleId"));
		}		
		
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BaseUser.class);
		detachedCriteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY).createAlias("roles", "r").add(Restrictions.ne("r.id", roleId));	
//		
		List userList = baseUserService.findByCriteria(detachedCriteria);
//		
//		List userList = baseUserService.find("from BaseUser u inner join u.roles r where u.id=r.id and r.id <> ?", new Object[]{roleId});
		
		//已有此角色的用户
		DetachedCriteria detachedCriteria2 = DetachedCriteria.forClass(BaseUser.class);		
		detachedCriteria2.createAlias("roles", "r").add(Restrictions.eq("r.id", roleId));		
		List roleUserList =  baseUserService.findByCriteria(detachedCriteria2);
		
		
		
		
		Map map = new HashMap();
		map.put("userList", userList);
		map.put("roleUserList", roleUserList);
		return map;
	}
	
	/*
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.BindException)
	 */
	protected void onBind(HttpServletRequest request, Object o,
			BindException errors) throws Exception {
	}

	/*
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		RoleUserForm form = (RoleUserForm) o;
		Role role = (Role) request.getAttribute("role");
		// 设置相关数据
		List roleUsers = new ArrayList();
		String[] userIds = form.getUserId();
		if (userIds != null) {
			for (int i = 0; i < userIds.length; i++) {
				BaseUser user = baseUserService.findById(new Long(userIds[i]));
				user.getRoles().add(role);

				baseUserService.update(user);
			}
		}
		
		// 写操作日志
//		Log log = new Log();
//		log.setUser(this.getCurrentUserInfo(request).getUser());
//		log.setLogObject("角色管理");
//		log.setLogAction("角色用户设置");
//		log.setDetail("被设置的角色名称:" + form.getRoleId());
//		logService.save(log);

		return new ModelAndView("redirect:" + form.getReturnUrl());
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

	/**
	 * 返回 baseUserService
	 */
	public BaseUserService getBaseUserService() {
		return baseUserService;
	}

	/**
	 * 设置 baseUserService
	 */
	public void setBaseUserService(BaseUserService baseUserService) {
		this.baseUserService = baseUserService;
	}
	
	


}
