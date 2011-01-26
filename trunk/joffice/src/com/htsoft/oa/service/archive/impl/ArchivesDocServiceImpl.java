 package com.htsoft.oa.service.archive.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.archive.ArchivesDocDao;
 import com.htsoft.oa.model.archive.ArchivesDoc;
 import com.htsoft.oa.service.archive.ArchivesDocService;
 import java.util.List;
 
 public class ArchivesDocServiceImpl extends BaseServiceImpl<ArchivesDoc>
   implements ArchivesDocService
 {
   private ArchivesDocDao dao;
 
   public ArchivesDocServiceImpl(ArchivesDocDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<ArchivesDoc> findByAid(Long archivesId)
   {
     return this.dao.findByAid(archivesId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.ArchivesDocServiceImpl
 * JD-Core Version:    0.5.4
 */