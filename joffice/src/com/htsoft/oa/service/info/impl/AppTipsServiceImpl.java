 package com.htsoft.oa.service.info.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.info.AppTipsDao;
 import com.htsoft.oa.model.info.AppTips;
 import com.htsoft.oa.service.info.AppTipsService;
 
 public class AppTipsServiceImpl extends BaseServiceImpl<AppTips>
   implements AppTipsService
 {
   private AppTipsDao dao;
 
   public AppTipsServiceImpl(AppTipsDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.impl.AppTipsServiceImpl
 * JD-Core Version:    0.5.4
 */