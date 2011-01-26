 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.FunUrlDao;
 import com.htsoft.oa.model.system.FunUrl;
 import com.htsoft.oa.service.system.FunUrlService;
 
 public class FunUrlServiceImpl extends BaseServiceImpl<FunUrl>
   implements FunUrlService
 {
   private FunUrlDao dao;
 
   public FunUrlServiceImpl(FunUrlDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public FunUrl getByPathFunId(String path, Long funId)
   {
     return this.dao.getByPathFunId(path, funId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.FunUrlServiceImpl
 * JD-Core Version:    0.5.4
 */