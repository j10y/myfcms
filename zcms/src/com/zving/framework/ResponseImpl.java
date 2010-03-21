 package com.zving.framework;
 
 import com.zving.framework.data.DataCollection;
 
 public class ResponseImpl extends DataCollection
 {
   private static final long serialVersionUID = 1L;
   public int Status = 1;
 
   public String Message = "";
 
   public String getMessage()
   {
     return this.Message;
   }
 
   public void setError(String message)
   {
     setMessage(message);
     setStatus(0);
   }
 
   public void setMessage(String message)
   {
     this.Message = message;
     put("_ZVING_MESSAGE", this.Message);
   }
 
   public int getStatus()
   {
     return this.Status;
   }
 
   public void setStatus(int status)
   {
     this.Status = status;
     put("_ZVING_STATUS", this.Status);
   }
 
   public void setLogInfo(int status, String message)
   {
     this.Status = status;
     put("_ZVING_STATUS", this.Status);
     this.Message = message;
     put("_ZVING_MESSAGE", this.Message);
   }
 
   public String toXML()
   {
     put("_ZVING_STATUS", this.Status);
     return super.toXML();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ResponseImpl
 * JD-Core Version:    0.5.3
 */