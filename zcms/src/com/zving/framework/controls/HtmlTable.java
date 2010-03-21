 package com.zving.framework.controls;
 
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.io.PrintStream;
 import java.util.ArrayList;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class HtmlTable extends HtmlElement
 {
   public static final Pattern PTable = Pattern.compile(
     "^\\s*<table(.*?)>(.*)</table>\\s*$", 34);
 
   public static final Pattern PInnerTable = Pattern.compile(
     "<table(.*?)>(.*?)</table>", 34);
 
   public static final Pattern PTR = Pattern.compile(
     "^\\s*<tr(.*?)>(.*)</tr>\\s*$", 34);
 
   public static final Pattern PTRPre = Pattern.compile("<tr.*?>.*?</tr>", 
     34);
 
   public static final Pattern PTD = Pattern.compile(
     "^\\s*<(td|th)(.*?)>(.*)</(td|th)>\\s*$", 34);
 
   public static final Pattern PTDPre = Pattern.compile(
     "<(td|th).*?>.*?</(td|th)>", 34);
   public static final String ProtectedTableStart = "<!--_ZVING_INNERTABLE_PROTECTED_";
   private ArrayList pList = null;
 
   public HtmlTable() {
     this.ElementType = "TABLE";
     this.TagName = "table";
   }
 
   public void addTR(HtmlTR tr) {
     addChild(tr);
   }
 
   public HtmlTR getTR(int index) {
     return ((HtmlTR)this.Children.get(index));
   }
 
   public void removeTR(int index) {
     if ((index < 0) || (index > this.Children.size())) {
       throw new RuntimeException("错误的索引");
     }
     this.Children.remove(index);
   }
 
   public void removeColumn(int index) {
     for (int i = 0; i < this.Children.size(); ++i) {
       HtmlTR tr = getTR(i);
       if (index < tr.Children.size())
         tr.removeTD(index);
     }
   }
 
   public void setWidth(int width)
   {
     this.Attributes.put("width", new Integer(width));
   }
 
   public int getWidth() {
     return ((Integer)this.Attributes.get("width")).intValue();
   }
 
   public void setHeight(int height) {
     this.Attributes.put("height", new Integer(height));
   }
 
   public int getHeight() {
     return ((Integer)this.Attributes.get("height")).intValue();
   }
 
   public void setAlign(String align) {
     this.Attributes.put("align", align);
   }
 
   public String getAlign() {
     return ((String)this.Attributes.get("align"));
   }
 
   public void setBgColor(String bgColor) {
     this.Attributes.put("bgColor", bgColor);
   }
 
   public String getBgColor() {
     return ((String)this.Attributes.get("bgColor"));
   }
 
   public void setBackgroud(String backgroud) {
     this.Attributes.put("backgroud", backgroud);
   }
 
   public String getBackgroud() {
     return ((String)this.Attributes.get("backgroud"));
   }
 
   public void setCellSpacing(String cellSpacing) {
     setAttribute("cellSpacing", cellSpacing);
   }
 
   public String getCellSpacing() {
     return getAttribute("cellSpacing");
   }
 
   public void setCellPadding(String cellPadding) {
     setAttribute("cellPadding", cellPadding);
   }
 
   public String getCellPadding() {
     return getAttribute("cellPadding");
   }
 
   public void parseHtml(String html) throws Exception {
     Matcher m = PTable.matcher(html);
     if (!(m.find())) {
       throw new Exception("Table解析html时发生错误");
     }
     String attrs = m.group(1);
     String trs = m.group(2).trim();
 
     this.Attributes.clear();
     this.Children.clear();
 
     this.Attributes = parseAttr(attrs);
 
     m = PInnerTable.matcher(trs);
     int lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       if (this.pList == null) {
         this.pList = new ArrayList();
       }
       this.pList.add(m.group(0));
       lastEndIndex = m.end();
     }
     if (this.pList != null) {
       for (int i = 0; i < this.pList.size(); ++i) {
         trs = StringUtil.replaceEx(trs, this.pList.get(i).toString(), 
           "<!--_ZVING_INNERTABLE_PROTECTED_" + i + "-->");
       }
 
     }
 
     m = PTRPre.matcher(trs);
     lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       String t = trs.substring(m.start(), m.end());
       HtmlTR tr = new HtmlTR(this);
       tr.parseHtml(t);
       addTR(tr);
       lastEndIndex = m.end();
     }
   }
 
   public String restoreInnerTable(String html) {
     if ((this.pList == null) || (this.pList.size() == 0)) {
       return html;
     }
     String[] arr = StringUtil.splitEx(html, "<!--_ZVING_INNERTABLE_PROTECTED_");
     StringBuffer sb = new StringBuffer();
     for (int i = 0; i < arr.length; ++i) {
       if (StringUtil.isNotEmpty(arr[i])) {
         if (i != 0) {
           int index = Integer.parseInt(arr[i].substring(0, arr[i]
             .indexOf("-")));
           sb.append(this.pList.get(index).toString());
           arr[i] = arr[i].substring(arr[i].indexOf(">") + 1);
         }
         sb.append(arr[i]);
       }
     }
     return sb.toString();
   }
 
   public String getOuterHtml() {
     StringBuffer sb = new StringBuffer();
     sb.append("<");
     sb.append(this.TagName);
     Object[] ks = this.Attributes.keyArray();
     Object[] vs = this.Attributes.valueArray();
     for (int i = 0; i < this.Attributes.size(); ++i) {
       if (vs[i] != null) {
         sb.append(" ");
         sb.append(ks[i]);
         sb.append("=\"");
         sb.append(vs[i]);
         sb.append("\"");
       }
     }
 
     sb.append(">\n");
     if (getTR(0).getTD(0).isHead()) {
       sb.append("<thead>\n");
     }
     sb.append(getTR(0).getOuterHtml());
     if (getTR(0).getTD(0).isHead()) {
       sb.append("</thead>\n");
       if (this.Children.size() > 1) {
         sb.append("<tbody>\n");
       }
     }
     for (i = 1; i < this.Children.size(); ++i) {
       sb.append(((HtmlElement)this.Children.get(i)).getOuterHtml());
     }
     if ((getTR(0).getTD(0).isHead()) && (this.Children.size() > 1)) {
       sb.append("</tbody>\n");
     }
     sb.append("</");
     sb.append(this.TagName);
     sb.append(">");
     return sb.toString();
   }
 
   public static void test() {
     HtmlTable table = new HtmlTable();
     try {
       String html = FileUtil.readText("G:/Test.txt");
       table.parseHtml(html);
       System.out.println(table.getOuterHtml());
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 
   public static void main(String[] args) {
     Pattern PTR = Pattern.compile("^\\s*<(tr|th)(.*?)>(.*)</(tr|th)>\\s*$", 
       34);
     Matcher m = PTR.matcher("<th>dsfsd</th>");
     if (m.find())
       System.out.println(m.group(3));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.HtmlTable
 * JD-Core Version:    0.5.3
 */