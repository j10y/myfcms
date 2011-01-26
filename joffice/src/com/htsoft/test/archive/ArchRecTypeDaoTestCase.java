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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.archive.ArchRecTypeDaoTestCase
 * JD-Core Version:    0.5.4
 */