 package com.htsoft.oa.service.archive.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.archive.ArchivesTypeDao;
 import com.htsoft.oa.model.archive.ArchivesType;
 import com.htsoft.oa.service.archive.ArchivesTypeService;
 
 public class ArchivesTypeServiceImpl extends BaseServiceImpl<ArchivesType>
   implements ArchivesTypeService
 {
   private ArchivesTypeDao dao;
 
   public ArchivesTypeServiceImpl(ArchivesTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.ArchivesTypeServiceImpl
 * JD-Core Version:    0.5.4
 */