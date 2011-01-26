 package com.htsoft.oa.dao.hrm.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.hrm.SalaryItemDao;
 import com.htsoft.oa.model.hrm.SalaryItem;
 import java.util.List;
 import org.apache.commons.lang.StringUtils;
 
 public class SalaryItemDaoImpl extends BaseDaoImpl<SalaryItem>
   implements SalaryItemDao
 {
   public SalaryItemDaoImpl()
   {
     super(SalaryItem.class);
   }
 
   public List<SalaryItem> getAllExcludeId(String excludeIds, PagingBean pb)
   {
     String hql = "from SalaryItem ";
     if (StringUtils.isNotEmpty(excludeIds)) {
       hql = hql + "where salaryItemId not in(" + excludeIds + ")";
     }
     return findByHql(hql, null, pb);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.impl.SalaryItemDaoImpl
 * JD-Core Version:    0.5.4
 */