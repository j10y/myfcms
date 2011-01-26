 package com.htsoft.oa.dao.admin.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.admin.OfficeGoodsDao;
 import com.htsoft.oa.model.admin.OfficeGoods;
 import java.util.List;
 
 public class OfficeGoodsDaoImpl extends BaseDaoImpl<OfficeGoods>
   implements OfficeGoodsDao
 {
   public OfficeGoodsDaoImpl()
   {
     super(OfficeGoods.class);
   }
 
   public List<OfficeGoods> findByWarm()
   {
     String hql = "from OfficeGoods vo where ((vo.stockCounts<=vo.warnCounts and vo.isWarning=1) or (vo.stockCounts<=0))";
     return findByHql(hql);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.admin.impl.OfficeGoodsDaoImpl
 * JD-Core Version:    0.5.4
 */