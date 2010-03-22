 package com.zving.framework.data;
 
 import java.io.Serializable;
 
 public class DataColumn
   implements Serializable, Cloneable
 {
   private static final long serialVersionUID = 1L;
   public static final int DATETIME = 0;
   public static final int STRING = 1;
   public static final int BLOB = 2;
   public static final int BIGDECIMAL = 3;
   public static final int DECIMAL = 4;
   public static final int FLOAT = 5;
   public static final int DOUBLE = 6;
   public static final int LONG = 7;
   public static final int INTEGER = 8;
   public static final int SMALLINT = 9;
   public static final int CLOB = 10;
   protected String ColumnName;
   protected int ColumnType;
   protected boolean isAllowNull = true;
 
   protected String dateFormat = null;
 
   public DataColumn()
   {
   }
 
   public Object clone() {
     return new DataColumn(this.ColumnName, this.ColumnType);
   }
 
   public DataColumn(String columnName, int columnType) {
     this.ColumnName = columnName;
     this.ColumnType = columnType;
   }
 
   public DataColumn(String columnName, int columnType, boolean allowNull) {
     this.ColumnName = columnName;
     this.ColumnType = columnType;
     this.isAllowNull = allowNull;
   }
 
   public String getColumnName() {
     return this.ColumnName;
   }
 
   public void setColumnName(String columnName) {
     this.ColumnName = columnName;
   }
 
   public int getColumnType() {
     return this.ColumnType;
   }
 
   public void setColumnType(int columnType) {
     this.ColumnType = columnType;
   }
 
   public boolean isAllowNull() {
     return this.isAllowNull;
   }
 
   public void setAllowNull(boolean isAllowNull) {
     this.isAllowNull = isAllowNull;
   }
 
   public static Class getDataType(int sqlType)
   {
     switch (sqlType)
     {
     case 2003:
     }
     return null;
   }
 
   public String getDateFormat() {
     return this.dateFormat;
   }
 
   public void setDateFormat(String dateFormat) {
     this.dateFormat = dateFormat;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.data.DataColumn
 * JD-Core Version:    0.5.3
 */