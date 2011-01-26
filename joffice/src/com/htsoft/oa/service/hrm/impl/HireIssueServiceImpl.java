 package com.htsoft.oa.service.hrm.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.hrm.HireIssueDao;
 import com.htsoft.oa.model.hrm.HireIssue;
 import com.htsoft.oa.service.hrm.HireIssueService;
 
 public class HireIssueServiceImpl extends BaseServiceImpl<HireIssue>
   implements HireIssueService
 {
   private HireIssueDao dao;
 
   public HireIssueServiceImpl(HireIssueDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.impl.HireIssueServiceImpl
 * JD-Core Version:    0.5.4
 */