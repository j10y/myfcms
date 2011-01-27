 package com.htsoft.oa.model.info;
 
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.AppUser;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class NewsComment extends BaseModel
 {
   protected Long commentId;
   protected String content;
   protected Date createtime;
   protected String fullname;
   protected News news;
   protected AppUser appUser;
 
   public NewsComment()
   {
   }
 
   public NewsComment(Long in_commentId)
   {
     setCommentId(in_commentId);
   }
 
   public News getNews()
   {
     return this.news;
   }
 
   public void setNews(News in_news) {
     this.news = in_news;
   }
 
   public AppUser getAppUser() {
     return this.appUser;
   }
 
   public void setAppUser(AppUser in_appUser) {
     this.appUser = in_appUser;
   }
 
   public Long getCommentId()
   {
     return this.commentId;
   }
 
   public void setCommentId(Long aValue)
   {
     this.commentId = aValue;
   }
 
   public Long getNewsId()
   {
     return (getNews() == null) ? null : getNews().getNewsId();
   }
 
   public void setNewsId(Long aValue)
   {
     if (aValue == null) {
       this.news = null;
     } else if (this.news == null) {
       this.news = new News(aValue);
       this.news.setVersion(new Integer(0));
     } else {
       this.news.setNewsId(aValue);
     }
   }
 
   public String getContent()
   {
     return this.content;
   }
 
   public void setContent(String aValue)
   {
     this.content = aValue;
   }
 
   public Date getCreatetime()
   {
     return this.createtime;
   }
 
   public void setCreatetime(Date aValue)
   {
     this.createtime = aValue;
   }
 
   public String getFullname()
   {
     return this.fullname;
   }
 
   public void setFullname(String aValue)
   {
     this.fullname = aValue;
   }
 
   public Long getUserId()
   {
     return (getAppUser() == null) ? null : getAppUser().getUserId();
   }
 
   public void setUserId(Long aValue)
   {
     if (aValue == null) {
       this.appUser = null;
     } else if (this.appUser == null) {
       this.appUser = new AppUser(aValue);
       this.appUser.setVersion(new Integer(0));
     } else {
       this.appUser.setUserId(aValue);
     }
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof NewsComment)) {
       return false;
     }
     NewsComment rhs = (NewsComment)object;
     return new EqualsBuilder()
       .append(this.commentId, rhs.commentId)
       .append(this.content, rhs.content)
       .append(this.createtime, rhs.createtime)
       .append(this.fullname, rhs.fullname)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.commentId)
       .append(this.content)
       .append(this.createtime)
       .append(this.fullname)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("commentId", this.commentId)
       .append("content", this.content)
       .append("createtime", this.createtime)
       .append("fullname", this.fullname)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.info.NewsComment
 * JD-Core Version:    0.5.4
 */