 package com.zving.workflow;
 
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
 import org.w3c.dom.Element;
 
 public class StepDescriptor extends AbstractDescriptor
 {
   protected List actions = new ArrayList();
 
   protected List permissions = new ArrayList();
   protected List postFunctions = new ArrayList();
   protected List preFunctions = new ArrayList();
   protected String name;
   protected boolean hasActions = false;
 

   StepDescriptor()
   {
   }
 

   StepDescriptor(Element step)
   {
     init(step);
   }
 
   StepDescriptor(Element step, AbstractDescriptor parent)
   {
     setParent(parent);
     init(step);
   }
 
   public ActionDescriptor getAction(long id)
   {
     for (Iterator iterator = this.actions.iterator(); iterator.hasNext(); ) {
       ActionDescriptor action = (ActionDescriptor)iterator.next();
 
       if (action.getId() == id) {
         return action;
       }
     }
 
     return null;
   }
 
   public List getActions()
   {
     return this.actions;
   }
 
   public void setName(String name) {
     this.name = name;
   }
 
   public String getName() {
     return this.name;
   }
 
   public List getPermissions()
   {
     return this.permissions;
   }
 
   public void setPostFunctions(List postFunctions) {
     this.postFunctions = postFunctions;
   }
 
   public List getPostFunctions() {
     return this.postFunctions;
   }
 
   public void setPreFunctions(List preFunctions) {
     this.preFunctions = preFunctions;
   }
 
   public List getPreFunctions() {
     return this.preFunctions;
   }
 
   public void removeActions()
   {
     this.actions.clear();
     this.hasActions = false;
   }
 
   public boolean resultsInJoin(int join) {
     for (Iterator iterator = this.actions.iterator(); iterator.hasNext(); ) {
       ActionDescriptor actionDescriptor = (ActionDescriptor)iterator.next();
 
       if (actionDescriptor.getUnconditionalResult().getJoin() == join) {
         return true;
       }
 
       List results = actionDescriptor.getConditionalResults();
 
       for (Iterator iterator2 = results.iterator(); iterator2.hasNext(); ) {
         ConditionalResultDescriptor resultDescriptor = (ConditionalResultDescriptor)iterator2.next();
 
         if (resultDescriptor.getJoin() == join) {
           return true;
         }
       }
     }
 
     return false;
   }
 
   public void writeXML(PrintWriter out, int indent)
   {
     XMLUtil.printIndent(out, indent++);
     out.print("<step id=\"" + getId() + "\"");
 
     if ((this.name != null) && (this.name.length() > 0)) {
       out.print(" name=\"" + XMLUtil.encode(this.name) + "\"");
     }
 
     out.println(">");
     int i;
     FunctionDescriptor function;
     if (this.preFunctions.size() > 0) {
       XMLUtil.printIndent(out, indent++);
       out.println("<pre-functions>");
 
       for (i = 0; i < this.preFunctions.size(); ++i) {
         function = (FunctionDescriptor)this.preFunctions.get(i);
         function.writeXML(out, indent);
       }
 
       XMLUtil.printIndent(out, --indent);
       out.println("</pre-functions>");
     }
 
     if (this.permissions.size() > 0) {
       XMLUtil.printIndent(out, indent++);
       out.println("<external-permissions>");
 
       for (i = 0; i < this.permissions.size(); ++i) {
         PermissionDescriptor permission = (PermissionDescriptor)this.permissions.get(i);
         permission.writeXML(out, indent);
       }
 
       XMLUtil.printIndent(out, --indent);
       out.println("</external-permissions>");
     }
 
     if (this.actions.size() > 0) {
       XMLUtil.printIndent(out, indent++);
       out.println("<actions>");
 
       for (i = 0; i < this.actions.size(); ++i) {
         ActionDescriptor action = (ActionDescriptor)this.actions.get(i);
 
         if (!(action.isCommon())) {
           action.writeXML(out, indent);
         }
       }
 
       XMLUtil.printIndent(out, --indent);
       out.println("</actions>");
     }
 
     if (this.postFunctions.size() > 0) {
       XMLUtil.printIndent(out, indent++);
       out.println("<post-functions>");
 
       for (i = 0; i < this.postFunctions.size(); ++i) {
         function = (FunctionDescriptor)this.postFunctions.get(i);
         function.writeXML(out, indent);
       }
 
       XMLUtil.printIndent(out, --indent);
       out.println("</post-functions>");
     }
 
     XMLUtil.printIndent(out, --indent);
     out.println("</step>");
   }
 
   protected void init(Element step) {
     try {
       setId(Integer.parseInt(step.getAttribute("id")));
     } catch (Exception ex) {
       throw new IllegalArgumentException("Invalid step id value " + step.getAttribute("id"));
     }
 
     this.name = step.getAttribute("name");
 
     Element pre = XMLUtil.getChildElement(step, "pre-functions");
 
     if (pre != null) {
       List preFunctions = XMLUtil.getChildElements(pre, "function");
 
       for (int k = 0; k < preFunctions.size(); ++k) {
         Element preFunction = (Element)preFunctions.get(k);
         FunctionDescriptor functionDescriptor = DescriptorFactory.getFactory().createFunctionDescriptor(preFunction);
         functionDescriptor.setParent(this);
         this.preFunctions.add(functionDescriptor);
       }
 
     }
 
     Element p = XMLUtil.getChildElement(step, "external-permissions");
 
     if (p != null) {
       List permissions = XMLUtil.getChildElements(p, "permission");
 
       for (int i = 0; i < permissions.size(); ++i) {
         Element permission = (Element)permissions.get(i);
         PermissionDescriptor permissionDescriptor = DescriptorFactory.getFactory().createPermissionDescriptor(permission);
         permissionDescriptor.setParent(this);
         this.permissions.add(permissionDescriptor);
       }
 
     }
 
     Element a = XMLUtil.getChildElement(step, "actions");
 
     if (a != null) {
       this.hasActions = true;
 
       List actions = XMLUtil.getChildElements(a, "action");
 
       for (int i = 0; i < actions.size(); ++i) {
         Element action = (Element)actions.get(i);
         ActionDescriptor actionDescriptor = DescriptorFactory.getFactory().createActionDescriptor(action);
         actionDescriptor.setParent(this);
         this.actions.add(actionDescriptor);
       }
 
     }
 
     Element post = XMLUtil.getChildElement(step, "post-functions");
 
     if (post != null) {
       List postFunctions = XMLUtil.getChildElements(post, "function");
 
       for (int k = 0; k < postFunctions.size(); ++k) {
         Element postFunction = (Element)postFunctions.get(k);
         FunctionDescriptor functionDescriptor = DescriptorFactory.getFactory().createFunctionDescriptor(postFunction);
         functionDescriptor.setParent(this);
         this.postFunctions.add(functionDescriptor);
       }
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.StepDescriptor
 * JD-Core Version:    0.5.3
 */