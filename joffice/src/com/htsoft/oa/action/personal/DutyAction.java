 package com.htsoft.oa.action.personal;
 
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.util.BeanUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.model.personal.Duty;
 import com.htsoft.oa.model.personal.DutySystem;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.personal.DutyService;
 import com.htsoft.oa.service.personal.DutySystemService;
 import com.htsoft.oa.service.system.AppUserService;
 import flexjson.JSONSerializer;
 import java.text.SimpleDateFormat;
 import java.util.List;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.lang.StringUtils;
 import org.apache.commons.logging.Log;
 
 public class DutyAction extends BaseAction
 {
 
   @Resource
   private DutyService dutyService;
 
   @Resource
   private DutySystemService dutySystemService;
 
   @Resource
   private AppUserService appUserService;
   private Duty duty;
   private Long dutyId;
 
   public Long getDutyId()
   {
     return this.dutyId;
   }
 
   public void setDutyId(Long dutyId) {
     this.dutyId = dutyId;
   }
 
   public Duty getDuty() {
     return this.duty;
   }
 
   public void setDuty(Duty duty) {
     this.duty = duty;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     List list = this.dutyService.getAll(filter);
 
     StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
       .append(filter.getPagingBean().getTotalItems()).append(",result:");
 
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "startTime", "endTime" });
     buff.append(serializer.serialize(list));
     buff.append("}");
 
     this.jsonString = buff.toString();
 
     return "success";
   }
 
   public String multiDel()
   {
     String[] ids = getRequest().getParameterValues("ids");
     if (ids != null) {
       for (String id : ids) {
         this.dutyService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     Duty duty = (Duty)this.dutyService.get(this.dutyId);
     JSONSerializer serializer = JsonUtil.getJSONSerializer(new String[] { "startTime", "endTime" });
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(serializer.serialize(duty));
     sb.append("}");
     setJsonString(sb.toString());
 
     return "success";
   }
 
   public String save()
   {
     String systemId = getRequest().getParameter("dutySystemId");
 
     if (StringUtils.isNotEmpty(systemId)) {
       DutySystem dutySystem = (DutySystem)this.dutySystemService.get(new Long(systemId));
       this.duty.setDutySystem(dutySystem);
     }
 
     String userId = getRequest().getParameter("duty.userId");
 
     String[] uIds = userId.split("[,]");
 
     StringBuffer ssb = new StringBuffer("");
     boolean isExcept = false;
     if (uIds != null) {
       for (int i = 0; i < uIds.length; ++i) {
         AppUser user = (AppUser)this.appUserService.get(new Long(uIds[i]));
         Duty uDuty = new Duty();
         try
         {
           BeanUtil.copyNotNullProperties(uDuty, this.duty);
 
           boolean isExist = false;
           if (uDuty.getDutyId() == null) {
             isExist = this.dutyService.isExistDutyForUser(user.getUserId(), uDuty.getStartTime(), uDuty.getEndTime());
           }
           if (isExist) {
             isExcept = true;
             ssb.append(user.getFullname()).append(",");
           } else {
             uDuty.setAppUser(user);
             uDuty.setFullname(user.getFullname());
             this.dutyService.save(uDuty);
           }
         }
         catch (Exception ex) {
           this.logger.error("error:" + ex.getMessage());
         }
       }
 
     }
 
     if (isExcept) {
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       ssb.append("在该时间(").append(sdf.format(this.duty.getStartTime())).append("至")
         .append(sdf.format(this.duty.getEndTime())).append(")内已经存在班制!");
     }
 
     setJsonString("{success:true,exception:'" + ssb.toString() + "'}");
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.personal.DutyAction
 * JD-Core Version:    0.5.4
 */