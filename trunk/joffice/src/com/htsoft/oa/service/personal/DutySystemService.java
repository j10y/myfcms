package com.htsoft.oa.service.personal;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.personal.DutySystem;

public abstract interface DutySystemService extends BaseService<DutySystem>
{
  public abstract DutySystem getDefaultDutySystem();
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.DutySystemService
 * JD-Core Version:    0.5.4
 */