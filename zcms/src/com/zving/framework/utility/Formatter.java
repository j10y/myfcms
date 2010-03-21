 package com.zving.framework.utility;
 
 import java.lang.reflect.Array;
 
 public abstract class Formatter
 {
   public static Formatter DefaultFormatter = new Formatter() {
     public String format(Object o) {
       if (o == null) {
         return null;
       }
       if (o.getClass().isArray()) {
         StringBuffer sb = new StringBuffer();
         sb.append("{");
         for (int i = 0; i < Array.getLength(o); ++i) {
           if (i != 0) {
             sb.append(",");
           }
           sb.append(Array.get(o, i));
         }
         sb.append("}");
         return sb.toString();
       }
       return o.toString();
     }
   };
 
   public abstract String format(Object paramObject);
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.Formatter
 * JD-Core Version:    0.5.3
 */