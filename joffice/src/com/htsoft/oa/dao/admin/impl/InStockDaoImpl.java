 package com.htsoft.oa.dao.admin.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.admin.InStockDao;
 import com.htsoft.oa.model.admin.InStock;
 import java.util.Iterator;
 import java.util.List;
 import org.hibernate.Query;
 import org.hibernate.Session;
 
 public class InStockDaoImpl extends BaseDaoImpl<InStock>
   implements InStockDao
 {
   public InStockDaoImpl()
   {
     super(InStock.class);
   }
 
   public Integer findInCountByBuyId(Long buyId)
   {
     String hql = "select vo.inCounts from InStock vo where vo.buyId=?";
     Query query = getSession().createQuery(hql);
     query.setLong(0, buyId.longValue());
     Integer inCount = Integer.valueOf(Integer.parseInt(query.list().iterator().next().toString()));
     return inCount;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.InStockDaoImpl
 * JD-Core Version:    0.5.4
 */