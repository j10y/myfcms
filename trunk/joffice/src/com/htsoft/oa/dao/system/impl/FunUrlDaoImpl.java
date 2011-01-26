 package com.htsoft.oa.dao.system.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.system.FunUrlDao;
 import com.htsoft.oa.model.system.FunUrl;
 
 public class FunUrlDaoImpl extends BaseDaoImpl<FunUrl>
   implements FunUrlDao
 {
   public FunUrlDaoImpl()
   {
     super(FunUrl.class);
   }
 
   public FunUrl getByPathFunId(String path, Long funId)
   {
     String hql = "from FunUrl fu where fu.urlPath=? and fu.appFunction.functionId=? ";
     return (FunUrl)findUnique(hql, new Object[] { path, funId });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.FunUrlDaoImpl
 * JD-Core Version:    0.5.4
 */