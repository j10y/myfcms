/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Jun 27, 2014</p>
 * <p>更新：</p>
 */
package action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

		try {
			// 使用从库读数据
			String URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_htmlextractor";
			String sql = "insert into report (url,title) values ('" + url + "','" + title + "');";
			// 通过SaeUserInfo提供的静态方法获取应用的access_key和secret_key
			String Username = "524042kxx4";
			String Password = "hw0hkh5i3mh1wj1hk34j20zmh5k4x454m4xh23h1";
			String Driver = "com.mysql.jdbc.Driver";
			Class.forName(Driver).newInstance();
			Connection con = DriverManager.getConnection(URL, Username, Password);
			Statement statement = con.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			con.close();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
