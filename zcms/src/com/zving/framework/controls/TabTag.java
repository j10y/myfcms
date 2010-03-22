 package com.zving.framework.controls;
 
 import com.zving.framework.Config;
 import javax.servlet.jsp.JspException;
 import javax.servlet.jsp.JspWriter;
 import javax.servlet.jsp.tagext.BodyContent;
 import javax.servlet.jsp.tagext.BodyTagSupport;
 
 public class TabTag extends BodyTagSupport
 {
   private static final long serialVersionUID = 1L;
 
   public int doAfterBody()
     throws JspException
   {
     String content = getBodyContent().getString();
     try {
       StringBuffer sb = new StringBuffer();
       sb.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"6\" class=\"blockTable\">");
       sb.append("  <tr>");
       sb.append("    <td height=\"26\" valign=\"middle\" class=\"blockTd\">");
       sb.append("    <table width=\"100%\" border='0' cellpadding='0' cellspacing='0' style=\"background:url(" + 
         Config.getContextPath() + 
         "Framework/Images/divchildtabBarBg.gif) repeat-x left bottom; margin-bottom:1px;\">");
       sb.append("    <tr>");
       sb.append("      <td valign=\"bottom\" height='30' style=\"padding:0 0 0 6px;_padding:0 0 0 2px;\">");
       sb.append(content);
       sb.append("\t\t\t</td>");
       sb.append("   </tr>");
       sb.append("   </table>");
       sb.append("   </td>");
       sb.append("  </tr>");
       sb.append("  <tr>");
       sb.append("     <td style=\"padding:2px 6px;\">");
 
       String[] arr = content.split("<\\/div>\\s*<div ");
       String selectedID = null;
       for (int i = 0; i < arr.length; ++i) {
         String html = arr[i].trim();
         if (i == 0)
           html = html + "</div>";
         else if (i == arr.length - 1)
           html = "<div " + html;
         else {
           html = "<div " + html + "</div>";
         }
         HtmlDiv div = new HtmlDiv();
         div.parseHtml(html);
         if (div.getAttribute("class").equals("divchildtabCurrent")) {
           sb.append("<iframe src=\"" + div.getAttribute("src") + 
             "\" width=\"100%\" height=\"0\" id=\"_ChildTabFrame_" + div.getAttribute("id") + 
             "\" frameborder=\"0\" scrolling=\"auto\" allowtransparency=\"true\"></iframe>");
           selectedID = div.getAttribute("id").toString();
         } else {
           sb.append("<iframe src=\"" + div.getAttribute("src") + 
             "\" width='100%' height='0' id=\"_ChildTabFrame_" + div.getAttribute("id") + 
             "\" frameborder=\"0\" scrolling=\"auto\" allowtransparency=\"true\"></iframe>");
         }
       }
       sb.append("     </td>");
       sb.append("  </tr>");
       sb.append("</table>");
       HtmlScript script = new HtmlScript();
       script.setInnerHTML("Page.onLoad(function(){Tab.initFrameHeight(\"_ChildTabFrame_" + selectedID + 
         "\");},5);");
       sb.append(script.getOuterHtml());
       getPreviousOut().print(sb);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return 6;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.TabTag
 * JD-Core Version:    0.5.3
 */