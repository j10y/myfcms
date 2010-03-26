/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�2007-9-4�������û������ʽ�ж�</p>
 */
package com.hxzy.common.user.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.util.StringUtil;
import com.hxzy.base.web.controller.BaseFormController;
import com.hxzy.common.log.model.Log;
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * <p>
 * ����: PasswordModifyController
 * </p>
 * <p>
 * ����: �����޸�Controller
 * </p>
 */

public class PasswordModifyController extends BaseFormController {

	/**
	 * ����: ��ԱManager
	 */
	private UserService userService;

	/*
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#onBind(javax.servlet.http.HttpServletRequest,
	 *      java.lang.Object, org.springframework.validation.BindException)
	 */
	protected void onBind(HttpServletRequest request, Object o,
			BindException errors) throws Exception {
		PasswordModifyForm form = (PasswordModifyForm) o;
		if (form.getNewPassword() == null)
			form.setNewPassword("");
		// �ж��¿����Ƿ���Ϲ涨�ĸ�ʽ
		if (!(StringUtil.isMatcherPattern(Constant.REGEXP_PASSWORD, form
				.getNewPassword().trim())))
			errors.reject("person.msg.invaidPasswordFormat", null, "");
		// �ж��ظ������Ƿ����¿���һ��
		if (!form.getNewPassword().trim().equals(
				form.getNewPasswordRepeat().trim())) {
			errors.reject(
					"person.msg.repeatPasswordIsNotIdentialToNewPassword",
					null, "");
		}
	}

	/*
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object o, BindException errors)
			throws Exception {
		PasswordModifyForm form = (PasswordModifyForm) o;
		// ��ȡ�û���Ϣ
		User person = userService.findById(this.getCurrentUserInfo(request).getUser().getId());
		// ���û���Ϣ��������ת����ʾҳ��
		if (person == null) {
			return new ModelAndView("systemMessage",
					Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
							"public.msg.dataDoesNotExist", request));
		}
		// �ж��û�ԭ���������Ƿ���ȷ���粻��ȷ��ת����ʾҳ��
		// String oldPassword = MD5.md5(form.getOrignialPassword().trim());
		String oldPassword = form.getOrignialPassword().trim();
		if (!person.getPassword().equals(oldPassword)) {
			errors.reject("person.msg.invalidPassword");
			return showForm(request, response, errors);
		}
		// ���Ŀ���
		person.setPassword(form.getNewPassword().trim());
		// person.setPassword(form.getNewPassword().trim());
		userService.update(person);

		// д������־
		Log log = new Log();
		log.setUser(this.getCurrentUserInfo(request).getUser());
		log.setLogObject("�����޸�");
		log.setLogAction("�޸�");
		log.setDetail("�޸��û�����");
		logService.save(log);

		return new ModelAndView(getSuccessView());
	}

	/**
	 * ���� userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * ���� userService
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	
	
}