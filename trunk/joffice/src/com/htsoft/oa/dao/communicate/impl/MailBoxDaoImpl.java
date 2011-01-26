 package com.htsoft.oa.dao.communicate.impl;
 
 import com.htsoft.core.Constants;
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.communicate.MailBoxDao;
 import com.htsoft.oa.model.communicate.MailBox;
 import java.util.ArrayList;
 import java.util.List;
 import org.apache.commons.lang.StringUtils;
 import org.hibernate.Query;
 import org.hibernate.Session;
 
 public class MailBoxDaoImpl extends BaseDaoImpl<MailBox>
   implements MailBoxDao
 {
   public MailBoxDaoImpl()
   {
     super(MailBox.class);
   }
 
   public Long CountByFolderId(Long folderId)
   {
     String hql = "select count(*) from MailBox where folderId =?";
 
     Query query = getSession().createQuery(hql);
     query.setLong(0, folderId.longValue());
     return (Long)query.uniqueResult();
   }
 
   public List<MailBox> findByFolderId(Long folderId) {
     String hql = "from MailBox where folderId = ?";
     return findByHql(hql, new Object[] { folderId });
   }
 
   public List<MailBox> findBySearch(String searchContent, PagingBean pb)
   {
     ArrayList params = new ArrayList();
 
     StringBuffer hql = new StringBuffer("from MailBox mb where mb.delFlag = ? and mb.appUser.userId =?");
     params.add(Constants.FLAG_UNDELETED);
     params.add(ContextUtil.getCurrentUserId());
 
     if (StringUtils.isNotEmpty(searchContent)) {
       hql.append(" and (mb.mail.subject like ? or mb.mail.content like ?)");
       params.add("%" + searchContent + "%");
       params.add("%" + searchContent + "%");
     }
 
     hql.append(" order by mb.sendTime desc");
     return findByHql(hql.toString(), params.toArray(), pb);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.communicate.impl.MailBoxDaoImpl
 * JD-Core Version:    0.5.4
 */