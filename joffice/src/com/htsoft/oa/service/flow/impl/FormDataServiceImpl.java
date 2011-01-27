 package com.htsoft.oa.service.flow.impl;
 
 import com.htsoft.core.service.impl.BaseServiceImpl;
 import com.htsoft.oa.dao.flow.FormDataDao;
 import com.htsoft.oa.model.flow.FormData;
 import com.htsoft.oa.service.flow.FormDataService;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;
 
 public class FormDataServiceImpl extends BaseServiceImpl<FormData>
   implements FormDataService
 {
   private FormDataDao dao;
 
   public FormDataServiceImpl(FormDataDao dao)
   {
     super(dao);
     this.dao = dao;
   }
 
   public Map<String, Object> getFromDataMap(Long runId, String activityName)
   {
     List<FormData> list = this.dao.getByRunIdActivityName(runId, activityName);
     Map dataMap = new HashMap();
     for (FormData form : list) {
       dataMap.put(form.getFieldName(), form.getValue());
     }
     return dataMap;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.service.flow.impl.FormDataServiceImpl
 * JD-Core Version:    0.5.4
 */