 package com.zving.framework.controls;
 
 import com.zving.framework.Ajax;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.utility.StringUtil;
 import java.lang.reflect.Method;
 
 public class DataListPage extends Ajax
 {
   public void doWork()
   {
     try
     {
       DataListAction dla = new DataListAction();
 
       dla.setTagBody(StringUtil.htmlDecode($V("_ZVING_TAGBODY")));
       dla.setPage("true".equalsIgnoreCase($V("_ZVING_PAGE")));
       String method = $V("_ZVING_METHOD");
       dla.setMethod(method);
 
       dla.setID($V("_ZVING_ID"));
       dla.setParams(this.Request);
       dla.setPageSize(Integer.parseInt($V("_ZVING_SIZE")));
       if (dla.isPage()) {
         dla.setPageIndex(0);
         if ((this.Request.get("_ZVING_PAGEINDEX") != null) && (!(this.Request.get("_ZVING_PAGEINDEX").equals("")))) {
           dla.setPageIndex(Integer.parseInt(this.Request.get("_ZVING_PAGEINDEX").toString()));
         }
         if (dla.getPageIndex() < 0) {
           dla.setPageIndex(0);
         }
         dla.setTotal(0);
         if ((this.Request.get("_ZVING_PAGETOTAL") != null) && (!(this.Request.get("_ZVING_PAGETOTAL").equals("")))) {
           dla.setTotal(Integer.parseInt(this.Request.get("_ZVING_PAGETOTAL").toString()));
           if (dla.getPageIndex() > Math.ceil(dla.getTotal() * 1.0D / dla.getPageSize())) {
             dla.setPageIndex(new Double(Math.floor(dla.getTotal() * 1.0D / dla.getPageSize())).intValue());
           }
         }
       }
       int index = method.lastIndexOf(46);
       String className = method.substring(0, index);
       method = method.substring(index + 1);
       Class c = Class.forName(className);
       Method m = c.getMethod(method, new Class[] { DataListAction.class });
       m.invoke(null, new Object[] { dla });
 
       $S("HTML", dla.getHtml());
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.DataListPage
 * JD-Core Version:    0.5.3
 */