 package com.zving.platform;
 
 import com.zving.framework.Ajax;
 import com.zving.framework.Config;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.license.LicenseInfo;
 import com.zving.framework.utility.DateUtil;
 import com.zving.framework.utility.FileUtil;
 import java.util.Date;
 
 public class License extends Ajax
 {
   public void getRequest()
   {
     String customer = $V("Customer");
     $S("Request", LicenseInfo.getLicenseRequest(customer));
   }
 
   public void saveLicense() {
     String license = $V("License");
     if (LicenseInfo.verifyLicense(license)) {
       FileUtil.writeText(Config.getContextRealPath() + "WEB-INF/classes/license.dat", license);
       LicenseInfo.update();
       this.Response.setMessage("保存成功!");
     } else {
       this.Response.setError("无效的许可证!");
     }
   }
 
   public static boolean needWarning() {
     Date endDate = LicenseInfo.getEndDate();
 
     return (DateUtil.addMonth(endDate, -3).getTime() < System.currentTimeMillis());
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.License
 * JD-Core Version:    0.5.3
 */