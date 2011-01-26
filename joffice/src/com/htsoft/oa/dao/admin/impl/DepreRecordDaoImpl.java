 package com.htsoft.oa.dao.admin.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.admin.DepreRecordDao;
 import com.htsoft.oa.model.admin.DepreRecord;
 import java.util.Date;
 import java.util.List;
 import org.hibernate.Query;
 import org.hibernate.Session;
 
 public class DepreRecordDaoImpl extends BaseDaoImpl<DepreRecord>
   implements DepreRecordDao
 {
   public DepreRecordDaoImpl()
   {
     super(DepreRecord.class);
   }
 
   public Date findMaxDate(Long assetsId)
   {
     String hql = "select max(vo.calTime) from DepreRecord vo where vo.fixedAssets.assetsId=?";
     Query query = getSession().createQuery(hql);
     query.setLong(0, assetsId.longValue());
     Date date = (Date)query.list().get(0);
     return date;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.DepreRecordDaoImpl
 * JD-Core Version:    0.5.4
 */