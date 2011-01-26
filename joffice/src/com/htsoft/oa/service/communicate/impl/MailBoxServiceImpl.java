 package com.htsoft.oa.service.communicate.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.communicate.MailBoxDao;
 import com.htsoft.oa.model.communicate.MailBox;
 import com.htsoft.oa.service.communicate.MailBoxService;
 import java.util.List;
 
 public class MailBoxServiceImpl extends BaseServiceImpl<MailBox>
   implements MailBoxService
 {
   private MailBoxDao dao;
 
   public MailBoxServiceImpl(MailBoxDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public Long CountByFolderId(Long folderId)
   {
     return this.dao.CountByFolderId(folderId);
   }
 
   public List<MailBox> findByFolderId(Long folderId) {
     return this.dao.findByFolderId(folderId);
   }
 
   public List<MailBox> findBySearch(String searchContent, PagingBean pb)
   {
     return this.dao.findBySearch(searchContent, pb);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.impl.MailBoxServiceImpl
 * JD-Core Version:    0.5.4
 */