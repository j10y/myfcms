 package com.zving.framework.utility;
 
 import java.util.ArrayList;
 
 public class Errorx
 {
   protected ArrayList list = new ArrayList();
 
   protected boolean ErrorFlag = false;
 
   protected boolean ErrorDealedFlag = true;
 
   protected static ThreadLocal ErrorXLocal = new ThreadLocal();
 
   public static void addMessage(String message)
   {
     add(message, false);
   }
 
   public static void addError(String message)
   {
     add(message, true);
   }
 
   private static void add(String message, boolean isError)
   {
     Message msg = new Message();
     msg.isError = isError;
     msg.Message = message;
     if (isError) {
       getCurrent().ErrorFlag = true;
       getCurrent().ErrorDealedFlag = false;
       StackTraceElement[] stack = new Throwable().getStackTrace();
       StringBuffer sb = new StringBuffer();
       sb.append("com.zving.framework.utility.Errorx : ");
       sb.append(message);
       sb.append("\n");
       for (int i = 2; i < stack.length; ++i) {
         StackTraceElement ste = stack[i];
         if (ste.getClassName().indexOf("DBConnPool") == -1) {
           sb.append("\tat ");
           sb.append(ste.getClassName());
           sb.append(".");
           sb.append(ste.getMethodName());
           sb.append("(");
           sb.append(ste.getFileName());
           sb.append(":");
           sb.append(ste.getLineNumber());
           sb.append(")\n");
         }
       }
       msg.StackTrace = sb.toString();
     }
     getCurrent().list.add(msg);
   }
 
   public static ArrayList getErrors()
   {
     return getCurrent().list;
   }
 
   public static boolean hasError()
   {
     return getCurrent().ErrorFlag;
   }
 
   public static boolean hasDealed()
   {
     return getCurrent().ErrorDealedFlag;
   }
 
   public void clear()
   {
     getCurrent().list.clear();
     getCurrent().ErrorFlag = false;
     getCurrent().ErrorDealedFlag = true;
   }
 
   public static String printString()
   {
     StringBuffer sb = new StringBuffer();
     int i = 0; for (int j = 1; i < getCurrent().list.size(); ++i) {
       Message msg = (Message)getCurrent().list.get(i);
       if (!(msg.isError)) {
         continue;
       }
       sb.append("错误：");
       sb.append(msg.Message);
       sb.append("<br>\n");
 
       ++j;
     }
 
     for (i = 0; i < getCurrent().list.size(); ++i) {
       Message msg = (Message)getCurrent().list.get(i);
       if (msg.isError) {
         continue;
       }
       sb.append("提示：");
       sb.append(msg.Message);
       sb.append("\n");
     }
 
     getCurrent().ErrorDealedFlag = true;
     return sb.toString();
   }
 
   public static String[] getMessages()
   {
     String[] arr = new String[getCurrent().list.size()];
     for (int i = 0; i < arr.length; ++i) {
       arr[i] = ((Message)getCurrent().list.get(i)).Message;
     }
     getCurrent().clear();
     return arr;
   }
 
   public static void init()
   {
     Object t = ErrorXLocal.get();
     if ((t == null) || (!(Errorx.class.isInstance(t))))
       t = new Errorx();
     else
       ((Errorx)t).clear();
   }
 
   public static Errorx getCurrent()
   {
     Object t = ErrorXLocal.get();
     if ((t == null) || (!(Errorx.class.isInstance(t)))) {
       t = new Errorx();
       ErrorXLocal.set(t);
     }
     return ((Errorx)t);
   }
 
   static class Message
   {
     public boolean isError;
     public String Message;
     public String StackTrace;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.Errorx
 * JD-Core Version:    0.5.3
 */