/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Jun 27, 2014</p>
 * <p>更新：</p>
 */
package action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiacc
 * 
 * 描述：
 */
public class Report extends HttpServlet {

	private final static Logger logger = LoggerFactory.getLogger(Report.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
			IOException {
		req.setCharacterEncoding("UTF-8");
		String url = req.getParameter("url");
		String title = req.getParameter("title");
		logger.error("url:" + url);
		logger.error("title:" + title);
		System.out.println("report");
	}

}
