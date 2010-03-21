 package com.zving.framework.data;
 
 import com.zving.framework.Config;
 import com.zving.framework.Constant;
 import com.zving.framework.utility.LogUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.sql.Connection;
 import java.sql.DriverManager;
 import java.sql.SQLException;
 import java.sql.Statement;
 import java.util.Hashtable;
 import java.util.Properties;
 import javax.naming.Context;
 import javax.naming.InitialContext;
 import javax.sql.DataSource;
 import org.apache.commons.logging.Log;
 
 public class DBConnPoolImpl
 {
   private DBConnConfig dcc;
   protected DBConn[] conns;
 
   public DBConnPoolImpl(String poolName)
   {
     this.dcc = new DBConnConfig();
     this.dcc.PoolName = poolName;
   }
 
   public DBConnPoolImpl(DBConnConfig config) {
     this.dcc = config;
     if (DBConnPool.getPoolMap().get(this.dcc.PoolName + ".") != null) {
       throw new RuntimeException("连接池序列中己存在名为" + this.dcc.PoolName + "的连接池，不能覆盖！");
     }
     DBConnPool.getPoolMap().put(this.dcc.PoolName + ".", this);
     if (!(config.isJNDIPool)) {
       this.conns = new DBConn[this.dcc.MaxConnCount];
       try {
         for (int i = 0; i < this.dcc.InitConnCount; ++i) {
           this.conns[i] = createConnection(this.dcc, false);
         }
         this.dcc.ConnCount = this.dcc.InitConnCount;
       } catch (Exception e) {
         LogUtil.getLogger().warn(this.dcc.PoolName + ",创建连接失败");
         e.printStackTrace();
       }
     }
   }
 
   public DBConn[] getDBConns() {
     return this.conns;
   }
 
   public void clear() {
     if (this.conns == null) {
       return;
     }
     for (int i = 0; i < this.conns.length; ++i) {
       try {
         this.conns[i].Conn.close();
         this.conns[i] = null;
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     this.dcc.ConnCount = 0;
   }
 
   public String getDBType() {
     return this.dcc.DBType;
   }
 
   public DBConnConfig getDBConnConfig() {
     return this.dcc;
   }
 
   public void init() {
     if (this.dcc.DBType != null) {
       return;
     }
     this.dcc.DBType = Config.getValue("Database." + this.dcc.PoolName + "Type");
     this.dcc.JNDIName = Config.getValue("Database." + this.dcc.PoolName + "JNDIName");
     if (StringUtil.isNotEmpty(this.dcc.JNDIName)) {
       this.dcc.isJNDIPool = true;
     } else {
       this.dcc.DBServerAddress = Config.getValue("Database." + this.dcc.PoolName + "ServerAddress");
       this.dcc.DBName = Config.getValue("Database." + this.dcc.PoolName + "Name");
       this.dcc.DBUserName = Config.getValue("Database." + this.dcc.PoolName + "UserName");
       this.dcc.DBPassword = Config.getValue("Database." + this.dcc.PoolName + "Password");
       this.dcc.TestTable = Config.getValue("Database." + this.dcc.PoolName + "TestTable");
       if ((this.dcc.DBType == null) || (this.dcc.DBType.equals(""))) {
         throw new RuntimeException("缺少配置项DB.Type");
       }
       if ((this.dcc.DBServerAddress == null) || (this.dcc.DBServerAddress.equals(""))) {
         throw new RuntimeException("缺少配置项DB.ServerAddress");
       }
       if ((this.dcc.DBName == null) || (this.dcc.DBName.equals(""))) {
         throw new RuntimeException("缺少配置项DB.Name");
       }
       if ((this.dcc.DBUserName == null) || (this.dcc.DBUserName.equals(""))) {
         throw new RuntimeException("缺少配置项DB.UserName");
       }
       if ((this.dcc.DBPassword == null) || (this.dcc.DBPassword.equals(""))) {
         throw new RuntimeException("缺少配置项DB.Password");
       }
       String s = Config.getValue("Database." + this.dcc.PoolName + "InitConnCount");
       try
       {
         this.dcc.InitConnCount = Integer.parseInt(s);
       } catch (NumberFormatException e) {
         this.dcc.InitConnCount = 0;
         LogUtil.getLogger().warn("配置项DB.InitConnCount错误," + s + "不是有效的整数，该配置项将采用默认值0!");
       }
 
       s = Config.getValue("Database." + this.dcc.PoolName + "MaxConnCount");
       try {
         this.dcc.MaxConnCount = Integer.parseInt(s);
       } catch (NumberFormatException e) {
         this.dcc.MaxConnCount = 20;
         LogUtil.getLogger().warn("配置项DB.MaxConnCount错误," + s + "不是有效的整数,该配置项将采用默认值20!");
       }
       s = Config.getValue("Database." + this.dcc.PoolName + "Port");
       try {
         this.dcc.DBPort = Integer.parseInt(s);
       } catch (NumberFormatException e) {
         this.dcc.DBPort = getDefaultPort();
         LogUtil.getLogger().warn("配置项DB.Port错误," + s + "不是有效的整数，该配置项将采用默认值!");
       }
       this.conns = new DBConn[this.dcc.MaxConnCount];
       try {
         for (int i = 0; i < this.dcc.InitConnCount; ++i) {
           this.conns[i] = createConnection(this.dcc, false);
         }
         this.dcc.ConnCount = this.dcc.InitConnCount;
       } catch (Exception e) {
         LogUtil.getLogger().warn(this.dcc.PoolName + ",创建连接失败");
         e.printStackTrace();
       }
     }
   }
 
   public DBConn getConnection() {
     return getConnection(false);
   }
 
   public DBConn getConnection(boolean bLongTimeFlag) {
     if (this.dcc.DBType == null) {
       init();
     }
     if (this.dcc.isJNDIPool) {
       return getJNDIPoolConnection(this.dcc);
     }
     long now = System.currentTimeMillis();
     DBConn conn = null;
     synchronized (this) {
       for (int i = 0; i < this.dcc.ConnCount; ++i) {
         conn = this.conns[i];
         if (conn.isUsing) {
           if (!(conn.LongTimeFlag)) {
             if (now - conn.LastSuccessExecuteTime > this.dcc.MaxConnUsingTime) {
               LogUtil.getLogger().error(this.dcc.PoolName + ":检测到连接使用超时，程序可能存在连接池泄漏，将自动关闭连接。以下是调用堆栈:");
               LogUtil.getLogger().error(conn.CallerString);
               try {
                 if (!(conn.Conn.getAutoCommit())) {
                   conn.Conn.rollback();
                 }
                 conn.Conn.close();
               } catch (SQLException e) {
                 e.printStackTrace(); }
             }
           } else {
             if ((now - conn.LastSuccessExecuteTime <= 4 * this.dcc.MaxConnUsingTime) || 
               (now - conn.LastWarnTime <= 300000L)) continue;
             LogUtil.getLogger()
               .warn(
               this.dcc.PoolName + ":检测到连接长时间使用，共使用了" + (now - conn.LastSuccessExecuteTime) + 
               "毫秒。以下是调用堆栈:");
             LogUtil.getLogger().warn(conn.CallerString);
             conn.LastWarnTime = now;
           }
         } else {
           conn.LongTimeFlag = bLongTimeFlag;
           conn.isUsing = true;
           conn.LastApplyTime = now;
           setCaller(conn);
 
           if (System.currentTimeMillis() - conn.getLastSuccessExecuteTime() > this.dcc.RefershPeriod) {
             DataAccess dAccess = new DataAccess(conn);
             try {
               dAccess.executeOneValue(new QueryBuilder("select 1 from " + this.dcc.TestTable + " where 1=2"));
             } catch (Exception e) {
               LogUtil.getLogger().warn(this.dcc.PoolName + ":发现连接失效，重新连接");
               try {
                 conn.Conn.close();
               } catch (SQLException localSQLException1) {
               }
               try {
                 conn.Conn = createConnection(this.dcc, bLongTimeFlag).Conn;
               } catch (Exception e1) {
                 e1.printStackTrace();
               }
             }
           }
           return conn;
         }
       }
       if (this.dcc.ConnCount < this.dcc.MaxConnCount) {
         try {
           conn = createConnection(this.dcc, bLongTimeFlag);
           this.conns[this.dcc.ConnCount] = conn;
           this.dcc.ConnCount += 1;
           LogUtil.info(this.dcc.PoolName + ":创建新连接，总数：" + this.dcc.ConnCount);
           setCaller(conn);
           return conn;
         } catch (Exception e) {
           e.printStackTrace();
           throw new RuntimeException("DBConnPoolImpl," + this.dcc.PoolName + ":创建连接失败!");
         }
       }
 
       throw new RuntimeException("DBConnPoolImpl," + this.dcc.PoolName + ":所有连接都在使用，无法分配连接!");
     }
   }
 
   public static DBConn getJNDIPoolConnection(DBConnConfig dbcc)
   {
     try {
       Context ctx = new InitialContext();
       Connection conn = null;
       DataSource ds;
       if (Config.isTomcat()) {
         ctx = (Context)ctx.lookup("java:comp/env");
         ds = (DataSource)ctx.lookup(dbcc.JNDIName);
         conn = ds.getConnection();
       } else if (Config.isJboss()) {
         Hashtable environment = new Hashtable();
         environment.put("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");
         environment.put("java.naming.factory.url.pkgs", "org.jboss.naming.client ");
         environment.put("java.naming.provider.url", "jnp://127.0.0.1:1099");
         ctx = new InitialContext(environment);
         DataSource ds = (DataSource)ctx.lookup("java:" + dbcc.JNDIName);
         conn = ds.getConnection();
       } else {
         ds = (DataSource)ctx.lookup(dbcc.JNDIName);
         conn = ds.getConnection();
       }
       Statement stmt;
       if (dbcc.DBType.equalsIgnoreCase("ORACLE")) {
         stmt = conn.createStatement(1005, 1008);
         stmt.execute("alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS'");
         stmt.close();
       } else if (dbcc.DBType.equalsIgnoreCase("MYSQL")) {
         stmt = conn.createStatement(1005, 1008);
         stmt.execute("SET NAMES '" + Constant.GlobalCharset.replaceAll("\\-", "").toLowerCase() + "'");
         stmt.close();
       }
       DBConn dbconn = new DBConn();
       dbconn.Conn = conn;
       dbconn.DBType = dbcc.DBType;
       dbconn.PoolName = dbcc.PoolName;
       dbconn.isJNDIPool = dbcc.isJNDIPool;
       return dbconn;
     } catch (Exception e) {
       e.printStackTrace();
       LogUtil.warn("查找JNDI连接池失败!" + e.getMessage());
     }
     return null;
   }
 
   public static DBConn createConnection(DBConnConfig dbcc, boolean bLongTimeFlag) throws Exception {
     Connection conn = null;
     if (dbcc.isJNDIPool) {
       return getJNDIPoolConnection(dbcc);
     }
 
     if (dbcc.DBType.equalsIgnoreCase("ORACLE")) {
       Class.forName("oracle.jdbc.driver.OracleDriver");
       Properties props = new Properties();
       props.setProperty("user", dbcc.DBUserName);
       props.setProperty("password", dbcc.DBPassword);
       props.setProperty("oracle.jdbc.V8Compatible", "true");
 
       conn = DriverManager.getConnection(getJdbcUrl(dbcc), props);
       Statement stmt = conn.createStatement(1005, 1008);
       stmt.execute("alter session set nls_date_format = 'YYYY-MM-DD HH24:MI:SS'");
       stmt.close();
     } else if (dbcc.DBType.equalsIgnoreCase("INFORMIX")) {
       Class.forName("com.informix.jdbc.IfxDriver");
       conn = DriverManager.getConnection(getJdbcUrl(dbcc));
     } else if (dbcc.DBType.equalsIgnoreCase("MSSQL")) {
       Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
       conn = DriverManager.getConnection(getJdbcUrl(dbcc), dbcc.DBUserName, dbcc.DBPassword);
     } else if (dbcc.DBType.equalsIgnoreCase("DB2")) {
       Class.forName("com.ibm.db2.jcc.DB2Driver");
       conn = DriverManager.getConnection(getJdbcUrl(dbcc), dbcc.DBUserName, dbcc.DBPassword);
     } else if (dbcc.DBType.equalsIgnoreCase("SYBASE")) {
       Class.forName("com.sybase.jdbc2.jdbc.SybDriver");
       conn = DriverManager.getConnection(getJdbcUrl(dbcc), dbcc.DBUserName, dbcc.DBPassword);
     } else if (dbcc.DBType.equalsIgnoreCase("MYSQL")) {
       Class.forName("com.mysql.jdbc.Driver");
       conn = DriverManager.getConnection(getJdbcUrl(dbcc), dbcc.DBUserName, dbcc.DBPassword);
       Statement stmt = conn.createStatement(1005, 1008);
       stmt.execute("SET NAMES '" + Constant.GlobalCharset.replaceAll("\\-", "").toLowerCase() + "'");
       stmt.close();
     } else if (dbcc.DBType.equalsIgnoreCase("MSSQL2000")) {
       Class.forName("net.sourceforge.jtds.jdbc.Driver");
       conn = DriverManager.getConnection(getJdbcUrl(dbcc), dbcc.DBUserName, dbcc.DBPassword);
     } else {
       LogUtil.getLogger().error("目前暂不支持此种类型的数据库!");
     }
 
     DBConn dbconn = new DBConn();
     dbconn.Conn = conn;
     dbconn.isUsing = true;
     dbconn.LongTimeFlag = bLongTimeFlag;
     dbconn.LastApplyTime = System.currentTimeMillis();
     dbconn.DBType = dbcc.DBType;
     dbconn.PoolName = dbcc.PoolName;
     return dbconn;
   }
 
   public static String getJdbcUrl(DBConnConfig dcc) {
     StringBuffer sUrl = new StringBuffer(128);
     if (dcc.DBType.trim().toUpperCase().equals("ORACLE")) {
       sUrl.append("jdbc:oracle:thin:@");
       sUrl.append(dcc.DBServerAddress);
       sUrl.append(":");
       sUrl.append(dcc.DBPort);
       sUrl.append(":");
       sUrl.append(dcc.DBName);
     }
     if (dcc.DBType.trim().toUpperCase().equals("INFORMIX")) {
       sUrl.append("jdbc:informix-sqli://");
       sUrl.append(dcc.DBServerAddress);
       sUrl.append(":");
       sUrl.append(dcc.DBPort);
       sUrl.append(dcc.DBName);
       sUrl.append(":");
       sUrl.append("informixserver=");
       sUrl.append(dcc.DBName);
       sUrl.append(";");
       sUrl.append("user=");
       sUrl.append(dcc.DBUserName);
       sUrl.append(";");
       sUrl.append("dcc.DBPassword=");
       sUrl.append(dcc.DBPassword);
       sUrl.append(";");
     }
     if (dcc.DBType.trim().toUpperCase().equals("MSSQL"))
     {
       sUrl.append("jdbc:sqlserver://");
       sUrl.append(dcc.DBServerAddress);
       sUrl.append(":");
       sUrl.append(dcc.DBPort);
       sUrl.append(";DatabaseName=");
       sUrl.append(dcc.DBName);
     }
     if (dcc.DBType.trim().toUpperCase().equals("MSSQL2000"))
     {
       sUrl.append("jdbc:jtds:sqlserver://");
       sUrl.append(dcc.DBServerAddress);
       sUrl.append(":");
       sUrl.append(dcc.DBPort);
       sUrl.append(";DatabaseName=");
       sUrl.append(dcc.DBName);
       sUrl.append(";useLOBs=false");
     }
     if (dcc.DBType.trim().toUpperCase().equals("DB2")) {
       sUrl.append("jdbc:db2://");
       sUrl.append(dcc.DBServerAddress);
       sUrl.append(":");
       sUrl.append(dcc.DBPort);
       sUrl.append("/");
       sUrl.append(dcc.DBName);
     }
     if (dcc.DBType.trim().toUpperCase().equals("SYBASE")) {
       sUrl.append("jdbc:sybase:Tds:");
       sUrl.append(dcc.DBServerAddress);
       sUrl.append(":");
       sUrl.append(dcc.DBPort);
       sUrl.append("/");
       sUrl.append(dcc.DBName);
     }
     if (dcc.DBType.trim().toUpperCase().equals("MYSQL")) {
       sUrl.append("jdbc:mysql://");
       sUrl.append(dcc.DBServerAddress);
       sUrl.append(":");
       sUrl.append(dcc.DBPort);
       sUrl.append("/");
       sUrl.append(dcc.DBName);
     }
     return sUrl.toString();
   }
 
   private void setCaller(DBConn conn) {
     StackTraceElement[] stack = new Throwable().getStackTrace();
     StringBuffer sb = new StringBuffer();
     for (int i = 0; i < stack.length; ++i) {
       StackTraceElement ste = stack[i];
       if (ste.getClassName().indexOf("DBConnPoolImpl") == -1) {
         sb.append("\t");
         sb.append(ste.getClassName());
         sb.append(".");
         sb.append(ste.getMethodName());
         sb.append("(),行号:");
         sb.append(ste.getLineNumber());
         sb.append("\n");
       }
     }
     conn.CallerString = sb.toString();
   }
 
   private int getDefaultPort() {
     if (this.dcc.DBType.equals("MSSQL")) {
       return 1433;
     }
     if (this.dcc.DBType.equals("ORACLE")) {
       return 1521;
     }
     if (this.dcc.DBType.equals("DB2")) {
       return 50000;
     }
     if (this.dcc.DBType.equals("MYSQL")) {
       return 3306;
     }
     return 0;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.data.DBConnPoolImpl
 * JD-Core Version:    0.5.3
 */