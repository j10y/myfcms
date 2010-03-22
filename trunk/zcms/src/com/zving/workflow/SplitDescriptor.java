 package com.zving.workflow;
 
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.List;
 import org.w3c.dom.Element;
 
 public class SplitDescriptor extends AbstractDescriptor
 {
   protected List results = new ArrayList();
 

   SplitDescriptor()
   {
   }
 

   SplitDescriptor(Element split)
   {
     init(split);
   }
 
   public List getResults()
   {
     return this.results;
   }
 
   public void writeXML(PrintWriter out, int indent) {
     XMLUtil.printIndent(out, indent++);
     out.println("<split id=\"" + getId() + "\">");
 
     for (int i = 0; i < this.results.size(); ++i) {
       ResultDescriptor result = (ResultDescriptor)this.results.get(i);
       result.writeXML(out, indent);
     }
 
     XMLUtil.printIndent(out, --indent);
     out.println("</split>");
   }
 
   private void init(Element split) {
     try {
       setId(Integer.parseInt(split.getAttribute("id")));
     } catch (Exception ex) {
       throw new IllegalArgumentException("Invalid split id value " + split.getAttribute("id"));
     }
 
     List uResults = XMLUtil.getChildElements(split, "unconditional-result");
 
     for (int i = 0; i < uResults.size(); ++i) {
       Element result = (Element)uResults.get(i);
       ResultDescriptor resultDescriptor = new ResultDescriptor(result);
       resultDescriptor.setParent(this);
       this.results.add(resultDescriptor);
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.SplitDescriptor
 * JD-Core Version:    0.5.3
 */