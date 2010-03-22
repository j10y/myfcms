 package com.zving.framework.controls;
 
 import com.zving.framework.utility.Mapx;
 import java.util.ArrayList;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 
 public class HtmlTD extends HtmlElement
 {
   private HtmlTR parent;
 
   public HtmlTD()
   {
     this(null);
   }
 
   public HtmlTD(HtmlTR parent) {
     this.parent = parent;
     this.ElementType = "TD";
     this.TagName = "td";
   }
 
   public void setWidth(int width) {
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
 
   public String getVAlign() {
     return ((String)this.Attributes.get("vAlign"));
   }
 
   public void setVAlign(String vAlign) {
     this.Attributes.put("vAlign", vAlign);
   }
 
   public void setColSpan(String colSpan) {
     setAttribute("colSpan", colSpan);
   }
 
   public String getColSpan() {
     return getAttribute("colSpan");
   }
 
   public void setRowSpan(String rowSpan) {
     setAttribute("rowSpan", rowSpan);
   }
 
   public String getRowSpan() {
     return getAttribute("rowSpan");
   }
 
   public int getCellIndex() {
     for (int i = 0; i < this.ParentElement.Children.size(); ++i) {
       if (this.ParentElement.Children.get(i).equals(this)) {
         return i;
       }
     }
     throw new RuntimeException("得到RowIndex时发生错误");
   }
 
   public void parseHtml(String html) throws Exception {
     Matcher m = HtmlTable.PTD.matcher(html);
     if (!(m.find())) {
       throw new Exception(this.TagName + "解析html时发生错误");
     }
     this.TagName = m.group(1);
     String attrs = m.group(2);
 
     this.Attributes.clear();
     this.Children.clear();
 
     this.Attributes = parseAttr(attrs);
     this.InnerHTML = m.group(3).trim();
 
     if (this.parent != null) {
       String newHtml = this.parent.restoreInnerTable(this.InnerHTML);
       if (newHtml.equals(this.InnerHTML)) {
         if (this.parent.getParent() != null)
           setInnerHTML(this.parent.getParent().restoreInnerTable(
             this.InnerHTML));
       }
       else
         setInnerHTML(newHtml);
     }
   }
 
   public HtmlTR getParent()
   {
     return this.parent;
   }
 
   public boolean isHead() {
     return this.TagName.equalsIgnoreCase("th");
   }
 
   public void setHead(boolean isHead) {
     if (isHead)
       this.TagName = "th";
     else
       this.TagName = "tr";
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.HtmlTD
 * JD-Core Version:    0.5.3
 */