package com.htsoft.oa.model.archive;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;
import com.htsoft.oa.model.system.Department;

public class ArchivesDep extends BaseModel {
	public static final Short RECEIVE_MAIN = 1;

	public static final Short RECEIVE_COPY = 0;

	public static final Short STATUS_SIGNED = 1;

	public static final Short STATUS_UNSIGNED = 0;
	protected Long archDepId;
	protected String signNo;
	protected String subject;
	protected Short status;
	protected Date signTime;
	protected String signFullname;
	protected Long signUserID;
	protected String handleFeedback;
	protected Short isMain;
	protected Archives archives;
	protected Department department;

	public ArchivesDep() {
	}

	public ArchivesDep(Long in_archDepId) {
		setArchDepId(in_archDepId);
	}

	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives in_archives) {
		this.archives = in_archives;
	}

	public Department getDepartment() {
		return this.department;
	}

	public void setDepartment(Department in_department) {
		this.department = in_department;
	}

	public Long getArchDepId() {
		return this.archDepId;
	}

	public void setArchDepId(Long aValue) {
		this.archDepId = aValue;
	}

	public String getSignNo() {
		return this.signNo;
	}

	public void setSignNo(String aValue) {
		this.signNo = aValue;
	}

	public Long getDepId() {
		return (getDepartment() == null) ? null : getDepartment().getDepId();
	}

	public void setDepId(Long aValue) {
		if (aValue == null) {
			this.department = null;
		} else if (this.department == null) {
			this.department = new Department(aValue);
			this.department.setVersion(new Integer(0));
		} else {
			this.department.setDepId(aValue);
		}
	}

	public Long getArchivesId() {
		return (getArchives() == null) ? null : getArchives().getArchivesId();
	}

	public void setArchivesId(Long aValue) {
		if (aValue == null) {
			this.archives = null;
		} else if (this.archives == null) {
			this.archives = new Archives(aValue);
			this.archives.setVersion(new Integer(0));
		} else {
			this.archives.setArchivesId(aValue);
		}
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String aValue) {
		this.subject = aValue;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short aValue) {
		this.status = aValue;
	}

	public Date getSignTime() {
		return this.signTime;
	}

	public void setSignTime(Date aValue) {
		this.signTime = aValue;
	}

	public String getSignFullname() {
		return this.signFullname;
	}

	public void setSignFullname(String aValue) {
		this.signFullname = aValue;
	}

	public Long getSignUserID() {
		return this.signUserID;
	}

	public void setSignUserID(Long signUserID) {
		this.signUserID = signUserID;
	}

	public String getHandleFeedback() {
		return this.handleFeedback;
	}

	public void setHandleFeedback(String aValue) {
		this.handleFeedback = aValue;
	}

	public Short getIsMain() {
		return this.isMain;
	}

	public void setIsMain(Short aValue) {
		this.isMain = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof ArchivesDep)) {
			return false;
		}
		ArchivesDep rhs = (ArchivesDep) object;
		return new EqualsBuilder().append(this.archDepId, rhs.archDepId).append(this.signNo,
				rhs.signNo).append(this.subject, rhs.subject).append(this.status, rhs.status)
				.append(this.signTime, rhs.signTime).append(this.signFullname, rhs.signFullname)
				.append(this.handleFeedback, rhs.handleFeedback).append(this.isMain, rhs.isMain)
				.isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.archDepId)
				.append(this.signNo).append(this.subject).append(this.status).append(this.signTime)
				.append(this.signFullname).append(this.handleFeedback).append(this.isMain)
				.toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("archDepId", this.archDepId).append("signNo",
				this.signNo).append("subject", this.subject).append("status", this.status).append(
				"signTime", this.signTime).append("signFullname", this.signFullname).append(
				"handleFeedback", this.handleFeedback).append("isMain", this.isMain).toString();
	}
}


 
 
 
 
 