package com.hxzy.base.web.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Session;

import com.hxzy.base.util.WebAppUtil;

/**
 * <p>
 * ¿‡√˚: AuthenticateInterceptor
 * </p>
 * <p>
 * √Ë ˆ: ”√ªßµ«¬ººÏ≤ÈInterceptor
 * </p>
 */
public class AuthenticateInterceptor extends HandlerInterceptor {

	/**
	 * √Ë ˆ: µ«¬º“≥√ÊUrl
	 */
	public String loginUrl = "";

	

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
	

	/* (non-Javadoc)
	 * @see com.hxzy.base.web.intercepter.HandlerInterceptor#handle(org.zkoss.zk.ui.Session, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handle(Session session, HttpServletRequest request, HttpServletResponse response) {
		Authenticatable handler = (Authenticatable) o;
		if (!handler.needAuthentication())
			return true;
		if (WebAppUtil.isUserLogined(request)) {
			return true;
		} else {
			try {
				response.sendRedirect(request.getContextPath() + loginUrl);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			return false;
		}
		
	}

}