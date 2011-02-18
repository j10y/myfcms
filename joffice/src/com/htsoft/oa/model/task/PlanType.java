package com.htsoft.oa.model.task;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class PlanType extends BaseModel {

	@Expose
	protected Long typeId;

	@Expose
	protected String typeName;

	public PlanType() {
	}

	public PlanType(Long in_typeId) {
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
		if (!(object instanceof PlanType)) {
			return false;
		}
		PlanType rhs = (PlanType) object;
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


 
 
 
 