 package com.zving.workflow;
 
 import com.zving.workflow.util.XMLizable;
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.List;
 import org.w3c.dom.Element;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 
 public class ConditionsDescriptor extends AbstractDescriptor
 {
   private List conditions = new ArrayList();
   private String type;
 

   ConditionsDescriptor()
   {
   }
 

   ConditionsDescriptor(Element element)
   {
     this.type = element.getAttribute("type");
 
     NodeList children = element.getChildNodes();
     int size = children.getLength();
 
     for (int i = 0; i < size; ++i) {
       Node child = children.item(i);
 
       if (child instanceof Element)
         if ("condition".equals(child.getNodeName())) {
           ConditionDescriptor condition = DescriptorFactory.getFactory().createConditionDescriptor((Element)child);
           this.conditions.add(condition);
         } else if ("conditions".equals(child.getNodeName())) {
           ConditionsDescriptor condition = DescriptorFactory.getFactory().createConditionsDescriptor((Element)child);
           this.conditions.add(condition);
         }
     }
   }
 
   public void setConditions(List conditions)
   {
     this.conditions = conditions;
   }
 
   public List getConditions() {
     return this.conditions;
   }
 
   public void setType(String type) {
     this.type = type;
   }
 
   public String getType() {
     return this.type;
   }
 
   public void writeXML(PrintWriter out, int indent) {
     if (this.conditions.size() > 0) {
       XMLUtil.printIndent(out, indent++);
 
       StringBuffer sb = new StringBuffer("<conditions");
 
       if (this.conditions.size() > 1) {
         sb.append(" type=\"").append(this.type).append('"');
       }
 
       sb.append('>');
       out.println(sb.toString());
 
       for (int i = 0; i < this.conditions.size(); ++i) {
         XMLizable condition = (XMLizable)this.conditions.get(i);
         condition.writeXML(out, indent);
       }
 
       XMLUtil.printIndent(out, --indent);
       out.println("</conditions>");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.ConditionsDescriptor
 * JD-Core Version:    0.5.3
 */