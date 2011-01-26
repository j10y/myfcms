 package com.htsoft.oa.dao.flow.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.flow.FormDefDao;
 import com.htsoft.oa.model.flow.FormDef;
 import java.util.List;
 
 public class FormDefDaoImpl extends BaseDaoImpl<FormDef>
   implements FormDefDao
 {
   public FormDefDaoImpl()
   {
     super(FormDef.class);
   }
 
   public List<FormDef> getByDeployId(String deployId)
   {
     String hql = "from FormDef fd where deployId=?";
     return findByHql(hql, new Object[] { deployId });
   }
 
   public FormDef getByDeployIdActivityName(String deployId, String activityName)
   {
     String hql = "from FormDef fd where fd.deployId=? and fd.activityName=?";
     return (FormDef)findUnique(hql, new Object[] { deployId, activityName });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.FormDefDaoImpl
 * JD-Core Version:    0.5.4
 */