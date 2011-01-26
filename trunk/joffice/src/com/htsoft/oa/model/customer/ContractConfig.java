 package com.htsoft.oa.model.customer;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class ContractConfig extends BaseModel
 {
 
   @Expose
   protected Long configId;
 
   @Expose
   protected String itemName;
 
   @Expose
   protected String itemSpec;
 
   @Expose
   protected BigDecimal amount;
 
   @Expose
   protected String notes;
   protected Contract contract;
 
   public ContractConfig()
   {
   }
 
   public ContractConfig(Long in_configId)
   {
     setConfigId(in_configId);
   }
 
   public Contract getContract()
   {
     return this.contract;
   }
 
   public void setContract(Contract in_contract) {
     this.contract = in_contract;
   }
 
   public Long getConfigId()
   {
     return this.configId;
   }
 
   public void setConfigId(Long aValue)
   {
     this.configId = aValue;
   }
 
   public String getItemName()
   {
     return this.itemName;
   }
 
   public void setItemName(String aValue)
   {
     this.itemName = aValue;
   }
 
   public Long getContractId()
   {
     return (getContract() == null) ? null : getContract().getContractId();
   }
 
   public void setContractId(Long aValue)
   {
     if (aValue == null) {
       this.contract = null;
     } else if (this.contract == null) {
       this.contract = new Contract(aValue);
       this.contract.setVersion(new Integer(0));
     } else {
       this.contract.setContractId(aValue);
     }
   }
 
   public String getItemSpec()
   {
     return this.itemSpec;
   }
 
   public void setItemSpec(String aValue)
   {
     this.itemSpec = aValue;
   }
 
   public BigDecimal getAmount()
   {
     return this.amount;
   }
 
   public void setAmount(BigDecimal aValue)
   {
     this.amount = aValue;
   }
 
   public String getNotes()
   {
     return this.notes;
   }
 
   public void setNotes(String aValue)
   {
     this.notes = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof ContractConfig) {
       return false;
     }
     ContractConfig rhs = (ContractConfig)object;
     return new EqualsBuilder()
       .append(this.configId, rhs.configId)
       .append(this.itemName, rhs.itemName)
       .append(this.itemSpec, rhs.itemSpec)
       .append(this.amount, rhs.amount)
       .append(this.notes, rhs.notes)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.configId)
       .append(this.itemName)
       .append(this.itemSpec)
       .append(this.amount)
       .append(this.notes)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("configId", this.configId)
       .append("itemName", this.itemName)
       .append("itemSpec", this.itemSpec)
       .append("amount", this.amount)
       .append("notes", this.notes)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.customer.ContractConfig
 * JD-Core Version:    0.5.4
 */