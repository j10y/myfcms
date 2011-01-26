 package com.htsoft.oa.dao.archive.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.archive.ArchDispatchDao;
 import com.htsoft.oa.model.archive.ArchDispatch;
 import com.htsoft.oa.model.system.AppRole;
 import com.htsoft.oa.model.system.AppUser;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 
 public class ArchDispatchDaoImpl extends BaseDaoImpl<ArchDispatch>
   implements ArchDispatchDao
 {
   public ArchDispatchDaoImpl()
   {
     super(ArchDispatch.class);
   }
 
   public List<ArchDispatch> findByUser(AppUser user, PagingBean pb)
   {
     Iterator it = user.getRoles().iterator();
     StringBuffer sb = new StringBuffer();
     while (it.hasNext()) {
       if (sb.length() > 0) {
         sb.append(",");
       }
       sb.append(((AppRole)it.next()).getRoleId().toString());
     }
     StringBuffer hql = new StringBuffer("from ArchDispatch vo where vo.archUserType=2 and vo.isRead=0 and (vo.userId=?");
     if (sb.length() > 0) {
       hql.append(" or vo.disRoleId in (" + sb + ")");
     }
     hql.append(") order by vo.dispatchId desc");
     Object[] objs = { user.getUserId() };
     return findByHql(hql.toString(), objs, pb);
   }
 
   public List<ArchDispatch> findRecordByArc(Long archivesId)
   {
     String hql = "from ArchDispatch vo where (vo.archUserType=0 or vo.archUserType=1) and vo.archives.archivesId=?";
     Object[] objs = { archivesId };
     return findByHql(hql, objs);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.archive.impl.ArchDispatchDaoImpl
 * JD-Core Version:    0.5.4
 */