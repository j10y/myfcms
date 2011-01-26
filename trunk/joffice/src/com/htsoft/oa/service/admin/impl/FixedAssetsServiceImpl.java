 package com.htsoft.oa.service.admin.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.admin.FixedAssetsDao;
 import com.htsoft.oa.model.admin.FixedAssets;
 import com.htsoft.oa.service.admin.FixedAssetsService;
 
 public class FixedAssetsServiceImpl extends BaseServiceImpl<FixedAssets>
   implements FixedAssetsService
 {
   private FixedAssetsDao dao;
 
   public FixedAssetsServiceImpl(FixedAssetsDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.FixedAssetsServiceImpl
 * JD-Core Version:    0.5.4
 */