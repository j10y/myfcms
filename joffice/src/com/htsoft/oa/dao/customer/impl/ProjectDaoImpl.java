 package com.htsoft.oa.dao.customer.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.customer.ProjectDao;
 import com.htsoft.oa.model.customer.Project;
 import java.sql.SQLException;
 import org.hibernate.HibernateException;
 import org.hibernate.Query;
 import org.hibernate.Session;
 import org.springframework.orm.hibernate3.HibernateCallback;
 import org.springframework.orm.hibernate3.HibernateTemplate;
 
 public class ProjectDaoImpl extends BaseDaoImpl<Project>
   implements ProjectDao
 {
   public ProjectDaoImpl()
   {
     super(Project.class);
   }
 
   public boolean checkProjectNo(String projectNo)
   {
     String hql = "select count(*) from Project p where p.projectNo = ?";
     Long count = (Long)getHibernateTemplate().execute(new HibernateCallback(projectNo)
     {
       public Object doInHibernate(Session session) throws HibernateException, SQLException
       {
         Query query = session.createQuery("select count(*) from Project p where p.projectNo = ?");
         query.setString(0, this.val$projectNo);
         return query.uniqueResult();
       }
     });
     return count.longValue() == 0L;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.customer.impl.ProjectDaoImpl
 * JD-Core Version:    0.5.4
 */