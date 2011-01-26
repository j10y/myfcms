 package com.htsoft.oa.action.hrm;
 
 import com.google.gson.Gson;
 import com.google.gson.reflect.TypeToken;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.hrm.EmpProfile;
 import com.htsoft.oa.model.hrm.JobChange;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.hrm.EmpProfileService;
 import com.htsoft.oa.service.hrm.JobChangeService;
 import java.lang.reflect.Type;
 import java.util.Date;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 
 public class JobChangeAction extends BaseAction
 {
 
   @Resource
   private JobChangeService jobChangeService;
   private JobChange jobChange;
 
   @Resource
   private EmpProfileService empProfileService;
   private Long changeId;
 
   public Long getChangeId()
   {
     return this.changeId;
   }
 
   public void setChangeId(Long changeId) {
     this.changeId = changeId;
   }
 
   public JobChange getJobChange() {
     return this.jobChange;
   }
 
   public void setJobChange(JobChange jobChange) {
     this.jobChange = jobChange;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.jobChangeService.getAll(filter);
 
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
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.jobChangeService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     JobChange jobChange = (JobChange)this.jobChangeService.get(this.changeId);
 
     Gson gson = new Gson();
 
     StringBuffer sb = new StringBuffer("{success:true,data:[");
     sb.append(gson.toJson(jobChange));
     sb.append("]}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     this.jobChange.setRegName(ContextUtil.getCurrentUser().getFullname());
     this.jobChange.setRegTime(new Date());
     this.jobChangeService.save(this.jobChange);
     setJsonString("{success:true}");
     return "success";
   }
 
   public String load()
   {
     String strId = getRequest().getParameter("changeId");
     if (StringUtils.isNotEmpty(strId)) {
       this.jobChange = ((JobChange)this.jobChangeService.get(new Long(strId)));
     }
     return "load";
   }
 
   public String check()
   {
     AppUser appUser = ContextUtil.getCurrentUser();
     Short status = this.jobChange.getStatus();
     String changeOpinion = this.jobChange.getCheckOpinion();
     Long changeId = this.jobChange.getChangeId();
     if (changeId != null) {
       this.jobChange = ((JobChange)this.jobChangeService.get(changeId));
       this.jobChange.setStatus(status);
       this.jobChange.setCheckOpinion(changeOpinion);
       this.jobChange.setCheckName(appUser.getFullname());
       this.jobChange.setCheckTime(new Date());
       this.jobChangeService.save(this.jobChange);
       if (status.shortValue() == 1) {
         Long profileId = this.jobChange.getProfileId();
         if (profileId != null) {
           EmpProfile empProfile = (EmpProfile)this.empProfileService.get(profileId);
           empProfile.setJobId(this.jobChange.getNewJobId());
           empProfile.setPosition(this.jobChange.getNewJobName());
           empProfile.setDepId(this.jobChange.getNewDepId());
           empProfile.setDepName(this.jobChange.getNewDepName());
           empProfile.setStandardId(this.jobChange.getNewStandardId());
           empProfile.setStandardMiNo(this.jobChange.getNewStandardNo());
           empProfile.setStandardName(this.jobChange.getNewStandardName());
           empProfile.setStandardMoney(this.jobChange.getNewTotalMoney());
           this.empProfileService.merge(empProfile);
         }
       }
       setJsonString("{success:true}");
     } else {
       setJsonString("{success:false}");
     }
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.hrm.JobChangeAction
 * JD-Core Version:    0.5.4
 */