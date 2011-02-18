package com.htsoft.oa.model.admin;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class OfficeGoodsType extends BaseModel {
	protected Long typeId;
	protected String typeName;

	public OfficeGoodsType() {
	}

	public OfficeGoodsType(Long in_typeId) {
		setTypeId(in_typeId);
	}

	public Long getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Long aValue) {
		this.typeId = aValue;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String aValue) {
		this.typeName = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof OfficeGoodsType)) {
			return false;
		}
		OfficeGoodsType rhs = (OfficeGoodsType) object;
		return new EqualsBuilder().append(this.typeId, rhs.typeId).append(this.typeName,
				rhs.typeName).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.typeId).append(this.typeName)
				.toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("typeId", this.typeId).append("typeName",
				this.typeName).toString();
	}
}


 
 
 
 
 