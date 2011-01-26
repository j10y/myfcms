 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.OfficeGoodsDao;
 import com.htsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class OfficeGoodsDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private OfficeGoodsDao officeGoodsDao;
 
   @Test
   public void test1()
   {
     Date date = new Date();
     SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
     System.out.print(sdf.format(date));
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.OfficeGoodsDaoTestCase
 * JD-Core Version:    0.5.4
 */