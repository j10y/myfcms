 package com.zving.platform;
 
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.cache.CacheManager;
 import com.zving.framework.cache.CacheProvider;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.OrderUtil;
 import com.zving.schema.ZDCodeSchema;
 import com.zving.schema.ZDCodeSet;
 import java.util.Date;
 
 public class Code extends Page
 {
   public static void dg1BindCode(DataGridAction dga)
   {
     String SearchCodeType = dga.getParam("SearchCodeType");
     String condition = " and (prop4!='S' or prop4 is null)";
 
     if (StringUtil.isNotEmpty(SearchCodeType)) {
       condition = condition + " and (CodeType like '%" + SearchCodeType + "%' or CodeName like '%" + SearchCodeType + "%')";
     }
     String sql = "select CodeType,ParentCode,CodeValue,CodeName,CodeOrder,Memo,CodeType as CodeType_key,ParentCode as ParentCode_key,CodeValue as CodeValue_key from ZDCode where ParentCode ='System' " + 
       condition + " order by CodeType,ParentCode";
     dga.setTotal(new QueryBuilder("select count(1) from ZDCode where ParentCode ='System'" + condition));
     dga.bindData(new QueryBuilder(sql));
   }
 
   public static void dg1BindCodeList(DataGridAction dga)
   {
     String where = " where ParentCode =?";
     String sql = 
       "select CodeType,ParentCode,CodeValue,CodeName,CodeOrder ,Memo,CodeType as CodeType_key,ParentCode as ParentCode_key,CodeValue as CodeValue_key from ZDCode " + 
       where;
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(1) from ZDCode " + where, dga.getParam("CodeType")));
     }
     sql = sql + " order by CodeOrder,CodeType,ParentCode";
     dga.bindData(new QueryBuilder(sql, dga.getParam("CodeType")));
   }
 
   public void dg1Edit() {
     DataTable dt = (DataTable)this.Request.get("DT");
     Transaction trans = new Transaction();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       QueryBuilder qb = 
         new QueryBuilder("update ZDCode set Codetype=?,ParentCode =?,CodeName=?,CodeValue=?,Memo=? where CodeType=? and ParentCode=? and CodeValue=?");
       qb.add(dt.getString(i, "CodeType"));
       qb.add(dt.getString(i, "ParentCode"));
       qb.add(dt.getString(i, "CodeName"));
       qb.add(dt.getString(i, "CodeValue"));
       qb.add(dt.getString(i, "Memo"));
       qb.add(dt.getString(i, "CodeType_Key"));
       qb.add(dt.getString(i, "ParentCode_Key"));
       qb.add(dt.getString(i, "CodeValue_Key"));
       trans.add(qb);
 
       if ((!("System".equals(dt.getString(i, "ParentCode")))) || 
         (dt.getString(i, "CodeType").equals(dt.getString(i, "CodeType_Key")))) continue;
       qb = new QueryBuilder("update ZDCode set Codetype=?,ParentCode =? where CodeType=? and ParentCode=?");
       qb.add(dt.getString(i, "CodeType"));
       qb.add(dt.getString(i, "CodeType"));
       qb.add(dt.getString(i, "CodeType_Key"));
       qb.add(dt.getString(i, "CodeType_Key"));
       trans.add(qb);
     }
 
     if (trans.commit()) {
       CacheManager.getCache("Code").init();
       this.Response.setLogInfo(1, "修改成功!");
     } else {
       this.Response.setLogInfo(0, "修改失败!");
     }
   }
 
   public static Mapx init(Mapx params) {
     return params;
   }
 
   public static Mapx initList(Mapx params) {
     String codeType = params.getString("CodeType");
     ZDCodeSchema code = new ZDCodeSchema();
     code.setCodeType(codeType);
     code.setParentCode("System");
     code.setCodeValue("System");
     code.fill();
     return code.toMapx();
   }
 
   public void add() {
     ZDCodeSchema code = new ZDCodeSchema();
     code.setValue(this.Request);
     if (code.fill()) {
       this.Response.setLogInfo(0, "代码值" + code.getCodeValue() + "已经存在了!");
       return;
     }
     code.setCodeOrder(System.currentTimeMillis());
     code.setAddTime(new Date());
     code.setAddUser(User.getUserName());
     if (code.insert()) {
       CacheManager.getCache("Code").init();
       this.Response.setLogInfo(1, "新建代码成功!");
     } else {
       this.Response.setLogInfo(0, "新建代码失败!");
     }
   }
 
   public void del() {
     DataTable dt = (DataTable)this.Request.get("DT");
     ZDCodeSet set = new ZDCodeSet();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       ZDCodeSchema code = new ZDCodeSchema();
       code.setValue(dt.getDataRow(i));
       code.fill();
       set.add(code);
       if ("System".equals(code.getParentCode())) {
         ZDCodeSchema childCode = new ZDCodeSchema();
         childCode.setParentCode(code.getCodeType());
         set.add(childCode.query());
       }
     }
 
     StringBuffer codeLog = new StringBuffer("删除代码:");
     for (int i = 0; i < set.size(); ++i) {
       codeLog.append(set.get(i).getCodeName() + ",");
     }
     if (set.deleteAndBackup()) {
       UserLog.log("System", "DelCode", codeLog + "成功", this.Request.getClientIP());
       CacheManager.getCache("Code").init();
       this.Response.setLogInfo(1, "删除代码成功!");
     } else {
       UserLog.log("System", "DelCode", codeLog + "失败", this.Request.getClientIP());
       this.Response.setLogInfo(0, "删除代码失败!");
     }
   }
 
   public void sortColumn() {
     String target = $V("Target");
     String orders = $V("Orders");
     String type = $V("Type");
     String parentCode = $V("ParentCode");
     if ((!(StringUtil.checkID(target))) && (!(StringUtil.checkID(orders)))) {
       return;
     }
     if (OrderUtil.updateOrder("ZDCode", "CodeOrder", type, target, orders, " ParentCode = '" + parentCode + "'"))
       this.Response.setMessage("排序成功");
     else
       this.Response.setError("排序失败");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.Code
 * JD-Core Version:    0.5.3
 */