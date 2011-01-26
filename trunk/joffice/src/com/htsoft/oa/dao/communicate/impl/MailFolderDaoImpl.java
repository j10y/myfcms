 package com.htsoft.oa.dao.communicate.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.communicate.MailFolderDao;
 import com.htsoft.oa.model.communicate.MailFolder;
 import java.util.List;
 
 public class MailFolderDaoImpl extends BaseDaoImpl<MailFolder>
   implements MailFolderDao
 {
   public MailFolderDaoImpl()
   {
     super(MailFolder.class);
   }
 
   public List<MailFolder> getUserFolderByParentId(Long userId, Long parentId)
   {
     String hql = "from MailFolder mf where mf.appUser.userId=? and parentId=?";
     return findByHql(hql, new Object[] { userId, parentId });
   }
 
   public List<MailFolder> getAllUserFolderByParentId(Long userId, Long parentId)
   {
     String hql = "from MailFolder mf where mf.appUser.userId=? and parentId=? or userId is null";
     return findByHql(hql, new Object[] { userId, parentId });
   }
 
   public List<MailFolder> getFolderLikePath(String path)
   {
     String hql = "from MailFolder mf where mf.path like ?";
     return findByHql(hql, new Object[] { path + '%' });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.impl.MailFolderDaoImpl
 * JD-Core Version:    0.5.4
 */