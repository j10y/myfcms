 package com.htsoft.oa.model.system;
 
 import com.htsoft.core.model.BaseModel;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class IndexDisplay extends BaseModel
 {
   protected Long indexId;
   protected String portalId;
   protected Integer colNum;
   protected Integer rowNum;
   protected AppUser appUser;
 
   public IndexDisplay()
   {
   }
 
   public IndexDisplay(Long in_indexId)
   {
     setIndexId(in_indexId);
   }
 
   public AppUser getAppUser()
   {
     return this.appUser;
   }
 
   public void setAppUser(AppUser in_appUser) {
     this.appUser = in_appUser;
   }
 
   public Long getIndexId()
   {
     return this.indexId;
   }
 
   public void setIndexId(Long aValue)
   {
     this.indexId = aValue;
   }
 
   public String getPortalId()
   {
     return this.portalId;
   }
 
   public void setPortalId(String aValue)
   {
     this.portalId = aValue;
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
 
   public Integer getColNum()
   {
     return this.colNum;
   }
 
   public void setColNum(Integer aValue)
   {
     this.colNum = aValue;
   }
 
   public Integer getRowNum()
   {
     return this.rowNum;
   }
 
   public void setRowNum(Integer aValue)
   {
     this.rowNum = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof IndexDisplay) {
       return false;
     }
     IndexDisplay rhs = (IndexDisplay)object;
     return new EqualsBuilder()
       .append(this.indexId, rhs.indexId)
       .append(this.portalId, rhs.portalId)
       .append(this.colNum, rhs.colNum)
       .append(this.rowNum, rhs.rowNum)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.indexId)
       .append(this.portalId)
       .append(this.colNum)
       .append(this.rowNum)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("indexId", this.indexId)
       .append("portalId", this.portalId)
       .append("colNum", this.colNum)
       .append("rowNum", this.rowNum)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.system.IndexDisplay
 * JD-Core Version:    0.5.4
 */