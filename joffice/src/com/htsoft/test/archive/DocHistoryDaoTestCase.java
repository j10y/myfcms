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


 
 
 