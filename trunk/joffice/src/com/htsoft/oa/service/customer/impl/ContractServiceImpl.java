 package com.htsoft.oa.service.customer.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.customer.ContractDao;
 import com.htsoft.oa.model.customer.Contract;
 import com.htsoft.oa.service.customer.ContractService;
 
 public class ContractServiceImpl extends BaseServiceImpl<Contract>
   implements ContractService
 {
   private ContractDao dao;
 
   public ContractServiceImpl(ContractDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.impl.ContractServiceImpl
 * JD-Core Version:    0.5.4
 */