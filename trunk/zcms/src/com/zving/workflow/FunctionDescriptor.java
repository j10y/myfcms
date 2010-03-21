 package com.zving.workflow;
 
 import java.io.PrintWriter;
 import java.util.HashMap;
 import java.util.Iterator;
 import java.util.List;
 import java.util.Map;
 import java.util.Map.Entry;
 import java.util.Set;
 import org.w3c.dom.Element;
 
 public class FunctionDescriptor extends AbstractDescriptor
 {
   protected Map args = new HashMap();
   protected String name;
   protected String type;
 

   FunctionDescriptor()
   {
   }
 

   FunctionDescriptor(Element function)
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
 
   public void setType(String type) {
     this.type = type;
   }
 
   public String getType() {
     return this.type;
   }
 
   public void writeXML(PrintWriter out, int indent) {
     XMLUtil.printIndent(out, indent++);
     out.println("<function " + ((hasId()) ? "id=\"" + getId() + "\" " : "") + (((this.name != null) && (this.name.length() > 0)) ? "name=\"" + XMLUtil.encode(getName()) + "\" " : "") + "type=\"" + this.type + "\">");
 
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
     out.println("</function>");
   }
 
   protected void init(Element function) {
     this.type = function.getAttribute("type");
     try
     {
       setId(Integer.parseInt(function.getAttribute("id")));
     }
     catch (NumberFormatException localNumberFormatException) {
     }
     if (function.getAttribute("name") != null) {
       this.name = function.getAttribute("name");
     }
 
     List args = XMLUtil.getChildElements(function, "arg");
 
     for (int l = 0; l < args.size(); ++l) {
       Element arg = (Element)args.get(l);
       String value = XMLUtil.getText(arg);
 
       this.args.put(arg.getAttribute("name"), value);
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.FunctionDescriptor
 * JD-Core Version:    0.5.3
 */