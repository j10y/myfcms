 package com.zving.cms.dataservice;
 
 import com.zving.framework.Config;
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.cache.CacheManager;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZCApplySchema;
 
 public class ApplyAdmin extends Page
 {
   public static void dg1DataBind(DataGridAction dga)
   {
     String searchGender = dga.getParams().getString("searchGender");
     String searchEduLevel = dga.getParams().getString("searchEduLevel");
     StringBuffer conditions = new StringBuffer();
 
     if (StringUtil.isNotEmpty(searchGender)) {
       conditions.append(" and Gender='" + searchGender + "'");
     }
     if (StringUtil.isNotEmpty(searchEduLevel)) {
       conditions.append(" and EduLevel='" + searchEduLevel + "'");
     }
     String sql = "select * from  ZCApply where 1=1 " + conditions + 
       " order by ID desc";
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(*) from ZCApply where 1=1 " + conditions));
     }
     DataTable dt = new QueryBuilder(sql).executePagedDataTable(dga
       .getPageSize(), dga.getPageIndex());
     dt.decodeColumn("Gender", Apply.Gender_MAP);
     dt.decodeColumn("EduLevel", Apply.EduLevel_MAP);
     dga.bindData(dt);
   }
 
   public static Mapx initSearch(Mapx params) {
     Mapx genderMap = new Mapx();
     genderMap.put("", "所有");
     genderMap.putAll(Apply.Gender_MAP);
     params.put("Gender", HtmlUtil.mapxToOptions(genderMap, ""));
     Mapx eduLevelMap = new Mapx();
     eduLevelMap.put("", "所有");
     eduLevelMap.putAll(Apply.EduLevel_MAP);
     params.put("EduLevel", HtmlUtil.mapxToOptions(eduLevelMap, ""));
     return params;
   }
 
   public void del() {
     String IDs = $V("IDs");
     String[] ids = IDs.split(",");
     Transaction trans = new Transaction();
     ZCApplySchema applicant = new ZCApplySchema();
     for (int i = 0; i < ids.length; ++i) {
       trans.add(applicant.query(
         new QueryBuilder(" where ID = ?", 
         ids[i])), 3);
     }
     if (trans.commit())
       this.Response.setLogInfo(1, "删除成功");
     else
       this.Response.setLogInfo(0, "删除失败");
   }
 
   public static Mapx initDialog(Mapx params) {
     String ID = params.getString("ID");
     if (StringUtil.isNotEmpty(ID)) {
       ZCApplySchema applicant = new ZCApplySchema();
       applicant.setID(ID);
       applicant.fill();
       Mapx map = applicant.toMapx();
       map.put("Gender", Apply.Gender_MAP.get(applicant.getGender()));
       map.put("EduLevel", Apply.EduLevel_MAP.get(applicant.getEduLevel()));
       map.put("Ethnicity", CacheManager.get("Code", "Ethnicity").get(applicant.getEthnicity()));
       map.put("Political", Apply.Political_MAP.get(applicant.getPolitical()));
       map.put("PictureFile", Config.getContextPath() + Config.getValue("UploadDir") + "/" + applicant.getPicture().replaceAll("//", "/"));
       return map;
     }
     return params;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.dataservice.ApplyAdmin
 * JD-Core Version:    0.5.3
 */