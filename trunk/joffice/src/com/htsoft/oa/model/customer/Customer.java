package com.htsoft.oa.model.customer;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class Customer extends BaseModel {

	@Expose
	protected Long customerId;

	@Expose
	protected String customerNo;

	@Expose
	protected String industryType;

	@Expose
	protected String customerSource;

	@Expose
	protected Short customerType;

	@Expose
	protected Integer companyScale;

	@Expose
	protected String customerName;

	@Expose
	protected String customerManager;

	@Expose
	protected String phone;

	@Expose
	protected String fax;

	@Expose
	protected String site;

	@Expose
	protected String email;

	@Expose
	protected String state;

	@Expose
	protected String city;

	@Expose
	protected String zip;

	@Expose
	protected String address;

	@Expose
	protected BigDecimal registerFun;

	@Expose
	protected BigDecimal turnOver;

	@Expose
	protected String currencyUnit;

	@Expose
	protected String otherDesc;

	@Expose
	protected String principal;

	@Expose
	protected String openBank;

	@Expose
	protected String accountsNo;

	@Expose
	protected String taxNo;

	@Expose
	protected String notes;

	@Expose
	protected Short rights;
	protected Set<CusLinkman> cusLinkmans = new HashSet();

	protected Set<Project> projects = new HashSet();

	protected Set<CusConnection> cusConnections = new HashSet();

	public Customer() {
	}

	public Customer(Long in_customerId) {
		setCustomerId(in_customerId);
	}

	public Set<CusLinkman> getCusLinkmans() {
		return this.cusLinkmans;
	}

	public void setCusLinkmans(Set<CusLinkman> in_cusLinkmans) {
		this.cusLinkmans = in_cusLinkmans;
	}

	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Set<CusConnection> getCusConnections() {
		return this.cusConnections;
	}

	public void setCusConnections(Set<CusConnection> cusConnections) {
		this.cusConnections = cusConnections;
	}

	public Long getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(Long aValue) {
		this.customerId = aValue;
	}

	public String getCustomerNo() {
		return this.customerNo;
	}

	public void setCustomerNo(String aValue) {
		this.customerNo = aValue;
	}

	public String getIndustryType() {
		return this.industryType;
	}

	public void setIndustryType(String aValue) {
		this.industryType = aValue;
	}

	public String getCustomerSource() {
		return this.customerSource;
	}

	public void setCustomerSource(String aValue) {
		this.customerSource = aValue;
	}

	public Integer getCompanyScale() {
		return this.companyScale;
	}

	public void setCompanyScale(Integer companyScale) {
		this.companyScale = companyScale;
	}

	public Short getCustomerType() {
		return this.customerType;
	}

	public void setCustomerType(Short aValue) {
		this.customerType = aValue;
	}

	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String aValue) {
		this.customerName = aValue;
	}

	public String getCustomerManager() {
		return this.customerManager;
	}

	public void setCustomerManager(String aValue) {
		this.customerManager = aValue;
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

	public String getSite() {
		return this.site;
	}

	public void setSite(String aValue) {
		this.site = aValue;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String aValue) {
		this.email = aValue;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String aValue) {
		this.state = aValue;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String aValue) {
		this.city = aValue;
	}

	public String getZip() {
		return this.zip;
	}

	public void setZip(String aValue) {
		this.zip = aValue;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String aValue) {
		this.address = aValue;
	}

	public BigDecimal getRegisterFun() {
		return this.registerFun;
	}

	public void setRegisterFun(BigDecimal registerFun) {
		this.registerFun = registerFun;
	}

	public BigDecimal getTurnOver() {
		return this.turnOver;
	}

	public void setTurnOver(BigDecimal aValue) {
		this.turnOver = aValue;
	}

	public String getOtherDesc() {
		return this.otherDesc;
	}

	public String getCurrencyUnit() {
		return this.currencyUnit;
	}

	public void setCurrencyUnit(String currencyUnit) {
		this.currencyUnit = currencyUnit;
	}

	public void setOtherDesc(String aValue) {
		this.otherDesc = aValue;
	}

	public String getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(String aValue) {
		this.principal = aValue;
	}

	public String getOpenBank() {
		return this.openBank;
	}

	public void setOpenBank(String aValue) {
		this.openBank = aValue;
	}

	public String getAccountsNo() {
		return this.accountsNo;
	}

	public void setAccountsNo(String aValue) {
		this.accountsNo = aValue;
	}

	public String getTaxNo() {
		return this.taxNo;
	}

	public void setTaxNo(String aValue) {
		this.taxNo = aValue;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String aValue) {
		this.notes = aValue;
	}

	public Short getRights() {
		return this.rights;
	}

	public void setRights(Short aValue) {
		this.rights = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Customer)) {
			return false;
		}
		Customer rhs = (Customer) object;
		return new EqualsBuilder().append(this.customerId, rhs.customerId).append(this.customerNo,
				rhs.customerNo).append(this.industryType, rhs.industryType).append(
				this.customerSource, rhs.customerSource)
				.append(this.customerType, rhs.customerType).append(this.companyScale,
						rhs.companyScale).append(this.customerName, rhs.customerName).append(
						this.customerManager, rhs.customerManager).append(this.phone, rhs.phone)
				.append(this.fax, rhs.fax).append(this.site, rhs.site)
				.append(this.email, rhs.email).append(this.state, rhs.state).append(this.city,
						rhs.city).append(this.zip, rhs.zip).append(this.address, rhs.address)
				.append(this.registerFun, rhs.registerFun).append(this.turnOver, rhs.turnOver)
				.append(this.currencyUnit, rhs.currencyUnit).append(this.otherDesc, rhs.otherDesc)
				.append(this.principal, rhs.principal).append(this.openBank, rhs.openBank).append(
						this.accountsNo, rhs.accountsNo).append(this.taxNo, rhs.taxNo).append(
						this.notes, rhs.notes).append(this.rights, rhs.rights).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.customerId).append(
				this.customerNo).append(this.industryType).append(this.customerSource).append(
				this.customerType).append(this.companyScale).append(this.customerName).append(
				this.customerManager).append(this.phone).append(this.fax).append(this.site).append(
				this.email).append(this.state).append(this.city).append(this.zip).append(
				this.address).append(this.registerFun).append(this.turnOver).append(
				this.currencyUnit).append(this.otherDesc).append(this.principal).append(
				this.openBank).append(this.accountsNo).append(this.taxNo).append(this.notes)
				.append(this.rights).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("customerId", this.customerId).append("customerNo",
				this.customerNo).append("industryType", this.industryType).append("customerSource",
				this.customerSource).append("customerType", this.customerType).append(
				"companyScale", this.companyScale).append("customerName", this.customerName)
				.append("customerManager", this.customerManager).append("phone", this.phone)
				.append("fax", this.fax).append("site", this.site).append("email", this.email)
				.append("state", this.state).append("city", this.city).append("zip", this.zip)
				.append("address", this.address).append("registerFun", this.registerFun).append(
						"turnOver", this.turnOver).append("currencyUnit", this.currencyUnit)
				.append("otherDesc", this.otherDesc).append("principal", this.principal).append(
						"openBank", this.openBank).append("accountsNo", this.accountsNo).append(
						"taxNo", this.taxNo).append("notes", this.notes).append("rights",
						this.rights).toString();
	}
}


 
 
 
 