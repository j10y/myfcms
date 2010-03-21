 package com.zving.cms.site;
 
 import com.zving.cms.document.Article;
 import com.zving.cms.pub.SiteUtil;
 import com.zving.framework.Config;
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataColumn;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.schema.ZCCatalogSchema;
 import com.zving.schema.ZCCatalogSet;
 import com.zving.schema.ZCMagazineIssueSchema;
 import com.zving.schema.ZCMagazineIssueSet;
 import com.zving.schema.ZCMagazineSchema;
 import java.io.PrintStream;
 import java.util.Date;
 
 public class MagazineIssue extends Page
 {
   public static void dg1DataBind(DataGridAction dga)
   {
     long magazineID = Long.parseLong(dga.getParam("MagazineID"));
     QueryBuilder qb = 
       new QueryBuilder("select ID,MagazineID,year,PeriodNum,CoverImage,Status,Memo,publishDate as pubDate,addtime from ZCMagazineIssue where magazineID=? order by ID desc", magazineID);
     DataTable dt = qb.executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     if ((dt != null) && (dt.getRowCount() > 0)) {
       dt.decodeColumn("Status", Article.STATUS_MAP);
       dt.getDataColumn("pubDate").setDateFormat("yy-MM-dd");
     }
     if (dga.getTotal() == 0) {
       QueryBuilder qbTotal = new QueryBuilder("select count(*) from ZCMagazineIssue where magazineID=?", magazineID);
       dga.setTotal(qbTotal);
     }
     dga.bindData(dt);
   }
 
   public static Mapx init(Mapx params) {
     return params;
   }
 
   public static Mapx initDialog(Mapx params) {
     String magazineIssueID = params.getString("ID");
     String coverImage = "upload/Image/nopicture.jpg";
     DataTable dt = new QueryBuilder("select coverImage from ZCMagazineIssue where id=?", magazineIssueID).executeDataTable();
     if ((dt.getRowCount() == 0) || (StringUtil.isEmpty((String)dt.get(0, 0)))) {
       params.put("CoverImage", coverImage);
       params.put("PicSrc", Config.getContextPath() + Config.getValue("UploadDir") + "/" + 
         SiteUtil.getAlias(Application.getCurrentSiteID()) + "/" + coverImage);
       System.out.println(params);
       return params;
     }
     ZCMagazineIssueSchema magazineIssue = new ZCMagazineIssueSchema();
     magazineIssue.setID(magazineIssueID);
     magazineIssue.fill();
     params = magazineIssue.toMapx();
     coverImage = magazineIssue.getCoverImage();
     params.put("PicSrc", Config.getContextPath() + Config.getValue("UploadDir") + "/" + 
       SiteUtil.getAlias(Application.getCurrentSiteID()) + "/" + coverImage);
     return params;
   }
 
   public void add() {
     long magazineID = Long.parseLong($V("MagazineID"));
     Transaction trans = new Transaction();
 
     Catalog catalog = new Catalog();
     this.Request.put("Name", $V("Year") + "年第" + $V("PeriodNum") + "期");
     this.Request.put("ParentID", magazineID);
     this.Request.put("Alias", $V("Year") + $V("PeriodNum"));
     this.Request.put("Type", "3");
     this.Request.put("ImagePath", $V("CoverImage"));
 
     ZCCatalogSchema catalogSchema = catalog.add(this.Request, trans);
 
     ZCMagazineIssueSchema issue = new ZCMagazineIssueSchema();
     issue.setID(catalogSchema.getID());
     issue.setValue(this.Request);
     issue.setAddTime(new Date());
     issue.setAddUser(User.getUserName());
     issue.setStatus(1L);
     trans.add(issue, 1);
 
     ZCMagazineSchema magazine = new ZCMagazineSchema();
     magazine.setID(magazineID);
     if (magazine.fill()) {
       magazine.setTotal(magazine.getTotal() + 1L);
       magazine.setCurrentYear($V("Year"));
       magazine.setCurrentPeriodNum($V("PeriodNum"));
       magazine.setCoverImage($V("CoverImage"));
       magazine.setModifyTime(new Date());
       magazine.setModifyUser(User.getUserName());
       trans.add(magazine, 2);
     }
 
     if (StringUtil.isNotEmpty($V("CatalogIDs"))) {
       if (!(StringUtil.checkID($V("CatalogIDs")))) {
         this.Response.setStatus(0);
         this.Response.setMessage("发生错误!");
         return;
       }
       ZCCatalogSchema catalogLastIssue = new ZCCatalogSchema();
       ZCCatalogSet set = catalogLastIssue.query(new QueryBuilder("where id in(" + $V("CatalogIDs") + ")"));
       for (int i = 0; i < set.size(); ++i) {
         catalog.add(catalogSchema, set.get(i), trans);
       }
     }
 
     trans.add(new QueryBuilder("update zccatalog set ImagePath=? where id=?", $V("CoverImage"), magazineID));
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("发生错误!");
     }
   }
 
   public void edit() {
     long magazineID = Long.parseLong($V("MagazineID"));
     Transaction trans = new Transaction();
 
     ZCMagazineIssueSchema issue = new ZCMagazineIssueSchema();
     issue.setID(Long.parseLong($V("ID")));
     if (!(issue.fill())) {
       this.Response.setStatus(0);
       this.Response.setMessage("没有找到期号!");
       return;
     }
     issue.setValue(this.Request);
     issue.setModifyTime(new Date());
     issue.setModifyUser(User.getUserName());
     issue.setStatus(1L);
     trans.add(issue, 2);
 
     ZCMagazineSchema magazine = new ZCMagazineSchema();
     magazine.setID(magazineID);
     if (magazine.fill()) {
       magazine.setTotal(magazine.getTotal() + 1L);
       magazine.setCurrentYear($V("Year"));
       magazine.setCurrentPeriodNum($V("PeriodNum"));
       magazine.setCoverImage($V("CoverImage"));
       magazine.setModifyTime(new Date());
       magazine.setModifyUser(User.getUserName());
       trans.add(magazine, 2);
     }
     trans.add(new QueryBuilder("update zccatalog set ImagePath=? where id=?", $V("CoverImage"), magazineID));
     trans.add(new QueryBuilder("update zccatalog set ImagePath=? where id=?", $V("CoverImage"), issue.getID()));
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("发生错误!");
     }
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
     ZCMagazineIssueSchema MagazineIssue = new ZCMagazineIssueSchema();
     ZCMagazineIssueSet set = MagazineIssue.query(new QueryBuilder("where id in (" + ids + ")"));
     trans.add(set, 5);
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 
   public void getPicSrc() {
     String ID = $V("PicID");
     String id = $V("ID");
     DataTable dt = new QueryBuilder("select path,filename from zcimage where id=?", ID).executeDataTable();
     if (dt.getRowCount() > 0) {
       this.Response.put("picSrc", Config.getContextPath() + Config.getValue("UploadDir") + "/" + 
         SiteUtil.getAlias(Application.getCurrentSiteID()) + "/" + dt.get(0, "path").toString() + "s_" + 
         dt.get(0, "filename").toString());
       this.Response.put("CoverImage", dt.get(0, "path").toString() + "1_" + dt.get(0, "filename").toString());
     }
     Transaction trans = new Transaction();
     ZCMagazineIssueSchema magazineIssue = new ZCMagazineIssueSchema();
     if (StringUtil.isNotEmpty(id)) {
       magazineIssue.setID(id);
       magazineIssue.fill();
       magazineIssue.setValue(this.Request);
       magazineIssue.setCoverImage((String)this.Response.get("CoverImage"));
       trans.add(magazineIssue, 2);
       trans.commit();
     } else {
       return;
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.MagazineIssue
 * JD-Core Version:    0.5.3
 */