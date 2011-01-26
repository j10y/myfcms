 package com.htsoft.oa.dao.flow.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.flow.ProDefinitionDao;
 import com.htsoft.oa.model.flow.ProDefinition;
 
 public class ProDefinitionDaoImpl extends BaseDaoImpl<ProDefinition>
   implements ProDefinitionDao
 {
   public ProDefinitionDaoImpl()
   {
     super(ProDefinition.class);
   }
 
   public ProDefinition getByDeployId(String deployId) {
     String hql = "from ProDefinition pd where pd.deployId=?";
     return (ProDefinition)findUnique(hql, new Object[] { deployId });
   }
 
   public ProDefinition getByName(String name) {
     String hql = "from ProDefinition pd where pd.name=?";
     return (ProDefinition)findUnique(hql, new Object[] { name });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.ProDefinitionDaoImpl
 * JD-Core Version:    0.5.4
 */