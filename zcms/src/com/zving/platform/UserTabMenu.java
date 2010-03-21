 package com.zving.platform;
 
 import com.zving.cms.pub.PubFun;
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataColumn;
 import com.zving.framework.data.DataRow;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Filter;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZDPrivilegeSchema;
 import java.util.LinkedList;
 import java.util.List;
 
 public class UserTabMenu extends Page
 {
   public static Mapx init(Mapx params)
   {
     String userName = params.getString("UserName");
     DataTable dt = new QueryBuilder("select name,id from zcsite order by BranchInnerCode ,orderflag ,id").executeDataTable();
     dt = dt.filter(new Filter(userName) {
       public boolean filter(Object obj) {
         DataRow dr = (DataRow)obj;
         return Priv.getPriv((String)this.Param, "site", dr.getString("ID"), "site_browse");
       }
     });
     String SiteID = params.getString("SiteID");
     if (StringUtil.isEmpty(SiteID)) {
       SiteID = params.getString("OldSiteID");
       if (StringUtil.isEmpty(SiteID)) {
         SiteID = Application.getCurrentSiteID();
       }
     }
     params.put("SiteID", HtmlUtil.dataTableToOptions(dt, SiteID));
     return params;
   }
 
   public static void dg1DataBind(DataGridAction dga)
   {
     String sql = "select ID ,Name,Icon,Type,'' as TreeLevel  from ZDMenu where (parentid in(select id from ZDMenu where parentid=0 and visiable='Y') or parentid=0) and visiable='Y' order by OrderFlag";
     DataTable dt = new QueryBuilder(sql).executeDataTable();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       if ("2".equals(dt.get(i, "Type")))
         dt.set(i, "TreeLevel", "1");
       else {
         dt.set(i, "TreeLevel", "0");
       }
     }
     dga.bindData(dt);
   }
 
   public void getCheckedMenu()
   {
     String UserName = $V("UserName");
     if (StringUtil.isEmpty(UserName)) {
       this.Response.put("checkedMenu", "");
       return;
     }
     String SiteID = $V("SiteID");
     List list = new LinkedList();
     String sql = "select ID ,Name,Icon,Type,'' as TreeLevel  from ZDMenu where (parentid in (select id from ZDMenu where parentid=0 and visiable='Y') or parentid=0) and visiable='Y' order by OrderFlag";
     DataTable dt = new QueryBuilder(sql).executeDataTable();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       if (Priv.getPriv(UserName, "menu", SiteID + "-" + dt.getString(i, "ID"), "menu_browse")) {
         list.add(dt.getString(i, "ID"));
       }
     }
     this.Response.put("checkedMenu", StringUtil.join(list.toArray()));
   }
 
   public void save()
   {
     String UserName = $V("UserName");
     String SiteID = $V("SiteID");
     DataTable resultDT = (DataTable)this.Request.get("dt");
     String[] RoleCodes = new String[0];
     List roleCodeList = PubFun.getRoleCodesByUserName(UserName);
     if ((roleCodeList != null) && (roleCodeList.size() != 0)) {
       RoleCodes = (String[])roleCodeList.toArray(new String[roleCodeList.size()]);
     }
     DataColumn[] types = resultDT.getDataColumns();
     DataColumn[] copyTypes = new DataColumn[types.length];
     System.arraycopy(types, 0, copyTypes, 0, types.length);
     Object[][] copyValues = new Object[resultDT.getRowCount()][types.length];
     for (int i = 0; i < copyValues.length; ++i) {
       System.arraycopy(resultDT.getDataRow(i).getDataValues(), 0, copyValues[i], 0, types.length);
     }
     DataTable rolePrivDT = new DataTable(copyTypes, copyValues);
     for (int i = 0; i < rolePrivDT.getRowCount(); ++i) {
       for (int j = 0; j < rolePrivDT.getColCount(); ++j) {
         if (rolePrivDT.getDataColumn(j).getColumnName().indexOf("_") > 0) {
           rolePrivDT.set(i, j, (RolePriv.getRolePriv(RoleCodes, "menu", SiteID + "-" + rolePrivDT.getString(i, "ID"), rolePrivDT.getDataColumn(j).getColumnName())) ? "1" : 
             "0");
         }
       }
 
     }
 
     String v1 = null;
     String v2 = null;
     for (int i = 0; i < rolePrivDT.getRowCount(); ++i) {
       for (int j = 0; j < rolePrivDT.getColCount(); ++j) {
         if (rolePrivDT.getDataColumn(j).getColumnName().indexOf("_") > 0) {
           v1 = rolePrivDT.getString(i, j);
           v2 = resultDT.getString(i, j);
           if (v1.equals(v2)) {
             resultDT.set(i, j, "0");
           } else if ("0".equals(v1)) {
             if ("1".equals(v2))
               resultDT.set(i, j, "1");
           } else {
             if ((!("1".equals(v1))) || 
               (!("0".equals(v2)))) continue;
             resultDT.set(i, j, "-1");
           }
         }
       }
 
     }
 
     Transaction trans = new Transaction();
     trans.add(new ZDPrivilegeSchema().query(
       new QueryBuilder(" where OwnerType=? and Owner=? and PrivType='menu' and ID like '" + 
       SiteID + "%'", "U", $V("UserName"))), 5);
     for (int i = 0; i < resultDT.getRowCount(); ++i) {
       if (("1".equals(resultDT.getString(i, "menu_browse"))) || ("-1".equals(resultDT.getString(i, "menu_browse")))) {
         ZDPrivilegeSchema priv = new ZDPrivilegeSchema();
         priv.setOwnerType("U");
         priv.setOwner(UserName);
         priv.setID(SiteID + "-" + resultDT.getString(i, "ID"));
         priv.setPrivType("menu");
         priv.setCode("menu_browse");
         priv.setValue(resultDT.getString(i, "menu_browse"));
         trans.add(priv, 1);
       }
     }
 
     if (trans.commit()) {
       Priv.updatePriv(UserName, "menu");
       this.Response.setLogInfo(1, "保存成功!");
     } else {
       this.Response.setLogInfo(0, "保存失败!");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.UserTabMenu
 * JD-Core Version:    0.5.3
 */