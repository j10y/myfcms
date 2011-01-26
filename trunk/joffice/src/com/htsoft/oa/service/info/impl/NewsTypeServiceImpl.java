 package com.htsoft.oa.service.info.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.dao.info.NewsTypeDao;
 import com.htsoft.oa.model.info.NewsType;
 import com.htsoft.oa.service.info.NewsTypeService;
 import java.util.List;
 
 public class NewsTypeServiceImpl extends BaseServiceImpl<NewsType>
   implements NewsTypeService
 {
   private NewsTypeDao newsTypeDao;
 
   public NewsTypeServiceImpl(NewsTypeDao dao)
   {
     super(dao);
     this.newsTypeDao = dao;
   }
 
   public Short getTop()
   {
     return this.newsTypeDao.getTop();
   }
 
   public NewsType findBySn(Short sn)
   {
     return this.newsTypeDao.findBySn(sn);
   }
 
   public List<NewsType> getAllBySn()
   {
     return this.newsTypeDao.getAllBySn();
   }
 
   public List<NewsType> getAllBySn(PagingBean pb)
   {
     return this.newsTypeDao.getAllBySn(pb);
   }
 
   public List<NewsType> findBySearch(NewsType newsType, PagingBean pb)
   {
     return this.newsTypeDao.findBySearch(newsType, pb);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.impl.NewsTypeServiceImpl
 * JD-Core Version:    0.5.4
 */