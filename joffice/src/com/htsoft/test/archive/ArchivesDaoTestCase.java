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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.archive.ArchivesDaoTestCase
 * JD-Core Version:    0.5.4
 */