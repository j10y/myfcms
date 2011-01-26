 package com.htsoft.test.communicate;
 
 import com.htsoft.oa.dao.communicate.PhoneBookDao;
 import com.htsoft.test.BaseTestCase;
 import flexjson.JSONSerializer;
 import java.io.PrintStream;
 import java.util.List;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class PhoneBookDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private PhoneBookDao phoneBookDao;
 
   @Test
   public void test()
   {
     List phoneBook = this.phoneBookDao.getAll();
 
     JSONSerializer serializer = new JSONSerializer();
 
     System.out.println("josn:" + serializer.exclude(new String[] { "class", "phoneGroup", "appUser.department" }).prettyPrint(phoneBook));
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.communicate.PhoneBookDaoTestCase
 * JD-Core Version:    0.5.4
 */