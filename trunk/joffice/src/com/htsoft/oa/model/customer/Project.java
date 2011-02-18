package com.htsoft.oa.model.customer;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;

public class Project extends BaseModel {

	@Expose
	protected Long projectId;

	@Expose
	protected String projectName;

	@Expose
	protected String projectNo;

	@Expose
	protected String reqDesc;

	@Expose
	protected Short isContract;

	@Expose
	protected String fullname;

	@Expose
	protected String mobile;

	@Expose
	protected String phone;

	@Expose
	protected String fax;

	@Expose
	protected String otherContacts;

	@Expose
	protected AppUser appUser;

	@Expose
	protected Customer customer;
	protected Set contracts = new HashSet();

	@Expose
	protected Set<FileAttach> projectFiles = new HashSet();

	public Project() {
	}

	public Project(Long in_projectId) {
		setProjectId(in_projectId);
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser in_appUser) {
		this.appUser = in_appUser;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer in_customer) {
		this.customer = in_customer;
	}

	public Set getContracts() {
		return this.contracts;
	}

	public void setContracts(Set in_contracts) {
		this.contracts = in_contracts;
	}

	public Set getProjectFiles() {
		return this.projectFiles;
	}

	public void setProjectFiles(Set in_projectFiles) {
		this.projectFiles = in_projectFiles;
	}

	public Long getProjectId() {
		return this.projectId;
	}

	public void setProjectId(Long aValue) {
		this.projectId = aValue;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String aValue) {
		this.projectName = aValue;
	}

	public String getProjectNo() {
		return this.projectNo;
	}

	public void setProjectNo(String aValue) {
		this.projectNo = aValue;
	}

	public String getReqDesc() {
		return this.reqDesc;
	}

	public void setReqDesc(String aValue) {
		this.reqDesc = aValue;
	}

	public Short getIsContract() {
		return this.isContract;
	}

	public void setIsContract(Short aValue) {
		this.isContract = aValue;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String aValue) {
		this.fullname = aValue;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String aValue) {
		this.mobile = aValue;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String aValue) {
		this.phone = aValue;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String aValue) {
		this.fax = aValue;
	}

	public String getOtherContacts() {
		return this.otherContacts;
	}

	public void setOtherContacts(String aValue) {
		this.otherContacts = aValue;
	}

	public Long getCustomerId() {
		return (getCustomer() == null) ? null : getCustomer().getCustomerId();
	}

	public void setCustomerId(Long aValue) {
		if (aValue == null) {
			this.customer = null;
		} else if (this.customer == null) {
			this.customer = new Customer(aValue);
			this.customer.setVersion(new Integer(0));
		} else {
			this.customer.setCustomerId(aValue);
		}
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
		if (!(object instanceof Project)) {
			return false;
		}
		Project rhs = (Project) object;
		return new EqualsBuilder().append(this.projectId, rhs.projectId).append(this.projectName,
				rhs.projectName).append(this.projectNo, rhs.projectNo).append(this.reqDesc,
				rhs.reqDesc).append(this.isContract, rhs.isContract).append(this.fullname,
				rhs.fullname).append(this.mobile, rhs.mobile).append(this.phone, rhs.phone).append(
				this.fax, rhs.fax).append(this.otherContacts, rhs.otherContacts).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.projectId).append(
				this.projectName).append(this.projectNo).append(this.reqDesc).append(
				this.isContract).append(this.fullname).append(this.mobile).append(this.phone)
				.append(this.fax).append(this.otherContacts).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("projectId", this.projectId).append("projectName",
				this.projectName).append("projectNo", this.projectNo).append("reqDesc",
				this.reqDesc).append("isContract", this.isContract).append("fullname",
				this.fullname).append("mobile", this.mobile).append("phone", this.phone).append(
				"fax", this.fax).append("otherContacts", this.otherContacts).toString();
	}
}


 
 
 
 