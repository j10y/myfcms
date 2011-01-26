 package com.htsoft.oa.dao.system.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.system.DiaryDao;
 import com.htsoft.oa.model.system.Diary;
 import java.util.List;
 
 public class DiaryDaoImpl extends BaseDaoImpl<Diary>
   implements DiaryDao
 {
   public DiaryDaoImpl()
   {
     super(Diary.class);
   }
 
   public List<Diary> getSubDiary(String userIds, PagingBean pb)
   {
     String hql = "from Diary vo where vo.appUser.userId in (" + userIds + ") and vo.diaryType=1";
     return findByHql(hql, null, pb);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.system.impl.DiaryDaoImpl
 * JD-Core Version:    0.5.4
 */