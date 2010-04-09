package com.hxzy.base.web.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventThreadInit;

import com.hxzy.base.util.WebAppUtil;

/**
 * <p>
 * ¿‡√˚: AuthenticateInterceptor
 * </p>
 * <p>
 * √Ë ˆ: ”√ªßµ«¬ººÏ≤ÈInterceptor
 * </p>
 */
public class AuthenticateInterceptor implements EventThreadInit {

	/**
	 * √Ë ˆ: µ«¬º“≥√ÊUrl
	 */
	public String loginUrl = "/login.zul";

	/**
	 * √Ë ˆ: ∑µªÿ loginUrl
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * √Ë ˆ: …Ë÷√ loginUrl
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.event.EventThreadInit#init(org.zkoss.zk.ui.Component,
	 *      org.zkoss.zk.ui.event.Event)
	 */
	public boolean init(Component comp, Event evt) throws Exception {
		HttpServletRequest request = (HttpServletRequest) Executions.getCurrent()
				.getNativeRequest();

		if (!(comp instanceof Authorizable))
			return true;

		Authenticatable handler = (Authenticatable) comp;
		if (!handler.needAuthentication())
			return true;
		if (WebAppUtil.isUserLogined(request)) {
			return true;
		} else {
			Executions.getCurrent().sendRedirect(loginUrl);
			return false;
		}

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