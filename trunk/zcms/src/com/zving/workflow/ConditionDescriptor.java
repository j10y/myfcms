 package com.zving.workflow;
 
 import java.io.PrintWriter;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import org.w3c.dom.Element;
 
 public class ConditionDescriptor extends AbstractDescriptor
 {
   protected Map args = new HashMap();
   protected String name;
   protected String type;
   protected boolean negate = false;
 

   ConditionDescriptor()
   {
   }
 

   ConditionDescriptor(Element function)
   {
     init(function);
   }
 
   public Map getArgs()
   {
     return this.args;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setNegate(boolean negate) {
     this.negate = negate;
   }
 
   public boolean isNegate() {
     return this.negate;
   }
 
   public void setType(String type) {
     this.type = type;
   }
 
   public String getType() {
     return this.type;
   }
 
   public void writeXML(PrintWriter out, int indent) {
     XMLUtil.printIndent(out, indent++);
     out.println("<condition " + ((hasId()) ? "id=\"" + getId() + "\" " : "") + (((this.name != null) && (this.name.length() > 0)) ? "name=\"" + getName() + "\" " : "") + ((this.negate) ? "negate=\"true\" " : "") + "type=\"" + this.type + "\">");
 
     Iterator iter = this.args.entrySet().iterator();
 
     while (iter.hasNext()) {
       Map.Entry entry = (Map.Entry)iter.next();
       XMLUtil.printIndent(out, indent);
       out.print("<arg name=\"");
       out.print(entry.getKey());
       out.print("\">");
 
       if (("beanshell".equals(this.type)) || ("bsf".equals(this.type))) {
         out.print("<![CDATA[");
         out.print(entry.getValue());
         out.print("]]>");
       } else {
         out.print(XMLUtil.encode(entry.getValue()));
       }
 
       out.println("</arg>");
     }
 
     XMLUtil.printIndent(out, --indent);
     out.println("</condition>");
   }
 
   protected void init(Element condition) {
     this.type = condition.getAttribute("type");
     try
     {
       setId(Integer.parseInt(condition.getAttribute("id")));
     }
     catch (NumberFormatException localNumberFormatException) {
     }
     String n = condition.getAttribute("negate");
 
     if (("true".equalsIgnoreCase(n)) || ("yes".equalsIgnoreCase(n)))
       this.negate = true;
     else {
       this.negate = false;
     }
 
     if (condition.getAttribute("name") != null) {
       this.name = condition.getAttribute("name");
     }
 
     List args = XMLUtil.getChildElements(condition, "arg");
 
     for (int l = 0; l < args.size(); ++l) {
       Element arg = (Element)args.get(l);
       this.args.put(arg.getAttribute("name"), XMLUtil.getText(arg));
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.ConditionDescriptor
 * JD-Core Version:    0.5.3
 */