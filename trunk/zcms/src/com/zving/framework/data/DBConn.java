 package com.zving.framework.data;
 
 import com.zving.framework.Config;
 import com.zving.framework.data.nativejdbc.CommonsDbcpNativeJdbcExtractor;
 import com.zving.framework.data.nativejdbc.JBossNativeJdbcExtractor;
 import com.zving.framework.data.nativejdbc.WebLogicNativeJdbcExtractor;
 import com.zving.framework.data.nativejdbc.WebSphereNativeJdbcExtractor;
 import com.zving.framework.utility.Mapx;
 import java.sql.CallableStatement;
 import java.sql.Connection;
 import java.sql.DatabaseMetaData;
 import java.sql.PreparedStatement;
 import java.sql.SQLException;
 import java.sql.SQLWarning;
 import java.sql.Savepoint;
 import java.sql.Statement;
 import java.util.Map;
 import org.apache.commons.lang.ArrayUtils;
 
 public class DBConn
   implements Connection
 {
   protected boolean LongTimeFlag = false;
   protected Connection Conn;
   protected long LastApplyTime;
   protected boolean isUsing = false;
 
   protected String CallerString = null;
   protected long LastWarnTime;
   protected long LastSuccessExecuteTime = System.currentTimeMillis();
   protected String PoolName;
   protected String DBType;
   public boolean isJNDIPool = false;
 
   public Connection getPhysicalConnection()
   {
     if (this.isJNDIPool) {
       try {
         if (Config.isTomcat())
           return CommonsDbcpNativeJdbcExtractor.doGetNativeConnection(this.Conn);
         if (Config.isWeblogic())
           return WebLogicNativeJdbcExtractor.doGetNativeConnection(this.Conn);
         if (Config.isWebSphere()) {
           return WebSphereNativeJdbcExtractor.doGetNativeConnection(this.Conn);
         }
         return JBossNativeJdbcExtractor.doGetNativeConnection(this.Conn);
       }
       catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return this.Conn;
   }
 
   public int getHoldability()
     throws SQLException
   {
     return this.Conn.getHoldability();
   }
 
   public int getTransactionIsolation()
     throws SQLException
   {
     return this.Conn.getTransactionIsolation();
   }
 
   public void clearWarnings()
     throws SQLException
   {
     this.Conn.clearWarnings();
   }
 
   public void close()
     throws SQLException
   {
     this.isUsing = false;
     this.LastApplyTime = 0L;
     if (this.isJNDIPool)
       this.Conn.close();
   }
 
   public void closeReally() throws SQLException
   {
     DBConnPoolImpl pool = (DBConnPoolImpl)DBConnPool.PoolMap.get(this.PoolName);
     if (pool != null) {
       ArrayUtils.removeElement(pool.conns, this);
       this.Conn.close();
     }
   }
 
   public void commit()
     throws SQLException
   {
     this.Conn.commit();
   }
 
   public void rollback()
     throws SQLException
   {
     this.Conn.rollback();
   }
 
   public boolean getAutoCommit()
     throws SQLException
   {
     return this.Conn.getAutoCommit();
   }
 
   public boolean isClosed()
     throws SQLException
   {
     return this.Conn.isClosed();
   }
 
   public boolean isReadOnly()
     throws SQLException
   {
     return this.Conn.isReadOnly();
   }
 
   public void setHoldability(int holdability)
     throws SQLException
   {
     this.Conn.setHoldability(holdability);
   }
 
   public void setTransactionIsolation(int level)
     throws SQLException
   {
     this.Conn.setTransactionIsolation(level);
   }
 
   public void setAutoCommit(boolean autoCommit)
     throws SQLException
   {
     this.Conn.setAutoCommit(autoCommit);
   }
 
   public void setReadOnly(boolean readOnly)
     throws SQLException
   {
     this.Conn.setReadOnly(readOnly);
   }
 
   public String getCatalog()
     throws SQLException
   {
     return this.Conn.getCatalog();
   }
 
   public void setCatalog(String catalog)
     throws SQLException
   {
     this.Conn.setCatalog(catalog);
   }
 
   public DatabaseMetaData getMetaData()
     throws SQLException
   {
     return this.Conn.getMetaData();
   }
 
   public SQLWarning getWarnings()
     throws SQLException
   {
     return this.Conn.getWarnings();
   }
 
   public Savepoint setSavepoint()
     throws SQLException
   {
     return this.Conn.setSavepoint();
   }
 
   public void releaseSavepoint(Savepoint savepoint)
     throws SQLException
   {
     this.Conn.releaseSavepoint(savepoint);
   }
 
   public void rollback(Savepoint savepoint)
     throws SQLException
   {
     this.Conn.rollback(savepoint);
   }
 
   public Statement createStatement()
     throws SQLException
   {
     return this.Conn.createStatement();
   }
 
   public Statement createStatement(int resultSetType, int resultSetConcurrency)
     throws SQLException
   {
     return this.Conn.createStatement(resultSetType, resultSetConcurrency);
   }
 
   public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
     throws SQLException
   {
     return this.Conn.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
   }
 
   public Map getTypeMap()
     throws SQLException
   {
     return this.Conn.getTypeMap();
   }
 
   public void setTypeMap(Map map)
     throws SQLException
   {
     this.Conn.setTypeMap(map);
   }
 
   public String nativeSQL(String sql)
     throws SQLException
   {
     return this.Conn.nativeSQL(sql);
   }
 
   public CallableStatement prepareCall(String sql)
     throws SQLException
   {
     return this.Conn.prepareCall(sql);
   }
 
   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
     throws SQLException
   {
     return this.Conn.prepareCall(sql, resultSetType, resultSetConcurrency);
   }
 
   public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
     throws SQLException
   {
     return this.Conn.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
   }
 
   public PreparedStatement prepareStatement(String sql)
     throws SQLException
   {
     return this.Conn.prepareStatement(sql);
   }
 
   public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
     throws SQLException
   {
     return this.Conn.prepareStatement(sql, autoGeneratedKeys);
   }
 
   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
     throws SQLException
   {
     return this.Conn.prepareStatement(sql, resultSetType, resultSetConcurrency);
   }
 
   public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
     throws SQLException
   {
     return this.Conn.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
   }
 
   public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
     throws SQLException
   {
     return this.Conn.prepareStatement(sql, columnIndexes);
   }
 
   public Savepoint setSavepoint(String name)
     throws SQLException
   {
     return this.Conn.setSavepoint(name);
   }
 
   public PreparedStatement prepareStatement(String sql, String[] columnNames)
     throws SQLException
   {
     return this.Conn.prepareStatement(sql, columnNames);
   }
 
   public long getLastSuccessExecuteTime() {
     return this.LastSuccessExecuteTime;
   }
 
   public void setLastSuccessExecuteTime(long lastSuccessExecuteTime) {
     this.LastSuccessExecuteTime = lastSuccessExecuteTime;
   }
 
   public String getDBType() {
     return this.DBType;
   }
 
   public void setDBType(String type) {
     this.DBType = type;
   }
 
   public String getPoolName() {
     return this.PoolName;
   }
 
   public void setPoolName(String poolName) {
     this.PoolName = poolName;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.data.DBConn
 * JD-Core Version:    0.5.3
 */