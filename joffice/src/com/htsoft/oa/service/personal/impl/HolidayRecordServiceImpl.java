 package com.htsoft.oa.service.personal.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.personal.HolidayRecordDao;
 import com.htsoft.oa.model.personal.HolidayRecord;
 import com.htsoft.oa.service.personal.HolidayRecordService;
 
 public class HolidayRecordServiceImpl extends BaseServiceImpl<HolidayRecord>
   implements HolidayRecordService
 {
   private HolidayRecordDao dao;
 
   public HolidayRecordServiceImpl(HolidayRecordDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.personal.impl.HolidayRecordServiceImpl
 * JD-Core Version:    0.5.4
 */