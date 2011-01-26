 package com.htsoft.test.system;
 
 import com.htsoft.oa.model.system.AppFunction;
 import com.htsoft.oa.model.system.AppRole;
 import com.htsoft.oa.service.system.AppFunctionService;
 import com.htsoft.oa.service.system.AppRoleService;
 import com.htsoft.test.BaseTestCase;
 import java.util.Set;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class AppRoleTestCase extends BaseTestCase
 {
 
   @Resource
   private AppRoleService appRoleService;
 
   @Resource
   private AppFunctionService appFunctionService;
 
   public void testMerge()
   {
     AppRole role = new AppRole();
     role.setRoleId(Long.valueOf(1L));
     role.setStatus(Short.valueOf(0));
     this.appRoleService.merge(role);
   }
 
   @Test
   public void updateFunctions() {
     AppRole role = (AppRole)this.appRoleService.get(Long.valueOf(3L));
     for (int id = 1; id <= 2; ++id) {
       AppFunction appFunction = (AppFunction)this.appFunctionService.get(new Long(id));
       role.getFunctions().add(appFunction);
     }
     this.appRoleService.save(role);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.AppRoleTestCase
 * JD-Core Version:    0.5.4
 */