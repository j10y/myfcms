 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.ReportTemplateDao;
 import com.htsoft.oa.model.system.ReportTemplate;
 import com.htsoft.oa.service.system.ReportTemplateService;
 
 public class ReportTemplateServiceImpl extends BaseServiceImpl<ReportTemplate>
   implements ReportTemplateService
 {
   private ReportTemplateDao dao;
 
   public ReportTemplateServiceImpl(ReportTemplateDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.ReportTemplateServiceImpl
 * JD-Core Version:    0.5.4
 */