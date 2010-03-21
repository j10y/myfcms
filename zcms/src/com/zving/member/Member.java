 package com.zving.member;
 
 import com.zving.framework.data.QueryBuilder;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZDMemberSchema;
 
 public class Member extends ZDMemberSchema
 {
   private static final long serialVersionUID = 1L;
 
   public Member()
   {
   }
 
   public Member(String userName)
   {
     setUserName(userName);
   }
 
   public void setPassword(String passWord) {
     super.setPassword(StringUtil.md5Hex(passWord));
   }
 
   public void setUnMd5Password(String passWord) {
     super.setPassword(passWord);
   }
 
   public boolean IsExists() {
     boolean flag = false;
     if (StringUtil.isNotEmpty(getUserName())) {
       int count = new QueryBuilder("select count(*) from ZDMember where UserName = ?", getUserName()).executeInt();
       if (count > 0) {
         flag = true;
       }
     }
     return flag;
   }
 
   public boolean checkPassWord(String passWord) {
     boolean flag = false;
     if (StringUtil.isNotEmpty(passWord)) {
       passWord = StringUtil.md5Hex(passWord.trim());
       if (getPassword().equals(passWord)) {
         flag = true;
       }
     }
     return flag;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.member.Member
 * JD-Core Version:    0.5.3
 */