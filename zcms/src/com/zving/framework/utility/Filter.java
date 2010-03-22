 package com.zving.framework.utility;
 
 public abstract class Filter
 {
   protected Object Param;
 
   public Filter()
   {
   }
 
   public Filter(Object param)
   {
     this.Param = param;
   }
 
   public abstract boolean filter(Object paramObject);
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.Filter
 * JD-Core Version:    0.5.3
 */