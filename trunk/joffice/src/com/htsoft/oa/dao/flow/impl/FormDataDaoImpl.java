 package com.htsoft.oa.dao.flow.impl;
 
 import com.htsoft.core.dao.impl.BaseDaoImpl;
 import com.htsoft.oa.dao.flow.FormDataDao;
 import com.htsoft.oa.model.flow.FormData;
 import java.util.List;
 
 public class FormDataDaoImpl extends BaseDaoImpl<FormData>
   implements FormDataDao
 {
   public FormDataDaoImpl()
   {
     super(FormData.class);
   }
 
   public List<FormData> getByRunIdActivityName(Long runId, String activityName)
   {
     String hql = "from FormData fd where fd.processForm.processRun.runId=? and fd.processForm.activityName=?";
     return findByHql(hql, new Object[] { runId, activityName });
   }
 
   public FormData getByFormIdFieldName(Long formId, String fieldName)
   {
     String hql = "from FormData fd where fd.processForm.formId=? and fd.fieldName=?";
     return (FormData)findUnique(hql, new Object[] { formId, fieldName });
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.dao.flow.impl.FormDataDaoImpl
 * JD-Core Version:    0.5.4
 */