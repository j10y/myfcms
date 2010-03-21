 package com.zving.framework.controls;
 
 import com.zving.framework.Page;
 import com.zving.framework.utility.StringUtil;
 import java.lang.reflect.Method;
 
 public class TreePage extends Page
 {
   public void doWork()
   {
     try
     {
       TreeAction ta = new TreeAction();
 
       ta.setTagBody(StringUtil.htmlDecode($V("_ZVING_TAGBODY")));
       String method = $V("_ZVING_METHOD");
       ta.setMethod(method);
 
       if ("true".equals($V("_ZVING_TREE_LAZY"))) {
         if (!("false".equals($V("_ZVING_TREE_EXPAND")))) {
           ta.setExpand(true);
         }
         ta.setLazy(true);
       }
 
       if (($V("ParentLevel") != null) && (!("".equals($V("ParentLevel"))))) {
         ta.setParentLevel(Integer.parseInt($V("ParentLevel")));
         ta.setLazyLoad(true);
       }
 
       ta.setID($V("_ZVING_ID"));
       ta.setParams(this.Request);
 
       String levelStr = $V("_ZVING_TREE_LEVEL");
       String style = $V("_ZVING_TREE_STYLE");
 
       int level = Integer.parseInt(levelStr);
       if (level <= 0) {
         level = 999;
       }
       ta.setLevel(level);
       ta.setStyle(style);
 
       HtmlP p = new HtmlP();
       p.parseHtml(ta.getTagBody());
       ta.setTemplate(p);
 
       int index = method.lastIndexOf(46);
       String className = method.substring(0, index);
       method = method.substring(index + 1);
       Class c = Class.forName(className);
       Method m = c.getMethod(method, new Class[] { TreeAction.class });
       m.invoke(null, new Object[] { ta });
 
       $S("HTML", ta.getHtml());
     } catch (Exception e) {
       e.printStackTrace();
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.framework.controls.TreePage
 * JD-Core Version:    0.5.3
 */