 package com.htsoft.oa.dao.system.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.system.FileAttachDao;
 import com.htsoft.oa.model.system.FileAttach;
 import java.sql.SQLException;
 import org.hibernate.HibernateException;
 import org.hibernate.Query;
 import org.hibernate.Session;
 import org.springframework.orm.hibernate3.HibernateCallback;
 import org.springframework.orm.hibernate3.HibernateTemplate;
 
 public class FileAttachDaoImpl extends BaseDaoImpl<FileAttach>
   implements FileAttachDao
 {
   public FileAttachDaoImpl()
   {
     super(FileAttach.class);
   }
 
   public void removeByPath(String filePath)
   {
     String hql = "delete from FileAttach fa where fa.filePath = ?";
     getHibernateTemplate().execute(new HibernateCallback(filePath)
     {
       public Object doInHibernate(Session session) throws HibernateException, SQLException
       {
         Query query = session.createQuery("delete from FileAttach fa where fa.filePath = ?");
         query.setString(0, this.val$filePath);
         return Integer.valueOf(query.executeUpdate());
       }
     });
   }
 
   public FileAttach getByPath(String filePath)
   {
     String hql = "from FileAttach fa where fa.filePath = ?";
     return (FileAttach)findUnique(hql, new Object[] { filePath });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.FileAttachDaoImpl
 * JD-Core Version:    0.5.4
 */