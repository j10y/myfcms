package com.htsoft.oa.model.archive;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.htsoft.core.model.BaseModel;

public class ArchHasten extends BaseModel {
	protected Long recordId;
	protected String content;
	protected Date createtime;
	protected String hastenFullname;
	protected String handlerFullname;
	protected Long handlerUserId;
	protected Archives archives;

	public ArchHasten() {
	}

	public ArchHasten(Long in_recordId) {
		setRecordId(in_recordId);
	}

	public Archives getArchives() {
		return this.archives;
	}

	public void setArchives(Archives in_archives) {
		this.archives = in_archives;
	}

	public Long getRecordId() {
		return this.recordId;
	}

	public void setRecordId(Long aValue) {
		this.recordId = aValue;
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

	public String getContent() {
		return this.content;
	}

	public void setContent(String aValue) {
		this.content = aValue;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date aValue) {
		this.createtime = aValue;
	}

	public String getHastenFullname() {
		return this.hastenFullname;
	}

	public void setHastenFullname(String aValue) {
		this.hastenFullname = aValue;
	}

	public String getHandlerFullname() {
		return this.handlerFullname;
	}

	public void setHandlerFullname(String aValue) {
		this.handlerFullname = aValue;
	}

	public Long getHandlerUserId() {
		return this.handlerUserId;
	}

	public void setHandlerUserId(Long aValue) {
		this.handlerUserId = aValue;
	}

	public boolean equals(Object object) {
		if (!(object instanceof ArchHasten)) {
			return false;
		}
		ArchHasten rhs = (ArchHasten) object;
		return new EqualsBuilder().append(this.recordId, rhs.recordId).append(this.content,
				rhs.content).append(this.createtime, rhs.createtime).append(this.hastenFullname,
				rhs.hastenFullname).append(this.handlerFullname, rhs.handlerFullname).append(
				this.handlerUserId, rhs.handlerUserId).isEquals();
	}

	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.recordId)
				.append(this.content).append(this.createtime).append(this.hastenFullname).append(
						this.handlerFullname).append(this.handlerUserId).toHashCode();
	}

	public String toString() {
		return new ToStringBuilder(this).append("recordId", this.recordId).append("content",
				this.content).append("createtime", this.createtime).append("hastenFullname",
				this.hastenFullname).append("handlerFullname", this.handlerFullname).append(
				"handlerUserId", this.handlerUserId).toString();
	}
}


 
 
 
 