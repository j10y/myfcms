package com.htsoft.oa.model.task;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.system.AppUser;

public class Appointment extends BaseModel {

	@Expose
	protected Long appointId;

	@Expose
	protected String subject;

	@Expose
	protected Date startTime;

	@Expose
	protected Date endTime;

	@Expose
	protected String content;

	@Expose
	protected String notes;

	@Expose
	protected String location;

	@Expose
	protected String inviteEmails;

	@Expose
	protected AppUser appUser;

	public Appointment() {
	}

	public Appointment(Long in_appointId) {
		setAppointId(in_appointId);
	}

	public AppUser getAppUser() {
		return this.appUser;
	}

	public void setAppUser(AppUser in_appUser) {
		this.appUser = in_appUser;
	}

	public Long getAppointId() {
		return this.appointId;
	}

	public void setAppointId(Long aValue) {
		this.appointId = aValue;
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

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String aValue) {
		this.subject = aValue;
	}

	public Date getStartTime() {
		return this.startTime;
	}

	public void setStartTime(Date aValue) {
		this.startTime = aValue;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date aValue) {
		this.endTime = aValue;
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

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String aValue) {
		this.location = aValue;
	}

	public String getInviteEmails() {
		return this.inviteEmails;
	}

	public void setInviteEmails(String aValue) {
		this.inviteEmails = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof Appointment)) {
			return false;
		}
		Appointment rhs = (Appointment) object;
		return new EqualsBuilder().append(this.appointId, rhs.appointId).append(this.subject,
				rhs.subject).append(this.startTime, rhs.startTime)
				.append(this.endTime, rhs.endTime).append(this.content, rhs.content).append(
						this.notes, rhs.notes).append(this.location, rhs.location).append(
						this.inviteEmails, rhs.inviteEmails).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.appointId).append(
				this.subject).append(this.startTime).append(this.endTime).append(this.content)
				.append(this.notes).append(this.location).append(this.inviteEmails).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("appointId", this.appointId).append("subject",
				this.subject).append("startTime", this.startTime).append("endTime", this.endTime)
				.append("content", this.content).append("notes", this.notes).append("location",
						this.location).append("inviteEmails", this.inviteEmails).toString();
	}
}


 
 
 
 