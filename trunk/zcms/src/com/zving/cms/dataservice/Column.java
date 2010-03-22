 package com.zving.cms.dataservice;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.platform.pub.NoUtil;
 import com.zving.platform.pub.OrderUtil;
 import com.zving.schema.ZDColumnRelaSchema;
 import com.zving.schema.ZDColumnRelaSet;
 import com.zving.schema.ZDColumnSchema;
 import com.zving.schema.ZDColumnSet;
 import com.zving.schema.ZDColumnValueSchema;
 import com.zving.schema.ZDColumnValueSet;
 import java.util.Date;
 
 public class Column extends Page
 {
   public static Mapx initDialog(Mapx params)
   {
     String ID = params.getString("ColumnID");
     if (StringUtil.isEmpty(ID)) {
       params.put("VerifyType", HtmlUtil.mapxToOptions(ColumnUtil.VerifyTypeMap));
       params.put("InputType", HtmlUtil.mapxToOptions(ColumnUtil.InputTypeMap));
       params.put("IsMandatory", HtmlUtil.codeToRadios("IsMandatory", "YesOrNo", "N"));
       params.put("MaxLength", "100");
       params.put("ColSize", "265");
       params.put("RowSize", "90");
     } else {
       ZDColumnSchema column = new ZDColumnSchema();
       column.setID(ID);
       column.fill();
       params = column.toMapx();
       params.put("VerifyType", HtmlUtil.mapxToOptions(ColumnUtil.VerifyTypeMap, column.getVerifyType()));
       params.put("InputType", HtmlUtil.mapxToOptions(ColumnUtil.InputTypeMap, column.getInputType()));
       params.put("IsMandatory", HtmlUtil.codeToRadios("IsMandatory", "YesOrNo", column.getIsMandatory()));
     }
     params.put("DataType", HtmlUtil.mapxToOptions(CustomTable.DBTypeMap));
     return params;
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     String FormID = dga.getParam("FormID");
     String sql = "select ZDColumn.* ,(select FormName from ZDForm where ZDForm.ID = ZDColumn.FormID) as FormName from ZDColumn where FormID=? order by OrderFlag asc ";
     QueryBuilder qb = new QueryBuilder();
     qb.add(FormID);
     qb.setSQL(sql);
     DataTable dt = qb.executeDataTable();
     dt.decodeColumn("VerifyType", ColumnUtil.VerifyTypeMap);
     dt.decodeColumn("InputType", ColumnUtil.InputTypeMap);
     dt.decodeColumn("DataType", CustomTable.DBTypeMap);
     dga.bindData(dt);
   }
 
   public void add() {
     ZDColumnSchema column = new ZDColumnSchema();
     String code = $V("Code");
     String formID = $V("FormID");
     long count = new QueryBuilder("select count(*) from ZDColumn where FormID=? and Code=?", formID, code)
       .executeLong();
     if (count > 0L) {
       this.Response.setLogInfo(0, "已经存在相同的字段");
       return;
     }
     column.setValue(this.Request);
 
     String defaultValue = column.getDefaultValue();
     defaultValue = defaultValue.replaceAll("　　", ",");
     defaultValue = defaultValue.replaceAll("　", ",");
     defaultValue = defaultValue.replaceAll("  ", ",");
     defaultValue = defaultValue.replaceAll(" ", ",");
     defaultValue = defaultValue.replaceAll(",,", ",");
     defaultValue = defaultValue.replaceAll("，，", ",");
     defaultValue = defaultValue.replaceAll("，", ",");
     column.setDefaultValue(defaultValue);
 
     column.setID(NoUtil.getMaxID("ColumnID"));
     column.setOrderFlag(OrderUtil.getDefaultOrder());
     column.setAddTime(new Date());
     column.setAddUser(User.getUserName());
     column.setSiteID(Application.getCurrentSiteID());
 
     if (ColumnUtil.Input.equals(column.getInputType())) {
       column.setColSize(null);
       column.setRowSize(null);
       column.setListOption("");
     } else if (ColumnUtil.Text.equals(column.getInputType())) {
       column.setListOption("");
     } else if (ColumnUtil.Select.equals(column.getInputType())) {
       column.setColSize(null);
       column.setRowSize(null);
       column.setMaxLength(null);
       column.setVerifyType(ColumnUtil.STRING);
     } else if (ColumnUtil.Radio.equals(column.getInputType())) {
       column.setIsMandatory("N");
       column.setColSize(null);
       column.setRowSize(null);
       column.setMaxLength(null);
       column.setVerifyType(ColumnUtil.STRING);
     } else if (ColumnUtil.Checkbox.equals(column.getInputType())) {
       column.setIsMandatory("N");
       column.setColSize(null);
       column.setRowSize(null);
       column.setMaxLength(null);
       column.setVerifyType(ColumnUtil.STRING);
     } else if (ColumnUtil.DateInput.equals(column.getInputType())) {
       column.setColSize(null);
       column.setRowSize(null);
       column.setMaxLength(null);
       column.setListOption("");
       column.setVerifyType(ColumnUtil.STRING);
     } else if (ColumnUtil.ImageInput.equals(column.getInputType())) {
       column.setIsMandatory("N");
       column.setColSize(null);
       column.setRowSize(null);
       column.setMaxLength(null);
       column.setListOption("");
       column.setVerifyType(ColumnUtil.STRING);
     }
 
     Transaction trans = new Transaction();
     trans.add(column, 1);
     if (trans.commit())
       this.Response.setLogInfo(1, "新建成功");
     else
       this.Response.setLogInfo(0, "新建失败");
   }
 
   public void edit()
   {
     String ID = $V("ColumnID");
     ZDColumnSchema column = new ZDColumnSchema();
     column.setID(ID);
     column.fill();
     long count = new QueryBuilder("select count(*) from ZDColumn where FormID=? and Code=?", $V("FormID"), 
       $V("ColumnCode")).executeLong();
     if (count > 1L) {
       this.Response.setLogInfo(0, "已经存在相同的字段");
       return;
     }
     column.setValue(this.Request);
 
     String defaultValue = column.getDefaultValue();
     defaultValue = defaultValue.replaceAll("　　", ",");
     defaultValue = defaultValue.replaceAll("　", ",");
     defaultValue = defaultValue.replaceAll("  ", ",");
     defaultValue = defaultValue.replaceAll(" ", ",");
     defaultValue = defaultValue.replaceAll(",,", ",");
     defaultValue = defaultValue.replaceAll("，，", ",");
     defaultValue = defaultValue.replaceAll("，", ",");
     column.setDefaultValue(defaultValue);
 
     column.setModifyUser(User.getUserName());
     column.setModifyTime(new Date());
 
     if (ColumnUtil.Input.equals(column.getInputType())) {
       column.setColSize(null);
       column.setRowSize(null);
       column.setListOption("");
     } else if (ColumnUtil.Text.equals(column.getInputType())) {
       column.setListOption("");
     } else if (ColumnUtil.Select.equals(column.getInputType())) {
       column.setColSize(null);
       column.setRowSize(null);
       column.setMaxLength(null);
       column.setVerifyType(ColumnUtil.STRING);
     } else if (ColumnUtil.Radio.equals(column.getInputType())) {
       column.setIsMandatory("N");
       column.setColSize(null);
       column.setRowSize(null);
       column.setMaxLength(null);
       column.setVerifyType(ColumnUtil.STRING);
     } else if (ColumnUtil.Checkbox.equals(column.getInputType())) {
       column.setIsMandatory("N");
       column.setColSize(null);
       column.setRowSize(null);
       column.setMaxLength(null);
       column.setVerifyType(ColumnUtil.STRING);
     } else if (ColumnUtil.DateInput.equals(column.getInputType())) {
       column.setColSize(null);
       column.setRowSize(null);
       column.setMaxLength(null);
       column.setListOption("");
       column.setVerifyType(ColumnUtil.STRING);
     } else if (ColumnUtil.ImageInput.equals(column.getInputType())) {
       column.setIsMandatory("N");
       column.setColSize(null);
       column.setRowSize(null);
       column.setMaxLength(null);
       column.setListOption("");
       column.setVerifyType(ColumnUtil.STRING);
     }
 
     if (column.update())
       this.Response.setLogInfo(1, "修改成功!");
     else
       this.Response.setLogInfo(0, "修改失败!");
   }
 
   public void del()
   {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setLogInfo(0, "传入ID时发生错误!");
       return;
     }
     ZDColumnSchema column = new ZDColumnSchema();
     Transaction trans = new Transaction();
     ZDColumnSet set = column.query(new QueryBuilder("where id in (" + ids + ")"));
     ZDColumnRelaSet relaSet = new ZDColumnRelaSchema().query(
       new QueryBuilder("where columnID in (" + ids + 
       ") and RelaType='" + "1" + "'"));
     ZDColumnValueSet valueSet = new ZDColumnValueSchema().query(
       new QueryBuilder("where columnID in (" + ids + 
       ") and RelaType='" + "2" + "'"));
     trans.add(set, 5);
     trans.add(relaSet, 5);
     trans.add(valueSet, 5);
     if (trans.commit())
       this.Response.setLogInfo(1, "删除成功");
     else
       this.Response.setLogInfo(0, "删除失败");
   }
 
   public void sortColumn()
   {
     String target = $V("Target");
     String orders = $V("Orders");
     String type = $V("Type");
     String formID = $V("FormID");
     if ((!(StringUtil.checkID(target))) && (!(StringUtil.checkID(orders)))) {
       return;
     }
     if (OrderUtil.updateOrder("ZDColumn", type, target, orders, " FormID = " + formID))
       this.Response.setMessage("排序成功");
     else
       this.Response.setError("排序失败");
   }
 
   public static Mapx initCopyTo(Mapx params)
   {
     Mapx map = new Mapx();
     String tIDs = params.get("IDs").toString();
     map.put("IDs", tIDs);
     String tCatalogID = params.get("CatalogID").toString();
     map.put("CatalogID", tCatalogID);
     String tSiteID = new QueryBuilder("select siteid from ZCCatalog where id=?", tCatalogID).executeString();
     map.put("SiteID", tSiteID);
     DataTable dt = new QueryBuilder("select name,id from ZCCatalog where siteid=? and id<>? order by id", tSiteID, 
       tCatalogID).executeDataTable();
     map.put("optCatalog", HtmlUtil.dataTableToOptions(dt));
     return map;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.dataservice.Column
 * JD-Core Version:    0.5.3
 */