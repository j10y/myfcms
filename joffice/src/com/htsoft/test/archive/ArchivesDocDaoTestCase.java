 package com.htsoft.test.archive;
 
 import com.htsoft.oa.dao.archive.ArchivesDocDao;
 import com.htsoft.oa.model.archive.ArchivesDoc;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ArchivesDocDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ArchivesDocDao archivesDocDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ArchivesDoc archivesDoc = new ArchivesDoc();
 
     this.archivesDocDao.save(archivesDoc);
   }
 }


 
 
 