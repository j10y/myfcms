package com.htsoft.oa.model.flow;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import flexjson.JSON;

public class ProType {
	protected Long typeId;
	protected String typeName;

	public ProType() {
	}

	public ProType(Long in_typeId) {
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
		if (!(object instanceof ProType)) {
			return false;
		}
		ProType rhs = (ProType) object;
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

	@JSON(include = false)
	public String getFirstKeyColumnName() {
		return "typeId";
	}

	@JSON
	public Long getId() {
		return this.typeId;
	}
}


 
 
 
 