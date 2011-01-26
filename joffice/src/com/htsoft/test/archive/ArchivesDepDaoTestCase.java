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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.archive.ArchivesDepDaoTestCase
 * JD-Core Version:    0.5.4
 */