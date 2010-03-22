 package com.zving.framework;
 
 import com.zving.framework.data.DataCollection;
 import com.zving.framework.utility.LogUtil;
 import org.apache.commons.logging.Log;
 
 public abstract class Page
 {
   protected ResponseImpl Response = new ResponseImpl();
   protected RequestImpl Request;
   protected CookieImpl Cookie;
   protected Log Log = LogUtil.getLogger();
 
   public void setRequest(RequestImpl r)
   {
     this.Request = r;
   }
 
   public String $V(String id)
   {
     return this.Request.getString(id);
   }
 
   public void $S(String id, Object value)
   {
     this.Response.put(id, value);
   }
 
   public DataCollection getResponse()
   {
     return this.Response;
   }
 
   public CookieImpl getCookie()
   {
     return this.Cookie;
   }
 
   public void setCookie(CookieImpl c)
   {
     this.Cookie = c;
   }
 
   public void redirect(String url)
   {
     this.Response.put("_ZVING_SCRIPT", "window.location=\"" + url + "\";");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.Page
 * JD-Core Version:    0.5.3
 */