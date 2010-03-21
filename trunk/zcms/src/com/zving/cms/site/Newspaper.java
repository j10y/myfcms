 package com.zving.cms.site;
 
 import com.zving.cms.pub.PubFun;
 import com.zving.cms.pub.SiteUtil;
 import com.zving.framework.Config;
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.TreeAction;
 import com.zving.framework.controls.TreeItem;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.platform.Application;
 import com.zving.schema.ZCCatalogSchema;
 import com.zving.schema.ZCPaperIssueSchema;
 import com.zving.schema.ZCPaperSchema;
 import java.io.PrintStream;
 import java.util.Date;
 import java.util.List;
 
 public class Newspaper extends Page
 {
   public static void treeDataBind(TreeAction ta)
   {
     Object obj = ta.getParams().get("SiteID");
     String siteID = Application.getCurrentSiteID();
     int catalogType = 8;
     DataTable dt = null;
 
     QueryBuilder qb = new QueryBuilder("select ID,ParentID,TreeLevel,Name from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel<3", catalogType, siteID);
     dt = qb.executeDataTable();
 
     String siteName = "报纸库";
     String inputType = (String)ta.getParams().get("Type");
     if ("3".equals(inputType))
       ta.setRootText("<input type='radio' name=CatalogID id='_site' value='" + siteID + "'><label for='_site'>" + 
         siteName + "</label>");
     else if ("2".equals(inputType))
       ta.setRootText("<input type='CheckBox' name=CatalogID id='_site' value='" + siteID + 
         "' onclick='selectAll()'><label for='_site'>" + siteName + "</label>");
     else {
       ta.setRootText(siteName);
     }
 
     ta.bindData(dt);
     List items = ta.getItemList();
     for (int i = 0; i < items.size(); ++i) {
       TreeItem item = (TreeItem)items.get(i);
       if (item.getLevel() == 1)
         item.setIcon("Icons/icon008a2.gif");
     }
   }
 
   public static void docTreeDataBind(TreeAction ta) {
     String siteID = Application.getCurrentSiteID();
     int catalogType = 8;
     DataTable dt = null;
 
     dt = new QueryBuilder("select ID,ParentID,TreeLevel,Name from ZCCatalog Where Type = ? and SiteID = ?", catalogType, siteID).executeDataTable();
     String siteName = "报纸库";
 
     String inputType = (String)ta.getParams().get("Type");
     if ("3".equals(inputType))
       ta.setRootText("<input type='radio' name=CatalogID id='_site' value='" + siteID + "'><label for='_site'>" + 
         siteName + "</label>");
     else if ("2".equals(inputType))
       ta.setRootText("<input type='CheckBox' name=CatalogID id='_site' value='" + siteID + 
         "' onclick='selectAll()'><label for='_site'>" + siteName + "</label>");
     else {
       ta.setRootText(siteName);
     }
 
     ta.bindData(dt);
     List items = ta.getItemList();
     for (int i = 0; i < items.size(); ++i) {
       TreeItem item = (TreeItem)items.get(i);
       if (item.getLevel() == 1)
         item.setIcon("Icons/icon008a1.gif");
       else if (item.getLevel() == 2)
         item.setIcon("Icons/icon018a11.gif");
       else if (item.getLevel() == 3)
         item.setIcon("Icons/icon5.gif");
     }
   }
 
   public static Mapx initDialog(Mapx params)
   {
     String sql = "select CodeName,CodeValue from ZDCode where ParentCode !='System' and CodeType ='Period' Order by CodeOrder";
     DataTable dt1 = new QueryBuilder(sql).executeDataTable();
 
     Object o1 = params.get("NewspaperID");
     if (o1 != null) {
       long ID = Long.parseLong(o1.toString());
       ZCPaperSchema paper = new ZCPaperSchema();
       paper.setID(ID);
       if (paper.fill()) {
         Mapx map = paper.toMapx();
         String imagePath = PubFun.getImagePath((String)map.get("CoverImage"));
         if (imagePath == null)
           imagePath = "../Images/nopicture.gif";
         else {
           imagePath = Config.getContextPath() + Config.getValue("UploadDir") + 
             SiteUtil.getAlias(paper.getSiteID()) + "/" + imagePath;
         }
 
         map.put("PicSrc", imagePath);
         map.put("optionPeriod", HtmlUtil.dataTableToOptions(dt1));
         return map;
       }
       return null;
     }
     params.put("SiteID", Application.getCurrentSiteID());
     params.put("PicSrc", "../Images/nopicture.gif");
     params.put("CoverTemplate", "/template/Paper.html");
     params.put("optionPeriod", HtmlUtil.dataTableToOptions(dt1));
     return params;
   }
 
   public static Mapx initIssue(Mapx params)
   {
     String sql = "select concat(year,'年',periodNum,'期') as Name,ID from zcPaperissue where PaperID=(select min(id) from zcPaper where siteid=" + 
       Application.getCurrentSiteID() + 
       ") order by id desc";
     DataTable dt1 = new QueryBuilder(sql).executeDataTable();
     params.put("optionIssue", HtmlUtil.dataTableToOptions(dt1));
     return params;
   }
 
   public static Mapx init(Mapx params) {
     Object o1 = params.get("NewspaperID");
     System.out.println(o1);
     if (o1 != null) {
       long ID = Long.parseLong(o1.toString());
       ZCPaperSchema Paper = new ZCPaperSchema();
       Paper.setID(ID);
       if (Paper.fill()) {
         Mapx map = Paper.toMapx();
         return map;
       }
       return params;
     }
     return params;
   }
 
   public void add()
   {
     Transaction trans = new Transaction();
     Catalog catalog = new Catalog();
     long catalogID = catalog.add(this.Request, trans).getID();
 
     ZCPaperSchema Paper = new ZCPaperSchema();
     Paper.setID(catalogID);
     Paper.setValue(this.Request);
     Paper.setAddTime(new Date());
     Paper.setAddUser(User.getUserName());
     trans.add(Paper, 1);
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("插入数据发生错误!");
     }
   }
 
   public void edit() {
     Transaction trans = new Transaction();
     ZCPaperSchema Paper = new ZCPaperSchema();
     Paper.setID(Long.parseLong($V("NewspaperID")));
     if (!(Paper.fill())) {
       this.Response.setStatus(0);
       this.Response.setMessage("修改数据发生错误!");
       return;
     }
     Paper.setValue(this.Request);
     Paper.setModifyTime(new Date());
     Paper.setModifyUser(User.getUserName());
     trans.add(Paper, 2);
 
     ZCCatalogSchema catalog = new ZCCatalogSchema();
     catalog.setID(Long.parseLong($V("NewspaperID")));
     catalog.fill();
     catalog.setValue(this.Request);
     catalog.setIndexTemplate($V("CoverTemplate"));
     catalog.setModifyUser(User.getUserName());
     catalog.setModifyTime(new Date());
     trans.add(catalog, 2);
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("修改数据发生错误!");
     }
   }
 
   public void del() {
     Transaction trans = new Transaction();
     String ID = $V("CatalogID");
 
     Catalog.deleteCatalog(trans, Long.parseLong(ID));
 
     ZCPaperSchema Paper = new ZCPaperSchema();
     Paper.setID(Long.parseLong(ID));
     Paper.fill();
 
     trans.add(Paper, 5);
 
     trans.add(new ZCPaperIssueSchema().query(new QueryBuilder(" where PaperID =?", ID)), 5);
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("删除期刊失败");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.Newspaper
 * JD-Core Version:    0.5.3
 */