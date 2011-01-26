 package com.htsoft.oa.action.personal;
 
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.personal.DutySystem;
 import com.htsoft.oa.model.personal.DutySystemSections;
 import com.htsoft.oa.service.personal.DutySystemService;
 import java.lang.reflect.Type;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class DutySystemAction extends BaseAction
 {
 
   @Resource
   private DutySystemService dutySystemService;
   private DutySystem dutySystem;
   private Long systemId;
 
   public Long getSystemId()
   {
     return this.systemId;
   }
 
   public void setSystemId(Long systemId) {
     this.systemId = systemId;
   }
 
   public DutySystem getDutySystem() {
     return this.dutySystem;
   }
 
   public void setDutySystem(DutySystem dutySystem) {
     this.dutySystem = dutySystem;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.dutySystemService.getAll(filter);
 
     Type type = new TypeToken() {  }
     .getType();
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     Gson gson = new Gson();
     buff.append(gson.toJson(list, type));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String setting()
   {
     if (this.systemId != null) {
       this.dutySystem = ((DutySystem)this.dutySystemService.get(this.systemId));
     }
     StringBuffer buff = new StringBuffer("{success:true,result:[{");
 
     if (this.dutySystem != null) {
       String[] ids = this.dutySystem.getSystemSetting().split("[|]");
       String[] desc = this.dutySystem.getSystemDesc().split("[|]");
 
       if ((desc != null) && (desc.length == 7)) {
         for (int i = 0; i < desc.length; ++i) {
           buff.append("day").append(i).append(":'").append(desc[i]).append("',");
         }
       }
       if ((ids != null) && (ids.length == 7)) {
         for (int i = 0; i < ids.length; ++i) {
           buff.append("dayId").append(i).append(":'").append(ids[i]).append("',");
         }
       }
 
       buff.deleteCharAt(buff.length() - 1);
     }
     else {
       buff.append("day0:'',day1:'',day2:'',day3:'',day4:'',day5:'',day6:''")
         .append(",dayId0:'',dayId1:'',dayId2:'',dayId3:'',dayId4:'',dayId5:'',dayId6:''");
     }
     buff.append("}]}");
     setJsonString(buff.toString());
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.dutySystemService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     DutySystem dutySystem = (DutySystem)this.dutySystemService.get(this.systemId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(dutySystem));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String combo()
   {
     StringBuffer sb = new StringBuffer();
 
     List dutySystemList = this.dutySystemService.getAll();
     sb.append("[");
     for (DutySystem dutySystem : dutySystemList) {
       sb.append("['").append(dutySystem.getSystemId()).append("','").append(dutySystem.getSystemName()).append("'],");
     }
     if (dutySystemList.size() > 0) {
       sb.deleteCharAt(sb.length() - 1);
     }
     sb.append("]");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String save()
   {
     String data = getRequest().getParameter("data");
 
     Gson gson = new Gson();
     DutySystemSections[] dss = (DutySystemSections[])gson.fromJson(data, [Lcom.htsoft.oa.model.personal.DutySystemSections.class);
 
     this.dutySystem.setSystemSetting(dss[0].dayIdToString());
     this.dutySystem.setSystemDesc(dss[0].dayToString());
 
     this.dutySystemService.save(this.dutySystem);
 
     setJsonString("{success:true}");
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.personal.DutySystemAction
 * JD-Core Version:    0.5.4
 */