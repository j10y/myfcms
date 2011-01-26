 package com.htsoft.oa.service.info.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.info.InMessageDao;
 import com.htsoft.oa.model.info.InMessage;
 import com.htsoft.oa.model.info.ShortMessage;
 import com.htsoft.oa.service.info.InMessageService;
 import java.util.Date;
 import java.util.List;
 
 public class InMessageServiceImpl extends BaseServiceImpl<InMessage>
   implements InMessageService
 {
   private InMessageDao dao;
 
   public InMessageServiceImpl(InMessageDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public InMessage findByRead(Long userId) {
     return this.dao.findByRead(userId);
   }
 
   public Integer findByReadFlag(Long userId) {
     return this.dao.findByReadFlag(userId);
   }
 
   public List<InMessage> findAll(Long userId, PagingBean pb) {
     return this.dao.findAll(userId, pb);
   }
 
   public List findByUser(Long userId, PagingBean pb) {
     return this.dao.findByUser(userId, pb);
   }
 
   public List searchInMessage(Long userId, InMessage inMessage, ShortMessage shortMessage, Date from, Date to, PagingBean pb)
   {
     return this.dao.searchInMessage(userId, inMessage, shortMessage, from, to, pb);
   }
 
   public InMessage findLatest(Long userId) {
     return this.dao.findLatest(userId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.impl.InMessageServiceImpl
 * JD-Core Version:    0.5.4
 */