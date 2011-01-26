 package com.htsoft.oa.dao.archive.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.archive.ArchFlowConfDao;
 import com.htsoft.oa.model.archive.ArchFlowConf;
 import java.util.List;
 
 public class ArchFlowConfDaoImpl extends BaseDaoImpl<ArchFlowConf>
   implements ArchFlowConfDao
 {
   public ArchFlowConfDaoImpl()
   {
     super(ArchFlowConf.class);
   }
 
   public ArchFlowConf getByFlowType(Short archType)
   {
     String hql = "from ArchFlowConf vo where vo.archType=?";
     Object[] objs = { archType };
     List list = findByHql(hql, objs);
     if (list.size() == 1) {
       return (ArchFlowConf)list.get(0);
     }
     return null;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.archive.impl.ArchFlowConfDaoImpl
 * JD-Core Version:    0.5.4
 */