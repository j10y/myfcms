 package com.htsoft.oa.model.system;
 
 import com.htsoft.core.model.BaseModel;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class AppFunction extends BaseModel
 {
   protected Long functionId;
   protected String funKey;
   protected String funName;
   protected Set<FunUrl> funUrls = new HashSet();
 
   public AppFunction()
   {
   }
 
   public AppFunction(String funKey, String funName)
   {
     this.funKey = funKey;
     this.funName = funName;
   }
 
   public AppFunction(Long in_functionId)
   {
     setFunctionId(in_functionId);
   }
 
   public Set<FunUrl> getFunUrls()
   {
     return this.funUrls;
   }
 
   public void setFunUrls(Set in_funUrls) {
     this.funUrls = in_funUrls;
   }
 
   public Long getFunctionId()
   {
     return this.functionId;
   }
 
   public void setFunctionId(Long aValue)
   {
     this.functionId = aValue;
   }
 
   public String getFunKey()
   {
     return this.funKey;
   }
 
   public void setFunKey(String aValue)
   {
     this.funKey = aValue;
   }
 
   public String getFunName()
   {
     return this.funName;
   }
 
   public void setFunName(String aValue)
   {
     this.funName = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof AppFunction) {
       return false;
     }
     AppFunction rhs = (AppFunction)object;
     return new EqualsBuilder()
       .append(this.functionId, rhs.functionId)
       .append(this.funKey, rhs.funKey)
       .append(this.funName, rhs.funName)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.functionId)
       .append(this.funKey)
       .append(this.funName)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("functionId", this.functionId)
       .append("funKey", this.funKey)
       .append("funName", this.funName)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.system.AppFunction
 * JD-Core Version:    0.5.4
 */