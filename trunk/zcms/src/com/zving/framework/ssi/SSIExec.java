 package com.zving.framework.ssi;
 
 import java.io.PrintWriter;
 
 public class SSIExec
   implements SSICommand
 {
   protected SSIInclude ssiInclude = new SSIInclude();
   protected static final int BUFFER_SIZE = 1024;
 
   public long process(SSIMediator ssiMediator, String commandName, String[] paramNames, String[] paramValues, PrintWriter writer)
   {
     long lastModified = 0L;
     String paramName = paramNames[0];
     String paramValue = paramValues[0];
     String substitutedValue = ssiMediator.substituteVariables(paramValue);
     if (paramName.equalsIgnoreCase("cgi"))
       lastModified = this.ssiInclude.process(ssiMediator, "include", new String[] { "virtual" }, 
         new String[] { substitutedValue }, writer);
     else if (paramName.equalsIgnoreCase("cmd")) {
       ssiMediator.log("SSI cmd exec is no supported! ");
     }
 
     return lastModified;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.SSIExec
 * JD-Core Version:    0.5.3
 */