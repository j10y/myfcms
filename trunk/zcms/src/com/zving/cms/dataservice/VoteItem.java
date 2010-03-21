 package com.zving.cms.dataservice;
 
 import com.zving.framework.Page;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.controls.DataGridAction;
 import com.zving.framework.data.DataRow;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCVoteItemSchema;
 import com.zving.schema.ZCVoteSchema;
 import com.zving.schema.ZCVoteSubjectSchema;
 
 public class VoteItem extends Page
 {
   public static Mapx init(Mapx params)
   {
     String VoteID = params.get("ID").toString();
     ZCVoteSchema vote = new ZCVoteSchema();
     vote.setID(VoteID);
     vote.fill();
     params.put("VoteName", vote.getTitle());
     return params;
   }
 
   public static void dg1DataBind(DataGridAction dga) {
     String sql = "";
     String id = dga.getParam("ID");
     if (dga.getTotal() == 0) {
       dga.setTotal(new QueryBuilder("select count(*) from zcvoteitem where VoteID = ?", id));
     }
     QueryBuilder qb = new QueryBuilder("select count(*) from zcvoteitem where VoteID = ?", id);
     if (qb.executeInt() > 0) {
       sql = "select ItemsTitle,Items,Scores,ID from zcvoteitem where VoteID = ?";
       QueryBuilder qb1 = new QueryBuilder();
       qb1.add(id);
       qb1.setSQL(sql);
       dga.bindData(qb1);
     } else {
       DataTable dt = new DataTable();
       dt.insertColumn("ItemsTitle", new String[] { "" });
       dt.insertColumn("Items", new String[] { "" });
       dt.insertColumn("Scores", new String[] { "0" });
       dt.insertColumn("ID", new String[] { "new" });
       dga.bindData(dt);
     }
   }
 
   public void save() {
     Mapx map = this.Request;
     String voteID = $V("ID");
     ZCVoteSubjectSchema subject = new ZCVoteSubjectSchema();
     ZCVoteItemSchema item = new ZCVoteItemSchema();
     subject.setVoteID(voteID);
     item.setVoteID(voteID);
     String key = null;
     Transaction trans = new Transaction();
     trans.add(subject.query(), 5);
     trans.add(item.query(), 5);
 
     Object[] ks = map.keyArray();
     for (int i = 0; i < map.size(); ++i) {
       key = (String)ks[i];
       if (key.startsWith("Subject_")) {
         String subjectID = key.substring(8);
         subject = new ZCVoteSubjectSchema();
         subject.setID(NoUtil.getMaxID("VoteSubjectID"));
         subject.setVoteID(voteID);
         subject.setType(map.getString("Type_" + subjectID));
         if (StringUtil.isEmpty(subject.getType())) {
           subject.setType("N");
         }
         subject.setSubject(map.getString(key));
         trans.add(subject, 1);
 
         for (int j = 0; j < map.size(); ++j) {
           key = (String)ks[j];
           if (key.startsWith("Item_" + subjectID + "_")) {
             item = new ZCVoteItemSchema();
             item.setID(NoUtil.getMaxID("VoteItemID"));
             item.setVoteID(subject.getVoteID());
             item.setSubjectID(subject.getID());
             item.setItem(map.getString(key));
             item.setScore(map.getString("Score_" + key.substring(5)));
             item.setItemType(map.getString("ItemType_" + key.substring(5)));
             if (StringUtil.isEmpty(item.getItemType())) {
               item.setItemType("0");
             }
             trans.add(item, 1);
           }
         }
       }
     }
     if (trans.commit()) {
       this.Response.setLogInfo(1, "保存成功");
       Vote.generateJS(voteID);
     } else {
       this.Response.setLogInfo(0, "保存失败");
     }
   }
 
   public static DataTable getVoteSubjects(Mapx params, DataRow parentDR) {
     String voteID = params.getString("ID");
     DataTable dt = new QueryBuilder("select * from ZCVoteSubject where voteID =? order by ID", voteID).executeDataTable();
 
     return dt;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.dataservice.VoteItem
 * JD-Core Version:    0.5.3
 */