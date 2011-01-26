 package com.htsoft.oa.service.hrm.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.hrm.JobChangeDao;
 import com.htsoft.oa.model.hrm.JobChange;
 import com.htsoft.oa.service.hrm.JobChangeService;
 
 public class JobChangeServiceImpl extends BaseServiceImpl<JobChange>
   implements JobChangeService
 {
   private JobChangeDao dao;
 
   public JobChangeServiceImpl(JobChangeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.impl.JobChangeServiceImpl
 * JD-Core Version:    0.5.4
 */