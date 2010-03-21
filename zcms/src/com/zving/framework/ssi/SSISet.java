 package com.zving.framework.ssi;
 
 import java.io.PrintWriter;
 
 public class SSISet
   implements SSICommand
 {
   public long process(SSIMediator ssiMediator, String commandName, String[] paramNames, String[] paramValues, PrintWriter writer)
     throws SSIStopProcessingException
   {
     long lastModified = 0L;
     String errorMessage = ssiMediator.getConfigErrMsg();
     String variableName = null;
     for (int i = 0; i < paramNames.length; ++i) {
       String paramName = paramNames[i];
       String paramValue = paramValues[i];
       if (paramName.equalsIgnoreCase("var")) {
         variableName = paramValue; } else {
         if (paramName.equalsIgnoreCase("value")) {
           if (variableName != null) {
             String substitutedValue = ssiMediator
               .substituteVariables(paramValue);
             ssiMediator.setVariableValue(variableName, 
               substitutedValue);
             lastModified = System.currentTimeMillis(); continue;
           }
           ssiMediator.log("#set--no variable specified");
           writer.write(errorMessage);
           throw new SSIStopProcessingException();
         }
 
         ssiMediator.log("#set--Invalid attribute: " + paramName);
         writer.write(errorMessage);
         throw new SSIStopProcessingException();
       }
     }
     return lastModified;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.SSISet
 * JD-Core Version:    0.5.3
 */