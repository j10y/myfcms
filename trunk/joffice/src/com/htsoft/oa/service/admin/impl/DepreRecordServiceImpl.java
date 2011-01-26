 package com.htsoft.oa.service.admin.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.admin.DepreRecordDao;
 import com.htsoft.oa.model.admin.DepreRecord;
 import com.htsoft.oa.service.admin.DepreRecordService;
 import java.util.Date;
 
 public class DepreRecordServiceImpl extends BaseServiceImpl<DepreRecord>
   implements DepreRecordService
 {
   private DepreRecordDao dao;
 
   public DepreRecordServiceImpl(DepreRecordDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public Date findMaxDate(Long assetsId)
   {
     return this.dao.findMaxDate(assetsId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.DepreRecordServiceImpl
 * JD-Core Version:    0.5.4
 */