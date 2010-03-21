 package com.zving.cms.site;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.schema.ZCImagePlayerSchema;
 import com.zving.schema.ZCImagePlayerSet;
 
 public class ImagePlayer extends Page
 {
   public static void dg1DataBind(DataGridAction dga)
   {
     String sql = "select ID,Name,Code,SiteID,DisplayType,ImageSource,Height,Width,Displaycount from ZCImagePlayer where SiteID = " + 
       Application.getCurrentSiteID() + " order by ID ";
     if (dga.getTotal() == 0) {
       String sql2 = "select count(*) from ZCImagePlayer where SiteID = " + Application.getCurrentSiteID();
       dga.setTotal(new QueryBuilder(sql2));
     }
     dga.bindData(new QueryBuilder(sql));
   }
 
   public void del() {
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
     Transaction trans = new Transaction();
     ZCImagePlayerSchema ImagePlayer = new ZCImagePlayerSchema();
     ZCImagePlayerSet set = ImagePlayer.query(new QueryBuilder("where id in (" + ids + ")"));
     trans.add(set, 5);
 
     if (trans.commit()) {
       this.Response.setStatus(1);
       this.Response.setMessage("删除成功!");
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.ImagePlayer
 * JD-Core Version:    0.5.3
 */