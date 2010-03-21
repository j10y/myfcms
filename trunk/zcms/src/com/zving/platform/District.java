 package com.zving.platform;
 
 import com.zving.framework.Config;
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZDDistrictSchema;
 import com.zving.schema.ZDDistrictSet;
 
 public class District extends Page
 {
   static Mapx codeMap;
   static Mapx nameMap;
   static DataTable table;
 
   public static void dg1BindDistrict(DataGridAction dga)
   {
     String sql = " select * from ZDDistrict where TreeLevel = 1 or TreeLevel = 2 order by CodeOrder ";
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(*) from ZDDistrict where TreeLevel = 1 or TreeLevel = 2"));
     }
     dga.bindData(new QueryBuilder(sql));
   }
 
   public static void dg1BindDistrictList(DataGridAction dga) {
     String code = dga.getParam("Code");
     if (StringUtil.isEmpty(code)) {
       dga.bindData(new DataTable());
       return;
     }
     String parentCode = "";
     ZDDistrictSchema district = new ZDDistrictSchema();
     district.setCode(code);
     district.fill();
     if ("0".equals(district.getType()))
       parentCode = code.substring(0, 2);
     else {
       parentCode = code.substring(0, 4);
     }
     dga.setTotal(new QueryBuilder("select count(*) from ZDDistrict where TreeLevel = '3' and code like ?", parentCode + "%"));
     dga.bindData(
       new QueryBuilder(" select * from ZDDistrict where TreeLevel = '3' and code like ? order by CodeOrder ", parentCode + 
       "%"));
   }
 
   public static Mapx init(Mapx params) {
     Mapx map = new Mapx();
     String code = (String)params.get("Code");
     map.put("Code", code);
     String Name = new QueryBuilder("select name from ZDDistrict where code = ?", code).executeString();
     map.put("Name", Name);
     return map;
   }
 
   public static Mapx initDialog(Mapx params) {
     params.put("Name", "");
     return params;
   }
 
   public void add() {
     ZDDistrictSchema district = new ZDDistrictSchema();
     String code = $V("Code");
     district.setCode(code);
     if (district.fill()) {
       this.Response.setLogInfo(0, district.getCode() + "已经存在了!");
       return;
     }
     district.setValue(this.Request);
     if (district.insert())
       this.Response.setLogInfo(1, "新建成功!");
     else
       this.Response.setLogInfo(0, "新建失败!");
   }
 
   public void dg1Edit()
   {
     DataTable dt = (DataTable)this.Request.get("DT");
     ZDDistrictSet set = new ZDDistrictSet();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       ZDDistrictSchema district = new ZDDistrictSchema();
       district.setCode(dt.getString(i, "Code"));
       if (!(district.fill())) {
         this.Response.setLogInfo(0, "您要修改的项" + dt.getString(i, "Code") + "不存在!");
         return;
       }
       district.setValue(dt.getDataRow(i));
       set.add(district);
     }
     if (set.update()) {
       generateDistrictJS();
       this.Response.setStatus(1);
       this.Response.setMessage("保存成功!");
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("保存失败!");
     }
   }
 
   public void del() {
     String IDs = $V("IDs");
     String[] ids = IDs.split(",");
     String code = "";
     Transaction trans = new Transaction();
     ZDDistrictSchema district = new ZDDistrictSchema();
     for (int i = 0; i < ids.length; ++i) {
       district.setCode(ids[i]);
       if (district.fill()) {
         if (district.getTreeLevel() == 1)
           code = ids[i].substring(0, 2);
         else if (district.getTreeLevel() == 2)
           code = ids[i].substring(0, 4);
         else if (district.getTreeLevel() == 3) {
           code = ids[i];
         }
         trans.add(district.query(new QueryBuilder(" where Code like ?", code + "%")), 3);
       }
     }
     if (trans.commit())
       this.Response.setLogInfo(1, "删除成功");
     else
       this.Response.setLogInfo(0, "删除失败");
   }
 
   public static void generateDistrictJS()
   {
     ZDDistrictSet set = new ZDDistrictSchema().query(new QueryBuilder("where code!='000000' order by code"));
 
     StringBuffer provinceMap = new StringBuffer();
     StringBuffer cityMap = new StringBuffer();
     StringBuffer districtMap = new StringBuffer();
     provinceMap.append("var provinceMap = [");
     cityMap.append("var cityMap = {\n");
     districtMap.append("var districtMap = {\n");
 
     boolean firstProv = true;
     boolean firstCity = true;
     boolean firstDistrict = true;
     for (int i = 0; i < set.size(); ++i) {
       ZDDistrictSchema d = set.get(i);
       int j;
       int k;
       if ("1".equals(d.getType())) {
         if (!(firstProv)) {
           provinceMap.append(",");
         }
         firstProv = false;
         provinceMap.append("'" + d.getCode() + "','" + d.getName() + "'");
 
         if (!(firstCity)) {
           cityMap.append(",\n");
         }
         cityMap.append("'" + d.getCode() + "':[");
         for (j = i + 1; j < set.size(); ++j) {
           d = set.get(j);
           if ("2".equals(d.getType())) {
             firstCity = false;
             if (j == i + 1)
               cityMap.append("'" + d.getCode() + "','" + d.getName() + "'");
             else {
               cityMap.append(",'" + d.getCode() + "','" + d.getName() + "'");
             }
 
             if (!(firstDistrict)) {
               districtMap.append(",\n");
             }
             districtMap.append("'" + d.getCode() + "':[");
             for (k = j + 1; k < set.size(); ++k) {
               d = set.get(k);
               if (!("3".equals(d.getType()))) break;
               firstDistrict = false;
               if (k == j + 1)
                 districtMap.append("'" + d.getCode() + "','" + d.getName() + "'");
               else {
                 districtMap.append(",'" + d.getCode() + "','" + d.getName() + "'");
               }
 
             }
 
             districtMap.append("]"); } else {
             if ("1".equals(d.getType())) break; if ("0".equals(d.getType()))
               break;
           }
         }
         cityMap.append("]"); } else {
         if (!("0".equals(d.getType())))
           continue;
         if (!(firstProv)) {
           provinceMap.append(",");
         }
         firstProv = false;
         provinceMap.append("'" + d.getCode() + "','" + d.getName() + "'");
 
         if (!(firstCity)) {
           cityMap.append(",\n");
         }
         cityMap.append("'" + d.getCode() + "':[");
         for (j = i; j < i + 1; ++j) {
           d = set.get(j);
           if ("0".equals(d.getType())) {
             firstCity = false;
             cityMap.append("'" + d.getCode() + "','" + d.getName() + "'");
 
             if (!(firstDistrict)) {
               districtMap.append(",\n");
             }
             districtMap.append("'" + d.getCode() + "':[");
             for (k = j + 1; k < set.size(); ++k) {
               d = set.get(k);
               if (!("3".equals(d.getType()))) break;
               firstDistrict = false;
               if (k == j + 1)
                 districtMap.append("'" + d.getCode() + "','" + d.getName() + "'");
               else {
                 districtMap.append(",'" + d.getCode() + "','" + d.getName() + "'");
               }
 
             }
 
             districtMap.append("]"); } else {
             if ("1".equals(d.getType()))
               break;
           }
         }
         cityMap.append("]");
       }
     }
 
     provinceMap.append("];\n");
     cityMap.append("};\n");
     districtMap.append("};\n");
 
     String path = Config.getContextRealPath() + "WEB-INF/classes/com/zving/Platform/district.template";
     String func = FileUtil.readText(path);
     String JS = Config.getContextRealPath() + "Framework/District.js";
     FileUtil.writeText(JS, provinceMap.toString() + cityMap.toString() + districtMap.toString() + func);
   }
 
   public static String getProvinceName(String address)
   {
     if (codeMap == null) {
       table = 
         new QueryBuilder("select Name,Code from ZDDistrict where code like '11%' or code like '12%' or code like '31%' or code like '50%' or treelevel in (1,2,3) order by treelevel,code desc")
         .executeDataTable();
       codeMap = table.toMapx(0, 1);
       nameMap = table.toMapx(1, 0);
     }
     String name;
     for (int j = 0; j < table.getRowCount(); ++j) {
       name = table.getString(j, "Name");
       if (address.indexOf(name) >= 0) {
         return name;
       }
     }
     for (j = 0; j < table.getRowCount(); ++j) {
       name = table.getString(j, "Name");
       if (address.startsWith(name.substring(0, 2))) {
         return name;
       }
     }
     for (j = 0; j < table.getRowCount(); ++j) {
       name = table.getString(j, "Name");
       if (address.indexOf(name.substring(0, 2)) >= 0) {
         return name;
       }
     }
     return null;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.District
 * JD-Core Version:    0.5.3
 */