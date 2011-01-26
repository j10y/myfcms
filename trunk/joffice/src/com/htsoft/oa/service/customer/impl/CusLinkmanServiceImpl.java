 package com.htsoft.oa.service.customer.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.customer.CusLinkmanDao;
 import com.htsoft.oa.model.customer.CusLinkman;
 import com.htsoft.oa.service.customer.CusLinkmanService;
 
 public class CusLinkmanServiceImpl extends BaseServiceImpl<CusLinkman>
   implements CusLinkmanService
 {
   private CusLinkmanDao dao;
 
   public CusLinkmanServiceImpl(CusLinkmanDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public boolean checkMainCusLinkman(Long customerId, Long linkmanId)
   {
     return this.dao.checkMainCusLinkman(customerId, linkmanId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.impl.CusLinkmanServiceImpl
 * JD-Core Version:    0.5.4
 */