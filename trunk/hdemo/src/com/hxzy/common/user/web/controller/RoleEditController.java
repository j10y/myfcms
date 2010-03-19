/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.util.StringUtil;
import com.hxzy.base.web.controller.BaseFormController;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.service.RoleService;

/**
 * <p>
 * 类名: RoleEditController
 * </p>
 * <p>
 * 描述: 角色编辑窗口Controller
 * </p>
 */
public class RoleEditController extends BaseFormController {

	/**
	 * 描述: 角色Manager
	 */
	private RoleService roleService;

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		// 获取编辑标志
		String editFlag = RequestUtils.getStringParameter(request, "editFlag",
				"");
		// 创建一个空的Command对象
		RoleEditForm form = new RoleEditForm();
		// 根据编辑标志进行相关处理(新增时返回一个空Command对象，修改时返回一个包含数据的Command对象)
		if (Constant.FLAG_EDIT_MODIFY.equals(editFlag)) {
			// 修改时的处理
			// 获取角色记录
			Long roleId = StringUtil.stringToLong(RequestUtils
					.getStringParameter(request, "roleId", "0"));
			Role role = roleService.findById(roleId);
			// 将数据放入request以便使用
			request.setAttribute(Constant.ATTRIBUTE_MODEL_DATA, role);
			if (role != null) {
				// 设置Command对象数据
				form.setRoleId(role.getId().toString());
				form.setRoleName(role.getRoleName());
			}
		}
		return form;
	}

	/*
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#showForm(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse,
	 *      org.springframework.validation.BindException, java.util.Map)
	 */
	protected ModelAndView showForm(HttpServletRequest request,
			HttpServletResponse response, BindException errors, Map controlModel)
			throws Exception {
		// 获取编辑标志
		String editFlag = RequestUtils.getStringParameter(request, "editFlag",
				"");
		// 根据编辑标志进行处理
		if (Constant.FLAG_EDIT_MODIFY.equals(editFlag)) {
			// 修改时的处理
			// 判断要修改的记录是否存在，如不存在则转入提示页面,(从request中获取,在formBackingObject方法中已设置)
			Role role = (Role) request
					.getAttribute(Constant.ATTRIBUTE_MODEL_DATA);
			if (role == null) {
				return new ModelAndView("systemMessage",
						Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
								"public.msg.dataDoesNotExist", request));
			}
		}
		return showForm(request, errors, getFormView(), controlModel);
	}

	/*
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.BindException)
	 */
	protected void onBind(HttpServletRequest request, Object o,
			BindException errors) throws Exception {
		// 判断角色是否存在
		RoleEditForm form = (RoleEditForm) o;
		List list = roleService.findByProperty("roleName",form.getRoleName().trim());
		Role valiRole = null; 
		if(!list.isEmpty()){
			valiRole = (Role) list.get(0);
		}
		if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {
			if (valiRole != null && !valiRole.getRoleName().equals("")) {
				String[] valiName = new String[1];
				valiName[0] = valiRole.getRoleName();
				errors.reject("public.msg.objectExist", valiName, "");
			}
		}
		if (Constant.FLAG_EDIT_MODIFY.equals(form.getEditFlag())) {
			Role role = (Role) request
					.getAttribute(Constant.ATTRIBUTE_MODEL_DATA);
			if (valiRole != null
					&& !valiRole.getRoleName().equals("")
					&& role.getId().intValue() != valiRole.getId().intValue()) {
				String[] valiName = new String[1];
				valiName[0] = valiRole.getRoleName();
				errors.reject("public.msg.objectExist", valiName, "");
			}
		}
	}

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		RoleEditForm form = (RoleEditForm) o;
		Role role = null;
		// 新增时的处理
		if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {
			role = new Role();
			role.setRoleName(form.getRoleName().trim());
			role.setPublicFlag(new Long(0));
			// 写数据库
			roleService.save(role);
		}
		// 修改时的处理
		if (Constant.FLAG_EDIT_MODIFY.equals(form.getEditFlag())) {
			role = (Role) request.getAttribute(Constant.ATTRIBUTE_MODEL_DATA);
			// 判断要修改的记录是否存在，如不存在则转入提示页面
			if (role == null) {
				return new ModelAndView("systemMessage",
						Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
								"public.msg.dataDoesNotExist", request));
			}
			role.setRoleName(form.getRoleName().trim());
			// 写数据库
			roleService.update(role);
		}

		// 写操作日志
//		Log log = new Log();
//		log.setUser(this.getCurrentUserInfo(request).getUser());
//		log.setLogObject("角色管理");
//		if (Constant.FLAG_EDIT_MODIFY.equals(form.getEditFlag())) {
//			log.setLogAction("修改");
//			log.setDetail("修改角色:" + role.getRoleName());
//		}
//		if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {
//			log.setLogAction("增加");
//			log.setDetail("增加角色:" + role.getRoleName());
//		}
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
	
	

}
