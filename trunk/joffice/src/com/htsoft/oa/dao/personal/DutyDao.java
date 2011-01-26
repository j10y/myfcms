package com.htsoft.oa.dao.personal;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.personal.Duty;
import java.util.Date;
import java.util.List;

public abstract interface DutyDao extends BaseDao<Duty>
{
  public abstract List<Duty> getUserDutyByTime(Long paramLong, Date paramDate1, Date paramDate2);

  public abstract List<Duty> getCurUserDuty(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.personal.DutyDao
 * JD-Core Version:    0.5.4
 */