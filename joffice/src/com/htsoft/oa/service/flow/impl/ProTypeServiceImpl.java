 package com.htsoft.oa.service.flow.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.flow.ProTypeDao;
 import com.htsoft.oa.model.flow.ProType;
 import com.htsoft.oa.service.flow.ProTypeService;
 
 public class ProTypeServiceImpl extends BaseServiceImpl<ProType>
   implements ProTypeService
 {
   private ProTypeDao dao;
 
   public ProTypeServiceImpl(ProTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.ProTypeServiceImpl
 * JD-Core Version:    0.5.4
 */