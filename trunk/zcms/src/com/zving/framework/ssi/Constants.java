 package com.zving.framework.ssi;
 
 import java.util.Locale;
 
 public final class Constants
 {
   public static final String DEFAULT_CHARACTER_ENCODING = "ISO-8859-1";
   public static final String LOCALE_DEFAULT = "en";
   public static final Locale DEFAULT_LOCALE = new Locale("en", "");
   public static final int MAX_NOTES = 32;
   public static final int STAGE_NEW = 0;
   public static final int STAGE_PARSE = 1;
   public static final int STAGE_PREPARE = 2;
   public static final int STAGE_SERVICE = 3;
   public static final int STAGE_ENDINPUT = 4;
   public static final int STAGE_ENDOUTPUT = 5;
   public static final int STAGE_KEEPALIVE = 6;
   public static final int STAGE_ENDED = 7;
   public static final boolean USE_CUSTOM_STATUS_MSG_IN_HEADER = Boolean.valueOf(System.getProperty(
     "org.apache.coyote.USE_CUSTOM_STATUS_MSG_IN_HEADER", 
     "false")).booleanValue();
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.Constants
 * JD-Core Version:    0.5.3
 */