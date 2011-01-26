 package com.htsoft.oa.dao.archive.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.archive.ArchHastenDao;
 import com.htsoft.oa.model.archive.ArchHasten;
 import java.util.Date;
 import java.util.List;
 
 public class ArchHastenDaoImpl extends BaseDaoImpl<ArchHasten>
   implements ArchHastenDao
 {
   public ArchHastenDaoImpl()
   {
     super(ArchHasten.class);
   }
 
   public Date getLeastRecordByUser(Long archivesId)
   {
     String hql = "from ArchHasten vo where vo.archives.archivesId=? order by vo.createtime desc";
     List list = findByHql(hql, new Object[] { archivesId });
     if (list.size() > 0) {
       return ((ArchHasten)list.get(0)).getCreatetime();
     }
     return null;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.archive.impl.ArchHastenDaoImpl
 * JD-Core Version:    0.5.4
 */