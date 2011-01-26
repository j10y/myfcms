 package com.htsoft.oa.action.personal;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.personal.HolidayRecord;
 import com.htsoft.oa.service.personal.HolidayRecordService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class HolidayRecordAction extends BaseAction
 {
 
   @Resource
   private HolidayRecordService holidayRecordService;
   private HolidayRecord holidayRecord;
   private Long recordId;
 
   public Long getRecordId()
   {
     return this.recordId;
   }
 
   public void setRecordId(Long recordId) {
     this.recordId = recordId;
   }
 
   public HolidayRecord getHolidayRecord() {
     return this.holidayRecord;
   }
 
   public void setHolidayRecord(HolidayRecord holidayRecord) {
     this.holidayRecord = holidayRecord;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.holidayRecordService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.holidayRecordService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     HolidayRecord holidayRecord = (HolidayRecord)this.holidayRecordService.get(this.recordId);
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(holidayRecord));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     if (this.holidayRecord.getIsAll() == null)
       this.holidayRecord.setIsAll(HolidayRecord.IS_PERSONAL_HOLIDAY);
     else {
       this.holidayRecord.setIsAll(HolidayRecord.IS_ALL_HOLIDAY);
     }
     this.holidayRecordService.save(this.holidayRecord);
 
     setJsonString("{success:true}");
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.personal.HolidayRecordAction
 * JD-Core Version:    0.5.4
 */