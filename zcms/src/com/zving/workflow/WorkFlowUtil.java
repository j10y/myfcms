 package com.zving.workflow;
 
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import java.io.ByteArrayInputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.sql.SQLException;
 import java.util.HashMap;
 import java.util.Map;
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.parsers.ParserConfigurationException;
 import org.w3c.dom.Document;
 import org.w3c.dom.Element;
 import org.w3c.dom.NodeList;
 import org.xml.sax.SAXException;
 
 public class WorkFlowUtil
 {
   private static Map WFConfigs = new HashMap();
 
   public static WorkflowDescriptor getWorkflow(String ID, boolean validate) {
     WFConfig c = (WFConfig)WFConfigs.get(ID);
 
     if (c == null) {
       c = new WFConfig(ID);
       WFConfigs.put(ID, c);
     }
 
     if (c.descriptor != null)
     {
       try
       {
         c.descriptor = load(c.ID, validate);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
     else {
       try {
         c.descriptor = load(c.ID, validate);
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
 
     return c.descriptor;
   }
 
   public static void clear() {
     WFConfigs.clear();
   }
 
   private static WorkflowDescriptor load(String wfName, boolean validate) throws IOException, SAXException {
     byte[] wf = (byte[])null;
     try
     {
       wf = read(wfName);
     } catch (SQLException e) {
       e.printStackTrace();
     }
 
     ByteArrayInputStream is = new ByteArrayInputStream(wf);
 
     return load(is, validate);
   }
 
   private static WorkflowDescriptor load(InputStream is, boolean validate) throws SAXException, IOException {
     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
     dbf.setNamespaceAware(true);
 
     dbf.setValidating(validate);
     DocumentBuilder db;
     try
     {
       db = dbf.newDocumentBuilder();
     }
     catch (ParserConfigurationException e) {
       throw new SAXException("Error creating document builder", e);
     }
 
     Document doc = db.parse(is);
 
     Element root = (Element)doc.getElementsByTagName("workflow").item(0);
 
     WorkflowDescriptor descriptor = DescriptorFactory.getFactory().createWorkflowDescriptor(root);
 
     return descriptor;
   }
 
   private static byte[] read(String ID) throws SQLException, IOException {
     DataTable dt = new QueryBuilder("SELECT DEFINITION FROM ZWWorkFlowDef WHERE ID = ?", ID).executeDataTable();
     if (dt.getRowCount() > 0) {
       return dt.getString(0, 0).getBytes("UTF-8");
     }
     return null;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.WorkFlowUtil
 * JD-Core Version:    0.5.3
 */