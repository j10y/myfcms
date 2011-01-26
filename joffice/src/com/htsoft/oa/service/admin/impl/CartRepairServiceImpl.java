 package com.htsoft.oa.service.admin.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.admin.CartRepairDao;
 import com.htsoft.oa.model.admin.CartRepair;
 import com.htsoft.oa.service.admin.CartRepairService;
 
 public class CartRepairServiceImpl extends BaseServiceImpl<CartRepair>
   implements CartRepairService
 {
   private CartRepairDao dao;
 
   public CartRepairServiceImpl(CartRepairDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.CartRepairServiceImpl
 * JD-Core Version:    0.5.4
 */