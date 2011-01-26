 package com.htsoft.oa.service.document.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.document.DocPrivilegeDao;
 import com.htsoft.oa.model.document.DocPrivilege;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.document.DocPrivilegeService;
 import java.util.List;
 
 public class DocPrivilegeServiceImpl extends BaseServiceImpl<DocPrivilege>
   implements DocPrivilegeService
 {
   private DocPrivilegeDao dao;
 
   public DocPrivilegeServiceImpl(DocPrivilegeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<DocPrivilege> getAll(DocPrivilege docPrivilege, Long folderId, PagingBean pb)
   {
     return this.dao.getAll(docPrivilege, folderId, pb);
   }
 
   public List<Integer> getRightsByFolder(AppUser user, Long folderId)
   {
     return this.dao.getRightsByFolder(user, folderId);
   }
 
   public Integer getRightsByDocument(AppUser user, Long docId)
   {
     return this.dao.getRightsByDocument(user, docId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.document.impl.DocPrivilegeServiceImpl
 * JD-Core Version:    0.5.4
 */