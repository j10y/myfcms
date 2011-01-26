 package com.htsoft.oa.service.flow.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.flow.ProUserAssignDao;
 import com.htsoft.oa.model.flow.ProUserAssign;
 import com.htsoft.oa.service.flow.ProUserAssignService;
 import java.util.List;
 
 public class ProUserAssignServiceImpl extends BaseServiceImpl<ProUserAssign>
   implements ProUserAssignService
 {
   private ProUserAssignDao dao;
 
   public ProUserAssignServiceImpl(ProUserAssignDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<ProUserAssign> getByDeployId(String deployId) {
     return this.dao.getByDeployId(deployId);
   }
 
   public ProUserAssign getByDeployIdActivityName(String deployId, String activityName)
   {
     return this.dao.getByDeployIdActivityName(deployId, activityName);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.ProUserAssignServiceImpl
 * JD-Core Version:    0.5.4
 */