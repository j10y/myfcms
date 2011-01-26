 package com.htsoft.oa.dao.system.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.system.AppRoleDao;
 import com.htsoft.oa.model.system.AppFunction;
 import com.htsoft.oa.model.system.AppRole;
 import com.htsoft.oa.model.system.FunUrl;
 import java.sql.SQLException;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 import java.util.TreeSet;
 import org.hibernate.HibernateException;
 import org.hibernate.Query;
 import org.hibernate.Session;
 import org.springframework.orm.hibernate3.HibernateCallback;
 import org.springframework.orm.hibernate3.HibernateTemplate;
 
 public class AppRoleDaoImpl extends BaseDaoImpl<AppRole>
   implements AppRoleDao
 {
   public AppRoleDaoImpl()
   {
     super(AppRole.class);
   }
 
   public AppRole getByRoleName(String roleName) {
     String hql = "from AppRole ar where ar.roleName=?";
     return (AppRole)findUnique(hql, new Object[] { roleName });
   }
 
   public HashMap<String, Set<String>> getSecurityDataSource()
   {
     HashMap source = new HashMap();
 
     getHibernateTemplate().execute(new HibernateCallback(source)
     {
       public Object doInHibernate(Session session) throws HibernateException, SQLException
       {
         String hql = "from AppRole";
         Query query = session.createQuery(hql);
         List roleList = query.list();
 
         for (AppRole role : roleList) {
           TreeSet urlSet = new TreeSet();
 
           Iterator functions = role.getFunctions().iterator();
 
           while (functions.hasNext()) {
             AppFunction fun = (AppFunction)functions.next();
             Iterator urlIt = fun.getFunUrls().iterator();
             while (urlIt.hasNext()) {
               urlSet.add(((FunUrl)urlIt.next()).getUrlPath());
             }
           }
 
           this.val$source.put(role.getName(), urlSet);
         }
         return null;
       }
     });
     return source;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.AppRoleDaoImpl
 * JD-Core Version:    0.5.4
 */