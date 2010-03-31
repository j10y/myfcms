package com.hxzy.base.web.intercepter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventThreadInit;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.exception.ApplicationException;
import com.hxzy.common.user.model.UserInfo;

public class AuthorizeInterceptor implements EventThreadInit {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.event.EventThreadInit#init(org.zkoss.zk.ui.Component,
	 *      org.zkoss.zk.ui.event.Event)
	 */
	public boolean init(Component comp, Event evt) throws Exception {
		HttpServletRequest request = (HttpServletRequest) comp.getDesktop().getExecution()
				.getNativeRequest();

		if (!(comp instanceof Authorizable))
			return true;
		Authorizable handler = (Authorizable) comp;
		if (!handler.needAuthorization())
			return true;
		Map userFunPriv = ((UserInfo) request.getSession().getAttribute(
				Constant.ATTRIBUTE_USER_INFO)).getUserFunPriv();
		if (!StringUtils.hasText(handler.getFunctionCode())) {
			return true;
		}
		if (!userFunPriv.containsKey((handler.getFunctionCode()))) {
			throw new ApplicationException("exception.msg.RequestWithoutFunPrivilegeException");
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