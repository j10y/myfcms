 package com.zving.member;
 
 import com.zving.cms.pub.SiteUtil;
 import com.zving.framework.Ajax;
 import com.zving.framework.ResponseImpl;
 import com.zving.framework.User;
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.HtmlUtil;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZCForumMemberSchema;
 import com.zving.schema.ZDMemberCompanyInfoSchema;
 import com.zving.schema.ZDMemberPersonInfoSchema;
 import java.util.Date;
 import org.apache.commons.mail.EmailException;
 import org.apache.commons.mail.SimpleEmail;
 
 public class MemberInfo extends Ajax
 {
   public static Mapx init(Mapx params)
   {
     String UserName = User.getUserName();
     if (StringUtil.isNotEmpty(UserName)) {
       Member m = new Member(UserName);
       m.fill();
       if (m.getType().equals("Person"))
         params.put("MemberType", "个人");
       else {
         params.put("MemberType", "企业");
       }
       params.putAll(m.toMapx());
       params.put("Gender", HtmlUtil.codeToRadios("Gender", "Gender", m.getGender()));
       params.put("MemberLevelName", new QueryBuilder("select Name from ZDMemberLevel where ID = ?", m.getMemberLevel()).executeString());
       if (m.getStatus().equals("Y"))
         params.put("StatusName", "通过审核");
       else if (m.getStatus().equals("X"))
         params.put("StatusName", "等待审核");
       else {
         params.put("StatusName", "审核未通过");
       }
       if (StringUtil.isEmpty(params.getString("Logo"))) {
         params.put("Logo", "../Images/nophoto.jpg");
       }
     }
     return params;
   }
 
   public static Mapx initDetail(Mapx params) {
     String UserName = User.getUserName();
     if (StringUtil.isNotEmpty(UserName)) {
       Member m = new Member(UserName);
       m.fill();
       if (m.getType().equals("Person")) {
         params.put("MemberType", "个人");
         ZDMemberPersonInfoSchema person = new ZDMemberPersonInfoSchema();
         person.setUserName(m.getUserName());
         person.fill();
         params.putAll(person.toMapx());
       } else {
         params.put("MemberType", "企业");
         ZDMemberCompanyInfoSchema company = new ZDMemberCompanyInfoSchema();
         company.setUserName(m.getUserName());
         company.fill();
         params.putAll(company.toMapx());
       }
       params.putAll(m.toMapx());
       if (StringUtil.isEmpty(params.getString("Pic")))
         params.put("PicPath", "../Images/nopicture.jpg");
       else {
         params.put("PicPath", params.getString("Pic"));
       }
     }
     return params;
   }
 
   public void doSave() {
     String UserName = $V("UserName");
     Member member = new Member(UserName);
     member.fill();
     member.setValue(this.Request);
     if (member.getType().equalsIgnoreCase("Person")) {
       ZDMemberPersonInfoSchema person = new ZDMemberPersonInfoSchema();
       person.setUserName(member.getUserName());
       person.fill();
       person.setNickName($V("Name"));
       person.update();
     } else {
       ZDMemberCompanyInfoSchema company = new ZDMemberCompanyInfoSchema();
       company.setUserName(member.getUserName());
       company.fill();
       company.setCompanyName($V("Name"));
       company.setEmail($V("Email"));
       company.update();
     }
     if (member.update()) {
       ZCForumMemberSchema forumMember = new ZCForumMemberSchema();
       forumMember.setUserName(UserName);
       if (forumMember.fill()) {
         if ((StringUtil.isEmpty(forumMember.getHeadImage())) && (StringUtil.isNotEmpty(member.getLogo()))) {
           forumMember.setHeadImage(member.getLogo());
         }
         if (StringUtil.isEmpty(forumMember.getNickName())) {
           forumMember.setNickName($V("Name"));
           forumMember.setModifyUser(UserName);
           forumMember.setModifyTime(new Date());
         }
         forumMember.update();
       }
       this.Response.setLogInfo(1, "保存成功");
     } else {
       this.Response.setLogInfo(0, "保存失败");
     }
   }
 
   public void doDetailSave() {
     String UserName = $V("UserName");
     Member member = new Member(UserName);
     member.fill();
     member.setValue(this.Request);
     if (member.getType().equalsIgnoreCase("Person")) {
       ZDMemberPersonInfoSchema person = new ZDMemberPersonInfoSchema();
       person.setUserName(member.getUserName());
       person.fill();
       person.setValue(this.Request);
       person.update();
     } else {
       ZDMemberCompanyInfoSchema company = new ZDMemberCompanyInfoSchema();
       company.setUserName(member.getUserName());
       company.fill();
       company.setValue(this.Request);
       company.update();
     }
     if (member.update())
       this.Response.setLogInfo(1, "保存成功");
     else
       this.Response.setLogInfo(0, "保存失败");
   }
 
   public void modifyPassword()
   {
     String UserName = $V("UserName");
     String OldPassWord = $V("OldPassWord").trim();
     String NewPassWord = $V("NewPassWord").trim();
     String ConfirmPassword = $V("ConfirmPassword").trim();
     Member member = new Member();
     member.setUserName(UserName);
     member.fill();
     String password = member.getPassword();
     if (StringUtil.md5Hex(OldPassWord).equals(password))
       if (NewPassWord.equals(ConfirmPassword)) {
         member.setPassword(NewPassWord);
         if (member.update())
           this.Response.setLogInfo(1, "修改密码成功");
         else
           this.Response.setLogInfo(0, "修改密码失败");
       }
       else {
         this.Response.setLogInfo(0, "两次输入的密码不一致");
       }
     else
       this.Response.setLogInfo(0, "原密码错误");
   }
 
   public void resetPassword()
   {
     String UserName = $V("UserName");
     String NewPassWord = $V("NewPassWord").trim();
     String ConfirmPassword = $V("ConfirmPassword").trim();
     Member member = new Member(UserName);
     member.fill();
     if (StringUtil.isNotEmpty(member.getPWQuestion()))
       if (NewPassWord.equals(ConfirmPassword)) {
         member.setPassword(NewPassWord);
         member.setPWQuestion("");
         if (member.update()) {
           this.Response.setLogInfo(1, "重置密码成功");
           this.Response.put("SiteID", member.getSiteID());
         } else {
           this.Response.setLogInfo(0, "重置密码失败");
         }
       } else {
         this.Response.setLogInfo(0, "两次输入的密码不一致");
       }
     else
       this.Response.setLogInfo(0, "修改链接已过期");
   }
 
   public void getPassWord()
   {
     String UserName = $V("UserName");
     String URL = $V("URL");
     URL = URL.substring(0, URL.lastIndexOf("/") + 1);
     if (StringUtil.isNotEmpty(UserName)) {
       Member member = new Member(UserName);
       if (member.fill()) {
         String SiteName = SiteUtil.getName(member.getSiteID());
         String to = member.getEmail();
         if (StringUtil.isNotEmpty(to)) {
           SimpleEmail email = new SimpleEmail();
           email.setHostName("smtp.163.com");
           try {
             String pwq = StringUtil.md5Hex(member.getUserName() + System.currentTimeMillis());
             StringBuffer sb = new StringBuffer();
             sb.append("尊敬的" + SiteName + "用户：<br/>");
             sb.append("你好！<br/>");
             sb.append("<a href='" + URL + "GetPassword.jsp?User=" + member.getUserName() + "&pwq=" + pwq + "&SiteID=" + member.getSiteID() + "'>请点击此处修改您的密码</a><br/>");
             sb.append("如果上面的链接无法点击，可能是您在以纯文本模式查看邮件，请复制以下地址，粘贴到浏览器地址栏然后按“回车键”直接访问<br/>");
             sb.append(URL + "GetPassword.jsp?User=" + member.getUserName() + "&pwq=" + pwq);
             sb.append("<br/><br/>注：此邮件为系统自动发送，请勿回复。<br/>");
             sb.append("　　　　　　　　　　　　　　　　　　　　　　　————" + SiteName);
             member.setPWQuestion(pwq);
             email.setAuthentication("0871huhu@163.com", "08715121182");
             email.addTo(to, member.getUserName());
             email.setFrom("0871huhu@163.com", SiteName);
             email.setSubject(SiteName + "：找回您的密码");
             email.setContent(sb.toString(), "text/html;charset=utf-8");
             if (member.update()) {
               this.Response.setLogInfo(1, "系统已经发送密码重置链接到您注册时填写的电子邮箱，请查收");
               this.Response.put("SiteID", member.getSiteID()); return;
             }
             this.Response.setLogInfo(0, "系统错误");
           }
           catch (EmailException e) {
             this.Response.setLogInfo(0, "邮件发送错误");
             e.printStackTrace();
           }
         }
       } else {
         this.Response.setLogInfo(0, "用户名不存在");
       }
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.member.MemberInfo
 * JD-Core Version:    0.5.3
 */