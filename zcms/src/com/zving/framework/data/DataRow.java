 package com.zving.framework.data;
 
 import com.zving.framework.utility.CaseIgnoreMapx;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.LogUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.io.Serializable;
 import java.util.Date;
 import java.util.HashMap;
 import org.apache.commons.lang.ArrayUtils;
 import org.apache.commons.logging.Log;
 
 public class DataRow
   implements Serializable
 {
   private static final long serialVersionUID = 1L;
   private boolean isWebMode;
   protected DataColumn[] columns;
   protected Object[] values;
 
   public DataRow(DataColumn[] types, Object[] values)
   {
     this.columns = types;
     this.values = values;
   }
 
   public Object get(int index) {
     if (this.values == null) {
       return null;
     }
     if ((index < 0) || (index >= this.columns.length)) {
       throw new RuntimeException("DataRow中没有指定的列：" + index);
     }
     return this.values[index];
   }
 
   public Object get(String columnName) {
     if ((columnName == null) || (columnName.equals(""))) {
       throw new RuntimeException("不能存取列名为空的列");
     }
     for (int i = 0; i < this.columns.length; ++i) {
       if (this.columns[i].getColumnName().equalsIgnoreCase(columnName)) {
         return this.values[i];
       }
     }
     LogUtil.getLogger().warn("指定的列名没有找到:" + columnName);
     throw new RuntimeException("指定的列名没有找到:" + columnName);
   }
 
   public String getString(int index) {
     if (this.values[index] != null) {
       if ((!("".equals(this.values[index]))) && (this.columns[index].getColumnType() == 0)) {
         if (StringUtil.isNotEmpty(this.columns[index].getDateFormat())) {
           return DateUtil.toString((Date)this.values[index], this.columns[index].getDateFormat());
         }
         return DateUtil.toString((Date)this.values[index], "yyyy-MM-dd HH:mm:ss");
       }
 
       String t = String.valueOf(this.values[index]).trim();
       if ((this.isWebMode) && ((
         (t == null) || (t.equals(""))))) {
         return "&nbsp;";
       }
 
       return t;
     }
     if (this.isWebMode) {
       return "&nbsp;";
     }
     return "";
   }
 
   public String getString(String columnName)
   {
     if ((columnName == null) || (columnName.equals(""))) {
       throw new RuntimeException("不能存取列名为空的列");
     }
     for (int i = 0; i < this.columns.length; ++i) {
       if (this.columns[i].getColumnName().equalsIgnoreCase(columnName)) {
         return getString(i);
       }
     }
     return "";
   }
 
   public Date getDate(int index) {
     Object obj = get(index);
     if (obj == null) {
       return null;
     }
     if (obj instanceof Date) {
       return ((Date)obj);
     }
     return DateUtil.parseDateTime(obj.toString());
   }
 
   public Date getDate(String columnName)
   {
     if ((columnName == null) || (columnName.equals(""))) {
       throw new RuntimeException("不能存取列名为空的列");
     }
     for (int i = 0; i < this.columns.length; ++i) {
       if (this.columns[i].getColumnName().equalsIgnoreCase(columnName)) {
         return getDate(i);
       }
     }
     return null;
   }
 
   public double getDouble(int index) {
     Object obj = get(index);
     if (obj == null) {
       return 0.0D;
     }
     if (obj instanceof Number) {
       return ((Number)obj).doubleValue();
     }
     String str = obj.toString();
     if (StringUtil.isEmpty(str)) {
       return 0.0D;
     }
     return Double.parseDouble(str);
   }
 
   public double getDouble(String columnName)
   {
     if ((columnName == null) || (columnName.equals(""))) {
       throw new RuntimeException("不能存取列名为空的列");
     }
     for (int i = 0; i < this.columns.length; ++i) {
       if (this.columns[i].getColumnName().equalsIgnoreCase(columnName)) {
         return getDouble(i);
       }
     }
     return 0.0D;
   }
 
   public long getLong(int index) {
     Object obj = get(index);
     if (obj == null) {
       return 0L;
     }
     if (obj instanceof Number) {
       return ((Number)obj).longValue();
     }
     String str = obj.toString();
     if (StringUtil.isEmpty(str)) {
       return 0L;
     }
     return Long.parseLong(str);
   }
 
   public long getLong(String columnName)
   {
     if ((columnName == null) || (columnName.equals(""))) {
       throw new RuntimeException("不能存取列名为空的列");
     }
     for (int i = 0; i < this.columns.length; ++i) {
       if (this.columns[i].getColumnName().equalsIgnoreCase(columnName)) {
         return getLong(i);
       }
     }
     return 0L;
   }
 
   public int getInt(int index) {
     Object obj = get(index);
     if (obj == null) {
       return 0;
     }
     if (obj instanceof Number) {
       return ((Number)obj).intValue();
     }
     String str = obj.toString();
     if (StringUtil.isEmpty(str)) {
       return 0;
     }
     return Integer.parseInt(str);
   }
 
   public int getInt(String columnName)
   {
     if ((columnName == null) || (columnName.equals(""))) {
       throw new RuntimeException("不能存取列名为空的列");
     }
     for (int i = 0; i < this.columns.length; ++i) {
       if (this.columns[i].getColumnName().equalsIgnoreCase(columnName)) {
         return getInt(i);
       }
     }
     return 0;
   }
 
   public boolean isNull(int index) {
     return (get(index) == null);
   }
 
   public boolean isNull(String columnName) {
     return (get(columnName) == null);
   }
 
   public void set(int index, Object value) {
     if (this.values == null) {
       return;
     }
     if ((index < 0) || (index >= this.values.length)) {
       throw new RuntimeException("DataRow中没有指定的列：" + index);
     }
     this.values[index] = value;
   }
 
   public void set(String columnName, Object value) {
     if ((columnName == null) || (columnName.equals(""))) {
       throw new RuntimeException("不能存取列名为空的列");
     }
     for (int i = 0; i < this.values.length; ++i) {
       if (this.columns[i].getColumnName().equalsIgnoreCase(columnName)) {
         this.values[i] = value;
         return;
       }
     }
     throw new RuntimeException("指定的列名没有找到：" + columnName);
   }
 
   public void set(int index, int value)
   {
     set(index, new Integer(value));
   }
 
   public void set(String columnName, int value) {
     set(columnName, new Integer(value));
   }
 
   public void set(String columnName, long value) {
     set(columnName, new Long(value));
   }
 
   public void set(int index, long value) {
     set(index, new Long(value));
   }
 
   public void set(String columnName, double value) {
     set(columnName, new Double(value));
   }
 
   public void set(int index, double value) {
     set(index, new Double(value));
   }
 
   public DataColumn getDataColumn(int index) {
     if ((index < 0) || (index >= this.columns.length)) {
       throw new RuntimeException("DataRow中没有指定的列：" + index);
     }
     return this.columns[index];
   }
 
   public DataColumn getDataColumn(String columnName) {
     if ((columnName == null) || (columnName.equals(""))) {
       throw new RuntimeException("不能存取列名为空的列");
     }
     for (int i = 0; i < this.columns.length; ++i) {
       if (this.columns[i].getColumnName().equalsIgnoreCase(columnName)) {
         return this.columns[i];
       }
     }
     throw new RuntimeException("指定的列名没有找到");
   }
 
   public int getColumnCount() {
     return this.columns.length;
   }
 
   public Object[] getDataValues() {
     return this.values;
   }
 
   public DataColumn[] getDataColumns() {
     return this.columns;
   }
 
   public void insertColumn(String columnName, Object columnValue) {
     insertColumn(new DataColumn(columnName, 1), columnValue);
   }
 
   public void insertColumn(String columnName, Object columnValue, int index) {
     insertColumn(new DataColumn(columnName, 1), columnValue, index);
   }
 
   public void insertColumn(DataColumn dc, Object columnValue) {
     insertColumn(dc, columnValue, this.values.length);
   }
 
   public void insertColumn(DataColumn dc, Object columnValue, int index) {
     if ((index < 0) || (index > this.columns.length)) {
       throw new RuntimeException(index + "超出范围，最大允许值为" + this.columns.length + "!");
     }
     this.columns = ((DataColumn[])ArrayUtils.add(this.columns, index, dc));
     this.values = ArrayUtils.add(this.values, index, columnValue);
   }
 
   public boolean isWebMode() {
     return this.isWebMode;
   }
 
   public void setWebMode(boolean isWebMode) {
     this.isWebMode = isWebMode;
   }
 
   public Mapx toMapx() {
     Mapx map = new Mapx();
     for (int i = 0; i < this.columns.length; ++i) {
       map.put(this.columns[i].getColumnName(), this.values[i]);
     }
     return map;
   }
 
   public CaseIgnoreMapx toCaseIgnoreMapx() {
     CaseIgnoreMapx map = new CaseIgnoreMapx(new HashMap());
     for (int i = 0; i < this.columns.length; ++i) {
       map.put(this.columns[i].getColumnName(), this.values[i]);
     }
     return map;
   }
 
   public void fill(Mapx map) {
     if (map == null) {
       return;
     }
     Object[] ks = map.keyArray();
     Object[] vs = map.valueArray();
     for (int i = 0; i < map.size(); ++i) {
       Object key = ks[i];
       if (key == null) {
         continue;
       }
       for (int j = 0; j < this.columns.length; ++j)
         if (key.toString().equalsIgnoreCase(this.columns[j].getColumnName())) {
           Object v = vs[i];
           if ((this.columns[j].ColumnType == 0) && (!(Date.class.isInstance(v)))) {
             throw new RuntimeException("数组的第" + j + "个元素必须是Date对象!");
           }
           set(j, v);
         }
     }
   }
 
   public void fill(Object[] values)
   {
     if (values == null) {
       return;
     }
     if (values.length != getColumnCount()) {
       throw new RuntimeException("执行fill操作数组长度为" + values.length + "，要求的长度为" + getColumnCount() + "！");
     }
     for (int i = 0; i < values.length; ++i) {
       if ((this.columns[i].ColumnType == 0) && (!(Date.class.isInstance(values[i])))) {
         throw new RuntimeException("第" + i + "列必须是Date对象!");
       }
       set(i, values[i]);
     }
   }
 
   public String toString() {
     StringBuffer sb = new StringBuffer();
     for (int i = 0; i < this.columns.length; ++i) {
       if (i != 0) {
         sb.append(",");
       }
       sb.append(this.columns[i].getColumnName());
       sb.append(":");
       sb.append(this.values[i]);
     }
     return sb.toString();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.data.DataRow
 * JD-Core Version:    0.5.3
 */