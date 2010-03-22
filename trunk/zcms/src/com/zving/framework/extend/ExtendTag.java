 package com.zving.framework.extend;
 
 import com.zving.framework.utility.LogUtil;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import javax.servlet.jsp.JspException;
 import javax.servlet.jsp.JspWriter;
 import javax.servlet.jsp.PageContext;
 import javax.servlet.jsp.tagext.TagSupport;
 import org.apache.commons.logging.Log;
 
 public class ExtendTag extends TagSupport
 {
   private static final long serialVersionUID = 1L;
   private String target;
 
   public String getTarget()
   {
     return this.target;
   }
 
   public void setTarget(String target) {
     this.target = target;
   }
 
   public int doStartTag() throws JspException {
     try {
       if (ExtendManager.hasAction(this.target)) {
         IExtendAction[] actions = ExtendManager.find(this.target);
         for (int i = 0; i < actions.length; ++i)
           if (!(actions[i] instanceof JSPExtendAction)) {
             LogUtil.getLogger().warn("类" + actions[i].getClass().getName() + "必须继承JSPExtendAction!");
           }
           else {
             HttpServletRequest request = (HttpServletRequest)this.pageContext.getRequest();
             HttpServletResponse response = (HttpServletResponse)this.pageContext.getResponse();
             actions[i].execute(new Object[] { request, response });
           }
       }
       this.pageContext.getOut().write("");
     } catch (Exception e) {
       e.printStackTrace();
     }
     return 0;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.extend.ExtendTag
 * JD-Core Version:    0.5.3
 */