 package com.zving.cms.site;
 
 import com.zving.cms.pub.SiteUtil;
 import com.zving.framework.Config;
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.schema.ZCCatalogSchema;
 import com.zving.schema.ZCCatalogSet;
 import com.zving.schema.ZCPaperIssueSchema;
 import com.zving.schema.ZCPaperIssueSet;
 import com.zving.schema.ZCPaperSchema;
 import java.io.PrintStream;
 import java.util.Date;
 
 public class NewspaperIssue extends Page
 {
   public static void dg1DataBind(DataGridAction dga)
   {
     long PaperID = Long.parseLong(dga.getParam("NewspaperID"));
     QueryBuilder qb = 
       new QueryBuilder("select ID,PaperID,year,PeriodNum,CoverImage,Status,Memo,publishDate as pubDate,addtime from ZCPaperIssue where PaperID=? order by ID desc", PaperID);
 
     if (dga.getTotal() == 0) {
       QueryBuilder qbTotal = new QueryBuilder("select count(*) from ZCPaperIssue where PaperID=?", PaperID);
       dga.setTotal(qbTotal);
     }
     dga.bindData(qb);
   }
 
   public static Mapx init(Mapx params) {
     return params;
   }
 
   public void add() {
     long paperID = Long.parseLong($V("NewspaperID"));
     Transaction trans = new Transaction();
     Catalog catalog = new Catalog();
     this.Request.put("Name", $V("PublishDate") + "(" + $V("Year") + "年" + $V("PeriodNum") + "期)");
     this.Request.put("ParentID", paperID);
     this.Request.put("Alias", $V("Year") + $V("PeriodNum"));
     this.Request.put("Type", "8");
 
     long catalogID = catalog.add(this.Request, trans).getID();
 
     ZCPaperIssueSchema issue = new ZCPaperIssueSchema();
     issue.setPaperID(paperID);
     issue.setID(catalogID);
     issue.setValue(this.Request);
     issue.setAddTime(new Date());
     issue.setAddUser(User.getUserName());
     issue.setStatus(1L);
     trans.add(issue, 1);
 
     ZCPaperSchema paper = new ZCPaperSchema();
     paper.setID(paperID);
     if (paper.fill()) {
       paper.setTotal(paper.getTotal() + 1L);
       paper.setCurrentYear($V("Year"));
       paper.setCurrentPeriodNum($V("PeriodNum"));
       paper.setCoverImage($V("CoverImage"));
       paper.setModifyTime(new Date());
       paper.setModifyUser(User.getUserName());
       trans.add(paper, 2);
     }
     System.out.println($V("CoverImage"));
     trans.add(new QueryBuilder("update zccatalog set imagePath=? where id=?", $V("CoverImage"), paperID));
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("发生错误!");
     }
   }
 
   public void edit() {
     long paperID = Long.parseLong($V("PaperID"));
     Transaction trans = new Transaction();
 
     ZCPaperIssueSchema issue = new ZCPaperIssueSchema();
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
 
     ZCPaperSchema Paper = new ZCPaperSchema();
     Paper.setID(paperID);
     if (Paper.fill()) {
       Paper.setTotal(Paper.getTotal() + 1L);
       Paper.setCurrentYear($V("Year"));
       Paper.setCurrentPeriodNum($V("PeriodNum"));
       Paper.setCoverImage($V("CoverImage"));
       Paper.setModifyTime(new Date());
       Paper.setModifyUser(User.getUserName());
       trans.add(Paper, 2);
     }
     trans.add(new QueryBuilder("update zccatalog set imageID=? where id=?", $V("CoverImage"), paperID));
 
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
     ZCPaperIssueSchema paperIssue = new ZCPaperIssueSchema();
     ZCPaperIssueSet set = paperIssue.query(new QueryBuilder("where id in (" + ids + ")"));
     trans.add(set, 5);
 
     ZCCatalogSchema catalog = new ZCCatalogSchema();
     ZCCatalogSet catalogSet = catalog.query(new QueryBuilder("where id in (" + ids + ") or parentid in (" + ids + ")"));
     trans.add(catalogSet, 5);
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 
   public void getPicSrc() {
     String ID = $V("PicID");
     DataTable dt = new QueryBuilder("select path,filename from zcimage where id=?", ID).executeDataTable();
 
     if (dt.getRowCount() > 0)
       this.Response.put("picSrc", Config.getContextPath() + Config.getValue("UploadDir") + 
         SiteUtil.getAlias(Application.getCurrentSiteID()) + "/" + dt.get(0, "path").toString() + "s_" + 
         dt.get(0, "filename").toString());
     else
       this.Response.put("picSrc", "../Images/nopicture.gif");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.NewspaperIssue
 * JD-Core Version:    0.5.3
 */