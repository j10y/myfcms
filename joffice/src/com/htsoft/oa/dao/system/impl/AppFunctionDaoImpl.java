 package com.htsoft.oa.dao.system.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.system.AppFunctionDao;
 import com.htsoft.oa.model.system.AppFunction;
 
 public class AppFunctionDaoImpl extends BaseDaoImpl<AppFunction>
   implements AppFunctionDao
 {
   public AppFunctionDaoImpl()
   {
     super(AppFunction.class);
   }
 
   public AppFunction getByKey(String key)
   {
     String hql = "from AppFunction af where af.funKey=?";
     return (AppFunction)findUnique(hql, new String[] { key });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.AppFunctionDaoImpl
 * JD-Core Version:    0.5.4
 */