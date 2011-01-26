 package com.htsoft.oa.action.flow;
 
 import com.htsoft.core.web.action.BaseAction;
 import com.htsoft.oa.model.flow.ProDefinition;
 import com.htsoft.oa.service.flow.ProDefinitionService;
 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 
 public class ProcessDetailAction extends BaseAction
 {
 
   @Resource
   private ProDefinitionService proDefinitionService;
   private ProDefinition proDefinition;
 
   public ProDefinition getProDefinition()
   {
     return this.proDefinition;
   }
 
   public void setProDefinition(ProDefinition proDefinition) {
     this.proDefinition = proDefinition;
   }
 
   public String execute() throws Exception
   {
     String defId = getRequest().getParameter("defId");
     this.proDefinition = ((ProDefinition)this.proDefinitionService.get(new Long(defId)));
     return "success";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.flow.ProcessDetailAction
 * JD-Core Version:    0.5.4
 */