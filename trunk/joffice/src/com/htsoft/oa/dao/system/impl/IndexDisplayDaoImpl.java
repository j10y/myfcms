 package com.htsoft.oa.dao.system.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.system.IndexDisplayDao;
 import com.htsoft.oa.model.system.IndexDisplay;
 import java.util.List;
 
 public class IndexDisplayDaoImpl extends BaseDaoImpl<IndexDisplay>
   implements IndexDisplayDao
 {
   public IndexDisplayDaoImpl()
   {
     super(IndexDisplay.class);
   }
 
   public List<IndexDisplay> findByUser(Long userId)
   {
     String hql = "from IndexDisplay vo where vo.appUser.userId=?";
     return findByHql(hql, new Object[] { userId });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.IndexDisplayDaoImpl
 * JD-Core Version:    0.5.4
 */