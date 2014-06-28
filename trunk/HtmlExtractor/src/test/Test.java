/**
 * <p>项目名称：</p>
 * <p>版权所有 (c) </p>
 * <p>版本：1.0</p>
 * <p>日期：Jun 27, 2014</p>
 * <p>更新：</p>
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;



/**
 * @author xiacc
 *
 * 描述：
 */
public class Test {

	public static void main(String[] args) {
		try {
			// 使用从库读数据
			String URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_htmlextractor";
			String sql = "insert into report (url,title) values ('123','234');";
			// 通过SaeUserInfo提供的静态方法获取应用的access_key和secret_key
			String Username = "yourfei@sina.com";
			String Password = "xiabiao851";
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
