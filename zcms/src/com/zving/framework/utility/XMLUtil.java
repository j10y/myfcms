 package com.zving.framework.utility;
 
 import java.io.IOException;
 import java.io.StringReader;
 import java.io.StringWriter;
 import javax.xml.transform.Transformer;
 import javax.xml.transform.TransformerConfigurationException;
 import javax.xml.transform.TransformerException;
 import javax.xml.transform.TransformerFactory;
 import javax.xml.transform.TransformerFactoryConfigurationError;
 import javax.xml.transform.dom.DOMSource;
 import javax.xml.transform.stream.StreamResult;
 import org.apache.xpath.XPathAPI;
 import org.cyberneko.html.parsers.DOMParser;
 import org.dom4j.io.SAXReader;
 import org.w3c.dom.Node;
 import org.w3c.dom.NodeList;
 import org.xml.sax.InputSource;
 import org.xml.sax.SAXException;
 
 public class XMLUtil
 {
   public static String toString(Node node)
   {
     return toString(node, "UTF-8");
   }
 
   public static String toString(Node node, String encoding)
   {
     try
     {
       Transformer serializer = TransformerFactory.newInstance().newTransformer();
       if (node.getNodeType() == 9)
         serializer.setOutputProperty("omit-xml-declaration", "no");
       else {
         serializer.setOutputProperty("omit-xml-declaration", "yes");
       }
       serializer.setOutputProperty("encoding", encoding);
       serializer.setOutputProperty("indent", "yes");
       serializer.setOutputProperty("cdata-section-elements", "yes");
       serializer.setOutputProperty("method", "xml");
       if (isTextNode(node)) {
         return node.getNodeValue();
       }
       StringWriter w = new StringWriter();
       serializer.transform(new DOMSource(node), new StreamResult(w));
       return w.toString();
     }
     catch (TransformerConfigurationException e) {
       e.printStackTrace();
     } catch (TransformerFactoryConfigurationError e) {
       e.printStackTrace();
     } catch (TransformerException e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static String toHTML(Node node)
   {
     try
     {
       Transformer serializer = TransformerFactory.newInstance().newTransformer();
       if (isTextNode(node)) {
         return node.getNodeValue();
       }
       StringWriter w = new StringWriter();
       serializer.transform(new DOMSource(node), new StreamResult(w));
       return w.toString();
     }
     catch (TransformerConfigurationException e) {
       e.printStackTrace();
     } catch (TransformerFactoryConfigurationError e) {
       e.printStackTrace();
     } catch (TransformerException e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static String htmlToXML(String html)
   {
     return htmlToXML(html, "UTF-8");
   }
 
   public static String htmlToXML(String html, String encoding)
   {
     DOMParser parser = new DOMParser();
     try {
       parser.parse(new InputSource(new StringReader(html)));
       org.w3c.dom.Document doc = parser.getDocument();
       NodeList list = XPathAPI.selectNodeList(doc, "//SCRIPT|//STYLE");
       for (int i = 0; i < list.getLength(); ++i) {
         Node script = list.item(i);
         if (script.hasChildNodes()) {
           script.replaceChild(doc.createCDATASection(script.getFirstChild().getNodeValue()), script
             .getFirstChild());
         }
       }
       return toString(doc);
     } catch (SAXException e) {
       e.printStackTrace();
     } catch (IOException e) {
       e.printStackTrace();
     } catch (TransformerFactoryConfigurationError e) {
       e.printStackTrace();
     } catch (TransformerException e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static boolean isTextNode(Node node)
   {
     if (node == null)
       return false;
     short nodeType = node.getNodeType();
     return ((nodeType == 4) || (nodeType == 3));
   }
 
   public static org.dom4j.Document xercesToDom4j(org.w3c.dom.Document doc)
   {
     return toDom4jDocument(toString(doc));
   }
 
   public static org.dom4j.Document toDom4jDocument(String content)
   {
     try
     {
       SAXReader reader = new SAXReader(false);
       return reader.read(new StringReader(content));
     } catch (Exception e) {
       e.printStackTrace();
     }
     return null;
   }
 
   public static org.w3c.dom.Document htmlToXercesDocument(String content)
   {
     try
     {
       DOMParser parser = new DOMParser();
       parser.parse(new InputSource(new StringReader(content)));
       return parser.getDocument();
     } catch (Exception e) {
       e.printStackTrace();
     }
     return null;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.utility.XMLUtil
 * JD-Core Version:    0.5.3
 */