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
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import sun.reflect.generics.tree.Tree;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.exception.ApplicationException;
import com.hxzy.base.util.StringUtil;
import com.hxzy.base.web.controller.BaseFormController;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.service.PrivilegeService;
import com.hxzy.common.user.service.RoleService;

/**
 * <p>
 * 类名: RolePrivilegeEditController
 * </p>
 * <p>
 * 描述: 角色栏目权限编辑Controller
 * </p>
 */
public class TreePrivilegeEditController extends BaseFormController {

	/**
	 * 描述: 角色Manager
	 */
	private RoleService roleService;

	/**
	 * 描述: 权限Manager
	 */
	private PrivilegeService privilegeService;

	/**
	 * 描述: 栏目Manager
	 */
	private TreeManager treeManager;

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#referenceData(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.Errors)
	 */
	protected Map referenceData(HttpServletRequest request, Object o,
			Errors errors) throws Exception {
		Map map = new HashMap();
		// 取树形节点的类别(取栏目和专题)
		HashMap treeMap = new HashMap();
		treeMap.put("parentId", new Long(0));
		List treeCategoryList = treeManager.findTreeByCondition(treeMap);
		map.put("treeCategoryList", treeCategoryList);

		// 取指定类型的所有值
		treeMap = new HashMap();
		String getcategory = RequestUtils.getStringParameter(request,
				"getcategory", "newsChannel");
		map.put("getcategory", getcategory);
		// 获取站点id
		// Long ChildsiteId = (Long) request.getSession().getAttribute(
		// "ChildsiteId");
		String publicFlag = "0";
		//treeMap.put("category", getcategory);
		treeMap.put("isLink", "0");
		// 得到符合条件的栏目记录数
		List treeList = treeManager.findTreeByCondition(treeMap);
		treeList.remove(0);
		for (int i = 0; i < treeList.size(); i++) {
			Tree tree = (Tree) treeList.get(i);
			String clientNodeInfo = "1";
			boolean isHasChild = false;
			// 判断是否有子节点, true 表示有子节点 false 表示无子节点
			isHasChild = treeManager.findTreeNodeList(tree.getItemId());
			if (isHasChild == true) {
				clientNodeInfo = "0";
			}
			tree.setClientNodeInfo(clientNodeInfo);
			// 栏目名称格式化
			tree.setItemText(treeManager.formatTreeText(tree.getLevel(), tree
					.getItemText()));
		}
		map.put("treeList", treeList);
		return map;
	}

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		Long ChildsiteId = new Long(1);
		// 创建一个空的Command对象
		TreePrivilegeEditForm form = new TreePrivilegeEditForm();
		// 获取角色代码
		Long roleId = StringUtil.stringToLong(RequestUtils.getStringParameter(
				request, "roleId", "0"));
		// 判断要授权的角色是否存在，如不存在则转入提示页面
		Role role = roleManager.findRoleById(roleId);
		if (role == null) {
			throw new ApplicationException("exception.msg.dataDoesNotExist");
		}
		// 形成角色数据
		request.setAttribute("role", role);
		Map map = new HashMap();
		map.put("roleId", roleId);
		map.put("ChildsiteId", ChildsiteId);
		Map rolePrivileges = privilegeManager.findRolePrivilegeByCondition(map);
		// 设置form数据
		if (roleId != null) {
			form.setRoleId(roleId.toString());
			form.setName(role.getRoleName());
			form.setPrivilege(rolePrivileges);
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
		// 判断要授权的角色是否存在，如不存在则转入提示页面
		Role role = roleManager.findRoleById(new Long(RequestUtils
				.getStringParameter(request, "roleId", "")));
		if (role == null) {
			return new ModelAndView("systemMessage",
					Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
							"public.msg.dataDoesNotExist", request));
		}
		// 形成角色数据
		request.setAttribute("role", role);
		return showForm(request, errors, getFormView(), controlModel);
	}

	/*
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.BindException)
	 */
	protected void onBind(HttpServletRequest request, Object o,
			BindException errors) throws Exception {
		// 对权限进行绑定
		TreePrivilegeEditForm form = (TreePrivilegeEditForm) o;
		// 获取权限
		String[] privileges = RequestUtils.getStringParameters(request,
				"privileges");
		HashMap map = new HashMap();
		String privilegeId = null;
		for (int i = 0; i < privileges.length; i++) {
			privilegeId = privileges[i];
			if (privilegeId != null)
				map.put(privilegeId, privilegeId);
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
		Long ChildsiteId = (Long) request.getSession().getAttribute(
				"ChildsiteId");
		TreePrivilegeEditForm form = (TreePrivilegeEditForm) o;
		// 判断要授权的角色是否存在，如不存在则转入提示页面
		Role role = roleManager.findRoleById(new Long(RequestUtils
				.getStringParameter(request, "roleId", "")));
		if (role == null) {
			return new ModelAndView("systemMessage",
					Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
							"public.msg.dataDoesNotExist", request));
		}
		// 设置相关数据
		ArrayList privileges = new ArrayList();
		Iterator it = form.getPrivilege().keySet().iterator();
		String privilegeId = null;
		while (it.hasNext()) {
			privilegeId = (String) it.next();
			privileges.add(privilegeId);
		}
		// 保存数据
		privilegeManager.grantPrivilegeToRole(role.getRoleId(), privileges,
				new Long(1));
		// 写操作日志
//		Log log = new Log();
//		log.setUser(this.getCurrentUserInfo(request).getUser());
//		log.setLogObject("角色管理");
//		log.setLogAction("角色栏目权限授予");
//		log.setDetail("被授予栏目权限的角色:" + role.getRoleName());
//		logService.insertLog(log);

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
	 * 返回 treeManager
	 */
	public TreeManager getTreeManager() {
		return treeManager;
	}

	/**
	 * 设置 treeManager
	 */
	public void setTreeManager(TreeManager treeManager) {
		this.treeManager = treeManager;
	}
	
	

}