 package com.zving.workflow;
 
 import java.io.PrintWriter;
 import org.w3c.dom.Element;
 
 public class PermissionDescriptor extends AbstractDescriptor
 {
   protected RestrictionDescriptor restriction;
   protected String name;
 

   PermissionDescriptor()
   {
   }
 

   PermissionDescriptor(Element permission)
   {
     init(permission);
   }
 
   public void setName(String name)
   {
     this.name = name;
   }
 
   public String getName() {
     return this.name;
   }
 
   public void setRestriction(RestrictionDescriptor restriction) {
     this.restriction = restriction;
   }
 
   public RestrictionDescriptor getRestriction() {
     return this.restriction;
   }
 
   public void writeXML(PrintWriter out, int indent) {
     XMLUtil.printIndent(out, indent++);
     out.print("<permission ");
 
     if (hasId()) {
       out.print("id=\"" + getId() + "\" ");
     }
 
     out.println("name=\"" + this.name + "\">");
     this.restriction.writeXML(out, indent);
     XMLUtil.printIndent(out, --indent);
     out.println("</permission>");
   }
 
   protected void init(Element permission) {
     this.name = permission.getAttribute("name");
     try
     {
       setId(Integer.parseInt(permission.getAttribute("id")));
     }
     catch (NumberFormatException localNumberFormatException) {
     }
     this.restriction = new RestrictionDescriptor(XMLUtil.getChildElement(permission, "restrict-to"));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.PermissionDescriptor
 * JD-Core Version:    0.5.3
 */