package com.htsoft.oa.model.admin;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class DepreType extends BaseModel {
	protected Long depreTypeId;
	protected String typeName;
	protected Integer deprePeriod;
	protected String typeDesc;
	protected Short calMethod;

	public DepreType() {
	}

	public DepreType(Long in_depreTypeId) {
		setDepreTypeId(in_depreTypeId);
	}

	public Long getDepreTypeId() {
		return this.depreTypeId;
	}

	public void setDepreTypeId(Long aValue) {
		this.depreTypeId = aValue;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String aValue) {
		this.typeName = aValue;
	}

	public Integer getDeprePeriod() {
		return this.deprePeriod;
	}

	public void setDeprePeriod(Integer aValue) {
		this.deprePeriod = aValue;
	}

	public String getTypeDesc() {
		return this.typeDesc;
	}

	public void setTypeDesc(String aValue) {
		this.typeDesc = aValue;
	}

	public Short getCalMethod() {
		return this.calMethod;
	}

	public void setCalMethod(Short aValue) {
		this.calMethod = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof DepreType)) {
			return false;
		}
		DepreType rhs = (DepreType) object;
		return new EqualsBuilder().append(this.depreTypeId, rhs.depreTypeId).append(this.typeName,
				rhs.typeName).append(this.deprePeriod, rhs.deprePeriod).append(this.typeDesc,
				rhs.typeDesc).append(this.calMethod, rhs.calMethod).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.depreTypeId).append(
				this.typeName).append(this.deprePeriod).append(this.typeDesc)
				.append(this.calMethod).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("depreTypeId", this.depreTypeId).append("typeName",
				this.typeName).append("deprePeriod", this.deprePeriod).append("typeDesc",
				this.typeDesc).append("calMethod", this.calMethod).toString();
	}
}


 
 
 
 