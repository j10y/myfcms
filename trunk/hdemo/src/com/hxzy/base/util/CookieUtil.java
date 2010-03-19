package com.hxzy.base.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Cookie�����Ĺ�����
 * 
 * @author huwei(jshuwei.org.cn)
 * @since 1.0
 * 
 */
public class CookieUtil {

	/**
	 * 
	 * ����cookie
	 * 
	 * @since 1.0
	 * @param name
	 *            cookie����
	 * @param cookies
	 *            �ͻ���cookie
	 * @return Cookie
	 * 
	 */
	public static Cookie FindCookie(String name, Cookie[] cookies) {
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}
		return null;
	}

	/**
	 * 
	 * ɾ��cookie
	 * 
	 * @since 1.0
	 * @param cookie
	 *            ��Ҫɾ����ĳ��cookie
	 * @param response
	 *            ��Ӧ����
	 * 
	 */
	public static void DeleteCookie(Cookie cookie, HttpServletResponse response) {
		if (cookie != null) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
	}

	/**
	 * 
	 * ����cookie
	 * 
	 * @since 1.0
	 * @param cookie
	 *            ��Ҫ�����cookie
	 * @param response
	 *            ��Ӧ����
	 * 
	 */
	public static void SaveCookie(Cookie cookie, HttpServletResponse response) {
		response.addCookie(cookie);
	}
}