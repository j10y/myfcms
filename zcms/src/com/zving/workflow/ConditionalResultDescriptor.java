 package com.zving.workflow;
 
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.List;
 import org.w3c.dom.Element;
 
 public class ConditionalResultDescriptor extends ResultDescriptor
 {
   protected List conditions = new ArrayList();
 

   ConditionalResultDescriptor()
   {
   }
 

   ConditionalResultDescriptor(Element conditionalResult)
   {
     init(conditionalResult);
   }
 
   public List getConditions()
   {
     return this.conditions;
   }
 
   public String getDestination() {
     WorkflowDescriptor desc = null;
     String sName = "";
     AbstractDescriptor actionDesc = getParent().getParent();
 
     if (actionDesc != null) {
       desc = (WorkflowDescriptor)actionDesc.getParent();
     }
 
     if (this.join != 0)
       return "join #" + this.join;
     if (this.split != 0) {
       return "split #" + this.split;
     }
     if (desc != null) {
       sName = desc.getStep(this.step).getName();
     }
 
     return "step #" + this.step + " [" + sName + "]";
   }
 
   public void writeXML(PrintWriter out, int indent)
   {
     XMLUtil.printIndent(out, indent++);
 
     StringBuffer buf = new StringBuffer();
     buf.append("<result");
 
     if (hasId()) {
       buf.append(" id=\"").append(getId()).append('"');
     }
 
     if ((this.dueDate != null) && (this.dueDate.length() > 0)) {
       buf.append(" due-date=\"").append(getDueDate()).append('"');
     }
 
     buf.append(" old-status=\"").append(this.oldStatus).append('"');
 
     if (this.join != 0) {
       buf.append(" join=\"").append(this.join).append('"');
     } else if (this.split != 0) {
       buf.append(" split=\"").append(this.split).append('"');
     } else {
       buf.append(" status=\"").append(this.status).append('"');
       buf.append(" step=\"").append(this.step).append('"');
 
       if ((this.owner != null) && (this.owner.length() > 0)) {
         buf.append(" owner=\"").append(this.owner).append('"');
       }
 
       if ((this.displayName != null) && (this.displayName.length() > 0)) {
         buf.append(" display-name=\"").append(this.displayName).append('"');
       }
     }
 
     buf.append('>');
     out.println(buf);
 
     for (int i = 0; i < this.conditions.size(); ++i) {
       ConditionsDescriptor condition = (ConditionsDescriptor)this.conditions.get(i);
       condition.writeXML(out, indent);
     }
 
     printPreFunctions(out, indent);
     printPostFunctions(out, indent);
     XMLUtil.printIndent(out, --indent);
     out.println("</result>");
   }
 
   protected void init(Element conditionalResult) {
     super.init(conditionalResult);
 
     List conditionNodes = XMLUtil.getChildElements(conditionalResult, "conditions");
 
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
 * Qualified Name:     com.zving.workflow.ConditionalResultDescriptor
 * JD-Core Version:    0.5.3
 */