 package com.htsoft.oa.service.flow.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.flow.ProDefinitionDao;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.service.flow.ProDefinitionService;
 
 public class ProDefinitionServiceImpl extends BaseServiceImpl<ProDefinition>
   implements ProDefinitionService
 {
   private ProDefinitionDao dao;
 
   public ProDefinitionServiceImpl(ProDefinitionDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public ProDefinition getByDeployId(String deployId) {
     return this.dao.getByDeployId(deployId);
   }
 
   public ProDefinition getByName(String name) {
     return this.dao.getByName(name);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.ProDefinitionServiceImpl
 * JD-Core Version:    0.5.4
 */