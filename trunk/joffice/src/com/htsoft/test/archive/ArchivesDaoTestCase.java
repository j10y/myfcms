 package com.htsoft.test.archive;
 
 import com.htsoft.oa.dao.archive.ArchivesDao;
 import com.htsoft.oa.model.archive.Archives;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ArchivesDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ArchivesDao archivesDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Archives archives = new Archives();
 
     this.archivesDao.save(archives);
   }
 }


 
 
 