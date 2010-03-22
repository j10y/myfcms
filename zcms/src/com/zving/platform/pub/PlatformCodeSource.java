 package com.zving.platform.pub;
 
 import com.zving.framework.cache.CacheManager;
 import com.zving.framework.controls.CodeSource;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 
 public class PlatformCodeSource extends CodeSource
 {
   public DataTable getCodeData(String codeType, Mapx params)
   {
     DataTable dt = null;
     String conditionField = params.getString("ConditionField");
     String conditionValue = params.getString("ConditionValue");
     QueryBuilder qb;
     if ("District".equals(codeType)) {
       qb = new QueryBuilder("select code,name from ZDDistrict where " + conditionField + "=?", 
         conditionValue);
       String parentCode = params.getString("ParentCode");
       if (StringUtil.isNotEmpty(parentCode)) {
         qb.appendSQLPart(" and Code like ?");
 
         if ((parentCode.startsWith("11")) || (parentCode.startsWith("12")) || (parentCode.startsWith("31")) || 
           (parentCode.startsWith("50"))) {
           qb.add(parentCode.substring(0, 2) + "%");
           qb.appendSQLPart(" and TreeLevel=3");
         } else if (parentCode.endsWith("0000")) {
           qb.add(parentCode.substring(0, 2) + "%");
           qb.appendSQLPart(" and TreeLevel=2");
         } else if (parentCode.endsWith("00")) {
           qb.add(parentCode.substring(0, 4) + "%");
           qb.appendSQLPart(" and TreeLevel=3");
         } else {
           qb.add("#");
         }
       } else if (conditionField.equals("1")) {
         return new DataTable();
       }
       dt = qb.executeDataTable();
     } else if ("User".equals(codeType)) {
       qb = new QueryBuilder(
         "select UserName,UserName as 'Name',RealName,isBranchAdmin from ZDUser where " + conditionField + 
         "=?", conditionValue);
       dt = qb.executeDataTable();
     } else {
       Mapx map = CacheManager.get("Code", codeType);
       if (conditionValue.equals("2")) {
         map.remove("01");
       }
       if (map != null) {
         dt = map.toDataTable();
       }
     }
     return dt;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.platform.pub.PlatformCodeSource
 * JD-Core Version:    0.5.3
 */