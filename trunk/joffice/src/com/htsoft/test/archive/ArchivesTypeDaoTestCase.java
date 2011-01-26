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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.archive.ArchivesTypeDaoTestCase
 * JD-Core Version:    0.5.4
 */