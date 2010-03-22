 package com.zving.framework.controls;
 
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import java.util.ArrayList;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class HtmlTR extends HtmlElement
 {
   private HtmlTable parent;
   private ArrayList pList;
 
   public HtmlTR()
   {
     this(null);
   }
 
   public HtmlTR(HtmlTable parent)
   {
     this.pList = null;
 
     this.parent = parent;
     this.ElementType = "TR";
     this.TagName = "tr";
   }
 
   public void addTD(HtmlTD td) {
     addChild(td);
   }
 
   public HtmlTD getTD(int index) {
     return ((HtmlTD)this.Children.get(index));
   }
 
   public void removeTD(int index) {
     if ((index < 0) || (index > this.Children.size())) {
       throw new RuntimeException("错误的索引");
     }
     this.Children.remove(index);
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
 
   public String getVAlign() {
     return ((String)this.Attributes.get("vAlign"));
   }
 
   public void setVAlign(String vAlign) {
     this.Attributes.put("vAlign", vAlign);
   }
 
   public int getRowIndex() {
     for (int i = 0; i < this.ParentElement.Children.size(); ++i) {
       if (this.ParentElement.Children.get(i).equals(this)) {
         return i;
       }
     }
     throw new RuntimeException("得到RowIndex时发生错误");
   }
 
   public void parseHtml(String html) throws Exception {
     Matcher m = HtmlTable.PTR.matcher(html);
     if (!(m.find())) {
       throw new Exception("TR解析html时发生错误");
     }
     String attrs = m.group(1);
     String tds = m.group(2).trim();
 
     this.Attributes.clear();
     this.Children.clear();
 
     m = HtmlTable.PInnerTable.matcher(tds);
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
         tds = StringUtil.replaceEx(tds, this.pList.get(i).toString(), "<!--_ZVING_INNERTABLE_PROTECTED_" + i + "-->");
       }
     }
 
     this.Attributes = parseAttr(attrs);
 
     m = HtmlTable.PTDPre.matcher(tds);
     lastEndIndex = 0;
     while (m.find(lastEndIndex)) {
       String t = tds.substring(m.start(), m.end());
       HtmlTD td = new HtmlTD(this);
       td.parseHtml(t);
       addTD(td);
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
           int index = Integer.parseInt(arr[i].substring(0, arr[i].indexOf("-")));
           sb.append(this.pList.get(index).toString());
           arr[i] = arr[i].substring(arr[i].indexOf(">") + 1);
         }
         sb.append(arr[i]);
       }
     }
     return sb.toString();
   }
 
   public HtmlTable getParent() {
     return this.parent;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.HtmlTR
 * JD-Core Version:    0.5.3
 */