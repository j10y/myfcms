 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.system.DiaryDao;
 import com.htsoft.oa.model.system.Diary;
 import com.htsoft.oa.service.system.DiaryService;
 import java.util.List;
 
 public class DiaryServiceImpl extends BaseServiceImpl<Diary>
   implements DiaryService
 {
   private DiaryDao dao;
 
   public DiaryServiceImpl(DiaryDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<Diary> getAllBySn(PagingBean pb)
   {
     return null;
   }
 
   public List<Diary> getSubDiary(String userIds, PagingBean pb)
   {
     return this.dao.getSubDiary(userIds, pb);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.DiaryServiceImpl
 * JD-Core Version:    0.5.4
 */