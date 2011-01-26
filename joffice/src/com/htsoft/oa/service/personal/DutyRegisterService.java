package com.htsoft.oa.service.personal;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.personal.DutyRegister;
import com.htsoft.oa.model.system.AppUser;
import java.util.Date;

public abstract interface DutyRegisterService extends BaseService<DutyRegister>
{
  public abstract void signInOff(Long paramLong, Short paramShort, AppUser paramAppUser, Date paramDate);

  public abstract DutyRegister getTodayUserRegister(Long paramLong1, Short paramShort, Long paramLong2);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.DutyRegisterService
 * JD-Core Version:    0.5.4
 */