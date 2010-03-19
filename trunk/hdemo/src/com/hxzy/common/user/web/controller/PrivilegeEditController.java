/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Mar 19, 2010</p>
 * <p>更新：</p>
 */
package com.hxzy.common.user.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.exception.ApplicationException;
import com.hxzy.base.util.StringUtil;
import com.hxzy.base.web.controller.BaseFormController;
import com.hxzy.common.user.model.Privilege;
import com.hxzy.common.user.service.PrivilegeService;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class PrivilegeEditController extends BaseFormController {
	
	private PrivilegeService privilegeService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected Object formBackingObject(HttpServletRequest request) throws Exception {

		String editFlag = RequestUtils.getStringParameter(request, "editFlag", "");
		String returnUrl = RequestUtils.getStringParameter(request, "returnUrl", "");
		String isBrother = RequestUtils.getStringParameter(request, "isBrother", "");

		PrivilegeEditForm form = new PrivilegeEditForm();
		form.setReturnUrl(returnUrl);
		form.setEditFlag(editFlag);
		if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {
			// 设置相关数据
			form.setIsBrother(isBrother);
		}
		if (Constant.FLAG_EDIT_MODIFY.equals(editFlag)) {
			Long itemId = StringUtil.stringToLong(RequestUtils
					.getStringParameter(request, "itemId", "0"));
			Privilege privilege = privilegeService.findById(itemId);
			if (privilege == null)
				throw new ApplicationException("exception.msg.dataDoesNotExist");
			form.setId(itemId.toString());
			form.setIsBrother(isBrother);
			form.setPrivCode(privilege.getPrivCode());
			form.setPrivName(privilege.getPrivName());
			request.setAttribute("privilege", privilege);
		}
		return form;
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		PrivilegeEditForm form = (PrivilegeEditForm) o;
		Map map = new HashMap();
		if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {
			Privilege privilege = new Privilege();
			privilege.setPrivCode(form.getPrivCode());
			privilege.setPrivName(form.getPrivName());
			Privilege parent = new Privilege();
			parent.setId(StringUtil.stringToLong(form.getParentId()));
			privilege.setParent(parent);
			
			privilegeService.save(privilege);
			
		}
		
		if (Constant.FLAG_EDIT_MODIFY.equals(form.getEditFlag())) {
			Privilege privilege = (Privilege) request.getAttribute("privilege");
			privilege.setPrivName(form.getPrivName());
			privilege.setPrivCode(form.getPrivCode());
			
			privilegeService.update(privilege);
		}
		
		return new ModelAndView("redirect:" + form.getReturnUrl(), map);
	}

}
