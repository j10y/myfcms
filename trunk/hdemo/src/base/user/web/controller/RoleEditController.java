/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
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
 * ����: RoleEditController
 * </p>
 * <p>
 * ����: ��ɫ�༭����Controller
 * </p>
 */
public class RoleEditController extends BaseFormController {

	/**
	 * ����: ��ɫManager
	 */
	private RoleService roleService;

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		// ��ȡ�༭��־
		String editFlag = RequestUtils.getStringParameter(request, "editFlag",
				"");
		// ����һ���յ�Command����
		RoleEditForm form = new RoleEditForm();
		// ���ݱ༭��־������ش���(����ʱ����һ����Command�����޸�ʱ����һ���������ݵ�Command����)
		if (Constant.FLAG_EDIT_MODIFY.equals(editFlag)) {
			// �޸�ʱ�Ĵ���
			// ��ȡ��ɫ��¼
			Long roleId = StringUtil.stringToLong(RequestUtils
					.getStringParameter(request, "roleId", "0"));
			Role role = roleService.findById(roleId);
			// �����ݷ���request�Ա�ʹ��
			request.setAttribute(Constant.ATTRIBUTE_MODEL_DATA, role);
			if (role != null) {
				// ����Command��������
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
		// ��ȡ�༭��־
		String editFlag = RequestUtils.getStringParameter(request, "editFlag",
				"");
		// ���ݱ༭��־���д���
		if (Constant.FLAG_EDIT_MODIFY.equals(editFlag)) {
			// �޸�ʱ�Ĵ���
			// �ж�Ҫ�޸ĵļ�¼�Ƿ���ڣ��粻������ת����ʾҳ��,(��request�л�ȡ,��formBackingObject������������)
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
		// �жϽ�ɫ�Ƿ����
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
		// ����ʱ�Ĵ���
		if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {
			role = new Role();
			role.setRoleName(form.getRoleName().trim());
			role.setPublicFlag(new Long(0));
			// д���ݿ�
			roleService.save(role);
		}
		// �޸�ʱ�Ĵ���
		if (Constant.FLAG_EDIT_MODIFY.equals(form.getEditFlag())) {
			role = (Role) request.getAttribute(Constant.ATTRIBUTE_MODEL_DATA);
			// �ж�Ҫ�޸ĵļ�¼�Ƿ���ڣ��粻������ת����ʾҳ��
			if (role == null) {
				return new ModelAndView("systemMessage",
						Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
								"public.msg.dataDoesNotExist", request));
			}
			role.setRoleName(form.getRoleName().trim());
			// д���ݿ�
			roleService.update(role);
		}

		// д������־
//		Log log = new Log();
//		log.setUser(this.getCurrentUserInfo(request).getUser());
//		log.setLogObject("��ɫ����");
//		if (Constant.FLAG_EDIT_MODIFY.equals(form.getEditFlag())) {
//			log.setLogAction("�޸�");
//			log.setDetail("�޸Ľ�ɫ:" + role.getRoleName());
//		}
//		if (Constant.FLAG_EDIT_ADD.equals(form.getEditFlag())) {
//			log.setLogAction("����");
//			log.setDetail("���ӽ�ɫ:" + role.getRoleName());
//		}
//		logService.save(log);

		return new ModelAndView("redirect:" + form.getReturnUrl());
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
