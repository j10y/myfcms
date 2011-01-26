 package com.htsoft.oa.service.communicate.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.communicate.MailDao;
 import com.htsoft.oa.model.communicate.Mail;
 import com.htsoft.oa.service.communicate.MailService;
 
 public class MailServiceImpl extends BaseServiceImpl<Mail>
   implements MailService
 {
   private MailDao dao;
 
   public MailServiceImpl(MailDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.impl.MailServiceImpl
 * JD-Core Version:    0.5.4
 */