 package com.htsoft.oa.model.hrm;
 
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class StandSalaryItem extends BaseModel
 {
   protected Long itemId;
   protected String itemName;
   protected BigDecimal amount;
   protected Long salaryItemId;
   protected StandSalary standSalary;
 
   public StandSalaryItem()
   {
   }
 
   public StandSalaryItem(Long in_itemId)
   {
     setItemId(in_itemId);
   }
 
   public StandSalary getStandSalary()
   {
     return this.standSalary;
   }
 
   public void setStandSalary(StandSalary in_standSalary) {
     this.standSalary = in_standSalary;
   }
 
   public Long getItemId()
   {
     return this.itemId;
   }
 
   public void setItemId(Long aValue)
   {
     this.itemId = aValue;
   }
 
   public Long getStandardId()
   {
     return (getStandSalary() == null) ? null : getStandSalary().getStandardId();
   }
 
   public void setStandardId(Long aValue)
   {
     if (aValue == null) {
       this.standSalary = null;
     } else if (this.standSalary == null) {
       this.standSalary = new StandSalary(aValue);
       this.standSalary.setVersion(new Integer(0));
     } else {
       this.standSalary.setStandardId(aValue);
     }
   }
 
   public String getItemName()
   {
     return this.itemName;
   }
 
   public void setItemName(String aValue)
   {
     this.itemName = aValue;
   }
 
   public BigDecimal getAmount()
   {
     return this.amount;
   }
 
   public void setAmount(BigDecimal aValue)
   {
     this.amount = aValue;
   }
 
   public Long getSalaryItemId()
   {
     return this.salaryItemId;
   }
 
   public void setSalaryItemId(Long aValue)
   {
     this.salaryItemId = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof StandSalaryItem) {
       return false;
     }
     StandSalaryItem rhs = (StandSalaryItem)object;
     return new EqualsBuilder()
       .append(this.itemId, rhs.itemId)
       .append(this.itemName, rhs.itemName)
       .append(this.amount, rhs.amount)
       .append(this.salaryItemId, rhs.salaryItemId)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.itemId)
       .append(this.itemName)
       .append(this.amount)
       .append(this.salaryItemId)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("itemId", this.itemId)
       .append("itemName", this.itemName)
       .append("amount", this.amount)
       .append("salaryItemId", this.salaryItemId)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.hrm.StandSalaryItem
 * JD-Core Version:    0.5.4
 */