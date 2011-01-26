package com.htsoft.oa.dao.task;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.task.Appointment;
import java.util.List;

public abstract interface AppointmentDao extends BaseDao<Appointment>
{
  public abstract List showAppointmentByUserId(Long paramLong, PagingBean paramPagingBean);
}

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.task.AppointmentDao
 * JD-Core Version:    0.5.4
 */