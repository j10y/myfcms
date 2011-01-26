 package com.htsoft.oa.service.communicate.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.communicate.PhoneGroupDao;
 import com.htsoft.oa.model.communicate.PhoneGroup;
 import com.htsoft.oa.service.communicate.PhoneGroupService;
 import java.util.List;
 
 public class PhoneGroupServiceImpl extends BaseServiceImpl<PhoneGroup>
   implements PhoneGroupService
 {
   private PhoneGroupDao dao;
 
   public PhoneGroupServiceImpl(PhoneGroupDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public Integer findLastSn(Long userId)
   {
     return this.dao.findLastSn(userId);
   }
 
   public PhoneGroup findBySn(Integer sn, Long userId)
   {
     return this.dao.findBySn(sn, userId);
   }
 
   public List<PhoneGroup> findBySnUp(Integer sn, Long userId)
   {
     return this.dao.findBySnUp(sn, userId);
   }
 
   public List<PhoneGroup> findBySnDown(Integer sn, Long userId)
   {
     return this.dao.findBySnDown(sn, userId);
   }
 
   public List<PhoneGroup> getAll(Long userId)
   {
     return this.dao.getAll(userId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.communicate.impl.PhoneGroupServiceImpl
 * JD-Core Version:    0.5.4
 */