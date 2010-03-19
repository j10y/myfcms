/**
 * <p>项目名称：公共模块</p>
 * <p>版权所有 (c) 2005 湖北全达信息科技有限公司</p>
 * <p>作者：</p>
 * <p>版本：1.0</p>
 * <p>日期：2007-9-3</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.util.StringUtil;
import com.hxzy.base.web.controller.BaseController;
import com.hxzy.common.log.model.Log;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.service.RoleService;

/**
 * <p>
 * 类名: RoleDeleteController
 * </p>
 * <p>
 * 描述: 角色删除Controller
 * </p>
 */
public class RoleDeleteController extends BaseController {

	/**
	 * 描述: 角色Manager
	 */
	private RoleService roleService;

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 获取角色ID
		Long roleId = StringUtil.stringToLong(RequestUtils.getStringParameter(
				request, "roleId", "0"));
		Role role = roleService.findById(roleId);
		// 判断要删除的记录是否存在，如不存在则转入提示页面
		if (role == null) {
			return new ModelAndView("systemMessage",
					Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
							"public.msg.dataDoesNotExist", request));
		}
		// 删除角色
		roleService.delete(roleId);

		// 写操作日志
		Log log = new Log();
		log.setUser(this.getCurrentUserInfo(request).getUser());
		log.setLogObject("角色管理");
		log.setLogAction("删除");
		log.setDetail("被删除的角色:" + role.getRoleName());
		logService.save(log);

		return new ModelAndView("redirect:"
				+ RequestUtils.getStringParameter(request,
						Constant.ATTRIBUTE_RETURN_URL, ""));
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
