 package com.zving.cms.document;
 
 import com.zving.framework.Page;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataAccess;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.schema.ZWCurrentStepSchema;
 import com.zving.schema.ZWCurrentStepSet;
 import com.zving.workflow.ActionDescriptor;
 import com.zving.workflow.Workflow;
 import java.sql.SQLException;
 
 public class WorkList extends Page
 {
   public static void dg1DataBind(DataGridAction dga)
   {
     StringBuffer condition = new StringBuffer();
     QueryBuilder qb = new QueryBuilder();
 
     String keyword = dga.getParam("Keyword");
     if (StringUtil.isNotEmpty(keyword)) {
       condition.append(" and a.title like ? ");
       qb.add("%" + keyword.trim() + "%");
     }
 
     String sql = "select (select name from zccatalog c where c.id=a.catalogid) as CatalogIDName,a.id,a.catalogid,a.title,a.workflowid,a.author,a.addtime,a.publishDate,a.status ,'' as StatusName,b.status as WorkFlowStatus,b.owner,'' as Actions from zcarticle a,zwcurrentstep b where a.siteID = " + 
       Application.getCurrentSiteID() + " and a.workflowid = b.entryid " + condition;
     String countSql = "select count(*) from zcarticle a , zwcurrentstep b where a.workflowid = b.entryid " + condition;
     String listType = dga.getParam("Type");
     if (StringUtil.isEmpty(listType)) {
       listType = "ALL";
     }
     if ("TOME".equals(listType)) {
       sql = sql + " and b.owner='" + User.getUserName() + "' ";
       countSql = countSql + " and b.owner='" + User.getUserName() + "' "; } else {
       "ALL".equals(listType);
     }
 
     qb.setSQL(countSql);
     dga.setTotal(qb);
 
     qb.setSQL(sql + " order by a.ID desc");
     DataTable dt = qb.executeDataTable();
     if ((dt == null) || (dt.getRowCount() < 1)) {
       dga.bindData(dt);
       return;
     }
     DataAccess da = new DataAccess();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       String workflowName = new QueryBuilder("select workflow from zccatalog where id =?", dt.getString(i, "catalogid")).executeString();
       if (StringUtil.isNotEmpty(workflowName))
       {
         Workflow workFlow = new Workflow(workflowName, da);
         long workFlowID = Long.parseLong(dt.getString(i, "workflowid"));
         ZWCurrentStepSet currentSteps = workFlow.findCurrentSteps(workFlowID);
         ZWCurrentStepSchema step = workFlow.getCurrentStep(10, currentSteps);
         if (step != null) {
           workFlow.populateTransientMap();
           ActionDescriptor[] actions = workFlow.getAvailableActions(step.getStepID());
           StringBuffer sb = new StringBuffer();
           for (int j = 0; j < actions.length; ++j) {
             if (actions[j].isQuickLink()) {
               sb.append("<a href=\"#;\" onclick=\"doAction('" + workFlowID + "','" + actions[j].getId() + "'," + dt.getString(i, "id") + ")\">" + actions[j].getName() + "</a>  ");
             }
           }
           dt.set(i, "Actions", sb.toString());
         }
       }
       dt.set(i, "StatusName", Article.STATUS_MAP.getString(dt.getString(i, "Status")));
     }
     try {
       da.close();
     } catch (SQLException e) {
       e.printStackTrace();
     }
 
     dga.bindData(dt);
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.document.WorkList
 * JD-Core Version:    0.5.3
 */