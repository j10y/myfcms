 package com.zving.workflow;
 
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.List;
 import org.w3c.dom.Element;
 
 public class RestrictionDescriptor extends AbstractDescriptor
 {
   protected List conditions = new ArrayList();
 
   public RestrictionDescriptor()
   {
   }
 
   public RestrictionDescriptor(Element restriction)
   {
     init(restriction);
   }
 

   public List getConditions()
   {
     return this.conditions;
   }
 
   public void setConditionsDescriptor(ConditionsDescriptor descriptor) {
     if (this.conditions.size() == 1)
       this.conditions.set(0, descriptor);
     else
       this.conditions.add(descriptor);
   }
 
   public ConditionsDescriptor getConditionsDescriptor()
   {
     if (this.conditions.size() == 0) {
       return null;
     }
 
     return ((ConditionsDescriptor)this.conditions.get(0));
   }
 
   public void writeXML(PrintWriter out, int indent) {
     ConditionsDescriptor conditions = getConditionsDescriptor();
 
     List list = conditions.getConditions();
 
     if (list.size() == 0) {
       return;
     }
 
     XMLUtil.printIndent(out, indent++);
     out.println("<restrict-to>");
     conditions.writeXML(out, indent);
     XMLUtil.printIndent(out, --indent);
     out.println("</restrict-to>");
   }
 
   protected void init(Element restriction)
   {
     List conditionNodes = XMLUtil.getChildElements(restriction, "conditions");
     int length = conditionNodes.size();
 
     for (int i = 0; i < length; ++i) {
       Element condition = (Element)conditionNodes.get(i);
       ConditionsDescriptor conditionDescriptor = DescriptorFactory.getFactory().createConditionsDescriptor(condition);
       conditionDescriptor.setParent(this);
       this.conditions.add(conditionDescriptor);
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.RestrictionDescriptor
 * JD-Core Version:    0.5.3
 */