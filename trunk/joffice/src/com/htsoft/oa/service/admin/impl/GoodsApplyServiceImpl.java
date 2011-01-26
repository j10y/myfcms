 package com.htsoft.oa.service.admin.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.admin.GoodsApplyDao;
 import com.htsoft.oa.model.admin.GoodsApply;
 import com.htsoft.oa.service.admin.GoodsApplyService;
 
 public class GoodsApplyServiceImpl extends BaseServiceImpl<GoodsApply>
   implements GoodsApplyService
 {
   private GoodsApplyDao dao;
 
   public GoodsApplyServiceImpl(GoodsApplyDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.GoodsApplyServiceImpl
 * JD-Core Version:    0.5.4
 */