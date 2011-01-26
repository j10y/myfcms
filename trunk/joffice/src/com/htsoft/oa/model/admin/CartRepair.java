 package com.htsoft.oa.model.admin;
 
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class CartRepair extends BaseModel
 {
   protected Long repairId;
   protected Date repairDate;
   protected String reason;
   protected String executant;
   protected String notes;
   protected String repairType;
   protected BigDecimal fee;
   protected Car car;
 
   public CartRepair()
   {
   }
 
   public CartRepair(Long in_repairId)
   {
     setRepairId(in_repairId);
   }
 
   public Car getCar()
   {
     return this.car;
   }
 
   public void setCar(Car in_car) {
     this.car = in_car;
   }
 
   public Long getRepairId()
   {
     return this.repairId;
   }
 
   public void setRepairId(Long aValue)
   {
     this.repairId = aValue;
   }
 
   public Long getCarId()
   {
     return (getCar() == null) ? null : getCar().getCarId();
   }
 
   public void setCarId(Long aValue)
   {
     if (aValue == null) {
       this.car = null;
     } else if (this.car == null) {
       this.car = new Car(aValue);
       this.car.setVersion(new Integer(0));
     } else {
       this.car.setCarId(aValue);
     }
   }
 
   public Date getRepairDate()
   {
     return this.repairDate;
   }
 
   public void setRepairDate(Date aValue)
   {
     this.repairDate = aValue;
   }
 
   public String getReason()
   {
     return this.reason;
   }
 
   public void setReason(String aValue)
   {
     this.reason = aValue;
   }
 
   public String getExecutant()
   {
     return this.executant;
   }
 
   public void setExecutant(String aValue)
   {
     this.executant = aValue;
   }
 
   public String getNotes()
   {
     return this.notes;
   }
 
   public void setNotes(String aValue)
   {
     this.notes = aValue;
   }
 
   public String getRepairType()
   {
     return this.repairType;
   }
 
   public void setRepairType(String aValue)
   {
     this.repairType = aValue;
   }
 
   public BigDecimal getFee()
   {
     return this.fee;
   }
 
   public void setFee(BigDecimal aValue)
   {
     this.fee = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof CartRepair) {
       return false;
     }
     CartRepair rhs = (CartRepair)object;
     return new EqualsBuilder()
       .append(this.repairId, rhs.repairId)
       .append(this.repairDate, rhs.repairDate)
       .append(this.reason, rhs.reason)
       .append(this.executant, rhs.executant)
       .append(this.notes, rhs.notes)
       .append(this.repairType, rhs.repairType)
       .append(this.fee, rhs.fee)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.repairId)
       .append(this.repairDate)
       .append(this.reason)
       .append(this.executant)
       .append(this.notes)
       .append(this.repairType)
       .append(this.fee)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("repairId", this.repairId)
       .append("repairDate", this.repairDate)
       .append("reason", this.reason)
       .append("executant", this.executant)
       .append("notes", this.notes)
       .append("repairType", this.repairType)
       .append("fee", this.fee)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.CartRepair
 * JD-Core Version:    0.5.4
 */