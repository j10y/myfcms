 package com.zving.workflow;
 
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.List;
 import org.w3c.dom.Element;
 
 public class ResultDescriptor extends AbstractDescriptor
 {
   protected List postFunctions = new ArrayList();
   protected List preFunctions = new ArrayList();
   protected String displayName;
   protected String dueDate;
   protected String oldStatus;
   protected String owner;
   protected String status;
   protected boolean hasStep = false;
   protected int join;
   protected int split;
   protected int step = 0;
 

   ResultDescriptor()
   {
   }
 

   ResultDescriptor(Element result)
   {
     init(result);
   }
 
   public void setDisplayName(String displayName)
   {
     if ((getParent() instanceof ActionDescriptor) && 
       (((ActionDescriptor)getParent()).getName().equals(displayName))) {
       this.displayName = null;
 
       return;
     }
 
     this.displayName = displayName;
   }
 
   public String getDisplayName() {
     return this.displayName;
   }
 
   public String getDueDate() {
     return this.dueDate;
   }
 
   public void setJoin(int join) {
     this.join = join;
   }
 
   public int getJoin() {
     return this.join;
   }
 
   public void setOldStatus(String oldStatus) {
     this.oldStatus = oldStatus;
   }
 
   public String getOldStatus() {
     return this.oldStatus;
   }
 
   public void setOwner(String owner) {
     this.owner = owner;
   }
 
   public String getOwner() {
     return this.owner;
   }
 
   public List getPostFunctions() {
     return this.postFunctions;
   }
 
   public List getPreFunctions() {
     return this.preFunctions;
   }
 
   public void setSplit(int split) {
     this.split = split;
   }
 
   public int getSplit() {
     return this.split;
   }
 
   public void setStatus(String status) {
     this.status = status;
   }
 
   public String getStatus() {
     return this.status;
   }
 
   public void setStep(int step) {
     this.step = step;
     this.hasStep = true;
   }
 
   public int getStep() {
     return this.step;
   }
 
   public void writeXML(PrintWriter out, int indent) {
     XMLUtil.printIndent(out, indent++);
 
     StringBuffer buf = new StringBuffer();
     buf.append("<unconditional-result");
 
     if (hasId()) {
       buf.append(" id=\"").append(getId()).append("\"");
     }
 
     if ((this.dueDate != null) && (this.dueDate.length() > 0)) {
       buf.append(" due-date=\"").append(getDueDate()).append("\"");
     }
 
     buf.append(" old-status=\"").append(this.oldStatus).append("\"");
 
     if (this.join != 0) {
       buf.append(" join=\"").append(this.join).append("\"");
     } else if (this.split != 0) {
       buf.append(" split=\"").append(this.split).append("\"");
     } else {
       buf.append(" status=\"").append(this.status).append("\"");
       buf.append(" step=\"").append(this.step).append("\"");
 
       if ((this.owner != null) && (this.owner.length() > 0)) {
         buf.append(" owner=\"").append(this.owner).append("\"");
       }
 
       if ((this.displayName != null) && (this.displayName.length() > 0)) {
         buf.append(" display-name=\"").append(this.displayName).append("\"");
       }
     }
 
     if ((this.preFunctions.size() == 0) && (this.postFunctions.size() == 0)) {
       buf.append("/>");
       out.println(buf.toString());
     } else {
       buf.append(">");
       out.println(buf.toString());
       printPreFunctions(out, indent);
       printPostFunctions(out, indent);
       XMLUtil.printIndent(out, --indent);
       out.println("</unconditional-result>");
     }
   }
 
   protected void init(Element result) {
     this.oldStatus = result.getAttribute("old-status");
     this.status = result.getAttribute("status");
     try
     {
       setId(Integer.parseInt(result.getAttribute("id")));
     }
     catch (NumberFormatException localNumberFormatException) {
     }
     this.dueDate = result.getAttribute("due-date");
     try
     {
       this.split = Integer.parseInt(result.getAttribute("split"));
     }
     catch (Exception localException) {
     }
     try {
       this.join = Integer.parseInt(result.getAttribute("join"));
     }
     catch (Exception localException1) {
     }
     try {
       this.step = Integer.parseInt(result.getAttribute("step"));
       this.hasStep = true;
     }
     catch (Exception localException2) {
     }
     this.owner = result.getAttribute("owner");
     this.displayName = result.getAttribute("display-name");
 
     Element pre = XMLUtil.getChildElement(result, "pre-functions");
 
     if (pre != null) {
       List preFunctions = XMLUtil.getChildElements(pre, "function");
 
       for (int k = 0; k < preFunctions.size(); ++k) {
         Element preFunction = (Element)preFunctions.get(k);
         FunctionDescriptor functionDescriptor = DescriptorFactory.getFactory().createFunctionDescriptor(preFunction);
         functionDescriptor.setParent(this);
         this.preFunctions.add(functionDescriptor);
       }
 
     }
 
     Element post = XMLUtil.getChildElement(result, "post-functions");
 
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
 
   protected void printPostFunctions(PrintWriter out, int indent) {
     if (this.postFunctions.size() > 0) {
       XMLUtil.printIndent(out, indent++);
       out.println("<post-functions>");
 
       Iterator iter = this.postFunctions.iterator();
 
       while (iter.hasNext()) {
         FunctionDescriptor function = (FunctionDescriptor)iter.next();
         function.writeXML(out, indent);
       }
 
       XMLUtil.printIndent(out, --indent);
       out.println("</post-functions>");
     }
   }
 
   protected void printPreFunctions(PrintWriter out, int indent) {
     if (this.preFunctions.size() > 0) {
       XMLUtil.printIndent(out, indent++);
       out.println("<pre-functions>");
 
       Iterator iter = this.preFunctions.iterator();
 
       while (iter.hasNext()) {
         FunctionDescriptor function = (FunctionDescriptor)iter.next();
         function.writeXML(out, indent);
       }
 
       XMLUtil.printIndent(out, --indent);
       out.println("</pre-functions>");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.ResultDescriptor
 * JD-Core Version:    0.5.3
 */