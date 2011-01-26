 package com.htsoft.oa.model.info;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.AppUser;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class AppTips extends BaseModel
 {
 
   @Expose
   protected Long tipsId;
 
   @Expose
   protected String tipsName;
 
   @Expose
   protected String content;
 
   @Expose
   protected Integer disheight;
 
   @Expose
   protected Integer diswidth;
 
   @Expose
   protected Integer disleft;
 
   @Expose
   protected Integer distop;
 
   @Expose
   protected Integer dislevel;
 
   @Expose
   protected Date createTime;
   protected AppUser appUser;
 
   public AppTips()
   {
   }
 
   public AppTips(Long in_tipsId)
   {
     setTipsId(in_tipsId);
   }
 
   public AppUser getAppUser()
   {
     return this.appUser;
   }
 
   public void setAppUser(AppUser in_appUser) {
     this.appUser = in_appUser;
   }
 
   public Long getTipsId()
   {
     return this.tipsId;
   }
 
   public void setTipsId(Long aValue)
   {
     this.tipsId = aValue;
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
 
   public String getTipsName()
   {
     return this.tipsName;
   }
 
   public void setTipsName(String aValue)
   {
     this.tipsName = aValue;
   }
 
   public String getContent()
   {
     return this.content;
   }
 
   public void setContent(String aValue)
   {
     this.content = aValue;
   }
 
   public Integer getDisheight()
   {
     return this.disheight;
   }
 
   public void setDisheight(Integer aValue)
   {
     this.disheight = aValue;
   }
 
   public Integer getDiswidth()
   {
     return this.diswidth;
   }
 
   public void setDiswidth(Integer aValue)
   {
     this.diswidth = aValue;
   }
 
   public Integer getDisleft()
   {
     return this.disleft;
   }
 
   public void setDisleft(Integer aValue)
   {
     this.disleft = aValue;
   }
 
   public Integer getDistop()
   {
     return this.distop;
   }
 
   public void setDistop(Integer aValue)
   {
     this.distop = aValue;
   }
 
   public Integer getDislevel()
   {
     return this.dislevel;
   }
 
   public void setDislevel(Integer aValue)
   {
     this.dislevel = aValue;
   }
 
   public Date getCreateTime()
   {
     return this.createTime;
   }
 
   public void setCreateTime(Date aValue)
   {
     this.createTime = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof AppTips) {
       return false;
     }
     AppTips rhs = (AppTips)object;
     return new EqualsBuilder()
       .append(this.tipsId, rhs.tipsId)
       .append(this.tipsName, rhs.tipsName)
       .append(this.content, rhs.content)
       .append(this.disheight, rhs.disheight)
       .append(this.diswidth, rhs.diswidth)
       .append(this.disleft, rhs.disleft)
       .append(this.distop, rhs.distop)
       .append(this.dislevel, rhs.dislevel)
       .append(this.createTime, rhs.createTime)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.tipsId)
       .append(this.tipsName)
       .append(this.content)
       .append(this.disheight)
       .append(this.diswidth)
       .append(this.disleft)
       .append(this.distop)
       .append(this.dislevel)
       .append(this.createTime)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("tipsId", this.tipsId)
       .append("tipsName", this.tipsName)
       .append("content", this.content)
       .append("disheight", this.disheight)
       .append("diswidth", this.diswidth)
       .append("disleft", this.disleft)
       .append("distop", this.distop)
       .append("dislevel", this.dislevel)
       .append("createTime", this.createTime)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.info.AppTips
 * JD-Core Version:    0.5.4
 */