 package com.zving.workflow;
 
 import com.zving.workflow.util.XMLizable;
 import java.io.PrintWriter;
 import java.io.Serializable;
 import java.io.StringWriter;
 
 public abstract class AbstractDescriptor
   implements XMLizable, Serializable
 {
   private AbstractDescriptor parent;
   private boolean hasId = false;
   private int entityId;
   private int id;
 
   public void setEntityId(int entityId)
   {
     this.entityId = entityId;
   }
 
   public int getEntityId() {
     return this.entityId;
   }
 
   public void setId(int id) {
     this.id = id;
     this.hasId = true;
   }
 
   public int getId() {
     return this.id;
   }
 
   public void setParent(AbstractDescriptor parent) {
     this.parent = parent;
   }
 
   public AbstractDescriptor getParent() {
     return this.parent;
   }
 
   public String asXML() {
     StringWriter stringWriter = new StringWriter();
     PrintWriter writer = new PrintWriter(stringWriter);
     writeXML(writer, 0);
     writer.close();
 
     return stringWriter.toString();
   }
 
   public boolean hasId() {
     return this.hasId;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.AbstractDescriptor
 * JD-Core Version:    0.5.3
 */