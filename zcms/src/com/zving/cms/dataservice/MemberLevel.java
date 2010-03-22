 package com.zving.cms.dataservice;
 
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZDMemberLevelSchema;
 import com.zving.schema.ZDMemberLevelSet;
 import java.util.Date;
 
 public class MemberLevel extends Page
 {
   public static void dg1DataBind(DataGridAction dga)
   {
     String sql1 = "select * from ZDMemberLevel order by TreeLevel asc";
     if (dga.getTotal() == 0) {
       String sql2 = "select count(*) from ZDMemberLevel";
       dga.setTotal(new QueryBuilder(sql2));
     }
     DataTable dt = new QueryBuilder(sql1).executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     dga.bindData(dt);
   }
 
   public void add() {
     ZDMemberLevelSchema ZDmemberlevel = new ZDMemberLevelSchema();
     ZDmemberlevel.setValue(this.Request);
     ZDmemberlevel.setID(NoUtil.getMaxID("MemberLevelID"));
     ZDmemberlevel.setDiscount("1.0");
     ZDmemberlevel.setIsDefault("Y");
     ZDmemberlevel.setIsValidate("Y");
     ZDmemberlevel.setType("用户");
     ZDmemberlevel.setAddUser(User.getUserName());
     ZDmemberlevel.setAddTime(new Date());
     if (ZDmemberlevel.insert()) {
       this.Response.setStatus(1);
       this.Response.setMessage("新增会员项成功！");
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("发生错误!");
     }
   }
 
   public void del()
   {
     String ids = $V("IDs");
     if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     ids = ids.replaceAll(",", "','");
     Transaction trans = new Transaction();
     ZDMemberLevelSchema ZDmemberlevel = new ZDMemberLevelSchema();
     ZDMemberLevelSet set = ZDmemberlevel.query(new QueryBuilder("where ID in ('" + ids + "')"));
     trans.add(set, 3);
     if (trans.commit()) {
       this.Response.setStatus(1);
       this.Response.setMessage("删除成功！");
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 
   public void save() {
     DataTable dt = (DataTable)this.Request.get("DT");
     Transaction trans = new Transaction();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       ZDMemberLevelSchema MemberLevel = new ZDMemberLevelSchema();
       MemberLevel.setValue(dt.getDataRow(i));
       MemberLevel.fill();
       MemberLevel.setValue(dt.getDataRow(i));
       trans.add(MemberLevel, 2);
     }
     if (trans.commit())
       this.Response.setLogInfo(1, "保存成功!");
     else
       this.Response.setLogInfo(0, "保存失败!");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.dataservice.MemberLevel
 * JD-Core Version:    0.5.3
 */