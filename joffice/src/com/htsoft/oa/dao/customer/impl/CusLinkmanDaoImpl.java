 package com.htsoft.oa.dao.customer.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.customer.CusLinkmanDao;
 import com.htsoft.oa.model.customer.CusLinkman;
 import java.sql.SQLException;
 import org.hibernate.HibernateException;
 import org.hibernate.Query;
 import org.hibernate.Session;
 import org.springframework.orm.hibernate3.HibernateCallback;
 import org.springframework.orm.hibernate3.HibernateTemplate;
 
 public class CusLinkmanDaoImpl extends BaseDaoImpl<CusLinkman>
   implements CusLinkmanDao
 {
   public CusLinkmanDaoImpl()
   {
     super(CusLinkman.class);
   }
 
   public boolean checkMainCusLinkman(final Long customerId, final Long linkmanId)
   {
     final StringBuffer hql = new StringBuffer("select count(*) from CusLinkman  cl where cl.isPrimary = 1 and cl.customer.customerId =? ");
     if (linkmanId != null) {
       hql.append("and cl.linkmanId != ? ");
     }
     Long count = (Long)getHibernateTemplate().execute(new HibernateCallback()
     {
       public Object doInHibernate(Session session)
         throws HibernateException, SQLException
       {
         Query query = session.createQuery(hql.toString());
         query.setLong(0, customerId.longValue());
         if (linkmanId != null) {
           query.setLong(1, linkmanId.longValue());
         }
         return query.uniqueResult();
       }
     });
     return count.longValue() == 0L;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.customer.impl.CusLinkmanDaoImpl
 * JD-Core Version:    0.5.4
 */