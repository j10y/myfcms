 package com.htsoft.oa.workflow.event;
 
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.oa.model.info.ShortMessage;
 import com.htsoft.oa.model.personal.ErrandsRegister;
 import com.htsoft.oa.model.system.AppUser;
 import com.htsoft.oa.service.info.ShortMessageService;
 import com.htsoft.oa.service.personal.ErrandsRegisterService;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.jbpm.api.listener.EventListener;
 import org.jbpm.api.listener.EventListenerExecution;
 import org.jbpm.api.model.OpenProcessInstance;
 
 public class HolidayApprovalListener
   implements EventListener
 {
   private Log logger = LogFactory.getLog(HolidayApprovalListener.class);
   Short choice;
 
   public void notify(EventListenerExecution execution)
     throws Exception
   {
     if (this.logger.isDebugEnabled()) {
       this.logger.info("enter the HolidayApprovalListener notify method...");
     }
 
     OpenProcessInstance pi = execution.getProcessInstance();
 
     Long dateId = (Long)pi.getVariable("dateId");
 
     String superOption = (String)pi.getVariable("superOption");
 
     if (dateId == null)
       return;
     ErrandsRegisterService erService = (ErrandsRegisterService)AppUtil.getBean("errandsRegisterService");
     ShortMessageService smService = (ShortMessageService)AppUtil.getBean("shortMessageService");
     ErrandsRegister errandsRegister = (ErrandsRegister)erService.get(dateId);
 
     if (errandsRegister != null) {
       errandsRegister.setStatus(this.choice);
       errandsRegister.setApprovalOption(superOption);
       erService.save(errandsRegister);
 
       smService.save(AppUser.SYSTEM_USER, errandsRegister.getUserId().toString(), "你的请假申请  （" + errandsRegister.getDescp() + "），最终审批意见：" + superOption, ShortMessage.MSG_TYPE_SYS);
     }
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.workflow.event.HolidayApprovalListener
 * JD-Core Version:    0.5.4
 */