 package com.zving.bbs.admin;
 
 import com.zving.framework.Page;
 import com.zving.framework.RequestImpl;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.data.Transaction;
 import com.zving.framework.utility.StringUtil;
 import com.zving.platform.pub.NoUtil;
 import com.zving.schema.ZCAdminGroupSchema;
 import com.zving.schema.ZCForumConfigSchema;
 import com.zving.schema.ZCForumGroupSchema;
 import com.zving.schema.ZCForumMemberSchema;
 import com.zving.schema.ZCForumScoreSchema;
 import com.zving.schema.ZDMemberPersonInfoSchema;
 import com.zving.schema.ZDMemberSchema;
 import java.util.Date;
 
 public class DBInit extends Page
 {
   public void DBDataInit()
   {
     String sqlSiteID = "SiteID=" + ForumUtil.getCurrentBBSSiteID();
     String sql = "select count(*) from ZCForumGroup where " + sqlSiteID + " and (SystemName='版主' or SystemName='超级版主' or SystemName='系统管理员' or SystemName='游客')";
     int count = new QueryBuilder(sql).executeInt();
     if (count == 0) {
       Transaction trans = new Transaction();
       ZCForumGroupSchema group1 = new ZCForumGroupSchema();
       group1.setID(NoUtil.getMaxID("ForumGroupID"));
       group1.setRadminID(group1.getID());
       group1.setName("版主");
       group1.setSystemName("版主");
       group1.setType("2");
       group1.setSiteID(ForumUtil.getCurrentBBSSiteID());
       group1.setAllowAutograph("Y");
       group1.setAllowFace("Y");
       group1.setAllowHeadImage("Y");
       group1.setAllowNickName("Y");
       group1.setAllowPanel("Y");
       group1.setAllowReply("Y");
       group1.setAllowSearch("Y");
       group1.setAllowTheme("Y");
       group1.setAllowUserInfo("Y");
       group1.setAllowVisit("Y");
       group1.setVerify("Y");
       group1.setImage("../Images/SystemHeadImage/HeadImage01.gif");
       group1.setAddUser("system");
       group1.setAddTime(new Date());
       trans.add(group1, 1);
       ZCAdminGroupSchema admin1 = new ZCAdminGroupSchema();
       admin1.setGroupID(group1.getID());
       admin1.setSiteID(ForumUtil.getCurrentBBSSiteID());
       admin1.setAllowEditUser("Y");
       admin1.setAllowForbidUser("Y");
       admin1.setAllowEditForum("Y");
       admin1.setAllowVerfyPost("Y");
       admin1.setAllowDel("Y");
       admin1.setAllowEdit("Y");
       admin1.setHide("Y");
       admin1.setTopTheme("Y");
       admin1.setBestTheme("Y");
       admin1.setBrightTheme("Y");
       admin1.setRemoveTheme("Y");
       admin1.setMoveTheme("Y");
       admin1.setUpOrDownTheme("Y");
       admin1.setAddUser("system");
       admin1.setAddTime(new Date());
       trans.add(admin1, 1);
 
       ZCForumGroupSchema group2 = new ZCForumGroupSchema();
       group2.setID(NoUtil.getMaxID("ForumGroupID"));
       group2.setRadminID(group2.getID());
       group2.setName("超级版主");
       group2.setSystemName("超级版主");
       group2.setType("2");
       group2.setSiteID(ForumUtil.getCurrentBBSSiteID());
       group2.setAllowAutograph("Y");
       group2.setAllowFace("Y");
       group2.setAllowHeadImage("Y");
       group2.setAllowNickName("Y");
       group2.setAllowPanel("Y");
       group2.setAllowReply("Y");
       group2.setAllowSearch("Y");
       group2.setAllowTheme("Y");
       group2.setAllowUserInfo("Y");
       group2.setAllowVisit("Y");
       group2.setVerify("Y");
       group2.setImage("../Images/SystemHeadImage/HeadImage01.gif");
       group2.setAddUser("system");
       group2.setAddTime(new Date());
       trans.add(group2, 1);
       ZCAdminGroupSchema admin2 = new ZCAdminGroupSchema();
       admin2.setGroupID(group2.getID());
       admin2.setSiteID(ForumUtil.getCurrentBBSSiteID());
       admin2.setAllowEditUser("Y");
       admin2.setAllowForbidUser("Y");
       admin2.setAllowEditForum("Y");
       admin2.setAllowVerfyPost("Y");
       admin2.setAllowDel("Y");
       admin2.setAllowEdit("Y");
       admin2.setHide("Y");
       admin2.setTopTheme("Y");
       admin2.setBestTheme("Y");
       admin2.setBrightTheme("Y");
       admin2.setRemoveTheme("Y");
       admin2.setMoveTheme("Y");
       admin2.setUpOrDownTheme("Y");
       admin2.setAddUser("system");
       admin2.setAddTime(new Date());
       trans.add(admin2, 1);
 
       ZCForumGroupSchema group3 = new ZCForumGroupSchema();
       group3.setID(NoUtil.getMaxID("ForumGroupID"));
       group3.setRadminID(group3.getID());
       group3.setName("系统管理员");
       group3.setSystemName("系统管理员");
       group3.setType("2");
       group3.setSiteID(ForumUtil.getCurrentBBSSiteID());
       group3.setAllowAutograph("Y");
       group3.setAllowFace("Y");
       group3.setAllowHeadImage("Y");
       group3.setAllowNickName("Y");
       group3.setAllowPanel("Y");
       group3.setAllowReply("Y");
       group3.setAllowSearch("Y");
       group3.setAllowTheme("Y");
       group3.setAllowUserInfo("Y");
       group3.setAllowVisit("Y");
       group3.setVerify("Y");
       group3.setImage("../Images/SystemHeadImage/HeadImage01.gif");
       group3.setAddUser("system");
       group3.setAddTime(new Date());
       trans.add(group3, 1);
       ZCAdminGroupSchema admin3 = new ZCAdminGroupSchema();
       admin3.setGroupID(group3.getID());
       admin3.setSiteID(ForumUtil.getCurrentBBSSiteID());
       admin3.setAllowEditUser("Y");
       admin3.setAllowForbidUser("Y");
       admin3.setAllowEditForum("Y");
       admin3.setAllowVerfyPost("Y");
       admin3.setAllowDel("Y");
       admin3.setAllowEdit("Y");
       admin3.setHide("Y");
       admin3.setTopTheme("Y");
       admin3.setBestTheme("Y");
       admin3.setBrightTheme("Y");
       admin3.setRemoveTheme("Y");
       admin3.setMoveTheme("Y");
       admin3.setUpOrDownTheme("Y");
       admin3.setAddUser("system");
       admin3.setAddTime(new Date());
       trans.add(admin3, 1);
 
       ZCForumGroupSchema group4 = new ZCForumGroupSchema();
       group4.setID(NoUtil.getMaxID("ForumGroupID"));
       group4.setName("游客");
       group4.setSystemName("游客");
       group4.setType("2");
       group4.setSiteID(ForumUtil.getCurrentBBSSiteID());
       group4.setAllowAutograph("N");
       group4.setAllowFace("N");
       group4.setAllowHeadImage("N");
       group4.setAllowNickName("N");
       group4.setAllowPanel("N");
       group4.setAllowReply("N");
       group4.setAllowSearch("N");
       group4.setAllowTheme("N");
       group4.setAllowUserInfo("N");
       group4.setAllowVisit("Y");
       group4.setVerify("N");
       group4.setImage("../Images/SystemHeadImage/HeadImage01.gif");
       group4.setAddUser("system");
       group4.setAddTime(new Date());
       trans.add(group4, 1);
 
       ZCForumGroupSchema group5 = new ZCForumGroupSchema();
       group5.setID(NoUtil.getMaxID("ForumGroupID"));
       group5.setName("禁止访问");
       group5.setSystemName("禁止访问");
       group5.setType("2");
       group5.setSiteID(ForumUtil.getCurrentBBSSiteID());
       group5.setAllowAutograph("N");
       group5.setAllowFace("N");
       group5.setAllowHeadImage("N");
       group5.setAllowNickName("N");
       group5.setAllowPanel("N");
       group5.setAllowReply("N");
       group5.setAllowSearch("N");
       group5.setAllowTheme("N");
       group5.setAllowUserInfo("N");
       group5.setAllowVisit("N");
       group5.setVerify("N");
       group5.setImage("../Images/SystemHeadImage/HeadImage01.gif");
       group5.setAddUser("system");
       group5.setAddTime(new Date());
       trans.add(group5, 1);
 
       ZCForumScoreSchema forumScore = new ZCForumScoreSchema();
       forumScore.setID(NoUtil.getMaxID("ForumGroupID"));
       forumScore.setSiteID(ForumUtil.getCurrentBBSSiteID());
       forumScore.setInitScore(20L);
       forumScore.setPublishTheme(5L);
       forumScore.setDeleteTheme(-5L);
       forumScore.setPublishPost(2L);
       forumScore.setDeletePost(-2L);
       forumScore.setBest(10L);
       forumScore.setCancelBest(-10L);
       forumScore.setBright(5L);
       forumScore.setCancelBright(-5L);
       forumScore.setTopTheme(10L);
       forumScore.setCancelTop(-10L);
       forumScore.setUpTheme(5L);
       forumScore.setDownTheme(-5L);
       forumScore.setUpload(5L);
       forumScore.setDownload(-2L);
       forumScore.setSearch(0L);
       forumScore.setVote(2L);
       forumScore.setAddUser("system");
       forumScore.setAddTime(new Date());
       trans.add(forumScore, 1);
 
       ZCForumGroupSchema userGroup = new ZCForumGroupSchema();
       userGroup.setID(NoUtil.getMaxID("ForumGroupID"));
       userGroup.setSiteID(ForumUtil.getCurrentBBSSiteID());
       userGroup.setName("新手上路");
       userGroup.setType("1");
       userGroup.setLowerScore(-99999L);
       userGroup.setUpperScore(99999L);
       userGroup.setOrderFlag(1L);
       userGroup.setAllowAutograph("N");
       userGroup.setAllowFace("N");
       userGroup.setAllowHeadImage("N");
       userGroup.setAllowNickName("N");
       userGroup.setAllowPanel("Y");
       userGroup.setAllowReply("Y");
       userGroup.setAllowSearch("N");
       userGroup.setAllowTheme("Y");
       userGroup.setAllowUserInfo("N");
       userGroup.setAllowVisit("Y");
       userGroup.setVerify("N");
       userGroup.setImage("../Images/SystemHeadImage/HeadImage01.gif");
       userGroup.setAddUser("system");
       userGroup.setAddTime(new Date());
       trans.add(userGroup, 1);
 
       ZCForumConfigSchema forumConfig = new ZCForumConfigSchema();
       forumConfig.setID(NoUtil.getMaxID("ForumConfigID"));
       forumConfig.setName("ZvingBBS");
       forumConfig.setSiteID(ForumUtil.getCurrentBBSSiteID());
       forumConfig.setSubdomains("");
       forumConfig.setDes("");
       forumConfig.setTempCloseFlag("N");
       forumConfig.setAddTime(new Date());
       forumConfig.setAddUser("system");
       trans.add(forumConfig, 1);
 
       ZDMemberSchema member = new ZDMemberSchema();
       member.setUserName("admin");
       if (!(member.fill())) {
         member.setPassword(StringUtil.md5Hex("admin"));
         member.setEmail("admin@zving.com");
         member.setSiteID(ForumUtil.getCurrentBBSSiteID());
         member.setRegIP(this.Request.getClientIP());
         member.setValue(this.Request);
         member.setType("person");
         member.setStatus("Y");
         member.setScore("0");
         member.setMemberLevel(new QueryBuilder("select ID from ZDMemberLevel where Score <= 0 order by Score desc").executeOneValue().toString());
         member.setRegTime(new Date());
         member.setName("系统管理员");
         ZDMemberPersonInfoSchema personInfo = new ZDMemberPersonInfoSchema();
         personInfo.setUserName(member.getUserName());
         ZCForumMemberSchema forumMember = new ZCForumMemberSchema();
         forumMember.setUserName(member.getUserName());
         forumMember.setSiteID(ForumUtil.getCurrentBBSSiteID());
         forumMember.setThemeCount(0L);
         forumMember.setReplyCount(0L);
         forumMember.setAdminID(group3.getID());
         forumMember.setUserGroupID(group3.getID());
         forumMember.setUseSelfImage("N");
         forumMember.setHeadImage("../Images/SystemHeadImage/HeadImage01.gif");
         forumMember.setAddUser(member.getUserName());
         forumMember.setAddTime(new Date());
         trans.add(member, 1);
         trans.add(personInfo, 1);
         trans.add(forumMember, 1);
       }
 
       if (trans.commit())
         this.Response.setLogInfo(1, "初始化成功！");
       else
         this.Response.setLogInfo(0, "初始化失败！");
     }
     else {
       this.Response.setLogInfo(0, "数据已存在！");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.bbs.admin.DBInit
 * JD-Core Version:    0.5.3
 */