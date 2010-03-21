 package com.zving.framework.ssi;
 
 import java.io.PrintWriter;
 
 public class SSIEcho
   implements SSICommand
 {
   protected static final String DEFAULT_ENCODING = "entity";
   protected static final String MISSING_VARIABLE_VALUE = "(none)";
 
   public long process(SSIMediator ssiMediator, String commandName, String[] paramNames, String[] paramValues, PrintWriter writer)
   {
     long lastModified = 0L;
     String encoding = "entity";
     String errorMessage = ssiMediator.getConfigErrMsg();
     for (int i = 0; i < paramNames.length; ++i) {
       String paramName = paramNames[i];
       String paramValue = paramValues[i];
       if (paramName.equalsIgnoreCase("var")) {
         String variableValue = ssiMediator.getVariableValue(
           paramValue, encoding);
         if (variableValue == null) {
           variableValue = "(none)";
         }
         writer.write(variableValue);
         lastModified = System.currentTimeMillis();
       } else if (paramName.equalsIgnoreCase("encoding")) {
         if (isValidEncoding(paramValue)) {
           encoding = paramValue;
         } else {
           ssiMediator.log("#echo--Invalid encoding: " + paramValue);
           writer.write(errorMessage);
         }
       } else {
         ssiMediator.log("#echo--Invalid attribute: " + paramName);
         writer.write(errorMessage);
       }
     }
     return lastModified;
   }
 
   protected boolean isValidEncoding(String encoding)
   {
     return ((encoding.equalsIgnoreCase("url")) || 
       (encoding.equalsIgnoreCase("entity")) || 
       (encoding.equalsIgnoreCase("none")));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.SSIEcho
 * JD-Core Version:    0.5.3
 */