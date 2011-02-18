 package com.htsoft.test.system;
 
 import com.htsoft.oa.dao.system.DiaryDao;
 import com.htsoft.oa.model.system.Diary;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class DiaryDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DiaryDao diaryDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Diary diary = new Diary();
 
     this.diaryDao.save(diary);
   }
 }


 
 
 