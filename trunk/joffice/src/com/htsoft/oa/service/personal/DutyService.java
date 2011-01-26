package com.htsoft.oa.service.personal;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.personal.Duty;
import java.util.Date;

public abstract interface DutyService extends BaseService<Duty>
{
  public abstract boolean isExistDutyForUser(Long paramLong, Date paramDate1, Date paramDate2);

  public abstract Duty getCurUserDuty(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.DutyService
 * JD-Core Version:    0.5.4
 */