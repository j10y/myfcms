 package com.htsoft.oa.service.archive.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.archive.DocHistoryDao;
 import com.htsoft.oa.model.archive.DocHistory;
 import com.htsoft.oa.service.archive.DocHistoryService;
 
 public class DocHistoryServiceImpl extends BaseServiceImpl<DocHistory>
   implements DocHistoryService
 {
   private DocHistoryDao dao;
 
   public DocHistoryServiceImpl(DocHistoryDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.archive.impl.DocHistoryServiceImpl
 * JD-Core Version:    0.5.4
 */