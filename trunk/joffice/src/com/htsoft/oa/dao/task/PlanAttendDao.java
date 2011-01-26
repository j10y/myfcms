package com.htsoft.oa.dao.task;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.task.PlanAttend;
import java.util.List;

public abstract interface PlanAttendDao extends BaseDao<PlanAttend>
{
  public abstract List<PlanAttend> FindPlanAttend(Long paramLong, Short paramShort1, Short paramShort2);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.task.PlanAttendDao
 * JD-Core Version:    0.5.4
 */