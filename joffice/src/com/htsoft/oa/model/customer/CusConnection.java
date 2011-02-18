package com.htsoft.oa.model.customer;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class CusConnection extends BaseModel {
	protected Long connId;
	protected String contactor;
	protected Date startDate;
	protected Date endDate;
	protected String content;
	protected String notes;
	protected String creator;
	protected Customer customer;

	public CusConnection() {
	}

	public CusConnection(Long in_connId) {
		setConnId(in_connId);
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer in_customer) {
		this.customer = in_customer;
	}

	public Long getConnId() {
		return this.connId;
	}

	public void setConnId(Long aValue) {
		this.connId = aValue;
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

	public String getContactor() {
		return this.contactor;
	}

	public void setContactor(String aValue) {
		this.contactor = aValue;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date aValue) {
		this.startDate = aValue;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date aValue) {
		this.endDate = aValue;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String aValue) {
		this.content = aValue;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String aValue) {
		this.notes = aValue;
	}

	public String getCreator() {
		return this.creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public boolean equals(Object object) {
		if (!(object instanceof CusConnection)) {
			return false;
		}
		CusConnection rhs = (CusConnection) object;
		return new EqualsBuilder().append(this.connId, rhs.connId).append(this.contactor,
				rhs.contactor).append(this.startDate, rhs.startDate).append(this.endDate,
				rhs.endDate).append(this.content, rhs.content).append(this.notes, rhs.notes)
				.append(this.creator, rhs.creator).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.connId)
				.append(this.contactor).append(this.startDate).append(this.endDate).append(
						this.content).append(this.notes).append(this.creator).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("connId", this.connId).append("contactor",
				this.contactor).append("startDate", this.startDate).append("endDate", this.endDate)
				.append("content", this.content).append("notes", this.notes).append("creator",
						this.creator).toString();
	}
}


 
 
 
 
 