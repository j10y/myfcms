 package com.htsoft.oa.model.flow;
 
 import java.util.HashMap;
 import java.util.Map;
 

 public class FlowStartInfo
 {
   private boolean isStartFlow = false;
 
   private Map variables = new HashMap();
 
   public FlowStartInfo(boolean isStartFlow, Map variables)
   {
     this.isStartFlow = isStartFlow;
     this.variables = variables;
   }
 
   public FlowStartInfo(boolean isStartFlow) {
     this.isStartFlow = isStartFlow;
   }
 
   public FlowStartInfo()
   {
   }
 
   public boolean isStartFlow() {
     return this.isStartFlow;
   }
 
   public void setStartFlow(boolean isStartFlow) {
     this.isStartFlow = isStartFlow;
   }
 
   public Map getVariables() {
     return this.variables;
   }
 
   public void setVariables(Map variables) {
     this.variables = variables;
   }
 
   public void setdAssignId(String assignId)
   {
     this.variables.put("flowAssignId", assignId);
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.FlowStartInfo
 * JD-Core Version:    0.5.4
 */