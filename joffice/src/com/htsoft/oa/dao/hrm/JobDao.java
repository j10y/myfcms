package com.htsoft.oa.dao.hrm;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.hrm.Job;
import java.util.List;

public abstract interface JobDao extends BaseDao<Job>
{
  public abstract List<Job> findByDep(Long paramLong);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.hrm.JobDao
 * JD-Core Version:    0.5.4
 */