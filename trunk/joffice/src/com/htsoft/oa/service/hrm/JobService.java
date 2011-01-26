package com.htsoft.oa.service.hrm;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.hrm.Job;
import java.util.List;

public abstract interface JobService extends BaseService<Job>
{
  public abstract List<Job> findByDep(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.JobService
 * JD-Core Version:    0.5.4
 */