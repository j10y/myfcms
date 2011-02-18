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


 
 
 