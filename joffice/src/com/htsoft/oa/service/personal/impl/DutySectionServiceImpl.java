 package com.htsoft.oa.service.personal.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.personal.DutySectionDao;
 import com.htsoft.oa.model.personal.DutySection;
 import com.htsoft.oa.service.personal.DutySectionService;
 
 public class DutySectionServiceImpl extends BaseServiceImpl<DutySection>
   implements DutySectionService
 {
   private DutySectionDao dao;
 
   public DutySectionServiceImpl(DutySectionDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.impl.DutySectionServiceImpl
 * JD-Core Version:    0.5.4
 */