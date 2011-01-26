 package com.htsoft.oa.model.system;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 
 public class Department extends BaseModel
 {
 
   @Expose
   private Long depId;
 
   @Expose
   private String depName;
 
   @Expose
   private String depDesc;
 
   @Expose
   private Integer depLevel;
 
   @Expose
   private Long parentId;
 
   @Expose
   private String path;
 
   public Department()
   {
   }
 
   public Department(Long depId)
   {
     setDepId(depId);
   }
 
   public Long getDepId() {
     return this.depId;
   }
 
   public void setDepId(Long depId) {
     this.depId = depId;
   }
 
   public String getDepName() {
     return this.depName;
   }
 
   public void setDepName(String depName) {
     this.depName = depName;
   }
 
   public String getDepDesc() {
     return this.depDesc;
   }
 
   public void setDepDesc(String depDesc) {
     this.depDesc = depDesc;
   }
 
   public Integer getDepLevel() {
     return this.depLevel;
   }
 
   public void setDepLevel(Integer depLevel) {
     this.depLevel = depLevel;
   }
 
   public Long getParentId() {
     return this.parentId;
   }
 
   public void setParentId(Long parentId) {
     this.parentId = parentId;
   }
 
   public String getPath() {
     return this.path;
   }
 
   public void setPath(String path) {
     this.path = path;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.system.Department
 * JD-Core Version:    0.5.4
 */