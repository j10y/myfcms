 package com.htsoft.oa.dao.communicate.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.communicate.MailDao;
 import com.htsoft.oa.model.communicate.Mail;
 
 public class MailDaoImpl extends BaseDaoImpl<Mail>
   implements MailDao
 {
   public MailDaoImpl()
   {
     super(Mail.class);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.impl.MailDaoImpl
 * JD-Core Version:    0.5.4
 */