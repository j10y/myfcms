package com.zving.framework;

import com.zving.framework.data.DBConnConfig;
import com.zving.framework.data.DBConnPool;
import com.zving.framework.data.DataTable;
import com.zving.framework.data.QueryBuilder;
import com.zving.framework.license.IProduct;
import com.zving.framework.security.EncryptUtil;
import com.zving.framework.utility.LogUtil;
import com.zving.framework.utility.Mapx;
import com.zving.framework.utility.StringUtil;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Config {
	private static String configFileName;
	protected static Mapx configMap = new Mapx();
	private static long LastUpdateTime;
	private static long RefershPeriod = 60000L;

	public static int OnlineUserCount = 0;

	public static int LoginUserCount = 0;
	public static boolean isDatabaseConfiged;
	public static boolean isAllowLogin = true;

	private static String AppCode = null;

	private static String AppName = null;

	private static float MainVersion = 1.0F;

	private static float MinorVersion = 0.0F;

	private static boolean ComplexDepolyMode = false;
	public static int ServletMajorVersion;
	public static int ServletMinorVersion;

	public static void readConfigFileName(String fileName) {
		configFileName = fileName;
		init();
	}

	protected static void init() {
		if (System.currentTimeMillis() - LastUpdateTime > RefershPeriod) {
			synchronized (Config.class) {
				if (System.currentTimeMillis() - LastUpdateTime > RefershPeriod) {
					String path = getContextRealPath();
					configMap.put("App.ContextRealPath", path);
					configFileName = getRealConfigFileName();
					File f = new File(configFileName);
					if (!(f.exists())) {
						LogUtil.warn("配置文件" + configFileName + "未找到!");
						isDatabaseConfiged = false;
					}

					configMap.put("System.JavaVersion", System.getProperty("java.version"));
					configMap.put("System.JavaVendor", System.getProperty("java.vendor"));
					configMap.put("System.JavaHome", System.getProperty("java.home"));
					configMap.put("System.OSPatchLevel", System.getProperty("sun.os.patch.level"));
					configMap.put("System.OSArch", System.getProperty("os.arch"));
					configMap.put("System.OSVersion", System.getProperty("os.version"));
					configMap.put("System.OSName", System.getProperty("os.name"));
					configMap.put("System.OSUserLanguage", System.getProperty("user.language"));
					configMap.put("System.OSUserName", System.getProperty("user.name"));
					configMap.put("System.LineSeparator", System.getProperty("line.separator"));
					configMap.put("System.FileSeparator", System.getProperty("file.separator"));
					configMap.put("System.FileEncoding", System.getProperty("file.encoding"));

					loadConfig();
					ComplexDepolyMode = "true".equals(configMap.get("App.ComplexDeployMode"));

					if (LastUpdateTime == 0L) {
						LogUtil.info("----" + getAppCode() + "(" + getAppName()
								+ "): Config Initialized----");
					}
					LastUpdateTime = System.currentTimeMillis();
				}
			}
			loadDBConfig();
		}
	}

	public static void loadConfig() {
		SAXReader reader = new SAXReader(false);
		try {
			configFileName = getRealConfigFileName();
			File f = new File(configFileName);
			if (!(f.exists())) {
				LogUtil.warn("配置文件" + configFileName + "未找到!");
				isDatabaseConfiged = false;
				return;
			}
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			Element application = root.element("application");
			List elements = application.elements();
			for (int i = 0; i < elements.size(); ++i) {
				Element ele = (Element) elements.get(i);
				configMap.put("App." + ele.attributeValue("name"), ele.getTextTrim());
			}
			Element databases = root.element("databases");
			if (databases != null) {
				List dbs = databases.elements();
				for (int i = 0; i < dbs.size(); ++i) {
					Element ele = (Element) dbs.get(i);
					String dbname = ele.attributeValue("name").trim();
					List configs = ele.elements();
					for (int k = 0; k < configs.size(); ++k) {
						ele = (Element) configs.get(k);
						String attr = ele.attributeValue("name");
						String value = ele.getTextTrim();
						if ((attr.equalsIgnoreCase("Password")) && (value.startsWith("$KEY"))) {
							value = EncryptUtil.decrypt3DES(value.substring(4),
									"27jrWz3sxrVbR+pnyg6j");
						}

						configMap.put("Database." + dbname + "." + attr, value);
					}
				}
				isDatabaseConfiged = true;
				return;
			}
			isDatabaseConfiged = false;
		} catch (Exception e) {
			e.printStackTrace();
			isDatabaseConfiged = false;
		}
	}

	private static void loadDBConfig() {
		if (configMap.get("Database.Default.Type") != null
				&& "true".equals(configMap.get("App.ExistPlatformDB")))
			try {
				DataTable dt = (new QueryBuilder("select type,value from zdconfig"))
						.executeDataTable();
				for (int i = 0; dt != null && i < dt.getRowCount(); i++)
					configMap.put(dt.getString(i, 0), dt.getString(i, 1));

			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static void update() {
		loadConfig();
		loadDBConfig();
	}

	protected static String getConfigFileName() {
		return configFileName;
	}

	protected static void setConfigFileName(String fileName) {
		configFileName = fileName;
	}

	public static String getValue(String configName) {
		init();
		return ((String) configMap.get(configName));
	}

	public static void setValue(String configName, String configValue) {
		init();
		configMap.put(configName, configValue);
	}

	public static String getClassesPath() {
		URL url = Thread.currentThread().getContextClassLoader().getResource("license.dat");
		if (url == null)
			throw new RuntimeException("未找到license.dat");
		try {
			String path = URLDecoder.decode(url.getPath(), getFileEncode());
			if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
				if (path.startsWith("/"))
					path = path.substring(1);
				else if (path.startsWith("file:/")) {
					path = path.substring(6);
				}
			} else if (path.startsWith("file:/")) {
				path = path.substring(5);
			}

			return path.substring(0, path.lastIndexOf("/") + 1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getContextRealPath() {
		if (configMap != null) {
			String str = (String) configMap.get("App.ContextRealPath");
			if (str != null) {
				return str;
			}
		}
		String path = getClassesPath();
		int index = path.indexOf("WEB-INF");
		if (index > 0) {
			path = path.substring(0, index);
		}
		return path;
	}

	public static String getRealConfigFileName() {
		String path = getClassesPath();
		return getClassesPath() + "framework.xml";
	}

	public static String getContextPath() {
		if (ComplexDepolyMode) {
			String path = (String) User.getValue("App.ContextPath");
			if (StringUtil.isEmpty(path)) {
				path = getValue("App.ContextPath");
			}
			return path;
		}
		return getValue("App.ContextPath");
	}

	public static String getLogLevel() {
		return getValue("App.LogLevel");
	}

	private static void initProduct() {
		if (AppCode != null)
			return;
		try {
			IProduct p = (IProduct) Class.forName("com.zving.Product").newInstance();
			AppCode = p.getAppCode();
			AppName = p.getAppName();
			MainVersion = p.getMainVersion();
			MinorVersion = p.getMinorVersion();

			if (configMap.get("App.Code") != null) {
				AppCode = configMap.getString("App.Code");
				AppName = configMap.getString("App.Name");
			}
		} catch (Exception e) {
			AppCode = "ZPlatform";
			AppName = "泽元开发平台";
		}
	}

	public static String getAppCode() {
		initProduct();
		return AppCode;
	}

	public static String getAppName() {
		initProduct();
		return AppName;
	}

	public static float getMainVersion() {
		initProduct();
		return MainVersion;
	}

	public static float getMinorVersion() {
		initProduct();
		return MinorVersion;
	}

	public static boolean isDebugMode() {
		return "true".equalsIgnoreCase(getValue("App.DebugMode"));
	}

	public static String getJavaVersion() {
		return getValue("System.JavaVersion");
	}

	public static String getJavaVendor() {
		return getValue("System.JavaVendor");
	}

	public static String getJavaHome() {
		return getValue("System.JavaHome");
	}

	public static String getContainerInfo() {
		return getValue("System.ContainerInfo");
	}

	public static String getContainerVersion() {
		String str = getValue("System.ContainerInfo");
		if (str.indexOf("/") > 0) {
			return str.substring(str.lastIndexOf("/") + 1);
		}
		return "0";
	}

	public static String getOSName() {
		return getValue("System.OSName");
	}

	public static String getOSPatchLevel() {
		return getValue("System.OSPatchLevel");
	}

	public static String getOSArch() {
		return getValue("System.OSArch");
	}

	public static String getOSVersion() {
		return getValue("System.OSVersion");
	}

	public static String getOSUserLanguage() {
		return getValue("System.OSUserLanguage");
	}

	public static String getOSUserName() {
		return getValue("System.OSUserName");
	}

	public static String getLineSeparator() {
		return getValue("System.LineSeparator");
	}

	public static String getFileSeparator() {
		return getValue("System.FileSeparator");
	}

	public static String getFileEncode() {
		return System.getProperty("file.encoding");
	}

	public static int getLoginUserCount() {
		return LoginUserCount;
	}

	public static int getOnlineUserCount() {
		return OnlineUserCount;
	}

	public static boolean isDB2() {
		return DBConnPool.getDBConnConfig().DBType.equals("DB2");
	}

	public static boolean isOracle() {
		return DBConnPool.getDBConnConfig().DBType.equals("ORACLE");
	}

	public static boolean isMysql() {
		return DBConnPool.getDBConnConfig().DBType.equals("MYSQL");
	}

	public static boolean isSQLServer() {
		return DBConnPool.getDBConnConfig().DBType.equals("MSSQL");
	}

	public static boolean isTomcat() {
		if (StringUtil.isEmpty(getContainerInfo())) {
			getJBossInfo();
		}
		return (getContainerInfo().toLowerCase().indexOf("tomcat") >= 0);
	}

	protected static void getJBossInfo() {
		String jboss = System.getProperty("jboss.home.dir");
		if (!(StringUtil.isNotEmpty(jboss)))
			return;
		try {
			Class c = Class.forName("org.jboss.Version");
			Method m = c.getMethod("getInstance", null);
			Object o = m.invoke(null, null);
			m = c.getMethod("getMajor", null);
			Object major = m.invoke(o, null);
			m = c.getMethod("getMinor", null);
			Object minor = m.invoke(o, null);
			m = c.getMethod("getRevision", null);
			Object revision = m.invoke(o, null);
			m = c.getMethod("getTag", null);
			Object tag = m.invoke(o, null);
			configMap.put("System.ContainerInfo", "JBoss/" + major + "." + minor + "." + revision
					+ "." + tag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isJboss() {
		if (StringUtil.isEmpty(getContainerInfo())) {
			getJBossInfo();
		}
		return (getContainerInfo().toLowerCase().indexOf("jboss") >= 0);
	}

	public static boolean isWeblogic() {
		return (getContainerInfo().toLowerCase().indexOf("weblogic") >= 0);
	}

	public static boolean isWebSphere() {
		return (getContainerInfo().toLowerCase().indexOf("websphere") >= 0);
	}

	public static boolean isComplexDepolyMode() {
		return ComplexDepolyMode;
	}

	public static boolean isDebugLoglevel() {
		return "Debug".equalsIgnoreCase(getLogLevel());
	}
}

/*
 * Location: F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\ Qualified Name:
 * com.zving.framework.Config JD-Core Version: 0.5.3
 */