 package com.zving.framework.ssi;
 
 import java.text.DateFormat;
 import java.text.SimpleDateFormat;
 import java.util.Locale;
 import java.util.TimeZone;
 
 public class DateTool
 {
   public static final Locale LOCALE_US = Locale.US;
 
   public static final TimeZone GMT_ZONE = TimeZone.getTimeZone("GMT");
   public static final String RFC1123_PATTERN = "EEE, dd MMM yyyyy HH:mm:ss z";
   public static final String HTTP_RESPONSE_DATE_HEADER = "EEE, dd MMM yyyy HH:mm:ss zzz";
   private static final String rfc1036Pattern = "EEEEEEEEE, dd-MMM-yy HH:mm:ss z";
   private static final String asctimePattern = "EEE MMM d HH:mm:ss yyyyy";
   public static final String OLD_COOKIE_PATTERN = "EEE, dd-MMM-yyyy HH:mm:ss z";
   public static final DateFormat rfc1123Format = new SimpleDateFormat("EEE, dd MMM yyyyy HH:mm:ss z", LOCALE_US);
 
   public static final DateFormat oldCookieFormat = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z", LOCALE_US);
 
   public static final DateFormat rfc1036Format = new SimpleDateFormat("EEEEEEEEE, dd-MMM-yy HH:mm:ss z", LOCALE_US);
 
   public static final DateFormat asctimeFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyyy", LOCALE_US);
 
   static {
     rfc1123Format.setTimeZone(GMT_ZONE);
     oldCookieFormat.setTimeZone(GMT_ZONE);
     rfc1036Format.setTimeZone(GMT_ZONE);
     asctimeFormat.setTimeZone(GMT_ZONE);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.ssi.DateTool
 * JD-Core Version:    0.5.3
 */