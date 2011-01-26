 package com.htsoft.oa.model.info;
 
 import java.util.Date;
 
 public class Notice
 {
   private long noticeId;
   private String postName;
   private String noticeTitle;
   private String noticeContent;
   private Date effectiveDate;
   private Date expirationDate;
   private short state;
 
   public String getPostName()
   {
     return this.postName;
   }
   public long getNoticeId() {
     return this.noticeId;
   }
   public void setNoticeId(long noticeId) {
     this.noticeId = noticeId;
   }
   public void setPostName(String postName) {
     this.postName = postName;
   }
   public String getNoticeTitle() {
     return this.noticeTitle;
   }
   public void setNoticeTitle(String noticeTitle) {
     this.noticeTitle = noticeTitle;
   }
 
   public String getNoticeContent() {
     return this.noticeContent;
   }
   public void setNoticeContent(String noticeContent) {
     this.noticeContent = noticeContent;
   }
   public Date getEffectiveDate() {
     return this.effectiveDate;
   }
   public void setEffectiveDate(Date effectiveDate) {
     this.effectiveDate = effectiveDate;
   }
   public Date getExpirationDate() {
     return this.expirationDate;
   }
   public void setExpirationDate(Date expirationDate) {
     this.expirationDate = expirationDate;
   }
   public short getState() {
     return this.state;
   }
   public void setState(short state) {
     this.state = state;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.info.Notice
 * JD-Core Version:    0.5.4
 */