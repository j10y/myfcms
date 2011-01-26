 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.UserSubDao;
 import com.htsoft.oa.model.system.UserSub;
 import com.htsoft.oa.service.system.UserSubService;
 import java.util.HashSet;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Set;
 
 public class UserSubServiceImpl extends BaseServiceImpl<UserSub>
   implements UserSubService
 {
   private UserSubDao dao;
 
   public UserSubServiceImpl(UserSubDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public Set<Long> findAllUpUser(Long userId)
   {
     List list = this.dao.upUser(userId);
     Set set = new HashSet();
     for (Long l : list) {
       set.add(l);
       List newlist = this.dao.upUser(l);
       Set sets = new HashSet();
       for (Long lon : newlist) {
         set.add(lon);
         sets.add(lon);
       }
       findUp(set, sets);
     }
     return set;
   }
 
   public void findUp(Set<Long> setOld, Set<Long> setNew)
   {
     Iterator it = setNew.iterator();
     while (it.hasNext()) {
       Long userId = (Long)it.next();
       List newlist = this.dao.upUser(userId);
       setOld.add(userId);
       Set set = new HashSet();
       for (Long lon : newlist) {
         if (!setOld.contains(lon)) {
           set.add(lon);
         }
       }
       findUp(setOld, set);
     }
   }
 
   public List<Long> subUsers(Long userId)
   {
     return this.dao.subUsers(userId);
   }
 
   public List<Long> upUser(Long userId)
   {
     return this.dao.upUser(userId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.UserSubServiceImpl
 * JD-Core Version:    0.5.4
 */