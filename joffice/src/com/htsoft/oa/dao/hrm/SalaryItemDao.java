package com.htsoft.oa.dao.hrm;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.hrm.SalaryItem;
import java.util.List;

public abstract interface SalaryItemDao extends BaseDao<SalaryItem>
{
  public abstract List<SalaryItem> getAllExcludeId(String paramString, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.SalaryItemDao
 * JD-Core Version:    0.5.4
 */