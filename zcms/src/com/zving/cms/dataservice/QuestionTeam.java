 package com.zving.cms.dataservice;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.TreeAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCQuestionGroupSchema;
 import com.zving.schema.ZCQuestionGroupSet;
 import java.util.Date;
 
 public class QuestionTeam extends Page
 {
   public static void treeDataBind(TreeAction ta)
   {
     DataTable dt = new QueryBuilder(" select InnerCode,ParentInnerCode,TreeLevel,Name from ZCQuestionGroup").executeDataTable();
     ta.setRootText("问题分类列表");
     ta.setParentIdentifierColumnName("ParentInnerCode");
     ta.setIdentifierColumnName("InnerCode");
     ta.setBranchIcon("Icons/treeicon12.gif");
     ta.setLeafIcon("Icons/treeicon12.gif");
     ta.bindData(dt);
   }
 
   public static Mapx initDialog(Mapx params)
   {
     String InnerCode = params.getString("InnerCode");
     String ParentInnerCode = params.getString("ParentInnerCode");
     if (StringUtil.isNotEmpty(InnerCode)) {
       ZCQuestionGroupSchema group = new ZCQuestionGroupSchema();
       group.setInnerCode(InnerCode);
       if (!(group.fill())) {
         return params;
       }
       Mapx map = group.toMapx();
       ParentInnerCode = map.getString("ParentInnerCode");
       if ((StringUtil.isNotEmpty(ParentInnerCode)) && (!("0".equals(ParentInnerCode)))) {
         String ParentName = new QueryBuilder("select name from zcquestionGroup where InnerCode=?", ParentInnerCode).executeString();
         map.put("ParentName", ParentName);
         map.put("ParentInnerCode", ParentInnerCode);
       }
       return map;
     }
     String ParentName = new QueryBuilder("select name as ParentName from zcquestiongroup where InnerCode=?", ParentInnerCode).executeString();
     params.put("ParentInnerCode", ParentInnerCode);
     params.put("ParentName", ParentName);
     return params;
   }
 
   public void add()
   {
     String ParentInnerCode = $V("ParentInnerCode");
     ZCQuestionGroupSchema group = new ZCQuestionGroupSchema();
     if (StringUtil.isNotEmpty(ParentInnerCode)) {
       group.setInnerCode(NoUtil.getMaxNo("GroupInnerCode", ParentInnerCode, 4));
       group.setParentInnerCode(ParentInnerCode);
       int parentTreelevel = new QueryBuilder("select TreeLevel from ZCQuestionGroup where InnerCode=?", ParentInnerCode).executeInt();
       group.setTreeLevel(parentTreelevel + 1);
     } else {
       group.setInnerCode(NoUtil.getMaxNo("GroupInnerCode", 4));
       group.setParentInnerCode("0");
       group.setTreeLevel(1);
     }
     group.setValue(this.Request);
     group.setOrderFlag(0L);
     group.setAddUser(User.getUserName());
     Date currentDate = new Date();
     group.setAddTime(currentDate);
     if (group.insert())
       this.Response.setLogInfo(1, "操作成功!");
     else
       this.Response.setLogInfo(0, "操作失败!");
   }
 
   public void edit()
   {
     ZCQuestionGroupSchema group = new ZCQuestionGroupSchema();
     String innerCode = $V("InnerCode");
     group.setInnerCode(innerCode);
     group.fill();
     group.setValue(this.Request);
     group.setModifyTime(new Date());
     group.setModifyUser(User.getUserName());
     if (group.update())
       this.Response.setLogInfo(1, "操作成功!");
     else
       this.Response.setLogInfo(0, "操作失败!");
   }
 
   public void del()
   {
     String InnerCode = $V("InnerCode");
     if (StringUtil.isEmpty(InnerCode)) {
       this.Response.setStatus(0);
       this.Response.setMessage("传入InnerCode时发生错误!");
       return;
     }
     Transaction trans = new Transaction();
     ZCQuestionGroupSchema group = new ZCQuestionGroupSchema();
     ZCQuestionGroupSet questionset = group.query(new QueryBuilder("where InnerCode = ?", InnerCode));
     trans.add(questionset, 5);
     if (trans.commit())
       this.Response.setLogInfo(1, "操作成功!");
     else
       this.Response.setLogInfo(0, "操作失败!");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.dataservice.QuestionTeam
 * JD-Core Version:    0.5.3
 */