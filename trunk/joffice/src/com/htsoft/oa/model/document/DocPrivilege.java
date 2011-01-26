 package com.htsoft.oa.model.document;
 
 import com.htsoft.core.model.BaseModel;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class DocPrivilege extends BaseModel
 {
   protected Long privilegeId;
   protected Integer rights;
   protected Integer udrId;
   protected String udrName;
   protected Short flag;
   protected Short fdFlag;
   protected Document document;
   protected DocFolder docFolder;
 
   public DocPrivilege()
   {
   }
 
   public DocPrivilege(Long in_privilegeId)
   {
     setPrivilegeId(in_privilegeId);
   }
 
   public Document getDocument()
   {
     return this.document;
   }
 
   public void setDocument(Document in_document) {
     this.document = in_document;
   }
 
   public DocFolder getDocFolder() {
     return this.docFolder;
   }
 
   public void setDocFolder(DocFolder in_docFolder) {
     this.docFolder = in_docFolder;
   }
 
   public Long getPrivilegeId()
   {
     return this.privilegeId;
   }
 
   public void setPrivilegeId(Long aValue)
   {
     this.privilegeId = aValue;
   }
 
   public Long getFolderId()
   {
     return (getDocFolder() == null) ? null : getDocFolder().getFolderId();
   }
 
   public void setFolderId(Long aValue)
   {
     if (aValue == null) {
       this.docFolder = null;
     } else if (this.docFolder == null) {
       this.docFolder = new DocFolder(aValue);
       this.docFolder.setVersion(new Integer(0));
     } else {
       this.docFolder.setFolderId(aValue);
     }
   }
 
   public Long getDocId()
   {
     return (getDocument() == null) ? null : getDocument().getDocId();
   }
 
   public void setDocId(Long aValue)
   {
     if (aValue == null) {
       this.document = null;
     } else if (this.document == null) {
       this.document = new Document(aValue);
       this.document.setVersion(new Integer(0));
     } else {
       this.document.setDocId(aValue);
     }
   }
 
   public Integer getRights()
   {
     return this.rights;
   }
 
   public void setRights(Integer aValue)
   {
     this.rights = aValue;
   }
 
   public Integer getUdrId()
   {
     return this.udrId;
   }
 
   public void setUdrId(Integer aValue)
   {
     this.udrId = aValue;
   }
 
   public String getUdrName()
   {
     return this.udrName;
   }
 
   public void setUdrName(String aValue)
   {
     this.udrName = aValue;
   }
 
   public Short getFlag()
   {
     return this.flag;
   }
 
   public void setFlag(Short aValue)
   {
     this.flag = aValue;
   }
 
   public Short getFdFlag() {
     return this.fdFlag;
   }
 
   public void setFdFlag(Short fdFlag) {
     this.fdFlag = fdFlag;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof DocPrivilege) {
       return false;
     }
     DocPrivilege rhs = (DocPrivilege)object;
     return new EqualsBuilder()
       .append(this.privilegeId, rhs.privilegeId)
       .append(this.rights, rhs.rights)
       .append(this.udrId, rhs.udrId)
       .append(this.udrName, rhs.udrName)
       .append(this.flag, rhs.flag)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.privilegeId)
       .append(this.rights)
       .append(this.udrId)
       .append(this.udrName)
       .append(this.flag)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("privilegeId", this.privilegeId)
       .append("rights", this.rights)
       .append("udrId", this.udrId)
       .append("udrName", this.udrName)
       .append("flag", this.flag)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.document.DocPrivilege
 * JD-Core Version:    0.5.4
 */