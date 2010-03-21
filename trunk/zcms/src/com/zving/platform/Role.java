 package com.zving.platform;
 
 import com.zving.framework.Page;
 import com.zving.framework.User;
 import com.zving.framework.controls.TreeAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 
 public class Role extends Page
 {
   public static final String EVERYONE = "everyone";
 
   public static void treeDataBind(TreeAction ta)
   {
     DataTable dt = new QueryBuilder("select RoleCode,'' as ParentID,'1' as TreeLevel,RoleName from ZDRole Where BranchInnerCode like ?", User.getBranchInnerCode() + "%").executeDataTable();
     ta.setRootText("角色列表");
     ta.setIdentifierColumnName("RoleCode");
     ta.setBranchIcon("Icons/icon025a1.gif");
     ta.setLeafIcon("Icons/icon025a1.gif");
     ta.bindData(dt);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.Role
 * JD-Core Version:    0.5.3
 */