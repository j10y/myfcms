 package com.zving.workflow;
 
 import java.io.PrintWriter;
 import java.util.ArrayList;
 import java.util.List;
 import org.w3c.dom.Element;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 
 public class XMLUtil
 {
   public static Element getChildElement(Element parent, String childName)
   {
     NodeList children = parent.getChildNodes();
     int size = children.getLength();
 
     for (int i = 0; i < size; ++i) {
       Node node = children.item(i);
 
       if (node.getNodeType() == 1) {
         Element element = (Element)node;
 
         if (childName.equals(element.getNodeName())) {
           return element;
         }
       }
     }
 
     return null;
   }
 
   public static List getChildElements(Element parent, String childName) {
     NodeList children = parent.getChildNodes();
     List list = new ArrayList();
     int size = children.getLength();
 
     for (int i = 0; i < size; ++i) {
       Node node = children.item(i);
 
       if (node.getNodeType() == 1) {
         Element element = (Element)node;
 
         if (childName.equals(element.getNodeName())) {
           list.add(element);
         }
       }
     }
 
     return list;
   }
 
   public static String getChildText(Element parent, String childName) {
     Element child = getChildElement(parent, childName);
 
     if (child == null) {
       return null;
     }
 
     return getText(child);
   }
 
   public static String getText(Element node) {
     StringBuffer sb = new StringBuffer();
     NodeList list = node.getChildNodes();
 
     for (int i = 0; i < list.getLength(); ++i) {
       Node child = list.item(i);
 
       switch (child.getNodeType())
       {
       case 3:
       case 4:
         sb.append(child.getNodeValue());
       }
     }
 
     return sb.toString();
   }
 
   public static String encode(Object string) {
     if (string == null) {
       return "";
     }
 
     char[] chars = string.toString().toCharArray();
     StringBuffer out = new StringBuffer();
 
     for (int i = 0; i < chars.length; ++i) {
       switch (chars[i])
       {
       case '&':
         out.append("&amp;");
 
         break;
       case '<':
         out.append("&lt;");
 
         break;
       case '>':
         out.append("&gt;");
 
         break;
       case '"':
         out.append("&quot;");
 
         break;
       default:
         out.append(chars[i]);
       }
     }
 
     return out.toString();
   }
 
   public static void printIndent(PrintWriter out, int indent) {
     for (int i = 0; i < indent; ++i)
       out.print("  ");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.XMLUtil
 * JD-Core Version:    0.5.3
 */