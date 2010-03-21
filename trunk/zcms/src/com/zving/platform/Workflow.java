 package com.zving.platform;
 
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataRow;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZWWorkflowDefSchema;
 import com.zving.schema.ZWWorkflowDefSet;
 import com.zving.workflow.WorkFlowUtil;
 import java.util.Date;
 
 public class Workflow extends Page
 {
   public static Mapx initDialog(Mapx params)
   {
     String ID = params.getString("ID");
     if (StringUtil.isNotEmpty(ID)) {
       ZWWorkflowDefSchema workflow = new ZWWorkflowDefSchema();
       workflow.setID(ID);
       workflow.fill();
       return workflow.toMapx();
     }
     return params;
   }
 
   public static void dg1DataBind(DataGridAction dga)
   {
     String sql = " select * from ZWWorkflowdef ";
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(*) from ZWWorkflowdef"));
     }
     dga.bindData(new QueryBuilder(sql));
   }
 
   public void add() {
     ZWWorkflowDefSchema workflow = new ZWWorkflowDefSchema();
     workflow.setValue(this.Request);
     workflow.setID(NoUtil.getMaxID("WorkflowDefID"));
     workflow.setAddTime(new Date());
     workflow.setAddUser(User.getUserName());
     if (workflow.insert())
       this.Response.setLogInfo(1, "新建工作流成功！");
     else
       this.Response.setLogInfo(0, "新建工作流失败！");
   }
 
   public void save()
   {
     DataTable dt = (DataTable)this.Request.get("DT");
     Transaction trans = new Transaction();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       DataRow dr = dt.getDataRow(i);
       QueryBuilder qb = new QueryBuilder("update ZWWorkflowDef set name = ?,memo=? where ID=?");
       qb.add(dr.getString("Name"));
       qb.add(dr.getString("Memo"));
       qb.add(dr.getString("ID"));
       trans.add(qb);
     }
     if (trans.commit())
       this.Response.setLogInfo(1, "修改成功!");
     else
       this.Response.setLogInfo(0, "修改失败!");
   }
 
   public void edit()
   {
     ZWWorkflowDefSchema workflow = new ZWWorkflowDefSchema();
     workflow.setValue(this.Request);
     workflow.fill();
     workflow.setValue(this.Request);
     workflow.setModifyTime(new Date());
     workflow.setModifyUser(User.getUserName());
     if (workflow.update()) {
       WorkFlowUtil.clear();
       this.Response.setLogInfo(1, "修改工作流成功！");
     } else {
       this.Response.setLogInfo(0, "修改工作流失败！");
     }
   }
 
   public void del() {
     String IDs = $V("IDs");
     if (!(StringUtil.checkID(IDs))) {
       this.Response.setLogInfo(0, "传入工作流发生错误!");
       return;
     }
     ZWWorkflowDefSchema wf = new ZWWorkflowDefSchema();
     ZWWorkflowDefSet set = wf.query(new QueryBuilder("where ID in (" + IDs + ")"));
 
     if (set.deleteAndBackup())
       this.Response.setLogInfo(1, "删除成功！");
     else
       this.Response.setLogInfo(0, "删除失败！");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.Workflow
 * JD-Core Version:    0.5.3
 */