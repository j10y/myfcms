 package com.htsoft.oa.model.customer;
 
 public class CustomerMail
 {
   private String ids;
   private String names;
   private String subject;
   private String mailContent;
   private String files;
   private Short type;
 
   public Short getType()
   {
     return this.type;
   }
   public void setType(Short type) {
     this.type = type;
   }
   public String getIds() {
     return this.ids;
   }
   public void setIds(String ids) {
     this.ids = ids;
   }
   public String getNames() {
     return this.names;
   }
   public void setNames(String names) {
     this.names = names;
   }
   public String getSubject() {
     return this.subject;
   }
   public void setSubject(String subject) {
     this.subject = subject;
   }
   public String getMailContent() {
     return this.mailContent;
   }
   public void setMailContent(String mailContent) {
     this.mailContent = mailContent;
   }
   public String getFiles() {
     return this.files;
   }
   public void setFiles(String files) {
     this.files = files;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.customer.CustomerMail
 * JD-Core Version:    0.5.4
 */