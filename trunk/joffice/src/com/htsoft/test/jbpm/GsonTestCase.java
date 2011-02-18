 package com.htsoft.test.jbpm;
 
 import com.htsoft.core.util.XmlUtil;
 import java.io.PrintStream;
 import org.dom4j.Document;
 import org.dom4j.Element;
 
 public class GsonTestCase
 {
   public static void main(String[] args)
   {
     test();
   }
 
   public static void test()
   {
     String path = "L:/devtools/workspace/joffice/test/com/htsoft/test/jbpm/jbpmdef.xml";
 
     String defXml = XmlUtil.load(path).getRootElement().asXML();
 
     System.out.println("xml:" + defXml);
   }
 }


 
 
 