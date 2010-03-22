 package com.zving.cms.dataservice;
 
 import com.zving.framework.Ajax;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCApplySchema;
 import java.util.Date;
 
 public class Apply extends Ajax
 {
   public static final String Gender_F = "F";
   public static final String Gender_M = "M";
   public static final Mapx Gender_MAP = new Mapx();
   public static final String EduLevel_Uni = "05";
   public static final String EduLevel_Jun = "04";
   public static final String EduLevel_Mas = "06";
   public static final Mapx EduLevel_MAP;
   public static final String Political_Pol = "01";
   public static final String Political_Pro = "02";
   public static final String Political_Lea = "03";
   public static final String Political_Peo = "04";
   public static final String Political_Oth = "05";
   public static final Mapx Political_MAP;
 
   static
   {
     Gender_MAP.put("M", "男");
     Gender_MAP.put("F", "女");
 
     EduLevel_MAP = new Mapx();
 
     EduLevel_MAP.put("05", "本科");
     EduLevel_MAP.put("04", "专科");
     EduLevel_MAP.put("06", "硕士");
 
     Political_MAP = new Mapx();
 
     Political_MAP.put("01", "中共党员");
     Political_MAP.put("02", "预备党员");
     Political_MAP.put("03", "共青团员");
     Political_MAP.put("04", "群众");
     Political_MAP.put("05", "其他党派人士");
   }
 
   public static Mapx init(Mapx params) {
     String position = params.getString("Position");
     params.put("Political", HtmlUtil.mapxToOptions(Political_MAP, true));
     params.put("EduLevel", HtmlUtil.mapxToOptions(EduLevel_MAP, true));
     params.put("Gender", HtmlUtil.mapxToRadios("Gender", Gender_MAP, "M"));
     params.put("Ethnicity", HtmlUtil.codeToOptions("Ethnicity"));
     params.put("District", "");
     params.put("Position", position);
 
     Mapx district = new QueryBuilder("select code,name from zddistrict where treelevel=1").executeDataTable().toMapx(0, 1);
     params.put("NativePlace", HtmlUtil.mapxToOptions(district));
     return params;
   }
 
   public void add() {
     ZCApplySchema apply = new ZCApplySchema();
     apply.setAddTime(new Date());
     apply.setID(NoUtil.getMaxID("ID"));
     apply.setValue(this.Request);
     apply.setAddUser("");
     apply.setAddTime(new Date());
 
     if (apply.insert())
       this.Response.setLogInfo(1, "新增成功");
     else
       this.Response.setLogInfo(0, "新增" + apply.getName() + "失败!");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.dataservice.Apply
 * JD-Core Version:    0.5.3
 */