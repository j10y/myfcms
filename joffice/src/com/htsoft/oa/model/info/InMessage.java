 package com.htsoft.oa.model.info;
 
 import com.htsoft.core.model.BaseModel;
 
 public class InMessage extends BaseModel
 {
   public static final Short FLAG_READ =  1 ;
   public static final Short FLAG_UNREAD =  0 ;
   private Long receiveId;
   private ShortMessage shortMessage;
   private Long userId;
   private String userFullname;
   private Short readFlag;
   private Short delFlag;
 
   public Long getReceiveId()
   {
     return this.receiveId;
   }
 
   public ShortMessage getShortMessage()
   {
     return this.shortMessage;
   }
 
   public void setShortMessage(ShortMessage shortMessage) {
     this.shortMessage = shortMessage;
   }
 
   public void setReceiveId(Long receiveId) {
     this.receiveId = receiveId;
   }
 
   public Long getUserId()
   {
     return this.userId;
   }
 
   public void setUserId(Long userId) {
     this.userId = userId;
   }
 
   public String getUserFullname() {
     return this.userFullname;
   }
 
   public void setUserFullname(String userFullname) {
     this.userFullname = userFullname;
   }
 
   public Short getReadFlag() {
     return this.readFlag;
   }
 
   public void setReadFlag(Short readFlag) {
     this.readFlag = readFlag;
   }
 
   public Short getDelFlag() {
     return this.delFlag;
   }
 
   public void setDelFlag(Short delFlag) {
     this.delFlag = delFlag;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.info.InMessage
 * JD-Core Version:    0.5.4
 */