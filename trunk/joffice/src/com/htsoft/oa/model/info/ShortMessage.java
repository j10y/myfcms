 package com.htsoft.oa.model.info;
 
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Set;
 
 public class ShortMessage extends BaseModel
 {
   private Long messageId;
   private String content;
   private Long senderId;
   private String sender;
   private Short msgType;
   private Date sendTime;
   public static final Short MSG_TYPE_PERSONAL = 1;
   public static final Short MSG_TYPE_CALENDAR = 2;
   public static final Short MSG_TYPE_PLAN = 3;
   public static final Short MSG_TYPE_TASK = 4;
   public static final Short MSG_TYPE_SYS = 5;
 
   private Set<InMessage> messages = new HashSet();
 
   public Set<InMessage> getMessages()
   {
     return this.messages;
   }
 
   public void setMessages(Set<InMessage> messages)
   {
     this.messages = messages;
   }
 
   public Long getMessageId() {
     return this.messageId;
   }
 
   public void setMessageId(Long messageId) {
     this.messageId = messageId;
   }
 
   public String getContent() {
     return this.content;
   }
 
   public void setContent(String content) {
     this.content = content;
   }
 
   public Long getSenderId() {
     return this.senderId;
   }
 
   public void setSenderId(Long senderId) {
     this.senderId = senderId;
   }
 
   public String getSender() {
     return this.sender;
   }
 
   public void setSender(String sender) {
     this.sender = sender;
   }
 
   public Short getMsgType() {
     return this.msgType;
   }
 
   public void setMsgType(Short msgType) {
     this.msgType = msgType;
   }
 
   public Date getSendTime() {
     return this.sendTime;
   }
 
   public void setSendTime(Date sendTime) {
     this.sendTime = sendTime;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.info.ShortMessage
 * JD-Core Version:    0.5.4
 */