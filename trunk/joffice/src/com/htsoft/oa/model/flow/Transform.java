 package com.htsoft.oa.model.flow;
 
 import org.jbpm.pvm.internal.model.Activity;
 import org.jbpm.pvm.internal.model.Transition;
 
 public class Transform
 {
   private String name;
   private String destination;
   private String source;
 
   public String getName()
   {
     return this.name;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getDestination() {
     return this.destination;
   }
 
   public void setDestination(String destination) {
     this.destination = destination;
   }
 
   public String getSource() {
     return this.source;
   }
 
   public void setSource(String source) {
     this.source = source;
   }
 
   public Transform()
   {
   }
 
   public Transform(Transition jbpmtran)
   {
     this.name = jbpmtran.getName();
     this.source = jbpmtran.getSource().getName();
     this.destination = jbpmtran.getDestination().getName();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.flow.Transform
 * JD-Core Version:    0.5.4
 */