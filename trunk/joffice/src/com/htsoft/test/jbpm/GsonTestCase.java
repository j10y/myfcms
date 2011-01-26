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

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.test.jbpm.GsonTestCase
 * JD-Core Version:    0.5.4
 */