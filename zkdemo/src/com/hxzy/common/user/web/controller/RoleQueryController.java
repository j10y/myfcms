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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.util.Pagination;
import com.hxzy.base.util.StringUtil;
import com.hxzy.base.web.controller.BaseCommandController;
import com.hxzy.common.user.model.Role;
import com.hxzy.common.user.service.RoleService;

/**
 * <p>
 * 类名: RoleQueryController
 * </p>
 * <p>
 * 描述: 角色查询Controller
 * </p>
 */
public class RoleQueryController extends BaseCommandController {

	/**
	 * 描述: 角色Manager
	 */
	private RoleService roleService;

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractCommandController#handle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView handle(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		RoleQueryForm form = (RoleQueryForm) o;
		HashMap map = new HashMap();
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Role.class);
		
		// 形成查询条件数据，以便在窗口重新显示
		map.put("formInfo", form);
		// 形成查询条件数据，以传给Service对象进行查询处理，并将结果放入Map以便在页面显示
		HashMap conditionMap = new HashMap();
		// conditionMap.put("searchCondition", "childsite_id = " + ChildsiteId
		// + " or public_flag = 1");
		if (form.getRoleName() != null && !"".equals(form.getRoleName().trim())) {
			String roleName = form.getRoleName().trim();
			detachedCriteria.add(Restrictions.eq("roleName", roleName));
		}
		
		int pageNo = StringUtil.String2Int(form.getPageNo());
		int pageSize = StringUtil.String2Int(form.getRecPerPage());
		
		Pagination pagination = roleService.findPageByCriteria(detachedCriteria, pageSize, pageNo);
		
		
		map.put("pagination", pagination);
		return new ModelAndView("common/role/roleQuery", map);
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
