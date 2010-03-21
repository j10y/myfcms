 package com.zving.framework.data;
 
 import java.io.IOException;
 import java.io.OutputStream;
 import java.io.Reader;
 import java.io.StringWriter;
 import java.io.Writer;
 import java.lang.reflect.Method;
 import java.sql.Blob;
 import java.sql.Clob;
 import java.sql.Connection;
 import java.sql.PreparedStatement;
 import java.sql.SQLException;
 
 public class LobUtil
 {
   public static String clobToString(Clob clob)
   {
     if (clob == null)
       return null; Reader r;
     StringWriter sw;
     char[] cs;
     try { r = clob.getCharacterStream();
       sw = new StringWriter();
       cs = new char[(int)clob.length()]; } catch (SQLException e) {
       try {
         r.read(cs);
         sw.write(cs);
         return sw.toString();
       } catch (IOException e) {
         e.printStackTrace(); break label62:
 
         e = e;
 
         e.printStackTrace(); }
     }
     label62: return null;
   }
 
   public static byte[] blobToBytes(Blob blob) {
     if (blob == null)
       return null;
     try
     {
       return blob.getBytes(1L, (int)blob.length());
     } catch (Exception e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static void setClob(DBConn conn, PreparedStatement ps, int i, Object v) throws SQLException {
     if (conn.getDBType().equals("ORACLE")) {
       Class clobClass = null;
       try {
         clobClass = Class.forName("oracle.sql.CLOB");
       } catch (ClassNotFoundException e2) {
         e2.printStackTrace();
         return;
       }
       Object clob = null;
       Object oc = conn.getPhysicalConnection();
       try {
         Method m = clobClass.getMethod("createTemporary", new Class[] { Connection.class, 
           Boolean.TYPE, Integer.TYPE });
         clob = m.invoke(null, new Object[] { oc, new Boolean(true), new Integer(1) });
 
         m = clobClass.getMethod("open", new Class[] { Integer.TYPE });
         m.invoke(clob, new Object[] { new Integer(1) });
 
         m = clobClass.getMethod("setCharacterStream", new Class[] { Long.TYPE });
         Writer writer = (Writer)m.invoke(clob, new Object[] { new Long(0L) });
 
         writer.write(String.valueOf(v));
         writer.close();
 
         clobClass.getMethod("close", null).invoke(clob, null);
         ps.setClob(i, (Clob)clob);
       } catch (Exception e) {
         try {
           if (clob != null) {
             Method m = clobClass.getMethod("freeTemporary", new Class[] { clobClass });
             m.invoke(null, new Object[] { clob });
           }
         } catch (Exception e1) {
           e1.printStackTrace();
         }
         e.printStackTrace();
       }
     } else {
       ps.setObject(i, v);
     }
   }
 
   public static void setBlob(DBConn conn, PreparedStatement ps, int i, byte[] v) throws SQLException {
     if (conn.getDBType().equals("ORACLE")) {
       Class blobClass = null;
       try {
         blobClass = Class.forName("oracle.sql.BLOB");
       } catch (ClassNotFoundException e2) {
         e2.printStackTrace();
         return;
       }
       Object blob = null;
       Object oc = conn.getPhysicalConnection();
       try {
         Method m = blobClass.getMethod("createTemporary", new Class[] { Connection.class, 
           Boolean.TYPE, Integer.TYPE });
         blob = m.invoke(null, new Object[] { oc, new Boolean(true), new Integer(1) });
 
         m = blobClass.getMethod("open", new Class[] { Integer.TYPE });
         m.invoke(blob, new Object[] { new Integer(1) });
 
         m = blobClass.getMethod("getBinaryOutputStream", new Class[] { Long.TYPE });
         OutputStream out = (OutputStream)m.invoke(blob, new Object[] { new Long(0L) });
         out.write(v);
         out.close();
 
         blobClass.getMethod("close", null).invoke(blob, null);
         ps.setBlob(i, (Blob)blob);
       } catch (Exception e) {
         try {
           if (blob != null) {
             Method m = blobClass.getMethod("freeTemporary", new Class[] { blobClass });
             m.invoke(null, new Object[] { blob });
           }
         } catch (Exception e1) {
           e1.printStackTrace();
         }
         e.printStackTrace();
       }
     } else {
       ps.setObject(i, v);
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.data.LobUtil
 * JD-Core Version:    0.5.3
 */