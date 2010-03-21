 package com.zving.bbs;
 
 import com.zving.bbs.admin.ForumScore;
 import com.zving.bbs.admin.ForumUtil;
 import com.zving.framework.Ajax;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.controls.DataListAction;
 import com.zving.framework.data.DataTable;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZCForumGroupSchema;
 import com.zving.schema.ZCForumMemberSchema;
 import com.zving.schema.ZCForumMemberSet;
 import com.zving.schema.ZCForumSchema;
 import com.zving.schema.ZCForumSet;
 import com.zving.schema.ZCPostSchema;
 import com.zving.schema.ZCPostSet;
 import com.zving.schema.ZCThemeSchema;
 import com.zving.schema.ZCThemeSet;
 import java.util.Date;
 
 public class PostAudit extends Ajax
 {
   public static Mapx init(Mapx params)
   {
     String sqlSiteID = "SiteID=" + params.getString("SiteID");
     ZCForumMemberSchema member = new ZCForumMemberSchema();
     ZCForumGroupSchema group = new ZCForumGroupSchema();
     member.setUserName(User.getUserName());
     member.fill();
     group.setID(member.getAdminID());
     group.fill();
     DataTable dt = null;
     String sql;
     if (group.getSystemName().equals("超级版主")) {
       sql = "select Name, ID from ZCForum where " + sqlSiteID + " and ParentID != 0";
       dt = new QueryBuilder(sql).executeDataTable();
     } else {
       sql = "select Name,ID from zcforum where " + sqlSiteID + " and (forumadmin like '%" + User.getUserName() + ",%' and ParentID<>0) or ParentID in (select ID from zcforum where " + sqlSiteID + " and forumadmin like '%" + User.getUserName() + ",%' and ParentID=0)";
       dt = new QueryBuilder(sql).executeDataTable();
     }
     params.put("ForumOptions", HtmlUtil.dataTableToOptions(dt));
     return params;
   }
 
   public static void getUnauditedPost(DataListAction dla)
   {
     String sqlSiteID = "SiteID=" + dla.getParam("SiteID");
     String auditFlag = dla.getParams().getString("auditFlag");
     String forumID = dla.getParams().getString("ForumID");
     String typeID = dla.getParams().getString("TypeID");
     String first = dla.getParams().getString("First");
     String sql = "";
     String sqlTotal = "";
     StringBuffer sb = new StringBuffer();
     ZCForumMemberSchema member = new ZCForumMemberSchema();
     ZCForumGroupSchema group = new ZCForumGroupSchema();
     member.setUserName(User.getUserName());
     member.fill();
     group.setID(member.getAdminID());
     group.fill();
     if (!(group.getSystemName().equals("超级版主"))) {
       sb.append(" and f.ForumAdmin like '%" + User.getUserName() + ",%' ");
     }
     if (StringUtil.isNotEmpty(typeID))
       sb.append(" and p.VerifyFlag=").append("'" + typeID + "'");
     else {
       sb.append(" and p.VerifyFlag=").append("'N'");
     }
 
     if ((StringUtil.isNotEmpty(forumID)) && (!(forumID.equals("0")))) {
       sb.append(" and p.ForumID in (" + forumID + ")");
     }
 
     if (StringUtil.isNotEmpty(first))
       sb.append(" and p.first=").append("'" + first + "'");
     else {
       sb.append(" and p.first=").append("'Y'");
     }
     sql = "select p.*, f.Name ForumName, '' as AuditFlag from ZCPost p, ZCForum f where p." + 
       sqlSiteID + " and f." + sqlSiteID + 
       " and p.Invisible='Y' and p.ForumID=f.ID " + sb;
     sqlTotal = "select count(*)from ZCPost p, ZCForum f where p." + 
       sqlSiteID + " and f." + sqlSiteID + 
       " and p.Invisible='Y' and p.ForumID=f.ID " + sb;
 
     int pageSize = dla.getPageSize();
     int pageIndex = dla.getPageIndex();
     DataTable dt = new QueryBuilder(sql).executePagedDataTable(pageSize, pageIndex);
     for (int i = 0; i < dt.getRowCount(); ++i) {
       Mapx map = new Mapx();
       map.put("Y", "通过");
       map.put("N", "删除");
       map.put("X", "忽略");
       if (StringUtil.isNotEmpty(auditFlag))
         dt.set(i, "AuditFlag", HtmlUtil.mapxToRadios("AuditFlag_" + dt.get(i, "ID"), map, auditFlag, true));
       else {
         dt.set(i, "AuditFlag", HtmlUtil.mapxToRadios("AuditFlag_" + dt.get(i, "ID"), map, "Y", true));
       }
     }
     int total = new QueryBuilder(sqlTotal).executeInt();
     dla.setTotal(total);
     dla.bindData(dt);
   }
 
   public void exeAudit()
   {
     String sqlSiteID = "SiteID=" + $V("SiteID");
     Transaction trans = new Transaction();
     ZCPostSet postSet = new ZCPostSchema().query(new QueryBuilder("where " + sqlSiteID + " and VerifyFlag != 'Y' or ApplyDel='1'"));
     ZCThemeSet themeSet = new ZCThemeSet();
     ZCForumSet forumSet = new ZCForumSet();
     ZCForumMemberSet userSet = new ZCForumMemberSet();
 
     ForumScore forumScore = new ForumScore($V("SiteID"));
     for (int i = 0; i < postSet.size(); ++i) {
       ZCPostSchema post = postSet.get(i);
       ZCThemeSchema theme = new ZCThemeSchema();
       theme.setID(post.getThemeID());
       ZCForumSchema forum = new ZCForumSchema();
       forum.setID(theme.getForumID());
       ZCForumMemberSchema user = new ZCForumMemberSchema();
       user.setUserName(post.getAddUser());
       int indexTheme = ForumUtil.getValueOfThemeSet(themeSet, theme);
       int indexForum = ForumUtil.getValueOfForumSet(forumSet, forum);
       int indexMember = ForumUtil.getValueOfMemberSet(userSet, user);
       String newFlag = $V("AuditFlag_" + post.getID());
       if (StringUtil.isNotEmpty(newFlag)) {
         if (newFlag.equals("Y")) {
           post.setVerifyFlag("Y");
 
           if (post.getFirst().equals("Y")) {
             theme.setVerifyFlag("Y");
             theme.setOrderTime(new Date());
 
             if (indexMember == -1) {
               user.fill();
               user.setThemeCount(user.getThemeCount() + 1L);
               user.setForumScore(user.getForumScore() + forumScore.PublishTheme);
               userSet.add(user);
             } else {
               userSet.get(indexMember).setThemeCount(userSet.get(indexMember).getThemeCount() + 1L);
               userSet.get(indexMember).setForumScore(userSet.get(indexMember).getForumScore() + forumScore.PublishTheme);
             }
 
             if (indexForum == -1) {
               forum.fill();
               forum.setPostCount(forum.getPostCount() + 1);
               forum.setThemeCount(forum.getThemeCount() + 1);
               forumSet.add(forum);
             } else {
               forumSet.get(indexForum).setPostCount(forumSet.get(indexForum).getPostCount() + 1);
               forumSet.get(indexForum).setThemeCount(forumSet.get(indexForum).getThemeCount() + 1);
             }
             forum.setLastPost(post.getSubject());
             forum.setLastPoster(post.getAddUser());
             forum.setLastThemeID(theme.getID());
           } else {
             post.setLayer(getMAXLayer(post.getThemeID()) + 1L);
             if (indexMember == -1) {
               user.fill();
               user.setReplyCount(user.getReplyCount() + 1L);
               user.setForumScore(user.getForumScore() + forumScore.PublishPost);
               userSet.add(user);
             } else {
               userSet.get(indexMember).setReplyCount(userSet.get(indexMember).getThemeCount() + 1L);
               userSet.get(indexMember).setForumScore(userSet.get(indexMember).getForumScore() + forumScore.PublishPost);
             }
 
             if (indexForum == -1) {
               forum.fill();
               forum.setPostCount(forum.getPostCount() + 1);
               forumSet.add(forum);
             } else {
               forumSet.get(indexForum).setPostCount(forumSet.get(indexForum).getPostCount() + 1);
             }
 
             if (indexTheme == -1) {
               theme.fill();
               theme.setReplyCount(theme.getReplyCount() + 1);
               themeSet.add(theme);
             } else {
               themeSet.get(indexTheme).setReplyCount(themeSet.get(indexTheme).getReplyCount() + 1);
             }
             theme.setLastPostTime(new Date());
           }
         } else if (newFlag.equals("N")) {
           post.setInvisible("N");
           if (post.getFirst().equals("Y")) {
             theme.fill();
             theme.setStatus("N");
             themeSet.add(theme);
           }
         } else if (newFlag.equals("Z")) {
           post.setApplyDel("N");
           if (post.getFirst().equals("Y")) {
             theme.fill();
             theme.setApplydel("N");
             themeSet.add(theme);
           }
         }
         else {
           post.setVerifyFlag("X");
           if (post.getFirst().equals("Y")) {
             theme.fill();
             theme.setVerifyFlag("X");
             themeSet.add(theme);
           }
         }
       }
     }
     trans.add(postSet, 2);
     trans.add(userSet, 2);
     trans.add(forumSet, 2);
     trans.add(themeSet, 2);
     if (trans.commit())
       this.Response.setLogInfo(1, "操作成功");
     else
       this.Response.setLogInfo(0, "操作失败!");
   }
 
   private long getMAXLayer(long ThemeID)
   {
     String sql = "select Layer from ZCPost where Invisible='Y' and ThemeID=" + ThemeID + " order by Layer desc";
     long layer = new QueryBuilder(sql).executePagedDataTable(1, 0).getLong(0, 0);
     return layer;
   }
 
   public void applyDel() {
     Transaction trans = new Transaction();
     ZCPostSchema post = new ZCPostSchema();
     ZCThemeSchema theme = new ZCThemeSchema();
     post.setID($V("ID"));
     post.fill();
     post.setApplyDel("1");
     if (post.getFirst().equals("Y")) {
       theme.setID(post.getThemeID());
       theme.fill();
       theme.setApplydel("1");
       trans.add(theme, 2);
     }
     trans.add(post, 2);
 
     if (trans.commit())
       this.Response.setLogInfo(1, "申请成功!");
     else
       this.Response.setLogInfo(0, "申请失败!");
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.PostAudit
 * JD-Core Version:    0.5.3
 */