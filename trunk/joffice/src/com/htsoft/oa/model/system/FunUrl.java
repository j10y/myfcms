 package com.htsoft.oa.model.system;
 
 import com.htsoft.core.model.BaseModel;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class FunUrl extends BaseModel
 {
   protected Long urlId;
   protected String urlPath;
   protected AppFunction appFunction;
 
   public FunUrl()
   {
   }
 
   public FunUrl(String urlPath)
   {
     this.urlPath = urlPath;
   }
 
   public FunUrl(Long in_urlId)
   {
     setUrlId(in_urlId);
   }
 
   public AppFunction getAppFunction()
   {
     return this.appFunction;
   }
 
   public void setAppFunction(AppFunction in_appFunction) {
     this.appFunction = in_appFunction;
   }
 
   public Long getUrlId()
   {
     return this.urlId;
   }
 
   public void setUrlId(Long aValue)
   {
     this.urlId = aValue;
   }
 
   public Long getFunctionId()
   {
     return (getAppFunction() == null) ? null : getAppFunction().getFunctionId();
   }
 
   public void setFunctionId(Long aValue)
   {
     if (aValue == null) {
       this.appFunction = null;
     } else if (this.appFunction == null) {
       this.appFunction = new AppFunction(aValue);
       this.appFunction.setVersion(new Integer(0));
     } else {
       this.appFunction.setFunctionId(aValue);
     }
   }
 
   public String getUrlPath()
   {
     return this.urlPath;
   }
 
   public void setUrlPath(String aValue)
   {
     this.urlPath = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!(object instanceof FunUrl)) {
       return false;
     }
     FunUrl rhs = (FunUrl)object;
     return new EqualsBuilder()
       .append(this.urlId, rhs.urlId)
       .append(this.urlPath, rhs.urlPath)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.urlId)
       .append(this.urlPath)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("urlId", this.urlId)
       .append("urlPath", this.urlPath)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.system.FunUrl
 * JD-Core Version:    0.5.4
 */