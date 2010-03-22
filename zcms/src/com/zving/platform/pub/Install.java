package com.zving.platform.pub;

import com.zving.framework.Ajax;
import com.zving.framework.Config;
import com.zving.framework.Constant;
import com.zving.framework.RequestImpl;
import com.zving.framework.ResponseImpl;
import com.zving.framework.User;
import com.zving.framework.data.DBConn;
import com.zving.framework.data.DBConnConfig;
import com.zving.framework.data.DBConnPoolImpl;
import com.zving.framework.data.DataAccess;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.messages.LongTimeTask;
import com.zving.framework.orm.DBImport;
import com.zving.framework.schedule.CronManager;
import com.zving.framework.security.EncryptUtil;
import com.zving.framework.utility.FileUtil;
import com.zving.framework.utility.StringUtil;
import com.zving.framework.utility.ZipUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tools.ant.filters.StringInputStream;

public class Install extends Ajax {
	public void execute() {
		if (Config.isDatabaseConfiged) {
			this.Response.setError("已经为ZCMS初始数据库完毕，不能再次初始化!");
			return;
		}

		final DBConnConfig dcc = new DBConnConfig();
		dcc.isJNDIPool = "1".equals($V("isJNDIPool"));
		dcc.JNDIName = $V("JNDIName");
		dcc.DBName = $V("DBName");
		dcc.DBPassword = $V("Password");
		try {
			dcc.DBPort = Integer.parseInt($V("Port"));
		} catch (NumberFormatException localNumberFormatException) {
		}
		dcc.DBServerAddress = $V("Address");
		dcc.DBType = $V("ServerType");
		dcc.DBUserName = $V("UserName");

		if ((Config.isJboss()) && (dcc.JNDIName.toLowerCase().startsWith("jdbc/"))) {
			dcc.JNDIName = dcc.JNDIName.substring(5);
		}

		DBConn conn = null;
		try {
			conn = DBConnPoolImpl.createConnection(dcc, false);
			boolean importData = "1".equals($V("ImportData"));
			final DBConn conn2 = conn;
			final boolean autoCreate = "1".equals($V("AutoCreate"));
			final RequestImpl r = this.Request;

			if (importData) {
				LongTimeTask ltt = LongTimeTask.getInstanceByType("Install");
				if (ltt != null) {
					this.Response.setError("相关任务正在运行中，请先中止！");
					return;
				}

				ltt = new LongTimeTask() {

					public void execute() {
						try {
							DBImport di = new DBImport();
							di.setTask(this);
							Config.setValue("App.DebugMode", "true");
							if (di.importDB(Config.getContextRealPath()
									+ "WEB-INF/data/backup/install.dat", conn2, autoCreate)) {
								if (autoCreate) {
									setCurrentInfo("正在建立索引");
									Install.createIndexes(conn2, null, conn2.getDBType());
								}
								setCurrentInfo("正在初始化系统配置");
								Install.init(conn2, r);
								setPercent(100);
								Install.generateFrameworkConfig(dcc);
								Config.loadConfig();
								CronManager.getInstance().init();
							}
							label160: addError("<font color=red>导入失败，请查看服务器日志! 确认问题后请按F5刷新页面重新导入。</font>");
						} finally {
							if (conn2 != null)
								try {
									conn2.closeReally();
								} catch (SQLException e) {
									e.printStackTrace();
								}
						}
					}
				};
				ltt.setType("Install");
				ltt.setUser(User.getCurrent());
				ltt.start();
				$S("TaskID", ltt.getTaskID());
				this.Response.setStatus(1);
				return;
			}
			init(conn2, r);
			generateFrameworkConfig(dcc);
			Config.loadConfig();
			CronManager.getInstance().init();
			this.Response.setError("ZCMS初始化完毕!");
		} catch (Exception e) {
			this.Response.setError("连接到数据库时发生错误:" + e.getMessage());
			if (conn == null)
				return;
			try {
				conn.closeReally();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public static void generateFrameworkConfig(DBConnConfig dcc) {
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<framework>\n");
		sb.append("\t<application>\n");
		sb.append("\t\t<config name=\"DebugMode\">false</config>\n");
		sb.append("\t\t<config name=\"LogLevel\">Debug</config>\n");
		sb.append("\t\t<config name=\"LoginClass\">com.zving.platform.Login</config>\n");
		sb.append("\t\t<config name=\"LoginPage\">Login.jsp</config>\n");
		sb.append("\t\t<config name=\"MinimalMemory\">true</config>\n");
		sb
				.append("\t\t<config name=\"CodeSource\">com.zving.platform.pub.PlatformCodeSource</config>\n");
		sb.append("\t\t<config name=\"ExistPlatformDB\">true</config>\n");
		sb.append("\t\t<config name=\"PDM\">Platform,ZCMS,WorkFlow</config>\n");
		sb.append("\t</application>\n");
		sb.append("\t<cron>\n");
		sb.append("\t\t<config name=\"RefreshInterval\">30000</config>\n");
		sb.append("\t\t<taskManager class=\"com.zving.datachannel.WebCrawlTaskManager\" />\n");
		sb.append("\t\t<taskManager class=\"com.zving.cms.dataservice.FullTextTaskManager\" />\n");
		sb.append("\t\t<taskManager class=\"com.zving.cms.datachannel.PublishTaskManager\" />\n");
		sb.append("\t\t<task class=\"com.zving.framework.FrameworkTask\" />\n");
		sb.append("\t\t<task class=\"com.zving.cms.datachannel.DeployTask\" />\n");
		sb.append("\t\t<task class=\"com.zving.cms.datachannel.PublishTask\" />\n");
		sb.append("\t\t<task class=\"com.zving.cms.stat.StatUpdateTask\" />\n");
		sb.append("\t\t<task class=\"com.zving.cms.dataservice.ADUpdating\" />\n");
		sb.append("\t\t<task class=\"com.zving.cms.datachannel.ArticleArchiveTask\" />\n");
		sb.append("\t\t<task class=\"com.zving.cms.datachannel.ArticleCancelTopTask\" />\n");
		sb.append("\t</cron>\n");
		sb.append("\t<databases>\n");
		sb.append("\t\t<database name=\"Default\">\n");
		sb.append("\t\t\t<config name=\"Type\">" + dcc.DBType + "</config>\n");
		if (dcc.isJNDIPool) {
			sb.append("\t\t\t<config name=\"JNDIName\">" + dcc.JNDIName + "</config>\n");
		} else {
			sb
					.append("\t\t\t<config name=\"ServerAddress\">" + dcc.DBServerAddress
							+ "</config>\n");
			sb.append("\t\t\t<config name=\"Port\">" + dcc.DBPort + "</config>\n");
			sb.append("\t\t\t<config name=\"Name\">" + dcc.DBName + "</config>\n");
			sb.append("\t\t\t<config name=\"UserName\">" + dcc.DBUserName + "</config>\n");
			sb.append("\t\t\t<config name=\"Password\">$KEY"
					+ EncryptUtil.encrypt3DES(dcc.DBPassword, "27jrWz3sxrVbR+pnyg6j")
					+ "</config>\n");
			sb.append("\t\t\t<config name=\"MaxConnCount\">1000</config>\n");
			sb.append("\t\t\t<config name=\"InitConnCount\">0</config>\n");
			sb.append("\t\t\t<config name=\"TestTable\">ZDMaxNo</config>\n");
		}
		sb.append("\t\t</database>\n");
		sb.append("\t</databases>\n");
		sb.append("</framework>\n");
		FileUtil.writeText(Config.getContextRealPath() + "WEB-INF/classes/framework.xml", sb
				.toString(), "UTF-8");
	}

	public static void generateSQL(HttpServletRequest request, HttpServletResponse response) {
		String dbtype = request.getParameter("Type");
		String sql = new DBImport().getSQL(Config.getContextRealPath()
				+ "WEB-INF/data/backup/install.dat", dbtype);

		StringBuffer sb = new StringBuffer(sql);
		createIndexes(null, sb, dbtype);
		try {
			request.setCharacterEncoding(Constant.GlobalCharset);
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + dbtype + ".zip");

			OutputStream os = response.getOutputStream();
			ZipUtil.zipStream(new StringInputStream(sb.toString()), response.getOutputStream(),
					dbtype + ".sql");
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void createIndexes(DBConn conn, StringBuffer sbAll, String dbtype) {
		String[] arr = { "zcarticle(CatalogID)", "zcarticle(CatalogInnercode)",
				"zcarticle(SiteID)", "zcarticle(keyword)", "zcimage(CatalogID)",
				"zcimage(CatalogInnercode)", "zcimage(SiteID)", "zcvideo(CatalogID)",
				"zcvideo(CatalogInnercode)", "zcvideo(SiteID)", "zcaudio(CatalogID)",
				"zcaudio(CatalogInnercode)", "zcaudio(SiteID)", "zcattachment(CatalogID)",
				"zcattachment(CatalogInnercode)", "zcattachment(SiteID)",
				"zcarticlelog(articleID)", "zcarticlevisitlog(articleID)", "zccomment(relaid)",
				"zccomment(catalogid)", "zccatalog(SiteID)", "zccatalog(InnerCode)",
				"zcpageblock(SiteID)", "zcpageblock(CatalogID)", "zdprivilege(owner)",
				"zdform(siteid)", "zdcolumnrela(relaid)", "zdcolumnvalue(relaid)",
				"zwworkflowentry(workflowdefid)", "zwcurrentstep(entryid)",
				"zwcurrentstep(stepid)", "zwcurrentstep(actionid)", "zwcurrentstep(owner)" };
		DataAccess da = null;
		if (conn == null)
			da = new DataAccess();
		else {
			da = new DataAccess(conn);
		}
		for (int i = 0; i < arr.length; ++i) {
			String str = arr[i];
			int p1 = str.indexOf("(");
			if (p1 < 0) {
				continue;
			}
			String table = str.substring(0, p1);
			if (!(str.endsWith(")"))) {
				continue;
			}
			str = str.substring(p1 + 1, str.length() - 1);
			String[] cs = str.split(",");
			StringBuffer sb = new StringBuffer();
			String name = "idx" + i + "_" + table.substring(2);
			if ((dbtype.equals("ORACLE")) && (name.length() > 15)) {
				name = name.substring(0, 15);
			}

			sb.append("create index " + name + " on " + table + " (");
			boolean first = true;
			for (int j = 0; j < cs.length; ++j) {
				if (StringUtil.isEmpty(cs[j])) {
					continue;
				}
				if (!(first)) {
					sb.append(",");
				}
				sb.append(cs[j]);
				first = false;
			}
			sb.append(")");
			if (conn != null) {
				try {
					if (dbtype.equals("MYSQL")) {
						da.executeNoQuery(new QueryBuilder("alter table " + table + " drop index "
								+ name));
					}
					if (dbtype.equals("MSSQL")) {
						da.executeNoQuery(new QueryBuilder("drop index " + name + " on " + table));
					}
					da.executeNoQuery(new QueryBuilder("drop index " + name));
				} catch (SQLException localSQLException1) {
				}
				try {
					label678: da.executeNoQuery(new QueryBuilder(sb.toString()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				sbAll.append(sb);
				if (dbtype.equals("MSSQL")) {
					sbAll.append("\n");
					sbAll.append("go\n");
				} else {
					sbAll.append(";\n");
				}
			}
		}
	}

	public static void init(DBConn conn, RequestImpl r) {
		DataAccess da = new DataAccess(conn);
		try {
			if (StringUtil.isNotEmpty(Config.getContextPath())) {
				String path = Config.getContextPath() + "Services";
				String prefix = r.getScheme() + "://" + r.getServerName();
				if ((r.getScheme().equalsIgnoreCase("http")) && (r.getPort() != 80)) {
					prefix = prefix + ":" + r.getPort();
				}
				if ((r.getScheme().equalsIgnoreCase("https")) && (r.getPort() != 443)) {
					prefix = ":" + r.getPort();
				}
				path = prefix + path;
				da.executeNoQuery(new QueryBuilder(
						"update ZDConfig set Value=? where Type='ServicesContext'", path));
				Config.update();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.platform.pub.Install JD-Core Version: 0.5.3
 */