 package com.htsoft.oa.dao.system.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.system.SysConfigDao;
 import com.htsoft.oa.model.system.SysConfig;
 import java.util.List;
 import org.hibernate.Query;
 import org.hibernate.Session;
 
 public class SysConfigDaoImpl extends BaseDaoImpl<SysConfig>
   implements SysConfigDao
 {
   public SysConfigDaoImpl()
   {
     super(SysConfig.class);
   }
 
   public SysConfig findByKey(String key)
   {
     String hql = "from SysConfig vo where vo.configKey=?";
     Object[] objs = { key };
     List list = findByHql(hql, objs);
     return (SysConfig)list.get(0);
   }
 
   public List<SysConfig> findConfigByTypeName(String typeName)
   {
     String hql = "from SysConfig vo where vo.typeName=?";
     Object[] objs = { typeName };
     return findByHql(hql, objs);
   }
 
   public List findTypeNames()
   {
     String sql = "select vo.typeName from SysConfig vo group by vo.typeName";
     Query query = getSession().createQuery(sql);
     return query.list();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.SysConfigDaoImpl
 * JD-Core Version:    0.5.4
 */