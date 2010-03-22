 package com.zving.cms.api;
 
 import com.zving.framework.data.Transaction;
 import com.zving.framework.orm.Schema;
 import com.zving.framework.utility.Errorx;
 import com.zving.framework.utility.Mapx;
 import com.zving.framework.utility.StringUtil;
 import com.zving.schema.ZDMemberSchema;
 import java.util.Iterator;
 import java.util.Set;
 
 public class MemberAPI
   implements APIInterface
 {
   private Mapx params;
 
   public boolean delete()
   {
     String UserName = this.params.getString("UserName");
     if (StringUtil.isNotEmpty(UserName)) {
       ZDMemberSchema member = new ZDMemberSchema();
       member.setUserName(UserName);
       if (!(member.fill())) {
         return false;
       }
       Transaction trans = new Transaction();
       trans.add(member, 3);
 
       return (trans.commit());
     }
 
     return false;
   }
 
   public long insert()
   {
     return insert(new Transaction());
   }
 
   public long insert(Transaction trans) {
     String UserName = this.params.getString("UserName");
     String PassWord = this.params.getString("PassWord");
     String realname = this.params.getString("RealName");
     String Email = this.params.getString("Email");
     String Type = "none";
     String Status = "N";
     if ((StringUtil.isEmpty(UserName)) || (StringUtil.isEmpty(PassWord)) || (StringUtil.isEmpty(Email))) {
       return -1L;
     }
     if (UserName.length() > 20) {
       Errorx.addError("会员名最多20位");
       return -1L;
     }
     ZDMemberSchema member = new ZDMemberSchema();
     member.setUserName(UserName);
     if (member.fill()) {
       Errorx.addError(UserName + "会员已经存在!");
       return -1L;
     }
 
     member.setName(realname);
     member.setPassword(StringUtil.md5Hex(PassWord));
     member.setEmail(Email);
     member.setType(Type);
     member.setStatus(Status);
     trans.add(member, 1);
     if (trans.commit())
       return 1L;
     return -1L;
   }
 
   public boolean setSchema(Schema schema) {
     return false;
   }
 
   public boolean update() {
     String UserName = this.params.getString("UserName");
     String realname = this.params.getString("RealName");
     String PassWord = this.params.getString("PassWord");
     String Email = this.params.getString("Email");
     String Type = "none";
     String Status = "N";
     if ((StringUtil.isEmpty(UserName)) || (StringUtil.isEmpty(PassWord)) || (StringUtil.isEmpty(Email))) {
       return false;
     }
     if (UserName.length() > 20) {
       Errorx.addError("会员名最多20位");
       return false;
     }
     if (!(StringUtil.isNotEmpty(UserName))) {
       Errorx.addError(UserName + "会员不存在!");
       return false;
     }
     Transaction trans = new Transaction();
     ZDMemberSchema member = new ZDMemberSchema();
     member.setUserName(UserName);
     member.fill();
     member.setName(realname);
     member.setPassword(StringUtil.md5Hex(PassWord));
     member.setEmail(Email);
     member.setType(Type);
     member.setStatus(Status);
     trans.add(member, 2);
     return trans.commit();
   }
 
   public Mapx getParams() {
     return this.params;
   }
 
   public void setParams(Mapx params) {
     convertParams(params);
     this.params = params;
   }
 
   public void convertParams(Mapx params) {
     Iterator iter = params.keySet().iterator();
     while (iter.hasNext()) {
       Object key = iter.next();
       String value = params.getString(key);
       if ((StringUtil.isEmpty(value)) || ("null".equalsIgnoreCase(value)))
         this.params.put(key, "");
     }
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.api.MemberAPI
 * JD-Core Version:    0.5.3
 */