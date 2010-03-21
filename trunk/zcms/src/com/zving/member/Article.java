 package com.zving.member;
 
 import com.zving.cms.dataservice.ColumnUtil;
 import com.zving.cms.pub.CatalogUtil;
 import com.zving.framework.Ajax;
 import com.zving.framework.Config;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataListAction;
 import com.zving.framework.controls.TreeAction;
 import com.zving.framework.data.DataColumn;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCArticleSchema;
 import com.zving.schema.ZCAttachmentRelaSchema;
 import com.zving.schema.ZCAttachmentSchema;
 import java.util.Date;
 
 public class Article extends Ajax
 {
   public static Mapx init(Mapx params)
   {
     String UserName = User.getUserName();
     String ID = params.getString("ID");
     String CatalogID = "";
     String MemberType = "";
     String CatalogName = "";
     if (StringUtil.isNotEmpty(UserName)) {
       params.put("UserName", UserName);
       Member m = new Member(UserName);
       m.fill();
       MemberType = new QueryBuilder("select CodeName from ZDCode where CodeType='Member.Type' and CodeValue='" + m.getType() + "'").executeString();
       params.put("MemberType", MemberType);
       params.put("Status", m.getStatus());
       if (StringUtil.isNotEmpty(ID)) {
         ZCArticleSchema article = new ZCArticleSchema();
         article.setID(ID);
         if (article.fill()) {
           CatalogID = article.getCatalogID();
           CatalogName = CatalogUtil.getName(CatalogID);
           String AttachIDs = "";
           String AttachNames = "";
           DataTable dt = new QueryBuilder("select ID from ZCAttachmentRela where RelaID = " + article.getID() + " and RelaType = 'MemberArticle'").executeDataTable();
           if ((dt != null) && (dt.getRowCount() > 0)) {
             ZCAttachmentSchema attach = new ZCAttachmentSchema();
             for (int i = 0; i < dt.getRowCount(); ++i) {
               attach = new ZCAttachmentSchema();
               attach.setID(dt.getString(i, 0));
               if (attach.fill()) {
                 AttachIDs = AttachIDs + attach.getID();
                 AttachNames = AttachNames + attach.getOldName();
               }
               if (i != dt.getRowCount() - 1) {
                 AttachIDs = AttachIDs + ",";
                 AttachNames = AttachNames + ",";
               }
             }
           }
           params.put("Catalog", CatalogID);
           params.put("CatalogName", CatalogName);
           params.put("AttachIDs", AttachIDs);
           params.put("AttachNames", AttachNames);
           params.put("ArticleID", article.getID());
           params.put("Title", article.getTitle());
           params.put("Content", article.getContent());
           params.put("CustomColumn", ColumnUtil.getHtml("1", CatalogID, "2", article.getID(), "hidden"));
         }
       } else {
         params.put("CustomColumn", ColumnUtil.getHtml("1", CatalogID, "hidden"));
       }
     }
     return params;
   }
 
   public static Mapx initSiteID(Mapx params) {
     String UserName = User.getUserName();
     Member member = new Member(UserName);
     member.fill();
     String siteID = member.getSiteID();
     if ((StringUtil.isEmpty(siteID)) || (siteID.equals("0"))) {
       siteID = new QueryBuilder("select ID from ZCSite order by AddTime desc").executeOneValue();
     }
     params.put("SiteID", siteID);
     return params;
   }
 
   public static void initCatalogTree(TreeAction ta) {
     String UserName = User.getUserName();
     Member member = new Member(UserName);
     member.fill();
     String siteID = member.getSiteID();
     if (StringUtil.isEmpty(siteID)) {
       siteID = new QueryBuilder("select ID from ZCSite order by AddTime desc").executeOneValue();
     }
     int catalogType = 1;
     DataTable dt = null;
     QueryBuilder qb = new QueryBuilder("select ID,ParentID,TreeLevel,Name,SingleFlag,InnerCode,OrderFlag from ZCCatalog Where Type = ? and SiteID = ? and TreeLevel-1 <=? and AllowContribute = 'Y' order by orderflag,innercode");
 
     qb.add(catalogType);
     qb.add(siteID);
     qb.add(ta.getLevel());
     dt = qb.executeDataTable();
     ta.setRootText("投稿文章栏目");
     ta.bindData(dt);
   }
 
   public void doSave() {
     String UserName = $V("UserName");
     String ArticleID = $V("ArticleID");
     String CatalogID = $V("Catalog");
     String AttachIDs = $V("AttachIDs");
     String Content = $V("Content");
     ZCArticleSchema article = new ZCArticleSchema();
     Member member = new Member(UserName);
     member.fill();
     String SiteID = member.getSiteID();
     if (StringUtil.isEmpty(SiteID)) {
       SiteID = new QueryBuilder("select ID from ZCSite order by AddTime desc").executeOneValue();
     }
     boolean flag = false;
     Transaction trans = new Transaction();
     if (StringUtil.isEmpty(ArticleID)) {
       flag = true;
       String CatalogInnerCode = CatalogUtil.getInnerCode(CatalogID);
       article.setID(NoUtil.getMaxID("DocID"));
       article.setCatalogID(CatalogID);
       article.setCatalogInnerCode(CatalogInnerCode);
       article.setSiteID(SiteID);
       if (StringUtil.isNotEmpty(member.getName()))
         article.setAuthor(member.getName());
       else {
         article.setAuthor(member.getUserName());
       }
       article.setType("1");
       article.setTopFlag("0");
       article.setTemplateFlag("0");
       article.setOrderFlag("0");
       article.setCommentFlag("0");
       article.setStatus(20L);
       article.setHitCount(0L);
       article.setStickTime(0L);
       article.setPublishFlag("0");
       article.setAddTime(new Date());
       article.setAddUser(User.getUserName());
       article.setProp4("Member");
     } else {
       article.setID(ArticleID);
       article.fill();
     }
     article.setTitle($V("Title"));
     if (Content.indexOf("<!-- Attach -->") != -1) {
       Content = Content.substring(0, Content.indexOf("<!-- Attach -->"));
     }
 
     new QueryBuilder("delete from ZCAttachmentrela where RelaID = " + article.getID() + " and RelaType = 'MemberArticle'").executeNoQuery();
     if (StringUtil.isNotEmpty(AttachIDs)) {
       Content = Content + "<!-- Attach -->";
       Content = Content + "<div id='attach'>";
       String[] AttachArr = AttachIDs.split(",");
       ZCAttachmentSchema attach = new ZCAttachmentSchema();
       ZCAttachmentRelaSchema attachrela = new ZCAttachmentRelaSchema();
       for (int i = 0; i < AttachArr.length; ++i) {
         attach = new ZCAttachmentSchema();
         attachrela = new ZCAttachmentRelaSchema();
         attach.setID(AttachArr[i]);
         if (attach.fill()) {
           Content = Content + "<a href='#;' onClick=\"javascript:window.open('" + Config.getContextPath() + "Services/AttachDownLoad.jsp?id=" + attach.getID() + "&SiteID=" + SiteID + "', '_blank')" + "\">下载附件：" + attach.getOldName() + "</a><br/>";
           attachrela.setID(attach.getID());
           attachrela.setRelaID(article.getID());
           attachrela.setRelaType("MemberArticle");
           trans.add(attachrela, 1);
         }
       }
       Content = Content + "</div>";
     }
     article.setContent(Content);
 
     if (flag) {
       trans.add(ColumnUtil.getValueFromRequest(Long.parseLong(CatalogID), article.getID(), this.Request), 1);
       trans.add(article, 1);
     } else {
       DataTable columnDT = ColumnUtil.getColumnValue("2", ArticleID);
       if (columnDT.getRowCount() > 0) {
         trans.add(new QueryBuilder("delete from zdcolumnvalue where relatype=? and relaid = ?", "2", ArticleID));
       }
       trans.add(ColumnUtil.getValueFromRequest(Long.parseLong(CatalogID), Long.parseLong(ArticleID), this.Request), 1);
       trans.add(article, 2);
     }
     if (trans.commit())
       this.Response.setLogInfo(1, "发表成功");
     else
       this.Response.setLogInfo(0, "发生错误");
   }
 
   public static void dg1DataList(DataListAction dla)
   {
     String sql = "select ID,CatalogID as Catalog,Title,Addtime,Status from ZCArticle where Prop4 = 'Member' and AddUser = '" + User.getUserName() + "'";
     String sql2 = "select count(*) from ZCArticle where Prop4 = 'Member' and AddUser = '" + User.getUserName() + "'";
     QueryBuilder qb = new QueryBuilder();
     qb.setSQL(sql2);
     dla.setTotal(qb);
 
     qb.setSQL(sql);
     DataTable dt = qb.executePagedDataTable(dla.getPageSize(), dla.getPageIndex());
     dt.insertColumn("DO");
     dt.insertColumn("CatalogName");
     if ((dt != null) && (dt.getRowCount() > 0)) {
       dt.decodeColumn("Status", com.zving.cms.document.Article.STATUS_MAP);
       dt.getDataColumn("AddTime").setDateFormat("yyyy-MM-dd HH:mm");
     }
     for (int i = 0; i < dt.getRowCount(); ++i) {
       dt.set(i, "CatalogName", CatalogUtil.getName(dt.getString(i, "Catalog")));
       if (dt.getString(i, "StatusName").equals("已发布"))
         dt.set(i, "DO", "<cite><a href='#;' onclick='preview(" + dt.getString(i, "ID") + ");'>浏览</a></cite>");
       else {
         dt.set(i, "DO", "<cite><a href='#;' onclick='preview(" + dt.getString(i, "ID") + ");'>预览</a></cite>&nbsp;<cite><a href='WriteArticle.jsp?ID=" + dt.getString(i, "ID") + "'>修改</a></cite>&nbsp;<em><a href='#;' onclick='del(" + dt.getString(i, "ID") + ")'>删除</a></em>");
       }
     }
     dla.bindData(dt);
   }
 
   public void del() {
     String ID = $V("ID");
     if (StringUtil.isNotEmpty(ID)) {
       ZCArticleSchema article = new ZCArticleSchema();
       article.setID(ID);
       article.fill();
       if (article.deleteAndBackup())
         this.Response.setLogInfo(1, "删除成功");
       else
         this.Response.setLogInfo(0, "删除失败");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.member.Article
 * JD-Core Version:    0.5.3
 */