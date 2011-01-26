 package com.htsoft.oa.service.admin.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.admin.InStockDao;
 import com.htsoft.oa.model.admin.InStock;
 import com.htsoft.oa.service.admin.InStockService;
 
 public class InStockServiceImpl extends BaseServiceImpl<InStock>
   implements InStockService
 {
   private InStockDao dao;
 
   public InStockServiceImpl(InStockDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public Integer findInCountByBuyId(Long buyId)
   {
     return this.dao.findInCountByBuyId(buyId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.InStockServiceImpl
 * JD-Core Version:    0.5.4
 */