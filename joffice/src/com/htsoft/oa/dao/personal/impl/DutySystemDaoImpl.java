 package com.htsoft.oa.dao.personal.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.personal.DutySystemDao;
 import com.htsoft.oa.model.personal.DutySystem;
 import java.sql.SQLException;
 import java.util.List;
 import org.hibernate.HibernateException;
 import org.hibernate.Query;
 import org.hibernate.Session;
 import org.springframework.orm.hibernate3.HibernateCallback;
 import org.springframework.orm.hibernate3.HibernateTemplate;
 
 public class DutySystemDaoImpl extends BaseDaoImpl<DutySystem>
   implements DutySystemDao
 {
   public DutySystemDaoImpl()
   {
     super(DutySystem.class);
   }
 
   public void updateForNotDefult()
   {
     String hql = "update DutySystem ds set ds.isDefault=? where ds.isDefault=?";
     getHibernateTemplate().execute(new HibernateCallback()
     {
       public Object doInHibernate(Session session) throws HibernateException, SQLException {
         Query query = session.createQuery("update DutySystem ds set ds.isDefault=? where ds.isDefault=?");
         query.setShort(0, DutySystem.NOT_DEFAULT.shortValue());
         query.setShort(1, DutySystem.DEFAULT.shortValue());
         query.executeUpdate();
         return null;
       }
     });
   }
 
   public List<DutySystem> getDefaultDutySystem()
   {
     String hql = "from DutySystem ds where ds.isDefault=? ";
     return findByHql(hql, new Object[] { DutySystem.DEFAULT });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.personal.impl.DutySystemDaoImpl
 * JD-Core Version:    0.5.4
 */