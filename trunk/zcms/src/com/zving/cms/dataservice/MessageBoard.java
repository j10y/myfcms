 package com.zving.cms.dataservice;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.Application;
 import com.zving.schema.ZCMessageBoardSchema;
 
 public class MessageBoard extends Page
 {
   public static void dg1DataBind(DataGridAction dga)
   {
     String PublishFlag = dga.getParam("PublishFlag");
     String ReplyFlag = dga.getParam("ReplyFlag");
     StringBuffer conditions = new StringBuffer();
     conditions.append(" where SiteID = " + Application.getCurrentSiteID());
     if (StringUtil.isNotEmpty(PublishFlag)) {
       conditions.append(" and PublishFlag = '" + PublishFlag + "' ");
     }
     if (StringUtil.isNotEmpty(ReplyFlag)) {
       conditions.append(" and ReplyFlag = '" + ReplyFlag + "' ");
     }
     String sql = "select * from ZCMessageBoard " + conditions + " order by ID desc";
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(*) from ZCMessageBoard " + conditions));
     }
     DataTable dt = new QueryBuilder(sql).executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     dt.insertColumn("ReplyFlagName");
     dt.insertColumn("PublishFlagName");
     for (int i = 0; i < dt.getRowCount(); ++i) {
       if (dt.getString(i, "ReplyFlag").equals("1")) {
         dt.set(i, "ReplyFlagName", "[已回复]");
       }
       if (dt.getString(i, "PublishFlag").equals("1"))
         dt.set(i, "PublishFlagName", "[审核通过]");
       else {
         dt.set(i, "PublishFlagName", "[等待审核]");
       }
     }
     dga.bindData(dt);
   }
 
   public void save() {
     String ID = $V("ID");
     String ReplyContent = $V("ReplyContent");
     if ((StringUtil.isNotEmpty(ID)) && (StringUtil.isNotEmpty(ReplyContent))) {
       ZCMessageBoardSchema message = new ZCMessageBoardSchema();
       message.setID(ID);
       message.fill();
       message.setReplyFlag("1");
       message.setReplyContent(ReplyContent);
       if (message.update())
         this.Response.setLogInfo(1, "回复成功");
       else
         this.Response.setLogInfo(0, "发生错误");
     }
     else {
       this.Response.setLogInfo(1, "回复成功");
     }
   }
 
   public void doCheck() {
     String IDs = $V("IDs");
     String PublishFlag = $V("PublishFlag");
     if ((StringUtil.isNotEmpty(IDs)) && (StringUtil.isNotEmpty(PublishFlag))) {
       String[] ids = IDs.split(",");
       Transaction trans = new Transaction();
       ZCMessageBoardSchema message = new ZCMessageBoardSchema();
       for (int i = 0; i < ids.length; ++i) {
         message = new ZCMessageBoardSchema();
         message.setID(ids[i]);
         message.fill();
         message.setPublishFlag(PublishFlag);
         trans.add(message, 2);
       }
       if (trans.commit())
         this.Response.setLogInfo(1, "审核成功");
       else
         this.Response.setLogInfo(0, "审核失败");
     }
   }
 
   public static Mapx initDialog(Mapx params)
   {
     String ID = params.getString("ID");
     if (ID != null) {
       ZCMessageBoardSchema message = new ZCMessageBoardSchema();
       message.setID(ID);
       message.fill();
       params.putAll(message.toMapx());
     }
     return params;
   }
 
   public void del() {
     String IDs = $V("IDs");
     String[] ids = IDs.split(",");
     Transaction trans = new Transaction();
     ZCMessageBoardSchema message = new ZCMessageBoardSchema();
     for (int i = 0; i < ids.length; ++i) {
       trans.add(message.query(new QueryBuilder(" where ID = ?", ids[i])), 5);
     }
     if (trans.commit())
       this.Response.setLogInfo(1, "删除成功");
     else
       this.Response.setLogInfo(0, "删除失败");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.dataservice.MessageBoard
 * JD-Core Version:    0.5.3
 */