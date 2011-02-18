 package com.htsoft.test.archive;
 
 import com.htsoft.oa.dao.archive.ArchivesTypeDao;
 import com.htsoft.oa.model.archive.ArchivesType;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ArchivesTypeDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ArchivesTypeDao archivesTypeDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ArchivesType archivesType = new ArchivesType();
 
     this.archivesTypeDao.save(archivesType);
   }
 }


 
 
 