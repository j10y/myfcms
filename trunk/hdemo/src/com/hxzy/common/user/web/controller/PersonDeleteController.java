/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-3</p>
 * <p>���£�</p>
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
import com.hxzy.common.user.model.User;
import com.hxzy.common.user.service.UserService;

/**
 * <p>
 * ����: PersonDeleteController
 * </p>
 * <p>
 * ����: ��Աɾ��Controller
 * </p>
 */
public class PersonDeleteController extends BaseController {

	/**
	 * ����: ��ԱManager
	 */
	private UserService userService;

	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// ��ȡ��ɫID
		Long id = StringUtil.stringToLong(RequestUtils.getStringParameter(
				request, "id", "0"));
		User person = userService.findById(id);
		// �ж�Ҫɾ���ļ�¼�Ƿ���ڣ��粻������ת����ʾҳ��
		if (person == null) {
			return new ModelAndView("systemMessage",
					Constant.ATTRIBUTE_SYSTEM_MESSAGE, this.getMessage(
							"public.msg.dataDoesNotExist", request));
		}
		// ɾ����ɫ
		userService.delete(id);

		// д������־
		Log log = new Log();
		log.setUser(this.getCurrentUserInfo(request).getUser());
		log.setLogObject("�û�����");
		log.setLogAction("ɾ��");
		log.setDetail("��ɾ�����û�:" + person.getName());
		logService.save(log);

		return new ModelAndView("redirect:"
				+ RequestUtils.getStringParameter(request,
						Constant.ATTRIBUTE_RETURN_URL, ""));
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