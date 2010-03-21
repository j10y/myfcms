 package com.zving.workflow.util;
 
 import com.zving.framework.utility.StringUtil;
 import com.zving.workflow.Function;
 import java.io.PrintStream;
 import java.util.Map;
 import org.apache.commons.mail.EmailException;
 import org.apache.commons.mail.SimpleEmail;
 
 public class RemindEmailService
   implements Function
 {
   public boolean execute(Map transientVars, Map args)
   {
     String subject = StringUtil.noNull((String)args.get("subject"));
     String msg = StringUtil.noNull((String)args.get("msg"));
 
     SimpleEmail email = new SimpleEmail();
     email.setHostName("mail.zving.com");
     try {
       email.addTo("huanglei@zving.com", "huanglei");
       email.setFrom("huanglei@zving.com", "huanglei");
       email.setSubject(subject);
       email.setMsg(msg);
       System.out.println(email.send());
     } catch (EmailException e) {
       e.printStackTrace();
       return false;
     }
     return true;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.util.RemindEmailService
 * JD-Core Version:    0.5.3
 */