 package com.zving.workflow;
 
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.List;
 import org.w3c.dom.Element;
 
 public class JoinDescriptor extends AbstractDescriptor
 {
   protected List conditions = new ArrayList();
   protected ResultDescriptor result;
 

   JoinDescriptor()
   {
   }
 

   JoinDescriptor(Element join)
   {
     init(join);
   }
 
   public List getConditions()
   {
     return this.conditions;
   }
 
   public void setResult(ResultDescriptor result) {
     this.result = result;
   }
 
   public ResultDescriptor getResult() {
     return this.result;
   }
 
   public void writeXML(PrintWriter out, int indent) {
     XMLUtil.printIndent(out, indent++);
     out.println("<join id=\"" + getId() + "\">");
 
     if (this.conditions.size() > 0) {
       for (int i = 0; i < this.conditions.size(); ++i) {
         ConditionsDescriptor condition = (ConditionsDescriptor)this.conditions.get(i);
         condition.writeXML(out, indent);
       }
     }
 
     if (this.result != null) {
       this.result.writeXML(out, indent);
     }
 
     XMLUtil.printIndent(out, --indent);
     out.println("</join>");
   }
 
   protected void init(Element join) {
     try {
       setId(Integer.parseInt(join.getAttribute("id")));
     } catch (Exception ex) {
       throw new IllegalArgumentException("Invalid join id value " + join.getAttribute("id"));
     }
 
     List conditionNodes = XMLUtil.getChildElements(join, "conditions");
     int length = conditionNodes.size();
 
     for (int i = 0; i < length; ++i) {
       Element condition = (Element)conditionNodes.get(i);
       ConditionsDescriptor conditionDescriptor = DescriptorFactory.getFactory().createConditionsDescriptor(condition);
       conditionDescriptor.setParent(this);
       this.conditions.add(conditionDescriptor);
     }
 
     Element resultElement = XMLUtil.getChildElement(join, "unconditional-result");
 
     if (resultElement != null) {
       this.result = new ResultDescriptor(resultElement);
       this.result.setParent(this);
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.JoinDescriptor
 * JD-Core Version:    0.5.3
 */