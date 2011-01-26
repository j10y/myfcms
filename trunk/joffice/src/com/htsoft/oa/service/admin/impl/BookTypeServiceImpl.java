 package com.htsoft.oa.service.admin.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.admin.BookTypeDao;
 import com.htsoft.oa.model.admin.BookType;
 import com.htsoft.oa.service.admin.BookTypeService;
 
 public class BookTypeServiceImpl extends BaseServiceImpl<BookType>
   implements BookTypeService
 {
   private BookTypeDao dao;
 
   public BookTypeServiceImpl(BookTypeDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.admin.impl.BookTypeServiceImpl
 * JD-Core Version:    0.5.4
 */