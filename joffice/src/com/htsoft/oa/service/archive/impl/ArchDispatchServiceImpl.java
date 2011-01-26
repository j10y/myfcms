 package com.htsoft.oa.service.archive.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.archive.ArchDispatchDao;
 import com.htsoft.oa.model.archive.ArchDispatch;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.archive.ArchDispatchService;
 import java.util.List;
 
 public class ArchDispatchServiceImpl extends BaseServiceImpl<ArchDispatch>
   implements ArchDispatchService
 {
   private ArchDispatchDao dao;
 
   public ArchDispatchServiceImpl(ArchDispatchDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<ArchDispatch> findByUser(AppUser user, PagingBean pb)
   {
     return this.dao.findByUser(user, pb);
   }
 
   public int countArchDispatch(Long archivesId)
   {
     return this.dao.findRecordByArc(archivesId).size();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.ArchDispatchServiceImpl
 * JD-Core Version:    0.5.4
 */