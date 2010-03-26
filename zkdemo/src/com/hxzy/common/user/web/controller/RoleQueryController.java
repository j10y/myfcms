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
 * ����: RoleQueryController
 * </p>
 * <p>
 * ����: ��ɫ��ѯController
 * </p>
 */
public class RoleQueryController extends BaseCommandController {

	/**
	 * ����: ��ɫManager
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
		
		// �γɲ�ѯ�������ݣ��Ա��ڴ���������ʾ
		map.put("formInfo", form);
		// �γɲ�ѯ�������ݣ��Դ���Service������в�ѯ���������������Map�Ա���ҳ����ʾ
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
