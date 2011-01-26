 package com.htsoft.test.flow;
 
 import com.htsoft.oa.dao.flow.FormDefDao;
 import com.htsoft.oa.model.flow.FormDef;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class FormDefDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private FormDefDao formDefDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     FormDef formDef = new FormDef();
 
     this.formDefDao.save(formDef);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.flow.FormDefDaoTestCase
 * JD-Core Version:    0.5.4
 */