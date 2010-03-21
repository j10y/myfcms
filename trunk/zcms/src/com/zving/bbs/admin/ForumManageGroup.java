 package com.zving.bbs.admin;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataListAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCForumGroupSchema;
 import java.util.Date;
 
 public class ForumManageGroup extends Page
 {
   public static void getListManageGroup(DataListAction dla)
   {
     String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
     String sql = "select f1.Name, f2.SystemName, f1.Type, a.* from ZCForumGroup f1, ZCForumGroup f2, ZCAdminGroup a where f1." + 
       sqlSiteID + " and f2." + sqlSiteID + " and a." + sqlSiteID + 
       " and f1.ID=a.GroupID and f1.RadminID=f2.ID";
     DataTable dt = new QueryBuilder(sql).executeDataTable();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       if (dt.get(i, "Type").equals("2"))
         dt.set(i, "Type", "内置");
       else {
         dt.set(i, "Type", "自定义");
       }
     }
     dla.bindData(dt); }
 
   public void addManageGroup() {
     Transaction trans = new Transaction();
     ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
     userGroup.setID(NoUtil.getMaxID("ForumGroupID"));
     userGroup.setType("2");
     userGroup.setValue(this.Request);
     userGroup.setAddUser(User.getUserName());
     userGroup.setAddTime(new Date());
     trans.add(userGroup, 1);
     if (trans.commit())
       this.Response.setLogInfo(1, "添加成功");
     else
       this.Response.setLogInfo(0, "操作失败");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.admin.ForumManageGroup
 * JD-Core Version:    0.5.3
 */