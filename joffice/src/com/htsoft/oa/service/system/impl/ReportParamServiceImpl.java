 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.ReportParamDao;
 import com.htsoft.oa.model.system.ReportParam;
 import com.htsoft.oa.service.system.ReportParamService;
 import java.util.List;
 
 public class ReportParamServiceImpl extends BaseServiceImpl<ReportParam>
   implements ReportParamService
 {
   private ReportParamDao dao;
 
   public ReportParamServiceImpl(ReportParamDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<ReportParam> findByRepTemp(Long reportId)
   {
     return this.dao.findByRepTemp(reportId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.ReportParamServiceImpl
 * JD-Core Version:    0.5.4
 */