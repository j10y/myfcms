 package com.htsoft.oa.dao.document.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.document.DocFolderDao;
 import com.htsoft.oa.model.document.DocFolder;
 import java.util.List;
 
 public class DocFolderDaoImpl extends BaseDaoImpl<DocFolder>
   implements DocFolderDao
 {
   public DocFolderDaoImpl()
   {
     super(DocFolder.class);
   }
 
   public List<DocFolder> getUserFolderByParentId(Long userId, Long parentId)
   {
     String hql = "from DocFolder df where df.isShared=0 and df.appUser.userId=? and parentId=?";
     return findByHql(hql, new Object[] { userId, parentId });
   }
 
   public List<DocFolder> getFolderLikePath(String path)
   {
     String hql = "from DocFolder df where df.path like ?";
     return findByHql(hql, new Object[] { path + '%' });
   }
 
   public List<DocFolder> getPublicFolderByParentId(Long parentId)
   {
     String hql = "from DocFolder df where df.isShared=1 and parentId=? ";
     return findByHql(hql, new Object[] { parentId });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.document.impl.DocFolderDaoImpl
 * JD-Core Version:    0.5.4
 */