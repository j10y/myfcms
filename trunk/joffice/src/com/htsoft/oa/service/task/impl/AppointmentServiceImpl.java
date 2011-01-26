 package com.htsoft.oa.service.task.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.task.AppointmentDao;
 import com.htsoft.oa.model.task.Appointment;
 import com.htsoft.oa.service.task.AppointmentService;
 import java.util.List;
 
 public class AppointmentServiceImpl extends BaseServiceImpl<Appointment>
   implements AppointmentService
 {
   private AppointmentDao dao;
 
   public AppointmentServiceImpl(AppointmentDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List showAppointmentByUserId(Long userId, PagingBean pb)
   {
     return this.dao.showAppointmentByUserId(userId, pb);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.task.impl.AppointmentServiceImpl
 * JD-Core Version:    0.5.4
 */