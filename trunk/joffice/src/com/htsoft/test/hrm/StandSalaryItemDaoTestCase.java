 package com.htsoft.test.hrm;
 
 import com.htsoft.oa.dao.hrm.StandSalaryItemDao;
 import com.htsoft.oa.model.hrm.StandSalaryItem;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class StandSalaryItemDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private StandSalaryItemDao standSalaryItemDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     StandSalaryItem standSalaryItem = new StandSalaryItem();
 
     this.standSalaryItemDao.save(standSalaryItem);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.hrm.StandSalaryItemDaoTestCase
 * JD-Core Version:    0.5.4
 */