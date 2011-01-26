 package com.htsoft.oa.service.system.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.system.SysConfigDao;
 import com.htsoft.oa.model.system.SysConfig;
 import com.htsoft.oa.service.system.SysConfigService;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 public class SysConfigServiceImpl extends BaseServiceImpl<SysConfig>
   implements SysConfigService
 {
   private SysConfigDao dao;
 
   public SysConfigServiceImpl(SysConfigDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public SysConfig findByKey(String key)
   {
     return this.dao.findByKey(key);
   }
 
   public Map findByType()
   {
     List list = this.dao.findTypeNames();
     Map cList = new HashMap();
     for (String typeName : list) {
       List confList = this.dao.findConfigByTypeName(typeName);
       cList.put(typeName, confList);
     }
     return cList;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.system.impl.SysConfigServiceImpl
 * JD-Core Version:    0.5.4
 */