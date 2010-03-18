package base.web.servlet;


import java.util.TreeMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;

import base.constant.Constant;


/**
 * <p>
 * ����: ApplicationInitializationServlet
 * </p>
 * <p>
 * ����: Ӧ�ó����ʼ��Servlet
 * </p>
 */
public class ApplicationInitializationServlet extends HttpServlet {

	public void init() throws ServletException {
		ServletContext servletContext = this.getServletContext();
		// ��WebApplicationContext����ServletContext�Ա�ʹ��
		WebApplicationContext webApplicationContext = (WebApplicationContext) servletContext
				.getAttribute(Constant.ATTRIBUTE_WEB_APPLICATION_CONTEXT);
		// �������ڸ�ʽ�Ա�ʹ��
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
		servletContext.setAttribute("dateFormatIndex", "MM��dd��");
		servletContext.setAttribute("dateFormatChinese", "yyyy��M��d��");

		// ���������û���
		servletContext.setAttribute(Constant.ATTRIBUTE_ONLINE_USER_INFO,
				new TreeMap());

	}

}