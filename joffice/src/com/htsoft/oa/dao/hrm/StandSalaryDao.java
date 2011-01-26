package com.htsoft.oa.dao.hrm;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.hrm.StandSalary;
import java.util.List;

public abstract interface StandSalaryDao extends BaseDao<StandSalary>
{
  public abstract boolean checkStandNo(String paramString);

  public abstract List<StandSalary> findByPassCheck();
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.StandSalaryDao
 * JD-Core Version:    0.5.4
 */