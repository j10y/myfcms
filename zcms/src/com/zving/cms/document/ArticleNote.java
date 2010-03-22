 package com.zving.cms.document;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCArticleLogSchema;
 import com.zving.schema.ZCArticleLogSet;
 import java.util.Date;
 
 public class ArticleNote extends Page
 {
   public static void noteDataBind(DataGridAction dga)
   {
     String articleID = (String)dga.getParams().get("ArticleID");
     String sql = "select * from ZCArticlelog where action='NOTE' and articleID=? order by ID desc";
     if (dga.getTotal() == 0) {
       String sql2 = "select count(*) from  ZCArticlelog where action='NOTE' and articleID=?";
       dga.setTotal(new QueryBuilder(sql2, articleID));
     }
 
     dga.bindData(new QueryBuilder(sql, articleID));
   }
 
   public static void logDataBind(DataGridAction dga) {
     String articleID = (String)dga.getParams().get("ArticleID");
     String sql = "select * from ZCArticlelog where action<>'NOTE' and articleID=? order by ID desc";
     if (dga.getTotal() == 0) {
       String sql2 = "select count(*) from  ZCArticlelog where action<>'NOTE' and articleID=?";
       dga.setTotal(new QueryBuilder(sql2, articleID));
     }
 
     dga.bindData(new QueryBuilder(sql, articleID));
   }
 
   public void del() {
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
     Transaction trans = new Transaction();
     ZCArticleLogSchema log = new ZCArticleLogSchema();
     ZCArticleLogSet set = log.query(new QueryBuilder("where id in (" + ids + ")"));
     trans.add(set, 5);
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 
   public void add() {
     String articleID = $V("ArticleID");
     long id = Long.parseLong(articleID);
 
     ZCArticleLogSchema articleLog = new ZCArticleLogSchema();
 
     articleLog.setID(NoUtil.getMaxID("ArticleLogID"));
     articleLog.setArticleID(id);
     articleLog.setAction("NOTE");
     articleLog.setActionDetail($V("Content"));
     articleLog.setAddUser(User.getUserName());
     articleLog.setAddTime(new Date());
 
     Transaction trans = new Transaction();
     trans.add(articleLog, 1);
 
     if (trans.commit()) {
       this.Response.setStatus(1);
     } else {
       this.Response.setStatus(0);
       this.Response.setMessage("操作数据库时发生错误!");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.document.ArticleNote
 * JD-Core Version:    0.5.3
 */