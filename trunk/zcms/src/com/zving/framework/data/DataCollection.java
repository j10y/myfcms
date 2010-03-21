 package com.zving.framework.data;
 
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaSet;
 import com.zving.framework.orm.SchemaUtil;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.io.StringReader;
 import java.util.List;
 import org.dom4j.Document;
 import org.dom4j.DocumentHelper;
 import org.dom4j.Element;
 import org.dom4j.io.SAXReader;
 import org.xml.sax.InputSource;
 
 public class DataCollection extends Mapx
 {
   private static final long serialVersionUID = 1L;
 
   public void put(String id, int number)
   {
     put(id, new Integer(number));
   }
 
   public void put(String id, long number) {
     put(id, new Long(number));
   }
 
   public void put(String id, double number) {
     put(id, new Double(number));
   }
 
   public void put(String id, float number) {
     put(id, new Float(number));
   }
 
   public Object get(String id) {
     return super.get(id);
   }
 
   public String[] getStringArray(String id) {
     Object o = super.get(id);
     if ((o != null) && (o instanceof String[])) {
       try {
         return ((String[])o);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return null;
   }
 
   public int[] getIntArray(String id) {
     Object o = super.get(id);
     if ((o != null) && (o instanceof int[])) {
       try {
         return ((int[])o);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return null;
   }
 
   public long[] getLongArray(String id) {
     Object o = super.get(id);
     if ((o != null) && (o instanceof long[])) {
       try {
         return ((long[])o);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return null;
   }
 
   public float[] getFloatArray(String id) {
     Object o = super.get(id);
     if ((o != null) && (o instanceof float[])) {
       try {
         return ((float[])o);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return null;
   }
 
   public double[] getDoubleArray(String id) {
     Object o = super.get(id);
     if ((o != null) && (o instanceof double[])) {
       try {
         return ((double[])o);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     return null;
   }
 
   public int getInt(String id) {
     Object o = super.get(id);
     if (Integer.class.isInstance(o)) {
       return ((Integer)o).intValue();
     }
     throw new RuntimeException("DataSource:getInt返回的不是int，id=" + id);
   }
 
   public long getLong(String id) {
     Object o = super.get(id);
     if (Long.class.isInstance(o)) {
       return ((Long)o).longValue();
     }
     throw new RuntimeException("DataSource:getLong返回的不是long，id=" + id);
   }
 
   public double getDouble(String id) {
     Object o = super.get(id);
     if (Double.class.isInstance(o)) {
       return ((Double)o).doubleValue();
     }
     throw new RuntimeException("DataSource:getDouble返回的不是double，id=" + id);
   }
 
   public float getFloat(String id) {
     Object o = super.get(id);
     if (Float.class.isInstance(o)) {
       return ((Float)o).floatValue();
     }
     throw new RuntimeException("DataSource:getFloat返回的不是float，id=" + id);
   }
 
   public String getString(String id) {
     Object o = super.get(id);
     if (String.class.isInstance(o)) {
       return ((String)o);
     }
     return null;
   }
 
   public DataTable getDataTable(String id) {
     Object o = super.get(id);
     if (DataTable.class.isInstance(o)) {
       return ((DataTable)o);
     }
     return null;
   }
 
   public Schema getSchema(String id) {
     Object o = super.get(id);
     if (Schema.class.isInstance(o)) {
       return ((Schema)o);
     }
     return null;
   }
 
   public SchemaSet getSchemaSet(String id) {
     Object o = super.get(id);
     if (SchemaSet.class.isInstance(o)) {
       return ((SchemaSet)o);
     }
     return null;
   }
 
   public String toXML() {
     Document doc = DocumentHelper.createDocument();
     doc.setXMLEncoding("UTF-8");
     Element root = doc.addElement("collection");
     Object[] ks = keyArray();
     Object[] vs = valueArray();
     for (int i = 0; i < size(); ++i) {
       String id = (String)ks[i];
       Object value = vs[i];
       Element ele = root.addElement("element");
       ele.addAttribute("ID", id);
       if ((value == null) || (value.equals(""))) {
         ele.addAttribute("Type", "String");
         ele.addCDATA("_ZVING_NULL");
       }
       else if (value instanceof String) {
         ele.addAttribute("Type", "String");
         ele.addCDATA((String)value);
       } else if (value instanceof Integer) {
         ele.addAttribute("Type", "Int");
         ele.addAttribute("Value", String.valueOf(value));
       } else if (value instanceof Long) {
         ele.addAttribute("Type", "Long");
         ele.addAttribute("Value", String.valueOf(value));
       } else if (value instanceof Float) {
         ele.addAttribute("Type", "Float");
         ele.addAttribute("Value", String.valueOf(value));
       } else if (value instanceof Double) {
         ele.addAttribute("Type", "Double");
         ele.addAttribute("Value", String.valueOf(value));
       }
       else
       {
         StringBuffer sb;
         int j;
         if (value instanceof int[]) {
           int[] t = (int[])value;
           sb = new StringBuffer();
           sb.append(t[0]);
           for (j = 1; j < t.length; ++j) {
             sb.append(",");
             sb.append(t[j]);
           }
           ele.addAttribute("Type", "IntArray");
           ele.addAttribute("Value", sb.toString());
         } else if (value instanceof long[]) {
           long[] t = (long[])value;
           sb = new StringBuffer();
           sb.append(t[0]);
           for (j = 1; j < t.length; ++j) {
             sb.append(",");
             sb.append(t[j]);
           }
           ele.addAttribute("Type", "LongArray");
           ele.addAttribute("Value", sb.toString());
         } else if (value instanceof float[]) {
           float[] t = (float[])value;
           sb = new StringBuffer();
           sb.append(t[0]);
           for (j = 1; j < t.length; ++j) {
             sb.append(",");
             sb.append(t[j]);
           }
           ele.addAttribute("Type", "FloatArray");
           ele.addAttribute("Value", sb.toString());
         } else if (value instanceof double[]) {
           double[] t = (double[])value;
           sb = new StringBuffer();
           sb.append(t[0]);
           for (j = 1; j < t.length; ++j) {
             sb.append(",");
             sb.append(t[j]);
           }
           ele.addAttribute("Type", "DoubleArray");
           ele.addAttribute("Value", sb.toString());
         } else if (value instanceof String[]) {
           String[] t = (String[])value;
           sb = new StringBuffer();
           for (j = 0; j < t.length; ++j) {
             if (j != 0) {
               sb.append(",");
             }
             sb.append("\"");
             if (t[j] == null) {
               t[j] = "_ZVING_NULL";
             }
             sb.append(StringUtil.javaEncode(t[j]));
             sb.append("\"");
           }
           ele.addAttribute("Type", "StringArray");
           ele.addCDATA(sb.toString());
         } else if (value instanceof DataTable) {
           dataTableToXML((DataTable)value, ele);
         } else if (value instanceof Schema) {
           schemaToXML((Schema)value, ele);
         } else if (value instanceof SchemaSet) {
           schemaSetToXML((SchemaSet)value, ele);
         } else if (value instanceof Mapx) {
           mapToXML((Mapx)value, ele); }
       }
     }
     return doc.asXML();
   }
 
   private void dataTableToXML(DataTable dt, Element ele) {
     translatDataTableToXML(dt, ele, "DataTable");
   }
 
   private void translatDataTableToXML(DataTable dt, Element ele, String type) {
     if (dt == null) {
       throw new RuntimeException("DataCollection:生成XML时发生错误，传入的DataTable为空!");
     }
     ele.addAttribute("Type", type);
     DataColumn[] dcs = dt.getDataColumns();
     Element cols = ele.addElement("columns");
     StringBuffer sb = new StringBuffer();
     sb.append("[");
     for (int i = 0; i < dcs.length; ++i) {
       if (i != 0) {
         sb.append(",");
       }
       sb.append("[\"");
       sb.append(dcs[i].getColumnName());
       sb.append("\"");
       sb.append(",");
       sb.append(dcs[i].getColumnType());
       sb.append("]");
     }
     sb.append("]");
     cols.addCDATA(sb.toString());
     sb = new StringBuffer();
     sb.append("[");
     for (i = 0; i < dt.getRowCount(); ++i) {
       sb.append("[");
       for (int j = 0; j < dcs.length; ++j) {
         String v = dt.getString(i, j);
         if (j == 0) {
           if (v == null) {
             continue;
           }
           sb.append("\"" + StringUtil.javaEncode(v) + "\"");
         }
         else if (v == null) {
           sb.append(",");
         }
         else {
           sb.append(",\"" + StringUtil.javaEncode(v) + "\"");
         }
       }
       if (i == dt.getRowCount() - 1)
         sb.append("]");
       else {
         sb.append("],\n");
       }
     }
     sb.append("]");
     Element value = ele.addElement("values");
     value.addCDATA(sb.toString());
   }
 
   public static String dataTableToJS(DataTable dt) {
     if (dt == null) {
       throw new RuntimeException("DataCollection:生成JS时发生错误，传入的DataTable为空!");
     }
     boolean webModeFlag = dt.isWebMode();
     dt.setWebMode(false);
     DataColumn[] dcs = dt.getDataColumns();
     StringBuffer sb = new StringBuffer();
 
     sb.append("var _Zving_Cols = [");
     for (int i = 0; i < dcs.length; ++i) {
       if (i != 0) {
         sb.append(",");
       }
       sb.append("[\"");
       sb.append(dcs[i].getColumnName());
       sb.append("\"");
       sb.append(",");
       sb.append(dcs[i].getColumnType());
       sb.append("]");
     }
     sb.append("]\n");
     sb.append("var _Zving_Values = [");
     for (i = 0; i < dt.getRowCount(); ++i) {
       sb.append("[");
       for (int j = 0; j < dcs.length; ++j) {
         String v = dt.getString(i, j);
         int ct = dcs[j].ColumnType;
         if (j != 0) {
           sb.append(",");
         }
         if (v == null) {
           sb.append("null");
         }
         else if ((ct == 1) || (ct == 10)) {
           String txt = StringUtil.javaEncode(v);
           txt = StringUtil.replaceEx(txt, "<", "\\<");
           txt = StringUtil.replaceEx(txt, ">", "\\>");
           sb.append("\"" + txt + "\"");
         } else if (ct == 0) {
           sb.append("\"" + v + "\"");
         } else {
           sb.append(v);
         }
       }
       if (i == dt.getRowCount() - 1)
         sb.append("]");
       else {
         sb.append("],\n");
       }
     }
     dt.setWebMode(webModeFlag);
     sb.append("];\n");
     return sb.toString();
   }
 
   private void schemaToXML(Schema schema, Element ele) {
     try {
       Class c = Class.forName(SchemaUtil.getNameSpace(schema) + "." + SchemaUtil.getTableCode(schema) + "Set");
       SchemaSet set = (SchemaSet)c.newInstance();
       set.add(schema);
       DataTable dt = set.toDataTable();
       ele.addAttribute("TableCode", SchemaUtil.getTableCode(schema));
       ele.addAttribute("NameSpace", SchemaUtil.getNameSpace(schema));
       translatDataTableToXML(dt, ele, "Schema");
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   private void schemaSetToXML(SchemaSet set, Element ele) {
     if ((set != null) && (set.size() != 0)) {
       DataTable dt = set.toDataTable();
       ele.addAttribute("TableCode", SchemaUtil.getTableCode(set));
       ele.addAttribute("NameSpace", SchemaUtil.getNameSpace(set));
       translatDataTableToXML(dt, ele, "SchemaSet");
     }
   }
 
   public void write(String fileName) {
     FileUtil.writeText(fileName, toXML(), "UTF-8");
   }
 
   public void parseXML(String xml) {
     if ((xml == null) || (xml.length() == 0)) {
       throw new RuntimeException("DataCollection:解析XML时发生错误，传入的字符串为空!");
     }
     xml = StringUtil.replaceEx(xml, StringUtil.urlDecode("%C2%A0", "UTF-8"), " ");
     xml = StringUtil.clearForXML(xml);
     SAXReader reader = new SAXReader(false);
     reader.setEncoding("UTF-8");
     try
     {
       Document doc = reader.read(new InputSource(new StringReader(xml)));
       Element root = doc.getRootElement();
       List elements = root.elements();
       for (int i = 0; i < elements.size(); ++i) {
         Element ele = (Element)elements.get(i);
         String id = ele.attributeValue("ID");
         String type = ele.attributeValue("Type");
         String str;
         if (type.equals("String")) {
           str = ele.getText();
           if ("_ZVING_NULL".equals(str)) {
             str = null;
           }
           put(id, str);
         } else if (type.equals("Int")) {
           str = ele.attributeValue("Value");
           put(id, Integer.parseInt(str));
         } else if (type.equals("Long")) {
           str = ele.attributeValue("Value");
           put(id, Long.parseLong(str));
         } else if (type.equals("Float")) {
           str = ele.attributeValue("Value");
           put(id, Float.parseFloat(str));
         } else if (type.equals("Double")) {
           str = ele.attributeValue("Value");
           put(id, Double.parseDouble(str));
         }
         else
         {
           String[] t;
           if (type.equals("StringArray")) {
             str = ele.getText();
             t = str.split("\",\"");
             for (int j = 0; j < t.length; ++j) {
               t[j] = StringUtil.javaDecode(t[j]);
             }
             t[0] = t[0].substring(1);
             t[(t.length - 1)] = t[(t.length - 1)].substring(0, t[(t.length - 1)].length() - 2);
             put(id, t);
           }
           else
           {
             int j;
             if (type.equals("IntArray")) {
               str = ele.attributeValue("Value");
               t = str.split(",");
               int[] a = new int[t.length];
               for (j = 0; j < t.length; ++j) {
                 a[j] = Integer.parseInt(t[j]);
               }
               put(id, a);
             } else if (type.equals("LongArray")) {
               str = ele.attributeValue("Value");
               t = str.split(",");
               long[] a = new long[t.length];
               for (j = 0; j < t.length; ++j) {
                 a[j] = Long.parseLong(t[j]);
               }
               put(id, a);
             } else if (type.equals("FloatArray")) {
               str = ele.attributeValue("Value");
               t = str.split(",");
               float[] a = new float[t.length];
               for (j = 0; j < t.length; ++j) {
                 a[j] = Float.parseFloat(t[j]);
               }
               put(id, a);
             } else if (type.equals("DoubleArray")) {
               str = ele.attributeValue("Value");
               t = str.split(",");
               double[] a = new double[t.length];
               for (j = 0; j < t.length; ++j) {
                 a[j] = Double.parseDouble(t[j]);
               }
               put(id, a);
             } else if (type.equals("DataTable")) {
               DataTable dt = parseDataTable(ele);
               put(id, dt);
             } else if (type.equals("Map")) {
               Mapx map = parseMap(ele);
               put(id, map);
             }
             else
             {
               DataTable dt;
               if (type.equals("Schema")) {
                 Class c = Class.forName(ele.attributeValue("NameSpace") + "." + ele.attributeValue("TableCode"));
                 Schema schema = (Schema)c.newInstance();
                 dt = parseDataTable(ele);
                 for (j = 0; j < SchemaUtil.getColumns(schema).length; ++j) {
                   schema.setV(i, dt.get(0, j));
                 }
                 put(id, schema);
               } else if (type.equals("SchemaSet")) {
                 Class cSchema = Class.forName(ele.attributeValue("NameSpace") + "." + 
                   ele.attributeValue("TableCode") + "Schema");
                 Class cSet = Class.forName(ele.attributeValue("NameSpace") + "." + ele.attributeValue("TableCode") + 
                   "Set");
                 dt = parseDataTable(ele);
                 SchemaSet set = (SchemaSet)cSet.newInstance();
                 for (int j = 0; j < dt.getRowCount(); ++j) {
                   Schema schema = (Schema)cSchema.newInstance();
                   for (int k = 0; k < SchemaUtil.getColumns(schema).length; ++k) {
                     schema.setV(i, dt.get(j, k));
                   }
                   set.add(schema);
                 }
                 put(id, set); } } }
         }
       }
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   private Mapx parseMap(Element ele) {
     Mapx map = new Mapx();
     String str = ele.element("columns").getText();
     str = str.trim().substring(0, str.length() - 1);
     String[] arr = StringUtil.splitEx(str, ",\"");
     for (int i = 0; i < arr.length; ++i) {
       String[] arr2 = StringUtil.splitEx(str, "\":");
       if (arr2.length != 2) {
         continue;
       }
       String k = arr2[0];
       String v = arr2[1];
       if (k.startsWith("\"")) {
         k = k.substring(1);
       }
       if (k.endsWith("\"")) {
         k = k.substring(0, k.length() - 1);
       }
       if (v.startsWith("\"")) {
         v = v.substring(1);
       }
       if (v.endsWith("\"")) {
         v = v.substring(0, v.length() - 1);
       }
       map.put(k, v);
     }
     return map;
   }
 
   private void mapToXML(Mapx map, Element ele) {
     ele.addAttribute("Type", "Map");
     StringBuffer sb = new StringBuffer();
     Object[] ks = map.keyArray();
     sb.append("{");
     for (int i = 0; i < ks.length; ++i) {
       if (i != 0) {
         sb.append(",");
       }
       sb.append(ks[i]);
       sb.append(":\"");
       sb.append(StringUtil.javaEncode(map.getString(ks[i])));
       sb.append("\"");
     }
     sb.append("}");
     ele.addCDATA(sb.toString());
   }
 
   private DataTable parseDataTable(Element ele) {
     String str = ele.element("columns").getText();
     String[] t = StringUtil.splitEx(str, "],[");
     DataColumn[] dcs = new DataColumn[t.length];
     t[0] = t[0].substring(2);
     int length = t.length;
     t[(length - 1)] = t[(length - 1)].substring(0, t[(length - 1)].length() - 2);
     for (int i = 0; i < t.length; ++i) {
       dcs[i] = new DataColumn();
       int index = t[i].lastIndexOf(",");
       dcs[i].setColumnName(t[i].substring(1, index - 1));
       dcs[i].setColumnType(Integer.parseInt(t[i].substring(index + 1)));
     }
     String value = ele.element("values").getText();
     t = value.split("\\\"\\]\\,\\s*?\\[\\\"");
     if (t[0].equals("[]")) {
       return new DataTable(dcs, null);
     }
     t[0] = t[0].substring(3);
     length = t.length;
     t[(length - 1)] = t[(length - 1)].substring(0, t[(length - 1)].length() - 3);
 
     Object[][] values = new Object[length][dcs.length];
     for (int i = 0; i < t.length; ++i) {
       String[] r = t[i].split("\",\"");
       for (int j = 0; j < r.length; ++j) {
         if (dcs[j].getColumnType() == 1) {
           values[i][j] = StringUtil.javaDecode(r[j]);
         }
         else if ((r[j].equals("_ZVING_NULL")) || (StringUtil.isEmpty(r[j])))
           values[i][j] = null;
         else if (dcs[j].getColumnType() == 3)
           values[i][j] = new Double(Double.parseDouble(r[j]));
         else if (dcs[j].getColumnType() == 0)
           values[i][j] = DateUtil.parse(r[j]);
         else if (dcs[j].getColumnType() == 4)
           values[i][j] = new Double(Double.parseDouble(r[j]));
         else if (dcs[j].getColumnType() == 6)
           values[i][j] = new Double(Double.parseDouble(r[j]));
         else if (dcs[j].getColumnType() == 5)
           values[i][j] = new Float(Float.parseFloat(r[j]));
         else if (dcs[j].getColumnType() == 8)
           values[i][j] = new Integer(Integer.parseInt(r[j]));
         else if (dcs[j].getColumnType() == 7)
           values[i][j] = new Long(Long.parseLong(r[j]));
         else if (dcs[j].getColumnType() == 9) {
           values[i][j] = new Integer(Integer.parseInt(r[j]));
         }
       }
     }
 
     return new DataTable(dcs, values);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.data.DataCollection
 * JD-Core Version:    0.5.3
 */