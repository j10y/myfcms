 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.SystemLogDao;
 import com.htsoft.oa.model.system.SystemLog;
 import com.htsoft.oa.service.system.SystemLogService;
 
 public class SystemLogServiceImpl extends BaseServiceImpl<SystemLog>
   implements SystemLogService
 {
   private SystemLogDao dao;
 
   public SystemLogServiceImpl(SystemLogDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.SystemLogServiceImpl
 * JD-Core Version:    0.5.4
 */