 package com.zving.platform;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.cache.CacheManager;
 import com.zving.framework.cache.CacheProvider;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZDCodeSchema;
 import com.zving.schema.ZDCodeSet;
 import java.util.Date;
 
 public class CodeArea extends Page
 {
   public static void dg1BindCode(DataGridAction dga)
   {
     String sql = 
       "select ParentCode,CodeValue,CodeName,CodeOrder,Memo from ZDCode where prop4='S' and parentcode='system' order by CodeOrder ";
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(1) from ZDCode where prop4='S' and parentcode='system'"));
     }
     dga.bindData(new QueryBuilder(sql));
   }
 
   public static void dg1BindCodeList(DataGridAction dga) {
     String sql = 
       "select ParentCode,CodeValue,CodeName,CodeOrder,Memo from ZDCode where prop4='S' and parentcode=? order by CodeOrder ";
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(1) from ZDCode where prop4='S' and parentcode=?", dga.getParam("CodeValue")));
     }
     dga.bindData(new QueryBuilder(sql, dga.getParam("CodeValue")));
   }
 
   public static Mapx init(Mapx params) {
     Mapx map = new Mapx();
     String codeValue = (String)params.get("CodeValue");
     map.put("CodeValue", codeValue);
     String codeName = 
       new QueryBuilder("select codename from zdcode where prop4='S' and codetype='AreaCode' and parentcode='System' and codevalue=?", codeValue).executeString();
     map.put("CodeName", codeName);
     return map;
   }
 
   public static Mapx initDialog(Mapx params) {
     Mapx map = new Mapx();
     map.put("CodeName", "");
     return map;
   }
 
   public void save() {
     ZDCodeSchema code = new ZDCodeSchema();
     code.setCodeType("AreaCode");
     if ($V("isEdit").equals("0")) {
       code.setValue(this.Request);
       if (code.fill()) {
         this.Response.setStatus(0);
         this.Response.setMessage(code.getCodeValue() + "已经存在了!");
         return;
       }
       code.setProp4("S");
       code.setAddTime(new Date());
       code.setAddUser(User.getUserName());
       if (code.insert()) {
         CacheManager.getCache("Code").init();
         this.Response.setStatus(1);
         this.Response.setMessage("操作成功!");
       } else {
         this.Response.setStatus(0);
         this.Response.setMessage("新建失败!");
       }
     } else {
       code.setParentCode($V("ParentCode"));
       code.setCodeValue($V("CodeValue"));
       code.fill();
       code.setMemo($V("Memo"));
       code.setCodeOrder(System.currentTimeMillis());
       code.setModifyTime(new Date());
       code.setAddUser(User.getUserName());
       if (code.update()) {
         CacheManager.getCache("Code").init();
         this.Response.setStatus(1);
         this.Response.setMessage("操作成功!");
       } else {
         this.Response.setStatus(0);
         this.Response.setMessage("修改失败!");
       }
     }
   }
 
   public void del() {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     ids = ids.replaceAll(",", "','");
 
     ZDCodeSchema code = new ZDCodeSchema();
     ZDCodeSet set = null;
 
     if ("System".equals($V("ParentCode")))
       set = 
         code.query(
         new QueryBuilder("where codetype='AreaCode' and ((ParentCode='System' and codevalue in ('" + ids + 
         "')) or (ParentCode in ('" + ids + "')))"));
     else {
       set = 
         code.query(new QueryBuilder("where codetype='AreaCode' and ParentCode=? and codevalue in ('" + ids + "')", $V("ParentCode")));
     }
 
     if (set.deleteAndBackup()) {
       CacheManager.getCache("Code").init();
       this.Response.setStatus(1);
       this.Response.setMessage("删除成功！");
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.CodeArea
 * JD-Core Version:    0.5.3
 */