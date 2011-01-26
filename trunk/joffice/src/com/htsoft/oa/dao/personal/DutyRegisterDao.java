package com.htsoft.oa.dao.personal;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.personal.DutyRegister;

public abstract interface DutyRegisterDao extends BaseDao<DutyRegister>
{
  public abstract DutyRegister getTodayUserRegister(Long paramLong1, Short paramShort, Long paramLong2);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.personal.DutyRegisterDao
 * JD-Core Version:    0.5.4
 */