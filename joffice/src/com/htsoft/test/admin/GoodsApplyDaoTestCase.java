 package com.htsoft.test.admin;
 
 import com.htsoft.oa.dao.admin.GoodsApplyDao;
 import com.htsoft.oa.model.admin.GoodsApply;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class GoodsApplyDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private GoodsApplyDao goodsApplyDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     GoodsApply goodsApply = new GoodsApply();
 
     this.goodsApplyDao.save(goodsApply);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.admin.GoodsApplyDaoTestCase
 * JD-Core Version:    0.5.4
 */