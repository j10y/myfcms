 package com.htsoft.test.system;
 
 import com.htsoft.oa.dao.system.RegionDao;
 import com.htsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import java.util.List;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class RegionDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private RegionDao regionDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     System.out.println(this.regionDao.getProvince().size());
     System.out.println(this.regionDao.getCity(Long.valueOf(20L)).size());
   }
 }


 
 
 