 package com.htsoft.oa.model.customer;
 
 import com.htsoft.core.model.BaseModel;
 import java.math.BigDecimal;
 import java.util.Date;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class Product extends BaseModel
 {
   protected Long productId;
   protected String productName;
   protected String productModel;
   protected String unit;
   protected BigDecimal costPrice;
   protected BigDecimal salesPrice;
   protected String productDesc;
   protected Date createtime;
   protected Date updatetime;
   protected Provider provider;
 
   public Product()
   {
   }
 
   public Product(Long in_productId)
   {
     setProductId(in_productId);
   }
 
   public Provider getProvider()
   {
     return this.provider;
   }
 
   public void setProvider(Provider in_provider) {
     this.provider = in_provider;
   }
 
   public Long getProductId()
   {
     return this.productId;
   }
 
   public void setProductId(Long aValue)
   {
     this.productId = aValue;
   }
 
   public String getProductName()
   {
     return this.productName;
   }
 
   public void setProductName(String aValue)
   {
     this.productName = aValue;
   }
 
   public String getProductModel()
   {
     return this.productModel;
   }
 
   public void setProductModel(String aValue)
   {
     this.productModel = aValue;
   }
 
   public String getUnit()
   {
     return this.unit;
   }
 
   public void setUnit(String aValue)
   {
     this.unit = aValue;
   }
 
   public BigDecimal getCostPrice()
   {
     return this.costPrice;
   }
 
   public void setCostPrice(BigDecimal aValue)
   {
     this.costPrice = aValue;
   }
 
   public BigDecimal getSalesPrice()
   {
     return this.salesPrice;
   }
 
   public void setSalesPrice(BigDecimal aValue)
   {
     this.salesPrice = aValue;
   }
 
   public String getProductDesc()
   {
     return this.productDesc;
   }
 
   public void setProductDesc(String aValue)
   {
     this.productDesc = aValue;
   }
 
   public Long getProviderId()
   {
     return (getProvider() == null) ? null : getProvider().getProviderId();
   }
 
   public void setProviderId(Long aValue)
   {
     if (aValue == null) {
       this.provider = null;
     } else if (this.provider == null) {
       this.provider = new Provider(aValue);
       this.provider.setVersion(new Integer(0));
     } else {
       this.provider.setProviderId(aValue);
     }
   }
 
   public Date getCreatetime()
   {
     return this.createtime;
   }
 
   public void setCreatetime(Date aValue)
   {
     this.createtime = aValue;
   }
 
   public Date getUpdatetime() {
     return this.updatetime;
   }
 
   public void setUpdatetime(Date updatetime) {
     this.updatetime = updatetime;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof Product) {
       return false;
     }
     Product rhs = (Product)object;
     return new EqualsBuilder()
       .append(this.productId, rhs.productId)
       .append(this.productName, rhs.productName)
       .append(this.productModel, rhs.productModel)
       .append(this.unit, rhs.unit)
       .append(this.costPrice, rhs.costPrice)
       .append(this.salesPrice, rhs.salesPrice)
       .append(this.productDesc, rhs.productDesc)
       .append(this.createtime, rhs.createtime)
       .append(this.updatetime, rhs.updatetime)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.productId)
       .append(this.productName)
       .append(this.productModel)
       .append(this.unit)
       .append(this.costPrice)
       .append(this.salesPrice)
       .append(this.productDesc)
       .append(this.createtime)
       .append(this.updatetime)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("productId", this.productId)
       .append("productName", this.productName)
       .append("productModel", this.productModel)
       .append("unit", this.unit)
       .append("costPrice", this.costPrice)
       .append("salesPrice", this.salesPrice)
       .append("productDesc", this.productDesc)
       .append("createtime", this.createtime)
       .append("updatetime", this.updatetime)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.customer.Product
 * JD-Core Version:    0.5.4
 */