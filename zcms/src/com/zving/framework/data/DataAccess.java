 package com.zving.framework.data;
 
 import com.zving.framework.Config;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaSet;
 import com.zving.framework.utility.LogUtil;
 import java.sql.Blob;
 import java.sql.Clob;
 import java.sql.PreparedStatement;
 import java.sql.ResultSet;
 import java.sql.SQLException;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Date;
 
 public class DataAccess
 {
   private DBConn conn;
 
   public DataAccess()
   {
   }
 
   public DataAccess(DBConn conn)
   {
     this.conn = conn;
   }
 
   public DBConn getConnection() {
     if (this.conn == null) {
       this.conn = DBConnPool.getConnection();
     }
     return this.conn;
   }
 
   public void setAutoCommit(boolean bCommit) throws SQLException {
     if (this.conn == null) {
       this.conn = DBConnPool.getConnection();
     }
     this.conn.setAutoCommit(bCommit);
   }
 
   public void commit() throws SQLException {
     if (this.conn == null) {
       return;
     }
     this.conn.commit();
   }
 
   public void rollback() throws SQLException {
     if (this.conn == null) {
       return;
     }
     this.conn.rollback();
   }
 
   public void close() throws SQLException {
     if (this.conn == null) {
       return;
     }
     this.conn.close();
   }
 
   public static void setParams(PreparedStatement stmt, QueryBuilder qb, DBConn conn) throws SQLException
   {
     ArrayList batches = null;
     if (qb.isBatchMode()) {
       batches = qb.getBatches();
       for (int k = 0; k < batches.size(); ++k) {
         ArrayList list = (ArrayList)batches.get(k);
         for (int i = 1; i <= list.size(); ++i) {
           Object o = list.get(i - 1);
           if (o == null)
             stmt.setNull(i, 12);
           else if (o instanceof Byte)
             stmt.setByte(i, ((Byte)o).byteValue());
           else if (o instanceof Short)
             stmt.setShort(i, ((Short)o).shortValue());
           else if (o instanceof Integer)
             stmt.setInt(i, ((Integer)o).intValue());
           else if (o instanceof Long)
             stmt.setLong(i, ((Long)o).longValue());
           else if (o instanceof Float)
             stmt.setFloat(i, ((Float)o).floatValue());
           else if (o instanceof Double)
             stmt.setDouble(i, ((Double)o).doubleValue());
           else if (o instanceof Date)
             stmt.setTimestamp(i, new Timestamp(((Date)o).getTime()));
           else if (o instanceof String)
             stmt.setString(i, (String)o);
           else if (o instanceof Clob)
             LobUtil.setClob(conn, stmt, i, o);
           else if (o instanceof byte[])
             LobUtil.setBlob(conn, stmt, i, (byte[])o);
           else {
             stmt.setObject(i, o);
           }
         }
         stmt.addBatch();
       }
     } else {
       ArrayList list = qb.getParams();
       for (int i = 1; i <= list.size(); ++i) {
         Object o = list.get(i - 1);
         if (o == null)
           stmt.setNull(i, 12);
         else if (o instanceof Byte)
           stmt.setByte(i, ((Byte)o).byteValue());
         else if (o instanceof Short)
           stmt.setShort(i, ((Short)o).shortValue());
         else if (o instanceof Integer)
           stmt.setInt(i, ((Integer)o).intValue());
         else if (o instanceof Long)
           stmt.setLong(i, ((Long)o).longValue());
         else if (o instanceof Float)
           stmt.setFloat(i, ((Float)o).floatValue());
         else if (o instanceof Double)
           stmt.setDouble(i, ((Double)o).doubleValue());
         else if (o instanceof Date)
           stmt.setTimestamp(i, new Timestamp(((Date)o).getTime()));
         else if (o instanceof String)
           stmt.setString(i, (String)o);
         else if (o instanceof Clob)
           LobUtil.setClob(conn, stmt, i, o);
         else if (o instanceof byte[])
           LobUtil.setBlob(conn, stmt, i, (byte[])o);
         else
           stmt.setObject(i, o);
       }
     }
   }
 
   public DataTable executeDataTable(QueryBuilder qb) throws SQLException
   {
     if (this.conn == null) {
       this.conn = DBConnPool.getConnection();
     }
     if (Config.isDebugLoglevel()) {
       LogUtil.info(qb.toString());
     }
     PreparedStatement stmt = null;
     ResultSet rs = null;
     DataTable dt = null;
     try {
       stmt = this.conn.prepareStatement(qb.getSQL(), 1003, 1007);
       setParams(stmt, qb, this.conn);
       rs = stmt.executeQuery();
       dt = new DataTable(rs);
       this.conn.setLastSuccessExecuteTime(System.currentTimeMillis());
     } finally {
       try {
         if (rs != null) {
           rs.close();
           rs = null;
         }
         if (stmt != null) {
           stmt.close();
           stmt = null;
         }
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return dt;
   }
 
   public DataTable executePagedDataTable(QueryBuilder qb, int pageSize, int pageIndex)
     throws SQLException
   {
     if (this.conn == null) {
       this.conn = DBConnPool.getConnection();
     }
     if (pageSize < 1) {
       pageSize = 2147483647;
     }
     if (pageIndex < 0) {
       pageIndex = 0;
     }
     String pageSQL = getPagedSQL(this.conn, qb, pageSize, pageIndex);
     if (Config.isDebugLoglevel()) {
       LogUtil.info(pageSQL + " " + pageIndex + "," + pageSize);
     }
     PreparedStatement stmt = null;
     ResultSet rs = null;
     DataTable dt = null;
     try {
       stmt = this.conn.prepareStatement(pageSQL, 1003, 1007);
       setParams(stmt, qb, this.conn);
       rs = stmt.executeQuery();
       if (this.conn.getDBType().equals("MSSQL2000"))
         dt = new DataTable(rs, pageSize, pageIndex);
       else {
         dt = new DataTable(rs);
       }
       this.conn.setLastSuccessExecuteTime(System.currentTimeMillis());
     }
     finally {
       try {
         if (!(this.conn.getDBType().equals("MSSQL2000"))) {
           qb.getParams().remove(qb.getParams().size() - 1);
           qb.getParams().remove(qb.getParams().size() - 1);
         }
         if (rs != null) {
           rs.close();
           rs = null;
         }
         if (stmt != null) {
           stmt.close();
           stmt = null;
         }
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return dt;
   }
 
   public static String getPagedSQL(DBConn conn, QueryBuilder qb, int pageSize, int pageIndex) {
     StringBuffer sb = new StringBuffer();
     String DBType = conn.getDBType().toUpperCase();
     int start = pageIndex * pageSize;
     int end = (pageIndex + 1) * pageSize;
     if (DBType.equals("ORACLE")) {
       sb.append("select * from (select rs.*,rownum rnm from (");
       sb.append(qb.getSQL());
       sb.append(") rs where rownum <= ?) rss where rnm > ?");
       qb.add(end);
       qb.add(start);
     } else if (DBType.equals("DB2")) {
       sb.append("select * from (select rs.*,rownumber() OVER () rnm from (");
       sb.append(qb.getSQL());
       sb.append(") rs) rss WHERE rnm BETWEEN ? and ?");
       qb.add(start + 1);
       qb.add(end);
     } else if (DBType.equals("MSSQL")) {
       String sql = qb.getSQL();
       SelectSQLParser sp = new SelectSQLParser();
       try {
         sp.setSQL(sql);
         sp.parse();
       } catch (Exception e) {
         e.printStackTrace();
       }
       sb.append(sp.getMSSQLPagedSQL());
       qb.add(start + 1);
       qb.add(end);
     } else if (DBType.equals("MSSQL2000")) {
       sb.append(qb.getSQL());
     } else if (DBType.equals("MYSQL")) {
       sb.append(qb.getSQL());
       sb.append(" limit ?,?");
       qb.add(start);
       qb.add(pageSize);
     }
     return sb.toString();
   }
 
   public static int getCount(String dbType, QueryBuilder qb) {
     QueryBuilder cqb = new QueryBuilder();
     cqb.setParams((ArrayList)qb.getParams().clone());
     String sql = qb.getSQL().toLowerCase();
     int index1 = sql.lastIndexOf(")");
     int index2 = sql.lastIndexOf("order by");
     if (index2 > index1) {
       sql = sql.substring(0, index2);
     }
     cqb.setSQL("select count(1) from (" + sql + ") t1");
     return cqb.executeInt();
   }
 
   public Object executeOneValue(QueryBuilder qb) throws SQLException {
     if (this.conn == null) {
       this.conn = DBConnPool.getConnection();
     }
     if (Config.isDebugLoglevel()) {
       LogUtil.info(qb.toString());
     }
     PreparedStatement stmt = null;
     ResultSet rs = null;
     Object t = null;
     try {
       stmt = this.conn.prepareStatement(qb.getSQL(), 1003, 1007);
       setParams(stmt, qb, this.conn);
       rs = stmt.executeQuery();
       if (rs.next()) {
         t = rs.getObject(1);
         if (t instanceof Clob) {
           t = LobUtil.clobToString((Clob)t);
         }
         if (t instanceof Blob) {
           t = LobUtil.blobToBytes((Blob)t);
         }
       }
       this.conn.setLastSuccessExecuteTime(System.currentTimeMillis());
     } finally {
       try {
         if (rs != null) {
           rs.close();
           rs = null;
         }
         if (stmt != null) {
           stmt.close();
           stmt = null;
         }
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return t;
   }
 
   public int executeNoQuery(QueryBuilder qb) throws SQLException {
     if (this.conn == null) {
       this.conn = DBConnPool.getConnection();
     }
     if (Config.isDebugLoglevel()) {
       LogUtil.info(qb.toString());
     }
     PreparedStatement stmt = null;
     int t = -1;
     try {
       stmt = this.conn.prepareStatement(qb.getSQL(), 1003, 1007);
       setParams(stmt, qb, this.conn);
       if (qb.isBatchMode())
         stmt.executeBatch();
       else {
         t = stmt.executeUpdate();
       }
       this.conn.setLastSuccessExecuteTime(System.currentTimeMillis());
     } finally {
       try {
         if (stmt != null) {
           stmt.close();
           stmt = null;
         }
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return t;
   }
 
   public void insert(Schema schema) {
     schema.setDataAccess(this);
     schema.insert();
   }
 
   public void update(Schema schema) {
     schema.setDataAccess(this);
     schema.update();
   }
 
   public void delete(Schema schema) {
     schema.setDataAccess(this);
     schema.delete();
   }
 
   public void deleteAndBackup(Schema schema) {
     schema.setDataAccess(this);
     schema.deleteAndBackup();
   }
 
   public void deleteAndInsert(Schema schema) {
     schema.setDataAccess(this);
     schema.deleteAndInsert();
   }
 
   public void insert(SchemaSet set) {
     set.setDataAccess(this);
     set.insert();
   }
 
   public void update(SchemaSet set) {
     set.setDataAccess(this);
     set.update();
   }
 
   public void delete(SchemaSet set) {
     set.setDataAccess(this);
     set.delete();
   }
 
   public void deleteAndBackup(SchemaSet set) {
     set.setDataAccess(this);
     set.deleteAndBackup();
   }
 
   public void deleteAndInsert(SchemaSet set) {
     set.setDataAccess(this);
     set.deleteAndInsert();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.data.DataAccess
 * JD-Core Version:    0.5.3
 */