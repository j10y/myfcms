 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.IndexDisplayDao;
 import com.htsoft.oa.model.system.IndexDisplay;
 import com.htsoft.oa.service.system.IndexDisplayService;
 import java.util.List;
 
 public class IndexDisplayServiceImpl extends BaseServiceImpl<IndexDisplay>
   implements IndexDisplayService
 {
   private IndexDisplayDao dao;
 
   public IndexDisplayServiceImpl(IndexDisplayDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<IndexDisplay> findByUser(Long userId)
   {
     return this.dao.findByUser(userId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.IndexDisplayServiceImpl
 * JD-Core Version:    0.5.4
 */