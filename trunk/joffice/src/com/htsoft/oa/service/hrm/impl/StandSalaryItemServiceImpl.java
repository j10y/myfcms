 package com.htsoft.oa.service.hrm.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.hrm.StandSalaryItemDao;
 import com.htsoft.oa.model.hrm.StandSalaryItem;
 import com.htsoft.oa.service.hrm.StandSalaryItemService;
 import java.util.List;
 
 public class StandSalaryItemServiceImpl extends BaseServiceImpl<StandSalaryItem>
   implements StandSalaryItemService
 {
   private StandSalaryItemDao dao;
 
   public StandSalaryItemServiceImpl(StandSalaryItemDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<StandSalaryItem> getAllByStandardId(Long standardId)
   {
     return this.dao.getAllByStandardId(standardId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.hrm.impl.StandSalaryItemServiceImpl
 * JD-Core Version:    0.5.4
 */