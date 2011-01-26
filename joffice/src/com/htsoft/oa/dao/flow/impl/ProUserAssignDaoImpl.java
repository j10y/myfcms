 package com.htsoft.oa.dao.flow.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.flow.ProUserAssignDao;
 import com.htsoft.oa.model.flow.ProUserAssign;
 import java.util.List;
 
 public class ProUserAssignDaoImpl extends BaseDaoImpl<ProUserAssign>
   implements ProUserAssignDao
 {
   public ProUserAssignDaoImpl()
   {
     super(ProUserAssign.class);
   }
 
   public List<ProUserAssign> getByDeployId(String deployId) {
     String hql = "from ProUserAssign pua where pua.deployId=?";
     return findByHql(hql, new Object[] { deployId });
   }
 
   public ProUserAssign getByDeployIdActivityName(String deployId, String activityName)
   {
     String hql = "from ProUserAssign pua where pua.deployId=? and pua.activityName=?";
     return (ProUserAssign)findUnique(hql, new Object[] { deployId, activityName });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.ProUserAssignDaoImpl
 * JD-Core Version:    0.5.4
 */