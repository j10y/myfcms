 package com.htsoft.oa.dao.hrm.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.hrm.JobDao;
 import com.htsoft.oa.model.hrm.Job;
 import java.util.List;
 
 public class JobDaoImpl extends BaseDaoImpl<Job>
   implements JobDao
 {
   public JobDaoImpl()
   {
     super(Job.class);
   }
 
   public List<Job> findByDep(Long depId)
   {
     String hql = "from Job vo where vo.department.depId=?";
     Object[] objs = { depId };
     return findByHql(hql, objs);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.impl.JobDaoImpl
 * JD-Core Version:    0.5.4
 */