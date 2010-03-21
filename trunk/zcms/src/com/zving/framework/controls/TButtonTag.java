 package com.zving.framework.controls;
 
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.servlet.jsp.JspException;
 import javax.servlet.jsp.JspWriter;
 import javax.servlet.jsp.tagext.BodyContent;
 import javax.servlet.jsp.tagext.BodyTagSupport;
 
 public class TButtonTag extends BodyTagSupport
 {
   private static final long serialVersionUID = 1L;
   private String id;
   private String onClick;
   private String priv;
   public static final Pattern PImg = Pattern.compile("<img .*?src\\=.*?>", 
     34);
 
   public int doAfterBody() throws JspException
   {
     String content = getBodyContent().getString();
     try {
       Matcher matcher = PImg.matcher(content);
       String img = null;
       String text = null;
       if (matcher.find()) {
         img = content.substring(matcher.start(), matcher.end());
         text = content.substring(matcher.end());
       }
       getPreviousOut().print(
         getHtml(this.id, this.onClick, this.priv, img, text + "&nbsp;"));
     } catch (Exception e) {
       e.printStackTrace();
     }
     return 6;
   }
 
   public static String getHtml(String onclick, String img, String text) {
     return getHtml(null, onclick, null, img, text);
   }
 
   public static String getHtml(String onclick, String priv, String img, String text)
   {
     return getHtml(null, onclick, priv, img, text);
   }
 
   public static String getHtml(String id, String onclick, String priv, String img, String text)
   {
     StringBuffer sb = new StringBuffer();
     sb
       .append("<a href='javascript:void(0);' ztype='zPushBtn' class='zPushBtn' hidefocus='true' tabindex='-1' onselectstart='return false' id='" + 
       ((id == null) ? "" : id) + "'");
     if (onclick != null) {
       sb.append(" onClick=\"" + onclick + ";return false;");
     }
     if (priv != null) {
       sb.append("\" priv=\"" + priv);
     }
     sb.append("\" >");
     sb.append(img);
     sb.append("<b>" + text + "</b></a>");
     return sb.toString();
   }
 
   public String getId() {
     return this.id;
   }
 
   public void setId(String id) {
     this.id = id;
   }
 
   public String getOnClick() {
     return this.onClick;
   }
 
   public void setOnClick(String onClick) {
     this.onClick = onClick;
   }
 
   public String getPriv() {
     return this.priv;
   }
 
   public void setPriv(String priv) {
     this.priv = priv;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.TButtonTag
 * JD-Core Version:    0.5.3
 */