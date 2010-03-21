 package com.zving.workflow.util;
 
 import com.zving.framework.User;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.StringUtil;
 import com.zving.workflow.Condition;
 import java.io.PrintStream;
 import java.util.Map;
 
 public class ConditionUserBelongToGroup
   implements Condition
 {
   public boolean passesCondition(Map transientVars, Map args)
   {
     String group = (String)args.get("group");
     if ((group == null) || ("".equals(group))) {
       System.out.println("condition节点没有设置group的值");
       return false;
     }
     if (!(StringUtil.checkID(group))) {
       System.out.println("传入group时发生错误!");
       return false;
     }
     DataTable dt = new QueryBuilder("select roleCode  from ZDUserRole where ZDUserRole.UserName=? and RoleCode in ('" + group.replaceAll(",", "','") + "')", User.getUserName()).executeDataTable();
 
     return ((dt != null) && (dt.getRowCount() > 0));
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.workflow.util.ConditionUserBelongToGroup
 * JD-Core Version:    0.5.3
 */