 package com.zving.platform;
 
 import com.zving.cms.pub.CatalogUtil;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Mapx;
 import java.util.HashMap;
 import java.util.Hashtable;
 import java.util.Map;
 
 public class RolePriv
 {
   public static final String OWNERTYPE_ROLE = "R";
   private static Map RolePrivMap = new Hashtable();
 
   public static void updateAllPriv(String RoleCode) {
     Object obj = new QueryBuilder("select RoleCode from ZDRole where RoleCode = ?", RoleCode).executeOneValue();
     if (obj == null) {
       RolePrivMap.remove(RoleCode);
       return;
     }
     Object[] ks = Priv.PRIV_MAP.keyArray();
     for (int i = 0; i < Priv.PRIV_MAP.size(); ++i)
       updatePriv(RoleCode, (String)ks[i]);
   }
 
   public static void updatePriv(String RoleCode, String PrivType)
   {
     String sql = "select ID,Code,Value from ZDPrivilege where OwnerType=? and Owner=? and PrivType=?";
     QueryBuilder qb = new QueryBuilder(sql);
     qb.add("R");
     qb.add(RoleCode);
     qb.add(PrivType);
     DataTable dt = qb.executeDataTable();
     Map PrivTypeMap = getPrivTypeMap(RoleCode, PrivType);
     getMapFromDataTable(PrivTypeMap, dt);
   }
 
   public static boolean getRolePriv(String[] RoleCodes, String PrivType, String ID, String Code)
   {
     String value = null;
     Map map;
     if ("menu".equals(PrivType)) {
       i = 0; break label74:
       while (true) { map = getPrivTypeMap(RoleCodes[i], PrivType);
         map = (Map)map.get(ID);
         if (map != null) {
           value = (String)map.get(Code);
           if ("1".equals(value))
             return true;
         }
         ++i; if (i >= RoleCodes.length)
         {
           label74: return false; } } }
     if ("site".equals(PrivType)) {
       i = 0; break label154:
       while (true) { map = getPrivTypeMap(RoleCodes[i], PrivType);
         map = (Map)map.get(ID);
         if (map != null) {
           value = (String)map.get(Code);
           if ("1".equals(value))
             return true;
         }
         ++i; if (i >= RoleCodes.length)
         {
           label154: return false; } }
     }
     for (int i = 0; i < RoleCodes.length; ++i) {
       map = getPrivTypeMap(RoleCodes[i], PrivType);
       map = (Map)map.get(ID);
       if (map != null) {
         value = (String)map.get(Code);
         if ("1".equals(value)) {
           return true;
         }
       }
     }
     if (value != null) {
       return "1".equals(value);
     }
     if (ID.length() > 7)
     {
       return getRolePriv(RoleCodes, PrivType, ID.substring(0, ID.length() - 4), Code);
     }
 
     return getRolePriv(RoleCodes, "site", CatalogUtil.getSiteIDByInnerCode(ID), Code);
   }
 
   public static Map getPrivTypeMap(String RoleCode, String PrivType)
   {
     Map RoleCodePrivMap = (Map)RolePrivMap.get(RoleCode);
     if (RoleCodePrivMap == null) {
       RoleCodePrivMap = new Hashtable();
       RolePrivMap.put(RoleCode, RoleCodePrivMap);
       updateAllPriv(RoleCode);
     }
     Map PrivTypeMap = (Map)RoleCodePrivMap.get(PrivType);
     if (PrivTypeMap == null) {
       PrivTypeMap = new HashMap();
       RoleCodePrivMap.put(PrivType, PrivTypeMap);
     }
     return PrivTypeMap;
   }
 
   public static void getMapFromDataTable(Map map, DataTable dt) {
     map.clear();
     if ((dt == null) || (dt.getRowCount() <= 0)) {
       return;
     }
     for (int i = 0; i < dt.getRowCount(); ++i) {
       Map childMap = (Map)map.get(dt.getString(i, "ID"));
       if (childMap == null) {
         childMap = new HashMap();
         map.put(dt.getString(i, "ID"), childMap);
       }
       childMap.put(dt.getString(i, "Code"), dt.getString(i, "Value"));
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.RolePriv
 * JD-Core Version:    0.5.3
 */