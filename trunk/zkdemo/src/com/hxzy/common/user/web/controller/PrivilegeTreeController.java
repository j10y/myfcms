/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 19, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.web.controller.BaseController;
import com.hxzy.common.user.service.PrivilegeService;

/**
 * @author xiacc
 *
 * 描述：
 */
public class PrivilegeTreeController extends BaseController{

	
	private PrivilegeService privilegeService;
	
	/* (non-Javadoc)
	 * @see com.hxzy.base.web.controller.BaseController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map referenceMap = new HashMap();
		
		List privileges = privilegeService.loadAll();
		
		referenceMap.put("privileges", privileges);
		
		return new ModelAndView("common/privilege/privilegeTree",referenceMap);
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

	

	
}
