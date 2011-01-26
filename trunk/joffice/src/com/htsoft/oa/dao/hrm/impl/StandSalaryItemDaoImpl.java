 package com.htsoft.oa.dao.hrm.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.hrm.StandSalaryItemDao;
 import com.htsoft.oa.model.hrm.StandSalaryItem;
 import java.util.List;
 
 public class StandSalaryItemDaoImpl extends BaseDaoImpl<StandSalaryItem>
   implements StandSalaryItemDao
 {
   public StandSalaryItemDaoImpl()
   {
     super(StandSalaryItem.class);
   }
 
   public List<StandSalaryItem> getAllByStandardId(Long standardId)
   {
     String hql = "from StandSalaryItem ssi where ssi.standSalary.standardId=?";
     return findByHql(hql, new Object[] { standardId });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.impl.StandSalaryItemDaoImpl
 * JD-Core Version:    0.5.4
 */