 package com.zving.statical;
 
 import com.zving.framework.script.EvalException;
 import com.zving.framework.script.ScriptEngine;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.io.PrintStream;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class TemplateParser
 {
   private static final Pattern pInclude = Pattern.compile(
     "<z:include *?file=\\\"(.*?)\\\" *?((/>)|(.*?</z:include>))", 34);
 
   private static final Pattern pConfig = Pattern.compile("<z:config *?(.*?) *?((/>)|(.*?</z:config>))", 
     34);
 
   private static final Pattern pDefine = Pattern.compile("<z:define *?(.*?) *?((/>)|(.*?</z:define>))", 
     34);
 
   private static final Pattern pJava = Pattern.compile("<\\%(.*?)\\%>", 34);
 
   private static final Pattern pJavaExpression = Pattern.compile("<\\%=(.*?)\\%>", 34);
 
   private static final Pattern pField = Pattern.compile("\\$\\{(\\w+?)\\.(\\w+?)(\\|(.*?))??\\}");
 
   private static final Pattern pFieldProp1 = Pattern.compile("\\&??(\\w+?)=([^\\|\\\"]*)");
 
   private static final Pattern pFieldProp2 = Pattern.compile("\\&??(\\w+?)=(\\\"|\\')(.*?)\\2");
 
   public static final Pattern pAttr = Pattern.compile("\\s*?(\\w+?)\\s*?=\\s*?(\\\"|\\')(.*?)\\2", 
     34);
   private String fileName;
   private String template;
   private String includePathBase;
   private String language;
   private String prefix;
   private String script;
   private String result;
   private Mapx defineMap = new Mapx();
 
   private ArrayList packageArr = new ArrayList();
 
   private ArrayList classArr = new ArrayList();
   private ScriptEngine se;
 
   public void addPackage(String packageName)
   {
     this.packageArr.add(packageName);
   }
 
   public void addClass(String className) {
     this.classArr.add(className);
   }
 
   public void removePackage(String packageName) {
     this.packageArr.remove(packageName);
   }
 
   public void removeClass(String className) {
     this.classArr.remove(className);
   }
 
   public String getIncludePathBase() {
     return this.includePathBase;
   }
 
   public void setIncludePathBase(String includePathBase) {
     this.includePathBase = includePathBase;
   }
 
   public String getTemplate() {
     return this.template;
   }
 
   public void setTemplate(String html) {
     this.template = html;
   }
 
   public String getLanguage() {
     return this.language;
   }
 
   public String getPrefix() {
     return this.prefix;
   }
 
   public String getResult() {
     return this.result;
   }
 
   public void parse() throws EvalException {
     parseInclude();
     parseHead();
     parsePlaceHolder();
     parseExpression();
     parseScript();
     StringBuffer sb = new StringBuffer();
 
     if (this.language.equalsIgnoreCase("java")) {
       this.se = new ScriptEngine(1);
       sb.append("StringBuffer _Result = new StringBuffer();\n");
       sb.append("void write(String str){if(str==null)str=\"\"; _Result.append(str);}\n");
       sb.append("void write(int i){ _Result.append(i);}\n");
       sb.append("void write(long i){ _Result.append(i);}\n");
       sb.append("void write(float i){ _Result.append(i);}\n");
       sb.append("void writeln(String str){if(str==null)str=\"\"; _Result.append(str+\"\\n\");}\n");
       sb.append("void writeln(int str){_Result.append(str+\"\\n\");}\n");
       sb.append("void writeln(long str){_Result.append(str+\"\\n\");}\n");
       sb.append("void writeln(float str){ _Result.append(str+\"\\n\");}\n");
       sb.append(this.script);
       sb.append("return _Result.toString();\n");
     } else {
       this.se = new ScriptEngine(0);
       sb.append("var _Result = [];\n");
       sb.append("function write(str){_Result.push(str);}\n");
       sb.append("function writeln(str){_Result.push(str+\"\\n\");}\n");
       sb.append(this.script);
       sb.append("return _Result.join('');\n");
     }
     this.se.importClass("com.zving.framework.data.DataTable");
     this.se.importClass("com.zving.framework.data.DataRow");
     this.se.importClass("com.zving.framework.data.DataColumn");
     this.se.importClass("com.zving.framework.utility.Mapx");
     this.se.importClass("com.zving.framework.utility.StringUtil");
     this.se.importClass("com.zving.framework.utility.DateUtil");
     this.se.importPackage("com.zving.framework.cache");
 
     for (int i = 0; i < this.packageArr.size(); ++i) {
       this.se.importPackage((String)this.packageArr.get(i));
     }
 
     for (i = 0; i < this.classArr.size(); ++i) {
       this.se.importClass((String)this.classArr.get(i));
     }
 
     this.script = sb.toString();
     this.se.setNeedCheck(true);
     this.se.compileFunction("_EvalTemplate", this.script);
   }
 
   private void parseInclude()
   {
     StringBuffer sb = new StringBuffer();
     Matcher m = pInclude.matcher(this.template);
     int lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       int s = m.start();
       int e = m.end();
       sb.append(this.template.substring(lastEndIndex, s));
       sb.append(FileUtil.readText(getFullPath(this.includePathBase, m.group(1))));
       lastEndIndex = e;
     }
     sb.append(this.template.substring(lastEndIndex));
     this.template = sb.toString();
   }
 
   private void parseHead() {
     Matcher m = pConfig.matcher(this.template);
     if (m.find()) {
       Mapx map = getAttrMap(m.group(1));
       this.language = ((String)map.get("language"));
       if (StringUtil.isEmpty(this.language)) {
         this.language = "java";
       }
       this.prefix = ((String)map.get("prefix"));
       if (StringUtil.isEmpty(this.prefix)) {
         this.prefix = "%";
       }
       this.template = this.template.substring(0, m.start()) + this.template.substring(m.end());
     }
     m = pDefine.matcher(this.template);
     int lastIndex = 0;
     StringBuffer sb = new StringBuffer();
     while (m.find(lastIndex)) {
       Mapx map = getAttrMap(m.group(1));
       String var = (String)map.get("var");
       if (!(StringUtil.isEmpty(var))) {
         this.defineMap.put(var, null);
       }
       sb.append(this.template.substring(lastIndex, m.start()));
       lastIndex = m.end();
     }
     sb.append(this.template.substring(lastIndex));
     this.template = sb.toString().trim();
   }
 
   private void parsePlaceHolder()
   {
     StringBuffer sb = new StringBuffer();
     Matcher m = pField.matcher(this.template);
     int lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       boolean flag = true;
       sb.append(this.template.substring(lastEndIndex, m.start()));
       sb.append("<" + this.prefix);
       String table = m.group(1);
       String field = m.group(2);
 
       if (table != null) {
         table = table.toLowerCase();
       }
       if (field != null) {
         field = field.toLowerCase();
       }
       String prop = m.group(3);
       if (StringUtil.isNotEmpty(prop)) {
         Mapx map = parseFieldProp(prop);
         if (StringUtil.isNotEmpty((String)map.get("charwidth"))) {
           int c = 0;
           try {
             c = Integer.parseInt(map.get("charwidth").toString());
           } catch (Exception ex) {
             ex.printStackTrace();
           }
           if (c != 0) {
             sb.append("if(" + table + ".getString(\"" + field + "\").length()>" + c + "){\n");
             sb.append("\twrite(StringUtil.subStringEx(" + table + ".getString(\"" + field + "\")," + 
               (c - 1) + ")+\"...\");\n");
             sb.append("}else{\n");
             sb.append("\twrite(" + table + ".getString(\"" + field + "\"));\n");
             sb.append("}\n");
             flag = false;
           }
         }
         if (map.get("format") != null) {
           define("DateFormator", new SimpleDateFormat((String)map.get("format")));
           sb.append("if(" + table + ".getDataColumn(\"" + field + 
             "\").getColumnType()==DataColumn.DATETIME){\n");
           sb.append("\tDate date = (Date)" + table + ".get(\"" + field + "\");\n");
           sb.append("\tif(date == null){\n date= new Date();\n}");
           sb.append("\twrite(DateFormator.format(date));\n");
           sb.append("}else if(\"html\".equals(\"" + map.get("format") + "\")){\n");
           sb.append("\twrite(StringUtil.getTextFromHtml(" + table + ".getString(\"" + field + "\")));\n");
           sb.append("}else{\n");
           sb.append("\twrite(" + table + ".getString(\"" + field + "\"));\n");
           sb.append("}\n");
           flag = false;
         }
         if (map.get("lowercase") != null) {
           sb.append("if(" + table + ".getString(\"" + field + "\")!=null){\n");
           sb.append("\twrite(" + table + ".getString(\"" + field + "\").toLowerCase());\n");
           sb.append("}\n");
           flag = false;
         }
       }
       if (flag) {
         sb.append("write(" + table + ".getString(\"" + field + "\"));");
       }
       lastEndIndex = m.end();
       sb.append(this.prefix + ">");
     }
     sb.append(this.template.substring(lastEndIndex));
     this.template = sb.toString();
   }
 
   private void parseExpression()
   {
     StringBuffer sb = new StringBuffer();
     Matcher m = pJavaExpression.matcher(this.template);
     int lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       int e = m.end();
       sb.append(this.template.substring(lastEndIndex, m.start()));
       sb.append("<%write(" + m.group(1) + ");%>");
       lastEndIndex = e;
     }
     sb.append(this.template.substring(lastEndIndex));
     this.template = sb.toString();
   }
 
   private void parseScript() {
     StringBuffer sb = new StringBuffer();
     Matcher m = pJava.matcher(this.template);
     int lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       int s = m.start();
       int e = m.end();
       javaEncode(sb, this.template.substring(lastEndIndex, s));
       sb.append(m.group(1));
       lastEndIndex = e;
     }
     javaEncode(sb, this.template.substring(lastEndIndex));
     this.script = sb.toString();
   }
 
   public static Mapx getAttrMap(String str)
   {
     Mapx map = new Mapx();
     Matcher m = pAttr.matcher(str);
     int lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       map.put(m.group(1).toLowerCase(), m.group(3));
       lastEndIndex = m.end();
     }
     return map;
   }
 
   private static String getFullPath(String pathBase, String path)
   {
     path = path.replace('\\', '/');
     pathBase = pathBase.replace('\\', '/');
     while (path.startsWith(".")) {
       if (path.startsWith("./")) {
         path = path.substring(2);
       }
       else if (path.startsWith("../")) {
         pathBase = pathBase.substring(0, pathBase.lastIndexOf(47) + 1);
         path = path.substring(3);
       }
     }
     return pathBase + path;
   }
 
   private void javaEncode(StringBuffer sb, String str)
   {
     if ("".equals(str)) {
       return;
     }
     sb.append("write(\"");
     sb.append(StringUtil.javaEncode(str));
     sb.append("\");\n");
   }
 
   private static Mapx parseFieldProp(String str) {
     Mapx map = new Mapx();
     str = str.substring(1);
     Matcher m = pFieldProp1.matcher(str);
     int lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       map.put(m.group(1).toLowerCase(), m.group(2));
       lastEndIndex = m.end();
     }
     m = pFieldProp2.matcher(str);
     lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       map.put(m.group(1).toLowerCase(), m.group(3));
       lastEndIndex = m.end();
     }
 
     return map;
   }
 
   public boolean hasError() {
     return false;
   }
 
   public void define(String varName, Object var) {
     this.defineMap.put(varName, var);
   }
 
   public void generate() throws TemplateException {
     Object[] ks = this.defineMap.keyArray();
     Object[] vs = this.defineMap.valueArray();
     for (int i = 0; i < ks.length; ++i)
       this.se.setVar(ks[i].toString(), vs[i]);
     try
     {
       this.result = ((String)this.se.executeFunction("_EvalTemplate"));
     } catch (EvalException e) {
       System.out.println("错误脚本：\n" + getScript());
       throw new TemplateException(e);
     }
   }
 
   public void clear() {
     this.se.exit();
   }
 
   public Mapx getDefineMap() {
     return this.defineMap;
   }
 
   public void setDefineMap(Mapx defineMap) {
     this.defineMap = defineMap;
   }
 
   public String getScript() {
     return this.script;
   }
 
   public void setScript(String script) {
     this.script = script;
   }
 
   public String getFileName() {
     return this.fileName;
   }
 
   public void setFileName(String fileName) {
     this.fileName = fileName;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.statical.TemplateParser
 * JD-Core Version:    0.5.3
 */