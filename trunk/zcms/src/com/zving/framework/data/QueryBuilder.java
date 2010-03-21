 package com.zving.framework.data;
 
 import java.sql.SQLException;
 import java.util.ArrayList;
 
 public class QueryBuilder
 {
   private ArrayList list = new ArrayList();
   private ArrayList batches;
   private String sql = null;
   private boolean batchMode;
 
   protected ArrayList getBatches()
   {
     return this.batches;
   }
 
   public QueryBuilder()
   {
   }
 
   public QueryBuilder(String sql)
   {
     setSQL(sql);
   }
 
   public QueryBuilder(String sql, int param) {
     setSQL(sql);
     add(param);
   }
 
   public QueryBuilder(String sql, long param) {
     setSQL(sql);
     add(param);
   }
 
   public QueryBuilder(String sql, Object param) {
     setSQL(sql);
     add(param);
   }
 
   public QueryBuilder(String sql, int param1, Object param2) {
     setSQL(sql);
     add(param1);
     add(param2);
   }
 
   public QueryBuilder(String sql, long param1, Object param2) {
     setSQL(sql);
     add(param1);
     add(param2);
   }
 
   public QueryBuilder(String sql, Object param1, int param2) {
     setSQL(sql);
     add(param1);
     add(param2);
   }
 
   public QueryBuilder(String sql, Object param1, long param2) {
     setSQL(sql);
     add(param1);
     add(param2);
   }
 
   public QueryBuilder(String sql, int param1, int param2) {
     setSQL(sql);
     add(param1);
     add(param2);
   }
 
   public QueryBuilder(String sql, long param1, long param2) {
     setSQL(sql);
     add(param1);
     add(param2);
   }
 
   public QueryBuilder(String sql, Object param1, Object param2) {
     setSQL(sql);
     add(param1);
     add(param2);
   }
 
   public boolean isBatchMode()
   {
     return this.batchMode;
   }
 
   public void setBatchMode(boolean batchMode) {
     if ((batchMode) && (this.batches == null)) {
       this.batches = new ArrayList();
     }
     this.batchMode = batchMode;
   }
 
   public void addBatch()
   {
     if (!(this.batchMode)) {
       throw new RuntimeException("非批处理模式下不能使用addBatch()");
     }
     this.batches.add(this.list);
     this.list = new ArrayList();
   }
 
   public void add(int param) {
     this.list.add(new Integer(param));
   }
 
   public void add(long param) {
     this.list.add(new Long(param));
   }
 
   public void add(Object param) {
     this.list.add(param);
   }
 
   public void set(int index, int param) {
     this.list.set(index, new Integer(param));
   }
 
   public void set(int index, long param) {
     this.list.set(index, new Long(param));
   }
 
   public void set(int index, Object param) {
     this.list.set(index, param);
   }
 
   public void setSQL(String sql) {
     this.sql = sql;
   }
 
   public void appendSQLPart(String sqlTail) {
     this.sql += sqlTail;
   }
 
   public DataTable executeDataTable() {
     DataAccess da = new DataAccess();
     DataTable dt = null;
     try {
       dt = da.executeDataTable(this);
     } catch (Throwable e) {
       throw new RuntimeException(e);
     } finally {
       try {
         da.close();
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return dt;
   }
 
   public DataTable executePagedDataTable(int pageSize, int pageIndex) {
     DataAccess da = new DataAccess();
     DataTable dt = null;
     try {
       dt = da.executePagedDataTable(this, pageSize, pageIndex);
     } catch (Throwable e) {
       throw new RuntimeException(e);
     } finally {
       try {
         da.close();
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return dt;
   }
 
   public Object executeOneValue() {
     DataAccess da = new DataAccess();
     Object t = null;
     try {
       t = da.executeOneValue(this);
     } catch (Throwable e) {
       throw new RuntimeException(e);
     } finally {
       try {
         da.close();
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return t;
   }
 
   public String executeString() {
     Object o = executeOneValue();
     if (o == null) {
       return null;
     }
     return o.toString();
   }
 
   public int executeInt()
   {
     Object o = executeOneValue();
     if (o == null) {
       return 0;
     }
     return Integer.parseInt(o.toString());
   }
 
   public long executeLong()
   {
     Object o = executeOneValue();
     if (o == null) {
       return 0L;
     }
     return Long.parseLong(o.toString());
   }
 
   public int executeNoQuery()
   {
     DataAccess da = new DataAccess();
     int t = -1;
     try {
       t = da.executeNoQuery(this);
     } catch (Throwable e) {
       throw new RuntimeException(e);
     } finally {
       try {
         da.close();
       } catch (SQLException e) {
         e.printStackTrace();
       }
     }
     return t;
   }
 
   public String getSQL() {
     return this.sql;
   }
 
   public ArrayList getParams() {
     return this.list;
   }
 
   public void setParams(ArrayList list) {
     this.list = list;
   }
 
   public void clearBatches() {
     if (this.batchMode) {
       if (this.batches != null) {
         this.batches.clear();
       }
       this.batches = new ArrayList();
     }
   }
 
   public boolean checkParams() {
     char[] arr = this.sql.toCharArray();
     boolean StringCharFlag = false;
     int count = 0;
     for (int i = 0; i < arr.length; ++i) {
       char c = arr[i];
       if (c == '\'') {
         if (!(StringCharFlag))
           StringCharFlag = true;
         else
           StringCharFlag = false;
       } else {
         if ((c != '?') || 
           (StringCharFlag)) continue;
         ++count;
       }
     }
 
     if (count != this.list.size()) {
       throw new RuntimeException("SQL中含有" + count + "个参数，但有" + this.list.size() + "个参数置值!");
     }
     return true;
   }
 
   public String toString() {
     StringBuffer sb = new StringBuffer();
     sb.append(this.sql);
     sb.append("\t{");
     for (int i = 0; i < this.list.size(); ++i) {
       if (i != 0) {
         sb.append(",");
       }
       Object o = this.list.get(i);
       if (o == null) {
         sb.append("null");
       }
       else {
         String str = this.list.get(i).toString();
         if (str.length() > 40) {
           str = str.substring(0, 37);
           sb.append(str);
           sb.append("...");
         } else {
           sb.append(str); }
       }
     }
     sb.append("}");
     return sb.toString();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.data.QueryBuilder
 * JD-Core Version:    0.5.3
 */