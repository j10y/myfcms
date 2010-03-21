 package com.zving.cms.site;
 
 import com.zving.cms.datachannel.Publisher;
 import com.zving.cms.pub.CatalogUtil;
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
 import com.zving.framework.messages.LongTimeTask;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.schema.ZCCatalogSchema;
 import com.zving.schema.ZCMagazineCatalogRelaSchema;
 import com.zving.schema.ZCMagazineIssueSchema;
 import com.zving.schema.ZCMagazineSchema;
 import java.util.Date;
 import java.util.List;
 
 public class Magazine extends Page
 {
   public static void treeDataBind(TreeAction ta)
   {
     String siteID = Application.getCurrentSiteID();
     int catalogType = 3;
     String parentLevel = (String)ta.getParams().get("ParentLevel");
     String parentID = (String)ta.getParams().get("ParentID");
     DataTable dt = null;
     QueryBuilder qb;
     if (ta.isLazyLoad()) {
       qb = new QueryBuilder("select ID,ParentID,TreeLevel,Name from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel>? and innerCode like ?");
       qb.add(catalogType);
       qb.add(siteID);
       qb.add(parentLevel);
       qb.add(CatalogUtil.getInnerCode(parentID) + "%");
       dt = qb.executeDataTable();
     } else {
       qb = new QueryBuilder("select ID,ParentID,TreeLevel,Name from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel-1 <=?");
       qb.add(catalogType);
       qb.add(siteID);
       qb.add(ta.getLevel());
       dt = qb.executeDataTable();
     }
 
     String siteName = "期刊库";
 
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
       if (item.getLevel() == 1) {
         item.setIcon("Icons/icon008a19.gif");
       }
       if (item.getLevel() == 2)
         item.setIcon("Icons/icon_magazaine.gif");
     }
   }
 
   public static Mapx initDialog(Mapx params)
   {
     Object o1 = params.get("MagazineID");
     if (o1 != null) {
       long ID = Long.parseLong(o1.toString());
       ZCMagazineSchema magazine = new ZCMagazineSchema();
       magazine.setID(ID);
       if (magazine.fill()) {
         Mapx map = magazine.toMapx();
         String imagePath = PubFun.getImagePath((String)map.get("CoverImage"));
         if (imagePath == null)
           imagePath = "../Images/nopicture.jpg";
         else {
           imagePath = Config.getContextPath() + Config.getValue("UploadDir") + "/" + 
             SiteUtil.getAlias(magazine.getSiteID()) + "/" + imagePath;
         }
 
         map.put("PicSrc", imagePath);
         return map;
       }
       return null;
     }
     params.put("SiteID", Application.getCurrentSiteID());
     params.put("PicSrc", "../Images/nocover.jpg");
     params.put("CoverTemplate", "/template/magazine.html");
     return params;
   }
 
   public static Mapx initIssue(Mapx params)
   {
     String sql = "select concat(year,'年',periodNum,'期') as Name,ID from zcmagazineissue where MagazineID=(select min(id) from zcmagazine where siteid=" + 
       Application.getCurrentSiteID() + 
       ") order by id desc";
     DataTable dt1 = new QueryBuilder(sql).executeDataTable();
     params.put("optionIssue", HtmlUtil.dataTableToOptions(dt1));
     return params;
   }
 
   public static Mapx init(Mapx params) {
     Object o1 = params.get("MagazineID");
     if (o1 != null) {
       long ID = Long.parseLong(o1.toString());
       ZCMagazineSchema magazine = new ZCMagazineSchema();
       magazine.setID(ID);
       magazine.fill();
 
       Mapx map = magazine.toMapx();
       return map;
     }
     return params;
   }
 
   public void add()
   {
     Transaction trans = new Transaction();
     Catalog catalog = new Catalog();
     long catalogID = catalog.add(this.Request, trans).getID();
 
     ZCMagazineSchema magazine = new ZCMagazineSchema();
     magazine.setID(catalogID);
     magazine.setValue(this.Request);
     magazine.setAddTime(new Date());
     magazine.setAddUser(User.getUserName());
     trans.add(magazine, 1);
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("插入数据发生错误!");
     }
   }
 
   public void edit() {
     Transaction trans = new Transaction();
     ZCMagazineSchema magazine = new ZCMagazineSchema();
     magazine.setID(Long.parseLong($V("MagazineID")));
     if (!(magazine.fill())) {
       this.Response.setStatus(0);
       this.Response.setMessage("修改数据发生错误!");
       return;
     }
     magazine.setValue(this.Request);
     magazine.setModifyTime(new Date());
     magazine.setModifyUser(User.getUserName());
     trans.add(magazine, 2);
 
     ZCCatalogSchema catalog = new ZCCatalogSchema();
     catalog.setID(Long.parseLong($V("MagazineID")));
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
 
     ZCMagazineSchema magazine = new ZCMagazineSchema();
     magazine.setID(Long.parseLong(ID));
     magazine.fill();
 
     trans.add(magazine, 5);
 
     trans.add(new ZCMagazineIssueSchema().query(new QueryBuilder(" where magazineID =?", ID)), 5);
 
     trans.add(new ZCMagazineCatalogRelaSchema().query(new QueryBuilder(" where magazineID =?", ID)), 5);
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("删除期刊失败");
     }
   }
 
   public void publish()
   {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
       return;
     }
     if ((ids.indexOf("\"") >= 0) || (ids.indexOf("'") >= 0)) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
 
     String[] arr = ids.split(",");
 
     for (int i = 0; i < arr.length; ++i) {
       String temp = arr[i];
       long catalogID = Long.parseLong(temp);
       long id = publishTask(catalogID, true, true);
       this.Response.setStatus(1);
       $S("TaskID", id);
     }
   }
 
   private long publishTask(long catalogID, boolean child, boolean detail) {
     LongTimeTask ltt = new LongTimeTask(catalogID, child, detail) { private final long val$catalogID;
       private final boolean val$child;
       private final boolean val$detail;
 
       public void execute() { Publisher p = new Publisher();
         p.publishCatalog(this.val$catalogID, this.val$child, this.val$detail, this);
         setPercent(100);
       }
     };
     ltt.setUser(User.getCurrent());
     ltt.start();
     return ltt.getTaskID();
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.Magazine
 * JD-Core Version:    0.5.3
 */