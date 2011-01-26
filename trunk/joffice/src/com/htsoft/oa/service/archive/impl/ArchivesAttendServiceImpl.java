 package com.htsoft.oa.service.archive.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.archive.ArchivesAttendDao;
 import com.htsoft.oa.model.archive.ArchivesAttend;
 import com.htsoft.oa.service.archive.ArchivesAttendService;
 
 public class ArchivesAttendServiceImpl extends BaseServiceImpl<ArchivesAttend>
   implements ArchivesAttendService
 {
   private ArchivesAttendDao dao;
 
   public ArchivesAttendServiceImpl(ArchivesAttendDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.ArchivesAttendServiceImpl
 * JD-Core Version:    0.5.4
 */