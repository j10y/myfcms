 package com.zving.framework.controls;
 
 import com.zving.framework.Constant;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.ServletUtil;
 import com.zving.framework.utility.StringUtil;
 import java.lang.reflect.Method;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.jsp.JspException;
 import javax.servlet.jsp.JspWriter;
 import javax.servlet.jsp.PageContext;
 import javax.servlet.jsp.tagext.BodyContent;
 import javax.servlet.jsp.tagext.BodyTagSupport;
 
 public class InitTag extends BodyTagSupport
 {
   private static final long serialVersionUID = 1L;
   private String method;
 
   public String getMethod()
   {
     return this.method;
   }
 
   public void setMethod(String method) {
     this.method = method;
   }
 
   public int doAfterBody() throws JspException
   {
     String content = getBodyContent().getString();
     try {
       HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
       Mapx map = null;
       if ((this.method == null) || (this.method.equals(""))) {
         map = ServletUtil.getParameterMap(request);
       } else {
         int index = this.method.lastIndexOf(46);
         String className = this.method.substring(0, index);
         this.method = this.method.substring(index + 1);
         Class c = Class.forName(className);
         Method m = c.getMethod(this.method, new Class[] { Mapx.class });
         Mapx params = ServletUtil.getParameterMap(request);
 
         Object o = m.invoke(null, new Object[] { params });
         if (o == null) {
           o = new Mapx();
         }
         if (!(o instanceof Mapx)) {
           throw new RuntimeException("调用z:init指定的method时发现返回类型不是Mapx");
         }
         map = (Mapx)o;
       }
 
       content = HtmlUtil.replacePlaceHolder(content, map, true);
 
       Matcher matcher = Constant.PatternSpeicalField.matcher(content);
       StringBuffer sb = new StringBuffer();
       int lastEndIndex = 0;
       while (matcher.find(lastEndIndex)) {
         sb.append(content.substring(lastEndIndex, matcher.start()));
         Object v = map.get(matcher.group(1));
         if ((v != null) && (!(v.equals("")))) {
           if (matcher.group().indexOf(35) > 0)
             sb.append(StringUtil.javaEncode(v.toString()));
           else
             sb.append(v.toString());
         }
         else {
           sb.append(content.substring(matcher.start(), matcher.end()));
         }
         lastEndIndex = matcher.end();
       }
       sb.append(content.substring(lastEndIndex));
       content = sb.toString();
       getPreviousOut().print(content);
     } catch (Exception e) {
       e.printStackTrace();
     }
     return 6;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.InitTag
 * JD-Core Version:    0.5.3
 */