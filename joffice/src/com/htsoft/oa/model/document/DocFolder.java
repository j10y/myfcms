 package com.htsoft.oa.model.document;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import com.htsoft.oa.model.system.AppUser;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class DocFolder extends BaseModel
 {
   public static final Short IS_SHARED = Short.valueOf(1);
   public static final Short IS_NOT_SHARED = Short.valueOf(0);
 
   @Expose
   protected Long folderId;
 
   @Expose
   protected String folderName;
 
   @Expose
   protected Long parentId;
 
   @Expose
   protected String path;
 
   @Expose
   protected Short isShared;
 
   @Expose
   protected AppUser appUser;
 
   public DocFolder()
   {
   }
 
   public DocFolder(Long in_folderId)
   {
     setFolderId(in_folderId);
   }
 
   public AppUser getAppUser()
   {
     return this.appUser;
   }
 
   public void setAppUser(AppUser in_appUser) {
     this.appUser = in_appUser;
   }
 
   public Long getFolderId()
   {
     return this.folderId;
   }
 
   public void setFolderId(Long aValue)
   {
     this.folderId = aValue;
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
 
   public String getFolderName()
   {
     return this.folderName;
   }
 
   public void setFolderName(String aValue)
   {
     this.folderName = aValue;
   }
 
   public Long getParentId()
   {
     return this.parentId;
   }
 
   public void setParentId(Long aValue)
   {
     this.parentId = aValue;
   }
 
   public String getPath()
   {
     return this.path;
   }
 
   public void setPath(String aValue)
   {
     this.path = aValue;
   }
 
   public Short getIsShared()
   {
     return this.isShared;
   }
 
   public void setIsShared(Short aValue)
   {
     this.isShared = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof DocFolder) {
       return false;
     }
     DocFolder rhs = (DocFolder)object;
     return new EqualsBuilder()
       .append(this.folderId, rhs.folderId)
       .append(this.folderName, rhs.folderName)
       .append(this.parentId, rhs.parentId)
       .append(this.path, rhs.path)
       .append(this.isShared, rhs.isShared)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.folderId)
       .append(this.folderName)
       .append(this.parentId)
       .append(this.path)
       .append(this.isShared)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("folderId", this.folderId)
       .append("folderName", this.folderName)
       .append("parentId", this.parentId)
       .append("path", this.path)
       .append("isShared", this.isShared)
       .toString();
   }
 
   public String getFirstKeyColumnName()
   {
     return "folderId";
   }
 
   public Long getId()
   {
     return this.folderId;
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.document.DocFolder
 * JD-Core Version:    0.5.4
 */