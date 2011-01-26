 package com.htsoft.oa.service.hrm.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.hrm.JobDao;
 import com.htsoft.oa.model.hrm.Job;
 import com.htsoft.oa.service.hrm.JobService;
 import java.util.List;
 
 public class JobServiceImpl extends BaseServiceImpl<Job>
   implements JobService
 {
   private JobDao dao;
 
   public JobServiceImpl(JobDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<Job> findByDep(Long depId)
   {
     return this.dao.findByDep(depId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.impl.JobServiceImpl
 * JD-Core Version:    0.5.4
 */