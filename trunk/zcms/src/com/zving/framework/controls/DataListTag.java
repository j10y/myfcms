 package com.zving.framework.controls;
 
 import com.zving.framework.Current;
 import com.zving.framework.utility.StringUtil;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.jsp.JspException;
 import javax.servlet.jsp.JspWriter;
 import javax.servlet.jsp.PageContext;
 import javax.servlet.jsp.tagext.BodyContent;
 import javax.servlet.jsp.tagext.BodyTagSupport;
 
 public class DataListTag extends BodyTagSupport
 {
   private static final long serialVersionUID = 1L;
   private String method;
   private String id;
   private int size;
   private boolean page;
 
   public void setPageContext(PageContext pc)
   {
     super.setPageContext(pc);
     this.method = null;
     this.id = null;
     this.page = true;
     this.size = 0;
   }
 
   public int doAfterBody() throws JspException {
     BodyContent body = getBodyContent();
     String content = body.getString().trim();
     try {
       if (StringUtil.isEmpty(this.method)) {
         throw new RuntimeException("DataList未指定Method");
       }
 
       DataListAction dla = new DataListAction();
       dla.setTagBody(content);
       dla.setPage(this.page);
       dla.setMethod(this.method);
 
       dla.setID(this.id);
       HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
       HttpServletResponse response = (HttpServletResponse)this.pageContext.getResponse();
       dla.setPageSize(this.size);
 
       if (this.page) {
         dla.setPageIndex(0);
         if (StringUtil.isNotEmpty(dla.getParam("_ZVING_PAGEINDEX"))) {
           dla.setPageIndex(Integer.parseInt(dla.getParam("_ZVING_PAGEINDEX")));
         }
         if (dla.getPageIndex() < 0) {
           dla.setPageIndex(0);
         }
         dla.setPageSize(this.size);
       }
 
       Current.init(request, response, this.method);
       dla.setParams(Current.getRequest());
       Current.invokeMethod(this.method, new Object[] { dla });
 
       this.pageContext.setAttribute(this.id + "_ZVING_PAGETOTAL", dla.getTotal());
       this.pageContext.setAttribute(this.id + "_ZVING_PAGEINDEX", dla.getPageIndex());
       this.pageContext.setAttribute(this.id + "_ZVING_SIZE", dla.getPageSize());
 
       getPreviousOut().write(dla.getHtml());
     } catch (Exception e1) {
       e1.printStackTrace();
     }
     return 6;
   }
 
   public String getMethod() {
     return this.method;
   }
 
   public void setMethod(String method) {
     this.method = method;
   }
 
   public String getId() {
     return this.id;
   }
 
   public void setId(String id) {
     this.id = id;
   }
 
   public int getSize() {
     return this.size;
   }
 
   public void setSize(int size) {
     this.size = size;
   }
 
   public boolean isPage() {
     return this.page;
   }
 
   public void setPage(boolean page) {
     this.page = page;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.DataListTag
 * JD-Core Version:    0.5.3
 */