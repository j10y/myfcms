 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.AppFunctionDao;
 import com.htsoft.oa.model.system.AppFunction;
 import com.htsoft.oa.service.system.AppFunctionService;
 
 public class AppFunctionServiceImpl extends BaseServiceImpl<AppFunction>
   implements AppFunctionService
 {
   private AppFunctionDao dao;
 
   public AppFunctionServiceImpl(AppFunctionDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public AppFunction getByKey(String key)
   {
     return this.dao.getByKey(key);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.AppFunctionServiceImpl
 * JD-Core Version:    0.5.4
 */