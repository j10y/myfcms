 package com.zving.cms.template;
 
 public class HtmlNameRule
 {
   private int level;
   private String nameRuleStr;
   private String fullPath;
   private String dirPath;
   private String fileName;
 
   public String getDirPath()
   {
     return this.dirPath;
   }
 
   public void setDirPath(String dirPath) {
     this.dirPath = dirPath;
   }
 
   public String getFullPath() {
     return this.fullPath;
   }
 
   public void setFullPath(String fullPath) {
     this.fullPath = fullPath;
   }
 
   public int getLevel() {
     return this.level;
   }
 
   public void setLevel(int level) {
     this.level = level;
   }
 
   public String getNameRuleStr() {
     return this.nameRuleStr;
   }
 
   public void setNameRuleStr(String nameRuleStr) {
     this.nameRuleStr = nameRuleStr;
   }
 
   public String getFileName() {
     return this.fileName;
   }
 
   public void setFileName(String fileName) {
     this.fileName = fileName;
   }
 }

/* Location:           F:\JAVA\Tomcat5.5\webapps\zcms\WEB-INF\classes\
 * Qualified Name:     com.zving.cms.template.HtmlNameRule
 * JD-Core Version:    0.5.3
 */