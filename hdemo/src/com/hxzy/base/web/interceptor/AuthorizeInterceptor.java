package com.hxzy.base.web.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hxzy.base.constant.Constant;
import com.hxzy.base.exception.ApplicationException;
import com.hxzy.common.user.model.UserInfo;

public class AuthorizeInterceptor implements HandlerInterceptor {

	/*
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {
		if (!(o instanceof Authorizable))
			return true;
		Authorizable handler = (Authorizable) o;
		if (!handler.needAuthorization())
			return true;
		Map userFunPriv = ((UserInfo) request.getSession().getAttribute(
				Constant.ATTRIBUTE_USER_INFO)).getUserFunPriv();
		if (handler.getFunctionCode() == null) {
			return true;
		}
		if (!userFunPriv.containsKey((handler.getFunctionCode()))) {
			throw new ApplicationException("exception.msg.RequestWithoutFunPrivilegeException");
		}
		return true;
		// return handler.authorize();
	}

	/*
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {

	}

	/*
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.web.servlet.ModelAndView)
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView modelAndView) {

	}

}