/**
 * <p>��Ŀ���ƣ�����ģ��</p>
 * <p>��Ȩ���� (c) 2005 ����ȫ����Ϣ�Ƽ����޹�˾</p>
 * <p>���ߣ�</p>
 * <p>�汾��1.0</p>
 * <p>���ڣ�2007-9-4</p>
 * <p>���£�</p>
 */
package com.hxzy.base.web.controller.login;

import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.web.controller.BaseController;
import com.hxzy.common.log.model.Log;
import com.hxzy.common.user.model.UserInfo;

/**
 * <p>
 * ����: LogoutController
 * </p>
 * <p>
 * ����: ע����¼Controller
 * </p>
 */
public class LogoutController extends BaseController {

	/*
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// д������־
		if (this.getCurrentUserInfo(request).getUser() != null) {
			Log log = new Log();
			log.setUser(this.getCurrentUserInfo(request).getUser());
			log.setLogObject("�û��˳�");
			log.setLogAction("�˳�");
			log.setDetail("�˳�ϵͳ");
			logService.save(log);
		}
		 //�������û������Ƴ�
		 UserInfo user = (UserInfo) request.getSession().getAttribute(Constant.ATTRIBUTE_USER_INFO);
		 TreeMap onlineUsers = 
			 (TreeMap) request.getSession().getServletContext().getAttribute(Constant.ATTRIBUTE_ONLINE_USER_INFO);
		 if (onlineUsers.get(user.getUser().getCode()) != null)
		 onlineUsers.remove(user.getUser().getCode());

		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/login.do");
		return null;

	}

}