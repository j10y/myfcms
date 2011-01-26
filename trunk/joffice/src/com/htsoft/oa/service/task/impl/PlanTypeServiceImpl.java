 package com.htsoft.oa.service.task.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.task.PlanTypeDao;
 import com.htsoft.oa.model.task.PlanType;
 import com.htsoft.oa.service.task.PlanTypeService;
 
 public class PlanTypeServiceImpl extends BaseServiceImpl<PlanType>
   implements PlanTypeService
 {
   private PlanTypeDao dao;
 
   public PlanTypeServiceImpl(PlanTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.task.impl.PlanTypeServiceImpl
 * JD-Core Version:    0.5.4
 */