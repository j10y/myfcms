 package com.htsoft.test.system;
 
 import com.htsoft.oa.dao.system.DictionaryDao;
 import com.htsoft.oa.model.system.Dictionary;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DictionaryDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DictionaryDao dictionaryDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Dictionary dictionary = new Dictionary();
 
     this.dictionaryDao.save(dictionary);
   }
 }


 
 
 