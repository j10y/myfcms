 package com.htsoft.oa.service.archive.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.archive.ArchHastenDao;
 import com.htsoft.oa.model.archive.ArchHasten;
 import com.htsoft.oa.service.archive.ArchHastenService;
 import java.util.Date;
 
 public class ArchHastenServiceImpl extends BaseServiceImpl<ArchHasten>
   implements ArchHastenService
 {
   private ArchHastenDao dao;
 
   public ArchHastenServiceImpl(ArchHastenDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public Date getLeastRecordByUser(Long archivesId)
   {
     return this.dao.getLeastRecordByUser(archivesId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.ArchHastenServiceImpl
 * JD-Core Version:    0.5.4
 */