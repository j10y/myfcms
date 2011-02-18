package com.htsoft.oa.model.communicate;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.system.AppUser;

public class PhoneGroup extends BaseModel {

	@Expose
	protected Long groupId;

	@Expose
	protected String groupName;

	@Expose
	protected Short isShared;

	@Expose
	protected Integer sn;
	protected AppUser appUser;
	protected Set<PhoneGroup> phoneBooks = new HashSet();

	public Set<PhoneGroup> getPhoneBooks() {
		return this.phoneBooks;
	}

	public void setPhoneBooks(Set<PhoneGroup> phoneBooks) {
		this.phoneBooks = phoneBooks;
	}

	public PhoneGroup() {
	}

	public PhoneGroup(Long in_groupId) {
		setGroupId(in_groupId);
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser in_appUser) {
		this.appUser = in_appUser;
	}

	public Long getGroupId() {
		return this.groupId;
	}

	public void setGroupId(Long aValue) {
		this.groupId = aValue;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String aValue) {
		this.groupName = aValue;
	}

	public Short getIsShared() {
		return this.isShared;
	}

	public void setIsShared(Short aValue) {
		this.isShared = aValue;
	}

	public Integer getSn() {
		return this.sn;
	}

	public void setSn(Integer aValue) {
		this.sn = aValue;
	}

	public Long getUserId() {
		return (getAppUser() == null) ? null : getAppUser().getUserId();
	}

	public void setUserId(Long aValue) {
		if (aValue == null) {
			this.appUser = null;
		} else if (this.appUser == null) {
			this.appUser = new AppUser(aValue);
			this.appUser.setVersion(new Integer(0));
		} else {
			this.appUser.setUserId(aValue);
		}
	}

	public boolean equals(Object object) {
		if (!(object instanceof PhoneGroup)) {
			return false;
		}
		PhoneGroup rhs = (PhoneGroup) object;
		return new EqualsBuilder().append(this.groupId, rhs.groupId).append(this.groupName,
				rhs.groupName).append(this.isShared, rhs.isShared).append(this.sn, rhs.sn)
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.groupId).append(
				this.groupName).append(this.isShared).append(this.sn).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("groupId", this.groupId).append("groupName",
				this.groupName).append("isShared", this.isShared).append("sn", this.sn).toString();
	}

	public String getFirstKeyColumnName() {
		return "groupId";
	}

	public Long getId() {
		return this.groupId;
	}
}


 
 
 
 
 