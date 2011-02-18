 package com.htsoft.test.archive;
 
 import com.htsoft.oa.dao.archive.ArchTemplateDao;
 import com.htsoft.oa.model.archive.ArchTemplate;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class ArchTemplateDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private ArchTemplateDao archTemplateDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     ArchTemplate archTemplate = new ArchTemplate();
 
     this.archTemplateDao.save(archTemplate);
   }
 }


 
 
 