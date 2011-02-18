package com.htsoft.oa.model.system;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class Region extends BaseModel {
	protected Long regionId;
	protected String regionName;
	protected Short regionType;
	protected Long parentId;

	public Region() {
	}

	public Region(Long in_regionId) {
		setRegionId(in_regionId);
	}

	public Long getRegionId() {
		return this.regionId;
	}

	public void setRegionId(Long aValue) {
		this.regionId = aValue;
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String aValue) {
		this.regionName = aValue;
	}

	public Short getRegionType() {
		return this.regionType;
	}

	public void setRegionType(Short aValue) {
		this.regionType = aValue;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setParentId(Long aValue) {
		this.parentId = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Region)) {
			return false;
		}
		Region rhs = (Region) object;
		return new EqualsBuilder().append(this.regionId, rhs.regionId).append(this.regionName,
				rhs.regionName).append(this.regionType, rhs.regionType).append(this.parentId,
				rhs.parentId).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.regionId).append(
				this.regionName).append(this.regionType).append(this.parentId).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("regionId", this.regionId).append("regionName",
				this.regionName).append("regionType", this.regionType).append("parentId",
				this.parentId).toString();
	}
}


 
 
 
 