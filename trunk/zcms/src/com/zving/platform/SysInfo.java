package com.zving.platform;

import com.zving.framework.Config;
import com.zving.framework.Constant;
import com.zving.framework.Page;
import com.zving.framework.ResponseImpl;
import com.zving.framework.SessionListener;
import com.zving.framework.User;
import com.zving.framework.controls.DataListAction;
import com.zving.framework.data.DBConnConfig;
import com.zving.framework.data.DBConnPool;
import com.zving.framework.data.DataTable;
import com.zving.framework.license.LicenseInfo;
import com.zving.framework.messages.LongTimeTask;
import com.zving.framework.orm.DBExport;
import com.zving.framework.orm.DBImport;
import com.zving.framework.schedule.CronManager;
import com.zving.framework.utility.DateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class SysInfo extends Page {
	public static String getLoginStatus() {
		if (!(Config.isAllowLogin)) {
			return "恢复允许登录";
		}
		return "临时禁止登录";
	}

	public void changeLoginStatus() {
		Config.isAllowLogin = !(Config.isAllowLogin);
		if (!(Config.isAllowLogin))
			$S("LoginStatus", "恢复允许登录");
		else
			$S("LoginStatus", "临时禁止登录");
	}

	public void forceExit() {
		SessionListener.forceExit();
		this.Response.setStatus(1);
	}

	public static void list1DataBind(DataListAction dla) {
		DataTable dt = new DataTable();
		dt.insertColumn("Name");
		dt.insertColumn("Value");
		dt
				.insertRow(new Object[] { "应用程序名称",
						Config.getAppCode() + "(" + Config.getAppName() + ")" });
		dt.insertRow(new Object[] { "应用程序版本",
				Config.getMainVersion() + "." + Config.getMinorVersion() });
		dt.insertRow(new Object[] {
				"本次启动时间",
				DateUtil.toString(new Date(Long.parseLong(Config.getValue("App.Uptime"))),
						"yyyy-MM-dd HH:mm:ss") });
		dt.insertRow(new Object[] { "当前己登录用户数", new Long(Config.getLoginUserCount()) });
		dt.insertRow(new Object[] { "是否是调试模式", Config.getValue("App.DebugMode") });
		dt.insertRow(new Object[] { "操作系统名称", Config.getValue("System.OSName") });
		dt.insertRow(new Object[] { "操作系统版本", Config.getValue("System.OSVersion") });
		dt.insertRow(new Object[] { "操作系统补丁", Config.getValue("System.OSPatchLevel") });
		dt.insertRow(new Object[] { "JDK厂商", Config.getValue("System.JavaVendor") });
		dt.insertRow(new Object[] { "JDK版本", Config.getValue("System.JavaVersion") });
		dt.insertRow(new Object[] { "JDK主目录", Config.getValue("System.JavaHome") });
		dt.insertRow(new Object[] { "Servlet容器名称", Config.getValue("System.ContainerInfo") });
		dt.insertRow(new Object[] { "启动Servlet容器的用户名", Config.getValue("System.OSUserName") });
		dt.insertRow(new Object[] {
				"JDK己用内存数/最大可用数",
				(Runtime.getRuntime().totalMemory() / 1024L / 1024L) + "M/"
						+ (Runtime.getRuntime().maxMemory() / 1024L / 1024L) + "M" });
		dt.insertRow(new Object[] { "文件编码", Config.getFileEncode() });
		dla.bindData(dt);
	}

	public static void list2DataBind(DataListAction dla) {
		DataTable dt = new DataTable();
		dt.insertColumn("Name");
		dt.insertColumn("Value");
		DBConnConfig dcc = DBConnPool.getDBConnConfig();
		dt.insertRow(new Object[] { "数据库类型", dcc.DBType });
		if (dcc.isJNDIPool) {
			dt.insertRow(new Object[] { "JNDI名称", dcc.JNDIName });
		} else {
			dt.insertRow(new Object[] { "数据库服务器地址", dcc.DBServerAddress });
			dt.insertRow(new Object[] { "数据库服务器端口", dcc.DBPort });
			dt.insertRow(new Object[] { "数据库名称", dcc.DBName });
			dt.insertRow(new Object[] { "用户名", dcc.DBUserName });
		}
		dla.bindData(dt);
	}

	public static void list3DataBind(DataListAction dla) {
		DataTable dt = new DataTable();
		dt.insertColumn("Name");
		dt.insertColumn("Value");
		dt.insertRow(new Object[] { "授权给", LicenseInfo.getName() });
		dt.insertRow(new Object[] { "有效期至",
				DateUtil.toString(LicenseInfo.getEndDate(), "yyyy-MM-dd HH:mm:ss") });
		dt.insertRow(new Object[] { "授权用户数", new Long(LicenseInfo.getUserLimit()) });
		dt.insertRow(new Object[] { "授权产品代码", LicenseInfo.getProduct() });
		dt.insertRow(new Object[] { "授权MAC地址", LicenseInfo.getMacAddress() });
		dla.bindData(dt);
	}

	public static void downloadDB(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding(Constant.GlobalCharset);
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=DB_"
					+ DateUtil.getCurrentDate("yyyyMMddHHmmss") + ".dat");
			OutputStream os = response.getOutputStream();

			String path = Config.getContextRealPath() + "WEB-INF/data/backup/DB_"
					+ DateUtil.getCurrentDate("yyyyMMddHHmmss") + ".dat";
			new DBExport().exportDB(path);

			byte[] buffer = new byte[1024];
			int read = -1;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(path);
				while ((read = fis.read(buffer)) != -1)
					if (read > 0) {
						byte[] chunk = (byte[]) null;
						if (read == 1024) {
							chunk = buffer;
						} else {
							chunk = new byte[read];
							System.arraycopy(buffer, 0, chunk, 0, read);
						}
						os.write(chunk);
						os.flush();
					}
			} finally {
				if (fis != null) {
					fis.close();
				}
			}
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void uploadDB(HttpServletRequest request, HttpServletResponse response) {
		try {
			DiskFileItemFactory fileFactory = new DiskFileItemFactory();
			ServletFileUpload fu = new ServletFileUpload(fileFactory);
			List fileItems = fu.parseRequest(request);
			fu.setHeaderEncoding("UTF-8");
			Iterator iter = fileItems.iterator();

			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (!(item.isFormField())) {
					String OldFileName = item.getName();
					System.out.println("Upload DB FileName:" + OldFileName);
					long size = item.getSize();
					if ((((OldFileName == null) || (OldFileName.equals("")))) && (size == 0L)) {
						continue;
					}
					OldFileName = OldFileName.substring(OldFileName.lastIndexOf("\\") + 1);
					String ext = OldFileName.substring(OldFileName.lastIndexOf("."));
					if (!(ext.toLowerCase().equals(".dat"))) {
						response.sendRedirect("DBUpload.jsp?Error=上传失败，只能导入dat格式的文件");
						return;
					}
					final String FileName = Config.getContextRealPath()
							+ "WEB-INF/data/backup/DBUpload_"
							+ DateUtil.getCurrentDate("yyyyMMddHHmmss") + ".dat";
					item.write(new File(FileName));

					LongTimeTask ltt = LongTimeTask.getInstanceByType("Install");
					if (ltt != null) {
						response.sendRedirect("DBUpload.jsp?Error=相关任务正在运行中，请先中止！");
						return;
					}
					SessionListener.forceExit();
					Config.isAllowLogin = false;

					ltt = new LongTimeTask() {
						public void execute() {
							DBImport di = new DBImport();
							di.setTask(this);
							di.importDB(FileName, "Default");
							setPercent(100);
							Config.loadConfig();
							CronManager.getInstance().init();
						}
					};
					ltt.setType("Install");
					ltt.setUser(User.getCurrent());
					ltt.start();
					response.sendRedirect("DBUpload.jsp?TaskID=" + ltt.getTaskID());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Config.isAllowLogin = true;
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.platform.SysInfo JD-Core Version: 0.5.3
 */