 package com.htsoft.oa.model.admin;
 
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class DepreRecord extends BaseModel
 {
   protected Long recordId;
   protected BigDecimal workCapacity;
   protected BigDecimal depreAmount;
   protected Date calTime;
   protected FixedAssets fixedAssets;
 
   public DepreRecord()
   {
   }
 
   public DepreRecord(Long in_recordId)
   {
     setRecordId(in_recordId);
   }
 
   public FixedAssets getFixedAssets()
   {
     return this.fixedAssets;
   }
 
   public void setFixedAssets(FixedAssets in_fixedAssets) {
     this.fixedAssets = in_fixedAssets;
   }
 
   public Long getRecordId()
   {
     return this.recordId;
   }
 
   public void setRecordId(Long aValue)
   {
     this.recordId = aValue;
   }
 
   public Long getAssetsId()
   {
     return (getFixedAssets() == null) ? null : getFixedAssets().getAssetsId();
   }
 
   public void setAssetsId(Long aValue)
   {
     if (aValue == null) {
       this.fixedAssets = null;
     } else if (this.fixedAssets == null) {
       this.fixedAssets = new FixedAssets(aValue);
       this.fixedAssets.setVersion(new Integer(0));
     } else {
       this.fixedAssets.setAssetsId(aValue);
     }
   }
 
   public BigDecimal getWorkCapacity()
   {
     return this.workCapacity;
   }
 
   public void setWorkCapacity(BigDecimal aValue)
   {
     this.workCapacity = aValue;
   }
 
   public BigDecimal getDepreAmount()
   {
     return this.depreAmount;
   }
 
   public void setDepreAmount(BigDecimal aValue)
   {
     this.depreAmount = aValue;
   }
 
   public Date getCalTime()
   {
     return this.calTime;
   }
 
   public void setCalTime(Date aValue)
   {
     this.calTime = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof DepreRecord) {
       return false;
     }
     DepreRecord rhs = (DepreRecord)object;
     return new EqualsBuilder()
       .append(this.recordId, rhs.recordId)
       .append(this.workCapacity, rhs.workCapacity)
       .append(this.depreAmount, rhs.depreAmount)
       .append(this.calTime, rhs.calTime)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.recordId)
       .append(this.workCapacity)
       .append(this.depreAmount)
       .append(this.calTime)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("recordId", this.recordId)
       .append("workCapacity", this.workCapacity)
       .append("depreAmount", this.depreAmount)
       .append("calTime", this.calTime)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.DepreRecord
 * JD-Core Version:    0.5.4
 */