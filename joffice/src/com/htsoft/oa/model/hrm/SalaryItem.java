package com.htsoft.oa.model.hrm;

import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class SalaryItem extends BaseModel {
	protected Long salaryItemId;
	protected String itemName;
	protected BigDecimal defaultVal;

	public SalaryItem() {
	}

	public SalaryItem(Long in_salaryItemId) {
		setSalaryItemId(in_salaryItemId);
	}

	public Long getSalaryItemId() {
		return this.salaryItemId;
	}

	public void setSalaryItemId(Long aValue) {
		this.salaryItemId = aValue;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String aValue) {
		this.itemName = aValue;
	}

	public BigDecimal getDefaultVal() {
		return this.defaultVal;
	}

	public void setDefaultVal(BigDecimal aValue) {
		this.defaultVal = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof SalaryItem)) {
			return false;
		}
		SalaryItem rhs = (SalaryItem) object;
		return new EqualsBuilder().append(this.salaryItemId, rhs.salaryItemId).append(
				this.itemName, rhs.itemName).append(this.defaultVal, rhs.defaultVal).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.salaryItemId).append(
				this.itemName).append(this.defaultVal).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("salaryItemId", this.salaryItemId).append(
				"itemName", this.itemName).append("defaultVal", this.defaultVal).toString();
	}
}


 
 
 
 