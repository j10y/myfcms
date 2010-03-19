package com.hxzy.base.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Cookie操作的工具类
 * 
 * @author huwei(jshuwei.org.cn)
 * @since 1.0
 * 
 */
public class CookieUtil {

	/**
	 * 
	 * 查找cookie
	 * 
	 * @since 1.0
	 * @param name
	 *            cookie名称
	 * @param cookies
	 *            客户端cookie
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
	 * 删除cookie
	 * 
	 * @since 1.0
	 * @param cookie
	 *            需要删除的某个cookie
	 * @param response
	 *            响应对象
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
	 * 保存cookie
	 * 
	 * @since 1.0
	 * @param cookie
	 *            需要保存的cookie
	 * @param response
	 *            响应对象
	 * 
	 */
	public static void SaveCookie(Cookie cookie, HttpServletResponse response) {
		response.addCookie(cookie);
	}
}