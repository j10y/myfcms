 package com.zving.framework.orm;
 
 import java.io.Serializable;
 
 public class SchemaColumn
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private int ColumnType;
   private String ColumnName;
   private int ColumnOrder;
   private int Length;
   private int Precision;
   private boolean Mandatory;
   private boolean isPrimaryKey;
 
   public SchemaColumn(String name, int type, int order, int length, int precision, boolean mandatory, boolean ispk)
   {
     this.ColumnType = type;
     this.ColumnName = name;
     this.ColumnOrder = order;
     this.Length = length;
     this.Precision = precision;
     this.Mandatory = mandatory;
     this.isPrimaryKey = ispk;
   }
 
   public String getColumnName() {
     return this.ColumnName;
   }
 
   public int getColumnOrder() {
     return this.ColumnOrder;
   }
 
   public int getColumnType() {
     return this.ColumnType;
   }
 
   public boolean isPrimaryKey() {
     return this.isPrimaryKey;
   }
 
   public int getLength() {
     return this.Length;
   }
 
   public int getPrecision() {
     return this.Precision;
   }
 
   public boolean isMandatory() {
     return this.Mandatory;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.orm.SchemaColumn
 * JD-Core Version:    0.5.3
 */