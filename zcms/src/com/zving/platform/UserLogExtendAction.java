 package com.zving.platform;
 
 import com.zving.framework.Current;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.extend.AfterPageMethodInvokeAction;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZDUserLogSchema;
 import java.util.Date;
 
 public class UserLogExtendAction extends AfterPageMethodInvokeAction
 {
   Transaction tran = new Transaction();
 
   public void execute(String method)
   {
   }
 
   private void insertLog()
   {
     ZDUserLogSchema userlog = new ZDUserLogSchema();
     userlog.setUserName(User.getUserName());
     userlog.setIP(Current.getRequest().getClientIP());
     userlog.setAddTime(new Date());
     userlog.setLogID(NoUtil.getMaxID("LogID"));
     userlog.setLogType("Menu");
     userlog.setLogMessage(Current.getResponse().getMessage());
     this.tran.add(userlog, 1);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.UserLogExtendAction
 * JD-Core Version:    0.5.3
 */