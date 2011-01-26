 package com.htsoft.test.archive;
 
 import com.htsoft.oa.dao.archive.DocHistoryDao;
 import com.htsoft.oa.model.archive.DocHistory;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DocHistoryDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DocHistoryDao docHistoryDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     DocHistory docHistory = new DocHistory();
 
     this.docHistoryDao.save(docHistory);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.archive.DocHistoryDaoTestCase
 * JD-Core Version:    0.5.4
 */