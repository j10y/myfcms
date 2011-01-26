 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.DictionaryDao;
 import com.htsoft.oa.model.system.Dictionary;
 import com.htsoft.oa.service.system.DictionaryService;
 import java.util.List;
 
 public class DictionaryServiceImpl extends BaseServiceImpl<Dictionary>
   implements DictionaryService
 {
   private DictionaryDao dao;
 
   public DictionaryServiceImpl(DictionaryDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public List<String> getAllItems()
   {
     return this.dao.getAllItems();
   }
 
   public List<String> getAllByItemName(String itemName)
   {
     return this.dao.getAllByItemName(itemName);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.DictionaryServiceImpl
 * JD-Core Version:    0.5.4
 */