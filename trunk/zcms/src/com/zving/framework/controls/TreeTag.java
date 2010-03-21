 package com.zving.framework.controls;
 
 import com.zving.framework.Current;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.jsp.JspException;
 import javax.servlet.jsp.JspWriter;
 import javax.servlet.jsp.PageContext;
 import javax.servlet.jsp.tagext.BodyContent;
 import javax.servlet.jsp.tagext.BodyTagSupport;
 
 public class TreeTag extends BodyTagSupport
 {
   private static final long serialVersionUID = 1L;
   private String id;
   private String method;
   private String style;
   private boolean lazy;
   private boolean expand;
   private int level;
 
   public void setPageContext(PageContext pc)
   {
     super.setPageContext(pc);
     this.method = null;
     this.id = null;
     this.style = null;
     this.lazy = false;
     this.expand = false;
   }
 
   public int doAfterBody() throws JspException {
     BodyContent body = getBodyContent();
     String content = body.getString().trim();
     try {
       if ((this.method == null) || (this.method.equals(""))) {
         throw new RuntimeException("Tree既未指定Method");
       }
 
       TreeAction tree = new TreeAction();
       tree.setTagBody(content);
       tree.setMethod(this.method);
 
       HtmlP p = new HtmlP();
       p.parseHtml(content);
       tree.setTemplate(p);
 
       tree.setID(this.id);
       tree.setLazy(this.lazy);
       tree.setExpand(this.expand);
       if (this.level <= 0) {
         this.level = 999;
       }
       tree.setLevel(this.level);
       tree.setStyle(this.style);
 
       HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
       HttpServletResponse response = (HttpServletResponse)this.pageContext.getResponse();
 
       Current.init(request, response, this.method);
       tree.setParams(Current.getRequest());
       Current.invokeMethod(this.method, new Object[] { tree });
 
       getPreviousOut().write(tree.getHtml());
     } catch (Exception e1) {
       e1.printStackTrace();
     }
     return 6;
   }
 
   public boolean isLazy() {
     return this.lazy;
   }
 
   public void setLazy(boolean lazy) {
     this.lazy = lazy;
   }
 
   public int getLevel() {
     return this.level;
   }
 
   public void setLevel(int level) {
     this.level = level;
   }
 
   public String getId() {
     return this.id;
   }
 
   public void setId(String id) {
     this.id = id;
   }
 
   public String getMethod() {
     return this.method;
   }
 
   public void setMethod(String method) {
     this.method = method;
   }
 
   public String getStyle() {
     return this.style;
   }
 
   public void setStyle(String style) {
     this.style = style;
   }
 
   public boolean isExpand() {
     return this.expand;
   }
 
   public void setExpand(boolean expand) {
     this.expand = expand;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.TreeTag
 * JD-Core Version:    0.5.3
 */