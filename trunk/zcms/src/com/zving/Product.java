 package com.zving;
 
 import com.zving.framework.license.IProduct;
 
 public class Product
   implements IProduct
 {
   public String getAppCode()
   {
     return "ZCMS";
   }
 
   public String getAppName() {
     return "泽元网站内容管理系统";
   }
 
   public float getMainVersion() {
     return 1.2F;
   }
 
   public float getMinorVersion() {
     return 0.1F;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.Product
 * JD-Core Version:    0.5.3
 */