 package com.htsoft.oa.service.personal.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.util.DateUtil;
 import com.htsoft.oa.dao.personal.DutyRegisterDao;
 import com.htsoft.oa.dao.personal.DutySectionDao;
 import com.htsoft.oa.model.personal.DutyRegister;
 import com.htsoft.oa.model.personal.DutySection;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.personal.DutyRegisterService;
 import java.util.Calendar;
 import java.util.Date;
 import javax.annotation.Resource;
 
 public class DutyRegisterServiceImpl extends BaseServiceImpl<DutyRegister>
   implements DutyRegisterService
 {
   private DutyRegisterDao dao;
 
   @Resource
   private DutySectionDao dutySectionDao;
 
   public DutyRegisterServiceImpl(DutyRegisterDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public void signInOff(Long sectionId, Short signInOff, AppUser curUser, Date registerDate)
   {
     DutySection dutySection = (DutySection)this.dutySectionDao.get(sectionId);
 
     DutyRegister dutyRegister = this.dao.getTodayUserRegister(curUser.getUserId(), signInOff, sectionId);
     if (dutyRegister != null) {
       return;
     }
 
     DutyRegister register = new DutyRegister();
     register.setAppUser(curUser);
     register.setFullname(curUser.getFullname());
 
     Calendar regiserCal = Calendar.getInstance();
     regiserCal.setTime(registerDate);
     register.setRegisterDate(registerDate);
     register.setDayOfWeek(Integer.valueOf(regiserCal.get(7)));
     register.setInOffFlag(signInOff);
 
     register.setDutySection(dutySection);
 
     Calendar startCalendar = Calendar.getInstance();
     if (DutyRegister.SIGN_IN.equals(signInOff))
       startCalendar.setTime(dutySection.getDutyStartTime());
     else {
       startCalendar.setTime(dutySection.getDutyEndTime());
     }
 
     DateUtil.copyYearMonthDay(startCalendar, regiserCal);
 
     if (DutyRegister.SIGN_IN.equals(signInOff)) {
       if (regiserCal.compareTo(startCalendar) > 0) {
         register.setRegFlag(DutyRegister.REG_FLAG_LATE);
 
         Long minis = Long.valueOf((regiserCal.getTimeInMillis() - startCalendar.getTimeInMillis()) / 60000L);
         register.setRegMins(Integer.valueOf(minis.intValue()));
       } else {
         register.setRegFlag(DutyRegister.REG_FLAG_NORMAL);
         register.setRegMins(Integer.valueOf(0));
       }
     }
     else if (regiserCal.compareTo(startCalendar) < 0) {
       register.setRegFlag(DutyRegister.REG_FLAG_EARLY_OFF);
 
       Long minis = Long.valueOf((startCalendar.getTimeInMillis() - regiserCal.getTimeInMillis()) / 60000L);
       register.setRegMins(Integer.valueOf(minis.intValue()));
     } else {
       register.setRegFlag(DutyRegister.REG_FLAG_NORMAL);
       register.setRegMins(Integer.valueOf(0));
     }
 
     save(register);
   }
 
   public DutyRegister getTodayUserRegister(Long userId, Short inOffFlag, Long sectionId)
   {
     return this.dao.getTodayUserRegister(userId, inOffFlag, sectionId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.impl.DutyRegisterServiceImpl
 * JD-Core Version:    0.5.4
 */