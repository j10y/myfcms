 package com.htsoft.oa.model.info;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import java.util.Set;
 
 public class NewsType extends BaseModel
 {
 
   @Expose
   private Long typeId;
 
   @Expose
   private String typeName;
 
   @Expose
   private Short sn;
   private Set<News> news;
 
   public Long getTypeId()
   {
     return this.typeId;
   }
   public void setTypeId(Long typeId) {
     this.typeId = typeId;
   }
   public String getTypeName() {
     return this.typeName;
   }
   public void setTypeName(String typeName) {
     this.typeName = typeName;
   }
   public Short getSn() {
     return this.sn;
   }
   public void setSn(Short sn) {
     this.sn = sn;
   }
   public Set<News> getNews() {
     return this.news;
   }
   public void setNews(Set<News> news) {
     this.news = news;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.info.NewsType
 * JD-Core Version:    0.5.4
 */