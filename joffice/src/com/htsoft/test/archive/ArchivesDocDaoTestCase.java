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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.archive.ArchivesDocDaoTestCase
 * JD-Core Version:    0.5.4
 */