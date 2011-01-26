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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.system.DiaryDaoTestCase
 * JD-Core Version:    0.5.4
 */