 package com.htsoft.oa.dao.archive.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.archive.ArchivesDocDao;
 import com.htsoft.oa.model.archive.ArchivesDoc;
 import java.util.List;
 
 public class ArchivesDocDaoImpl extends BaseDaoImpl<ArchivesDoc>
   implements ArchivesDocDao
 {
   public ArchivesDocDaoImpl()
   {
     super(ArchivesDoc.class);
   }
 
   public List<ArchivesDoc> findByAid(Long archivesId)
   {
     String hql = "from ArchivesDoc vo where vo.archives.archivesId=?";
     Object[] objs = { archivesId };
     return findByHql(hql, objs);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.archive.impl.ArchivesDocDaoImpl
 * JD-Core Version:    0.5.4
 */