 package com.zving.framework.ssi;
 
 import java.io.PrintWriter;
 
 public final class SSIConfig
   implements SSICommand
 {
   public long process(SSIMediator ssiMediator, String commandName, String[] paramNames, String[] paramValues, PrintWriter writer)
   {
     for (int i = 0; i < paramNames.length; ++i) {
       String paramName = paramNames[i];
       String paramValue = paramValues[i];
       String substitutedValue = ssiMediator
         .substituteVariables(paramValue);
       if (paramName.equalsIgnoreCase("errmsg")) {
         ssiMediator.setConfigErrMsg(substitutedValue);
       } else if (paramName.equalsIgnoreCase("sizefmt")) {
         ssiMediator.setConfigSizeFmt(substitutedValue);
       } else if (paramName.equalsIgnoreCase("timefmt")) {
         ssiMediator.setConfigTimeFmt(substitutedValue);
       } else {
         ssiMediator.log("#config--Invalid attribute: " + paramName);
 
         String configErrMsg = ssiMediator.getConfigErrMsg();
         writer.write(configErrMsg);
       }
     }
 
     return 0L;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.SSIConfig
 * JD-Core Version:    0.5.3
 */