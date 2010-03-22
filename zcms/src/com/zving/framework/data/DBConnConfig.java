 package com.zving.framework.data;
 
 public class DBConnConfig
 {
   public static final String ORACLE = "ORACLE";
   public static final String DB2 = "DB2";
   public static final String MYSQL = "MYSQL";
   public static final String MSSQL = "MSSQL";
   public static final String MSSQL2000 = "MSSQL2000";
   public String JNDIName = null;
 
   public boolean isJNDIPool = false;
 
   public int MaxConnCount = 1000;
 
   public int InitConnCount = 0;
   public int ConnCount;
   public int MaxConnUsingTime = 120000;
 
   public int RefershPeriod = 60000;
   public String DBType;
   public String DBServerAddress;
   public int DBPort;
   public String DBName;
   public String DBUserName;
   public String DBPassword;
   public String TestTable;
   public String PoolName;
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.data.DBConnConfig
 * JD-Core Version:    0.5.3
 */