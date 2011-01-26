 package com.htsoft.oa.action.flow;
 
 import com.htsoft.core.jbpm.pv.ParamField;
 import com.htsoft.core.util.AppUtil;
 import com.htsoft.core.util.XmlUtil;
 import java.io.FileInputStream;
 import java.io.InputStream;
 import java.util.LinkedHashMap;
 import java.util.List;
 import java.util.Map;
 import org.apache.commons.logging.Log;
 import org.apache.commons.logging.LogFactory;
 import org.dom4j.Attribute;
 import org.dom4j.Document;
 import org.dom4j.Element;
 
 public class ProcessActivityAssistant
 {
   private static final Log logger = LogFactory.getLog(ProcessActivityAssistant.class);
 
   public static Map<String, ParamField> constructFieldMap(String processName, String activityName)
   {
     String fieldsXmlLoaction = getFieldAbsPath(processName, activityName);
 
     InputStream is = null;
     Map map = new LinkedHashMap();
     try {
       is = new FileInputStream(fieldsXmlLoaction);
     } catch (Exception ex) {
       logger.warn("error when read the file from " + activityName + "-fields.xml, the reason is not upload the ");
     }
 
     if (is == null) {
       try {
         is = new FileInputStream(getCommonFieldsAbsPath(activityName));
       } catch (Exception ex) {
         logger.warn("error when read the file from 通用、表单-fields.xml, the reason is not upload the ");
       }
     }
 
     Document doc = XmlUtil.load(is);
     Element fields = doc.getRootElement();
     List els = fields.elements();
     for (Element el : els) {
       String name = el.attribute("name").getValue();
 
       Attribute attLabel = el.attribute("label");
       Attribute attType = el.attribute("type");
       Attribute attLength = el.attribute("length");
       Attribute attIsShowed = el.attribute("isShowed");
 
       String label = (attLabel == null) ? name : attLabel.getValue();
       String type = (attType == null) ? "varchar" : attType.getValue();
       Integer length = Integer.valueOf((attLength == null) ? 0 : new Integer(attLength.getValue()).intValue());
       Short isShowed = Short.valueOf(((attIsShowed == null) || ("true".equals(attIsShowed.getValue()))) ? 1 : 0);
 
       ParamField pf = new ParamField(name, type, label, length, isShowed);
       map.put(name, pf);
     }
 
     return map;
   }
 
   public static String getStartFormPath(String processName) {
     return "/" + processName + "/开始.vm";
   }
 
   public static String getFormPath(String processName, String activityName)
   {
     return "/" + processName + "/" + activityName + ".vm";
   }
 
   public static String getFieldAbsPath(String processName, String activityName)
   {
     return AppUtil.getFlowFormAbsolutePath() + processName + "/" + activityName + "-fields.xml";
   }
 
   public static String getFieldStartAbsPath(String processName)
   {
     return AppUtil.getFlowFormAbsolutePath() + processName + "/开始-fields.xml";
   }
 
   public static String getCommonFormPath(String activityName)
   {
     if ("开始".equals(activityName)) {
       return "/通用/开始.vm";
     }
     return "/通用/表单.vm";
   }
 
   public static String getCommonFieldsAbsPath(String activityName)
   {
     String absPath = AppUtil.getFlowFormAbsolutePath();
 
     if ("开始".equals(activityName)) {
       return absPath + "通用/开始-fields.xml";
     }
     return absPath + "通用/表单-fields.xml";
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.action.flow.ProcessActivityAssistant
 * JD-Core Version:    0.5.4
 */