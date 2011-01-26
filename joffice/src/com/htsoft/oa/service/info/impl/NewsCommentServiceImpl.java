 package com.htsoft.oa.service.info.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.info.NewsCommentDao;
 import com.htsoft.oa.model.info.NewsComment;
 import com.htsoft.oa.service.info.NewsCommentService;
 
 public class NewsCommentServiceImpl extends BaseServiceImpl<NewsComment>
   implements NewsCommentService
 {
   private NewsCommentDao dao;
 
   public NewsCommentServiceImpl(NewsCommentDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.info.impl.NewsCommentServiceImpl
 * JD-Core Version:    0.5.4
 */