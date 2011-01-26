 package com.htsoft.oa.model.admin;
 
 import com.google.gson.annotations.Expose;
 import com.htsoft.core.model.BaseModel;
 import java.util.HashSet;
 import java.util.Set;
 import org.apache.commons.lang.builder.EqualsBuilder;
 import org.apache.commons.lang.builder.HashCodeBuilder;
 import org.apache.commons.lang.builder.ToStringBuilder;
 
 public class OfficeGoods extends BaseModel
 {
 
   @Expose
   protected Long goodsId;
 
   @Expose
   protected String goodsName;
 
   @Expose
   protected String goodsNo;
 
   @Expose
   protected String specifications;
 
   @Expose
   protected String unit;
 
   @Expose
   protected Short isWarning;
 
   @Expose
   protected String notes;
 
   @Expose
   protected Integer stockCounts;
 
   @Expose
   protected Integer warnCounts;
 
   @Expose
   protected OfficeGoodsType officeGoodsType;
   protected Set goodsApplys = new HashSet();
   protected Set inStocks = new HashSet();
 
   public Set getGoodsApplys() { return this.goodsApplys; }
 
   public void setGoodsApplys(Set goodsApplys)
   {
     this.goodsApplys = goodsApplys;
   }
 
   public Set getInStocks() {
     return this.inStocks;
   }
 
   public void setInStocks(Set inStocks) {
     this.inStocks = inStocks;
   }
 
   public OfficeGoods()
   {
   }
 
   public OfficeGoods(Long in_goodsId)
   {
     setGoodsId(in_goodsId);
   }
 
   public OfficeGoodsType getOfficeGoodsType()
   {
     return this.officeGoodsType;
   }
 
   public void setOfficeGoodsType(OfficeGoodsType in_officeGoodsType) {
     this.officeGoodsType = in_officeGoodsType;
   }
 
   public Integer getWarnCounts()
   {
     return this.warnCounts;
   }
 
   public void setWarnCounts(Integer warnCounts) {
     this.warnCounts = warnCounts;
   }
 
   public Long getGoodsId()
   {
     return this.goodsId;
   }
 
   public void setGoodsId(Long aValue)
   {
     this.goodsId = aValue;
   }
 
   public Long getTypeId()
   {
     return (getOfficeGoodsType() == null) ? null : getOfficeGoodsType().getTypeId();
   }
 
   public void setTypeId(Long aValue)
   {
     if (aValue == null) {
       this.officeGoodsType = null;
     } else if (this.officeGoodsType == null) {
       this.officeGoodsType = new OfficeGoodsType(aValue);
       this.officeGoodsType.setVersion(new Integer(0));
     } else {
       this.officeGoodsType.setTypeId(aValue);
     }
   }
 
   public String getGoodsName()
   {
     return this.goodsName;
   }
 
   public void setGoodsName(String aValue)
   {
     this.goodsName = aValue;
   }
 
   public String getGoodsNo()
   {
     return this.goodsNo;
   }
 
   public void setGoodsNo(String aValue)
   {
     this.goodsNo = aValue;
   }
 
   public String getSpecifications()
   {
     return this.specifications;
   }
 
   public void setSpecifications(String aValue)
   {
     this.specifications = aValue;
   }
 
   public String getUnit()
   {
     return this.unit;
   }
 
   public void setUnit(String aValue)
   {
     this.unit = aValue;
   }
 
   public Short getIsWarning()
   {
     return this.isWarning;
   }
 
   public void setIsWarning(Short aValue)
   {
     this.isWarning = aValue;
   }
 
   public String getNotes()
   {
     return this.notes;
   }
 
   public void setNotes(String aValue)
   {
     this.notes = aValue;
   }
 
   public Integer getStockCounts()
   {
     return this.stockCounts;
   }
 
   public void setStockCounts(Integer aValue)
   {
     this.stockCounts = aValue;
   }
 
   public boolean equals(Object object)
   {
     if (!object instanceof OfficeGoods) {
       return false;
     }
     OfficeGoods rhs = (OfficeGoods)object;
     return new EqualsBuilder()
       .append(this.goodsId, rhs.goodsId)
       .append(this.goodsName, rhs.goodsName)
       .append(this.goodsNo, rhs.goodsNo)
       .append(this.specifications, rhs.specifications)
       .append(this.unit, rhs.unit)
       .append(this.isWarning, rhs.isWarning)
       .append(this.notes, rhs.notes)
       .append(this.stockCounts, rhs.stockCounts)
       .isEquals();
   }
 
   public int hashCode()
   {
     return new HashCodeBuilder(-82280557, -700257973)
       .append(this.goodsId)
       .append(this.goodsName)
       .append(this.goodsNo)
       .append(this.specifications)
       .append(this.unit)
       .append(this.isWarning)
       .append(this.notes)
       .append(this.stockCounts)
       .toHashCode();
   }
 
   public String toString()
   {
     return new ToStringBuilder(this)
       .append("goodsId", this.goodsId)
       .append("goodsName", this.goodsName)
       .append("goodsNo", this.goodsNo)
       .append("specifications", this.specifications)
       .append("unit", this.unit)
       .append("isWarning", this.isWarning)
       .append("notes", this.notes)
       .append("stockCounts", this.stockCounts)
       .toString();
   }
 }

/* Location:           E:\JOffice1.3安装试用版10人\joffice131Tomcat6\tomcat6-joffice\webapps\joffice1.3.1\WEB-INF\classes\
 * Qualified Name:     com.htsoft.oa.model.admin.OfficeGoods
 * JD-Core Version:    0.5.4
 */