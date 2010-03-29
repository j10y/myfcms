package com.hxzy.base.web.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.util.RequestInterceptor;

import com.hxzy.base.util.WebAppUtil;

/**
 * <p>
 * ����: AuthenticateInterceptor
 * </p>
 * <p>
 * ����: �û���¼���Interceptor
 * </p>
 */
public class AuthenticateInterceptor implements RequestInterceptor {

	/**
	 * ����: ��¼ҳ��Url
	 */
	public String loginUrl = "";

	

	/**
	 * ����: ���� loginUrl
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * ����: ���� loginUrl
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.zkoss.zk.ui.util.RequestInterceptor#request(org.zkoss.zk.ui.Session,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void request(Session arg0, Object arg1, Object arg2) {
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