 package com.htsoft.test.archive;
 
 import com.htsoft.oa.dao.archive.ArchRecTypeDao;
 import com.htsoft.oa.model.archive.ArchRecType;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ArchRecTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ArchRecTypeDao archRecTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ArchRecType archRecType = new ArchRecType();
 
     this.archRecTypeDao.save(archRecType);
   }
 }


 
 
 