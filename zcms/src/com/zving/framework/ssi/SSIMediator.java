 package com.zving.framework.ssi;
 
 import java.io.IOException;
 import java.util.Collection;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.Set;
 import java.util.TimeZone;
 
 public class SSIMediator
 {
   protected static final String DEFAULT_CONFIG_ERR_MSG = "[an error occurred while processing this directive]";
   protected static final String DEFAULT_CONFIG_TIME_FMT = "%A, %d-%b-%Y %T %Z";
   protected static final String DEFAULT_CONFIG_SIZE_FMT = "abbrev";
   protected static URLEncoder urlEncoder = new URLEncoder();
 
   protected String configErrMsg = "[an error occurred while processing this directive]";
   protected String configTimeFmt = "%A, %d-%b-%Y %T %Z";
   protected String configSizeFmt = "abbrev";
   protected String className = ???.getClass().getName();
   protected SSIExternalResolver ssiExternalResolver;
   protected long lastModifiedDate;
   protected int debug;
   protected Strftime strftime;
   protected SSIConditionalState conditionalState = new SSIConditionalState();
 
   static
   {
     urlEncoder.addSafeCharacter(',');
     urlEncoder.addSafeCharacter(':');
     urlEncoder.addSafeCharacter('-');
     urlEncoder.addSafeCharacter('_');
     urlEncoder.addSafeCharacter('.');
     urlEncoder.addSafeCharacter('*');
     urlEncoder.addSafeCharacter('/');
     urlEncoder.addSafeCharacter('!');
     urlEncoder.addSafeCharacter('~');
     urlEncoder.addSafeCharacter('\'');
     urlEncoder.addSafeCharacter('(');
     urlEncoder.addSafeCharacter(')');
   }
 
   public SSIMediator(SSIExternalResolver ssiExternalResolver, long lastModifiedDate, int debug)
   {
     this.ssiExternalResolver = ssiExternalResolver;
     this.lastModifiedDate = lastModifiedDate;
     this.debug = debug;
     setConfigTimeFmt("%A, %d-%b-%Y %T %Z", true);
   }
 
   public void setConfigErrMsg(String configErrMsg)
   {
     this.configErrMsg = configErrMsg;
   }
 
   public void setConfigTimeFmt(String configTimeFmt)
   {
     setConfigTimeFmt(configTimeFmt, false);
   }
 
   public void setConfigTimeFmt(String configTimeFmt, boolean fromConstructor)
   {
     this.configTimeFmt = configTimeFmt;
 
     this.strftime = new Strftime(configTimeFmt, DateTool.LOCALE_US);
 
     setDateVariables(fromConstructor);
   }
 
   public void setConfigSizeFmt(String configSizeFmt)
   {
     this.configSizeFmt = configSizeFmt;
   }
 
   public String getConfigErrMsg()
   {
     return this.configErrMsg;
   }
 
   public String getConfigTimeFmt()
   {
     return this.configTimeFmt;
   }
 
   public String getConfigSizeFmt()
   {
     return this.configSizeFmt;
   }
 
   public SSIConditionalState getConditionalState()
   {
     return this.conditionalState;
   }
 
   public Collection getVariableNames()
   {
     Set variableNames = new HashSet();
 
     variableNames.add("DATE_GMT");
     variableNames.add("DATE_LOCAL");
     variableNames.add("LAST_MODIFIED");
     this.ssiExternalResolver.addVariableNames(variableNames);
 
     Iterator iter = variableNames.iterator();
     while (iter.hasNext()) {
       String name = (String)iter.next();
       if (isNameReserved(name)) {
         iter.remove();
       }
     }
     return variableNames;
   }
 
   public long getFileSize(String path, boolean virtual) throws IOException
   {
     return this.ssiExternalResolver.getFileSize(path, virtual);
   }
 
   public long getFileLastModified(String path, boolean virtual)
     throws IOException
   {
     return this.ssiExternalResolver.getFileLastModified(path, virtual);
   }
 
   public String getFileText(String path, boolean virtual) throws IOException
   {
     return this.ssiExternalResolver.getFileText(path, virtual);
   }
 
   protected boolean isNameReserved(String name)
   {
     return name.startsWith(this.className + ".");
   }
 
   public String getVariableValue(String variableName)
   {
     return getVariableValue(variableName, "none");
   }
 
   public void setVariableValue(String variableName, String variableValue)
   {
     if (!(isNameReserved(variableName)))
       this.ssiExternalResolver.setVariableValue(variableName, variableValue);
   }
 
   public String getVariableValue(String variableName, String encoding)
   {
     String lowerCaseVariableName = variableName.toLowerCase();
     String variableValue = null;
     if (!(isNameReserved(lowerCaseVariableName)))
     {
       variableValue = this.ssiExternalResolver.getVariableValue(variableName);
       if (variableValue == null) {
         variableName = variableName.toUpperCase();
         variableValue = this.ssiExternalResolver
           .getVariableValue(this.className + "." + variableName);
       }
       if (variableValue != null) {
         variableValue = encode(variableValue, encoding);
       }
     }
     return variableValue;
   }
 
   public String substituteVariables(String val)
   {
     if (val.indexOf(36) < 0) return val;
     StringBuffer sb = new StringBuffer(val);
     int i = 0; break label220:
 
     if (sb.charAt(i) == '$')
       ++i;
     else
       ++i; do { if (i >= sb.length());
       if (i == sb.length())
         break;
       if ((i > 1) && (sb.charAt(i - 2) == '\\')) {
         sb.deleteCharAt(i - 2);
         label220: --i;
       }
       else {
         int nameStart = i;
         int start = i - 1;
         int end = -1;
         int nameEnd = -1;
         char endChar = ' ';
 
         if (sb.charAt(i) == '{') {
           ++nameStart;
           endChar = '}';
         }
 
         for (; i < sb.length(); ++i)
           if (sb.charAt(i) == endChar)
             break;
         end = i;
         nameEnd = end;
         if (endChar == '}') ++end;
 
         String varName = sb.substring(nameStart, nameEnd);
         String value = getVariableValue(varName);
         if (value == null) value = "";
 
         sb.replace(start, end, value);
 
         i = start + value.length();
       }
     }
     while (i < sb.length());
 
     return sb.toString();
   }
 
   protected String formatDate(Date date, TimeZone timeZone)
   {
     String retVal;
     if (timeZone != null)
     {
       TimeZone oldTimeZone = this.strftime.getTimeZone();
       this.strftime.setTimeZone(timeZone);
       retVal = this.strftime.format(date);
       this.strftime.setTimeZone(oldTimeZone);
     } else {
       retVal = this.strftime.format(date);
     }
     return retVal;
   }
 
   protected String encode(String value, String encoding)
   {
     String retVal = null;
     if (encoding.equalsIgnoreCase("url"))
       retVal = urlEncoder.encode(value);
     else if (encoding.equalsIgnoreCase("none"))
       retVal = value;
     else if (encoding.equalsIgnoreCase("entity"))
     {
       retVal = value;
     }
     else {
       throw new IllegalArgumentException("Unknown encoding: " + encoding);
     }
     return retVal;
   }
 
   public void log(String message)
   {
     this.ssiExternalResolver.log(message, null);
   }
 
   public void log(String message, Throwable throwable)
   {
     this.ssiExternalResolver.log(message, throwable);
   }
 
   protected void setDateVariables(boolean fromConstructor)
   {
     boolean alreadySet = this.ssiExternalResolver.getVariableValue(this.className + 
       ".alreadyset") != null;
 
     if ((!(fromConstructor)) || (!(alreadySet))) {
       this.ssiExternalResolver.setVariableValue(this.className + ".alreadyset", 
         "true");
       Date date = new Date();
       TimeZone timeZone = TimeZone.getTimeZone("GMT");
       String retVal = formatDate(date, timeZone);
 
       setVariableValue("DATE_GMT", null);
       this.ssiExternalResolver.setVariableValue(this.className + ".DATE_GMT", 
         retVal);
       retVal = formatDate(date, null);
       setVariableValue("DATE_LOCAL", null);
       this.ssiExternalResolver.setVariableValue(this.className + ".DATE_LOCAL", 
         retVal);
       retVal = formatDate(new Date(this.lastModifiedDate), null);
       setVariableValue("LAST_MODIFIED", null);
       this.ssiExternalResolver.setVariableValue(this.className + ".LAST_MODIFIED", 
         retVal);
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.SSIMediator
 * JD-Core Version:    0.5.3
 */