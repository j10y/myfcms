 package com.zving.cms.pub;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.orm.SchemaColumn;
 import com.zving.framework.orm.SchemaUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringFormat;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZDCodeSchema;
 import java.io.File;
 import java.io.IOException;
 import java.io.Serializable;
 import java.net.URL;
 import java.util.ArrayList;
 import java.util.Enumeration;
 import org.apache.tools.zip.ZipFile;
 
 public class SiteTableRela
 {
   public static TableRela[] getRelas()
   {
     ArrayList list = new ArrayList();
     list.add(new TableRela("ZCAdvertisement", "PositionID", "ZCAdPosition", "ID", null, false));
     list.add(new TableRela("ZCAdVisitLog", "AdID", "ZCAdvertisement", "ID", null));
     list.add(new TableRela("ZCApproval", "ArticleID", "ZCArticle", "ID", null, false));
     list.add(new TableRela("ZCArticleLog", "ArticleID", "ZCArticle", "ID", null));
     list.add(new TableRela("ZCArticlePage", "ArticleID", "ZCArticle", "ID", null));
     list.add(new TableRela("ZCArticle", "CatalogID", "ZCCatalog", "ID", null, false));
 
     list.add(new TableRela("ZCAttachmentRela", "RelaID", "ZCArticle", "ID", null));
     list.add(new TableRela("ZCAudioRela", "RelaID", "ZCArticle", "ID", null));
     list.add(new TableRela("ZCImageRela", "RelaID", "ZCArticle", "ID", "RelaType='ArticleImage'"));
     list.add(new TableRela("ZCImageRela", "RelaID", "ZCImagePlayer", "ID", "RelaType='ImagePlayerImage'"));
     list.add(new TableRela("ZCVideoRela", "RelaID", "ZCArticle", "ID", null));
 
     list.add(new TableRela("ZCAttachmentRela", "ID", "ZCAttachment", "ID", null, false));
     list.add(new TableRela("ZCAudioRela", "ID", "ZCAudio", "ID", null, false));
     list.add(new TableRela("ZCImageRela", "ID", "ZCImage", "ID", null, false));
     list.add(new TableRela("ZCVideoRela", "ID", "ZCVideo", "ID", null, false));
 
     list.add(new TableRela("ZCAttachment", "CatalogID", "ZCCatalog", "ID", null, false));
     list.add(new TableRela("ZCAudio", "CatalogID", "ZCCatalog", "ID", null, false));
     list.add(new TableRela("ZCCatalog", "ParentID", "ZCCatalog", "ID", null, false));
     list.add(new TableRela("ZCVideo", "CatalogID", "ZCCatalog", "ID", null, false));
     list.add(new TableRela("ZCImage", "CatalogID", "ZCCatalog", "ID", null, false));
 
     list.add(new TableRela("ZCComment", "RelaID", "ZCArticle", "ID", null, false));
     list.add(new TableRela("ZCComment", "CatalogID", "ZCCatalog", "ID", null, false));
     list.add(new TableRela("ZCDeployJob", "ConfigID", "ZCDeployConfig", "ID", null, false));
     list.add(new TableRela("ZCDeployLog", "JobID", "ZCDeployJob", "ID", null, false));
 
     list.add(new TableRela("ZCLink", "LinkGroupID", "ZCLinkGroup", "ID", null, false));
     list.add(new TableRela("ZCMagazineIssue", "MagazineID", "ZCMagazine", "ID", null));
     list.add(new TableRela("ZCMagazineCatalogRela", "CatalogID", "ZCCatalog", "ID", null));
     list.add(new TableRela("ZCMagazineCatalogRela", "MagazineID", "ZCMagazine", "ID", null, false));
     list.add(new TableRela("ZCMagazineCatalogRela", "IssueID", "ZCMagazineIssue", "ID", null, false));
 
     list.add(new TableRela("ZCPageBlock", "CatalogID", "ZCCatalog", "ID", null, false));
     list.add(new TableRela("ZCPageBlockItem", "BlockID", "ZCPageBlock", "ID", null));
     list.add(new TableRela("ZCTemplateBlockRela", "BlockID", "ZCPageBlock", "ID", null, false));
 
     list.add(new TableRela("ZCVoteSubject", "VoteID", "ZCVote", "ID", null));
     list.add(new TableRela("ZCVoteItem", "VoteID", "ZCVote", "ID", null));
     list.add(new TableRela("ZCVoteItem", "SubjectID", "ZCVoteSubject", "ID", null, false));
     list.add(new TableRela("ZCVoteLog", "VoteID", "ZCVote", "ID", null));
 
     list.add(new TableRela("ZDColumnRela", "RelaID", "ZCCatalog", "ID", "RelaType='0'"));
     list.add(new TableRela("ZDColumnRela", "RelaID", "ZCArticle", "ID", "RelaType='2'"));
     list.add(new TableRela("ZDColumnRela", "ColumnID", "ZDColumn", "ID", null, false));
     list.add(new TableRela("ZDColumnValue", "RelaID", "ZCCatalog", "ID", "RelaType='0'"));
     list.add(new TableRela("ZDColumnValue", "RelaID", "ZCArticle", "ID", "RelaType='2'"));
     list.add(new TableRela("ZDColumnValue", "ColumnID", "ZDColumn", "ID", null, false));
 
     list.add(new TableRela("ZDSchedule", "SourceID", "ZCFullText", "ID", "TypeCode='IndexMaintenance'"));
 
     list.add(new TableRela("ZDSchedule", "SourceID", "ZCWebGather", "ID", "TypeCode='WebCrawl'"));
     list.add(new TableRela("ZCCustomTable", "DatabaseID", "ZCDatabase", "ID", null, false));
     list.add(new TableRela("ZCCustomTableColumn", "TableID", "ZCCustomTable", "ID", null));
 
     list.add(new TableRela("ZCArticle", "CatalogInnerCode", "ZCCatalog", "InnerCode", null, false));
     list.add(new TableRela("ZCImage", "CatalogInnerCode", "ZCCatalog", "InnerCode", null, false));
     list.add(new TableRela("ZCVideo", "CatalogInnerCode", "ZCCatalog", "InnerCode", null, false));
     list.add(new TableRela("ZCAttachment", "CatalogInnerCode", "ZCCatalog", "InnerCode", null, false));
     list.add(new TableRela("ZCAudio", "CatalogInnerCode", "ZCCatalog", "InnerCode", null, false));
     list.add(new TableRela("ZCArticle", "CatalogInnerCode", "ZCCatalog", "InnerCode", null, false));
 
     list.add(new TableRela("ZCCatalogConfig", "CatalogID", "ZCCatalog", "ID", null, false));
     list.add(new TableRela("ZCCatalog", "Parent", "ZCCatalog", "ID", null, false));
 
     TableRela[] trs = new TableRela[list.size()];
     for (int i = 0; i < list.size(); ++i) {
       trs[i] = ((TableRela)list.get(i));
     }
     return trs;
   }
 
   public static NoType[] getNoRelaArray()
   {
     ArrayList list = new ArrayList();
     list.add(new NoType("ZDColumnValue", "ID", "ColumnValueID"));
     list.add(new NoType("ZCArticleLog", "ID", "ArticleLogID"));
     list.add(new NoType("ZCArticle", "ID", "DocID"));
 
     list.add(new NoType("ZCMessage", "ID", "MessageID"));
     list.add(new NoType("ZCDeployJob", "ID", "DeployJobID"));
     list.add(new NoType("ZCDeployLog", "ID", "DeployLogID"));
     list.add(new NoType("ZCDeployConfig", "ID", "DeployConfigID"));
 
     list.add(new NoType("ZCAdvertisement", "ID", "AdvertiseID"));
     list.add(new NoType("ZCAdPosition", "ID", "AdPositionID"));
     list.add(new NoType("ZDColumn", "ID", "ColumnID"));
 
     list.add(new NoType("ZCCustomTable", "ID", "CustomTableID"));
     list.add(new NoType("ZCFullText", "ID", "FullTextID"));
 
     list.add(new NoType("ZDMemberLevel", "ID", "MembeLevelID"));
     list.add(new NoType("ZCDatabase", "ID", "DatabaseID"));
 
     list.add(new NoType("ZCVote", "ID", "VoteID"));
     list.add(new NoType("ZCVoteSubject", "ID", "VoteSubjectID"));
     list.add(new NoType("ZCNotes", "ID", "NotesID"));
 
     list.add(new NoType("ZCBadWord", "ID", "BadWordID"));
     list.add(new NoType("ZCCustomTableColumn", "ID", "CustomTableColumnID"));
     list.add(new NoType("ZDSchedule", "ID", "ScheduleID"));
 
     list.add(new NoType("ZCVoteItem", "ID", "VoteItemID"));
     list.add(new NoType("ZDColumnRela", "ID", "ColumnRelaID"));
     list.add(new NoType("ZCImagePlayer", "ID", "ImagePlayerID"));
 
     list.add(new NoType("ZCLink", "ID", "LinkID"));
     list.add(new NoType("ZCLinkGroup", "ID", "LinkGroupID"));
     list.add(new NoType("ZCPageBlock", "ID", "PageBlockID"));
 
     list.add(new NoType("ZCPageBlockItem", "ID", "PageBlockID"));
     list.add(new NoType("ZCSite", "ID", "SiteID"));
     list.add(new NoType("ZCImage", "ID", "DocID"));
 
     list.add(new NoType("ZCAttachment", "ID", "DocID"));
     list.add(new NoType("ZCAudio", "ID", "DocID"));
     list.add(new NoType("ZCVideo", "ID", "DocID"));
 
     list.add(new NoType("ZCWebGather", "ID", "GatherID"));
     list.add(new NoType("ZDUserLog", "ID", "LogID"));
     list.add(new NoType("ZDMenu", "ID", "MenuID"));
 
     list.add(new NoType("ZWWorkflowDef", "ID", "WorkflowDefID"));
     list.add(new NoType("ZCQuestion", "ID", "QuestionID"));
     list.add(new NoType("ZWWorkflowEntry", "ID", "WorkflowEntryID"));
 
     list.add(new NoType("ZCComment", "ID", "CommentID"));
     list.add(new NoType("ZCCatalog", "ID", "CatalogID"));
     list.add(new NoType("ZCCatalog", "InnerCode", "CatalogInnerCode"));
 
     list.add(new NoType("ZCForum", "ID", "ForumID"));
     list.add(new NoType("ZCPost", "ID", "PostID"));
     list.add(new NoType("ZCTheme", "ID", "ThemeID"));
 
     list.add(new NoType("ZCForumGroup", "ID", "ForumGroupID"));
     list.add(new NoType("ZCForumConfig", "ID", "ForumID"));
     list.add(new NoType("ZCForumAttachment", "ID", "ForumAttachmentID"));
     list.add(new NoType("ZCForum", "ID", "ForumID"));
 
     list.add(new NoType("ZCPaper", "ID", "CatalogID"));
     list.add(new NoType("ZCPaperIssue", "ID", "CatalogID"));
     list.add(new NoType("ZCPaperPage", "ID", "CatalogID"));
     list.add(new NoType("ZCMagazine", "ID", "CatalogID"));
     list.add(new NoType("ZCMagazineIssue", "ID", "CatalogID"));
     list.add(new NoType("ZCMessageBoard", "ID", "MessageBoardID"));
 
     list.add(new NoType("ZCVoteLog", "ID", "VoteLogID"));
     list.add(new NoType("ZCCatalogConfig", "ID", "PublishConfigID"));
 
     NoType[] arr = new NoType[list.size()];
     for (int i = 0; i < list.size(); ++i) {
       arr[i] = ((NoType)list.get(i));
     }
     return arr;
   }
 
   public static String[] getSiteIDTables()
   {
     ArrayList list = new ArrayList();
     list.add("ZCCatalog");
     list.add("ZCAdPosition");
     list.add("ZCAdvertisement");
     list.add("ZCArticle");
     list.add("ZCAttachment");
     list.add("ZCAudio");
     list.add("ZCComment");
     list.add("ZCDatabase");
     list.add("ZCCustomTable");
     list.add("ZCDeployConfig");
     list.add("ZCDeployJob");
     list.add("ZCDeployLog");
     list.add("ZCForumAttachment");
     list.add("ZCForum");
     list.add("ZCForumConfig");
     list.add("ZCForumGroup");
     list.add("ZCForumMember");
     list.add("ZCForumScore");
     list.add("ZCFullText");
     list.add("ZCImagePlayer");
     list.add("ZCImage");
     list.add("ZCJsFile");
     list.add("ZCKeywordType");
     list.add("ZCLinkGroup");
     list.add("ZCLink");
     list.add("ZCMagazine");
     list.add("ZCMessageBoard");
     list.add("ZCPageBlock");
     list.add("ZCPaper");
     list.add("ZCPost");
     list.add("ZCCatalogConfig");
     list.add("ZCStatItem");
     list.add("ZCTag");
     list.add("ZCTemplateBlockRela");
     list.add("ZCTemplate");
     list.add("ZCTemplateTagRela");
     list.add("ZCTheme");
     list.add("ZCVideo");
     list.add("ZCVisitLog");
     list.add("ZCVote");
     list.add("ZCWebGather");
     list.add("ZDColumn");
     list.add("ZDMember");
     String[] arr = new String[list.size()];
     for (int i = 0; i < list.size(); ++i) {
       arr[i] = ((String)list.get(i));
     }
     return arr;
   }
 
   public static String[] getSiteIDTablesBySchema()
   {
     ArrayList list = new ArrayList();
     String path = ZDCodeSchema.class.getResource("ZDCodeSchema.class").getPath();
     if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
       if (path.startsWith("/"))
         path = path.substring(1);
       else if (path.startsWith("file:/")) {
         path = path.substring(6);
       }
     }
     else if (path.startsWith("file:/")) {
       path = path.substring(5);
     }
 
     path = path.replaceAll("%20", " ");
     if (path.toLowerCase().indexOf(".jar!") > 0) {
       try {
         path = path.substring(0, path.indexOf(".jar!") + 4);
         ZipFile z = new ZipFile(path);
         Enumeration all = z.getEntries();
         while (all.hasMoreElements()) {
           String name = all.nextElement().toString();
           if (name.startsWith("com.zving.schema.")) {
             name = name.substring("com.zving.schema.".length(), name.lastIndexOf(".") + 1);
             name = name.substring(0, name.indexOf("Schema"));
             if (hasSiteIDField(name))
               list.add(name);
           }
         }
       }
       catch (IOException e) {
         e.printStackTrace();
         return null;
       }
     } else {
       File p = new File(path.substring(0, path.toLowerCase().indexOf("zdcodeschema.class")));
       File[] fs = p.listFiles();
       for (int i = 0; i < fs.length; ++i) {
         String name = fs[i].getName();
         if ((name.endsWith("Schema.class")) && (!(name.startsWith("B")))) {
           name = name.substring(0, name.length() - "Schema.class".length());
           if (hasSiteIDField(name)) {
             list.add(name);
           }
         }
       }
     }
     String[] names = new String[list.size()];
     for (int i = 0; i < names.length; ++i) {
       names[i] = list.get(i).toString();
     }
     return names;
   }
 
   public static boolean hasSiteIDField(String tableName)
   {
     if (tableName.equalsIgnoreCase("ZCAdminGroup"))
       return false;
     try
     {
       Schema schema = (Schema)Class.forName("com.zving.schema." + tableName + "Schema").newInstance();
       SchemaColumn[] scs = SchemaUtil.getColumns(schema);
       boolean flag = false;
       for (int i = 0; i < scs.length; ++i) {
         String name = scs[i].getColumnName();
         if (name.equalsIgnoreCase("SiteID")) {
           flag = true; } else {
           if (!(name.endsWith("ID"))) continue; name.equals("ID");
         }
       }
 
       return flag;
     } catch (Exception e) {
       e.printStackTrace();
     }
     return false;
   }
 
   public static void deleteRelated(Schema schema, Transaction tran)
   {
     String tableName = SchemaUtil.getTableCode(schema);
     TableRela[] trs = getRelas();
     for (int i = 0; i < trs.length; ++i) {
       TableRela tr = trs[i];
       if (tr.RelaTable.equalsIgnoreCase(tableName)) {
         Object o = schema.toMapx(true).get(tr.RelaTable.toLowerCase());
         StringFormat sf = new StringFormat("delete from ? where ?=?");
         sf.add(tr.TableCode);
         sf.add(tr.KeyField);
         sf.add(o);
         QueryBuilder qb = new QueryBuilder(sf.toString());
         tran.add(qb);
       }
     }
   }
 
   public static QueryBuilder getSelectQuery(long SiteID, TableRela tr)
   {
     StringFormat sf = new StringFormat(
       "select * from ? where ? and exists (select 1 from ? where SiteID=? and ?=?.?)");
     sf.add(tr.TableCode);
     if (StringUtil.isNotEmpty(tr.Condition))
       sf.add(tr.Condition);
     else {
       sf.add("1=1");
     }
     sf.add(tr.RelaTable);
     sf.add(SiteID);
     sf.add(tr.RelaField);
     sf.add(tr.TableCode);
     sf.add(tr.KeyField);
     QueryBuilder qb = new QueryBuilder(sf.toString());
     return qb;
   }
 
   public static QueryBuilder getDeleteQuery(long SiteID, TableRela tr)
   {
     StringFormat sf = new StringFormat(
       "delete from ? where ? and exists (select 1 from ? where SiteID=? and ?=?.?)");
     sf.add(tr.TableCode);
     sf.add(tr.Condition);
     sf.add(tr.RelaTable);
     sf.add(SiteID);
     sf.add(tr.RelaField);
     sf.add(tr.TableCode);
     sf.add(tr.KeyField);
     QueryBuilder qb = new QueryBuilder(sf.toString());
     return qb;
   }
 
   public static QueryBuilder getSelectQuery(long SiteID, String TableName)
   {
     StringFormat sf = new StringFormat("select * from ? where SiteID=?");
     sf.add(TableName);
     sf.add(SiteID);
     QueryBuilder qb = new QueryBuilder(sf.toString());
     return qb;
   }
 
   public static QueryBuilder getDeleteQuery(long SiteID, String TableName)
   {
     StringFormat sf = new StringFormat("delete from ? where SiteID=?");
     sf.add(TableName);
     sf.add(SiteID);
     QueryBuilder qb = new QueryBuilder(sf.toString());
     return qb;
   }
 
   public static void clearData()
   {
     Transaction tran = new Transaction();
     String[] tables = getSiteIDTables();
     for (int i = 0; i < tables.length; ++i) {
       QueryBuilder qb = new QueryBuilder("delete from " + tables[i] + 
         " where SiteID not in (select ID from ZCSite)");
       tran.add(qb);
     }
     TableRela[] trs = getRelas();
     for (int i = 0; i < trs.length; ++i) {
       TableRela tr = trs[i];
       if (tr.isExportData) {
         StringFormat sf = new StringFormat("delete from ? where ? and not exists (select 1 from ? where ?=?.?)");
         sf.add(tr.TableCode);
         if (StringUtil.isNotEmpty(tr.Condition))
           sf.add(tr.Condition);
         else {
           sf.add("1=1");
         }
         sf.add(tr.RelaTable);
         sf.add(tr.RelaField);
         sf.add(tr.TableCode);
         sf.add(tr.KeyField);
         QueryBuilder qb = new QueryBuilder(sf.toString());
         tran.add(qb);
       }
     }
     tran.commit();
   }
 
   public static void clearAllBackupData()
   {
     String path = ZDCodeSchema.class.getResource("ZDCodeSchema.class").getPath();
     if (System.getProperty("os.name").toLowerCase().indexOf("windows") >= 0) {
       if (path.startsWith("/"))
         path = path.substring(1);
       else if (path.startsWith("file:/")) {
         path = path.substring(6);
       }
     }
     else if (path.startsWith("file:/")) {
       path = path.substring(5);
     }
 
     path = path.replaceAll("%20", " ");
     File p = new File(path.substring(0, path.toLowerCase().indexOf("zdcodeschema.class")));
     File[] fs = p.listFiles();
     for (int i = 0; i < fs.length; ++i) {
       String name = fs[i].getName();
       if ((name.endsWith("Schema.class")) && (name.startsWith("B"))) {
         name = name.substring(0, name.length() - "Schema.class".length());
         QueryBuilder qb = new QueryBuilder("truncate table " + name);
         try {
           qb.executeNoQuery();
         } catch (Exception e) {
           e.printStackTrace();
         }
       }
     }
   }
 
   public static void main(String[] args) {
     clearAllBackupData();
   }
 
   public static class NoType
   {
     public String TableCode;
     public String FieldName;
     public String NoType;
 
     public NoType(String TableCode, String FieldName, String NoType)
     {
       this.TableCode = TableCode;
       this.FieldName = FieldName;
       this.NoType = NoType;
     }
   }
 
   public static class TableRela
     implements Serializable
   {
     private static final long serialVersionUID = 1L;
     public String TableCode;
     public String KeyField;
     public String RelaTable;
     public String RelaField;
     public String Condition;
     public boolean isExportData;
 
     public TableRela(String TableCode, String KeyField, String RelaTable, String RelaField, String Condition, boolean isExportData)
     {
       this.TableCode = TableCode;
       this.KeyField = KeyField;
       this.RelaTable = RelaTable;
       this.RelaField = RelaField;
       this.Condition = Condition;
       this.isExportData = isExportData;
     }
 
     public TableRela(String TableCode, String KeyField, String RelaTable, String RelaField, String Condition)
     {
       this(TableCode, KeyField, RelaTable, RelaField, Condition, true);
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.pub.SiteTableRela
 * JD-Core Version:    0.5.3
 */