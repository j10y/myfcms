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
 import com.zving.schema.ZCPaperIssueSchema;
 import com.zving.schema.ZCPaperIssueSet;
 import com.zving.schema.ZCPaperPageSchema;
 import com.zving.schema.ZCPaperSchema;
 import java.util.Date;
 
 public class NewspaperPage extends Page
 {
   public static void dg1DataBind(DataGridAction dga)
   {
     long issueID = Long.parseLong(dga.getParam("CatalogID"));
     QueryBuilder qb = 
       new QueryBuilder("select a.ID,a.IssueID,a.pageNo,a.Title,a.PaperImage,(select concat(path,srcfilename) from zcimage where id=a.PaperImage) as Image,a.PDFFile,(select concat(path,srcfilename) from zcattachment where id=a.pdffile) as File,a.Memo,b.listtemplate,b.detailtemplate from ZCPaperPage a,zccatalog b where a.issueid=? and a.id = b.id order by a.pageno", issueID);
     if (dga.getTotal() == 0) {
       QueryBuilder qbTotal = new QueryBuilder("select count(*) from ZCPaperPage where issueid=?", issueID);
       dga.setTotal(qbTotal);
     }
     dga.bindData(qb);
   }
 
   public static Mapx init(Mapx params) {
     return params;
   }
 
   public void add() {
     long issueID = Long.parseLong($V("IssueID"));
     Transaction trans = new Transaction();
 
     Catalog catalog = new Catalog();
     this.Request.put("Name", "第" + $V("PageNo") + "版 " + $V("Title"));
     this.Request.put("ParentID", issueID);
     this.Request.put("Alias", $V("PageNo"));
     this.Request.put("Type", "8");
 
     long catalogID = catalog.add(this.Request, trans).getID();
 
     ZCPaperPageSchema page = new ZCPaperPageSchema();
     page.setID(catalogID);
     page.setValue(this.Request);
     page.setAddTime(new Date());
     page.setAddUser(User.getUserName());
     trans.add(page, 1);
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("发生错误!");
     }
   }
 
   public void edit() {
     long PaperID = Long.parseLong($V("PaperID"));
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
     Paper.setID(PaperID);
     if (Paper.fill()) {
       Paper.setTotal(Paper.getTotal() + 1L);
       Paper.setCurrentYear($V("Year"));
       Paper.setCurrentPeriodNum($V("PeriodNum"));
       Paper.setCoverImage($V("CoverImage"));
       Paper.setModifyTime(new Date());
       Paper.setModifyUser(User.getUserName());
       trans.add(Paper, 2);
     }
     trans.add(new QueryBuilder("update zccatalog set imageID=? where id=?", $V("CoverImage"), PaperID));
 
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
 * Qualified Name:     com.zving.cms.site.NewspaperPage
 * JD-Core Version:    0.5.3
 */