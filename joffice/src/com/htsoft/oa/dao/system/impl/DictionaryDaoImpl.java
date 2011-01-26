 package com.htsoft.oa.dao.system.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.system.DictionaryDao;
 import com.htsoft.oa.model.system.Dictionary;
 import java.sql.SQLException;
 import java.util.List;
 import org.hibernate.HibernateException;
 import org.hibernate.Query;
 import org.hibernate.Session;
 import org.springframework.orm.hibernate3.HibernateCallback;
 import org.springframework.orm.hibernate3.HibernateTemplate;
 
 public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary>
   implements DictionaryDao
 {
   public DictionaryDaoImpl()
   {
     super(Dictionary.class);
   }
 
   public List<String> getAllItems()
   {
     String hql = "select itemName from Dictionary group by itemName";
     return (List)getHibernateTemplate().execute(new HibernateCallback()
     {
       public Object doInHibernate(Session session) throws HibernateException, SQLException
       {
         Query query = session.createQuery("select itemName from Dictionary group by itemName");
         return query.list();
       }
     });
   }
 
   public List<String> getAllByItemName(String itemName)
   {
     String hql = "select itemValue from Dictionary where itemName=?";
     return (List)getHibernateTemplate().execute(new HibernateCallback(itemName)
     {
       public Object doInHibernate(Session session) throws HibernateException, SQLException
       {
         Query query = session.createQuery("select itemValue from Dictionary where itemName=?");
         query.setString(0, this.val$itemName);
         return query.list();
       }
     });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.DictionaryDaoImpl
 * JD-Core Version:    0.5.4
 */