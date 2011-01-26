 package com.htsoft.oa.service.hrm.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.hrm.ResumeDao;
 import com.htsoft.oa.model.hrm.Resume;
 import com.htsoft.oa.service.hrm.ResumeService;
 
 public class ResumeServiceImpl extends BaseServiceImpl<Resume>
   implements ResumeService
 {
   private ResumeDao dao;
 
   public ResumeServiceImpl(ResumeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.impl.ResumeServiceImpl
 * JD-Core Version:    0.5.4
 */