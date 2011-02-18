package com.htsoft.oa.model.archive;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class ArchivesAttend extends BaseModel {
	protected Long attendId;
	protected Long userID;
	protected String fullname;
	protected String attendType;
	protected Date executeTime;
	protected String memo;
	protected Archives archives;

	public ArchivesAttend() {
	}

	public ArchivesAttend(Long in_attendId) {
		setAttendId(in_attendId);
	}

	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives in_archives) {
		this.archives = in_archives;
	}

	public Long getAttendId() {
		return this.attendId;
	}

	public void setAttendId(Long aValue) {
		this.attendId = aValue;
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

	public Long getUserID() {
		return this.userID;
	}

	public void setUserID(Long aValue) {
		this.userID = aValue;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String aValue) {
		this.fullname = aValue;
	}

	public String getAttendType() {
		return this.attendType;
	}

	public void setAttendType(String aValue) {
		this.attendType = aValue;
	}

	public Date getExecuteTime() {
		return this.executeTime;
	}

	public void setExecuteTime(Date aValue) {
		this.executeTime = aValue;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String aValue) {
		this.memo = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof ArchivesAttend)) {
			return false;
		}
		ArchivesAttend rhs = (ArchivesAttend) object;
		return new EqualsBuilder().append(this.attendId, rhs.attendId).append(this.userID,
				rhs.userID).append(this.fullname, rhs.fullname).append(this.attendType,
				rhs.attendType).append(this.executeTime, rhs.executeTime).append(this.memo,
				rhs.memo).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.attendId).append(this.userID)
				.append(this.fullname).append(this.attendType).append(this.executeTime).append(
						this.memo).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("attendId", this.attendId).append("userID",
				this.userID).append("fullname", this.fullname)
				.append("attendType", this.attendType).append("executeTime", this.executeTime)
				.append("memo", this.memo).toString();
	}
}


 
 
 
 
 