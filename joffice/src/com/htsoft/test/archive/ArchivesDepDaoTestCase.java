 package com.htsoft.test.archive;
 
 import com.htsoft.oa.dao.archive.ArchivesDepDao;
 import com.htsoft.oa.model.archive.ArchivesDep;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ArchivesDepDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ArchivesDepDao archivesDepDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ArchivesDep archivesDep = new ArchivesDep();
 
     this.archivesDepDao.save(archivesDep);
   }
 }


 
 
 