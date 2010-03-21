 package com.zving.framework.extend;
 
 import com.zving.framework.Ajax;
 import com.zving.framework.CookieImpl;
 import com.zving.framework.Current;
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import java.io.IOException;
 import java.io.PrintWriter;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 
 public abstract class JSPExtendAction extends Ajax
   implements IExtendAction
 {
   public void execute(Object[] args)
   {
     HttpServletRequest request = (HttpServletRequest)args[0];
     HttpServletResponse response = (HttpServletResponse)args[1];
     Current.init(request, response, super.getClass().getName() + ".execute");
     RequestImpl requestImpl = Current.getRequest();
     CookieImpl cookie = Current.getPage().getCookie();
     String html = execute(requestImpl, cookie);
     try {
       response.getWriter().print(html);
     } catch (IOException e) {
       e.printStackTrace();
     }
   }
 
   public abstract String execute(RequestImpl paramRequestImpl, CookieImpl paramCookieImpl);
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.extend.JSPExtendAction
 * JD-Core Version:    0.5.3
 */