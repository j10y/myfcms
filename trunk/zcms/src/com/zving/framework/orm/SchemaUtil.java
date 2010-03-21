 package com.zving.framework.orm;
 
 import com.zving.framework.data.DBConn;
 import com.zving.framework.data.DataAccess;
 import com.zving.framework.data.LobUtil;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.SQLException;
 import java.sql.Timestamp;
 
 public class SchemaUtil
 {
   private static long BackupNoBase = System.currentTimeMillis();
 
   public static boolean deleteByCondition(Schema conditionSchema)
   {
     return deleteByCondition(conditionSchema, new DataAccess(), 0);
   }
 
   public static boolean delete(Schema conditionSchema, DataAccess dAccess)
   {
     return deleteByCondition(conditionSchema, dAccess, 1);
   }
 
   public static boolean deleteByCondition(Schema conditionSchema, DataAccess dAccess, int bConnFlag)
   {
     SchemaColumn[] columns = conditionSchema.Columns;
     boolean firstFlag = true;
     StringBuffer sb = new StringBuffer(128);
     sb.append("delete from " + conditionSchema.TableCode);
     for (int i = 0; i < columns.length; ++i) {
       SchemaColumn sc = columns[i];
       if (!(conditionSchema.isNull(sc))) {
         if (firstFlag) {
           sb.append(" where ");
           sb.append(sc.getColumnName());
           sb.append("=?");
           firstFlag = false;
         } else {
           sb.append(" and ");
           sb.append(sc.getColumnName());
           sb.append("=?");
         }
       }
     }
     Connection conn = dAccess.getConnection();
     PreparedStatement pstmt = null;
     try {
       pstmt = conn.prepareStatement(sb.toString(), 1003, 1007);
       int i = 0; for (int j = 0; i < columns.length; ++i) {
         SchemaColumn sc = columns[i];
         Object v = conditionSchema.getV(sc.getColumnOrder());
         if (v != null) {
           if (sc.getColumnType() == 0)
             pstmt.setDate(j + 1, new java.sql.Date(((java.util.Date)v).getTime()));
           else {
             pstmt.setObject(j + 1, v);
           }
           ++j;
         }
       }
       pstmt.executeUpdate();
     } catch (Exception e) {
       e.printStackTrace();
       return false;
     } finally {
       if (pstmt != null) {
         try {
           pstmt.close();
         } catch (SQLException e) {
           e.printStackTrace();
         }
         pstmt = null;
       }
       if (bConnFlag == 0) {
         conn = null;
         try {
           dAccess.close();
         } catch (SQLException e) {
           e.printStackTrace();
         }
       }
     }
     return true;
   }
 
   public static boolean copyFieldValue(Schema srcSchema, Schema destSchema)
   {
     try
     {
       SchemaColumn[] srcSC = srcSchema.Columns;
       SchemaColumn[] destSC = destSchema.Columns;
       for (int i = 0; i < srcSC.length; ++i)
         for (int j = 0; j < destSC.length; ++j)
           if (srcSC[i].getColumnName().equals(destSC[j].getColumnName())) {
             int order = destSC[j].getColumnOrder();
             Object v = srcSchema.getV(srcSC[i].getColumnOrder());
             if (v instanceof java.util.Date) {
               destSchema.setV(order, ((java.util.Date)v).clone());
             }
             if (v instanceof Double) {
               destSchema.setV(order, new Double(((Double)v).doubleValue()));
             }
             if (v instanceof Float) {
               destSchema.setV(order, new Float(((Float)v).floatValue()));
             }
             if (v instanceof Integer) {
               destSchema.setV(order, new Integer(((Integer)v).intValue()));
             }
             if (v instanceof Long) {
               destSchema.setV(order, new Long(((Long)v).longValue()));
             }
             if (v instanceof byte[]) {
               destSchema.setV(order, ((byte[])v).clone());
             }
             if (!(v instanceof String)) break;
             destSchema.setV(order, v);
 
             break;
           }
     }
     catch (Exception e)
     {
       e.printStackTrace();
     }
     return true;
   }
 
   public static Schema getZSchemaFromBSchema(Schema bSchema)
   {
     String TableCode = bSchema.TableCode;
     if (!(TableCode.startsWith("BZ")))
       throw new RuntimeException("必须传入B表的Schema");
     try
     {
       Class c = Class.forName(bSchema.NameSpace + "." + TableCode.substring(1) + "Schema");
       Schema schema = (Schema)c.newInstance();
       for (int i = 0; i < schema.Columns.length; ++i) {
         schema.setV(i, bSchema.getV(i));
       }
       return schema;
     } catch (Exception e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static synchronized String getBackupNo()
   {
     return String.valueOf(BackupNoBase++).substring(1);
   }
 
   public static void setParam(SchemaColumn sc, PreparedStatement pstmt, DBConn conn, int i, Object v) throws SQLException
   {
     if (v == null) {
       pstmt.setNull(i + 1, 12);
     }
     else if (sc.getColumnType() == 0)
       pstmt.setTimestamp(i + 1, new Timestamp(((java.util.Date)v).getTime()));
     else if (sc.getColumnType() == 10)
       LobUtil.setClob(conn, pstmt, i + 1, v);
     else if (sc.getColumnType() == 2)
       LobUtil.setBlob(conn, pstmt, i + 1, (byte[])v);
     else
       pstmt.setObject(i + 1, v);
   }
 
   public static String getTableCode(Schema schema)
   {
     return schema.TableCode;
   }
 
   public static String getNameSpace(Schema schema) {
     return schema.NameSpace;
   }
 
   public static SchemaColumn[] getColumns(Schema schema) {
     return schema.Columns;
   }
 
   public static String getTableCode(SchemaSet set) {
     return set.TableCode;
   }
 
   public static String getNameSpace(SchemaSet set) {
     return set.NameSpace;
   }
 
   public static SchemaColumn[] getColumns(SchemaSet set) {
     return set.Columns;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.orm.SchemaUtil
 * JD-Core Version:    0.5.3
 */