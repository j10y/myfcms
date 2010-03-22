 package com.zving.framework;
 
 import com.zving.framework.data.DataCollection;
 
 public class RequestImpl extends DataCollection
 {
   private static final long serialVersionUID = 1L;
   private String URL;
   private String ClientIP;
   private String ClassName;
   private String ServerName;
   private int Port;
   private String Scheme;
 
   public String getServerName()
   {
     return this.ServerName;
   }
 
   public void setServerName(String serverName)
   {
     this.ServerName = serverName;
   }
 
   public int getPort()
   {
     return this.Port;
   }
 
   public void setPort(int port)
   {
     this.Port = port;
   }
 
   public String getScheme()
   {
     return this.Scheme;
   }
 
   public void setScheme(String scheme)
   {
     this.Scheme = scheme;
   }
 
   public String getClassName()
   {
     return this.ClassName;
   }
 
   public void setClassName(String className)
   {
     this.ClassName = className;
   }
 
   public String getClientIP()
   {
     return this.ClientIP;
   }
 
   public void setClientIP(String clientIP)
   {
     this.ClientIP = clientIP;
   }
 
   public String getURL()
   {
     return this.URL;
   }
 
   public void setURL(String url)
   {
     this.URL = url;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.RequestImpl
 * JD-Core Version:    0.5.3
 */