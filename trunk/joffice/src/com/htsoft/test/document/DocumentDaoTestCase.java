 package com.htsoft.test.document;
 
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.document.DocumentDao;
 import com.htsoft.oa.dao.system.AppUserDao;
 import com.htsoft.oa.model.document.Document;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.test.BaseTestCase;
 import java.io.PrintStream;
 import java.util.List;
 import javax.annotation.Resource;
 import org.junit.Test;
 
 public class DocumentDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private DocumentDao documentDao;
 
   @Resource
   private AppUserDao appUserDao;
 
   @Test
   public void tesss()
   {
     AppUser user = (AppUser)this.appUserDao.get(Long.valueOf(2L));
 
     PagingBean pb = new PagingBean(0, 6);
     Document document = (Document)this.documentDao.get(Long.valueOf(6L));
     List docs = this.documentDao.findByPersonal(Long.valueOf(2L), null, null, null, null, pb);
     System.out.println("size:" + docs.size());
   }
 }


 
 
 