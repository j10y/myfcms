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
 import com.zving.framework.utility.Mapx;
 import com.zving.schema.ZDPrivilegeSchema;
 import java.util.List;
 
 public class UserTabSite extends Page
 {
   public static void dg1DataBind(DataGridAction dga)
   {
     Mapx map = dga.getParams();
     String UserName = (String)map.get("UserName");
     if ((UserName == null) || ("".equals(UserName))) {
       dga.bindData(new DataTable());
       return;
     }
     String s = "";
     String PrivType = (String)map.get("PrivType");
     StringBuffer sb = new StringBuffer();
     sb.append(",'" + UserName + "' as UserName");
 
     Object[] ks = Priv.SITE_MAP.keyArray();
     for (int i = 0; i < Priv.SITE_MAP.size(); ++i) {
       sb.append(",'" + s + "' as " + ks[i].toString());
     }
 
     String sql = 
       "select ID,Name,0 as TreeLevel ,'site' as PrivType " + sb.toString() + 
       " from ZCSite a order by BranchInnerCode ,orderflag ,id";
     dga.setTotal(new QueryBuilder("select count(*) from ZCSite a "));
     DataTable siteDT = new QueryBuilder(sql).executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     DataRow dr = null;
     for (int i = 0; i < siteDT.getRowCount(); ++i) {
       dr = siteDT.getDataRow(i);
       for (int j = 0; j < dr.getColumnCount(); ++j) {
         String columnName = dr.getDataColumn(j).getColumnName().toLowerCase();
         if (columnName.indexOf("_") > 0) {
           dr.set(j, (Priv.getPriv(UserName, PrivType, dr.getString("ID"), columnName)) ? "√" : "");
         }
       }
     }
     dga.bindData(siteDT);
   }
 
   public void dg1Edit() {
     DataTable dt = (DataTable)this.Request.get("DT");
     String UserName = $V("UserName");
     String PrivType = $V("PrivType");
     String[] RoleCodes = new String[0];
     List roleCodeList = PubFun.getRoleCodesByUserName(UserName);
     if ((roleCodeList != null) && (roleCodeList.size() != 0)) {
       RoleCodes = (String[])roleCodeList.toArray(new String[roleCodeList.size()]);
     }
     Transaction trans = new Transaction();
     ZDPrivilegeSchema p = new ZDPrivilegeSchema();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       for (int j = 0; j < dt.getColCount(); ++j) {
         if (dt.getDataColumn(j).getColumnName().indexOf("_") > 0) {
           trans.add(p.query(
             new QueryBuilder("where OwnerType='U' and Owner = '" + UserName + 
             "' and PrivType = '" + dt.getString(i, "PrivType") + "' and ID = '" + dt.getString(i, "ID") + 
             "' and Code = '" + dt.getDataColumn(j).getColumnName() + "' ")), 5);
         }
       }
     }
     boolean value = false;
     for (int i = 0; i < dt.getRowCount(); ++i) {
       DataRow dr = dt.getDataRow(i);
 
       int j = 0;
       boolean same = true;
 
       while (j < dr.getColumnCount()) {
         if (dr.getDataColumn(j).getColumnName().indexOf("_") > 0) {
           value = "√".equals(dr.getString(j));
           roleValue = 
             RolePriv.getRolePriv(RoleCodes, PrivType, dr.getString("ID"), dr.getDataColumn(j).getColumnName());
           if (value != roleValue) {
             same = false;
             break;
           }
         }
         ++j;
       }
       if (same) {
         continue;
       }
       boolean roleValue = false;
       for (j = 0; j < dr.getColumnCount(); ++j) {
         if (dr.getDataColumn(j).getColumnName().indexOf("_") > 0) {
           value = "√".equals(dr.getString(j));
           roleValue = 
             RolePriv.getRolePriv(RoleCodes, PrivType, dr.getString("ID"), dr.getDataColumn(j).getColumnName());
 
           ZDPrivilegeSchema userPriv = new ZDPrivilegeSchema();
           userPriv.setOwnerType("U");
           userPriv.setOwner(UserName);
           userPriv.setID(dr.getString("ID"));
           userPriv.setPrivType(dr.getString("PrivType"));
           userPriv.setCode(dr.getDataColumn(j).getColumnName());
           if (roleValue) {
             if (value)
               userPriv.setValue("0");
             else {
               userPriv.setValue("-1");
             }
           }
           else if (value)
             userPriv.setValue("1");
           else {
             userPriv.setValue("0");
           }
 
           trans.add(userPriv, 1);
         }
       }
     }
     if (trans.commit()) {
       Priv.updateAllPriv(UserName);
       this.Response.setLogInfo(1, "修改成功!");
     } else {
       this.Response.setLogInfo(0, "修改失败!");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.UserTabSite
 * JD-Core Version:    0.5.3
 */