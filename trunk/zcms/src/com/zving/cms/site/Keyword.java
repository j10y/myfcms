 package com.zving.cms.site;
 
 import com.zving.framework.Config;
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.FileUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCKeywordSchema;
 import com.zving.schema.ZCKeywordSet;
 import java.util.Date;
 
 public class Keyword extends Page
 {
   public static Mapx KeyWordMap = new Mapx();
 
   static {
     updateCache();
   }
 
   private static void updateCache() {
     DataTable dt = new QueryBuilder("select distinct siteID from ZCKeyWord").executeDataTable();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       ZCKeywordSchema keyword = new ZCKeywordSchema();
       QueryBuilder qb = new QueryBuilder("where siteID=? order by length(KeyWord) desc", dt.getString(i, 0));
       if (Config.isSQLServer())
         qb.setSQL("where siteID=? order by len(KeyWord) desc");
       else {
         qb.setSQL("where siteID=? order by length(KeyWord) desc");
       }
       ZCKeywordSet set = keyword.query(qb);
       KeyWordMap.put(dt.getString(i, 0), set);
     }
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     String word = dga.getParam("Word");
     String siteID = Application.getCurrentSiteID();
     String wherePart = "";
     if (StringUtil.isNotEmpty(word)) {
       wherePart = wherePart + " and KeyWord like '%" + word.trim() + "%";
     }
 
     String sql = "select ID,Keyword,LinkURL,LinkAlt,LinkTarget,addTime from ZCKeyword where 1=1 " + wherePart;
     sql = sql + "and SiteId=" + siteID + " ";
     if (StringUtil.isNotEmpty(dga.getSortString()))
       sql = sql + dga.getSortString();
     else {
       sql = sql + " Order by ID desc";
     }
     String sql2 = "select count(*) from ZCKeyword where 1=1 " + wherePart;
     sql2 = sql2 + "and SiteId=" + siteID + " ";
     dga.setTotal(new QueryBuilder(sql2));
     dga.bindData(new QueryBuilder(sql));
   }
 
   public void dg1Edit() {
     DataTable dt = (DataTable)this.Request.get("DT");
     ZCKeywordSet set = new ZCKeywordSet();
     for (int i = 0; i < dt.getRowCount(); ++i) {
       ZCKeywordSchema keyword = new ZCKeywordSchema();
       keyword.setID(Integer.parseInt(dt.getString(i, "ID")));
       keyword.fill();
       keyword.setValue(dt.getDataRow(i));
       keyword.setModifyTime(new Date());
       keyword.setModifyUser(User.getUserName());
 
       set.add(keyword);
     }
     if (set.update()) {
       updateCache();
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("发生错误!");
     }
   }
 
   public static Mapx init(Mapx params) {
     return null;
   }
 
   public void add() {
     String KeyWord = $V("Keyword").trim();
     if (new QueryBuilder("select count(*) from ZCKeyWord where SiteID=? and Keyword=?", 
       Application.getCurrentSiteID(), KeyWord).executeInt() == 0) {
       ZCKeywordSchema keyword = new ZCKeywordSchema();
       keyword.setID(NoUtil.getMaxID("KeywordID"));
       keyword.setKeyword(KeyWord);
       keyword.setSiteId(Application.getCurrentSiteID());
       keyword.setLinkUrl($V("LinkURL"));
       keyword.setLinkTarget($V("LinkTarget"));
 
       keyword.setLinkAlt($V("LinkAlt"));
 
       keyword.setAddTime(new Date());
       keyword.setAddUser(User.getUserName());
       if (keyword.insert()) {
         updateCache();
         this.Response.setStatus(1);
         this.Response.setMessage("新增成功！!");
       } else {
         this.Response.setStatus(0);
         this.Response.setMessage("发生错误!");
       }
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("已经存在的关键词!");
     }
   }
 
   public void importWords()
   {
     String FilePath = $V("FilePath");
     String Words = $V("KeyWords");
     long SiteID = Application.getCurrentSiteID();
     String wordsText = "";
     if (StringUtil.isNotEmpty(FilePath)) {
       FilePath = FilePath.replaceAll("//", "/");
       wordsText = FileUtil.readText(FilePath);
     } else {
       wordsText = Words;
     }
     String[] keyWords = wordsText.split("\n");
     String temp = "";
     Transaction trans = new Transaction();
     ZCKeywordSchema keyword = new ZCKeywordSchema();
     for (int i = 0; i < keyWords.length; ++i) {
       if ((!(keyWords[i].equals("\r"))) && (!(StringUtil.isEmpty(keyWords[i])))) {
         temp = keyWords[i];
         temp = temp.trim().replaceAll("\\s+", ",");
         temp = temp.replaceAll("，", ",");
         String[] word = StringUtil.splitEx(temp, ",");
         if ((word.length == 0) || (word.length > 4)) continue; if (StringUtil.isEmpty(word[0])) {
           continue;
         }
         boolean flag = false;
 
         if (new QueryBuilder("select count(*) from ZCKeyWord where KeyWord = ? and SiteID=? ", word[0], 
           SiteID).executeInt() > 0)
           flag = true;
         else {
           flag = false;
         }
         keyword = new ZCKeywordSchema();
         if (flag) {
           String WordID = new QueryBuilder("select ID from ZCKeyWord where KeyWord = ? and SiteID=? ", 
             word[0], SiteID).executeOneValue().toString();
           keyword.setID(WordID);
           keyword.fill();
         } else {
           keyword.setID(NoUtil.getMaxID("KeywordID"));
         }
         keyword.setKeyword(word[0]);
         keyword.setSiteId(SiteID);
         if (word.length == 1) {
           keyword.setLinkUrl("");
           keyword.setLinkAlt("");
           keyword.setLinkTarget("_blank");
         } else if (word.length == 2) {
           keyword.setLinkUrl(word[1]);
           keyword.setLinkAlt("");
           keyword.setLinkTarget("_blank");
         } else if (word.length == 3) {
           keyword.setLinkUrl(word[1]);
           keyword.setLinkAlt(word[2]);
           keyword.setLinkTarget("_blank");
         } else if (word.length == 4) {
           keyword.setLinkUrl(word[1]);
           keyword.setLinkAlt(word[2]);
           if ((StringUtil.isDigit(word[3])) && (Integer.parseInt(word[3]) > 0) && 
             (Integer.parseInt(word[3]) < 4)) {
             if (word[3].equals("1"))
               keyword.setLinkTarget("_self");
             else if (word[3].equals("2"))
               keyword.setLinkTarget("_blank");
             else
               keyword.setLinkTarget("_parent");
           }
           else {
             keyword.setLinkTarget("_blank");
           }
         }
         if (flag) {
           keyword.setModifyTime(new Date());
           keyword.setModifyUser(User.getUserName());
           trans.add(keyword, 2);
         } else {
           keyword.setAddTime(new Date());
           keyword.setAddUser(User.getUserName());
           trans.add(keyword, 1);
         }
       }
     }
 
     if (trans.commit()) {
       updateCache();
       this.Response.setLogInfo(1, "导入成功");
     } else {
       this.Response.setLogInfo(0, "导入失败");
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
     long SiteID = Application.getCurrentSiteID();
     ZCKeywordSchema keyword = new ZCKeywordSchema();
     ZCKeywordSet set = keyword.query(new QueryBuilder("where SiteID=? and id in (" + ids + ")", SiteID));
     trans.add(set, 5);
 
     if (trans.commit()) {
       updateCache();
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.site.Keyword
 * JD-Core Version:    0.5.3
 */