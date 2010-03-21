 package com.zving.cms.dataservice;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCQuestionSchema;
 import com.zving.schema.ZCQuestionSet;
 import java.util.Date;
 
 public class Question extends Page
 {
   public static void dg1DataBind(DataGridAction dga)
   {
     String InnerCode = dga.getParam("QuestionInnerCode");
     StringBuffer conditions = new StringBuffer();
     if (StringUtil.isEmpty(InnerCode))
       conditions.append(" and QuestionInnerCode = '" + dga.getParams().getString("Cookie.Ask.InnerCode") + "'");
     else {
       conditions.append(" and QuestionInnerCode = '" + InnerCode.trim() + "'");
     }
     String sql = "select *  from zcquestion where 1=1 " + conditions + " order by AddTime desc";
     String sql2 = "select count(*)  from zcquestion where 1=1 " + conditions;
     dga.setTotal(new QueryBuilder(sql2));
     DataTable dt = new QueryBuilder(sql).executePagedDataTable(dga.getPageSize(), dga.getPageIndex());
     dga.bindData(dt);
   }
 
   public static Mapx initDialog(Mapx params)
   {
     String ID = params.getString("ID");
     String questionInnerCode = params.getString("QuestionInnerCode");
     if (StringUtil.isNotEmpty(ID)) {
       ZCQuestionSchema question = new ZCQuestionSchema();
       question.setID(ID);
       if (!(question.fill())) {
         return params;
       }
       Mapx map = question.toMapx();
       String questionName = new QueryBuilder("select name from zcquestiongroup where InnerCode=?", questionInnerCode).executeString();
       map.put("QuestionInnerCode", questionInnerCode);
       map.put("QuestionName", questionName);
       return map;
     }
     String questionName = new QueryBuilder("select name from zcquestiongroup where InnerCode=?", questionInnerCode).executeString();
     params.put("QuestionInnerCode", questionInnerCode);
     params.put("QuestionName", questionName);
     return params;
   }
 
   public void add()
   {
     String questionInnerCode = $V("QuestionInnerCode");
     if (StringUtil.isEmpty(questionInnerCode)) {
       this.Response.setLogInfo(0, "操作失败，请选择分类!");
       return;
     }
     ZCQuestionSchema question = new ZCQuestionSchema();
     question.setID(NoUtil.getMaxID("QuestionID"));
     question.setValue(this.Request);
     question.setQuestionInnerCode(questionInnerCode);
     question.setAddUser(User.getUserName());
     Date currentDate = new Date();
     question.setAddTime(currentDate);
     if (question.insert())
       this.Response.setLogInfo(1, "操作成功!");
     else
       this.Response.setLogInfo(0, "操作失败!");
   }
 
   public void edit()
   {
     ZCQuestionSchema question = new ZCQuestionSchema();
     String id = $V("ID");
     question.setID(id);
     question.fill();
     question.setValue(this.Request);
     question.setModifyTime(new Date());
     question.setModifyUser(User.getUserName());
     if (question.update())
       this.Response.setLogInfo(1, "操作成功!");
     else
       this.Response.setLogInfo(0, "操作失败!");
   }
 
   public void del()
   {
     String ids = $V("IDs");
     if (!(StringUtil.checkID(ids))) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入ID时发生错误!");
       return;
     }
     Transaction trans = new Transaction();
     ZCQuestionSchema question = new ZCQuestionSchema();
     ZCQuestionSet questionset = question.query(new QueryBuilder("where ID in (" + ids + ")"));
     trans.add(questionset, 5);
     if (trans.commit())
       this.Response.setLogInfo(1, "操作成功!");
     else
       this.Response.setLogInfo(0, "操作失败!");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.dataservice.Question
 * JD-Core Version:    0.5.3
 */