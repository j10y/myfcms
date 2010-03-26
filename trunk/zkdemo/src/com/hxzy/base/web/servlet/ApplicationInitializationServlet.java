package com.hxzy.base.web.servlet;


import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;

import com.hxzy.base.constant.Constant;


/**
 * <p>
 * 类名: ApplicationInitializationServlet
 * </p>
 * <p>
 * 描述: 应用程序初始化Servlet
 * </p>
 */
public class ApplicationInitializationServlet extends HttpServlet {

	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		// 将WebApplicationContext放入ServletContext以便使用
		WebApplicationContext webApplicationContext = (WebApplicationContext) servletContext
				.getAttribute(Constant.ATTRIBUTE_WEB_APPLICATION_CONTEXT);
		// 设置日期格式以便使用
		servletContext.setAttribute("dateFormatLong",
				Constant.DATE_FORMAT_LONG_STRING);
		servletContext.setAttribute("dateFormatShort",
				Constant.DATE_FORMAT_SHORT_STRING);
		servletContext.setAttribute("dateFormatLongSecond",
				Constant.DATE_FORMAT_LONG_SECOND_STRING);
		servletContext.setAttribute("dateFormatMonth",
				Constant.DATE_FORMAT_MONTH_STRING);
		servletContext.setAttribute("dateFormatYear",
				Constant.DATE_FORMAT_YEAR_STRING);
		servletContext.setAttribute("dateFormatLongChinese",
				Constant.DATE_FORMAT_LONG_CHINESE_STRING);
		servletContext.setAttribute("dateFormatIndex", "MM月dd日");
		servletContext.setAttribute("dateFormatChinese", "yyyy年M月d日");

		// 创建在线用户表
		servletContext.setAttribute(Constant.ATTRIBUTE_ONLINE_USER_INFO,
				new TreeMap());

	}

}