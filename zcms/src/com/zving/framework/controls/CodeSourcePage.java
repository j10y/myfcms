 package com.zving.framework.controls;
 
 import com.zving.framework.Current;
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.utility.StringUtil;
 
 public class CodeSourcePage extends Page
 {
   public void getData()
   {
     String codeType = $V("CodeType");
     if (StringUtil.isEmpty($V("ConditionField"))) {
       this.Request.put("ConditionField", "1");
       this.Request.put("ConditionValue", "1");
     }
     DataTable dt = null;
     String method = $V("Method");
     if ((StringUtil.isEmpty(method)) && (codeType.startsWith("#"))) {
       method = codeType.substring(1);
     }
     if (StringUtil.isNotEmpty(method)) {
       String className = method.substring(0, method.lastIndexOf("."));
       String methodName = method.substring(method.lastIndexOf(".") + 1);
       try {
         Object o = Current.invokeMethod(method, new Object[] { this.Request });
         dt = (DataTable)o;
       } catch (Exception e) {
         throw new RuntimeException("确认类" + className + "的方法" + methodName + "返回值是DataTable类型!");
       }
     } else {
       CodeSource cs = SelectTag.getCodeSourceInstance();
       dt = cs.getCodeData(codeType, this.Request);
     }
     $S("DT", dt);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.CodeSourcePage
 * JD-Core Version:    0.5.3
 */