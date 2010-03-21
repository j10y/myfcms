 package com.zving.platform;
 
 import com.zving.cms.pub.PubFun;
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataRow;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZDBranchSchema;
 import java.util.ArrayList;
 import java.util.Date;
 import java.util.List;
 
 public class Branch extends Page
 {
   public static Mapx initDialog(Mapx params)
   {
     String branchInnerCode = params.getString("BranchInnerCode");
     if (StringUtil.isNotEmpty(branchInnerCode)) {
       ZDBranchSchema branch = new ZDBranchSchema();
       branch.setBranchInnerCode(branchInnerCode);
       branch.fill();
       params = branch.toMapx();
       params.put("ParentName", new QueryBuilder("select name from zdbranch where branchInnercode=?", branch.getParentInnerCode()).executeString());
     } else {
       params.put("ParentInnerCode", HtmlUtil.dataTableToOptions(getBranchTable(), params.getString("ParentInnerCode")));
     }
     return params;
   }
 
   public static DataTable getBranchTable() {
     DataTable dt = new QueryBuilder("select Name,branchInnerCode,TreeLevel from zdbranch where type='0' order by orderflag").executeDataTable();
     PubFun.indentDataTable(dt);
     return dt;
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     QueryBuilder qb = new QueryBuilder("select * from ZDBranch order by orderflag");
     DataTable dt = qb.executeDataTable();
     dga.bindData(dt);
   }
 
   public void add() {
     String parentInnerCode = $V("ParentInnerCode");
     Transaction trans = new Transaction();
     if (StringUtil.isEmpty(parentInnerCode)) {
       parentInnerCode = "0";
       ZDBranchSchema branch = new ZDBranchSchema();
       branch.setValue(this.Request);
       branch.setBranchInnerCode(NoUtil.getMaxNo("BranchInnerCode", 4));
       branch.setParentInnerCode(parentInnerCode);
       branch.setTreeLevel(1L);
       branch.setType("0");
       branch.setIsLeaf("Y");
 
       DataTable dt = new QueryBuilder("select * from zdbranch order by orderflag").executeDataTable();
       String orderflag = "";
       if ((dt != null) && (dt.getRowCount() > 0))
         orderflag = dt.getString(dt.getRowCount() - 1, "OrderFlag");
       else {
         orderflag = "0";
       }
       branch.setOrderFlag(orderflag + 1);
       branch.setAddTime(new Date());
       branch.setAddUser(User.getUserName());
       trans.add(branch, 1);
 
       trans.add(new QueryBuilder("update zdbranch set orderflag = orderflag+1 where orderflag>?", orderflag));
       if (trans.commit())
         this.Response.setLogInfo(1, "新建成功");
       else
         this.Response.setLogInfo(0, "新建失败");
     }
     else {
       ZDBranchSchema pBranch = new ZDBranchSchema();
       pBranch.setBranchInnerCode(parentInnerCode);
       pBranch.fill();
       long pTreeLevel = pBranch.getTreeLevel();
 
       ZDBranchSchema branch = new ZDBranchSchema();
       branch.setValue(this.Request);
       branch.setBranchInnerCode(NoUtil.getMaxNo("BranchInnerCode", pBranch.getBranchInnerCode(), 4));
       branch.setParentInnerCode(pBranch.getBranchInnerCode());
       branch.setTreeLevel(pTreeLevel + 1L);
       branch.setType("0");
       branch.setIsLeaf("Y");
       branch.setAddTime(new Date());
       branch.setAddUser(User.getUserName());
 
       DataTable dt = new QueryBuilder("select * from zdbranch where BranchInnerCode like '" + pBranch.getBranchInnerCode() + "%' order by orderflag").executeDataTable();
       long orderflag = Long.parseLong(dt.getString(dt.getRowCount() - 1, "OrderFlag"));
       branch.setOrderFlag(orderflag + 1L);
 
       trans.add(new QueryBuilder("update zdbranch set orderflag = orderflag+1 where orderflag>?", orderflag));
       trans.add(branch, 1);
 
       trans.add(new QueryBuilder("update zdbranch set IsLeaf='N' where branchInnerCode =?", pBranch.getBranchInnerCode()));
       if (trans.commit())
         this.Response.setLogInfo(1, "新建成功");
       else
         this.Response.setLogInfo(0, "新建失败");
     }
   }
 
   public void save()
   {
     String branchInnerCode = $V("BranchInnerCode");
     Transaction trans = new Transaction();
     if (StringUtil.isEmpty(branchInnerCode)) {
       this.Response.setLogInfo(0, "传入数据错误！");
       return;
     }
     ZDBranchSchema branch = new ZDBranchSchema();
     branch.setBranchInnerCode(branchInnerCode);
     if (!(branch.fill())) {
       this.Response.setLogInfo(0, branchInnerCode + "机构不存在！");
       return;
     }
 
     branch.setValue(this.Request);
     branch.setModifyUser(User.getUserName());
     branch.setModifyTime(new Date());
     trans.add(branch, 2);
     if (trans.commit())
       this.Response.setLogInfo(1, "保存成功!");
     else
       this.Response.setLogInfo(0, "保存失败!");
   }
 
   public void del()
   {
     String IDs = $V("IDs");
     String[] ids = IDs.split(",");
     Transaction trans = new Transaction();
     ZDBranchSchema branch = new ZDBranchSchema();
     for (int i = 0; i < ids.length; ++i) {
       branch.setBranchInnerCode(ids[i]);
       if (branch.fill()) {
         if ("0".equals(branch.getParentInnerCode())) {
           this.Response.setLogInfo(0, "删除失败：不能删除顶级机构");
           UserLog.log("System", "DelBranch", "删除机构:" + branch.getName() + "失败", this.Request.getClientIP());
           return;
         }
         QueryBuilder qb = new QueryBuilder("where BranchInnerCode like ?", branch.getBranchInnerCode() + "%");
         trans.add(branch.query(qb), 5);
       }
     }
 
     if (trans.commit()) {
       UserLog.log("System", "DelBranch", "删除机构成功", this.Request.getClientIP());
       this.Response.setLogInfo(1, "删除成功");
     } else {
       UserLog.log("System", "DelBranch", "删除机构失败", this.Request.getClientIP());
       this.Response.setLogInfo(0, "删除失败");
     }
   }
 
   public void sortBranch() {
     String orderBranch = $V("OrderBranch");
     String nextBranch = $V("NextBranch");
     String ordertype = $V("OrderType");
     if ((StringUtil.isEmpty(orderBranch)) || (StringUtil.isEmpty(nextBranch)) || (StringUtil.isEmpty(ordertype))) {
       this.Response.setLogInfo(0, "传递数据有误！");
       return;
     }
 
     Transaction trans = new Transaction();
     DataTable DT = new QueryBuilder("select * from zdbranch order by orderflag").executeDataTable();
 
     List branchList = new ArrayList();
 
     DataTable orderDT = new QueryBuilder("select * from zdbranch where branchinnercode like '" + orderBranch + "%' order by orderflag").executeDataTable();
 
     DataTable nextDT = new QueryBuilder("select * from zdbranch where branchinnercode like '" + nextBranch + "%' order by orderflag").executeDataTable();
     int m;
     if ("before".equalsIgnoreCase(ordertype)) {
       for (i = 0; i < DT.getRowCount(); ++i) {
         if (DT.getString(i, "BranchInnerCode").equals(nextBranch)) {
           m = 0; break label213:
           do { branchList.add(orderDT.getDataRow(m));
 
             ++m; label213: if (orderDT == null) break label262;  }
           while (m < orderDT.getRowCount());
         }
         else if (DT.getString(i, "BranchInnerCode").equals(orderBranch))
         {
           i = i - 1 + orderDT.getRowCount();
           continue;
         }
         label262: branchList.add(DT.getDataRow(i));
       }
 
     }
     else if ("after".equalsIgnoreCase(ordertype)) {
       for (i = 0; (DT != null) && (i < DT.getRowCount()); ++i) {
         if (DT.getString(i, "BranchInnerCode").equals(orderBranch))
         {
           i = i - 1 + orderDT.getRowCount();
         }
         else if (DT.getString(i, "BranchInnerCode").equals(nextBranch))
         {
           for (m = 0; (nextDT != null) && (m < nextDT.getRowCount()); ++m) {
             branchList.add(nextDT.getDataRow(m));
           }
 
           for (int j = 0; (orderDT != null) && (j < orderDT.getRowCount()); ++j) {
             branchList.add(orderDT.getDataRow(j));
           }
 
           i = i - 1 + nextDT.getRowCount();
         } else {
           branchList.add(DT.getDataRow(i));
         }
       }
     }
 
     for (int i = 0; (branchList != null) && (i < branchList.size()); ++i) {
       DataRow dr = (DataRow)branchList.get(i);
       trans.add(new QueryBuilder("update zdbranch set orderflag = ? where BranchInnerCode = ?", i, dr.getString("BranchInnerCode")));
     }
     if (trans.commit())
       this.Response.setLogInfo(1, "排序成功！");
     else
       this.Response.setLogInfo(0, "排序失败！");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.Branch
 * JD-Core Version:    0.5.3
 */