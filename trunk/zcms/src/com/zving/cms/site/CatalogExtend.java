 package com.zving.cms.site;
 
 import com.zving.cms.dataservice.ColumnUtil;
 import com.zving.cms.pub.SiteUtil;
 import com.zving.framework.Config;
 import com.zving.framework.Page;
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
 
 public class CatalogExtend extends Page
 {
   public static String Input = "1";
 
   public static String ImageInput = "7";
 
   public static String HtmlInput = "8";
 
   public static Mapx InputTypeMap = new Mapx();
 
   static {
     InputTypeMap.put(Input, "文本框");
     InputTypeMap.put(ImageInput, "媒体库图片框");
     InputTypeMap.put(HtmlInput, "HTML");
   }
 
   public static Mapx initDialog(Mapx params) {
     String ID = params.getString("ColumnID");
     if (StringUtil.isEmpty(ID)) {
       params.put("ImagePath", "upload/Image/nopicture.jpg");
       params.put("ImageFile", Config.getContextPath() + Config.getValue("UploadDir") + "/" + Application.getCurrentSiteAlias() + "/" + 
         "upload/Image/nopicture.jpg".replaceAll("//", "/"));
       params.put("InputType", HtmlUtil.mapxToOptions(InputTypeMap));
     } else {
       String CatalogID = params.getString("CatalogID");
       QueryBuilder qb = new QueryBuilder("select b.ColumnID as id,a.Code,a.InputType,a.Name,b.ID as ValueID,b.ColumnID,b.TextValue from ZDColumn a, ZDColumnValue b where a.ID = b.ColumnID and b.RelaType='0' and b.RelaID =? and b.ColumnID= ? order by a.OrderFlag asc", CatalogID, ID);
       DataTable dt = qb.executeDataTable();
       DataRow dr = dt.getDataRow(0);
       params = dr.toMapx();
       String inputType = dr.getString("InputType");
       if (Input.equals(inputType)) {
         params.put("Text", dr.getString("TextValue"));
       } else if (ImageInput.equals(inputType)) {
         params.put("ImagePath", dr.getString("TextValue"));
         params.put("ImageFile", Config.getContextPath() + Config.getValue("UploadDir") + "/" + Application.getCurrentSiteAlias() + 
           "/" + dr.getString("TextValue").replaceAll("//", "/"));
       } else if (HtmlInput.equals(inputType)) {
         params.put("Content", dr.getString("TextValue"));
       }
       params.put("InputType", HtmlUtil.mapxToOptions(InputTypeMap, inputType));
     }
     return params;
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     String CatalogID = dga.getParam("CatalogID");
     QueryBuilder qb = new QueryBuilder("select a.Code,a.InputType,a.Name,b.ID,b.ColumnID,b.TextValue from ZDColumn a, ZDColumnValue b where a.ID = b.ColumnID and b.RelaType='0' and b.RelaID =? order by a.OrderFlag asc", CatalogID);
     DataTable dt = qb.executeDataTable();
     String path = Config.getContextPath() + Config.getValue("UploadDir") + "/" + Application.getCurrentSiteAlias() + 
       "/".replaceAll("//", "/");
     String Str = "";
     for (int i = 0; i < dt.getRowCount(); ++i) {
       if (ImageInput.equals(dt.getString(i, "InputType"))) {
         dt.set(i, "TextValue", "<img src='" + path + dt.getString(i, "TextValue") + "' width='100' height='75'>");
       }
       if (HtmlInput.equals(dt.getString(i, "InputType"))) {
         Str = dt.getString(i, "TextValue");
         if (Str.length() > 50) {
           Str = Str.substring(0, 50) + "...";
         }
         dt.set(i, "TextValue", StringUtil.htmlEncode(Str));
       }
     }
     dt.decodeColumn("InputType", InputTypeMap);
     dga.bindData(dt);
   }
 
   public void add() {
     String catalogID = $V("CatalogID");
     String columnCode = $V("Code");
     String textValue = "";
     long count = new QueryBuilder("select count(*) from ZDColumnRela where RelaType='0' and RelaID = ? and ColumnCode =? ", 
       catalogID, columnCode).executeLong();
     if (count > 0L) {
       this.Response.setLogInfo(0, "已经存在相同的字段");
       return;
     }
     Transaction trans = new Transaction();
     ZDColumnSchema column = new ZDColumnSchema();
     column.setID(NoUtil.getMaxID("ColumnID"));
     column.setCode($V("Code"));
     column.setName($V("Name"));
     column.setSiteID(Application.getCurrentSiteID());
     column.setInputType($V("InputType"));
     column.setVerifyType(ColumnUtil.STRING);
     column.setIsMandatory("N");
     column.setOrderFlag(OrderUtil.getDefaultOrder());
     column.setAddTime(new Date());
     column.setAddUser(User.getUserName());
 
     ZDColumnRelaSchema rela = new ZDColumnRelaSchema();
     rela.setID(NoUtil.getMaxID("ColumnRelaID"));
     rela.setColumnID(column.getID());
     rela.setColumnCode(columnCode);
     rela.setRelaType("0");
     rela.setRelaID(catalogID);
     rela.setAddTime(column.getAddTime());
     rela.setAddUser(column.getAddUser());
 
     if (Input.equals(column.getInputType())) {
       textValue = $V("TextValue");
     } else if (ImageInput.equals(column.getInputType())) {
       textValue = $V("ImagePath");
       if ((StringUtil.isNotEmpty(textValue)) && (textValue.indexOf("upload") > 0))
         textValue = textValue.substring(textValue.indexOf("upload"));
     }
     else if (HtmlInput.equals(column.getInputType())) {
       textValue = $V("Content");
     }
     ZDColumnValueSchema value = new ZDColumnValueSchema();
     value.setID(NoUtil.getMaxID("ColumnValueID"));
     value.setColumnID(column.getID());
     value.setColumnCode(columnCode);
     value.setRelaType("0");
     value.setRelaID(catalogID);
     value.setTextValue(textValue);
 
     trans.add(column, 1);
     trans.add(rela, 1);
     trans.add(value, 1);
     if (trans.commit())
       this.Response.setLogInfo(1, "新建成功");
     else
       this.Response.setLogInfo(0, "新建失败");
   }
 
   public void save()
   {
     String catalogID = $V("CatalogID");
     String columnID = $V("ColumnID");
     String valueID = $V("ValueID");
     String columnCode = $V("Code");
     String textValue = "";
     long count = new QueryBuilder("select count(*) from ZDColumnRela where RelaType='0' and RelaID = ? and ColumnCode =? and ColumnID !=" + 
       columnID, catalogID, columnCode).executeLong();
     if (count > 0L) {
       this.Response.setLogInfo(0, "已经存在相同的字段");
       return;
     }
     Transaction trans = new Transaction();
     ZDColumnSchema column = new ZDColumnSchema();
     column.setID(columnID);
     column.fill();
     column.setCode($V("Code"));
     column.setName($V("Name"));
     column.setInputType($V("InputType"));
     column.setVerifyType(ColumnUtil.STRING);
     column.setIsMandatory("N");
     column.setModifyTime(new Date());
     column.setModifyUser(User.getUserName());
 
     ZDColumnRelaSchema rela = new ZDColumnRelaSchema();
     rela.setColumnID(column.getID());
     rela.setRelaType("0");
     rela.setRelaID(catalogID);
     rela = rela.query().get(0);
 
     rela.setColumnID(column.getID());
     rela.setColumnCode(columnCode);
     rela.setRelaType("0");
     rela.setRelaID(catalogID);
     rela.setModifyTime(column.getModifyTime());
     rela.setModifyUser(column.getModifyUser());
 
     if (Input.equals(column.getInputType())) {
       textValue = $V("TextValue");
     } else if (ImageInput.equals(column.getInputType())) {
       textValue = $V("ImagePath");
       if ((StringUtil.isNotEmpty(textValue)) && (textValue.indexOf("upload") > 0))
         textValue = textValue.substring(textValue.indexOf("upload"));
     }
     else if (HtmlInput.equals(column.getInputType())) {
       textValue = $V("Content");
     }
     ZDColumnValueSchema value = new ZDColumnValueSchema();
     value.setID(valueID);
     value.setColumnID(column.getID());
     value.setColumnCode(columnCode);
     value.setRelaType("0");
     value.setRelaID(catalogID);
     value.setTextValue(textValue);
 
     trans.add(column, 2);
     trans.add(rela, 2);
     trans.add(value, 2);
     if (trans.commit())
       this.Response.setLogInfo(1, "修改成功");
     else
       this.Response.setLogInfo(0, "修改失败");
   }
 
   public void del()
   {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     String catalogID = $V("CatalogID");
     Transaction trans = new Transaction();
     ZDColumnSet columnSet = new ZDColumnSchema().query(new QueryBuilder("where ID in (" + ids + ") "));
     ZDColumnRelaSet relaSet = new ZDColumnRelaSchema().query(
       new QueryBuilder("where columnID in (" + ids + ") and RelaType='" + 
       "0" + "' and RelaID='" + catalogID + "'"));
     ZDColumnValueSet valueSet = new ZDColumnValueSchema().query(
       new QueryBuilder("where columnID in (" + ids + ") and RelaType='" + 
       "0" + "' and RelaID='" + catalogID + "'"));
 
     trans.add(columnSet, 5);
     trans.add(relaSet, 5);
     trans.add(valueSet, 5);
     if (trans.commit())
       this.Response.setLogInfo(1, "删除成功");
     else
       this.Response.setLogInfo(0, "删除失败");
   }
 
   public void getPicSrc()
   {
     String ID = $V("PicID");
     DataTable dt = new QueryBuilder("select path,filename,srcfilename from zcimage where id=?", ID).executeDataTable();
     if (dt.getRowCount() > 0) {
       this.Response.put("picSrc", Config.getContextPath() + Config.getValue("UploadDir") + "/" + 
         SiteUtil.getAlias(Application.getCurrentSiteID()) + "/" + dt.get(0, "path").toString() + "s_" + 
         dt.get(0, "filename").toString());
       this.Response.put("ImagePath", dt.get(0, "path").toString() + dt.get(0, "srcfilename").toString());
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.CatalogExtend
 * JD-Core Version:    0.5.3
 */