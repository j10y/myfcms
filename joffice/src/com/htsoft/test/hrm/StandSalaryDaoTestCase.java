 package com.htsoft.test.hrm;
 
 import com.htsoft.oa.dao.hrm.StandSalaryDao;
 import com.htsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import java.math.BigDecimal;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class StandSalaryDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private StandSalaryDao standSalaryDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     BigDecimal abc = new BigDecimal("0");
     BigDecimal abc1 = new BigDecimal("1");
     BigDecimal abc2 = new BigDecimal("2");
 
     System.out.println(abc.add(abc1).add(abc2));
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.hrm.StandSalaryDaoTestCase
 * JD-Core Version:    0.5.4
 */