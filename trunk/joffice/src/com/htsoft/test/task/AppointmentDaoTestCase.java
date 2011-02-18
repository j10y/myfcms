 package com.htsoft.test.task;
 
 import com.htsoft.oa.dao.task.AppointmentDao;
 import com.htsoft.oa.model.task.Appointment;
 import com.htsoft.test.BaseTestCase;
 import javax.annotation.Resource;
 import org.junit.Test;
 import org.springframework.test.annotation.Rollback;
 
 public class AppointmentDaoTestCase extends BaseTestCase
 {
 
   @Resource
   private AppointmentDao appointmentDao;
 
   @Test
   @Rollback(false)
   public void add()
   {
     Appointment appointment = new Appointment();
     this.appointmentDao.save(appointment);
   }
 }


 
 
 