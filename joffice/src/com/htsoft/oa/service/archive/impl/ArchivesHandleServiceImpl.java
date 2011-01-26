 package com.htsoft.oa.service.archive.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.archive.ArchivesHandleDao;
 import com.htsoft.oa.model.archive.ArchivesHandle;
 import com.htsoft.oa.service.archive.ArchivesHandleService;
 import java.util.List;
 
 public class ArchivesHandleServiceImpl extends BaseServiceImpl<ArchivesHandle>
   implements ArchivesHandleService
 {
   private ArchivesHandleDao dao;
 
   public ArchivesHandleServiceImpl(ArchivesHandleDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public ArchivesHandle findByUAIds(Long userId, Long archiveId)
   {
     return this.dao.findByUAIds(userId, archiveId);
   }
 
   public List<ArchivesHandle> findByAid(Long archiveId)
   {
     return this.dao.findByAid(archiveId);
   }
 
   public int countHandler(Long archiveId)
   {
     return this.dao.findByAid(archiveId).size();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.ArchivesHandleServiceImpl
 * JD-Core Version:    0.5.4
 */