 package com.htsoft.oa.model.info;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import java.util.Date;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class News extends BaseModel
 {
   public static Short ISDESKNEWS =  1 ;
   public static Short NOTDESKNEWS =  0 ;
 
   @Expose
   protected Long newsId;
 
   @Expose
   protected String subjectIcon;
 
   @Expose
   protected String subject;
 
   @Expose
   protected String author;
 
   @Expose
   protected Date createtime;
 
   @Expose
   protected Integer replyCounts;
 
   @Expose
   protected Integer viewCounts;
 
   @Expose
   protected String content;
 
   @Expose
   protected Date updateTime;
 
   @Expose
   protected Short status;
 
   @Expose
   protected NewsType newsType;
 
   @Expose
   protected String issuer;
 
   @Expose
   protected Short isDeskImage;
   protected Set<NewsComment> newsComments = new HashSet();
 
   public News()
   {
   }
 
   public News(Long in_newsId)
   {
     setNewsId(in_newsId);
   }
 
   public Short getIsDeskImage()
   {
     return this.isDeskImage;
   }
 
   public void setIsDeskImage(Short isDeskImage) {
     this.isDeskImage = isDeskImage;
   }
 
   public Set<NewsComment> getNewsComments() {
     return this.newsComments;
   }
 
   public void setNewsComments(Set<NewsComment> newsComments) {
     this.newsComments = newsComments;
   }
 
   public NewsType getNewsType() {
     return this.newsType;
   }
 
   public void setNewsType(NewsType in_newsType) {
     this.newsType = in_newsType;
   }
 
   public Long getNewsId()
   {
     return this.newsId;
   }
 
   public void setNewsId(Long aValue)
   {
     this.newsId = aValue;
   }
 
   public Long getTypeId()
   {
     return (getNewsType() == null) ? null : getNewsType().getTypeId();
   }
 
   public void setTypeId(Long aValue)
   {
     if (aValue == null) {
       this.newsType = null;
     } else if (this.newsType == null) {
       this.newsType = new NewsType();
       this.newsType.setTypeId(aValue);
     } else {
       this.newsType.setTypeId(aValue);
     }
   }
 
   public String getSubjectIcon()
   {
     return this.subjectIcon;
   }
 
   public void setSubjectIcon(String aValue)
   {
     this.subjectIcon = aValue;
   }
 
   public String getSubject()
   {
     return this.subject;
   }
 
   public void setSubject(String aValue)
   {
     this.subject = aValue;
   }
 
   public String getAuthor()
   {
     return this.author;
   }
 
   public void setAuthor(String aValue)
   {
     this.author = aValue;
   }
 
   public Date getCreatetime()
   {
     return this.createtime;
   }
 
   public void setCreatetime(Date aValue)
   {
     this.createtime = aValue;
   }
 
   public Integer getReplyCounts()
   {
     return this.replyCounts;
   }
 
   public void setReplyCounts(Integer aValue)
   {
     this.replyCounts = aValue;
   }
 
   public Integer getViewCounts()
   {
     return this.viewCounts;
   }
 
   public void setViewCounts(Integer aValue)
   {
     this.viewCounts = aValue;
   }
 
   public String getContent()
   {
     return this.content;
   }
 
   public void setContent(String aValue)
   {
     this.content = aValue;
   }
 
   public Date getUpdateTime()
   {
     return this.updateTime;
   }
 
   public void setUpdateTime(Date aValue)
   {
     this.updateTime = aValue;
   }
 
   public Short getStatus()
   {
     return this.status;
   }
 
   public void setStatus(Short aValue)
   {
     this.status = aValue;
   }
 
   public String getIssuer() {
     return this.issuer;
   }
 
   public void setIssuer(String issuer) {
     this.issuer = issuer;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof News)) {
       return false;
     }
     News rhs = (News)object;
     return new EqualsBuilder()
       .append(this.newsId, rhs.newsId)
       .append(this.subjectIcon, rhs.subjectIcon)
       .append(this.subject, rhs.subject)
       .append(this.author, rhs.author)
       .append(this.createtime, rhs.createtime)
       .append(this.replyCounts, rhs.replyCounts)
       .append(this.viewCounts, rhs.viewCounts)
       .append(this.content, rhs.content)
       .append(this.updateTime, rhs.updateTime)
       .append(this.status, rhs.status)
       .append(this.issuer, rhs.issuer)
       .append(this.isDeskImage, rhs.isDeskImage)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.newsId)
       .append(this.subjectIcon)
       .append(this.subject)
       .append(this.author)
       .append(this.createtime)
       .append(this.replyCounts)
       .append(this.viewCounts)
       .append(this.content)
       .append(this.updateTime)
       .append(this.status)
       .append(this.issuer)
       .append(this.isDeskImage)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("newsId", this.newsId)
       .append("subjectIcon", this.subjectIcon)
       .append("subject", this.subject)
       .append("author", this.author)
       .append("createtime", this.createtime)
       .append("replyCounts", this.replyCounts)
       .append("viewCounts", this.viewCounts)
       .append("content", this.content)
       .append("updateTime", this.updateTime)
       .append("status", this.status)
       .append("issuer", this.issuer)
       .append("isDeskImage", this.isDeskImage)
       .toString();
   }
 
   public String getFirstKeyColumnName()
   {
     return "newsId";
   }
 
   public Long getId()
   {
     return this.newsId;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.info.News
 * JD-Core Version:    0.5.4
 */