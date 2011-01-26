 package com.htsoft.oa.model.admin;
 
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class InStock extends BaseModel
 {
   protected Long buyId;
   protected String providerName;
   protected String stockNo;
   protected BigDecimal price;
   protected Integer inCounts;
   protected BigDecimal amount;
   protected Date inDate;
   protected String buyer;
   protected OfficeGoods officeGoods;
 
   public InStock()
   {
   }
 
   public InStock(Long in_buyId)
   {
     setBuyId(in_buyId);
   }
 
   public OfficeGoods getOfficeGoods()
   {
     return this.officeGoods;
   }
 
   public void setOfficeGoods(OfficeGoods in_officeGoods) {
     this.officeGoods = in_officeGoods;
   }
 
   public Long getBuyId()
   {
     return this.buyId;
   }
 
   public void setBuyId(Long aValue)
   {
     this.buyId = aValue;
   }
 
   public Long getGoodsId()
   {
     return (getOfficeGoods() == null) ? null : getOfficeGoods().getGoodsId();
   }
 
   public void setGoodsId(Long aValue)
   {
     if (aValue == null) {
       this.officeGoods = null;
     } else if (this.officeGoods == null) {
       this.officeGoods = new OfficeGoods(aValue);
       this.officeGoods.setVersion(new Integer(0));
     } else {
       this.officeGoods.setGoodsId(aValue);
     }
   }
 
   public String getProviderName()
   {
     return this.providerName;
   }
 
   public void setProviderName(String aValue)
   {
     this.providerName = aValue;
   }
 
   public String getStockNo()
   {
     return this.stockNo;
   }
 
   public void setStockNo(String aValue)
   {
     this.stockNo = aValue;
   }
 
   public BigDecimal getPrice()
   {
     return this.price;
   }
 
   public void setPrice(BigDecimal aValue)
   {
     this.price = aValue;
   }
 
   public Integer getInCounts()
   {
     return this.inCounts;
   }
 
   public void setInCounts(Integer aValue)
   {
     this.inCounts = aValue;
   }
 
   public BigDecimal getAmount()
   {
     return this.amount;
   }
 
   public void setAmount(BigDecimal aValue)
   {
     this.amount = aValue;
   }
 
   public Date getInDate()
   {
     return this.inDate;
   }
 
   public void setInDate(Date aValue)
   {
     this.inDate = aValue;
   }
 
   public String getBuyer()
   {
     return this.buyer;
   }
 
   public void setBuyer(String aValue)
   {
     this.buyer = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof InStock) {
       return false;
     }
     InStock rhs = (InStock)object;
     return new EqualsBuilder()
       .append(this.buyId, rhs.buyId)
       .append(this.providerName, rhs.providerName)
       .append(this.stockNo, rhs.stockNo)
       .append(this.price, rhs.price)
       .append(this.inCounts, rhs.inCounts)
       .append(this.amount, rhs.amount)
       .append(this.inDate, rhs.inDate)
       .append(this.buyer, rhs.buyer)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.buyId)
       .append(this.providerName)
       .append(this.stockNo)
       .append(this.price)
       .append(this.inCounts)
       .append(this.amount)
       .append(this.inDate)
       .append(this.buyer)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("buyId", this.buyId)
       .append("providerName", this.providerName)
       .append("stockNo", this.stockNo)
       .append("price", this.price)
       .append("inCounts", this.inCounts)
       .append("amount", this.amount)
       .append("inDate", this.inDate)
       .append("buyer", this.buyer)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.InStock
 * JD-Core Version:    0.5.4
 */