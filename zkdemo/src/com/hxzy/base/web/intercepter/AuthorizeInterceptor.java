package com.hxzy.base.web.intercepter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventThreadInit;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.exception.ApplicationException;
import com.hxzy.base.web.window.Message;
import com.hxzy.common.user.model.UserInfo;

public class AuthorizeInterceptor implements EventThreadInit {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.event.EventThreadInit#init(org.zkoss.zk.ui.Component,
	 *      org.zkoss.zk.ui.event.Event)
	 */
	public boolean init(Component comp, Event evt) throws Exception {
		HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
		
		HttpSession session = (HttpSession) Sessions.getCurrent().getNativeSession();

		if (!(comp instanceof Authorizable))
			return true;
		Authorizable handler = (Authorizable) comp;
		if (!handler.needAuthorization())
			return true;
		
		UserInfo userInfo = (UserInfo) session.getAttribute(Constant.ATTRIBUTE_USER_INFO);
		
		if(userInfo == null){
			Executions.getCurrent().sendRedirect("/login.zul");
			return false;
		}
		
		Map userFunPriv = userInfo.getUserFunPriv();
		if (!StringUtils.hasText(handler.getFunctionCode())) {
			return true;
		}
		if (!userFunPriv.containsKey((handler.getFunctionCode()))) {
			Message.showError("您没有该功能权限！请联系管理员！");
			comp.detach();
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.event.EventThreadInit#prepare(org.zkoss.zk.ui.Component,
	 *      org.zkoss.zk.ui.event.Event)
	 */
	public void prepare(Component comp, Event evt) throws Exception {

	}

}