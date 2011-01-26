 package com.htsoft.oa.service.customer.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.customer.ContractConfigDao;
 import com.htsoft.oa.model.customer.ContractConfig;
 import com.htsoft.oa.service.customer.ContractConfigService;
 
 public class ContractConfigServiceImpl extends BaseServiceImpl<ContractConfig>
   implements ContractConfigService
 {
   private ContractConfigDao dao;
 
   public ContractConfigServiceImpl(ContractConfigDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.customer.impl.ContractConfigServiceImpl
 * JD-Core Version:    0.5.4
 */