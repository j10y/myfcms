 package com.htsoft.test.system;
 
 import com.htsoft.oa.dao.system.AppUserDao;
 import com.htsoft.oa.dao.system.DepartmentDao;
 import com.htsoft.oa.model.system.AppRole;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.model.system.Department;
 import com.htsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import java.util.Date;
 import java.util.Iterator;
 import java.util.Set;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class AppUserDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private AppUserDao appUserDao;
 
   @Resource
   private DepartmentDao departmentDao;
 
   @Rollback(false)
   public void add()
   {
     AppUser appUser = new AppUser();
 
     appUser = (AppUser)this.appUserDao.get(Long.valueOf(4L));
     System.out.println(appUser.getAddress());
     Set set = appUser.getRoles();
     Iterator it = set.iterator();
 
     AppRole role = (AppRole)it.next();
     System.out.println(role.getRoleName());
   }
 
   public void addDep()
   {
     Department dep = new Department();
     dep.setDepName("Root Dep");
     dep.setDepLevel(Integer.valueOf(1));
 
     this.departmentDao.save(dep);
   }
 
   @Test
   public void bacthAdd() {
     Department dep = (Department)this.departmentDao.get(Long.valueOf(1L));
     for (int i = 101; i < 102; ++i) {
       AppUser au = new AppUser();
 
       au.setTitle(Short.valueOf(1));
       au.setUsername("user" + i);
       au.setPassword("1");
       au.setFullname("李海" + i);
       au.setAddress("testAddress");
       au.setEducation("test");
       au.setEmail("user" + i + "@htsoft.com");
       au.setAccessionTime(new Date());
       au.setPhoto("photo");
       au.setZip("00003");
       au.setStatus(Short.valueOf(1));
       au.setFax("020-003034034");
       au.setPosition("UserManager");
 
       this.appUserDao.save(au);
     }
   }
 }


 
 
 