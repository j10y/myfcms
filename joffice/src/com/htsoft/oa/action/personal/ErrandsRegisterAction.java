 package com.htsoft.oa.action.personal;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 import com.htsoft.core.command.QueryFilter;
 import com.htsoft.core.jbpm.pv.ParamField;
 import com.htsoft.core.util.ContextUtil;
 import com.htsoft.core.util.JsonUtil;
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.core.web.paging.PagingBean;
 import com.htsoft.oa.action.flow.FlowRunInfo;
 import com.htsoft.oa.action.flow.ProcessActivityAssistant;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.model.flow.ProcessForm;
 import com.htsoft.oa.model.flow.ProcessRun;
 import com.htsoft.oa.model.personal.ErrandsRegister;
 import com.htsoft.oa.service.flow.JbpmService;
 import com.htsoft.oa.service.flow.ProcessRunService;
 import com.htsoft.oa.service.personal.ErrandsRegisterService;
 import flexjson.JSONSerializer;
 import java.text.SimpleDateFormat;
 import java.util.List;
 import java.util.Map;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.commons.logging.Log;
 
 public class ErrandsRegisterAction extends BaseAction
 {
 
   @Resource
   private ProcessRunService processRunService;
 
   @Resource
   private JbpmService jbpmService;
 
   @Resource
   private ErrandsRegisterService errandsRegisterService;
   private ErrandsRegister errandsRegister;
   private Long dateId;
 
   public Long getDateId()
   {
     return this.dateId;
   }
 
   public void setDateId(Long dateId) {
     this.dateId = dateId;
   }
 
   public ErrandsRegister getErrandsRegister() {
     return this.errandsRegister;
   }
 
   public void setErrandsRegister(ErrandsRegister errandsRegister) {
     this.errandsRegister = errandsRegister;
   }
 
   public String list()
   {
     QueryFilter filter = new QueryFilter(getRequest());
     filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil.getCurrentUserId().toString());
     List list = this.errandsRegisterService.getAll(filter);
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
         this.errandsRegisterService.remove(new Long(id));
       }
     }
 
     this.jsonString = "{success:true}";
 
     return "success";
   }
 
   public String get()
   {
     ErrandsRegister errandsRegister = (ErrandsRegister)this.errandsRegisterService.get(this.dateId);
 
     Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
 
     StringBuffer sb = new StringBuffer("{success:true,data:");
     sb.append(gson.toJson(errandsRegister));
     sb.append("}");
     setJsonString(sb.toString());
     return "success";
   }
 
   public String save()
   {
     boolean isNew = this.errandsRegister.getDateId() == null;
 
     this.errandsRegister.setAppUser(ContextUtil.getCurrentUser());
     this.errandsRegister.setStatus(Short.valueOf(0));
     this.errandsRegisterService.save(this.errandsRegister);
 
     if ((ErrandsRegister.FLAG_LEAVE.equals(this.errandsRegister.getFlag())) && 
       (isNew)) {
       Map fieldMap = constructStartFlowMap(this.errandsRegister);
 
       ProDefinition proDefintion = this.jbpmService.getProDefinitionByKey("-requestHoliday");
 
       if (proDefintion != null) {
         ProcessRun processRun = this.processRunService.initNewProcessRun(proDefintion);
 
         ProcessForm processForm = new ProcessForm();
         processForm.setActivityName("开始");
         processForm.setProcessRun(processRun);
 
         FlowRunInfo runInfo = new FlowRunInfo();
         runInfo.setStartFlow(true);
 
         runInfo.setdAssignId(this.errandsRegister.getApprovalId().toString());
 
         this.processRunService.saveProcessRun(processRun, processForm, runInfo);
       }
       else {
         this.logger.error("请假流程没有定义！");
       }
     }
 
     setJsonString("{success:true}");
     return "success";
   }
 
   protected Map<String, ParamField> constructStartFlowMap(ErrandsRegister register)
   {
     String activityName = "开始";
     String processName = "请假";
 
     Map map = ProcessActivityAssistant.constructFieldMap(processName, activityName);
 
     ParamField pfDateId = (ParamField)map.get("dateId");
 
     if (pfDateId != null) {
       pfDateId.setValue(register.getDateId().toString());
     }
 
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     ParamField pfOption = (ParamField)map.get("reqDesc");
     if (pfOption != null) {
       pfOption.setValue(register.getDescp());
     }
 
     ParamField pfStartTime = (ParamField)map.get("startTime");
     if (pfStartTime != null) {
       pfStartTime.setValue(sdf.format(register.getStartTime()));
     }
 
     ParamField pfEndTime = (ParamField)map.get("endTime");
     if (pfEndTime != null) {
       pfEndTime.setValue(sdf.format(register.getEndTime()));
     }
 
     ParamField pfApprovalName = (ParamField)map.get("approvalName");
     if (pfApprovalName != null) {
       pfApprovalName.setValue(register.getApprovalName());
     }
 
     return map;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.personal.ErrandsRegisterAction
 * JD-Core Version:    0.5.4
 */