 package com.htsoft.oa.service.admin.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.admin.AssetsTypeDao;
 import com.htsoft.oa.model.admin.AssetsType;
 import com.htsoft.oa.service.admin.AssetsTypeService;
 
 public class AssetsTypeServiceImpl extends BaseServiceImpl<AssetsType>
   implements AssetsTypeService
 {
   private AssetsTypeDao dao;
 
   public AssetsTypeServiceImpl(AssetsTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.AssetsTypeServiceImpl
 * JD-Core Version:    0.5.4
 */