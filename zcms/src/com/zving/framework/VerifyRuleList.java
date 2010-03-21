 package com.zving.framework;
 
 import java.util.ArrayList;
 
 public class VerifyRuleList
 {
   private ArrayList array = new ArrayList();
   private String Message;
   private RequestImpl Request;
   private ResponseImpl Response;
 
   public void add(String fieldID, String fieldName, String rule)
   {
     this.array.add(new String[] { fieldID, fieldName, rule });
   }
 
   public boolean doVerify()
   {
     VerifyRule rule = new VerifyRule();
     boolean flag = true;
     StringBuffer sb = new StringBuffer();
     for (int i = 0; i < this.array.size(); ++i) {
       String[] f = (String[])this.array.get(i);
       rule.setRule(f[2]);
       if (!(rule.verify(this.Request.getString(f[0])))) {
         sb.append(rule.getMessages(f[1]));
         flag = false;
       }
     }
     if (!(flag)) {
       this.Response.setStatus(0);
       this.Message = sb.toString();
       this.Response.setMessage(sb.toString());
     }
     return flag;
   }
 
   public String getMessage() {
     return this.Message;
   }
 
   public RequestImpl getRequest() {
     return this.Request;
   }
 
   public void setRequest(RequestImpl request) {
     this.Request = request;
   }
 
   public ResponseImpl getResponse() {
     return this.Response;
   }
 
   public void setResponse(ResponseImpl response) {
     this.Response = response;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.VerifyRuleList
 * JD-Core Version:    0.5.3
 */